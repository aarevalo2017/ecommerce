/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.util;

import cl.transbank.webpay.configuration.Configuration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 *
 * @author Alejandro
 */
public class WebpayUtils {

  public static Configuration getConfiguration() throws IOException {
//    try {
      String environment = ResourceBundle.getBundle("webpay").getString("environment");
      String commerce_code = ResourceBundle.getBundle("webpay").getString("commerce_code");
      String publicCertFile = ResourceBundle.getBundle("webpay").getString("public_cert");
      String privateKeyFile = ResourceBundle.getBundle("webpay").getString("private_key");
      String webpayCertFile = ResourceBundle.getBundle("webpay").getString("webpay_cert");
      Configuration configuration = new Configuration();
      String public_cert_file = System.getProperty("user.dir") + "\\" + publicCertFile;
      String public_cert = new String(Files.readAllBytes(Paths.get(public_cert_file)));
      String private_key_file = System.getProperty("user.dir") + "\\" + privateKeyFile;
      String private_key = new String(Files.readAllBytes(Paths.get(private_key_file)));
      String webpay_cert_file = System.getProperty("user.dir") + "\\" + webpayCertFile;
      String webpay_cert = new String(Files.readAllBytes(Paths.get(webpay_cert_file)));
      configuration.setCommerceCode(commerce_code);
      configuration.setPrivateKey(private_key);
      configuration.setPublicCert(public_cert);
      configuration.setWebpayCert(webpay_cert);
      configuration.setEnvironment(environment);
      return configuration;
//    } catch (Exception e) {
//    }
  }

}
