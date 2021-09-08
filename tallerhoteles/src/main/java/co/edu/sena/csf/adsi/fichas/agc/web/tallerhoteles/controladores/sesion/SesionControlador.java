/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.sesion;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Permiso;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Rol;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.RolUsuario;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Usuario;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.MensajeUtil;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.RedireccionUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Ismael
 */
@Named
@SessionScoped
public class SesionControlador implements Serializable {
    
    private static final String FOLDER_PATH = "/resources/img";
    private static final String IMG_PERFIL_DEFAULT = "/perfil/default.jpg";
    

    private Usuario usuario;
    private List<Rol> rolesActivosDelUsuario;
    private Rol rolSeleccionado;

    public boolean inicioSesion() {
        return Objects.nonNull(usuario);
    }

    public void validarSesion() {
        if (!inicioSesion()) {
            MensajeUtil.mostrarError("Datos incorrectos", "Verifice su usuario y clave.");
            RedireccionUtil.redireccionar("/index.xhtml");
        }
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .invalidateSession();
        RedireccionUtil.redireccionar("/index.xhtml");
    }

    public boolean cargarRolInicial() {
        validarSesion();
        cargarRolesActivosDelUsuario();
        if (!usuarioTieneRolesActivos()) {
            return false;
        }
        rolSeleccionado = usuario.getRolesUsuario()
                .get(0)
                .getRol();
        return true;
    }

    private void cargarRolesActivosDelUsuario() {
        if (usuarioTieneRoles()) {
            rolesActivosDelUsuario = usuario.getRolesUsuario().stream()
                    .filter(rolUsuario -> rolUsuario.getActivo())
                    .map(rolUsuario -> rolUsuario.getRol())
                    .collect(Collectors.toList());
        }
    }

    private boolean usuarioTieneRoles() {
        return Objects.nonNull(usuario.getRolesUsuario())
                && !usuario.getRolesUsuario().isEmpty();
    }

    private boolean usuarioTieneRolesActivos() {
        return Objects.nonNull(rolesActivosDelUsuario)
                && !rolesActivosDelUsuario.isEmpty();
    }
    
    public String getFotoPerfil(){
        //String folderApp = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
        String contextApp = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        String imagen = Objects.isNull(usuario.getFotoPerfil()) ? IMG_PERFIL_DEFAULT : usuario.getFotoPerfil();
        return contextApp + FOLDER_PATH + imagen;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRolSeleccionado() {
        return rolSeleccionado;
    }

    public void setRolSeleccionado(Rol rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public List<Rol> getRolesActivosDelUsuario() {
        return rolesActivosDelUsuario;
    }

    public void setRolesActivosDelUsuario(List<Rol> rolesActivosDelUsuario) {
        this.rolesActivosDelUsuario = rolesActivosDelUsuario;
    }

    public List<Permiso> getPermisosPrincipalesRolSeleccionado() {
        return rolSeleccionado.getPermisos().stream()
                .filter(permiso -> Objects.isNull(permiso.getPermisoSuperior()))
                .collect(Collectors.toList());
    }

    public List<Permiso> getSubpermisosDePermisoSuperior(Permiso permisoSuperior) {
        return rolSeleccionado.getPermisos().stream()
                .filter(permiso -> 
                        Objects.nonNull(permiso.getPermisoSuperior()) 
                        && permiso.getPermisoSuperior().equals(permisoSuperior))
                .collect(Collectors.toList());
    }

    //</editor-fold>
}
