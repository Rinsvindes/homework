package ru.homework.loanmaster.rest;

import org.springframework.web.bind.annotation.*;
import ru.homework.loanmaster.rest.dto.LoanDto;
import ru.homework.loanmaster.rest.dto.LoansDto;
import ru.homework.loanmaster.rest.dto.ResultDto;
import ru.homework.loanmaster.service.LoanService;
import ru.homework.loanmaster.util.IpUtil;
import ru.homework.loanmaster.util.TermRate;
import ru.homework.loanmaster.validation.ErrorCode;
import ru.homework.loanmaster.validation.ValidationException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
public class LoanController {

    @Resource
    private LoanService loanService;

    @ResponseBody
    @GetMapping(value = "/applyLoan.do")
    public ResultDto applyLoan(@RequestParam(name = "term", required = true) Integer term,
                                    @RequestParam(name = "termRate", required = false) TermRate termRate,
                                    @RequestParam(name = "amount", required = true) String amount,
                                    @RequestParam(name = "currency", required = false) String currency,
                                    @RequestParam(name = "personalId", required = true) String personalId,
                                    @RequestParam(name = "name", required = true) String name,
                                    @RequestParam(name = "surname") String surname,
                                    HttpServletRequest request){
        loanService.applyLoan(term, termRate, amount, currency, personalId,
                name, surname, IpUtil.getIpFromHttpRequest(request));
        return ResultDto.SUCCESS;
    }

    @ResponseBody
    @GetMapping(value = "/loanList.do")
    public LoansDto loanList(@RequestParam(name = "personalId", required = false) String personalId) {
        return new LoansDto(loanService.loanList(personalId).stream()
                .map(LoanDto::new).collect(Collectors.toList()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDto handleException(Exception ex) {
        return new ResultDto(ErrorCode.INTERNAL_ERROR, ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResultDto handleValidationException(ValidationException ex) {
        return new ResultDto(ex.getErrorCode(), ex.getErrorMessage());
    }


}
