package com.dataexport.consumer.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "interbank_transfers")
@Getter
@Setter
public class InterbankTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    private String senderBankCode;

    private String receiverBankCode;

    private String senderAccount;

    private String receiverAccount;

    private Double amount;

    private String currency;

    private String transferReason;

    private LocalDateTime transactionTime;

    private String status;
}
