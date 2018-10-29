/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "DETALLE_OC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleOc.findAll", query = "SELECT d FROM DetalleOc d")
    , @NamedQuery(name = "DetalleOc.findById", query = "SELECT d FROM DetalleOc d WHERE d.id = :id")
    , @NamedQuery(name = "DetalleOc.findByCantidad", query = "SELECT d FROM DetalleOc d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleOc.findByPrecio", query = "SELECT d FROM DetalleOc d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetalleOc.findByTotal", query = "SELECT d FROM DetalleOc d WHERE d.total = :total")})
public class DetalleOc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private int precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private int total;
    @JoinColumn(name = "ORDEN_COMPRA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OrdenCompra ordenCompraId;
    @JoinColumn(name = "PRODUCTO_PRODUCTOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductoProductor productoProductorId;

    public DetalleOc() {
    }

    public DetalleOc(Integer id) {
        this.id = id;
    }

    public DetalleOc(Integer id, int cantidad, int precio, int total) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public OrdenCompra getOrdenCompraId() {
        return ordenCompraId;
    }

    public void setOrdenCompraId(OrdenCompra ordenCompraId) {
        this.ordenCompraId = ordenCompraId;
    }

    public ProductoProductor getProductoProductorId() {
        return productoProductorId;
    }

    public void setProductoProductorId(ProductoProductor productoProductorId) {
        this.productoProductorId = productoProductorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleOc)) {
            return false;
        }
        DetalleOc other = (DetalleOc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.feriaweb.ecommerce.entity.DetalleOc[ id=" + id + " ]";
    }
    
}
