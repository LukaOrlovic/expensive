(ns expensive.models.statement-model
  (:require [yesql.core :refer [defqueries]]
            [expensive.models.connection :refer [db-spec]]
            [compojure.core :refer :all]
            [expensive.layout :as layout]
            [ring.util.response :as response]
            [clj-time.coerce :as c]
            [noir.cookies :as cookies]
            [clojure.data.json :as json]))

(defqueries "expensive/models/statement.sql"
            {:connection db-spec})


(defn store-data-into-cookies
  []
  (doall (cookies/put! "statement" (json/write-str (get-all)))))

;(for [x (get-all)] (cookies/put! (str "statement" {:statementid x}) x))

;(doall (cookies/put! "statement" (get-all)))

;(doseq [statement (get-all)]
;  (println statement)
;  (cookies/put! "statement" statement))

;Logic for getting all statements from the db
(defn statement-get-all
  []
  (store-data-into-cookies)
  (layout/render "plot.html"))