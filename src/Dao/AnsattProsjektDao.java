package Dao;

import Model.Ansatt;
import Model.Prosjekt;
import Model.AnsattProsjekt;
import jakarta.persistence.*;

public class AnsattProsjektDao {
    private EntityManager em;

    public AnsattProsjektDao(EntityManager em) {
        this.em = em;
    }

    public void registrerDeltagelse(Ansatt ansatt, Prosjekt prosjekt, String rolle, int arbeidstimer) {
        AnsattProsjekt ap = new AnsattProsjekt(ansatt, prosjekt, rolle, arbeidstimer);
        em.getTransaction().begin();
        em.persist(ap);
        em.getTransaction().commit();
    }

    public void oppdaterTimer(Ansatt ansatt, Prosjekt prosjekt, int arbeidstimer) {
        TypedQuery<AnsattProsjekt> query = em.createQuery("SELECT ap FROM AnsattProsjekt ap WHERE ap.ansatt = :ansatt AND ap.prosjekt = :prosjekt", AnsattProsjekt.class);
        query.setParameter("ansatt", ansatt);
        query.setParameter("prosjekt", prosjekt);
        
        AnsattProsjekt ap = query.getSingleResult();
        ap.setArbeidstimer(arbeidstimer);
        
        em.getTransaction().begin();
        em.merge(ap);
        em.getTransaction().commit();
    }
}