package com.dataexport.consumer.config.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transaction {

    @Id
    Integer transaction_id;
}
