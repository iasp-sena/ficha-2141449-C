/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Hotel;
import javax.ejb.Stateless;

/**
 *
 * @author Ismael
 */
@Stateless
public class HotelDAOJPA extends GenericDAOJPA<Hotel, Integer> implements HotelDAO {
    
    public HotelDAOJPA() {
        super(Hotel.class);
    }
    
}
