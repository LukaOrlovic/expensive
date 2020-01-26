(ns expensive.routes.access
  (:require [expensive.models.user-model :refer [is-authed?]]))

(def rules
  [{:redirect "/login" :rule is-authed?}])
