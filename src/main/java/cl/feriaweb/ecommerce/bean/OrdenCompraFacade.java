/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.bean;

import cl.feriaweb.ecommerce.entity.OrdenCompra;
import java.util.Date;
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
public class OrdenCompraFacade extends AbstractFacade<OrdenCompra> {

  private final String SP_AGREGAR = "PKG_ORDEN_COMPRA.SP_AGREGAR";
  @PersistenceContext(unitName = "cl.feriaweb_ecommerce_war_1.0-SNAPSHOTPU")
  private EntityManager em;
  private final String P_ID = "P_ID";
  private final String P_TOTAL = "P_TOTAL";
  private final String P_FECHA_COMPRA = "P_FECHA_COMPRA";
  private final String P_FECHA_MODIFICACION = "P_FECHA_MODIFICACION";
  private final String P_TIPO_PAGO_ID = "P_TIPO_PAGO_ID";
  private final String P_IP_ORIGEN = "P_IP_ORIGEN";
  private final String P_CLIENTE_ID = "P_CLIENTE_ID";
  private final String P_TIPO_DESPACHO_ID = "P_TIPO_DESPACHO_ID";

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public OrdenCompraFacade() {
    super(OrdenCompra.class);
  }

  public void spAgregar(OrdenCompra ordenCompra) {
    try {
      StoredProcedureQuery query = em.createStoredProcedureQuery(SP_AGREGAR);
      query.registerStoredProcedureParameter(P_ID, Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_TOTAL, Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_FECHA_COMPRA, Date.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_FECHA_MODIFICACION, Date.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_TIPO_PAGO_ID, Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_IP_ORIGEN, String.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_CLIENTE_ID, Number.class, ParameterMode.IN);
      query.registerStoredProcedureParameter(P_TIPO_DESPACHO_ID, Number.class, ParameterMode.IN);
      query.setParameter(P_ID, null);
      query.setParameter(P_TOTAL, ordenCompra.getTotal());
      query.setParameter(P_FECHA_COMPRA, ordenCompra.getFechaCompra());
      query.setParameter(P_FECHA_MODIFICACION, ordenCompra.getFechaModificacion());
      query.setParameter(P_TIPO_PAGO_ID, ordenCompra.getTipoPagoId().getId());
      query.setParameter(P_IP_ORIGEN, ordenCompra.getIpOrigen());
      query.setParameter(P_CLIENTE_ID, ordenCompra.getClienteId().getId());
      query.setParameter(P_TIPO_DESPACHO_ID, ordenCompra.getTipoDespachoId().getId());
      query.execute();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
