package com.dataexport.consumer.model;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEBIT("debit"),
    CREDIT("credit"),
    ALL("all");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}

