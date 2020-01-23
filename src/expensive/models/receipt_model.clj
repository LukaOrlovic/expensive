(ns expensive.models.receipt-model
  (:require [yesql.core :refer [defqueries]]
            [expensive.models.connection :refer [db-spec]]
            [compojure.core :refer :all]
            [expensive.layout :as layout]
            [ring.util.response :as response]
            [clj-time.coerce :as c]
            [expensive.validators.user-validator :as v]
            [expensive.validators.receipt-validator :as r]))

(defqueries "expensive/models/receipt.sql"
            {:connection db-spec})

(defn receipts-get-all
  []
  (layout/render "receipts/get-all.html" {:receipts (get-all)}))

(defn render-insert-receipt-form
  []
  (layout/render "receipts/insert-receipt.html"))

(defn prepare-receipt-data-for-db
  [receipt]
  (-> receipt
       (assoc :amount (Double/parseDouble (receipt :amount)))
       (assoc :date (c/to-sql-date (receipt :date)))
       (assoc :account_number (Double/parseDouble (receipt :account_number)))))

(defn insert-receipt
  [receipt]
  (let [errors (r/validate-receipt receipt)]
    (if (empty? errors)
      (do
        (insert_receipt<! (prepare-receipt-data-for-db receipt))
        (response/redirect "/receipt/get-all"))
      (layout/render "signup.html" (assoc receipt :errors errors)))))

(defn show-id
  [receiptid]
  (println receiptid)
  (layout/render "about.html"))

(defn delete
  [receiptid]
  (delete! {:receiptid (Double/parseDouble receiptid)})
  (receipts-get-all))