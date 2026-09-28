package com.microsave.service;

import com.microsave.entity.Loan;
import com.microsave.entity.LoanStatus;
import com.microsave.entity.Repayment;
import com.microsave.repository.LoanRepository;
import com.microsave.repository.RepaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RepaymentService {

    private final RepaymentRepository repaymentRepository;
    private final LoanRepository loanRepository;

    public RepaymentService(
            RepaymentRepository repaymentRepository,
            LoanRepository loanRepository) {

        this.repaymentRepository = repaymentRepository;
        this.loanRepository = loanRepository;
    }

    public Repayment createRepayment(Repayment repayment) {

        Loan loan = loanRepository.findById(
                repayment.getLoan().getId()
        ).orElseThrow(() -> new RuntimeException("Loan not found"));

        BigDecimal newOutstanding =
                loan.getOutstandingAmount()
                        .subtract(repayment.getAmount());

        if (newOutstanding.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException(
                    "Repayment cannot be greater than outstanding amount"
            );
        }

        loan.setOutstandingAmount(newOutstanding);

        if (newOutstanding.compareTo(BigDecimal.ZERO) == 0) {
            loan.setStatus(LoanStatus.PAID);
        }

        loanRepository.save(loan);

        repayment.setLoan(loan);

        return repaymentRepository.save(repayment);
    }

    public List<Repayment> getAllRepayments() {
        return repaymentRepository.findAll();
    }

    public Repayment getRepaymentById(Long id) {
        return repaymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Repayment not found"));
    }
}