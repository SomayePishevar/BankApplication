package com.faraz.interview.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ECustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;
}
