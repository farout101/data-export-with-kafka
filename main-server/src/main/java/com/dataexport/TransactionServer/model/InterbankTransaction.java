package com.dataexport.TransactionServer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "interbank_transfers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterbankTransaction {

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
