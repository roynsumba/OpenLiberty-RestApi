package com.demo.rest.Data;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@RequestScoped
public class TinProfileDAO {

    @PersistenceContext(name = "TaxPU")
    private EntityManager entityManager;


    @Transactional
    public void create(TinProfilePayloadEntity entity) {
        entityManager.persist(entity);
    }

    public TinProfilePayloadEntity read(Long id) {
        return entityManager.find(TinProfilePayloadEntity.class, id);
    } 

    public  List<TinProfilePayloadEntity> findAll() {
        return entityManager.createNamedQuery("TinProfilePayloadEntity.findAll", TinProfilePayloadEntity.class).getResultList();
    }

    @Transactional
    public void update(TinProfilePayloadEntity entity) {
        entityManager.merge(entity);
    }

    
    public  void delete(TinProfilePayloadEntity entity) {
        entityManager.remove(entity);
    }
}