/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.controller;

import java.io.StringWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
 *
 * @author Alejandro
 */
@MessageDriven(activationConfig = {
  @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jmsVegaMail")
  ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class HelloMessageBean implements MessageListener {

  @Resource(name = "vegaOnlineMail")
  private Session vegaOnlineMail;

  public HelloMessageBean() {
  }

  @Override
  public void onMessage(Message message) {
    try {
      TextMessage textMessage = (TextMessage) message;
      if (textMessage != null) {
        String[] data = textMessage.getText().split("-");
        switch (data[0]) {
          case "enviarMailBienvenida":
            enviarMailBienvenida(textMessage);
            break;
        }
      }
    } catch (Exception e) {
      System.out.println("Error : " + e.getMessage());
    }
  }

  private void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
    MimeMessage message = new MimeMessage(vegaOnlineMail);
    message.setSubject(subject);
    message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email, false));
    message.setText(body, "utf-8", "html");
    Transport.send(message);
  }

  private void enviarMailBienvenida(TextMessage textMessage) {
    try {
      String ambiente = ResourceBundle.getBundle("config").getString("ambiente");
      String nombre = textMessage.getText().split("-")[1];
      String mail = textMessage.getText().split("-")[2];
      String token = textMessage.getText().split("-")[3];
      Properties p = new Properties();
      p.setProperty("resource.loader", "class");
      p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
      Velocity.init(p);
      Template t = Velocity.getTemplate("email/bienvenida.vm");
      VelocityContext context = new VelocityContext();
      context.put("nombre", nombre);
      context.put("url", ambiente + "/ecommerce/servlet/cliente?accion=validar_mail&token=" + token);
      StringWriter writer = new StringWriter();
      t.merge(context, writer);
      sendMail(mail, "La Vega Online", writer.toString());
      System.out.println("Correo enviado a " + mail);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
