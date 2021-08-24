/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.converters;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.HotelDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.Hotel;
import java.util.Objects;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Ismael
 */
@FacesConverter(forClass = Hotel.class)
public class HotelConverter implements Converter<Hotel>{
    
    private final HotelDAO hotelDAO;
    
    public HotelConverter(){
        this.hotelDAO = CDI.current().select(HotelDAO.class).get();
    }

    @Override
    public Hotel getAsObject(FacesContext context, UIComponent component, String value) {
        if(Objects.isNull(value)){
            return null;
        }
        Integer idHotel = Integer.valueOf(value);
        return hotelDAO.buscarPorId(idHotel);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Hotel hotel) {
        if(Objects.isNull(hotel)){
            return null;
        }
        return String.valueOf(hotel.getId());
    }
    
}
