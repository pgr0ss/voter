(ns voter.views.topics
  (:use hiccup.core)
  (:use hiccup.form)
  (:require [voter.models.topic :as topic]))

(defn index []
  (html
    [:body
     [:p "List of Topics"]
     [:ul
      (for [topic (topic/all)]
        [:li topic])]
     [:p "Enter New Topic:"]
     (form-to [:post "/topics"]
              (label "topic" "Topic:")
              (text-field "topic")
              (submit-button "Add"))]))
