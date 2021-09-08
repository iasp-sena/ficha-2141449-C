/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.exception;

import lombok.Getter;

/**
 *
 * @author Ismael
 */
@Getter
public class AppException extends RuntimeException{
    private TypeException type;

    public AppException(TypeException type) {
        super(type.getCodeAndMessage());
        this.type = type;
    }

    public AppException(TypeException type, Throwable thrwbl) {
        super(type.getCodeAndMessage(), thrwbl);
        this.type = type;
    }
    
}
