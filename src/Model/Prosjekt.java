package Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(schema = "oblig3")
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prosjekt_id;

    private String navn;
    private String beskrivelse;

    @OneToMany(mappedBy = "prosjekt")
    private List<AnsattProsjekt> deltagere;

    public Prosjekt() {}

    public Prosjekt(String navn, String beskrivelse) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    public int getProsjektId() {
        return prosjekt_id;
    }

    public String getNavn() {
        return navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public List<AnsattProsjekt> getDeltagere() {
        return deltagere;
    }

    public void skrivUt() {
        System.out.printf("Prosjekt: %s (ID: %d)%n", navn, prosjekt_id);
        System.out.println("Beskrivelse: " + beskrivelse);
        int totalTimer = 0;
        if (deltagere != null && !deltagere.isEmpty()) {
            System.out.println("Deltagere:");
            for (AnsattProsjekt ap : deltagere) {
                System.out.printf("  - %s %s, Rolle: %s, Timer: %d%n", ap.getAnsatt().getFornavn(), ap.getAnsatt().getEtternavn(), ap.getRolle(), ap.getArbeidstimer());
                totalTimer += ap.getArbeidstimer();
            }
        }
        System.out.printf("Totalt timetall for prosjektet: %d%n", totalTimer);
    }
}