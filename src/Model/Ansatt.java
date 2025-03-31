package Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "oblig3")
public class Ansatt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String brukernavn;

    private String fornavn;
    private String etternavn;
    private LocalDate ansettelsesdato;
    private String stilling;
    private double manedslonn;

    @ManyToOne
    private Avdeling avdeling;

    public Ansatt() {}

    public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelsesdato, String stilling, double manedslonn, Avdeling avdeling) {
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelsesdato = ansettelsesdato;
        this.stilling = stilling;
        this.manedslonn = manedslonn;
        this.avdeling = avdeling;
    }
    
    public void setManedslonn(double manedslonn) {
    	this.manedslonn = manedslonn;
    }
    
    public double getManedslonn() {
    	return manedslonn;
    }

    public void setStilling(String stilling) {
    	this.stilling = stilling;
    }
    
    public String getStilling() {
    	return this.stilling;
    }
    
    public int getId() { 
    	return id;
    }
    
    public String getBrukernavn() { 
    	return brukernavn; 
    }
    
    public void skrivUt() {
        System.out.printf("Ansatt #%d: %s %s (%s), Stilling: %s, Lønn: %.2f%n", id, fornavn, etternavn, brukernavn, stilling, manedslonn);
    }
}

