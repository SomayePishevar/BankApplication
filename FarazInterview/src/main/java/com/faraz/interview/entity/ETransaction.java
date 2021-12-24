package com.faraz.interview.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EntityListeners(TransactionLoggingListener.class)
public class ETransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @Column(nullable = false)
    private String transactionDate;

    @Column(nullable = false)
    private String sourceCardNo;

    @Column(nullable = false)
    private String destinationCardNo;
}
