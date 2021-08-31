/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author ismael
 */
public final class FileUtil {

    private FileUtil() {

    }

    public static boolean save(Part filePart, String folderOut, String fileName) {
        File folder = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + folderOut);
        System.out.println(folder.getAbsolutePath());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try ( InputStream is = filePart.getInputStream()) {
            Files.copy(is, (new File(folder, fileName)).toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
