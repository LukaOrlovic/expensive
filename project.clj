(defproject expensive "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [lib-noir "0.9.4"]
                 [ring-server "0.3.1"]
                 [selmer "0.7.2"]
                 [com.taoensso/timbre "3.3.1"]
                 [com.taoensso/tower "3.0.2"]
                 [markdown-clj "0.9.55"
                  :exclusions [com.keminglabs/cljx]]
                 [environ "1.0.0"]
                 [im.chit/cronj "1.4.2"]
                 [noir-exception "0.2.2"]
                 [prone "0.6.0"]
                 [com.novemberain/validateur "2.5.0"]
                 [yesql "0.5.3"]
                 [crypto-password "0.2.0"]
                 [org.clojure/data.json "0.2.7"]]

  :repl-options {:init-ns expensive.repl}
  :jvm-opts ["-server"]
  :plugins [[lein-ring "0.12.5"]
            [lein-environ "1.0.0"]
            [lein-ancient "0.5.5"]
            [quickie "0.3.6"]
            [migratus-lein "0.1.0"]]
  :migratus {:store         :database
             :migration-dir "migrations"
             :migration-table-name "_migrations"
             :db            {:classname "org.postgresql.Driver"
                             :subprotocol "postgresql"
                             :subname "//localhost/postgres"
                             :user "expensive"
                             :password "expensive"}}
  :ring {:handler expensive.handler/app
         :init    expensive.handler/init
         :destroy expensive.handler/destroy}
  :profiles
  {:uberjar {:omit-source true
             :env {:production true}
             :aot :all}
   :production {:ring {:open-browser? false
                       :stacktraces?  false
                       :auto-reload?  false}}
   :dev {:dependencies [[ring-mock "0.1.5"]
                        [ring/ring-devel "1.3.1"]
                        [pjstadig/humane-test-output "0.6.0"]
                        [postgresql/postgresql "9.3-1102.jdbc41"]
                        [migratus "0.7.0"]]
         :injections [(require 'pjstadig.humane-test-output)
                      (pjstadig.humane-test-output/activate!)]
         :env {:dev true}}}
  :min-lein-version "2.0.0")