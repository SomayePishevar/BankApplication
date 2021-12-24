package com.faraz.interview.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class ECard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(nullable = false)
    private String cardNo;

    @Column(nullable = false)
    private Date expireDate;

    @ManyToOne(optional = false)
    private ECustomer customer;

    public static boolean bankServiceProviderType(String cardNo){
        if (cardNo.startsWith("6037")){
            return true;
        }else
            return false;
    }
}
