/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.entity;

import java.math.BigDecimal;

/**
 *
 * @author Alejandro
 */
public class BusquedaProducto {
  private BigDecimal productores;
  private Producto productoId;
  private BigDecimal precioMinimo;
  private BigDecimal precioMaximo;
  private BigDecimal stockDisponible;

  public BusquedaProducto(BigDecimal productores, Producto productoId, BigDecimal precioMinimo, BigDecimal precioMaximo, BigDecimal stockDisponible) {
    this.productores = productores;
    this.productoId = productoId;
    this.precioMinimo = precioMinimo;
    this.precioMaximo = precioMaximo;
    this.stockDisponible = stockDisponible;
  }

  public BigDecimal getProductores() {
    return productores;
  }

  public void setProductores(BigDecimal productores) {
    this.productores = productores;
  }

  public Producto getProductoId() {
    return productoId;
  }

  public void setProductoId(Producto productoId) {
    this.productoId = productoId;
  }

  public BigDecimal getPrecioMinimo() {
    return precioMinimo;
  }

  public void setPrecioMinimo(BigDecimal precioMinimo) {
    this.precioMinimo = precioMinimo;
  }

  public BigDecimal getPrecioMaximo() {
    return precioMaximo;
  }

  public void setPrecioMaximo(BigDecimal precioMaximo) {
    this.precioMaximo = precioMaximo;
  }

  public BigDecimal getStockDisponible() {
    return stockDisponible;
  }

  public void setStockDisponible(BigDecimal stockDisponible) {
    this.stockDisponible = stockDisponible;
  }

    
  
}
