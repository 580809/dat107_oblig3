package Model;

import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3")
public class AnsattProsjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ansatt_id")
    private Ansatt ansatt;

    @ManyToOne
    @JoinColumn(name = "prosjekt_id")
    private Prosjekt prosjekt;

    private String rolle;
    private int arbeidstimer;

    public AnsattProsjekt() {}

    public AnsattProsjekt(Ansatt ansatt, Prosjekt prosjekt, String rolle, int arbeidstimer) {
        this.ansatt = ansatt;
        this.prosjekt = prosjekt;
        this.rolle = rolle;
        this.arbeidstimer = arbeidstimer;
    }

    public Ansatt getAnsatt() {
        return ansatt;
    }

    public Prosjekt getProsjekt() {
        return prosjekt;
    }

    public String getRolle() {
        return rolle;
    }

    public int getArbeidstimer() {
        return arbeidstimer;
    }

    public void setArbeidstimer(int arbeidstimer) {
        this.arbeidstimer = arbeidstimer;
    }
}