(ns voter.db.migrate
  (:require [clojure.string :as string]
            [ragtime.core]
            [ragtime.sql.database]
            [ragtime.sql.files]
            [voter.db.config :as config]))

(defn -main []
  (ragtime.core/migrate-all
    (ragtime.core/connection (config/db-url->jdbc-url config/db-connection-string))
    (ragtime.sql.files/migrations)))
