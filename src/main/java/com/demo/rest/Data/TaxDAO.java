package com.demo.rest.Data;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

// import com.demo.rest.Models.Event;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
@Transactional
public class TaxDAO {

    @PersistenceContext(name = "TaxPU")
    private EntityManager em;

    public void createEvent(TaxInfo event) {
        em.persist(event);
    }

    public TaxInfo readEvent(int eventId) {
        return em.find(TaxInfo.class, eventId);
    }

    public void updateEvent(TaxInfo event) {
        em.merge(event);
    }

    public void deleteEvent(TaxInfo event) {
        em.remove(event);
    }

    public List<TaxInfo> readAllEvents() {
        return em.createNamedQuery("TaxInfo.findAll", TaxInfo.class).getResultList();
    }

}