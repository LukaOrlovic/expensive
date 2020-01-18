(ns expensive.routes.receipt
  (:require [compojure.core :refer :all]
            [expensive.layout :as layout]
            [expensive.models.receipt-model :as receipt]))

(defroutes receipt-routes
           (GET "/receipt/get-all" [] (receipt/receipts-get-all))
           (GET "/receipt/insert-receipt" [] (receipt/render-insert-receipt-form))
           (POST "/receipt/insert-receipt" [& form] (receipt/insert-receipt form)))
