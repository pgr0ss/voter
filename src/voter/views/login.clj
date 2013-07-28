(ns voter.views.login
  (:require [voter.views.layout :as layout])
  (:use hiccup.core)
  (:use hiccup.element)
  (:use hiccup.form))

(defn login-button []
  (form-to [:post "/login"]
           [:input {:type "hidden" :name "identifier" :value "https://www.google.com/accounts/o8/id"}]
           [:button.btn {:type "submit"} "Login with Google"]))

(defn required []
  (layout/layout
    (login-button)))

(defn unauthorized []
  (layout/layout
    [:p "You authenticated with a google email address that is not in the list of allowed email addresses."]
    (login-button)))
