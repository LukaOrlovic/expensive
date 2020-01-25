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

;Logic for getting all receipts from the db
(defn receipts-get-all
  []
  (layout/render "receipts/get-all.html" {:receipts (get-all)}))

;Logic for inserting receipt into the db
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

;Logic for deleting a receipt from the db
(defn delete
  [receiptid]
  (delete! {:receiptid (Double/parseDouble receiptid)})
  (receipts-get-all))

;Logic for getting a specific receipt from the db
(defn get-receipt-by-id
  [receiptid]
  (get-receipt {:receiptid (Double/parseDouble receiptid)}))

;Logic for updating receipt in the db
(defn render-update-receipt-form
  [receiptid]
  (layout/render "receipts/update-receipt.html" { :r (get-receipt-by-id receiptid) }))

(defn update
  [receipt]
  (let [errors (r/validate-receipt receipt)]
    (if (empty? errors)
      (do
        (update! { :receiptid (Integer/parseInt (receipt :receiptid))
                   :amount (Double/parseDouble (receipt :amount))
                   :date (c/to-sql-date (receipt :date))
                   :account_number (Double/parseDouble (receipt :account_number))})
        (receipts-get-all))
      (layout/render "receipts/update-receipt.html" (assoc receipt :errors errors)))))