/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.commons;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.HuespedDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.TipoDocumentoDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.Huesped;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.TipoDocumento;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.MensajeUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
public class BuscarHuespedControlador implements Serializable {
    
    @Inject
    private TipoDocumentoDAO tipoDocumentoDAO;
    @Inject
    private HuespedDAO huespedDAO;
    
    private List<TipoDocumento> tiposDocumentos;
    private TipoDocumento tipoDocumentoSeleccionado;
    private String numeroDocumento;
    private Huesped huespedSeleccionado;
    
    @PostConstruct
    public void init(){
        tiposDocumentos = tipoDocumentoDAO.buscarTodos();
    }
    
    public void buscarHuesped(){
        if(datosDeBusquedaValidos()){
            Optional<Huesped> huespedBuscado = huespedDAO
                    .buscarPorTipoYNumeroDocumento(
                            tipoDocumentoSeleccionado.getId(), 
                            numeroDocumento);
            if(huespedBuscado.isPresent()){
                huespedSeleccionado = huespedBuscado.get();
            } else{
                MensajeUtil.mostrarAlerta(null, "Huesped no encontrado", "");
                huespedSeleccionado = null;
            }
        }
    }
    
    private boolean datosDeBusquedaValidos(){
        return Objects.nonNull(tipoDocumentoSeleccionado) &&
                Objects.nonNull(numeroDocumento) && !numeroDocumento.isEmpty();
    }
    
    public boolean mostarInfoHuespedSeleccionado(){
        return Objects.nonNull(huespedSeleccionado);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
    
    public TipoDocumento getTipoDocumentoSeleccionado() {
        return tipoDocumentoSeleccionado;
    }
    
    public void setTipoDocumentoSeleccionado(TipoDocumento tipoDocumentoSeleccionado) {
        this.tipoDocumentoSeleccionado = tipoDocumentoSeleccionado;
    }
    
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Huesped getHuespedSeleccionado() {
        return huespedSeleccionado;
    }

    public void setHuespedSeleccionado(Huesped huespedSeleccionado) {
        this.huespedSeleccionado = huespedSeleccionado;
    }
    
    public List<TipoDocumento> getTiposDocumentos() {
        return tiposDocumentos;
    }
    
    //</editor-fold>

    
}
