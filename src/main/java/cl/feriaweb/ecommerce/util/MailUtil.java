/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Alejandro
 */
public class MailUtil {

  public static void enviarMailBienvenida() throws IOException{
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");
    props.put("mail.smtp.starttls.enable","true");
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    Properties config = new Properties();
    final String gmailAccount = "lavegaonline2018@gmail.com";
    final String gmailPassword = "portafolio2018";
    final String[] emailDestinations = {"alej.arevalo@alumnos.duoc.cl"};
    final String[] attachmentFiles = {};
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(gmailAccount, gmailPassword);
      }
    });
    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(gmailAccount));
      for (String emailDestination : emailDestinations) {
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestination));
      }
      message.setSubject("Email Subject - Asunto del correo electronico");
      BodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart.setText("Email text Body - Texto o cuerpo del correo electronico");
      Multipart multipart = new MimeMultipart();
      for (String attachmentFile : attachmentFiles) {
        addAttachment(multipart, attachmentFile);
      }
//Setting email text message
      multipart.addBodyPart(messageBodyPart);
//set the attachments to the email
      message.setContent(multipart);
      Transport.send(message);
      System.out.println("Correo enviado");
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  private static void addAttachment(Multipart multipart, String filePath) throws MessagingException {
    File file = new File(filePath);
    DataSource source = new FileDataSource(file);
    BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(file.getName());
    multipart.addBodyPart(messageBodyPart);
  }

}
