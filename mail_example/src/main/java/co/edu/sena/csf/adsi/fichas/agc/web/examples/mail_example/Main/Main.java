/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.examples.mail_example.Main;

import co.edu.sena.csf.adsi.fichas.agc.web.examples.mail_example.util.MailUtil;
import java.io.File;

/**
 *
 * @author Ismael
 */
public class Main {
    public static void main(String[] args) {
        MailUtil.sendHTML(
                "set-ra@hotmail.com,william.bacafb@gmail.com", 
                "Mensaje de prueba multiple", 
                "<h2 style='color: red;'>Bienvenido a nuestra aplicación</h2>"
                + "<p>Este es un mensaje de bienvenidad de nuestra aplicación de <span style='color:blue;'>Java</span></p>"
                + "<img src='https://www.muycomputer.com/wp-content/uploads/2020/12/Servicios_de_Google.png'/>",
                new File("C:\\Users\\Ismael\\Downloads\\Envío de correos en Java.pptx.pdf"),
                new File("C:\\Users\\Ismael\\Downloads\\Contenido_Testing_V1.0.docx.pdf")
        );
    }
}
