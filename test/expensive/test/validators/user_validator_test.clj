(ns expensive.test.validators.user_validator_test
  (:require [clojure.test :refer :all]
            [expensive.validators.user-validator :as uv]))

(defn validate-email
  [email]
  "Validate provided email"
  (:email (uv/email-validator {:email email})))

(defn validate-password
  [password]
  "Validate provided password"
  (:password (uv/password-validator {:password password})))

(defn validate-username
  [username]
  "Validate provided password"
  (:username (uv/username-validator {:username username})))

(deftest blank-email
  (let [result (validate-email "")]
    (is (= 1 (count result)))
    (is (= "is a required field." (first result)))))

(deftest not-an-email
  (let [result (validate-email "Something that is not an email")]
    (is (= 1 (count result)))
    (is (= "The email's format is incorrect" (first result)))))

(deftest contains-at-symbol
  (let [result (validate-email "contains @symbol.com")]
    (is (= 1 (count result)))
    (is (= "The email's format is incorrect" (first result)))))

(deftest blank-password
  (let [result (validate-password "")]
    (is (= 1 (count result)))
    (is (= "is a required field." (first result)))))

(deftest too-long-password
  (let [result (validate-password "123456789123456789")]
    (is (= 1 (count result)))
    (is (= "Password must be between 8 and 15 characters long." (first result)))))

(deftest short-password
  (let [result (validate-password "1234567")]
    (is (= 1 (count result)))
    (is (= "Password must be between 8 and 15 characters long." (first result)))))

(deftest blank-username
  (let [result (validate-username "")]
    (is (= 1 (count result)))
    (is (= "is a required field." (first result)))))

(deftest username-contains-dots
  (let [result (validate-username "...")]
    (is (= 1 (count result)))
    (is (= "Only letters, numbers and underscore." (first result)))))

(deftest username-contains-exclamation-point
  (let [result (validate-username "!")]
    (is (= 1 (count result)))
    (is (= "Only letters, numbers and underscore." (first result)))))