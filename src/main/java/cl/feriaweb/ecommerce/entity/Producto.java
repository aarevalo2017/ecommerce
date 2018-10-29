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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
  , @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id")
  , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
  , @NamedQuery(name = "Producto.findByDescripcionHtml", query = "SELECT p FROM Producto p WHERE p.descripcionHtml = :descripcionHtml")
  , @NamedQuery(name = "Producto.findByEliminado", query = "SELECT p FROM Producto p WHERE p.eliminado = :eliminado")})
public class Producto implements Serializable {

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
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 4000)
  @Column(name = "DESCRIPCION_HTML")
  private String descripcionHtml;
  @Column(name = "ELIMINADO")
  private Short eliminado;
  @Lob
  @Column(name = "FOTO")
  private byte[] foto;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId")
  private List<Foto> fotoList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId")
  private List<ProductoProductor> productoProductorList;
  @JoinColumn(name = "CATEGORIA_ID", referencedColumnName = "ID")
  @ManyToOne(optional = false)
  private Categoria categoriaId;
  @JoinColumn(name = "IMPUESTO_ID", referencedColumnName = "ID")
  @ManyToOne(optional = false)
  private Impuesto impuestoId;
  @JoinColumn(name = "UNIDAD_MEDIDA_ID", referencedColumnName = "ID")
  @ManyToOne(optional = false)
  private UnidadMedida unidadMedidaId;

  public Producto() {
  }

  public Producto(Integer id) {
    this.id = id;
  }

  public Producto(Integer id, String nombre, String descripcionHtml) {
    this.id = id;
    this.nombre = nombre;
    this.descripcionHtml = descripcionHtml;
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

  public String getDescripcionHtml() {
    return descripcionHtml;
  }

  public void setDescripcionHtml(String descripcionHtml) {
    this.descripcionHtml = descripcionHtml;
  }

  public Short getEliminado() {
    return eliminado;
  }

  public void setEliminado(Short eliminado) {
    this.eliminado = eliminado;
  }

  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] foto) {
    this.foto = foto;
  }

  @XmlTransient
  public List<Foto> getFotoList() {
    return fotoList;
  }

  public void setFotoList(List<Foto> fotoList) {
    this.fotoList = fotoList;
  }

  @XmlTransient
  public List<ProductoProductor> getProductoProductorList() {
    return productoProductorList;
  }

  public void setProductoProductorList(List<ProductoProductor> productoProductorList) {
    this.productoProductorList = productoProductorList;
  }

  public Categoria getCategoriaId() {
    return categoriaId;
  }

  public void setCategoriaId(Categoria categoriaId) {
    this.categoriaId = categoriaId;
  }

  public Impuesto getImpuestoId() {
    return impuestoId;
  }

  public void setImpuestoId(Impuesto impuestoId) {
    this.impuestoId = impuestoId;
  }

  public UnidadMedida getUnidadMedidaId() {
    return unidadMedidaId;
  }

  public void setUnidadMedidaId(UnidadMedida unidadMedidaId) {
    this.unidadMedidaId = unidadMedidaId;
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
    if (!(object instanceof Producto)) {
      return false;
    }
    Producto other = (Producto) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "cl.feriaweb.ecommerce.entity.Producto[ id=" + id + " ]";
  }

}
