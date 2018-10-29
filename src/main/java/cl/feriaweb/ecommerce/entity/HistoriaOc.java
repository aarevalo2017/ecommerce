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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "HISTORIA_OC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoriaOc.findAll", query = "SELECT h FROM HistoriaOc h")
    , @NamedQuery(name = "HistoriaOc.findById", query = "SELECT h FROM HistoriaOc h WHERE h.id = :id")
    , @NamedQuery(name = "HistoriaOc.findByComentario", query = "SELECT h FROM HistoriaOc h WHERE h.comentario = :comentario")
    , @NamedQuery(name = "HistoriaOc.findByNotificarCliente", query = "SELECT h FROM HistoriaOc h WHERE h.notificarCliente = :notificarCliente")})
public class HistoriaOc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTIFICAR_CLIENTE")
    private short notificarCliente;
    @Column(name = "FECHA")
    private Date fecha;
    @JoinColumn(name = "ESTADO_OC_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EstadoOc estadoOcId;
    @JoinColumn(name = "ORDEN_COMPRA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OrdenCompra ordenCompraId;

    public HistoriaOc() {
    }

    public HistoriaOc(Integer id) {
        this.id = id;
    }

    public HistoriaOc(Integer id, String comentario, short notificarCliente) {
        this.id = id;
        this.comentario = comentario;
        this.notificarCliente = notificarCliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public short getNotificarCliente() {
        return notificarCliente;
    }

    public void setNotificarCliente(short notificarCliente) {
        this.notificarCliente = notificarCliente;
    }
    
    public Date getFecha(){
      return fecha;
    }
    
    public void setFecha(Date fecha){
      this.fecha = fecha;
    }

    public EstadoOc getEstadoOcId() {
        return estadoOcId;
    }

    public void setEstadoOcId(EstadoOc estadoOcId) {
        this.estadoOcId = estadoOcId;
    }

    public OrdenCompra getOrdenCompraId() {
        return ordenCompraId;
    }

    public void setOrdenCompraId(OrdenCompra ordenCompraId) {
        this.ordenCompraId = ordenCompraId;
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
        if (!(object instanceof HistoriaOc)) {
            return false;
        }
        HistoriaOc other = (HistoriaOc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.feriaweb.ecommerce.entity.HistoriaOc[ id=" + id + " ]";
    }
    
}
