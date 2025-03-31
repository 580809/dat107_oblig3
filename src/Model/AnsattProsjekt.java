package Model;

import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3", name = "AnsattProsjekt")
public class AnsattProsjekt {
	
    @EmbeddedId
    private AnsattProsjektId id;
    
    @ManyToOne
    @JoinColumn(name = "ansattProsjekt_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Ansatt ansatt;

    @ManyToOne
    @JoinColumn(name = "prosjekt_id", referencedColumnName = "prosjekt_id", insertable = false, updatable = false)
    private Prosjekt prosjekt;

    @Column(name = "rolle")
    private String rolle;

    @Column(name = "arbeidstimer")
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