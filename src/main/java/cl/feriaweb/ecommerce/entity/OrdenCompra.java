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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "ORDEN_COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenCompra.findAll", query = "SELECT o FROM OrdenCompra o")
    , @NamedQuery(name = "OrdenCompra.findById", query = "SELECT o FROM OrdenCompra o WHERE o.id = :id")
    , @NamedQuery(name = "OrdenCompra.findByTotal", query = "SELECT o FROM OrdenCompra o WHERE o.total = :total")
    , @NamedQuery(name = "OrdenCompra.findByFechaCompra", query = "SELECT o FROM OrdenCompra o WHERE o.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "OrdenCompra.findByFechaModificacion", query = "SELECT o FROM OrdenCompra o WHERE o.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "OrdenCompra.findByIpOrigen", query = "SELECT o FROM OrdenCompra o WHERE o.ipOrigen = :ipOrigen")})
public class OrdenCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private int total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_COMPRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "IP_ORIGEN")
    private String ipOrigen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenCompraId")
    private List<HistoriaOc> historiaOcList;
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Cliente clienteId;
    @JoinColumn(name = "TIPO_DESPACHO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoDespacho tipoDespachoId;
    @JoinColumn(name = "TIPO_PAGO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoPago tipoPagoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenCompraId")
    private List<DetalleOc> detalleOcList;

    public OrdenCompra() {
    }

    public OrdenCompra(Integer id) {
        this.id = id;
    }

    public OrdenCompra(Integer id, int total, Date fechaCompra, Date fechaModificacion, String ipOrigen) {
        this.id = id;
        this.total = total;
        this.fechaCompra = fechaCompra;
        this.fechaModificacion = fechaModificacion;
        this.ipOrigen = ipOrigen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getIpOrigen() {
        return ipOrigen;
    }

    public void setIpOrigen(String ipOrigen) {
        this.ipOrigen = ipOrigen;
    }

    @XmlTransient
    public List<HistoriaOc> getHistoriaOcList() {
        return historiaOcList;
    }

    public void setHistoriaOcList(List<HistoriaOc> historiaOcList) {
        this.historiaOcList = historiaOcList;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public TipoDespacho getTipoDespachoId() {
        return tipoDespachoId;
    }

    public void setTipoDespachoId(TipoDespacho tipoDespachoId) {
        this.tipoDespachoId = tipoDespachoId;
    }

    public TipoPago getTipoPagoId() {
        return tipoPagoId;
    }

    public void setTipoPagoId(TipoPago tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
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
        if (!(object instanceof OrdenCompra)) {
            return false;
        }
        OrdenCompra other = (OrdenCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.feriaweb.ecommerce.entity.OrdenCompra[ id=" + id + " ]";
    }
    
}
