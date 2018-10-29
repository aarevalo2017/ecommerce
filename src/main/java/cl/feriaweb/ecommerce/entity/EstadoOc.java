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
@Table(name = "ESTADO_OC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoOc.findAll", query = "SELECT e FROM EstadoOc e")
    , @NamedQuery(name = "EstadoOc.findById", query = "SELECT e FROM EstadoOc e WHERE e.id = :id")
    , @NamedQuery(name = "EstadoOc.findByNombre", query = "SELECT e FROM EstadoOc e WHERE e.nombre = :nombre")})
public class EstadoOc implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoOcId")
    private List<HistoriaOc> historiaOcList;

    public EstadoOc() {
    }

    public EstadoOc(Integer id) {
        this.id = id;
    }

    public EstadoOc(Integer id, String nombre) {
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

    @XmlTransient
    public List<HistoriaOc> getHistoriaOcList() {
        return historiaOcList;
    }

    public void setHistoriaOcList(List<HistoriaOc> historiaOcList) {
        this.historiaOcList = historiaOcList;
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
        if (!(object instanceof EstadoOc)) {
            return false;
        }
        EstadoOc other = (EstadoOc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.feriaweb.ecommerce.entity.EstadoOc[ id=" + id + " ]";
    }
    
}
