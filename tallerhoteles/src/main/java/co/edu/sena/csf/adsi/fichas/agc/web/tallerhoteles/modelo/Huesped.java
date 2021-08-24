/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ismael
 */
@Entity
@Table(name = "huespedes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Huesped.findAll", query = "SELECT h FROM Huesped h"),
    @NamedQuery(name = "Huesped.findByIdHuesped", query = "SELECT h FROM Huesped h WHERE h.id = :idHuesped"),
    @NamedQuery(name = "Huesped.findByTelefono", query = "SELECT h FROM Huesped h WHERE h.telefono = :telefono"),
    @NamedQuery(name = "Huesped.findByTipoYNumeroDocumento", query = "SELECT h FROM Huesped h WHERE h.datosBasicos.tipoDocumento.id = :idTipoDocumento AND h.datosBasicos.numeroDocumento = :numeroDocumento")})
public class Huesped implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_huesped")
    private Integer id;
    @Size(max = 10)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ciudad ciudad;
    @JoinColumn(name = "id_dato_basico_persona", referencedColumnName = "id_dato_basico_persona")
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private DatosBasicosPersona datosBasicos;

    public Huesped() {
    }

    public Huesped(Integer idHuesped) {
        this.id = idHuesped;
    }

    public Huesped(Integer idHuesped, String direccion) {
        this.id = idHuesped;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public DatosBasicosPersona getDatosBasicos() {
        return datosBasicos;
    }

    public void setDatosBasicos(DatosBasicosPersona datosBasicos) {
        this.datosBasicos = datosBasicos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof Huesped)) {
            return false;
        }
        Huesped other = (Huesped) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.Huesped[ idHuesped=" + id + " ]";
    }
    
}
