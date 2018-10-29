/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "PRODUCTO_PRODUCTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoProductor.findAll", query = "SELECT p FROM ProductoProductor p")
    , @NamedQuery(name = "ProductoProductor.findById", query = "SELECT p FROM ProductoProductor p WHERE p.id = :id")
    , @NamedQuery(name = "ProductoProductor.findByPrecio", query = "SELECT p FROM ProductoProductor p WHERE p.precio = :precio")
    , @NamedQuery(name = "ProductoProductor.findByCantidad", query = "SELECT p FROM ProductoProductor p WHERE p.cantidad = :cantidad")
    , @NamedQuery(name = "ProductoProductor.findByCantidadMinima", query = "SELECT p FROM ProductoProductor p WHERE p.cantidadMinima = :cantidadMinima")
    , @NamedQuery(name = "ProductoProductor.findByFechaDisponible", query = "SELECT p FROM ProductoProductor p WHERE p.fechaDisponible = :fechaDisponible")
    , @NamedQuery(name = "ProductoProductor.findByActivo", query = "SELECT p FROM ProductoProductor p WHERE p.activo = :activo")
    , @NamedQuery(name = "ProductoProductor.findByTieneDespacho", query = "SELECT p FROM ProductoProductor p WHERE p.tieneDespacho = :tieneDespacho")
    , @NamedQuery(name = "ProductoProductor.findByEliminado", query = "SELECT p FROM ProductoProductor p WHERE p.eliminado = :eliminado")})
public class ProductoProductor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private int precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_MINIMA")
    private int cantidadMinima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_DISPONIBLE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDisponible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO")
    private short activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIENE_DESPACHO")
    private short tieneDespacho;
    @Column(name = "ELIMINADO")
    private Short eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoProductorId")
    private List<Calificacion> calificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoProductorId")
    private List<DetalleCarro> detalleCarroList;
    @JoinColumn(name = "PRODUCTO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Producto productoId;
    @JoinColumn(name = "PRODUCTOR_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Productor productorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoProductorId")
    private List<Descuento> descuentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoProductorId")
    private List<DetalleOc> detalleOcList;

    public ProductoProductor() {
    }

    public ProductoProductor(Integer id) {
        this.id = id;
    }

    public ProductoProductor(Integer id, int precio, int cantidad, int cantidadMinima, Date fechaDisponible, short activo, short tieneDespacho) {
        this.id = id;
        this.precio = precio;
        this.cantidad = cantidad;
        this.cantidadMinima = cantidadMinima;
        this.fechaDisponible = fechaDisponible;
        this.activo = activo;
        this.tieneDespacho = tieneDespacho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public Date getFechaDisponible() {
        return fechaDisponible;
    }

    public void setFechaDisponible(Date fechaDisponible) {
        this.fechaDisponible = fechaDisponible;
    }

    public short getActivo() {
        return activo;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }

    public short getTieneDespacho() {
        return tieneDespacho;
    }

    public void setTieneDespacho(short tieneDespacho) {
        this.tieneDespacho = tieneDespacho;
    }

    public Short getEliminado() {
        return eliminado;
    }

    public void setEliminado(Short eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
        this.calificacionList = calificacionList;
    }

    @XmlTransient
    public List<DetalleCarro> getDetalleCarroList() {
        return detalleCarroList;
    }

    public void setDetalleCarroList(List<DetalleCarro> detalleCarroList) {
        this.detalleCarroList = detalleCarroList;
    }

    public Producto getProductoId() {
        return productoId;
    }

    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }

    public Productor getProductorId() {
        return productorId;
    }

    public void setProductorId(Productor productorId) {
        this.productorId = productorId;
    }

    @XmlTransient
    public List<Descuento> getDescuentoList() {
        return descuentoList;
    }

    public void setDescuentoList(List<Descuento> descuentoList) {
        this.descuentoList = descuentoList;
    }

    @XmlTransient
    public List<DetalleOc> getDetalleOcList() {
        return detalleOcList;
    }

    public void setDetalleOcList(List<DetalleOc> detalleOcList) {
        this.detalleOcList = detalleOcList;
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
        if (!(object instanceof ProductoProductor)) {
            return false;
        }
        ProductoProductor other = (ProductoProductor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.feriaweb.ecommerce.entity.ProductoProductor[ id=" + id + " ]";
    }
    
}
