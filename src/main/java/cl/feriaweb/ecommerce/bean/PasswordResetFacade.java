/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.bean;

import cl.feriaweb.ecommerce.entity.Cliente;
import cl.feriaweb.ecommerce.entity.PasswordReset;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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
public class PasswordResetFacade extends AbstractFacade<PasswordReset> {

  private final static Logger log = Logger.getLogger(PasswordResetFacade.class.getName());

  private final static String SP_ELIMINAR = "PKG_PASSWORD_RESET.SP_ELIMINAR";
  private final static String IN_ID = "P_ID";

  @PersistenceContext(unitName = "cl.feriaweb_ecommerce_war_1.0-SNAPSHOTPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public PasswordResetFacade() {
    super(PasswordReset.class);
  }

  public boolean spAgregar(PasswordReset passwordReset) {
    try {
      StoredProcedureQuery query = em.createStoredProcedureQuery("PKG_PASSWORD_RESET.SP_AGREGAR");
      query.registerStoredProcedureParameter("P_ID", Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_EMAIL", String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_TOKEN", String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_FECHA_CREACION", Date.class, ParameterMode.IN);
      query.setParameter("P_ID", null);
      query.setParameter("P_EMAIL", passwordReset.getEmail());
      query.setParameter("P_TOKEN", passwordReset.getToken());
      query.setParameter("P_FECHA_CREACION", new Date());
      query.execute();
      return true;
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
      return false;
    }
  }

  public PasswordReset spBuscarPorToken(String token) {
    PasswordReset passwordReset = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_PASSWORD_RESET.SP_BUSCAR_POR_TOKEN", PasswordReset.class);
      sp.registerStoredProcedureParameter("P_TOKEN", String.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter("P_CURSOR", void.class, ParameterMode.REF_CURSOR);
      sp.setParameter("P_TOKEN", token);
      sp.execute();
      List filas = sp.getResultList();
      if (filas.size() > 0) {
        passwordReset = (PasswordReset) filas.get(0);
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      return passwordReset;
    }
  }

  public void spEliminar(PasswordReset passwordReset) {
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_ELIMINAR);
      sp.registerStoredProcedureParameter(IN_ID, Number.class, ParameterMode.IN);
      sp.setParameter(IN_ID, passwordReset.getId());
      sp.execute();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
  }
}
