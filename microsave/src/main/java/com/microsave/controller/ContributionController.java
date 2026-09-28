package com.microsave.controller;

import com.microsave.entity.Contribution;
import com.microsave.service.ContributionService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/contributions")
public class ContributionController {

    private final ContributionService contributionService;

    public ContributionController(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @PostMapping
    public Contribution createContribution(
            @Valid @RequestBody Contribution contribution) {
        return contributionService.createContribution(contribution);
    }

    @GetMapping
    public List<Contribution> getAllContributions() {
        return contributionService.getAllContributions();
    }

    @GetMapping("/{id}")
    public Contribution getContributionById(@PathVariable Long id) {
        return contributionService.getContributionById(id);
    }
}