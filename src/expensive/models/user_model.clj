(ns expensive.models.user-model
  (:require [yesql.core :refer [defqueries]]
            [crypto.password.bcrypt :as password]))

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost/postgres"
              :user "expensive"
              :password "expensive"})

(defqueries "expensive/models/users.sql"
            {:connection db-spec})

(defn	add-user!
  "Saves	a	user	to	the	database."
  [user]
  (let	[new-user	(->>	(password/encrypt	(:password	user))
                         (assoc	user	:password)
                         insert_user<!)]
    (dissoc	new-user	:pass)))
