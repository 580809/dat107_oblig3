package Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(schema = "oblig3")
public class Avdeling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String navn;

    @OneToOne
    private Ansatt sjef;

    @OneToMany(mappedBy = "avdeling")
    private List<Ansatt> ansatte;

    public Avdeling() {}

    public Avdeling(String navn) {
        this.navn = navn;
    }

    // Getters og Setters
    
    public Ansatt getSjef() {
    	return sjef;
    }
    
    public void setSjef(Ansatt sjef) {
    	this.sjef = sjef;
    }
    
    public int getId() { return id; }
    
    public String getNavn() { return navn; }
    
    public void skrivUt() {
        // Skriver ut informasjon om avdelingen
        System.out.printf("Avdeling #%d: %s%n", id, navn);
        
        // Skriver ut informasjon om sjefen
        System.out.printf("Sjef: %s %s (ID: %d)%n", sjef.getFornavn(), sjef.getEtternavn(), sjef.getId());
        
        // Skriver ut informasjon om ansatte i avdelingen, med utheving av sjefen
        if (ansatte != null && !ansatte.isEmpty()) {
            System.out.println("Ansatte i avdelingen:");
            for (Ansatt ansatt : ansatte) {
                // Hvis ansatt er sjefen, uthever vi med et merke
                if (ansatt.getId() == sjef.getId()) {
                    System.out.printf("  - %s %s (Sjef) (ID: %d), Stilling: %s%n", ansatt.getFornavn(), ansatt.getEtternavn(), ansatt.getId(), ansatt.getStilling());
                } else {
                    System.out.printf("  - %s %s (ID: %d), Stilling: %s%n", ansatt.getFornavn(), ansatt.getEtternavn(), ansatt.getId(), ansatt.getStilling());
                }
            }
        } else {
            System.out.println("Ingen ansatte i avdelingen.");
        }
    }
}

