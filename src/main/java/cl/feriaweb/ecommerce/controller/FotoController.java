/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.controller;

import cl.feriaweb.ecommerce.bean.ProductoFacade;
import cl.feriaweb.ecommerce.entity.Producto;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "FotoController", urlPatterns = {"/servlet/foto"})
public class FotoController extends HttpServlet {

  private final static Logger log = Logger.getLogger(FotoController.class.getName());
  @EJB
  private ProductoFacade productoFacade;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String id = request.getParameter("id");
      if (id != null) {
//        Producto producto = productoFacade.find(Integer.parseInt(id));
        Producto producto = productoFacade.spBuscar(Integer.parseInt(id));
        response.getOutputStream().write(producto.getFoto());
      }
    } catch (Exception e) {
    }
  }

}
