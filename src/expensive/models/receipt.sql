--name: insert_receipt<!
INSERT INTO receipt (amount, date, account_number)
VALUES (:amount, :date, :account_number);

--name: get-all
SELECT *
FROM receipt
ORDER BY date ASC;

--name: delete!
DELETE FROM receipt
WHERE receiptid = :receiptid;

--name: update!
UPDATE receipt
SET amount = :amount, date = :date, account_number = :account_number
WHERE receiptid = :receiptid;

--name: get-receipt
SELECT *
FROM receipt
WHERE receiptid = :receiptid;
