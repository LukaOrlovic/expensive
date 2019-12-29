--name: insert_user<!
INSERT INTO users (username, email, password)
VALUES (:username, :email, :password);