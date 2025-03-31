package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AnsattProsjektId implements Serializable {
    @Column(name = "ansattProsjekt_id")
    private int ansattProsjektId;

    @Column(name = "prosjekt_id")
    private int prosjektId;

    // Standardkonstruktør, getters, setters, hashCode og equals

    public AnsattProsjektId() {}

    public AnsattProsjektId(int ansattProsjektId, int prosjektId) {
        this.ansattProsjektId = ansattProsjektId;
        this.prosjektId = prosjektId;
    }

    public int getAnsattProsjektId() {
        return ansattProsjektId;
    }

    public void setAnsattProsjektId(int ansattProsjektId) {
        this.ansattProsjektId = ansattProsjektId;
    }

    public int getProsjektId() {
        return prosjektId;
    }

    public void setProsjektId(int prosjektId) {
        this.prosjektId = prosjektId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnsattProsjektId that = (AnsattProsjektId) o;
        return ansattProsjektId == that.ansattProsjektId && prosjektId == that.prosjektId;
    }

    @Override
    public int hashCode() {
        return 31 * ansattProsjektId + prosjektId;
    }
}