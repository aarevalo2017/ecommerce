/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.controller;

import cl.feriaweb.ecommerce.bean.CategoriaFacade;
import cl.feriaweb.ecommerce.bean.ProductoFacade;
import cl.feriaweb.ecommerce.bean.ProductoProductorFacade;
import cl.feriaweb.ecommerce.entity.BusquedaProducto;
import cl.feriaweb.ecommerce.entity.Categoria;
import cl.feriaweb.ecommerce.entity.Producto;
import cl.feriaweb.ecommerce.entity.ProductoProductor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ProductoController", urlPatterns = {"/servlet/producto"})
public class ProductoController extends HttpServlet {

  @EJB
  private ProductoFacade productoFacade;

  private static final String IN_CRITERIO = "criterio";
  private static final String IN_ACCION = "accion";
  private static final String IN_ID_PRODUCTO = "idp";
  private static final String IN_NOMBRE = "nombre";
  private static final String IN_APELLIDOS = "apellidos";
  private static final String IN_EMAIL = "email";
  private static final String IN_TELEFONO = "telefono";
  private static final String IN_PASSWORD = "password";
  private static final String IN_PASSWORD_CONFIRM = "passwordConfirm";
  private static final String IN_RECIBE_OFERTAS = "recibeOfertas";

  private static final String ACC_RECUPERAR_PASSWORD = "recuperarPassword";
  private static final String ACC_LOGOUT = "logout";
  private static final String ACC_BUSCAR_PRODUCTO = "buscarProducto";
  private static final String ACC_PDP = "pdp";

  @EJB
  private CategoriaFacade categoriaFacade;

  @EJB
  private ProductoProductorFacade productoProductorFacade;
  private static final Logger log = Logger.getLogger(ProductoController.class.getName());

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
    try {
      String accion = request.getParameter(IN_ACCION);
      switch (accion) {
        case ACC_BUSCAR_PRODUCTO:
          buscarProducto(request, response);
          break;
        case ACC_PDP:
          getPdp(request, response);
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
//    response.getWriter().print(request.getParameter("accion"));
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

  private void buscarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      int token = new Random().nextInt(999999999);
      request.getSession().setAttribute("token", token);
      String criterio = request.getParameter(IN_CRITERIO);
//      List<ProductoProductor> productos = productoProductorFacade.spBuscarTodos(criterio);
      List<BusquedaProducto> productos = productoProductorFacade.spBuscarProductos(criterio);
      request.setAttribute("productos", productos);
//      request.getRequestDispatcher("/plp.jsp").forward(request, response);
      request.getRequestDispatcher("/plp_1.jsp").forward(request, response);
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  private void getPdp(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      int idp = Integer.parseInt(request.getParameter(IN_ID_PRODUCTO));
//      ProductoProductor producto = productoProductorFacade.find(idp);
      Producto producto = productoFacade.find(idp);
      List<ProductoProductor> productores = producto.getProductoProductorList();
      request.setAttribute("producto", producto);
      request.setAttribute("productores", productores);
      request.getRequestDispatcher("/pdp.jsp").forward(request, response);
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

}
