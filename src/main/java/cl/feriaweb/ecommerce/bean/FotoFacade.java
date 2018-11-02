/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.bean;

import cl.feriaweb.ecommerce.entity.Foto;
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
public class FotoFacade extends AbstractFacade<Foto> {

    @PersistenceContext(unitName = "cl.feriaweb_ecommerce_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FotoFacade() {
        super(Foto.class);
    }
    
    public void spAgregar(Foto foto){
      StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_FOTO.SP_AGREGAR", Foto.class);
      sp.registerStoredProcedureParameter("P_ID", Number.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter("P_IMAGEN", byte[].class, ParameterMode.IN);
      sp.registerStoredProcedureParameter("P_ORDEN", Number.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter("P_PRODUCTO_ID", Number.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter("P_ELIMINADO", Number.class, ParameterMode.IN);
      
      sp.setParameter("P_ID", null);
      sp.setParameter("P_IMAGEN", foto.getImagen());
      sp.setParameter("P_ORDEN", foto.getOrden());
      sp.setParameter("P_PRODUCTO_ID", foto.getProductoId().getId());
      sp.setParameter("P_ELIMINADO", foto.getEliminado());
      
      sp.execute();
    }
    
}
