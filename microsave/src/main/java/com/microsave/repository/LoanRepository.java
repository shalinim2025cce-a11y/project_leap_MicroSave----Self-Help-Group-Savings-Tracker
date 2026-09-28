package com.microsave.repository;

import com.microsave.entity.Loan;
import com.microsave.entity.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByMemberIdAndStatus(Long memberId, LoanStatus status);
}