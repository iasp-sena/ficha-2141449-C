/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.commons;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.CiudadDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Ciudad;
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
public class BuscarCiudadController implements Serializable{
    
    @Inject
    private CiudadDAO ciudadDAO;
    private List<Ciudad> ciudades;
    
    private Ciudad ciudadSeleccionada;
    
    @PostConstruct
    public void init(){
        System.out.println("Buscando las ciudades............");
        this.ciudades = ciudadDAO.buscarTodos();
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public Ciudad getCiudadSeleccionada() {
        System.out.println("Ciudad es: " + ciudadSeleccionada);
        return ciudadSeleccionada;
    }

    public void setCiudadSeleccionada(Ciudad ciudadSeleccionada) {
        this.ciudadSeleccionada = ciudadSeleccionada;
        System.out.println("Seleccionamos la ciudad: " + ciudadSeleccionada);
    }
    
}
