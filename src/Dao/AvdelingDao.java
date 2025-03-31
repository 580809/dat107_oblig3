package Dao;

import Model.Avdeling;

import jakarta.persistence.*;

public class AvdelingDao {
    private EntityManager em;

    public AvdelingDao(EntityManager em) {
        this.em = em;
    }

    public Avdeling finnAvdelingMedId(int id) {
        return em.find(Avdeling.class, id);
    }

    public void lagreNyAvdeling(Avdeling avdeling) {
        em.getTransaction().begin();
        em.persist(avdeling);
        em.getTransaction().commit();
    }
}

