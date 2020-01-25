(ns expensive.routes.home
  (:require [compojure.core :refer :all]
            [expensive.layout :as layout]
            [expensive.util :as util]
            [ring.util.response :as response]
            [expensive.validators.user-validator :as v]
            [expensive.models.user-model :as user_model]
            [expensive.models.receipt-model :as receipt]
            [noir.cookies :as cookies]))

(defn home-page
  []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn signup-page
  []
  (layout/render "signup.html"))

(defn signup-page-submit
  [user]
  (let [errors (v/validate-signup user)]
      (if (empty? errors)
        (do
          (user_model/add-user! user)
          (response/redirect "/signup-success"))
        (layout/render "signup.html" (assoc user :errors errors)))))

(defn about-page
  []
  (layout/render "about.html"))

(defn plot-something
  []
  (cookies/put! "January" 100000)
  (cookies/put! "February" 250000)
  (cookies/put! "March" 88000)
  (cookies/put! "April" 92300)
  (cookies/put! "May" 158000)
  (layout/render "plot.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/signup" [] (signup-page))
  (POST "/signup" [& form] (signup-page-submit form))
  (GET "/signup-success" [] "Success!")
  (GET "/plot" [] (plot-something)))
