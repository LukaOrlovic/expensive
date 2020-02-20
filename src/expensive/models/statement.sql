--name: get-all
SELECT *
FROM statement
ORDER BY year ASC, month ASC;

--name: get-receipts-statement
SELECT statementid
FROM statement
WHERE month = :month AND year = :year;

--name: insert-statement!
INSERT INTO statement (month, year, user_id, amount)
VALUES (:month, :year, :user_id, :amount);

--name: get-selected-statements
SELECT *
FROM statement
WHERE (:starting_year * 365 + :starting_month * 30) < (year * 365 + month * 30)
AND (:ending_year * 365 + :ending_month * 30) > (year * 365 + month * 30)
AND user_id = :user_id
ORDER BY (year * 365 + month * 30) ASC;
