/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ismael
 */
@Stateless
public class UsuarioDAOJPA extends GenericDAOJPA<Usuario, Integer> implements UsuarioDAO {
    
    public UsuarioDAOJPA() {
        super(Usuario.class);
    }

    @Override
    public Usuario consultarPorUsuarioClave(String usuario, String clave) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUsuarioYClave", classType);
            query.setParameter("nombreUsuario", usuario);
            query.setParameter("clave", clave);
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex){
            return null;
        }
    }
    
}
