/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * @author Ismael
 */
@Entity
@Table(name = "habitaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habitacion.findAll", query = "SELECT h FROM Habitacion h"),
    @NamedQuery(name = "Habitacion.findByIdHabitacion", query = "SELECT h FROM Habitacion h WHERE h.id = :idHabitacion"),
    @NamedQuery(name = "Habitacion.findByNumero", query = "SELECT h FROM Habitacion h WHERE h.numero = :numero")})
public class Habitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_habitacion")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "numero")
    private String numero;
    @JoinTable(name = "caracteristicas_de_habitacion",
            joinColumns = {
                @JoinColumn(name = "id_habitacion", referencedColumnName = "id_habitacion")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_caracteristica_habitacion", referencedColumnName = "id_caracteristica_habitacion")
                })
    @ManyToMany(fetch = FetchType.LAZY)
    private List<CaracteristicaHabitacion> caracteristicasDeLaHabitacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habitacion", fetch = FetchType.LAZY)
    private List<Reserva> reservasDeLaHabitacion;
    @JoinColumn(name = "id_hotel", referencedColumnName = "id_hotel")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Hotel hotel;
    @JoinColumn(name = "id_tipo_habitacion", referencedColumnName = "id_tipo_habitacion")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoHabitacion tipoHabitacion;

    public Habitacion() {
    }

    public Habitacion(Integer idHabitacion) {
        this.id = idHabitacion;
    }

    public Habitacion(Integer idHabitacion, String numero) {
        this.id = idHabitacion;
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @XmlTransient
    public List<CaracteristicaHabitacion> getCaracteristicasDeLaHabitacion() {
        return caracteristicasDeLaHabitacion;
    }

    public void setCaracteristicasDeLaHabitacion(List<CaracteristicaHabitacion> caracteristicasDeLaHabitacion) {
        this.caracteristicasDeLaHabitacion = caracteristicasDeLaHabitacion;
    }

    @XmlTransient
    public List<Reserva> getReservasDeLaHabitacion() {
        return reservasDeLaHabitacion;
    }

    public void setReservasDeLaHabitacion(List<Reserva> reservasDeLaHabitacion) {
        this.reservasDeLaHabitacion = reservasDeLaHabitacion;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
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
        if (!(object instanceof Habitacion)) {
            return false;
        }
        Habitacion other = (Habitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.Habitacion[ idHabitacion=" + id + " ]";
    }

}
