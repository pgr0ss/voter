(ns voter.test.helpers
  (:require [voter.handler :as handler]
            [voter.models.category :as category]
            [voter.models.topic :as topic]
            [voter.db.config :as config]
            [korma.db :as db]))

(defn wrap-test-in-transaction [f]
  (db/transaction
    (f)
    (db/rollback)))

(defn clear-categories [f]
  (category/delete-all!)
  (f))

(defn clear-topics [f]
  (topic/delete-all!)
  (f))
