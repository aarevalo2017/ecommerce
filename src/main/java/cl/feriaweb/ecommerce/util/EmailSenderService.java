/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.util;

/**
 *
 * @author Alejandro
 */
import cl.feriaweb.ecommerce.entity.Cliente;
import cl.feriaweb.ecommerce.entity.PasswordReset;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EmailSenderService {

  private final Properties properties = new Properties();

  private final String PASSWORD = "portafolio2018";
  private String bodyMailBienvenida = "<div style='text-align:center'>\n"
          + "<h1>Bienvenido a La Vega OnLine</h1><br>\n"
          + "<h3>Estimado : _nombre_</h3>\n"
          + "<p>Gracias por registrarse en La Vega OnLine</p>\n"
          + "<p>Haga click <a href='http://localhost:8080/ecommerce/servlet/cliente?accion=validar_mail&token=_token_'>aquí</a> para activar su cuenta, o presione el siguiente botón</p>\n"
          + "<form method='post' action='http://localhost:8080/ecommerce/servlet/cliente'>\n"
          + "<input type='hidden' name='accion' value='validar_mail'/>\n"
          + "<input type='hidden' name='token' value='_token_'/>\n"
          + "<input type='submit' value='Activar Cuenta'/>\n"
          + "</form>\n"
          + "<p>La Vega OnLine</p>\n"
          + "</div>";
  private String bodyMailRecuperacion = "<div style='text-align:center'>\n"
          + "<h1>Bienvenido a La Vega OnLine</h1><br>\n"
          + "<h3>Estimado : _nombre_</h3>\n"
          + "<p>Se ha solicitado un enlace para resstablecer su cotraseña</p>\n"
          + "<p>Haga click <a href='http://localhost:8080/ecommerce/servlet/cliente?accion=validar_mail&token=_token_'>aquí</a> para cambiar su contraseña, o presione el siguiente botón</p>\n"
          + "<form method='post' action='http://localhost:8080/ecommerce/servlet/cliente'>\n"
          + "<input type='hidden' name='accion' value='validar_mail'/>\n"
          + "<input type='hidden' name='token' value='_token_'/>\n"
          + "<input type='submit' value='Activar Cuenta'/>\n"
          + "</form>\n"
          + "<p>La Vega OnLine</p>\n"
          + "<p>Si usted no ha solicitado este enlace, por favor ignore este correo.</p>\n"
          + "</div>";

  private Session session;

  private void init() {
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.port", 25);
    properties.put("mail.smtp.mail.sender", "lavegaonline2018@gmail.com");
    properties.put("mail.smtp.user", "lavegaonline2018@gmail.com");
    properties.put("mail.smtp.auth", "true");
    session = Session.getDefaultInstance(properties);
  }

  public void enviarMailBienvenida(Cliente cliente, String token) {
    bodyMailBienvenida = bodyMailBienvenida.replace("_nombre_", cliente.getNombre() + " " + cliente.getApellidos());
    bodyMailBienvenida = bodyMailBienvenida.replace("_token_", token);

    try {
      sendMail(cliente.getEmail(), "Bienvenido a la Vega OnLine", bodyMailBienvenida);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
//    init();
//    try {
//      MimeMessage message = new MimeMessage(session);
//      message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
//      message.addRecipient(Message.RecipientType.TO, new InternetAddress(cliente.getEmail()));
//      message.setSubject("Bienvenido a La Vega OnLine");
//      bodyMailBienvenida = bodyMailBienvenida.replace("_nombre_", cliente.getNombre() + " " + cliente.getApellidos());
//      bodyMailBienvenida = bodyMailBienvenida.replace("_token_", token);
//      message.setContent(bodyMailBienvenida, "text/html; charset=utf-8");
//      Transport t = session.getTransport("smtp");
//      t.connect((String) properties.get("mail.smtp.user"), PASSWORD);
//      t.sendMessage(message, message.getAllRecipients());
//      t.close();
//    } catch (MessagingException me) {
//      System.out.println(me.getMessage());
//      //Aqui se deberia o mostrar un mensaje de error o en lugar
//      //de no hacer nada con la excepcion, lanzarla para que el modulo
//      //superior la capture y avise al usuario con un popup, por ejemplo.
//      return;
//    }
  }

  public void enviarEnlaceDeRecuperacion(Cliente cliente, PasswordReset passwordReset) {
    init();
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(passwordReset.getEmail()));
      message.setSubject("Bienvenido a La Vega OnLine");
      bodyMailRecuperacion = bodyMailRecuperacion.replace("_nombre_", cliente.getNombre() + " " + cliente.getApellidos());
      bodyMailRecuperacion = bodyMailRecuperacion.replace("_token_", passwordReset.getToken());
      message.setContent(bodyMailRecuperacion, "text/html; charset=utf-8");
      Transport t = session.getTransport("smtp");
      t.connect((String) properties.get("mail.smtp.user"), PASSWORD);
      t.sendMessage(message, message.getAllRecipients());
      t.close();
    } catch (MessagingException me) {
      System.out.println(me.getMessage());
      //Aqui se deberia o mostrar un mensaje de error o en lugar
      //de no hacer nada con la excepcion, lanzarla para que el modulo
      //superior la capture y avise al usuario con un popup, por ejemplo.
      return;
    }
  }

  private Session getVegaOnlineMail() throws NamingException {
    Context c = new InitialContext();
    return (Session) c.lookup("java:comp/env/vegaOnlineMail");
  }

  private void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
    Session vegaOnlineMail = getVegaOnlineMail();
    MimeMessage message = new MimeMessage(vegaOnlineMail);
    message.setSubject(subject);
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
    message.setText(body);
    Transport.send(message);
  }

}
