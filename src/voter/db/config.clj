(ns voter.db.config
  (:use korma.db)
  (:require [clojure.string :as string]))

(def db-connection-string (or (System/getenv "DATABASE_URL")
                              "postgres://localhost/voter"))

(defn db-url->map [url]
  (let [uri (java.net.URI. url)
        db (string/replace-first (.getPath uri) #"/" "")
        raw-port (.getPort uri)
        port (if (< raw-port 0)
               "5432"
               (str raw-port))
        user-info (.getUserInfo uri)
        [user password] (if user-info
                          (string/split user-info #":")
                          [nil nil])]
    {:user user
     :password password
     :host (.getHost uri)
     :port port
     :db db}))

(defn setup-korma []
  (defdb korma-db (postgres (db-url->map db-connection-string))))
