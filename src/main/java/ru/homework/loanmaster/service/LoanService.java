package ru.homework.loanmaster.service;

import com.google.common.base.Strings;
import org.joda.money.Money;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.homework.loanmaster.dao.LoanRepository;
import ru.homework.loanmaster.model.Loan;
import ru.homework.loanmaster.util.MoneyUtil;
import ru.homework.loanmaster.util.TermRate;
import ru.homework.loanmaster.util.TermUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoanService {
    @Resource
    private LoanRepository loanRepository;
    @Resource
    private CountryService countryService;

    public void applyLoan(Integer term, TermRate termRate, String amount, String currency,
                          String personalId, String name, String surname) {
        Loan loan = new Loan();
        loan.setTerm(TermUtil.getTermByRate(term, termRate));

        Money moneyAmount = MoneyUtil.toMoney(amount, currency);
        Assert.notNull(moneyAmount, "amount is empty");

        loan.setAmount(moneyAmount.getAmountMinorLong());
        loan.setCurrency(moneyAmount.getCurrencyUnit().getNumericCode());

        loan.setPersonalId(personalId);
        loan.setName(name);
        loan.setSurname(surname);

        loanRepository.save(loan);
    }

    public List<Loan> loanList(String personalId) {
        if (Strings.isNullOrEmpty(personalId)) {
            return loanRepository.getLoans();
        } else {
            return loanRepository.getLoanByPersonalId(personalId);
        }
    }
}
