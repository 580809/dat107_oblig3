package Dao;

import Model.Prosjekt;
import jakarta.persistence.*;

public class ProsjektDao {
    private EntityManager em;

    public ProsjektDao(EntityManager em) {
        this.em = em;
    }

    public void lagreProsjekt(Prosjekt prosjekt) {
        em.getTransaction().begin();
        em.persist(prosjekt);
        em.getTransaction().commit();
    }

    public Prosjekt finnProsjektMedId(int prosjektId) {
        return em.find(Prosjekt.class, prosjektId);
    }
}