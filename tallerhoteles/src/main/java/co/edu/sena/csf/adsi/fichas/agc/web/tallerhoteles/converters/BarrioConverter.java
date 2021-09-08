/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.converters;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.BarrioDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Barrio;
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
@FacesConverter(forClass = Barrio.class)
public class BarrioConverter implements Converter<Barrio>{
    
    private final BarrioDAO barrioDAO;
    
    public BarrioConverter(){
        this.barrioDAO = CDI.current().select(BarrioDAO.class).get();
    }

    @Override
    public Barrio getAsObject(FacesContext context, UIComponent component, String value) {
        if(Objects.isNull(value)){
            return null;
        }
        Integer idBarrio = Integer.valueOf(value);
        return barrioDAO.buscarPorId(idBarrio);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Barrio barrio) {
        if(Objects.isNull(barrio)){
            return null;
        }
        return String.valueOf(barrio.getId());
    }
    
}
