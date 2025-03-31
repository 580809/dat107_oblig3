package Dao;

import Model.Ansatt;

import jakarta.persistence.*;
import java.util.List;

public class AnsattDao {
    private EntityManager em;

    public AnsattDao(EntityManager em) {
        this.em = em;
    }

    public Ansatt finnAnsattMedId(int id) {
        return em.find(Ansatt.class, id);
    }

    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
        try {
            return em.createQuery("FROM Ansatt a WHERE a.brukernavn = :bn", Ansatt.class)
                     .setParameter("bn", brukernavn)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Ansatt> hentAlleAnsatte() {
        return em.createQuery("FROM Ansatt a", Ansatt.class).getResultList();
    }

    public void lagreNyAnsatt(Ansatt ansatt) {
        em.getTransaction().begin();
        em.persist(ansatt);
        em.getTransaction().commit();
    }

    public void oppdaterStillingOgLonn(int id, String stilling, double lonn) {
        Ansatt a = finnAnsattMedId(id);
        if (a != null) {
            em.getTransaction().begin();
            a.setStilling(stilling);
            a.setManedslonn(lonn);
            em.getTransaction().commit();
        }
    }
}
