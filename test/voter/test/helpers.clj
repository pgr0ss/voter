(ns voter.test.helpers
  (:require [voter.models.topic :as topic]))

(defn clear-topics [f]
  (topic/delete-all!)
  (topic/reset-id!)
  (f))
