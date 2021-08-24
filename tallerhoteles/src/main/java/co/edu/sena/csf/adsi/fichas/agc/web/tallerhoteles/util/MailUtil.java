/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Ismael
 */
public final class MailUtil {

    private static final String KEY_SMTP_SERVER = "mail.smtp.host";
    private static final String KEY_SMTP_AUTH = "mail.smtp.auth";
    private static final String KEY_SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String KEY_SMTP_PORT = "mail.smtp.port";
    private static final String KEY_USER_FROM = "mail.smtp.user";
    private static final String KEY_USER_PASSWORD = "mail.smtp.password";

    private static Properties props;

    private MailUtil() {

    }

    public static void send(String para, String asunto, String mensaje) {
        try {
            loadConfig();
            Session session = Session.getDefaultInstance(props);
            MimeMessage message = buildMessage(session, para, asunto, mensaje);
            sendMessage(session.getTransport(), message);
        } catch (AddressException ex) {
            ex.printStackTrace(System.out);
        } catch (MessagingException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private static void loadConfig() {
        if (Objects.isNull(props)) {
            props = new Properties();
            props.setProperty(KEY_SMTP_SERVER, "smtp.gmail.com");
            props.setProperty(KEY_SMTP_AUTH, "true");
            props.setProperty(KEY_SMTP_STARTTLS, "true");
            props.setProperty(KEY_SMTP_PORT, "587");
            props.setProperty(KEY_USER_FROM, "ia.sena.csf@gmail.com");
            props.setProperty(KEY_USER_PASSWORD, "Sena9713*");
        }
    }

    private static MimeMessage buildMessage(Session session, String para, String asunto, String mensaje) throws AddressException, MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(buildAddress(props.getProperty(KEY_USER_FROM)));
        message.addRecipient(Message.RecipientType.TO, buildAddress(para));
        message.setSubject(asunto);
        message.setText(mensaje);
        return message;
    }

    private static Address buildAddress(String email) throws AddressException {
        return new InternetAddress(email);
    }

    private static void sendMessage(Transport transport, MimeMessage message) throws MessagingException {
        transport.connect(
                props.getProperty(KEY_USER_FROM),
                props.getProperty(KEY_USER_PASSWORD)
        );
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static void sendHTML(String para, String asunto, String mensaje, File... files) {
        try {
            loadConfig();
            Session session = Session.getDefaultInstance(props);
            MimeMessage message = buildMessageHTML(session, para, asunto, mensaje, Arrays.asList(files));
            sendMessage(session.getTransport(), message);
        } catch (AddressException ex) {
            ex.printStackTrace(System.out);
        } catch (MessagingException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private static MimeMessage buildMessageHTML(Session session, String para, String asunto, String mensaje) throws AddressException, MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(props.getProperty(KEY_USER_FROM));
        message.addRecipients(Message.RecipientType.TO, para);
        message.setSubject(asunto);

        Multipart partsMessage = new MimeMultipart();
        BodyPart contenidoHTML = new MimeBodyPart();
        contenidoHTML.setContent(mensaje, "text/html");
        partsMessage.addBodyPart(contenidoHTML);

        message.setContent(partsMessage);

        return message;
    }

    private static MimeMessage buildMessageHTML(
            Session session,
            String para,
            String asunto,
            String mensaje,
            List<File> archivos) throws AddressException, MessagingException, IOException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(props.getProperty(KEY_USER_FROM));
        message.addRecipients(Message.RecipientType.TO, para);
        message.setSubject(asunto);

        Multipart partsMessage = new MimeMultipart();
        BodyPart contenidoHTML = new MimeBodyPart();
        contenidoHTML.setContent(mensaje, "text/html");
        partsMessage.addBodyPart(contenidoHTML);

        attachFiles(archivos, partsMessage);
        
        

        message.setContent(partsMessage);

        return message;
    }

    private static void attachFiles(List<File> files, Multipart partsMessage) throws IOException, MessagingException {     
        //archivos.forEach(archivo -> attachFile(archivo, partsMessage));
        
        for (File file : files) {
            attachFile(file, partsMessage);
        }
    }
    
    private static void attachFile(File file, Multipart partsMessage) throws IOException, MessagingException {
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile(file);
        partsMessage.addBodyPart(attachPart);
    }

}
