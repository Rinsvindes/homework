package ru.homework.loanmaster.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LOAN")
public class Loan implements Serializable {
    private Long id;
    private Integer term;
    private Long amount;
    private Integer currency;
    private String personalId;
    private String name;
    private String surname;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loanSequence")
    @SequenceGenerator(name = "loanSequence", sequenceName = "SEQ_LOAN", allocationSize = 50)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TERM")
    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    @Column(name = "AMOUNT")
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Column(name = "CURRENCY")
    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    @Column(name = "PERSONAL_ID")
    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
