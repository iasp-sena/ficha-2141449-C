/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.commons;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.BarrioDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Barrio;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Ciudad;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
public class BuscarBarrioController implements Serializable {
    
    @Inject
    private BuscarCiudadController buscarCiudadController;
    @Inject
    private BarrioDAO barrioDAO;
    private List<Barrio> barriosCiudadSeleccionada;
    
    public void buscarPorCiudadSeleccionada(){
        Ciudad ciudadSeleccionada = buscarCiudadController.getCiudadSeleccionada();
        System.out.println("Buscando los barrios............");
        System.out.println("Ciudad es: " + ciudadSeleccionada);
        if(Objects.nonNull(ciudadSeleccionada)){
            barriosCiudadSeleccionada = barrioDAO.buscarPorIdCiudad(ciudadSeleccionada.getId());
        } else {
            barriosCiudadSeleccionada = Collections.EMPTY_LIST;
        }
    }

    public List<Barrio> getBarriosCiudadSeleccionada() {
        //if(buscarCiudadController.getCiudadSeleccionada() != null)
        return barriosCiudadSeleccionada;
    }
    
}
