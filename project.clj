(defproject voter "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.3"]
                 [korma "0.3.0-RC5"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [postgresql "9.1-901.jdbc4"]
                 [ragtime/ragtime.core "0.3.3"]
                 [ragtime/ragtime.sql "0.3.3"]
                 [ragtime/ragtime.sql.files "0.3.3"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler voter.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}}
  :aliases {"migrate" ["run" "-m" "voter.db.migrate"]})
