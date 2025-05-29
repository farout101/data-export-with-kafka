CREATE TABLE customer_transactions (
                                       transaction_id     BIGSERIAL PRIMARY KEY,
                                       customer_id        BIGINT NOT NULL,
                                       account_id         BIGINT NOT NULL,
                                       transaction_type   VARCHAR(20),
                                       amount             NUMERIC(15, 2),
                                       currency           VARCHAR(10),
                                       transaction_time   TIMESTAMP NOT NULL,
                                       location           VARCHAR(100),
                                       status             VARCHAR(20)
);
CREATE TABLE atm_withdrawals (
                                 atm_tx_id          BIGSERIAL PRIMARY KEY,
                                 atm_id             VARCHAR(50),
                                 card_number_hash   CHAR(64),
                                 withdrawal_amount  NUMERIC(10, 2),
                                 transaction_time   TIMESTAMP NOT NULL,
                                 atm_location       VARCHAR(255),
                                 balance_remaining  NUMERIC(15, 2),
                                 transaction_status VARCHAR(20)
);
CREATE TABLE interbank_transfers (
                                     transfer_id         BIGSERIAL PRIMARY KEY,
                                     sender_bank_code    VARCHAR(20),
                                     receiver_bank_code  VARCHAR(20),
                                     sender_account      VARCHAR(30),
                                     receiver_account    VARCHAR(30),
                                     amount              NUMERIC(18, 2),
                                     currency            VARCHAR(10),
                                     transfer_reason     VARCHAR(100),
                                     transaction_time    TIMESTAMP NOT NULL,
                                     status              VARCHAR(20)
);