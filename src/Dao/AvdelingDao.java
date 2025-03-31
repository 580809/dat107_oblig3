package Dao;

import Model.Ansatt;
import Model.Avdeling;

import jakarta.persistence.*;

public class AvdelingDao {
    private EntityManager em;

    public AvdelingDao() {
    }
    
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
    
    public void lagreNyAvdelingMedSjef(String navn, int sjefId) {

        Ansatt sjef = em.find(Ansatt.class, sjefId);

        if (sjef != null) {

            if (sjef.getAvdeling() != null && sjef.getAvdeling().getSjef().getId() == sjef.getId()) {
                System.out.println("Denne ansatte er allerede sjef i en avdeling og kan ikke tildeles en ny avdeling.");
                return;
            }

            Avdeling nyAvdeling = new Avdeling(navn);
            
            nyAvdeling.setSjef(sjef);

            em.getTransaction().begin();
            sjef.setAvdeling(nyAvdeling);
            em.persist(nyAvdeling);
            em.getTransaction().commit();
            
            System.out.println("Ny avdeling opprettet og sjef tildelt.");
        } else {
            System.out.println("Ansatt ikke funnet.");
        }
    }   
}

