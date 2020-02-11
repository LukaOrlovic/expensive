CREATE TABLE receipt
(receiptId SERIAL NOT NULL PRIMARY KEY,
 amount NUMERIC,
 date TIMESTAMP,
 user_id INT,
 statementId INT);