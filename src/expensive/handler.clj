(ns expensive.handler
  (:require [compojure.core :refer [defroutes]]
            [expensive.routes.home :refer [home-routes]]
            [expensive.middleware :refer [load-middleware]]
            [expensive.session-manager :as session-manager]
            [noir.response :refer [redirect]]
            [noir.util.middleware :refer [app-handler]]
            [ring.middleware.defaults :refer [site-defaults]]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]
            [taoensso.timbre.appenders.rotor :as rotor]
            [selmer.parser :as parser]
            [environ.core :refer [env]]
            [cronj.core :as cronj]
            [migratus.core :as migratus]
            [expensive.routes.receipt :refer [receipt-routes]]
            [expensive.routes.access :as access]
            [expensive.routes.statement :refer [statement-routes]]))

(defroutes base-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def migratus-config
  {:store         :database
   :migration-dir "migrations"
   :migration-table-name "_migrations"
   :db            {:classname "org.postgresql.Driver"
                   :subprotocol "postgresql"
                   :subname "//localhost/postgres"
                   :user "expensive"
                   :password "expensive"}})

(defn migrate-db []
  (timbre/info "checking migrations")
  (try
    (migratus/migrate migratus-config)
    (catch Exception e
      (timbre/error "Failed to migrate" e)))
  (timbre/info "finished migrations"))

(defn init
  "init will be called once when
   app is deployed as a servlet on
   an app server such as Tomcat
   put any initialization code here"
  []
  (timbre/set-config!
    [:appenders :rotor]
    {:min-level :info
     :enabled? true
     :async? false ; should be always false for rotor
     :max-message-per-msecs nil
     :fn rotor/appender-fn})

  (timbre/set-config!
    [:shared-appender-config :rotor]
    {:path "expensive.log" :max-size (* 512 1024) :backlog 10})

  (if (env :dev) (parser/cache-off!))
  ;;start the expired session cleanup job
  (migrate-db)
  (cronj/start! session-manager/cleanup-job)
  (timbre/info "\n-=[ expensive started successfully"
               (when (env :dev) "using the development profile") "]=-"))

(defn destroy
  "destroy will be called when your application
   shuts down, put any clean up code here"
  []
  (timbre/info "expensive is shutting down...")
  (cronj/shutdown! session-manager/cleanup-job)
  (timbre/info "shutdown complete!"))

;; timeout sessions after 30 minutes
(def session-defaults
  {:timeout (* 60 30)
   :timeout-response (redirect "/")})

(defn- mk-defaults
       "set to true to enable XSS protection"
       [xss-protection?]
       (-> site-defaults
           (update-in [:session] merge session-defaults)
           (assoc-in [:security :anti-forgery] xss-protection?)))

(def app (app-handler
           ;; add your application routes here
           [home-routes receipt-routes statement-routes base-routes]
           ;; add custom middleware here
           :middleware (load-middleware)
           :ring-defaults (mk-defaults false)
           ;; add access rules here
           :access-rules access/rules
           ;; serialize/deserialize the following data formats
           ;; available formats:
           ;; :json :json-kw :yaml :yaml-kw :edn :yaml-in-html
           :formats [:json-kw :edn :transit-json]))
