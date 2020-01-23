--name: insert_receipt<!
INSERT INTO receipt (amount, date, account_number)
VALUES (:amount, :date, :account_number);

--name: get-all
SELECT *
FROM receipt
ORDER BY date ASC;

--name: delete!
DELETE FROM receipt WHERE receiptid = :receiptid;
