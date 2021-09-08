/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Barrio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ismael
 */
@Stateless
public class BarrioDAOJPA extends GenericDAOJPA<Barrio, Integer> implements BarrioDAO {
    
    public BarrioDAOJPA() {
        super(Barrio.class);
    }

    @Override
    public List<Barrio> buscarPorIdCiudad(Integer idCiudad) {
        TypedQuery<Barrio> query = em.createQuery(
                "SELECT b FROM Barrio b WHERE b.ciudad.id = :idCiudad", 
                classType
        );
        query.setParameter("idCiudad", idCiudad);
        return query.getResultList();
    }
    
}
