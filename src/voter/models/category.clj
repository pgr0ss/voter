(ns voter.models.category
  (:require [voter.db.config :as config])
  (:use korma.core))

(config/setup-korma)

(defentity categories)

(defn all []
  (select categories (order :id)))

(defn create! [text]
  (insert categories (values {:name text})))

(defn delete-all! []
  (delete categories))
