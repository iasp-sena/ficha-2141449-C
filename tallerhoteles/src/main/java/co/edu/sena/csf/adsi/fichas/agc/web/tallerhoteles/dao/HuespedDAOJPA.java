/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Huesped;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ismael
 */
@Stateless
public class HuespedDAOJPA extends GenericDAOJPA<Huesped, Integer> implements HuespedDAO {

    public HuespedDAOJPA() {
        super(Huesped.class);
    }

    @Override
    public Optional<Huesped> buscarPorTipoYNumeroDocumento(Integer idTipoDocumento, String numeroDocumento) {
        try {
            TypedQuery<Huesped> query = em.createNamedQuery(
                    "Huesped.findByTipoYNumeroDocumento",
                    classType
            );
            query.setParameter("idTipoDocumento", idTipoDocumento);
            query.setParameter("numeroDocumento", numeroDocumento);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException | NonUniqueResultException ex) {
            ex.printStackTrace(System.out);
            return Optional.empty();
        }
    }

}
