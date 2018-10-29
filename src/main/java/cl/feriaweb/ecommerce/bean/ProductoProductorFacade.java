/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.bean;

import cl.feriaweb.ecommerce.entity.BusquedaProducto;
import cl.feriaweb.ecommerce.entity.Cliente;
import cl.feriaweb.ecommerce.entity.ProductoProductor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
public class ProductoProductorFacade extends AbstractFacade<ProductoProductor> {

  @EJB
  private ProductoFacade productoFacade;

  private final static Logger log = Logger.getLogger(ProductoProductorFacade.class.getName());

  @PersistenceContext(unitName = "cl.feriaweb_ecommerce_war_1.0-SNAPSHOTPU")
  private EntityManager em;

  private final String P_ID = "P_ID";
  private final String P_PRECIO = "P_PRECIO";
  private final String P_CANTIDAD = "P_CANTIDAD";
  private final String P_CANTIDAD_MINIMA = "P_CANTIDAD_MINIMA";
  private final String P_FECHA_DISPONIBLE = "P_FECHA_DISPONIBLE";
  private final String P_PRODUCTOR_ID = "P_PRODUCTOR_ID";
  private final String P_PRODUCTO_ID = "P_PRODUCTO_ID";
  private final String P_ACTIVO = "P_ACTIVO";
  private final String P_TIENE_DESPACHO = "P_TIENE_DESPACHO";
  private final String P_ELIMINADO = "P_ELIMINADO";
  private final String P_NOMBRE = "P_NOMBRE";
  private final String P_CURSOR = "P_CURSOR";
  private final String SP_BUSQUEDA_GENERAL = "PKG_PRODUCTO_PRODUCTOR.SP_BUSQUEDA_GENERAL";
  private final String SP_BUSCAR_PRODUCTOS = "PKG_PRODUCTO_PRODUCTOR.SP_BUSCAR_PRODUCTOS";
  private final String SP_TODOS = "PKG_PRODUCTO_PRODUCTOR.SP_TODOS";
  private final String SP_BUSCAR_HOME = "PKG_PRODUCTO_PRODUCTOR.SP_BUSCAR_HOME";
  private final String SP_BUSCAR = "PKG_PRODUCTO_PRODUCTOR.SP_BUSCAR";

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public ProductoProductorFacade() {
    super(ProductoProductor.class);
  }

  public ProductoProductor spBuscar(int id){
    ProductoProductor productoProductor = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_BUSCAR, ProductoProductor.class);
      sp.registerStoredProcedureParameter(P_ID, Number.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.setParameter(P_ID, id);
      sp.execute();
      productoProductor = (ProductoProductor) sp.getSingleResult();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      return productoProductor;
    }
    
  }
  public ProductoProductor spBusquedaGeneral(String nombre) {
    ProductoProductor productoProductor = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_BUSQUEDA_GENERAL, ProductoProductor.class);
      sp.registerStoredProcedureParameter(P_NOMBRE, String.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.setParameter(P_NOMBRE, nombre);
      sp.execute();
      productoProductor = (ProductoProductor) sp.getSingleResult();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    } finally {
      return productoProductor;
    }
  }

  public List<ProductoProductor> spTodos() {
    List<ProductoProductor> productos = null;
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_TODOS, ProductoProductor.class);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.execute();
      productos = (List<ProductoProductor>) sp.getOutputParameterValue(P_CURSOR);
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
    return productos;
  }

  public List<BusquedaProducto> spBuscarProductos(String nombre) {
    List<BusquedaProducto> productos = new ArrayList<>();
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_BUSCAR_PRODUCTOS);
      sp.registerStoredProcedureParameter(P_NOMBRE, String.class, ParameterMode.IN);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      sp.setParameter(P_NOMBRE, nombre);
      List<Object[]> filas = sp.getResultList();
      for (Object[] fila : filas) {
        productos.add(
                new BusquedaProducto((BigDecimal) fila[0],
                        productoFacade.find(((BigDecimal) fila[1]).intValue()),
                        (BigDecimal) fila[2],
                        (BigDecimal) fila[3],
                        (BigDecimal) fila[4]
                )
        );
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
    return productos;
  }
  
  public List<BusquedaProducto> spBuscarHome() {
    List<BusquedaProducto> productos = new ArrayList<>();
    try {
      StoredProcedureQuery sp = em.createStoredProcedureQuery(SP_BUSCAR_HOME);
      sp.registerStoredProcedureParameter(P_CURSOR, void.class, ParameterMode.REF_CURSOR);
      List<Object[]> filas = sp.getResultList();
      for (Object[] fila : filas) {
        productos.add(
                new BusquedaProducto((BigDecimal) fila[0],
                        productoFacade.find(((BigDecimal) fila[1]).intValue()),
                        (BigDecimal) fila[2],
                        (BigDecimal) fila[3],
                        (BigDecimal) fila[4]
                )
        );
      }
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
    return productos;
  }
}
