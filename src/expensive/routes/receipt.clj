(ns expensive.routes.receipt
  (:require [compojure.core :refer :all]
            [expensive.layout :as layout]
            [expensive.models.receipt-model :as receipt]
            [noir.util.route :refer [restricted]]))

(defroutes receipt-routes
           (GET "/receipt/all-receipts" [] (restricted (receipt/receipts-get-all)))
           (GET "/receipt/insert-receipt" [] (restricted (receipt/render-insert-receipt-form)))
           (POST "/receipt/insert-receipt" [& form] (receipt/insert-receipt form))
           (GET "/receipt/delete/:receiptid" [receiptid] (receipt/delete receiptid))
           (GET "/receipt/update/:receiptid" [receiptid] (receipt/render-update-receipt-form receiptid))
           (POST "/receipt/update/:receiptid" [& form] (receipt/update form)))
