(ns expensive.models.statement-model
  (:require [yesql.core :refer [defqueries]]
            [expensive.models.connection :refer [db-spec]]
            [compojure.core :refer :all]
            [expensive.layout :as layout]
            [ring.util.response :as response]
            [clj-time.core :as c]
            [noir.cookies :as cookies]
            [clojure.data.json :as json]
            [clj-time.coerce :as coerce]
            [noir.session :as session]))

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

(defn get-receipts-statementid-from-db
  [receipt]
  (if (empty? (get-receipts-statement {
                                       :year (c/year (coerce/to-local-date (receipt :date)))
                                       :month (c/month (coerce/to-local-date (receipt :date)))
                                       }))
    (insert-statement! {
                        :month (c/month (coerce/to-local-date (receipt :date)))
                        :year (c/year (coerce/to-local-date (receipt :date)))
                        :user_id (session/get :user_id)
                        :amount (Double/parseDouble (receipt :amount))
                        }))
  ((first (get-receipts-statement {
                                   :year (c/year (coerce/to-local-date (receipt :date)))
                                   :month (c/month (coerce/to-local-date (receipt :date)))
                                   })) :statementid))