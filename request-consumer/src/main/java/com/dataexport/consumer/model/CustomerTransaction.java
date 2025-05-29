package com.dataexport.consumer.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer_transactions")
@Getter
@Setter
public class CustomerTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long customerId;

    private Long accountId;

    private String transactionType;

    private Double amount;

    private String currency;

    private LocalDateTime transactionTime;

    private String location;

    private String status;
}