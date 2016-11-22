package ru.homework.loanmaster.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.homework.loanmaster.model.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    @Query("from Loan l where l.personalId = :personalId")
    List<Loan> getLoanByPersonalId(@Param("personalId") String personalId);

    @Query("from Loan l")
    List<Loan> getLoans();


}
