package com.faraz.interview.repository;

import com.faraz.interview.entity.ETransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<ETransaction , Long> {
}
