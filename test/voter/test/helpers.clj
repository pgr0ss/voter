(ns voter.test.helpers
  (:require [voter.models.topic :as topic]
            [voter.db.config :as config]
            [korma.db :as db]))

(defn wrap-test-in-transaction [f]
  (db/transaction
    (f)
    (db/rollback)))

(defn clear-topics [f]
  (topic/delete-all!)
  (f))
