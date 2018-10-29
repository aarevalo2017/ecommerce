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
@Table(name = "DETALLE_CARRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCarro.findAll", query = "SELECT d FROM DetalleCarro d")
    , @NamedQuery(name = "DetalleCarro.findById", query = "SELECT d FROM DetalleCarro d WHERE d.id = :id")
    , @NamedQuery(name = "DetalleCarro.findByCantidad", query = "SELECT d FROM DetalleCarro d WHERE d.cantidad = :cantidad")})
public class DetalleCarro implements Serializable {

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
    @JoinColumn(name = "CARRO_COMPRA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CarroCompra carroCompraId;
    @JoinColumn(name = "PRODUCTO_PRODUCTOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductoProductor productoProductorId;

    public DetalleCarro() {
    }

    public DetalleCarro(Integer id) {
        this.id = id;
    }

    public DetalleCarro(Integer id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
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

    public CarroCompra getCarroCompraId() {
        return carroCompraId;
    }

    public void setCarroCompraId(CarroCompra carroCompraId) {
        this.carroCompraId = carroCompraId;
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
        if (!(object instanceof DetalleCarro)) {
            return false;
        }
        DetalleCarro other = (DetalleCarro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.feriaweb.ecommerce.entity.DetalleCarro[ id=" + id + " ]";
    }
    
}
