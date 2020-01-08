--name: insert_receipt<!
INSERT INTO receipt (username, email, password)
VALUES (:username, :email, :password);

--name: get-all
SELECT *
FROM receipt
ORDER BY date ASC;
