CREATE TABLE receipt
(receiptId SERIAL NOT NULL PRIMARY KEY,
 amount NUMERIC,
 date TIMESTAMP,
 account_number INT,
 statementId INT);