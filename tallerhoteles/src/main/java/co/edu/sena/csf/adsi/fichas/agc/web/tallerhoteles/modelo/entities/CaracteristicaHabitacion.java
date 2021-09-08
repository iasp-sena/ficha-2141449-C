/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ismael
 */
@Entity
@Table(name = "caracteristicas_habitaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaracteristicaHabitacion.findAll", query = "SELECT c FROM CaracteristicaHabitacion c"),
    @NamedQuery(name = "CaracteristicaHabitacion.findByIdCaracteristicaHabitacion", query = "SELECT c FROM CaracteristicaHabitacion c WHERE c.id = :idCaracteristicaHabitacion"),
    @NamedQuery(name = "CaracteristicaHabitacion.findByNombre", query = "SELECT c FROM CaracteristicaHabitacion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CaracteristicaHabitacion.findByDescripcion", query = "SELECT c FROM CaracteristicaHabitacion c WHERE c.descripcion = :descripcion")})
public class CaracteristicaHabitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_caracteristica_habitacion")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;

    public CaracteristicaHabitacion() {
    }

    public CaracteristicaHabitacion(Integer idCaracteristicaHabitacion) {
        this.id = idCaracteristicaHabitacion;
    }

    public CaracteristicaHabitacion(Integer idCaracteristicaHabitacion, String nombre) {
        this.id = idCaracteristicaHabitacion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof CaracteristicaHabitacion)) {
            return false;
        }
        CaracteristicaHabitacion other = (CaracteristicaHabitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.CaracteristicaHabitacion[ idCaracteristicaHabitacion=" + id + " ]";
    }
    
}
