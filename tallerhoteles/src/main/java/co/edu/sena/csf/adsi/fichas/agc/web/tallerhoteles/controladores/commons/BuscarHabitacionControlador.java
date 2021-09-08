/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.commons;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.HabitacionDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Habitacion;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Hotel;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.MensajeUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class BuscarHabitacionControlador implements Serializable {

    @Inject
    private BuscarHotelControlador buscarHotelControlador;
    @Inject
    private HabitacionDAO habitacionDAO;

    private List<Habitacion> habitaciones;
    private Habitacion habitacionSeleccionada;

    public void buscarHabitaciones() {
        Hotel hotelSeleccionado = buscarHotelControlador.getHotelSeleccionado();
        habitacionSeleccionada = null;
        if (Objects.nonNull(hotelSeleccionado)) {
            habitaciones = habitacionDAO.buscarPorIdHotel(hotelSeleccionado.getId());
        } else {
            MensajeUtil.mostrarAlerta(null, "No se ha selecciona un criterio de búsqueda", "");
            habitaciones = Collections.emptyList();
        }
    }
    
    public boolean mostarPanelHabitacionesEncontradas(){
        return Objects.isNull(habitacionSeleccionada) &&
                Objects.nonNull(habitaciones) && !habitaciones.isEmpty();
    }
    
    public void seleccionarHabitacion(Habitacion habitacion){
        System.out.println("Seleccionando la habitación: " + habitacion.getNumero());
        this.habitacionSeleccionada = habitacion;
    }
    
    public boolean mostarPanelHabitacionSeleccionada(){
        System.out.println("Mostrar datos habitacion seleccionada: " + Objects.nonNull(habitacionSeleccionada));
        return Objects.nonNull(habitacionSeleccionada);
    }
    
//<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    
    public Habitacion getHabitacionSeleccionada() {
        return habitacionSeleccionada;
    }
    
    public void setHabitacionSeleccionada(Habitacion habitacionSeleccionada) {
        this.habitacionSeleccionada = habitacionSeleccionada;
    }
    
    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }
//</editor-fold>
    
}
