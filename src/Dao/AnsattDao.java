package Dao;

import Model.Ansatt;
import Model.Avdeling;

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
			return em.createQuery("FROM Ansatt a WHERE a.brukernavn = :bn", Ansatt.class).setParameter("bn", brukernavn)
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
	
	public void byttAvdeling(int ansattId, int nyAvdelingId) {
	    // Hent ansatt basert på ID
	    Ansatt ansatt = finnAnsattMedId(ansattId);

	    if (ansatt != null) {

	        Avdeling nåværendeAvdeling = ansatt.getAvdeling();

	        if (nåværendeAvdeling != null && nåværendeAvdeling.getSjef().getId() == ansatt.getId()) {
	            System.out.println("Kan ikke bytte avdeling. Ansatte er sjef i sin nåværende avdelingen.");
	            return;
	        }

	        Avdeling nyAvdeling = em.find(Avdeling.class, nyAvdelingId);

	        if (nyAvdeling != null) {
	            em.getTransaction().begin();
	            ansatt.setAvdeling(nyAvdeling);
	            em.getTransaction().commit();
	            System.out.println("Ansattens avdeling er oppdatert.");
	        } else {
	            System.out.println("Ny avdeling finnes ikke.");
	        }
	    } else {
	        System.out.println("Ansatt ikke funnet.");
	    }
	}
}
