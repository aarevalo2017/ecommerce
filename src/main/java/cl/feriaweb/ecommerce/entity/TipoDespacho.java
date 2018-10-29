/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "TIPO_DESPACHO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDespacho.findAll", query = "SELECT t FROM TipoDespacho t")
    , @NamedQuery(name = "TipoDespacho.findById", query = "SELECT t FROM TipoDespacho t WHERE t.id = :id")
    , @NamedQuery(name = "TipoDespacho.findByNombre", query = "SELECT t FROM TipoDespacho t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoDespacho.findByEliminado", query = "SELECT t FROM TipoDespacho t WHERE t.eliminado = :eliminado")})
public class TipoDespacho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ELIMINADO")
    private Short eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDespachoId")
    private List<OrdenCompra> ordenCompraList;

    public TipoDespacho() {
    }

    public TipoDespacho(Integer id) {
        this.id = id;
    }

    public TipoDespacho(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getEliminado() {
        return eliminado;
    }

    public void setEliminado(Short eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<OrdenCompra> getOrdenCompraList() {
        return ordenCompraList;
    }

    public void setOrdenCompraList(List<OrdenCompra> ordenCompraList) {
        this.ordenCompraList = ordenCompraList;
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
        if (!(object instanceof TipoDespacho)) {
            return false;
        }
        TipoDespacho other = (TipoDespacho) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.feriaweb.ecommerce.entity.TipoDespacho[ id=" + id + " ]";
    }
    
}
