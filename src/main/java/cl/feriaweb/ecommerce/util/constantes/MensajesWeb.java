/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.util.constantes;

/**
 *
 * @author Alejandro
 */
public class MensajesWeb {

  public static final String NOMBRE_FANTASIA = "La Vega OnLine";
  public static final String MSG_CUENTA_CONFIRMADA = "<strong>Cuenta activada exitosamente.</strong> "
          + "Estimado _nombre_, sus datos han sido validados correctamente, "
          + "ahora puede realizar compras en " + NOMBRE_FANTASIA + ".";
  public static final String MSG_TOKEN_NO_VALIDO = "<strong>Token no válido. </strong>"
          + "El token no existe o ha expirado, recuerde que solo tiene una validéz de 24 horas.";
  public static final String MSG_CLIENTE_REGISTRADO = "<strong>Sus datos han sido agregados</strong><br>"
          + "Un correo de confirmación ha sido enviado a <strong>_email_</strong><br>"
          + "Debe confirmar su cuenta para poder realizar compras en La Vega OnLine";
  public static final String MSG_EMAIL_EXISTE = "El correo electrónico <strong>_email_</strong> ya se encuentra registrado.";
  public static String MSG_EMAIL_NO_REGISTRADO = "El correo electrónico <strong>_email_</strong> no se encuentra registrado.";
  public static String MSG_ENLACE_RECUPERACION = "Se ha enviado un enlace de recuperación a <strong>_email_</strong>.";
  public static String PASSWORD_BLOQUEADA = "Estimado cliente, su contraseña se encuentra bloqueada por demasiados intentos erroneos, solicite el restablecimiento"
          + " <a class='text-primary' href='http://localhost:8080/ecommerce/reset-password.jsp'>Aquí</a>";
  public static String CUENTA_INACTIVA = "Estimado cliente, su cuenta se encuentra inactiva.";
  public static String CUENTA_POR_CONFIRMAR = "Estimado cliente, su cuenta no ha sido confirmada, revise su correo, o solicite uno nuevo <a href='#'>Aquí</a>";
  public static String VALIDAR_EMAIL = "Estimado cliente, su correo aun no ha sido confirmado.";
  public static String MSG_PAGO_CANCELADO = "Transacción rechazada desde Transbank.";
}
