package com.test.ClientServer.enu;

import lombok.Getter;

@Getter
public enum TransactionType {
    ATM("atm"),
    CUSTOMER("customer"),
    INTER_BANK("interbank");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
