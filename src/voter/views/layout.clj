(ns voter.views.layout
  (:use hiccup.core))

(defn layout [& content]
  (html
    [:html
     [:head
      [:meta {:charset "utf-8"}]
      [:title "Voter"]
      [:meta {:viewport "width=device-width, initial-scale=1.0"}]
      [:link {:href "/css/bootstrap.min.css" :rel "stylesheet"}]
      [:link {:href "/css/bootstrap-responsive.min.css" :rel "stylesheet"}]]
     [:body
      [:div.container content]]]))
