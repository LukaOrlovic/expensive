(ns expensive.models.connection)

(def db-spec {:classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost/postgres"
              :user "expensive"
              :password "expensive"})
