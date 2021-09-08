/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.reservas;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.ReservaDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.dto.graficos.DataPoint;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.view.ReservaPorMes;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
@Getter
public class InformeReservas implements Serializable{
    
    @Inject 
    private ReservaDAO reservaDAO;
    
    private List<DataPoint> points;
    
    @PostConstruct
    public void init(){
        List<ReservaPorMes> datosInforme = reservaDAO.buscarDatosReservaPorMes();
        points = datosInforme.stream()
                .map(this::toDatapoint)
                .collect(Collectors.toList());
    }
    
    private DataPoint toDatapoint(ReservaPorMes reservaPorMes){
        return DataPoint.builder()
                .label(reservaPorMes.getMes())
                .y((double)reservaPorMes.getCantidad())
                .build();
    }
    
    public String getPointJson(){
        Gson gson = new Gson();
        String json = gson.toJson(points);
        System.out.println("Este el objeto transformado a json:");
        System.out.println(json);
        return json;
    }
}
