/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.bean;

import cl.feriaweb.ecommerce.entity.TipoDespacho;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Alejandro
 */
@Stateless
public class TipoDespachoFacade extends AbstractFacade<TipoDespacho> {
  
  private static final Logger log = Logger.getLogger(TipoDespacho.class.getName());
  private static final String SP_BUSCAR = "PKG_TIPO_DESPACHO.SP_BUSCAR";
  private static final String P_CURSOR = "P_CURSOR";
  
  @PersistenceContext(unitName = "cl.feriaweb_ecommerce_war_1.0-SNAPSHOTPU")
  private EntityManager em;
  
  @Override
  protected EntityManager getEntityManager() {
    return em;
  }
  
  public TipoDespachoFacade() {
    super(TipoDespacho.class);
  }
  
  public TipoDespacho spBuscar(int id) {
    TipoDespacho tipoDespacho = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_BUSCAR, TipoDespacho.class);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.execute();
      tipoDespacho = (TipoDespacho) sp.getSingleResult();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
    return tipoDespacho;
  }
  
}
