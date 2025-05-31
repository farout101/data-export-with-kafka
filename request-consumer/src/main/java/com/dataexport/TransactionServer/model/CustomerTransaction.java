package com.dataexport.TransactionServer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer_transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
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