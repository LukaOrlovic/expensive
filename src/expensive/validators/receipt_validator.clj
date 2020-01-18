(ns expensive.validators.receipt-validator
  (:require [validateur.validation :refer :all]
            [noir.validation :as v]))

(def amount-validator
  (validation-set
    (validate-with-predicate :amount
                             #(v/valid-number? (:amount %))
                             :message-fn
                             (fn [validation-map]
                               (if (v/has-value? (:amount validation-map))
                                 "The amount is incorrect"
                                 "is a required field.")))))

(defn validate-receipt
  [receipt]
  "Validate receipt input field"
  ((compose-sets amount-validator) receipt))
