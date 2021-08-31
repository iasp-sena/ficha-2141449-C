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
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;

/**
 *
 * @author Ismael
 */
@Named(value = "fileMultiUpload")
@ViewScoped
public class FileMultiUpload implements Serializable {

    private List<Part> filesParts;
    private Part filePart;

    /**
     * Creates a new instance of FileUpload
     */
    public FileMultiUpload() {
    }

    public Part getFilePart() {
        return filePart;
    }

    public void setFilePart(Part filePart) {
        this.filePart = filePart;
    }

    public List<Part> getFilesParts() {
        if (filesParts == null) {
            filesParts = new ArrayList<>();
        }
        return filesParts;
    }

    public void addFile() {
        if (filePart != null) {
            getFilesParts().add(filePart);
            sendMessageInfo("file", "Se precargó el archivo.", "");
            filePart = null;
        } else {
            sendMessageInfo("file", "Debe seleccionar un archivo ", "");
        }
    }

    public void accion() throws Exception {
        if (!getFilesParts().isEmpty()) {
            System.out.println("dhasjkhdajkdhjasdkasdasd");
            try {
                for (Part part : filesParts) {
                    saveFileTemp(part);
                }
                filesParts = null;
                //sendMessageInfo(null, "Debe agregar por lo menos un archivo.", "");
            } catch (Exception e) {
                e.printStackTrace();
                for (Part part : filesParts) {
                    deleteFileTemp(part);
                }
                sendMessageInfo(null, e.getMessage(), "");
            } 
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        } else {
            sendMessageInfo(null, "Debe agregar por lo menos un archivo.", "");
        }
    }

    private void saveFileTemp(Part part) throws Exception {
        System.out.println("********************************************");
        System.out.println(part.getContentType());
        System.out.println(part.getName());
        System.out.println(part.getSubmittedFileName());
        System.out.println(part.getSize());
        File folder = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/files/temps");
        System.out.println(folder.getAbsolutePath());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try (InputStream is = part.getInputStream()) {
            Files.copy(is, (new File(folder, part.getSubmittedFileName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
            sendMessageInfo(null, "Se cargó correctamente el archivo " + part.getSubmittedFileName(), "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al guardar el archivo " + part.getSubmittedFileName(), e);
        }
    }

    private void deleteFileTemp(Part part) throws Exception {
        System.out.println("********************************************");
        System.out.println(part.getContentType());
        System.out.println(part.getName());
        System.out.println(part.getSubmittedFileName());
        System.out.println(part.getSize());
        File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "/files/temps/" + part.getSubmittedFileName());
        if (file.exists()) {
            file.deleteOnExit();
        }
    }

    public void sendMessageInfo(String clientId, String message, String detail) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail));
    }

}
