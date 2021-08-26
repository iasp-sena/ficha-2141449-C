/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.controladores.reservas;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao.ReservaDAO;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.Reserva;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util.MensajeUtil;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Ismael
 */
@Named
@ViewScoped
public class ReservasReporteControlador implements Serializable {
    
    @Inject
    private ReservaDAO reservaDAO;
    
    public void generarResportePDF(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) ec.getResponse();
        response.setHeader("Content-diposition", "attachment; filename=reporteReservas.pdf");
        
        List<Reserva> reservas = reservaDAO.buscarTodos();
        File reporteFile = new File(ec.getRealPath("/WEB-INF/classes/reportes/report_reservas.jasper"));
        
        System.out.println("El archivo se está buscando en: " + reporteFile.getPath());
        if(reporteFile.exists()){
            try(OutputStream out = response.getOutputStream()) {
                Map<String, Object> parametrosReporte = new HashMap<>();
                parametrosReporte.put("NOMBRE_GENERA_REPORTE", "Daniel Castro");
                JRDataSource dataReporte = new JRBeanCollectionDataSource(reservas);
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporteFile.getPath(), parametrosReporte, dataReporte);
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                fc.responseComplete();
            } catch (JRException ex) {
                ex.printStackTrace(System.out);
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        } else{
            MensajeUtil.mostrarAlerta(
                    null, 
                    "El repote solicitado no se encuentra.", 
                    "Intente nuevamente, si persiste el problema contacte al administrador.");
        }
    }
    
}
