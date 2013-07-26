(ns voter.test.db.config
  (:use clojure.test)
  (:require [voter.db.config :as config]))

(deftest db-url->map
  (is (= {:user nil :password nil :host "localhost" :port "5432" :db "voter"}
         (config/db-url->map "postgresql://localhost/voter")))
  (is (= {:user "myuser" :password "mypass" :host "my-host" :port "5432" :db "mydb"}
         (config/db-url->map "postgres://myuser:mypass@my-host:5432/mydb"))))
