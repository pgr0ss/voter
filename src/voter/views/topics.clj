(ns voter.views.topics
  (:require [voter.models.topic :as topic]
            [voter.views.layout :as layout])
  (:use hiccup.core)
  (:use hiccup.element)
  (:use hiccup.form))

(defn- user [email]
  (if email
    [:h4 (str "Logged in as: " email)
     [:a.btn {:href "/logout"} "Logout"]]))

(defn index [email]
  (layout/layout
    (user email)
     [:h2 "List of Topics"]
     [:ul
      (for [topic (topic/all)]
        (let [{:keys [id text votes]} topic]
          [:li (format "%s (votes: %s)" text votes)
           (form-to [:post (format "/topics/%s/vote" id)]
                    [:button.btn {:type "submit"} "Vote"])]))]
     (form-to [:post "/topics"]
      [:fieldset
       [:legend "Enter A New Topic:"]
       (label "topic" "Topic:")
       (text-field "topic")
       [:button.btn {:type "submit"} "Add"]])
     (form-to [:delete "/topics"]
       [:fieldset
        [:button.btn {:type "submit"} "Delete All Topics"]])
    (form-to [:delete "/topics/votes"]
       [:fieldset
        [:button.btn {:type "submit"} "Reset Votes"]])))
