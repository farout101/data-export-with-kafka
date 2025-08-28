package com.dataexport.TransactionServer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "atm_withdrawals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtmTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atmTxId;
    private String atmId;
    private String cardNumberHash;
    private Double withdrawalAmount;
    private LocalDateTime transactionTime;
    private String atmLocation;
    private Double balanceRemaining;
    private String transactionStatus;
}
