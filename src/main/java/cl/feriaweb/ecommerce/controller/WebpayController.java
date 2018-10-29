/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.controller;

import cl.feriaweb.ecommerce.bean.ClienteFacade;
import cl.feriaweb.ecommerce.bean.OrdenCompraFacade;
import cl.feriaweb.ecommerce.bean.TipoDespachoFacade;
import cl.feriaweb.ecommerce.bean.TipoPagoFacade;
import cl.feriaweb.ecommerce.entity.CarroCompra;
import cl.feriaweb.ecommerce.entity.DetalleCarro;
import cl.feriaweb.ecommerce.entity.OrdenCompra;
import cl.feriaweb.ecommerce.util.WebpayUtils;
import cl.feriaweb.ecommerce.util.constantes.Bootstrap;
import cl.feriaweb.ecommerce.util.constantes.MensajesWeb;
import cl.transbank.webpay.Webpay;
import com.transbank.webpay.wswebpay.service.TransactionResultOutput;
import com.transbank.webpay.wswebpay.service.WsInitTransactionOutput;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author Alejandro
 */
@WebServlet(name = "WebpayController", urlPatterns = {"/webpay"})
@ServletSecurity(
        @HttpConstraint(rolesAllowed = "CLIENTE"))
public class WebpayController extends HttpServlet {

  private static final Logger log = Logger.getLogger(WebpayController.class.getName());
  @EJB
  private OrdenCompraFacade ordenCompraFacade;
  @EJB
  private TipoDespachoFacade tipoDespachoFacade;
  @EJB
  private ClienteFacade clienteFacade;
  @EJB
  private TipoPagoFacade tipoPagoFacade;

  private final String CARRO_COMPRA = "CARRO_COMPRA";
  private static final String IN_ACTION = "action";
  private static final String WEBPAY_NORMAL_INIT = "webpayNormalInit";
  private static final String WEBPAY_NORMAL_GET_RESULT = "webpayNormalGetResult";
  private static final String WEBPAY_NORMAL_END = "end";

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String action = request.getParameter(IN_ACTION);
      switch (action) {
        case WEBPAY_NORMAL_INIT:
          webpayNormalInit(request, response);
          break;
        case WEBPAY_NORMAL_GET_RESULT:
          webpayNormalGetResult(request, response);
          break;
        case WEBPAY_NORMAL_END:
          finalizarPago(request, response);
          break;
        default:
          response.sendError(HttpServletResponse.SC_BAD_REQUEST);
          break;
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
      response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
  }

  private void webpayNormalInit(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Enumeration<String> params = request.getParameterNames();
    while (params.hasMoreElements()) {
      System.out.println("parametro webpayNormalInit : " + params.nextElement());
    }
    CarroCompra carro = (CarroCompra) request.getSession().getAttribute(CARRO_COMPRA);

    int totalCarro = 0;
    for (DetalleCarro detalleCarro : carro.getDetalleCarroList()) {
      totalCarro += (detalleCarro.getCantidad() * detalleCarro.getProductoProductorId().getPrecio());
    }
    OrdenCompra ordenCompra = new OrdenCompra();
    ordenCompra.setTotal(totalCarro);
    ordenCompra.setFechaCompra(new Date());
    ordenCompra.setFechaModificacion(new Date());
    ordenCompra.setTipoPagoId(tipoPagoFacade.spBuscar(1));
    ordenCompra.setIpOrigen(request.getRemoteAddr());
    ordenCompra.setClienteId(clienteFacade.spBuscar(1));
    ordenCompra.setTipoDespachoId(tipoDespachoFacade.spBuscar(1));
    request.getSession().setAttribute("oc", ordenCompra);
    String buyOrder = RandomStringUtils.randomAlphanumeric(16);
    Webpay webpay = new Webpay(WebpayUtils.getConfiguration());
    WsInitTransactionOutput result = new WsInitTransactionOutput();
    String idSession, urlReturn, urlFinal;
    int amount = 0;
    try {
      urlReturn = request.getRequestURL().toString() + "?action=webpayNormalGetResult";
      urlFinal = request.getRequestURL().toString() + "?action=end";
      amount = totalCarro;
      idSession = request.getSession().getId();
      result = webpay.getNormalTransaction().initTransaction(amount, idSession, buyOrder, urlReturn, urlFinal);
      if (result.getToken() != null) {
        log.log(Level.INFO, "Sesion iniciada con exito en Webpay");
        request.setAttribute("amount", amount);
        request.setAttribute("idSession", idSession);
        request.setAttribute("buyOrder", buyOrder);
        request.setAttribute("urlReturn", urlReturn);
        request.setAttribute("urlFinal", urlFinal);
        request.setAttribute("url", result.getUrl());
        request.setAttribute("token", result.getToken());
        request.getRequestDispatcher("pagotbk.jsp").forward(request, response);
      } else {
        log.log(Level.SEVERE, "Ocurrio un error en la operacion InitTransaction Webpay.");
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
  }

  private void webpayNormalGetResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Enumeration<String> params = request.getParameterNames();
    while (params.hasMoreElements()) {
      System.out.println("parametro webpayNormalGetResult : " + params.nextElement());
    }
    PrintWriter out = response.getWriter();
    TransactionResultOutput result = new TransactionResultOutput();
    String token = "";
    int responseCode = 0;
    try {
      token = request.getParameter("token_ws");
      Webpay webpay = new Webpay(WebpayUtils.getConfiguration());
      result = webpay.getNormalTransaction().getTransactionResult(token);
      if (!result.getDetailOutput().isEmpty()) {
        String accountingDate = result.getAccountingDate();
        String cardExpirationDate = result.getCardDetail().getCardExpirationDate();
        String cardNumber = result.getCardDetail().getCardNumber();
        String authorizationCode = result.getDetailOutput().get(0).getAuthorizationCode();
        String authorizedAmount = result.getDetailOutput().get(0).getAmount().toString();
        String buyOrder = result.getDetailOutput().get(0).getBuyOrder();
        String paymentTypeCode = result.getDetailOutput().get(0).getPaymentTypeCode();
        responseCode = result.getDetailOutput().get(0).getResponseCode();
        Integer sharesNumber = result.getDetailOutput().get(0).getSharesNumber();
        String commerceCode = result.getDetailOutput().get(0).getCommerceCode();
        request.setAttribute("accountingDate", accountingDate);
        request.setAttribute("cardExpirationDate", cardExpirationDate);
        request.setAttribute("cardNumber", cardNumber);
        request.setAttribute("authorizationCode", authorizationCode);
        request.setAttribute("authorizedAmount", authorizedAmount);
        request.setAttribute("buyOrder", buyOrder);
        request.setAttribute("paymentTypeCode", paymentTypeCode);
        request.setAttribute("sharesNumber", sharesNumber);
        request.setAttribute("commerceCode", commerceCode);
        request.setAttribute("responseCode", responseCode);
      }
      System.out.println("Codigo respuesta Webpay : " + responseCode);
      if (responseCode != 0) {
        log.log(Level.INFO, "Pago rechazado por Transbank.");
        request.setAttribute("mensaje", MensajesWeb.MSG_PAGO_CANCELADO);
        request.setAttribute("class", Bootstrap.ALERT_DANGER);
        request.getRequestDispatcher("info.jsp").forward(request, response);
        return;
      } else {
        log.log(Level.INFO, "Pago aceptado por Transbank.");
        OrdenCompra ordenCompra = (OrdenCompra) request.getSession().getAttribute("oc");
        ordenCompraFacade.spAgregar(ordenCompra);
        request.getSession().removeAttribute(CARRO_COMPRA);
      }
      String sessionId = result.getSessionId();
      String transactionDate = result.getTransactionDate().toString();
      String urlRedirection = result.getUrlRedirection();
      String vci = result.getVCI();
      request.setAttribute("url", result.getUrlRedirection());
      request.setAttribute("token", token);
      request.setAttribute("sessionId", sessionId);
      request.setAttribute("transactionDate", transactionDate);
      request.setAttribute("urlRedirection", urlRedirection);
      request.setAttribute("vci", vci);
      request.getRequestDispatcher("procesarpago.jsp").forward(request, response);
    } catch (Exception e) {
      System.out.println("ERROR: " + e);
    }
  }

  private void finalizarPago(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
    try {
      PrintWriter out = response.getWriter();
      Enumeration<String> params = request.getParameterNames();
      while (params.hasMoreElements()) {
        String p = params.nextElement();
        System.out.println("parametro finalizarPago : " + p + " - " + request.getParameter(p));
      }
      String token = request.getParameter("TBK_TOKEN"); //Este parametro nulo, el pago es exitoso
      String token_ws = request.getParameter("token_ws"); // Este parametro nulo, el pago fue cancelado.
      if (token != null) {
        log.log(Level.INFO, "Transacción cancelada.");
        request.getRequestDispatcher("carro/carro.jsp").forward(request, response);
        return;
      } else {
        request.getRequestDispatcher("wpend.jsp").forward(request, response);
        return;
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
  }

}
