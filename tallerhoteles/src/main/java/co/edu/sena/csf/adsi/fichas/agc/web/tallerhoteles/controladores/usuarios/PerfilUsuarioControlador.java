/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.usuarios;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.sesion.SesionControlador;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.UsuarioDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Usuario;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.FileUtil;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.MensajeUtil;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author ismael
 */
@Named
@ViewScoped
public class PerfilUsuarioControlador implements Serializable {
    
    @Inject
    private SesionControlador sesionControlador;
    @Inject
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private Part archivoImagen;
    
    @PostConstruct
    public void init(){
        usuario = sesionControlador.getUsuario();
    }
    
    public void guardar(){
        if(camposValidos()){
            String rootPath = "/resources/img";
            String folderPerfil = "/perfil/";
            String fileName = getFileNamePerfil(archivoImagen.getSubmittedFileName());
            if(FileUtil.save(archivoImagen, rootPath + folderPerfil, fileName)){
                usuario.setFotoPerfil(folderPerfil + fileName);
                usuarioDAO.actualizar(usuario);
                MensajeUtil.mostrarInformativo(null, "Perfil actualizado correctamente!", "");
            } else {
                MensajeUtil.mostrarAlerta(null, "Error guardando la foto de perfil", "");
            }
        } else {
            MensajeUtil.mostrarAlerta(null, "Formulario incompleto", "");
        }
    }
    
    public boolean camposValidos(){
        return Objects.nonNull(usuario.getDatosBasicos().getNombres()) && !usuario.getDatosBasicos().getNombres().isEmpty()
                && Objects.nonNull(usuario.getDatosBasicos().getApellidos()) && !usuario.getDatosBasicos().getApellidos().isEmpty()
                && Objects.nonNull(archivoImagen);
                
    }

    private String getFileNamePerfil(String submittedFileName) {
        String[] splitFileName = submittedFileName.split("\\.");
        String ext = splitFileName[splitFileName.length-1];
        String fileName = "perfil_" + usuario.getId() + "." + ext;
        System.out.println(String.format("Nombre del archivo: %s", fileName));
        return fileName;
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Part getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(Part archivoImagen) {
        this.archivoImagen = archivoImagen;
    }
    
    
}
