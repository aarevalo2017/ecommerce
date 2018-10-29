/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.feriaweb.ecommerce.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "PASSWORD_RESET")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "PasswordReset.findAll", query = "SELECT p FROM PasswordReset p")
  , @NamedQuery(name = "PasswordReset.findById", query = "SELECT p FROM PasswordReset p WHERE p.id = :id")
  , @NamedQuery(name = "PasswordReset.findByEmail", query = "SELECT p FROM PasswordReset p WHERE p.email = :email")
  , @NamedQuery(name = "PasswordReset.findByToken", query = "SELECT p FROM PasswordReset p WHERE p.token = :token")
  , @NamedQuery(name = "PasswordReset.findByFechaCreacion", query = "SELECT p FROM PasswordReset p WHERE p.fechaCreacion = :fechaCreacion")})
public class PasswordReset implements Serializable {

  private static final Logger log = Logger.getLogger(PasswordReset.class.getName());

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "ID")
  private Integer id;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "EMAIL")
  private String email;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 32)
  @Column(name = "TOKEN")
  private String token;
  @Basic(optional = false)
  @NotNull
  @Column(name = "FECHA_CREACION")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaCreacion;

  public PasswordReset() {
  }

  public PasswordReset(Integer id) {
    this.id = id;
  }

  public PasswordReset(Integer id, String email, String token, Date fechaCreacion) {
    this.id = id;
    this.email = email;
    this.token = token;
    this.fechaCreacion = fechaCreacion;
  }

  public PasswordReset(String email) {
    try {
      this.id = null;
      this.email = email;
      this.token = RandomStringUtils.randomAlphanumeric(32);
      this.fechaCreacion = new Date();
    } catch (Exception e) {
      log.log(Level.SEVERE, e.getMessage());
    }
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Date getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(Date fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
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
    if (!(object instanceof PasswordReset)) {
      return false;
    }
    PasswordReset other = (PasswordReset) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "cl.feriaweb.ecommerce.entity.PasswordReset[ id=" + id + " ]";
  }

}
