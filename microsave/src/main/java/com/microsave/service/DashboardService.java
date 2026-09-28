package com.microsave.service;

import com.microsave.entity.Contribution;
import com.microsave.entity.Loan;
import com.microsave.repository.ContributionRepository;
import com.microsave.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final ContributionRepository contributionRepository;
    private final LoanRepository loanRepository;

    public DashboardService(
            ContributionRepository contributionRepository,
            LoanRepository loanRepository) {

        this.contributionRepository = contributionRepository;
        this.loanRepository = loanRepository;
    }

    public Map<String, BigDecimal> getSummary() {

        List<Contribution> contributions =
                contributionRepository.findAll();

        List<Loan> loans =
                loanRepository.findAll();

        BigDecimal totalContributions = contributions.stream()
                .map(Contribution::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalOutstandingLoans = loans.stream()
                .map(Loan::getOutstandingAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal availablePool =
                totalContributions.subtract(totalOutstandingLoans);

        Map<String, BigDecimal> summary = new HashMap<>();

        summary.put("totalContributions", totalContributions);
        summary.put("totalOutstandingLoans", totalOutstandingLoans);
        summary.put("availablePool", availablePool);

        return summary;
    }
}