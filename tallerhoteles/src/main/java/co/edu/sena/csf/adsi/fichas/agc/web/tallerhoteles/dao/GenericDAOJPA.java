/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.csf.adsi.fichas.agc.web.tallerhoteles.dao;

import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ismael
 */
public abstract class GenericDAOJPA<T, K> {
    
    private final static String FORMAT_NQ_FIND_ALL = "%s.findAll";
    
    @PersistenceContext(unitName = "tallerHotelPU")
    protected EntityManager em;
    protected Class<T> classType;
    
    protected GenericDAOJPA(Class<T> classType){
        this.classType = classType;
    }

    public List<T> buscarTodos() {
        String namedQuery = String.format(FORMAT_NQ_FIND_ALL, classType.getSimpleName());
        TypedQuery<T> query = em.createNamedQuery(namedQuery, classType);
        return query.getResultList();
    }

    public T buscarPorId(K id) {
        return em.find(classType, id);
    }

    public void registrar(T obj) {
        em.persist(obj);
    }

    public void registrar(List<T> objs) {
        if(Objects.isNull(objs)){
            throw new IllegalArgumentException("La lista de objetos a persistir no puede ser nula (" + classType.getName() + ")");
        }
        objs.forEach(this::registrar);
    }

    public void actualizar(T obj) {
        em.persist(em.merge(obj));
    }

    public void eliminar(T obj) {
        em.remove(obj);
    }
    
}
