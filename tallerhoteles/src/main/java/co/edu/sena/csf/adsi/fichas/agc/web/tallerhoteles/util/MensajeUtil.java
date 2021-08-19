/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ismael
 */
public final class MensajeUtil {
    
    private MensajeUtil(){}
    
    public static void mostrarErrorFatal(String idElemento, String mensaje, String detalle){
        mostrarMensaje(idElemento, mensaje, detalle, FacesMessage.SEVERITY_FATAL);
    }
    
    public static void mostrarError(String mensaje, String detalle){
        mostrarMensaje(null, mensaje, detalle, FacesMessage.SEVERITY_ERROR);
    }
    
    public static void mostrarError(String idElemento, String mensaje, String detalle){
        mostrarMensaje(idElemento, mensaje, detalle, FacesMessage.SEVERITY_ERROR);
    }
    
    public static void mostrarAlerta(String idElemento, String mensaje, String detalle){
        mostrarMensaje(idElemento, mensaje, detalle, FacesMessage.SEVERITY_WARN);
    }
    
    public static void mostrarInformativo(String mensaje, String detalle){
        mostrarMensaje(null, mensaje, detalle, FacesMessage.SEVERITY_INFO);
    }
    
    public static void mostrarInformativo(String idElemento, String mensaje, String detalle){
        mostrarMensaje(idElemento, mensaje, detalle, FacesMessage.SEVERITY_INFO);
    }
    
    public static void mostrarMensaje(String idElemento, String mensaje, String detalle, FacesMessage.Severity tipoMensaje){
        FacesMessage message = new FacesMessage(
                tipoMensaje, 
                mensaje, 
                detalle);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getFlash().setKeepMessages(true);
        fc.addMessage(idElemento, message);
    }
}
