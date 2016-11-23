package ru.homework.loanmaster.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "BLOCKED_PERSONAL_ID",
        uniqueConstraints = @UniqueConstraint(name = "BPI_PERSONAL_ID_UNQ", columnNames = "PERSONAL_ID"))
public class BlockedPersonalId implements Serializable {

    private String personalId;

    @Id
    @Column(name = "PERSONAL_ID")
    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }
}
