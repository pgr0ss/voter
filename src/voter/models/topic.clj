(ns voter.models.topic
  (:require [voter.db.config :as config])
  (:use korma.core))

(config/setup-korma)

(defentity topics)

(defn all []
  (select topics (order :id)))

(defn create! [text]
  (insert topics (values {:text text})))

(defn delete-all! []
  (delete topics))

(defn reset-votes! []
  (update topics (set-fields {:votes 0})))

(defn vote! [topic-id]
  (update topics
    (set-fields {:votes (raw "votes + 1")})
    (where {:id topic-id})))
