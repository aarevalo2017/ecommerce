/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "PRODUCTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productor.findAll", query = "SELECT p FROM Productor p")
    , @NamedQuery(name = "Productor.findById", query = "SELECT p FROM Productor p WHERE p.id = :id")
    , @NamedQuery(name = "Productor.findByRut", query = "SELECT p FROM Productor p WHERE p.rut = :rut")
    , @NamedQuery(name = "Productor.findByNombre", query = "SELECT p FROM Productor p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Productor.findByApellidos", query = "SELECT p FROM Productor p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Productor.findByRazonSocial", query = "SELECT p FROM Productor p WHERE p.razonSocial = :razonSocial")
    , @NamedQuery(name = "Productor.findByNombreFantasia", query = "SELECT p FROM Productor p WHERE p.nombreFantasia = :nombreFantasia")
    , @NamedQuery(name = "Productor.findByEmail", query = "SELECT p FROM Productor p WHERE p.email = :email")
    , @NamedQuery(name = "Productor.findByPassword", query = "SELECT p FROM Productor p WHERE p.password = :password")
    , @NamedQuery(name = "Productor.findByTelefono", query = "SELECT p FROM Productor p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Productor.findByDireccion", query = "SELECT p FROM Productor p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Productor.findByCodigoComuna", query = "SELECT p FROM Productor p WHERE p.codigoComuna = :codigoComuna")
    , @NamedQuery(name = "Productor.findByCodigoPostal", query = "SELECT p FROM Productor p WHERE p.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "Productor.findByLatitud", query = "SELECT p FROM Productor p WHERE p.latitud = :latitud")
    , @NamedQuery(name = "Productor.findByLongitud", query = "SELECT p FROM Productor p WHERE p.longitud = :longitud")
    , @NamedQuery(name = "Productor.findByCodigoUbicacion", query = "SELECT p FROM Productor p WHERE p.codigoUbicacion = :codigoUbicacion")
    , @NamedQuery(name = "Productor.findByEliminado", query = "SELECT p FROM Productor p WHERE p.eliminado = :eliminado")})
public class Productor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RUT")
    private String rut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 100)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Size(max = 50)
    @Column(name = "NOMBRE_FANTASIA")
    private String nombreFantasia;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "LOGO")
    private byte[] logo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_COMUNA")
    private int codigoComuna;
    @Column(name = "CODIGO_POSTAL")
    private Integer codigoPostal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "LATITUD")
    private BigDecimal latitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LONGITUD")
    private BigDecimal longitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CODIGO_UBICACION")
    private String codigoUbicacion;
    @Column(name = "ELIMINADO")
    private Short eliminado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productorId")
    private List<ProductoProductor> productoProductorList;
    @JoinColumn(name = "ESTADO_USUARIO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EstadoUsuario estadoUsuarioId;
    @JoinColumn(name = "ROL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Rol rolId;

    public Productor() {
    }

    public Productor(Integer id) {
        this.id = id;
    }

    public Productor(Integer id, String rut, String nombre, String apellidos, String email, String password, byte[] logo, String telefono, String direccion, int codigoComuna, BigDecimal latitud, BigDecimal longitud, String codigoUbicacion) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.logo = logo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoComuna = codigoComuna;
        this.latitud = latitud;
        this.longitud = longitud;
        this.codigoUbicacion = codigoUbicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoComuna() {
        return codigoComuna;
    }

    public void setCodigoComuna(int codigoComuna) {
        this.codigoComuna = codigoComuna;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }

    public Short getEliminado() {
        return eliminado;
    }

    public void setEliminado(Short eliminado) {
        this.eliminado = eliminado;
    }

    @XmlTransient
    public List<ProductoProductor> getProductoProductorList() {
        return productoProductorList;
    }

    public void setProductoProductorList(List<ProductoProductor> productoProductorList) {
        this.productoProductorList = productoProductorList;
    }

    public EstadoUsuario getEstadoUsuarioId() {
        return estadoUsuarioId;
    }

    public void setEstadoUsuarioId(EstadoUsuario estadoUsuarioId) {
        this.estadoUsuarioId = estadoUsuarioId;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
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
        if (!(object instanceof Productor)) {
            return false;
        }
        Productor other = (Productor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.feriaweb.ecommerce.entity.Productor[ id=" + id + " ]";
    }
    
}
