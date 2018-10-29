/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.controller;

import cl.feriaweb.ecommerce.bean.ClienteFacade;
import cl.feriaweb.ecommerce.entity.Cliente;
import cl.feriaweb.ecommerce.entity.EstadoUsuario;
import cl.feriaweb.ecommerce.util.constantes.Bootstrap;
import cl.feriaweb.ecommerce.util.constantes.MensajesWeb;
import cl.feriaweb.ecommerce.util.constantes.Paginas;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alejandro
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

  @EJB
  private ClienteFacade clienteFacade;

  private static final String ATTR_INTENTO_LOGIN = "ATTR_INTENTO_LOGIN";
  private static final String MAIL_LOGIN = "MAIL_LOGIN";
  private static final String LOGIN_FAILED = "login.jsp?retry=true";

  private String url;

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("RequestURL : " + request.getRequestURL().toString());
    int intentoLogin;
    String email = request.getParameter("j_username");
    String password = request.getParameter("j_password");
    Cliente cliente = clienteFacade.spBuscarPorEmail(email);
    if (cliente != null) {
      try {
        switch (cliente.getEstadoUsuarioId().getId()) {
          case EstadoUsuario.EST_ACTIVO:
            request.login(email, password);
//            cliente.getDireccionList()
            System.out.println("Direcciones : " + cliente.getDireccionList().size());
            request.getSession().setAttribute("cliente", cliente);
            url = "index.jsp";
            break;
          case EstadoUsuario.EST_INACTIVO:
            request.setAttribute("class", Bootstrap.ALERT_DANGER);
            request.setAttribute("mensaje", MensajesWeb.CUENTA_INACTIVA);
            url = Paginas.INFO;
            break;
          case EstadoUsuario.EST_PASSWORD_BLOQUEADA:
            request.setAttribute("class", Bootstrap.ALERT_DANGER);
            request.setAttribute("mensaje", MensajesWeb.PASSWORD_BLOQUEADA);
            url = Paginas.INFO;
            break;
          case EstadoUsuario.EST_POR_CONFIRMAR:
            request.setAttribute("class", Bootstrap.ALERT_DANGER);
            request.setAttribute("mensaje", MensajesWeb.CUENTA_POR_CONFIRMAR);
            url = Paginas.INFO;
            break;
          case EstadoUsuario.EST_VALIDAR_EMAIL:
            request.setAttribute("class", Bootstrap.ALERT_DANGER);
            request.setAttribute("mensaje", MensajesWeb.VALIDAR_EMAIL);
            url = Paginas.INFO;
            break;
        }
        response.getWriter().println("Login OK");
      } catch (ServletException e) {
        String emailSession = (String) request.getSession().getAttribute(MAIL_LOGIN);
        boolean flag = emailSession != null && emailSession.equals(email);
        if (!flag) {
          request.getSession().setAttribute(MAIL_LOGIN, email);
          request.getSession().setAttribute(ATTR_INTENTO_LOGIN, 0);
        }
        intentoLogin = (int) request.getSession().getAttribute(ATTR_INTENTO_LOGIN);
        if (++intentoLogin > 2) {
          bloquearPasswordCliente(cliente);
          request.setAttribute("class", Bootstrap.ALERT_DANGER);
          request.setAttribute("mensaje", MensajesWeb.PASSWORD_BLOQUEADA);
          url = Paginas.INFO;
        } else {
          request.getSession().setAttribute(ATTR_INTENTO_LOGIN, intentoLogin);
          url = LOGIN_FAILED;
        }
      }
    } else {
      request.getSession().removeAttribute(MAIL_LOGIN);
      request.getSession().removeAttribute(ATTR_INTENTO_LOGIN);
      url = LOGIN_FAILED;
    }
    request.getRequestDispatcher(url).forward(request, response);
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

  private void bloquearPasswordCliente(Cliente cliente) {
    try {
      clienteFacade.spBloquearPassword(cliente);
    } catch (Exception e) {
      log(e.getMessage(), e);
    }
  }

}
