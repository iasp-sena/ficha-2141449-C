/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Ciudad;
import javax.ejb.Stateless;

/**
 *
 * @author Ismael
 */
@Stateless
public class CiudadDAOJPA extends GenericDAOJPA<Ciudad, Integer> implements CiudadDAO {
    
    public CiudadDAOJPA() {
        super(Ciudad.class);
    }
    
}
