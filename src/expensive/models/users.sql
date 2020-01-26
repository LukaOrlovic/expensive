--name: insert_user<!
INSERT INTO users (username, email, password)
VALUES (:username, :email, :password);

--name: get-user-by-username
SELECT *
FROM users
WHERE username = :username;