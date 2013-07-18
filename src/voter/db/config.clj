(ns voter.db.config
  (:use korma.db))

(def db-connection-string (or (System/getenv "DATABASE_URL")
                              "jdbc:postgresql://localhost/voter"))

(defn setup-korma []
  (when (nil? @korma.db/_default)
    (korma.db/default-connection db-connection-string)))

