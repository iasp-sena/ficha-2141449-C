/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.hoteles;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.HotelDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.Hotel;
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
public class VerHotelesController implements Serializable {
    
    @Inject
    private HotelDAO hotelDAO;
    
    private List<Hotel> hoteles;
    
    @PostConstruct
    public void init(){
        this.hoteles = hotelDAO.buscarTodos();
    }

    public List<Hotel> getHoteles() {
        return hoteles;
    }
    
}
