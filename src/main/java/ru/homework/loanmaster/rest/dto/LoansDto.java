package ru.homework.loanmaster.rest.dto;

import ru.homework.loanmaster.validation.ErrorCode;

import java.util.List;

public class LoansDto extends ResultDto {
    private List<LoanDto> loans;

    public LoansDto(List<LoanDto> loans) {
        super(ErrorCode.SUCCESS);
        this.loans = loans;
    }

    public List<LoanDto> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanDto> loans) {
        this.loans = loans;
    }
}
