(ns voter.controllers.topics
  (:require [cemerick.friend :as friend]
            [ring.util.response :as response]
            [voter.models.topic :as topic]
            [voter.views.topics :as views]))

(defn index [request]
  (let [auth (friend/current-authentication request)
        email (:email auth)]
  (views/index email)))

(defn create [request]
  (let [topic-text (-> request :params :topic)]
    (topic/create! topic-text))
  (response/redirect "/topics"))

(defn delete [request]
  (topic/delete-all!)
  (response/redirect "/topics"))

(defn reset-votes [request]
  (topic/reset-votes!)
  (response/redirect "/topics"))

(defn vote [request]
  (let [id (read-string (-> request :params :id))]
    (topic/vote! id))
  (response/redirect "/topics"))
