/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.sesion;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.UsuarioDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Usuario;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.MensajeUtil;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.RedireccionUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class LoginControlador implements Serializable {
    
    @Inject
    private SesionControlador sesionControlador;
    @Inject
    private UsuarioDAO usuarioDAO;

    private String usuario;
    private String clave;

    public void iniciarSesion() {
        if (camposValidos()) {
            Usuario usuario = usuarioDAO.consultarPorUsuarioClave(this.usuario, clave);
            sesionControlador.setUsuario(usuario);
            if(sesionControlador.cargarRolInicial()){
                RedireccionUtil.redireccionar("/app/index.xhtml");
            } else {
                MensajeUtil.mostrarError("Usuario sin rol habilitado", "Por favor contacte al administrador para más información.");
            }
        } else {
            MensajeUtil.mostrarError("Validación de datos", "Todos los campos son requeridos.");
        }
    }

    private boolean camposValidos() {
        return Objects.nonNull(usuario) && !usuario.isEmpty()
                && Objects.nonNull(clave) && !clave.isEmpty();
    }

    private void validarUsuario(Usuario usuario) {
        if(Objects.isNull(usuario)){
            MensajeUtil.mostrarError("Datos incorrectos", "Verifice su usuario y clave.");
        } else{
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setter">
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    //</editor-fold>

}
