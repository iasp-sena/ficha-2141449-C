/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.reservas;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.ReservaDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Reserva;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class VerReservasControlador implements Serializable {
    
    @Inject
    private ReservaDAO reservaDAO;
    
    List<Reserva> reservas;
    
    @PostConstruct
    public void init(){
        reservas = reservaDAO.buscarTodos();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
    
    
}
