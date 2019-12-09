(ns expensive.test.handler
  (:use clojure.test
        ring.mock.request
        expensive.handler))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= 404 (:status response))))))

(deftest test-missing-email-request
  (let [response (app (request :post "/signup" {:username "Username" :password "Password"}))]
    (is (= 200 (:status response)))))
