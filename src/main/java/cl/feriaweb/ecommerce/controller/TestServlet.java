/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.controller;

import cl.feriaweb.ecommerce.bean.ClienteFacade;
import cl.feriaweb.ecommerce.bean.FotoFacade;
import cl.feriaweb.ecommerce.bean.ProductoFacade;
import cl.feriaweb.ecommerce.bean.ProductoProductorFacade;
import cl.feriaweb.ecommerce.bean.ProductorFacade;
import cl.feriaweb.ecommerce.bean.TipoPagoFacade;
import cl.feriaweb.ecommerce.entity.BusquedaProducto;
import cl.feriaweb.ecommerce.entity.Cliente;
import cl.feriaweb.ecommerce.entity.Foto;
import cl.feriaweb.ecommerce.entity.Producto;
import cl.feriaweb.ecommerce.entity.TipoPago;
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.instance.TMNewMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author Alejandro
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/test"})
public class TestServlet extends HttpServlet {

  @EJB
  private FotoFacade fotoFacade;

  @EJB
  private ProductoFacade productoFacade;

  @EJB
  private ProductorFacade productorFacade;

  @EJB
  private ClienteFacade clienteFacade;

  @Resource(mappedName = "jmsVegaMail")
  private Queue vegaMail;

  @Inject
  @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
  private JMSContext context;

  @Resource(name = "vegaOnlineMail")
  private Session mailgmail;

  @EJB
  private TipoPagoFacade tipoPagoFacade;

  @EJB
  private ProductoProductorFacade productoProductorFacade;

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
//    System.out.println(System.getProperty("com.sun.aas.instanceRoot"));
//    System.out.println(request.getContextPath());
//    System.out.println(request.getServletPath());
//    System.out.println(request.getServletPath());
//    System.out.println(request.getServletContext().getRealPath(""));
//**********************************************************************************************************************************
    String SAVE_DIR = "img/productos";
    String appPath = request.getServletContext().getRealPath("");
    String savePath = appPath + File.separator + SAVE_DIR;
    File fileSaveDir = new File(savePath);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdir();
    }
    List<Producto> productos = productoFacade.findAll();
//    Producto producto = productoFacade.find(20);
    URL url;
    InputStream is;
    byte[] bytes;
    OutputStream out;
    for (Producto producto : productos) {
//      url = new URL(producto.getDescripcionHtml());
//      String nombreArchivo = producto.getNombre().replaceAll(" ", "_") + ".jpg";
      String nombreArchivo = RandomStringUtils.randomAlphanumeric(16) + ".jpg";
//      is = url.openStream();
//      bytes = IOUtils.toByteArray(is);
      out = new FileOutputStream(new File(savePath + File.separator + nombreArchivo));
//      out.write(bytes);
      if (producto.getFoto() != null) {
        out.write(producto.getFoto());
      }
      producto.setDescripcionHtml("/" + SAVE_DIR + "/" + nombreArchivo);
      productoFacade.edit(producto);
      System.out.println(nombreArchivo + " cargada.");
    }
    response.getWriter().print("foto cargada");
    //**********************************************************************************************************************************
    //    List<Producto> productos = productoFacade.findAll();
    ////    Producto producto = productoFacade.find(20);
    //    for (Producto producto : productos) {
    //      URL url = null;
    //      try {
    //        url = new URL(producto.getDescripcionHtml());
    //      } catch (Exception e) {
    //        System.out.println(e.getMessage());
    //      }
    //      if (url != null) {
    //        byte[] arr = null;
    //        try (InputStream is = url.openStream()) {
    //          arr = IOUtils.toByteArray(is);
    //          producto.setFoto(arr);
    //          productoFacade.edit(producto);
    //        } catch (IOException ex) {
    //          System.out.println(ex.getMessage());
    //        }
    //        response.getOutputStream().write(arr);
    //      }
    //    }
    // **********************************************************************************************************************************
    //    String msg = request.getParameter("msg");
    //    RestClient client = new RestClient("alejandroarevalo", "C2O0g7b2agtN2zgVWAMZPO4EnvyMEi");
    //    TMNewMessage m = client.getResource(TMNewMessage.class);
    //    m.setText(msg);
    //    m.setPhones(Arrays.asList(new String[]{"+56996091811"}));
    //    try {
    //      m.send();
    //    } catch (final RestException e) {
    //      System.out.println(e.getErrors());
    //      throw new RuntimeException(e);
    //    }
    //    System.out.println(m.getId());
    //    response.getWriter().print("Revisa tu celu !!!");
    //    **********************************************************************************************************************************
    //    String token = "asdfasdfasdfasdfasdfasfdasdfasdf";
    //    Cliente cliente = clienteFacade.find(1);
    //    String jms = "enviarMailBienvenida-" + cliente.getNombre() + " " + cliente.getApellidos() + "-alej.arevalo@alumnos.duoc.cl-" + token;
    //    sendJMSMessageToJmsVegaMail(jms);
    //    response.setContentType("text/html;charset=UTF-8");
    //    String to = "alej.arevalo@alumnos.duoc.cl";
    //    String subject = "Test";
    //    String body = "Esta es una prueba";
    //    try {
    //      sendMail(to, subject, body);
    //    } catch (Exception e) {
    //      System.out.println(e.getMessage());
    //    }
    {

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

  private void printEstados(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println(request.getAuthType() + "<br>");
      out.println(request.getCharacterEncoding() + "<br>");
      out.println(request.getContentType() + "<br>");
      out.println(request.getContextPath() + "<br>");
      out.println(request.getLocalAddr() + "<br>");
      out.println(request.getLocalName() + "<br>");
      out.println(request.getMethod() + "<br>");
      out.println(request.getPathInfo() + "<br>");
      out.println(request.getPathTranslated() + "<br>");
      out.println(request.getProtocol() + "<br>");
      out.println(request.getQueryString() + "<br>");
      out.println(request.getRemoteAddr() + "<br>");
      out.println(request.getRemoteUser() + "<br>");
      out.println(request.getRequestURI() + "<br>");
      out.println(request.getRequestedSessionId() + "<br>");
      out.println(request.getScheme() + "<br>");
      out.println(request.getServerName() + "<br>");
      out.println(request.getServletPath() + "<br>");
      out.println(request.getServletContext().getServletContextName() + "<br>");
    }
  }

  private void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
    MimeMessage message = new MimeMessage(mailgmail);
    message.setSubject(subject);
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
    message.setText(body);
    Transport.send(message);
  }

  private void sendJMSMessageToJmsVegaMail(String messageData) {
    context.createProducer().send(vegaMail, messageData);
  }

}
