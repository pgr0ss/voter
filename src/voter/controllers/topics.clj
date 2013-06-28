(ns voter.controllers.topics
  (:require [ring.util.response :as response]
            [voter.models.topic :as topic]
            [voter.views.topics :as views]))

(defn index [request]
  (views/index))

(defn create [request]
  (let [topic-text (-> request :params :topic)]
    (topic/create! topic-text))
  (response/redirect "/"))

(defn delete [request]
  (topic/delete-all!)
  (response/redirect "/"))
