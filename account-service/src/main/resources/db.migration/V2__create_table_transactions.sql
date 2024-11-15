CREATE TABLE IF NOT EXISTS transactions
(
    transactionId             INT PRIMARY KEY AUTO_INCREMENT,
    accountId                 INT            NOT NULL,
    amount                    NUMERIC(10, 2) NOT NULL DEFAULT 0,
    balance_after_transaction NUMERIC(10, 2) NOT NULL DEFAULT 0,
    type                      VARCHAR(100)   NOT NULL,
    method                    VARCHAR(100)   NOT NULL,
    created_at                TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    updated_at                TIMESTAMP               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

