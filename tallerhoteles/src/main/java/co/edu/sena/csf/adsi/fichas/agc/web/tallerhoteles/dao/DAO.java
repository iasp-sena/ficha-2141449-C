/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ismael
 */
public interface DAO<T, K> extends Serializable {
    
    List<T> buscarTodos();
    
    T buscarPorId(K id);
    
    void registrar(T obj);
    
    void registrar(List<T> obj);
    
    void actualizar(T obj);
    
    void eliminar(T obj);
}
