(ns voter.db.migrate
  (:require [ragtime.core]
            [ragtime.sql.database]
            [ragtime.sql.files]
            [voter.db.config :as config]))

(defn -main []
  (ragtime.core/migrate-all
       (ragtime.core/connection config/db-connection-string)
       (ragtime.sql.files/migrations)))
