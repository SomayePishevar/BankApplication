package com.faraz.interview.repository;

import com.faraz.interview.entity.ECustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<ECustomer, Long> {
}
