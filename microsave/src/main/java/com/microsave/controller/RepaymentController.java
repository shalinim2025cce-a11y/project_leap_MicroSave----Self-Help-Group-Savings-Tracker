package com.microsave.controller;

import com.microsave.entity.Repayment;
import com.microsave.service.RepaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repayments")
public class RepaymentController {

    private final RepaymentService repaymentService;

    public RepaymentController(RepaymentService repaymentService) {
        this.repaymentService = repaymentService;
    }

    @PostMapping
    public Repayment createRepayment(
            @Valid @RequestBody Repayment repayment) {
        return repaymentService.createRepayment(repayment);
    }

    @GetMapping
    public List<Repayment> getAllRepayments() {
        return repaymentService.getAllRepayments();
    }

    @GetMapping("/{id}")
    public Repayment getRepaymentById(@PathVariable Long id) {
        return repaymentService.getRepaymentById(id);
    }
}