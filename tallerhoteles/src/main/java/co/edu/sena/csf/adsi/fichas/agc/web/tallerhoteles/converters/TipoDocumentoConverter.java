/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.converters;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.TipoDocumentoDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.TipoDocumento;
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
@FacesConverter(forClass = TipoDocumento.class)
public class TipoDocumentoConverter implements Converter<TipoDocumento>{
    
    private final TipoDocumentoDAO tipoDocumentoDAO;
    
    public TipoDocumentoConverter(){
        this.tipoDocumentoDAO = CDI.current().select(TipoDocumentoDAO.class).get();
    }

    @Override
    public TipoDocumento getAsObject(FacesContext context, UIComponent component, String value) {
        if(Objects.isNull(value)){
            return null;
        }
        Integer idTipoDocumento = Integer.valueOf(value);
        return tipoDocumentoDAO.buscarPorId(idTipoDocumento);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, TipoDocumento tipoDocumento) {
        if(Objects.isNull(tipoDocumento)){
            return null;
        }
        return String.valueOf(tipoDocumento.getId());
    }
    
}
