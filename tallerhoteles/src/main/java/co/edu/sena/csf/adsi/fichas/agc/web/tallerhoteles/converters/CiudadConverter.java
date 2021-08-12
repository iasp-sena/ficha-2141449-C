/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.converters;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.CiudadDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.Ciudad;
import java.util.Objects;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Ismael
 */
@FacesConverter(forClass = Ciudad.class)
public class CiudadConverter implements Converter<Ciudad>{
    
    private final CiudadDAO ciudadDAO;
    
    public CiudadConverter(){
        this.ciudadDAO = CDI.current().select(CiudadDAO.class).get();
    }

    @Override
    public Ciudad getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("Converter recuperando valor " + value);
        System.out.println("Converter ciudadDAO " + ciudadDAO);
        if(Objects.isNull(value)){
            return null;
        }
        Integer idCiudad = Integer.valueOf(value);
        return ciudadDAO.buscarPorId(idCiudad);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Ciudad ciudad) {
        System.out.println("Converter enviando ciudad " + ciudad);
        if(Objects.isNull(ciudad)){
            return null;
        }
        return String.valueOf(ciudad.getId());
    }
    
}
