(ns voter.db.migrate
  (:require [clojure.string :as string]
            [ragtime.core]
            [ragtime.sql.database]
            [ragtime.sql.files]
            [voter.db.config :as config]))

(defn -main []
  (let [db-url (str "jdbc:" (string/replace-first config/db-connection-string "postgres:" "postgresql:"))]
    (ragtime.core/migrate-all
      (ragtime.core/connection db-url)
      (ragtime.sql.files/migrations))))
