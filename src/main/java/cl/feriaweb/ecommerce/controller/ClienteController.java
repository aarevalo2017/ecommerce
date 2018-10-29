/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.controller;

import cl.feriaweb.ecommerce.bean.ClienteFacade;
import cl.feriaweb.ecommerce.bean.DireccionFacade;
import cl.feriaweb.ecommerce.bean.PasswordResetFacade;
import cl.feriaweb.ecommerce.entity.Cliente;
import cl.feriaweb.ecommerce.entity.Direccion;
import cl.feriaweb.ecommerce.entity.DivisionPolitica;
import cl.feriaweb.ecommerce.entity.PasswordReset;
import cl.feriaweb.ecommerce.util.EmailSenderService;
import cl.feriaweb.ecommerce.util.constantes.Bootstrap;
import cl.feriaweb.ecommerce.util.constantes.MensajesWeb;
import cl.feriaweb.ecommerce.util.constantes.Paginas;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
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
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Alejandro
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/servlet/cliente"})
public class ClienteController extends HttpServlet {

  @EJB
  private DireccionFacade direccionFacade;
  @EJB
  private PasswordResetFacade passwordResetFacade;
  @EJB
  private ClienteFacade clienteFacade;

  private static final String IN_TOKEN = "token";
  private static final String IN_ACCION = "accion";
  private static final String IN_NOMBRE = "nombre";
  private static final String IN_APELLIDOS = "apellidos";
  private static final String IN_EMAIL = "email";
  private static final String IN_TELEFONO = "telefono";
  private static final String IN_PASSWORD = "password";
  private static final String IN_PASSWORD_CONFIRM = "passwordConfirm";
  private static final String IN_RECIBE_OFERTAS = "recibeOfertas";
  private static final String ACC_RECUPERAR_PASSWORD = "recuperarPassword";
  private static final String ACC_LOGOUT = "logout";
  private static final String ACC_OBTENER_DIRECCIONES = "obtenerDirecciones";
  private static final String ACC_AGREGAR_DIRECCION = "agregarDireccion";

  final static Logger log = Logger.getLogger(ClienteController.class.getName());
  String url = "";

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try {
      String accion = request.getParameter(IN_ACCION);
      switch (accion) {
        case "create":
          agregarCliente(request, response);
          break;
        case "validar_mail":
          validarMail(request, response);
          break;
        case ACC_RECUPERAR_PASSWORD:
          enviarEnlaceDeRecuperacion(request, response);
          break;
        case ACC_LOGOUT:
          logout(request, response);
          break;
        case ACC_OBTENER_DIRECCIONES:
          obtenerDirecciones(request, response);
          break;
        case ACC_AGREGAR_DIRECCION:
          agregarDireccion(request, response);
          break;
        default:
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
          break;
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  private void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String nombre = request.getParameter(IN_NOMBRE);
      String apellidos = request.getParameter(IN_APELLIDOS);
      String email = request.getParameter(IN_EMAIL);
      String telefono = request.getParameter(IN_TELEFONO);
      String password = request.getParameter(IN_PASSWORD);
      String passwordConfirm = request.getParameter(IN_PASSWORD_CONFIRM);
      boolean recibeOfertas = request.getParameter(IN_RECIBE_OFERTAS).equals("1");
      if (existeEmailRegistrado(email)) {
        request.setAttribute("class", Bootstrap.ALERT_WARNING);
        request.setAttribute("mensaje", MensajesWeb.MSG_EMAIL_EXISTE.replace("_email_", email));
        url = Paginas.INFO;
        return;
      }
      if (validaPassword(password, passwordConfirm)) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);
        cliente.setPassword(DigestUtils.sha256Hex(password));
        cliente.setRecibeBoletin(recibeOfertas);
        cliente.setEliminado(false);
        cliente.setActivo(true);
        clienteFacade.spAgregar(cliente);
        request.setAttribute("class", Bootstrap.ALERT_SUCCESS);
        request.setAttribute("mensaje", MensajesWeb.MSG_CLIENTE_REGISTRADO.replace("_email_", email));
        url = Paginas.INFO;
      } else {
        log.info("Error en contraseñas.");
        request.setAttribute("error", "Contraseñas no coinciden");
        url = "/registro.jsp";
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      request.getRequestDispatcher(url).forward(request, response);
    }
  }

  private boolean validaPassword(String password, String passwordConfirm) {
    return password.equals(passwordConfirm);
  }

  private void validarMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try {
      boolean existeToken = false;
      boolean estaVigente = false;
      boolean existeCliente = false;
      Cliente cliente = null;
      String token = request.getParameter(IN_TOKEN);
      PasswordReset passwordReset = passwordResetFacade.spBuscarPorToken(token);
      existeToken = passwordReset != null;
      if (existeToken) {
        cliente = clienteFacade.spBuscarPorEmail(passwordReset.getEmail());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(passwordReset.getFechaCreacion());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        estaVigente = calendar.getTime().compareTo(new Date()) > 0;
      }
      existeCliente = cliente != null;
      if (existeToken && estaVigente && existeCliente) {
        clienteFacade.spActivarCuenta(cliente);
        passwordResetFacade.spEliminar(passwordReset);
        request.setAttribute("mensaje", MensajesWeb.MSG_CUENTA_CONFIRMADA.replace("_nombre_", cliente.getNombre()));
        request.setAttribute("class", Bootstrap.ALERT_SUCCESS);
      } else {
        request.setAttribute("mensaje", MensajesWeb.MSG_TOKEN_NO_VALIDO);
        request.setAttribute("class", Bootstrap.ALERT_DANGER);
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      request.getRequestDispatcher(Paginas.INFO).forward(request, response);
    }
  }

  private boolean existeEmailRegistrado(String email) {
    boolean resp = false;
    try {
      resp = clienteFacade.spBuscarPorEmail(email) != null;
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
    return resp;
  }

  private void enviarEnlaceDeRecuperacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String email = request.getParameter(IN_EMAIL).toLowerCase();
      Cliente cliente = clienteFacade.spBuscarPorEmail(email);
      if (cliente == null) {
        request.setAttribute("class", Bootstrap.ALERT_DANGER);
        request.setAttribute("mensaje", MensajesWeb.MSG_EMAIL_NO_REGISTRADO.replace("_email_", email));
        url = Paginas.INFO;
        return;
      }
      PasswordReset passwordReset = new PasswordReset(email);
      passwordResetFacade.spAgregar(passwordReset);
      EmailSenderService ess = new EmailSenderService();
      ess.enviarEnlaceDeRecuperacion(cliente, passwordReset);
      request.setAttribute("class", Bootstrap.ALERT_SUCCESS);
      request.setAttribute("mensaje", MensajesWeb.MSG_ENLACE_RECUPERACION.replace("_email_", email));
      url = Paginas.INFO;
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      request.getRequestDispatcher(url).forward(request, response);
    }
  }

  private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    request.logout();
    response.sendRedirect(request.getContextPath() + "/index.jsp");
  }

  private void obtenerDirecciones(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    response.setCharacterEncoding("utf-8");
    response.setContentType("application/json");
    JsonArrayBuilder jsonResp = Json.createArrayBuilder();
    try {
      int idc = Integer.parseInt(request.getParameter("idc"));
      Cliente cliente = clienteFacade.spBuscar(idc);
      List<Direccion> direcciones = cliente.getDireccionList();
      for (Direccion direccion : direcciones) {
        System.out.println(direccion.getAlias());
        String comuna = DivisionPolitica.getComunaByCodigo(String.valueOf(direccion.getCodigoComuna())).getNombre();
        jsonResp.add(Json.createObjectBuilder()
                .add("id", direccion.getId())
                .add("alias", direccion.getAlias())
                .add("direccion", direccion.getDireccion())
                .add("comuna", comuna)
        );
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      JsonObjectBuilder obj = Json.createObjectBuilder().add("error", e.getMessage());
      jsonResp.add(obj);
    } finally {
      out.print(jsonResp.build());
    }
  }

  private void agregarDireccion(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.print("agegar direccion");
    response.setCharacterEncoding("utf-8");
    response.setContentType("application/json");
    JsonArrayBuilder jsonResp = Json.createArrayBuilder();
    try {
      Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
      String direccion = request.getParameter("direccion");
      int codigoComuna = Integer.parseInt(request.getParameter("comuna"));
      int codPostal = Integer.parseInt(request.getParameter("cod-postal"));
      String alias = request.getParameter("alias");
      Direccion dir = new Direccion();
      dir.setDireccion(direccion);
      dir.setCodigoComuna(codigoComuna);
      dir.setCodigoPostal(codPostal);
      dir.setAlias(alias);
      dir.setApellidos("Arevalo");
      dir.setClienteId(cliente);
      dir.setCodigoUbicacion("asdf");
      dir.setEliminado(false);
      dir.setLatitud(BigDecimal.valueOf(-33.555));
      dir.setLongitud(BigDecimal.valueOf(-70.555));
      dir.setNombre("Alejandro");
      direccionFacade.spAgregar(dir);
        jsonResp.add(Json.createObjectBuilder()
                .add("id", dir.getId())
                .add("alias", dir.getAlias())
                .add("direccion", dir.getDireccion())
                .add("comuna", dir.getCodigoComuna())
        );
    } catch (Exception e) {
      System.out.println(e.getMessage());
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      JsonObjectBuilder obj = Json.createObjectBuilder().add("error", e.getMessage());
      jsonResp.add(obj);
    } finally {
      out.print(jsonResp.build());
    }
  }
}
