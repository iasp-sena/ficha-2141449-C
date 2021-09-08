/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.converters;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.RolDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Rol;
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
@FacesConverter(forClass = Rol.class)
public class RolConverter implements Converter<Rol>{
    
    private final RolDAO rolDAO;
    
    public RolConverter(){
        this.rolDAO = CDI.current().select(RolDAO.class).get();
    }

    @Override
    public Rol getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("Converter recuperando valor " + value);
        System.out.println("Converter ciudadDAO " + rolDAO);
        if(Objects.isNull(value)){
            return null;
        }
        Integer idRol = Integer.valueOf(value);
        return rolDAO.buscarPorId(idRol);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Rol rol) {
        System.out.println("Converter enviando ciudad " + rol);
        if(Objects.isNull(rol)){
            return null;
        }
        return String.valueOf(rol.getId());
    }
    
}
