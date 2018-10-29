/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.bean;

import cl.feriaweb.ecommerce.entity.Categoria;
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
public class CategoriaFacade extends AbstractFacade<Categoria> {

  private static final Logger log = Logger.getLogger(CategoriaFacade.class.getName());

  private static final String SP_TODOS = "PKG_CATEGORIA.SP_TODOS";
  private static final String P_ID = "P_ID";
  private static final String P_CURSOR = "P_CURSOR";

  @PersistenceContext(unitName = "cl.feriaweb_ecommerce_war_1.0-SNAPSHOTPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public CategoriaFacade() {
    super(Categoria.class);
  }

  public List<Categoria> spTodos() {
    List<Categoria> categorias = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_TODOS, Categoria.class);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.execute();
      categorias = (List<Categoria>) sp.getOutputParameterValue(P_CURSOR);
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
    return categorias;
  }
}
