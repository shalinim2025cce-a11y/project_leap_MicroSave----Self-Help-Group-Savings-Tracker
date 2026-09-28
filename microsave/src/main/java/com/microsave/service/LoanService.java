package com.microsave.service;

import com.microsave.entity.Loan;
import com.microsave.entity.LoanStatus;
import com.microsave.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(Loan loan) {

        if (loanRepository.findByMemberIdAndStatus(
                loan.getMember().getId(),
                LoanStatus.ACTIVE
        ).isPresent()) {

            throw new RuntimeException(
                    "Member already has an active loan"
            );
        }

        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
    }
}