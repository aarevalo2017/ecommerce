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
@WebServlet(name = "HomeController", urlPatterns = {"/index"})
public class HomeController extends HttpServlet {

  private static final Logger log = Logger.getLogger(HomeController.class.getName());
  @EJB
  private CategoriaFacade categoriaFacade;

  @EJB
  private ProductoFacade productoFacade;

  @EJB
  private ProductoProductorFacade productoProductorFacade;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      int token = new Random().nextInt(999999999);
      request.getSession().setAttribute("token", token);
      List<BusquedaProducto> productos = productoProductorFacade.spBuscarHome();
      request.setAttribute("productos", productos);
      
//      List<Producto> menuProductos = productoFacade.findAll();
      List<Producto> menuProductos = productoFacade.spTodos();
      request.getSession().setAttribute("menuProductos", menuProductos);
      
//      List<Categoria> categorias = categoriaFacade.findAll();
      List<Categoria> categorias = categoriaFacade.spTodos();
      request.getSession().setAttribute("categorias", categorias);
//      categorias.get(0).getProductoList()
      request.getRequestDispatcher("index.jsp").forward(request, response);
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
