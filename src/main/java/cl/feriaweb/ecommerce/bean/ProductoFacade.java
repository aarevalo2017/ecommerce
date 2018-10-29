/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.bean;

import cl.feriaweb.ecommerce.entity.Cliente;
import cl.feriaweb.ecommerce.entity.Producto;
import java.util.List;
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
public class ProductoFacade extends AbstractFacade<Producto> {

  private final static Logger log = Logger.getLogger(ProductoFacade.class.getName());

  private final String SP_BUSCAR = "PKG_PRODUCTO.SP_BUSCAR";
  private final String SP_TODOS = "PKG_PRODUCTO.SP_TODOS";
  private final String P_ID = "P_ID";
  private final String P_CURSOR = "P_CURSOR";

  @PersistenceContext(unitName = "cl.feriaweb_ecommerce_war_1.0-SNAPSHOTPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public ProductoFacade() {
    super(Producto.class);
  }

  public Producto spBuscar(int id) {
    Producto producto = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_BUSCAR, Producto.class);
      sp.registerStoredProcedureParameter(P_ID, Number.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.setParameter(P_ID, id);
      sp.execute();
      producto = (Producto) sp.getSingleResult();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      return producto;
    }
  }
  
  public List<Producto> spTodos() {
    List<Producto> productos = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_TODOS, Producto.class);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.execute();
      productos = (List<Producto>) sp.getOutputParameterValue(P_CURSOR);
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
    return productos;
  }

}
