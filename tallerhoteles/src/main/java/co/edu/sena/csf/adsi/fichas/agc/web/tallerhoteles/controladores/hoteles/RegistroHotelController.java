/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.hoteles;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.HotelDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Hotel;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.MensajeUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class RegistroHotelController implements Serializable{
    
    @Inject
    private HotelDAO hotelDAO;
    private Hotel nuevoHotel;
    
    @PostConstruct
    public void init(){
        this.nuevoHotel = new Hotel();
    }
    
    public void registrarHotel(){
        System.out.println("Vamos a registrar un hotel....");
        System.out.println(
                String.format("Nombre: %s - Dirección: %s - Telefonos: %s - Barrio: %s",
                        this.nuevoHotel.getNombre(),
                        this.nuevoHotel.getDireccion(),
                        this.nuevoHotel.getTelefonos(),
                        this.nuevoHotel.getBarrio()
                ));
        
        hotelDAO.registrar(nuevoHotel);
        
        MensajeUtil.mostrarInformativo("Registro", "Se ha registrado correctamente el hotel");
    }
    
    public Hotel getNuevoHotel() {
        return nuevoHotel;
    }
    
}
