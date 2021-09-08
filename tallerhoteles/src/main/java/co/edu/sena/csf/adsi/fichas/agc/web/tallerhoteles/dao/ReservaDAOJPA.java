/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Reserva;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.view.ReservaPorMes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ismael
 */
@Stateless
public class ReservaDAOJPA extends GenericDAOJPA<Reserva, Integer> implements ReservaDAO {
    
    public ReservaDAOJPA() {
        super(Reserva.class);
    }

    @Override
    public List<ReservaPorMes> buscarDatosReservaPorMes() {
        TypedQuery<ReservaPorMes> query = em.createQuery("SELECT rpm FROM ReservaPorMes rpm", ReservaPorMes.class);
        return query.getResultList();
    }
    
}
