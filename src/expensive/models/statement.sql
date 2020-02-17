--name: get-all
SELECT *
FROM statement
ORDER BY month ASC;

--name: get-receipts-statement
SELECT statementid
FROM statement
WHERE month = :month AND year = :year;

--name: insert-statement!
INSERT INTO statement (month, year, user_id, amount)
VALUES (:month, :year, :user_id, :amount);
