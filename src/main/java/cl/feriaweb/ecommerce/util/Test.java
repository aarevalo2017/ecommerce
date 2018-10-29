/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.util;

import cl.feriaweb.ecommerce.entity.Comuna;
import cl.feriaweb.ecommerce.entity.DivisionPolitica;
import cl.feriaweb.ecommerce.entity.Provincia;
import cl.feriaweb.ecommerce.entity.Region;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.commons.io.IOUtils;
//import org.apache.log4j.Logger;

/**
 *
 * @author Alejandro
 */
public class Test {

  private static Logger log = Logger.getLogger(Test.class.getName());
//  private final static Logger log2 = MiLog.setLogConfig(Test.class.getName());

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException, UnirestException, AddressException {
//    /*  Get the Template  */
//    Template t = Velocity.getTemplate("/src/main/resources/email/Helloworld.vm");
////    Velocity.g
//    /*  create a context and add data */
//    VelocityContext context = new VelocityContext();
//    context.put("name", "Alejandro");
//    /* now render the template into a StringWriter */
//    StringWriter writer = new StringWriter();
//    t.merge(context, writer);
//    /* show the World */
//    System.out.println(writer.toString());

//    Comuna comuna = DivisionPolitica.getComunaByCodigo("13201");
//    Provincia provincia = DivisionPolitica.getProvinciaByCodigo(comuna.getCodigo_padre());
//    Region region = DivisionPolitica.getRegionByCodigo(provincia.getCodigo_padre());
//    System.out.println(comuna.getNombre());
//    System.out.println(provincia.getNombre());
//    System.out.println(region.getNombre());
//    EmailSenderService ess = new EmailSenderService();
//    ess.enviarMailBienvenida(new InternetAddress("alej.arevalo@alumnos.duoc.cl"));
//    MailUtil.enviarMailBienvenida();
//    MailGun mg = new MailGun();
//    mg.sendSimpleMessage();
//MailGun.sendSimpleMessage();
//    System.out.println(RandomStringUtils.randomAlphanumeric(32));
//    log = MiLog.setLogConfig(log);
//    System.out.println(System.getProperty("user.dir"));
//    Handler consoleHandler = new ConsoleHandler();
//    Handler fileHandler = new FileHandler("./bitacora.log", false);
//    SimpleFormatter simpleFormatter = new SimpleFormatter();
//    fileHandler.setFormatter(simpleFormatter);
//    log.addHandler(consoleHandler);
//    log.addHandler(fileHandler);
//    consoleHandler.setLevel(Level.ALL);
//    fileHandler.setLevel(Level.ALL);
//    MiLog.setLogConfig(log);
//    log.log(Level.INFO, "info");
//    log.log(Level.SEVERE, "error");
//    String sha256hex = DigestUtils.sha256Hex("cliente");
//    System.out.println(sha256hex);
  }

}
