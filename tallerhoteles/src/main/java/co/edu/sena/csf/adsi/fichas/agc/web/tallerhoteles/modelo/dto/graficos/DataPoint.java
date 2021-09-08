/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.modelo.dto.graficos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author Ismael
 */
@Builder
@AllArgsConstructor
@Getter
public class DataPoint {
    private String label;
    private Double x;
    private Double y;
    private String indexLabel;
}
