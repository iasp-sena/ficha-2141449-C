/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Habitacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ismael
 */
@Stateless
public class HabitacionDAOJPA extends GenericDAOJPA<Habitacion, Integer> implements HabitacionDAO {
    
    public HabitacionDAOJPA() {
        super(Habitacion.class);
    }

    @Override
    public List<Habitacion> buscarPorIdHotel(Integer idHotel) {
        TypedQuery<Habitacion> query = em.createQuery(
                "SELECT h FROM Habitacion h WHERE h.hotel.id = :idHotel", 
                classType
        );
        query.setParameter("idHotel", idHotel);
        return query.getResultList();
    }
    
}
