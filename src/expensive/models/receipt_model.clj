(ns expensive.models.receipt-model
  (:require [yesql.core :refer [defqueries]]
            [expensive.models.connection :refer [db-spec]]))

(defqueries "expensive/models/receipt.sql"
            {:connection db-spec})