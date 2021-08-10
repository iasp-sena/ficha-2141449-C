/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ismael
 */
@Local
public interface UsuarioDAO {
    
    List<Usuario> buscarTodos();
    
    Usuario buscarUsuarioPorId(Integer idUsuario);
    
}
