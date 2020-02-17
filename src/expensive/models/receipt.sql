--name: insert_receipt<!
INSERT INTO receipt (amount, date, user_id, statementid)
VALUES (:amount, :date, :user_id, :statementid);

--name: get-all
SELECT *
FROM receipt
ORDER BY date ASC;

--name: delete!
DELETE FROM receipt
WHERE receiptid = :receiptid;

--name: update!
UPDATE receipt
SET amount = :amount, date = :date, user_id = :user_id
WHERE receiptid = :receiptid;

--name: get-receipt
SELECT *
FROM receipt
WHERE receiptid = :receiptid;
