/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Ismael
 */
@FacesConverter(value = "dateFormatConverter")
public class DateFormatConverter implements Converter<Date> {

    private final SimpleDateFormat formatDate;

    public DateFormatConverter() {
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public Date getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("Convertidor de string a fecha.......");
        try {
            if (Objects.isNull(value)) {
                return null;
            }
            return formatDate.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(DateFormatConverter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Date fecha) {
        System.out.println("Convertidor de fecha a string.......");
        if (Objects.isNull(fecha)) {
            return null;
        }
        return formatDate.format(fecha);
    }

}
