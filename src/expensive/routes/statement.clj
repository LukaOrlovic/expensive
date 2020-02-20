(ns expensive.routes.statement
  (:require [compojure.core :refer :all]
            [expensive.layout :as layout]
            [expensive.models.statement-model :as statement]
            [noir.util.route :refer [restricted]]))

(defroutes statement-routes
           (GET "/statement/all-statements" [] (restricted (statement/statement-get-all)))
           (GET "/statement/view-statements" [] (restricted (statement/choose-statement-dates)))
           (POST "/statement/view-statements" [& form] (statement/view-chosen-statements form)))
