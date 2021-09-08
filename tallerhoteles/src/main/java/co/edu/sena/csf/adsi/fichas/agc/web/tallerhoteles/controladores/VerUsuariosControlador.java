/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.UsuarioDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Usuario;
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
public class VerUsuariosControlador implements Serializable {

    @Inject
    private UsuarioDAO usuarioDAO;

    private List<Usuario> usuarios;

    public VerUsuariosControlador() {
        System.out.println("Se ejecuta el constructor....");
        System.out.println("UsuarioDAO es: " + usuarioDAO);
    }

    @PostConstruct
    public void init() {
        System.out.println("Se ejecuta el init (PostConstruct)....");
        System.out.println("UsuarioDAO es: " + usuarioDAO);
        buscarUsuarios();
    }

    private void buscarUsuarios() {
        usuarios = usuarioDAO.buscarTodos();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

}
