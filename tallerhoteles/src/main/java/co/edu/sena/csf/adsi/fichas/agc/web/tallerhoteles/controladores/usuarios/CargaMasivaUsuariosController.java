/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.usuarios;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.UsuarioDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.exception.AppException;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Usuario;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.servicios.usuarios.MapperCsvUsuariosServicio;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.MensajeUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class CargaMasivaUsuariosController implements Serializable {

    @Inject
    private MapperCsvUsuariosServicio mapper;
    @Inject
    private UsuarioDAO usuarioDAO;
    
    @Getter
    @Setter
    private Part file;

    public void cargarUsuarios() {
        try {
            List<Usuario> usuariosARegistrar = mapper.transformar(file);
            usuarioDAO.registrar(usuariosARegistrar);
        } catch (AppException e) {
            MensajeUtil.mostrarAlerta(null, e);
            e.printStackTrace(System.out);
        }
    }
}
