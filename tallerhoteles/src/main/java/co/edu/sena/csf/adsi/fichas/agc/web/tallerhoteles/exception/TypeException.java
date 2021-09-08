/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Ismael
 */
@Getter
@AllArgsConstructor
public enum TypeException {
    USU_CARG_MASIVA_VALID_ARCHIVO_VACIO("USU-0001", "Error validando el archivo", "No se ha adjuntado un archivo."),
    USU_CARG_MASIVA_VALID_ARCHIVO_NO_CSV("USU-0002", "Error validando el archivo", "El archivo no tiene el formato CSV."),
    USU_CARG_MASIVA_VALID_ARCHIVO_LINEA_CANTIDAD_COLUMNAS("USU-0003", "Error transoformando datos", "El archivo tiene filas que no tienen el número de columanas esperado."),
    USU_CARG_MASIVA_VALID_ARCHIVO_LINEA_DATO_INVALIDO("USU-0004", "Error transoformando datoo", "Hay filas que no tienen los datos en formato esperado."),
    USU_CARG_MASIVA_VALID_ARCHIVO_1("USU-0005", "Error validando el archivo", "El archivo no tiene el formato CSV."),
    USU_CARG_MASIVA_VALID_ARCHIVO_2("USU-0006", "Error validando el archivo", null);
    
    private final String code;
    private final String message;
    private final String details;

    public String getCodeAndMessage(){
        return code.concat(" - ").concat(message);
    }
}
