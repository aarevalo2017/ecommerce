/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "DESCUENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descuento.findAll", query = "SELECT d FROM Descuento d")
    , @NamedQuery(name = "Descuento.findById", query = "SELECT d FROM Descuento d WHERE d.id = :id")
    , @NamedQuery(name = "Descuento.findByCantidad", query = "SELECT d FROM Descuento d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "Descuento.findByPrecio", query = "SELECT d FROM Descuento d WHERE d.precio = :precio")
    , @NamedQuery(name = "Descuento.findByFechaInicio", query = "SELECT d FROM Descuento d WHERE d.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Descuento.findByFechaFin", query = "SELECT d FROM Descuento d WHERE d.fechaFin = :fechaFin")
    , @NamedQuery(name = "Descuento.findByActivo", query = "SELECT d FROM Descuento d WHERE d.activo = :activo")
    , @NamedQuery(name = "Descuento.findByEliminado", query = "SELECT d FROM Descuento d WHERE d.eliminado = :eliminado")})
public class Descuento implements Serializable {

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
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO")
    private short activo;
    @Column(name = "ELIMINADO")
    private Short eliminado;
    @JoinColumn(name = "PRODUCTO_PRODUCTOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductoProductor productoProductorId;

    public Descuento() {
    }

    public Descuento(Integer id) {
        this.id = id;
    }

    public Descuento(Integer id, int cantidad, int precio, Date fechaInicio, Date fechaFin, short activo) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public Short getEliminado() {
        return eliminado;
    }

    public void setEliminado(Short eliminado) {
        this.eliminado = eliminado;
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
        if (!(object instanceof Descuento)) {
            return false;
        }
        Descuento other = (Descuento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.feriaweb.ecommerce.entity.Descuento[ id=" + id + " ]";
    }
    
}
