/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.bean;

import cl.feriaweb.ecommerce.entity.Cliente;
import cl.feriaweb.ecommerce.entity.PasswordReset;
import cl.feriaweb.ecommerce.util.EmailSenderService;
import cl.feriaweb.ecommerce.util.MailGun;
import cl.feriaweb.ecommerce.util.MailUtil;
import cl.feriaweb.ecommerce.util.MiLog;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
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
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author Alejandro
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

  @Resource(mappedName = "jmsVegaMail")
  private Queue vegaMail;

  @Inject
  @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
  private JMSContext context;

  private final String P_ID = "P_ID";
  private final String P_ACTIVO = "P_ACTIVO";
  private final String P_NOMBRE = "P_NOMBRE";
  private final String P_APELLIDOS = "P_APELLIDOS";
  private final String P_EMAIL = "P_EMAIL";
  private final String P_TELEFONO = "P_TELEFONO";
  private final String P_PASSWORD = "P_PASSWORD";
  private final String P_RECIBE_BOLETIN = "P_RECIBE_BOLETIN";
  private final String P_ESTADO_USUARIO_ID = "P_ESTADO_USUARIO_ID";
  private final String P_ELIMINADO = "P_ELIMINADO";
  private final String P_CURSOR = "P_CURSOR";

  private final String SP_BUSCAR = "PKG_CLIENTE.SP_BUSCAR";
  private final String SP_BUSCAR_POR_EMAIL = "PKG_CLIENTE.SP_BUSCAR_POR_EMAIL";
  private final String SP_ACTIVAR_CUENTA = "PKG_CLIENTE.SP_ACTIVAR_CUENTA";
  private final String SP_AGREGAR = "PKG_CLIENTE.SP_AGREGAR";
  private final static Logger log = Logger.getLogger(ClienteFacade.class.getName());

  @PersistenceContext(unitName = "cl.feriaweb_ecommerce_war_1.0-SNAPSHOTPU")
  private EntityManager em;
  private final String SP_BLOQUEAR_PASSWORD = "PKG_CLIENTE.SP_BLOQUEAR_PASSWORD";

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public ClienteFacade() {
    super(Cliente.class);
  }

  public boolean spAgregar(Cliente cliente) {
    try {
      StoredProcedureQuery query = em.createStoredProcedureQuery(SP_AGREGAR);
      query.registerStoredProcedureParameter(P_ID, Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_ACTIVO, Boolean.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_NOMBRE, String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_APELLIDOS, String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_EMAIL, String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_TELEFONO, String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_PASSWORD, String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_RECIBE_BOLETIN, Boolean.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_ESTADO_USUARIO_ID, Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_ELIMINADO, Boolean.class, ParameterMode.IN);
      query.setParameter(P_ID, null);
      query.setParameter(P_ACTIVO, true);
      query.setParameter(P_NOMBRE, cliente.getNombre());
      query.setParameter(P_APELLIDOS, cliente.getApellidos());
      query.setParameter(P_EMAIL, cliente.getEmail());
      query.setParameter(P_TELEFONO, cliente.getTelefono());
      query.setParameter(P_PASSWORD, cliente.getPassword());
      query.setParameter(P_RECIBE_BOLETIN, cliente.getRecibeBoletin());
      query.setParameter(P_ESTADO_USUARIO_ID, 4);
      query.setParameter(P_ELIMINADO, false);
      query.execute();

      StoredProcedureQuery queryToken = em.createStoredProcedureQuery("PKG_PASSWORD_RESET.SP_AGREGAR");
      queryToken.registerStoredProcedureParameter("P_ID", Number.class, ParameterMode.IN);
      queryToken.registerStoredProcedureParameter("P_EMAIL", String.class, ParameterMode.IN);
      queryToken.registerStoredProcedureParameter("P_TOKEN", String.class, ParameterMode.IN);
      queryToken.registerStoredProcedureParameter("P_FECHA_CREACION", Date.class, ParameterMode.IN);
      queryToken.setParameter("P_ID", null);
      queryToken.setParameter("P_EMAIL", cliente.getEmail());
      String token = RandomStringUtils.randomAlphanumeric(32);
      queryToken.setParameter("P_TOKEN", token);
      queryToken.setParameter("P_FECHA_CREACION", new Date());
      queryToken.execute();
      String jms = "enviarMailBienvenida-" + cliente.getNombre() + " " + cliente.getApellidos() + "-" + cliente.getEmail() + "-" + token;
      sendJMSMessageToJmsVegaMail(jms);
      return true;
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
      return false;
    }
  }

  public Cliente spBuscarPorEmail(String email) {
    Cliente cliente = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_BUSCAR_POR_EMAIL, Cliente.class);
      sp.registerStoredProcedureParameter(P_EMAIL, String.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.setParameter(P_EMAIL, email);
      sp.execute();
      cliente = (Cliente) sp.getSingleResult();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      return cliente;
    }
  }

  public Cliente spBuscar(int id) {
    Cliente cliente = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_BUSCAR, Cliente.class);
      sp.registerStoredProcedureParameter(P_ID, Number.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.setParameter(P_ID, id);
      sp.execute();
      cliente = (Cliente) sp.getSingleResult();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      return cliente;
    }
  }

  public void spActivarCuenta(Cliente cliente) {
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_ACTIVAR_CUENTA);
      sp.registerStoredProcedureParameter(P_ID, Number.class, ParameterMode.IN);
      sp.setParameter(P_ID, cliente.getId());
      sp.execute();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
  }

  public void spBloquearPassword(Cliente cliente) {
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_BLOQUEAR_PASSWORD);
      sp.registerStoredProcedureParameter(P_ID, Number.class, ParameterMode.IN);
      sp.setParameter(P_ID, cliente.getId());
      sp.execute();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
  }

  private void sendJMSMessageToJmsVegaMail(String messageData) {
    context.createProducer().send(vegaMail, messageData);
  }

}
