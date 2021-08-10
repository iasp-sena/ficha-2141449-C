/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "datos_basicos_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosBasicosPersona.findAll", query = "SELECT d FROM DatosBasicosPersona d"),
    @NamedQuery(name = "DatosBasicosPersona.findByIdDatoBasicoPersona", query = "SELECT d FROM DatosBasicosPersona d WHERE d.id = :idDatoBasicoPersona"),
    @NamedQuery(name = "DatosBasicosPersona.findByNumeroDocumento", query = "SELECT d FROM DatosBasicosPersona d WHERE d.numeroDocumento = :numeroDocumento")})
public class DatosBasicosPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dato_basico_persona")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "apellidos")
    private String apellidos;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "datosBasicos", fetch = FetchType.LAZY)
    private Huesped huesped;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoDocumento tipoDocumento;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "datosBasicos", fetch = FetchType.LAZY)
    private Usuario usuario;

    public DatosBasicosPersona() {
    }

    public DatosBasicosPersona(Integer idDatoBasicoPersona) {
        this.id = idDatoBasicoPersona;
    }

    public DatosBasicosPersona(Integer idDatoBasicoPersona, String numeroDocumento, String nombres, String apellidos) {
        this.id = idDatoBasicoPersona;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof DatosBasicosPersona)) {
            return false;
        }
        DatosBasicosPersona other = (DatosBasicosPersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.DatosBasicosPersona[ idDatoBasicoPersona=" + id + " ]";
    }
    
}
