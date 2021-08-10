/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ismael
 */
@Stateless
public class UsuarioDAOJPA implements UsuarioDAO {
    
    @PersistenceContext(unitName = "tallerHotelPU")
    private EntityManager em;

    @Override
    public List<Usuario> buscarTodos() {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findAll", Usuario.class);
        return query.getResultList();
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        return em.find(Usuario.class, idUsuario);
    }
    
}
