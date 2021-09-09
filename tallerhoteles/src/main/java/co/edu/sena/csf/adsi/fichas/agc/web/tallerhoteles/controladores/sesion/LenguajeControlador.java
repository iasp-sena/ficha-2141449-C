/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.sesion;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Ismael
 */
@Named
@SessionScoped
@Getter
public class LenguajeControlador implements Serializable {

    private List<String> idiomas;
    private Locale idioma;

    @PostConstruct
    public void init() {
        idioma = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        
        idiomas = Arrays.asList("es","en");
    }
    
    public void cambiarIdioma(String nuevoIdioma){
        idioma = new Locale(nuevoIdioma);
    }
}
