(ns expensive.routes.statement
  (:require [compojure.core :refer :all]
            [expensive.layout :as layout]
            [expensive.models.statement-model :as statement]
            [noir.util.route :refer [restricted]]))

(defroutes statement-routes
           (GET "/statement/all-statements" [] (statement/statement-get-all)))
