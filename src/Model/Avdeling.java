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

    public int getId() { return id; }
    public String getNavn() { return navn; }
}

