package ru.homework.loanmaster.rest.dto;

import ru.homework.loanmaster.model.Loan;
import ru.homework.loanmaster.util.MoneyUtil;
import ru.homework.loanmaster.util.TermUtil;

public class LoanDto {
    private Long id;
    private String term;
    private String amount;
    private String personalId;
    private String name;
    private String surname;
    private String countryCode;

    public LoanDto(Loan loan) {
        this.id = loan.getId();
        this.term = TermUtil.getTermBySeconds(loan.getTerm());
        this.amount = MoneyUtil.toFormattedAmountWithDot(MoneyUtil.toMoney(loan.getAmount(), loan.getCurrency()));
        this.personalId = loan.getPersonalId();
        this.name = loan.getName();
        this.surname = loan.getSurname();
        this.countryCode = loan.getCountryCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
