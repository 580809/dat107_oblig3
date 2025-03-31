package Main;

import Dao.*;
import Model.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("firmaPU");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		AnsattDao ansattDAO = new AnsattDao(em);
		AvdelingDao avdelingDAO = new AvdelingDao(em);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\nMeny:");
			System.out.println("1. Vis alle ansatte");
			System.out.println("2. Søk ansatt med ID");
			System.out.println("3. Søk ansatt med brukernavn");
			System.out.println("4. Oppdater stilling/lønn");
			System.out.println("5. Legg til ny ansatt");
			System.out.println("6. Søk avdeling med ID");
			System.out.println("7. Flytt ansatt til annen avdeling");
			System.out.println("8. Opprett ny avdeling");
			System.out.println("9. Avslutt");
			System.out.print("Velg: ");
			int valg = scanner.nextInt();
			scanner.nextLine();

			switch (valg) {
			case 1 -> {
				List<Ansatt> ansatte = ansattDAO.hentAlleAnsatte();
				ansatte.forEach(Ansatt::skrivUt);
			}
			case 2 -> {
				System.out.print("ID: ");
				int id = scanner.nextInt();
				Ansatt a = ansattDAO.finnAnsattMedId(id);
				if (a != null)
					a.skrivUt();
				else
					System.out.println("Ikke funnet.");
			}

			case 3 -> {
				System.out.print("Brukernavn: ");
				String bn = scanner.nextLine();
				Ansatt a = ansattDAO.finnAnsattMedBrukernavn(bn);
				if (a != null)
					a.skrivUt();
				else
					System.out.println("Ikke funnet.");
			}

			case 4 -> {
				System.out.print("Ansatt ID: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Ny stilling: ");
				String stilling = scanner.nextLine();
				System.out.print("Ny lønn: ");
				double lonn = scanner.nextDouble();
				ansattDAO.oppdaterStillingOgLonn(id, stilling, lonn);
			}
			case 5 -> {
				System.out.print("Brukernavn: ");
				String bn = scanner.nextLine();
				System.out.print("Fornavn: ");
				String fn = scanner.nextLine();
				System.out.print("Etternavn: ");
				String en = scanner.nextLine();
				System.out.print("Stilling: ");
				String stilling = scanner.nextLine();
				System.out.print("Lønn: ");
				double lonn = scanner.nextDouble();
				System.out.print("Avdeling ID: ");
				int avdId = scanner.nextInt();
				Avdeling avd = avdelingDAO.finnAvdelingMedId(avdId);
				if (avd != null) {
					Ansatt ny = new Ansatt(bn, fn, en, LocalDate.now(), stilling, lonn, avd);
					ansattDAO.lagreNyAnsatt(ny);
					System.out.println("Ny ansatt lagret.");
				} else {
					System.out.println("Avdeling ikke funnet.");
				}
			}

			case 6 -> {
				System.out.print("ID: ");
				int id = scanner.nextInt();
				Avdeling avd = avdelingDAO.finnAvdelingMedId(id);
				if (avd != null)
					avd.skrivUt();
				else
					System.out.println("Ikke funnet.");
			}

			case 7 -> {
				System.out.print("Ansatt ID: ");
				int id = scanner.nextInt();
				//scanner.nextLine();
				System.out.print("Ny avdeling ID: ");
				int avdId = scanner.nextInt();
				ansattDAO.byttAvdeling(id, avdId);
			}

			case 8 -> {
				System.out.print("Navn på ny avdeling: ");
				String avdNavn = scanner.nextLine();
				System.out.print("ID på ansatt som skal bli leder: ");
				int avdId = scanner.nextInt();
				avdelingDAO.lagreNyAvdelingMedSjef(avdNavn, avdId);
			}

			case 9 -> {
				em.close();
				emf.close();
				return;
			}
			}
		}
	}
}
