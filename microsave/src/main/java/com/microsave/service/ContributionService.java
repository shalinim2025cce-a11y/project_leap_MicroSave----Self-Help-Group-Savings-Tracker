package com.microsave.service;

import com.microsave.entity.Contribution;
import com.microsave.repository.ContributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionService {

    private final ContributionRepository contributionRepository;

    public ContributionService(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    public Contribution createContribution(Contribution contribution) {
        return contributionRepository.save(contribution);
    }

    public List<Contribution> getAllContributions() {
        return contributionRepository.findAll();
    }

    public Contribution getContributionById(Long id) {
        return contributionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contribution not found"));
    }
}