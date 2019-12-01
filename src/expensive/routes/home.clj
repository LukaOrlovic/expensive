(ns expensive.routes.home
  (:require [compojure.core :refer :all]
            [expensive.layout :as layout]
            [expensive.util :as util]))

(defn home-page
  []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn signup-page
  []
  (layout/render "signup.html"))

(defn about-page
  []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/signup" [] (signup-page)))
