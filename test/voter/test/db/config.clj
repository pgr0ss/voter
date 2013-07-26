(ns voter.test.db.config
  (:use clojure.test)
  (:require [voter.db.config :as config]))

(deftest db-url->jdbc-url
  (is (= "jdbc:postgresql://localhost/voter" (config/db-url->jdbc-url "postgres://localhost/voter")))
  (is (= "jdbc:postgresql://localhost/voter" (config/db-url->jdbc-url "jdbc:postgres://localhost/voter"))))

(deftest db-url->korma-map
  (is (= {:user nil :password nil :host "localhost" :port "5432" :db "voter"}
         (config/db-url->korma-map "postgresql://localhost/voter")))
  (is (= {:user "myuser" :password "mypass" :host "my-host" :port "5432" :db "mydb"}
         (config/db-url->korma-map "postgres://myuser:mypass@my-host:5432/mydb"))))
