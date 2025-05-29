package com.dataexport.consumer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "atm_withdrawals")
@Getter
@Setter
public class AtmWithdrawal {

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
