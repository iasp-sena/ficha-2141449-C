/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.servicios.usuarios;

import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.exception.AppException;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.exception.TypeException;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.DatosBasicosPersona;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Rol;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.RolUsuario;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.TipoDocumento;
import co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.entities.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.servlet.http.Part;

/**
 *
 * @author Ismael
 */
@Stateless
public class MapperCsvUsuariosServicio {

    private static final String FORMAT_TEXT_CSV = "text/csv";
    private static final String FORMAT_EXCEL_CSV = "application/vnd.ms-excel";
    private static final String EXTENTION_CSV = ".csv";
    private static final String SEPARETOR_CHAR = ",";
    private static final String SEPARETOR_CHAR_ROLES = "\\|";
    private static final int COLUMNS_QUANTITY = 8;
    
    public List<Usuario> transformar(Part file){
        validarArchivo(file);
        return transformarDataAObjetos(file);
    }
    

    private void validarArchivo(final Part file) {
        validaArchivoVacio(file);
        validarFormatoArchivoCSV(file);
    }

    private void validaArchivoVacio(final Part file) {
        if (Objects.isNull(file) || file.getSize() == 0) {
            throw new AppException(TypeException.USU_CARG_MASIVA_VALID_ARCHIVO_VACIO);
        }
    }

    private void validarFormatoArchivoCSV(final Part file) {
        if ((!file.getContentType().equals(FORMAT_TEXT_CSV) && !file.getContentType().equals(FORMAT_EXCEL_CSV))
                || !file.getSubmittedFileName().endsWith(EXTENTION_CSV)) {
            throw new AppException(TypeException.USU_CARG_MASIVA_VALID_ARCHIVO_NO_CSV);
        }
    }

    private List<Usuario> transformarDataAObjetos(final Part file) {
        try (InputStream is = file.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr)) {
            String line = br.readLine();
            List<Usuario> usuarios = new ArrayList<>();
            while (Objects.nonNull(line = br.readLine())) {
                usuarios.add(transformarDatosLineaArchivo(line.split(SEPARETOR_CHAR)));
            }
            return usuarios;
        } catch (IOException ex) {
            throw new AppException(TypeException.USU_CARG_MASIVA_VALID_ARCHIVO_VACIO, ex);
        }
    }

    private Usuario transformarDatosLineaArchivo(String[] data) {
        if (data.length != COLUMNS_QUANTITY) {
            throw new AppException(TypeException.USU_CARG_MASIVA_VALID_ARCHIVO_LINEA_CANTIDAD_COLUMNAS);
        }
        try {
            Usuario usuario = Usuario.builder()
                    .datosBasicos(buildDatosBasicosPersona(data))
                    .nombreUsuario(data[5])
                    .clave(data[6])
                    .activo(true)
                    .build();
            usuario.setRolesUsuario(buildRolesUsuarios(data[7].split(SEPARETOR_CHAR_ROLES), usuario));
            return usuario;
        } catch (Exception e) {
            throw new AppException(TypeException.USU_CARG_MASIVA_VALID_ARCHIVO_LINEA_CANTIDAD_COLUMNAS, e);
        }
    }

    private DatosBasicosPersona buildDatosBasicosPersona(String data[]) {
        return DatosBasicosPersona.builder()
                .tipoDocumento(new TipoDocumento(Integer.valueOf(data[0])))
                .numeroDocumento(data[1])
                .nombres(data[2])
                .apellidos(data[3])
                .email(data[4])
                .build();
    }

    private List<RolUsuario> buildRolesUsuarios(String[] dataRoles, Usuario usuario) {
        return Arrays.stream(dataRoles)
                .map(idRol -> buldRolUsuario(idRol, usuario))
                .collect(Collectors.toList());
    }

    public RolUsuario buldRolUsuario(String idRol, Usuario usuario) {
        return RolUsuario.builder()
                .activo(true)
                .rol(new Rol(Integer.valueOf(idRol)))
                .usuario(usuario)
                .build();
    }
    /*
    td,num_doc,nombres,apellidos,email,nobre_usuario,clave,roles
    1,123456,Andres Francisco,Pérez,iasuarez5@misena.edu.co,afperez,123456,1|2
    2,123457,Adriana Daniela,Castro,iasuarez5@misena.edu.co,adcastro,123456,1|3
     */

    /*
    1,123456,Andres Francisco,Pérez,iasuarez5@misena.edu.co,afperez,123456,1|2
    2,123457,Adriana Daniela,Castro,iasuarez5@misena.edu.co,adcastro,123456,1|3
     */
}
