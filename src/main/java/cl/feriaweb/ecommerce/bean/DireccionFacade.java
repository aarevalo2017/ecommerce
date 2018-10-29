/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.bean;

import cl.feriaweb.ecommerce.entity.Direccion;
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
public class DireccionFacade extends AbstractFacade<Direccion> {

  private static final Logger log = Logger.getLogger(DireccionFacade.class.getName());

  @PersistenceContext(unitName = "cl.feriaweb_ecommerce_war_1.0-SNAPSHOTPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public DireccionFacade() {
    super(Direccion.class);
  }

  public Direccion spBuscarPorAlias(String alias) {
    try {
      StoredProcedureQuery query = em.createStoredProcedureQuery("PKG_DIRECCION.SP_BUSCAR_POR_ALIAS");
      query.registerStoredProcedureParameter("P_ALIAS", String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_CURSOR", void.class, ParameterMode.REF_CURSOR);
      query.setParameter("P_ALIAS", alias);
      return null;
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
    return null;
  }

  public boolean spAgregar(Direccion direccion) {
    try {
      StoredProcedureQuery query = em.createStoredProcedureQuery("PKG_DIRECCION.SP_AGREGAR");
      query.registerStoredProcedureParameter("P_ID", Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_DIRECCION", String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_CODIGO_COMUNA", Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_CODIGO_POSTAL", Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_LATITUD", Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_LONGITUD", Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_CODIGO_UBICACION", String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_ALIAS", String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_NOMBRE", String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_APELLIDOS", String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_CLIENTE_ID", Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter("P_ELIMINADO", Boolean.class, ParameterMode.IN);
      query.setParameter("P_ID", null);
      query.setParameter("P_DIRECCION", direccion.getDireccion());
      query.setParameter("P_CODIGO_COMUNA", direccion.getCodigoComuna());
      query.setParameter("P_CODIGO_POSTAL", direccion.getCodigoPostal());
      query.setParameter("P_LATITUD", direccion.getLatitud());
      query.setParameter("P_LONGITUD", direccion.getLongitud());
      query.setParameter("P_CODIGO_UBICACION", direccion.getCodigoUbicacion());
      query.setParameter("P_ALIAS", direccion.getAlias());
      query.setParameter("P_NOMBRE", direccion.getNombre());
      query.setParameter("P_APELLIDOS", direccion.getApellidos());
      query.setParameter("P_CLIENTE_ID", direccion.getClienteId().getId());
      query.setParameter("P_ELIMINADO", direccion.getEliminado());
      query.execute();

      return true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
//      log.log(Level.SEVERE, e.getMessage());
      return false;
    }
  }

}
