(ns voter.views.topics
  (:use hiccup.core))

(defn index []
  (html
    [:body
     [:p "List of Topics"]]))
