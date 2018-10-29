/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.bean;

import cl.feriaweb.ecommerce.entity.TipoPago;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;

/**
 *
 * @author Alejandro
 */
@Stateless
public class TipoPagoFacade extends AbstractFacade<TipoPago> {

  private final static Logger log = Logger.getLogger(TipoPagoFacade.class.getName());

  private final String SP_AGREGAR = "PKG_TIPO_PAGO.SP_AGREGAR";
  private final String SP_BUSCAR = "PKG_TIPO_PAGO.SP_BUSCAR";
  private final String P_ID = "P_ID";
  private final String P_NOMBRE = "P_NOMBRE";
  private final String P_ELIMINADO = "P_ELIMINADO";
  private final String P_CURSOR = "P_CURSOR";
  private final String OUT_ID = "OUT_ID";

  @PersistenceContext(unitName = "cl.feriaweb_ecommerce_war_1.0-SNAPSHOTPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public TipoPagoFacade() {
    super(TipoPago.class);
  }

  public TipoPago spBuscar(int id) {
    TipoPago tipoPago = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_BUSCAR, TipoPago.class);
      sp.registerStoredProcedureParameter(P_ID, Number.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.setParameter(P_ID, id);
      sp.execute();
      tipoPago = (TipoPago) sp.getSingleResult();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
    return tipoPago;
  }

  public boolean spAgregar(TipoPago tipoPago) {
    try {
      StoredProcedureQuery query = em.createStoredProcedureQuery(SP_AGREGAR);
      query.registerStoredProcedureParameter(P_ID, Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_NOMBRE, String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_ELIMINADO, Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(OUT_ID, Integer.class, ParameterMode.OUT);

      query.setParameter(P_ID, null);
      query.setParameter(P_NOMBRE, tipoPago.getNombre());
      query.setParameter(P_ELIMINADO, 0);
      query.execute();
      int id = (int) query.getOutputParameterValue(OUT_ID);
      System.out.println("Tipo pago : " + id);

      return true;
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
      return false;
    }
  }

}
