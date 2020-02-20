(ns expensive.models.user-model
  (:require [yesql.core :refer [defqueries]]
            [crypto.password.bcrypt :as password]
            [expensive.models.connection :refer [db-spec]]
            [noir.session :as session]
            [noir.session :as session]))

(defqueries "expensive/models/users.sql"
            {:connection db-spec})

(defn	add-user!
  "Saves	a	user	to	the	database."
  [user]
  (let	[new-user	(->>	(password/encrypt	(:password	user))
                         (assoc	user	:password)
                         insert_user<!)]
    (dissoc	new-user	:pass)))

(defn is-authed?
  "Returns false if the current request is anonymous; otherwise true."
  [_]
  (not (nil? (session/get :user_id))))

(defn auth-user
  [username password]
  (let [user (first (get-user-by-username {:username username}))]
    (when (password/check password (:password user))
      (session/put! :user_id (:user_id user))
      (dissoc user :password))))

(defn logout
  []
  (session/clear!))
