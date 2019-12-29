(ns expensive.routes.home
  (:require [compojure.core :refer :all]
            [expensive.layout :as layout]
            [expensive.util :as util]
            [ring.util.response :as response]
            [expensive.validators.user-validator :as v]
            [expensive.models.user-model :as user_model]))

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

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/signup" [] (signup-page))
  (POST "/signup" [& form] (signup-page-submit form))
  (GET "/signup-success" [] "Success!"))
