(ns voter.views.topics
  (:use hiccup.core)
  (:use hiccup.element)
  (:use hiccup.form)
  (:require [voter.models.topic :as topic]))

(defn index []
  (html
    [:head
     [:meta {:charset "utf-8"}]
     [:title "Voter"]
     [:meta {:viewport "width=device-width, initial-scale=1.0"}]
     [:link {:href "/css/bootstrap.min.css" :rel "stylesheet"}]
     [:link {:href "/css/bootstrap-responsive.min.css" :rel "stylesheet"}]]
    [:body
     [:div.container
       [:h2 "List of Topics"]
       [:ul
        (for [topic (topic/all)]
          [:li topic])]
       (form-to [:post "/topics"]
        [:fieldset
         [:legend "Enter A New Topic:"]
         (label "topic" "Topic:")
         (text-field "topic")
         [:button.btn {:type "submit"} "Add"]])
       (form-to [:delete "/topics"]
         [:fieldset
          [:legend "Delete All Topics:"]
          [:button.btn {:type "submit"} "Delete All"]])]]))
