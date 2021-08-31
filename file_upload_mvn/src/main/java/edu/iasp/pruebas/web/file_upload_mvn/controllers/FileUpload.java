/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iasp.pruebas.web.file_upload_mvn.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;

/**
 *
 * @author Ismael
 */
@Named(value = "fileUpload")
@ViewScoped
public class FileUpload implements Serializable{

    private Part filePart;
    /**
     * Creates a new instance of FileUpload
     */
    public FileUpload() {
    }

    public Part getFilePart() {
        return filePart;
    }

    public void setFilePart(Part filePart) {
        this.filePart = filePart;
    }

    public void accion() throws IOException{
        if(filePart != null){
            System.out.println("********************************************");
            System.out.println(filePart.getContentType());
            System.out.println(filePart.getName());
            System.out.println(filePart.getSubmittedFileName());
            System.out.println(filePart.getSize());
            if(filePart.getContentType().equals("application/pdf")){
                File folder = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("")+"/files/temps");
                System.out.println(folder.getAbsolutePath());
                if(!folder.exists()){
                    folder.mkdirs();
                }
                try(InputStream is = filePart.getInputStream()) {
                    Files.copy(is, (new File(folder, filePart.getSubmittedFileName())).toPath() , StandardCopyOption.REPLACE_EXISTING);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se cargó correctamente el archivo " + filePart.getSubmittedFileName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El archivo de ser un pdf."));
            }
        }
    }
    
    
}
