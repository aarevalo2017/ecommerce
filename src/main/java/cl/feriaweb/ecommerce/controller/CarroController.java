/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.controller;

import cl.feriaweb.ecommerce.bean.ProductoFacade;
import cl.feriaweb.ecommerce.bean.ProductoProductorFacade;
import cl.feriaweb.ecommerce.entity.CarroCompra;
import cl.feriaweb.ecommerce.entity.DetalleCarro;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alejandro
 */
@WebServlet(name = "CarroController", urlPatterns = {"/servlet/carro/*"})
public class CarroController extends HttpServlet {

  private final String IN_ACCION = "action";
  private final String CARRO_COMPRA = "CARRO_COMPRA";
  private final String ACC_AGREGAR_PRODUCTO = "agregar";
  private final String ACC_ELIMINAR_PRODUCTO = "eliminar";
  private final String ACC_ACTUALIZAR = "actualizar";

  private static final Logger log = Logger.getLogger(CarroController.class.getName());

  @EJB
  private ProductoProductorFacade productoProductorFacade;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    response.setCharacterEncoding("utf-8");
    response.setContentType("application/json");
    JsonArrayBuilder jsonResp = Json.createArrayBuilder();
    try {
      CarroCompra carro = (CarroCompra) request.getSession().getAttribute(CARRO_COMPRA);
      if (carro == null) {
        carro = new CarroCompra();
        request.getSession().setAttribute(CARRO_COMPRA, carro);
      }

      if (carro.getDetalleCarroList() == null) {
        carro.setDetalleCarroList(new ArrayList());
      }

      int index = 0;
      for (DetalleCarro detalleCarro : carro.getDetalleCarroList()) {
        int id = detalleCarro.getProductoProductorId().getProductoId().getId();
        String nombre = detalleCarro.getProductoProductorId().getProductoId().getNombre();
        String url_imagen = detalleCarro.getProductoProductorId().getProductoId().getDescripcionHtml();
        int cantidad = detalleCarro.getCantidad();
        int precio = detalleCarro.getProductoProductorId().getPrecio();
        String medida = detalleCarro.getProductoProductorId().getProductoId().getUnidadMedidaId().getNombre();
        JsonObjectBuilder jsonProducto = Json.createObjectBuilder();
        jsonProducto.add("id", index++);
        jsonProducto.add("nombre", nombre);
        jsonProducto.add("url_imagen", url_imagen);
        jsonProducto.add("cantidad", cantidad);
        jsonProducto.add("medida", medida);
        jsonProducto.add("precio", precio);
        jsonProducto.add("url_pdp", request.getContextPath() + "/servlet/producto?accion=pdp&idp=" + id);
        jsonResp.add(jsonProducto);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      JsonObjectBuilder obj = Json.createObjectBuilder().add("error", "kjh");
      jsonResp.add(obj);
    } finally {
      out.print(jsonResp.build());
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String accion = request.getParameter(IN_ACCION);
      switch (accion) {
        case ACC_AGREGAR_PRODUCTO:
          agregarProducto(request, response);
          break;
        case ACC_ELIMINAR_PRODUCTO:
          eliminarProducto(request, response);
          break;
        case ACC_ACTUALIZAR:
          actualizarProducto(request, response);
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

  private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    response.setCharacterEncoding("UTF-8");
    JsonObjectBuilder jsonResp = null;
    try {
//      String csrf_token = request.getParameter("csrf_token");
//      if (csrf_token == null || !csrf_token.equals(request.getSession().getId())) {
//        response.sendError(HttpServletResponse.SC_FORBIDDEN);
//      }
      CarroCompra carro = (CarroCompra) request.getSession().getAttribute(CARRO_COMPRA);
      if (carro == null) {
        carro = new CarroCompra();
        request.getSession().setAttribute(CARRO_COMPRA, carro);
      }

      int itemId = Integer.parseInt(request.getParameter("item_id"));

      if (carro.getDetalleCarroList() == null) {
        carro.setDetalleCarroList(new ArrayList());
      }

      carro.getDetalleCarroList().remove(itemId);
      jsonResp = Json.createObjectBuilder().add("ok", "Item eliminado del carrito.");

    } catch (Exception e) {
      jsonResp = Json.createObjectBuilder().add("error", e.getMessage());
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      out.print(jsonResp.build());
    }
  }

  private void agregarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    response.setCharacterEncoding("UTF-8");
    JsonObjectBuilder jsonResp = null;
//    jsonResp = Json.createObjectBuilder().add("redirect", "creado");
    try {
//      String csrf_token = request.getParameter("csrf_token");
//      if (csrf_token == null || !csrf_token.equals(request.getSession().getId())) {
//        response.sendError(HttpServletResponse.SC_FORBIDDEN);
//      }
      CarroCompra carro = (CarroCompra) request.getSession().getAttribute(CARRO_COMPRA);
      if (carro == null) {
        carro = new CarroCompra();
        request.getSession().setAttribute(CARRO_COMPRA, carro);
      }
      int idProductoProductor = Integer.parseInt(request.getParameter("id_producto"));
      int cantidad = Integer.parseInt(request.getParameter("cantidad"));
      if (carro.getDetalleCarroList() == null) {
        carro.setDetalleCarroList(new ArrayList());
      }
      List<DetalleCarro> detalleItem = carro.getDetalleCarroList();
      DetalleCarro item = new DetalleCarro();
      item.setId(detalleItem.size());
      item.setCantidad(cantidad);
      item.setProductoProductorId(productoProductorFacade.spBuscar(idProductoProductor));
      boolean existeItem = false;
      for (DetalleCarro detalle : detalleItem) {
        if (detalle.getProductoProductorId().getId() == item.getProductoProductorId().getId()) {
          detalle.setCantidad(detalle.getCantidad() + item.getCantidad());
          existeItem = true;
          break;
        }
      }
      if (!existeItem) {
        carro.getDetalleCarroList().add(item);
      }
      int totalCarro = 0;
      for (DetalleCarro detalleCarro : carro.getDetalleCarroList()) {
        totalCarro += (detalleCarro.getCantidad() * detalleCarro.getProductoProductorId().getPrecio());
      }
      int totalItems = carro.getDetalleCarroList().size();
      jsonResp = Json.createObjectBuilder().add("success", "Success: You have added <a href=\"https://demo.opencart.com/index.php?route=product/product&amp;product_id=41\">iMac</a> to your <a href=\"https://demo.opencart.com/index.php?route=checkout/cart\">shopping cart</a>!")
              .add("total", totalItems + " Producto(s) - $ " + totalCarro);
    } catch (Exception e) {
      jsonResp = Json.createObjectBuilder().add("redirect", "redirect...");
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      out.print(jsonResp.build());
    }
  }

  private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    response.setCharacterEncoding("UTF-8");
    JsonObjectBuilder jsonResp = null;
    try {
      CarroCompra carro = (CarroCompra) request.getSession().getAttribute(CARRO_COMPRA);
      int idProductoProductor = Integer.parseInt(request.getParameter("id_producto"));
      int cant = Integer.parseInt(request.getParameter("cant"));
      List<DetalleCarro> detalleItem = carro.getDetalleCarroList();
      for (DetalleCarro detalle : detalleItem) {
        if (detalle.getProductoProductorId().getId() == idProductoProductor) {
          detalle.setCantidad(detalle.getCantidad() + cant);
          break;
        }
      }
      int totalCarro = 0;
      for (DetalleCarro detalleCarro : carro.getDetalleCarroList()) {
        totalCarro += (detalleCarro.getCantidad() * detalleCarro.getProductoProductorId().getPrecio());
      }
      int totalItems = carro.getDetalleCarroList().size();
      jsonResp = Json.createObjectBuilder().add("totalCarro", totalCarro)
              .add("totalItems", totalItems);
    } catch (Exception e) {
      jsonResp = Json.createObjectBuilder().add("error", e.getMessage());
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      out.print(jsonResp.build());
    }
  }
}
