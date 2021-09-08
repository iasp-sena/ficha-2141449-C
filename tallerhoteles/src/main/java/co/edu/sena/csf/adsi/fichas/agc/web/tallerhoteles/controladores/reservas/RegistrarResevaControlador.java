/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.reservas;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.commons.BuscarHabitacionControlador;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.commons.BuscarHuespedControlador;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.ReservaDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.EstadoReserva;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Habitacion;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Huesped;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Reserva;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.MailUtil;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.MensajeUtil;
import java.io.Serializable;
import java.util.Date;
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
public class RegistrarResevaControlador implements Serializable {

    @Inject
    private ReservaDAO reservaDAO;
    @Inject
    private BuscarHabitacionControlador buscarHabitacionControlador;
    @Inject
    private BuscarHuespedControlador buscarHuespedControlador;

    private Date fechaIngreso;
    private Date fechaSalida;

    public void regisrtarReserva() {
        Habitacion habitacionSeleccionada = buscarHabitacionControlador.getHabitacionSeleccionada();
        Huesped huespedSelecciondado = buscarHuespedControlador.getHuespedSeleccionado();
        if (datosRegistroHabitacionValidos(habitacionSeleccionada, huespedSelecciondado)) {
            System.out.println(String.format("Fecha ingreso: %s", fechaIngreso.toString()));
            System.out.println(String.format("Fecha salida: %s", fechaSalida.toString()));
            System.out.println(String.format("Habitacion: %s", habitacionSeleccionada.getNumero()));
            System.out.println(String.format("Huesped: %s", huespedSelecciondado.getDatosBasicos().getNombreCompleto()));
            Reserva reserva = new Reserva();
            reserva.setHuesped(huespedSelecciondado);
            reserva.setHabitacion(habitacionSeleccionada);
            reserva.setFechaIngreso(fechaIngreso);
            reserva.setFechaSalida(fechaSalida);
            reserva.setEstadoReserva(new EstadoReserva(1));
            reservaDAO.registrar(reserva);
            MensajeUtil.mostrarInformativo("Reserva realizada", "Gracias por preferirnos");
            MailUtil.sendHTML(huespedSelecciondado.getDatosBasicos().getEmail(), "Nueva reserva App Hoteles", 
                    String.format(
                            "<h3>Reserva realizada</h3><p><span style='color:red;'>Hotel:</span> %s</p><p><span style='color:red;'>Habitación:</span> %s</p><p><span style='color:red;'>Fecha ingreso:</span> %s</p><p><span style='color:red;'>Fecha salida:</span> %s</p><p><span style='color:red;'>A nombre de:</span> %s</p><p>Lo esperamos!</p>",
                            habitacionSeleccionada.getHotel().getNombre() + " - " + habitacionSeleccionada.getHotel().getDireccion(),
                            habitacionSeleccionada.getNumero(),
                            fechaIngreso,
                            fechaSalida,
                            huespedSelecciondado.getDatosBasicos().getNombreCompleto())
            );
        }
    }

//<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

//</editor-fold>
    private boolean datosRegistroHabitacionValidos(Habitacion habitacionSeleccionada, Huesped huespedSelecciondado) {
        return Objects.nonNull(habitacionSeleccionada)
                && Objects.nonNull(huespedSelecciondado)
                && Objects.nonNull(fechaIngreso)
                && Objects.nonNull(fechaSalida);
    }

}
