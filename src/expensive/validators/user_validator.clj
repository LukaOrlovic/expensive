(ns expensive.validators.user-validator
  (:require [validateur.validation :refer :all]
            [noir.validation :as v]))

(def email-validator
  (validation-set
    (validate-with-predicate :email
                             #(v/is-email? (:email %))
                             :message-fn
                             (fn [validation-map]
                               (if (v/has-value? (:email validation-map))
                                 "The email's format is incorrect"
                                 "is a required field.")))))

(def username-validator
  (validation-set
    (format-of :username
               :format #"^[a-zA-Z0-9_]*$"
               :message "Only letters, numbers and underscore."
               :blank-message "is a required field.")))

(def password-validator
  (validation-set
    (length-of :password
               :within (range 8 16)
               :blank-message "is a required field."
               :message-fn
               (fn [type m attribute & args]
                 (if (= type :blank)
                   "is a required field."
                   "Password must be between 8 and 15 characters long.")))))

(defn validate-signup
  [signup]
  "Validate map of signup data and expects it to have :username, :email and :password"
  ((compose-sets email-validator username-validator password-validator) signup))
