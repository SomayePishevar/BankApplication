package com.faraz.interview.repository;

import com.faraz.interview.entity.ECard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<ECard, Long> {
    ECard findByCardNo(String cardNo);
}
