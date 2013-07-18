(ns voter.views.login
  (:use hiccup.core)
  (:use hiccup.element)
  (:use hiccup.form))

(defn required []
  (html
    [:html
     [:head
      [:meta {:charset "utf-8"}]
      [:title "Voter"]
      [:meta {:viewport "width=device-width, initial-scale=1.0"}]
      [:link {:href "/css/bootstrap.min.css" :rel "stylesheet"}]
      [:link {:href "/css/bootstrap-responsive.min.css" :rel "stylesheet"}]]
     [:body
      [:div.container
       (form-to [:post "/login"]
                [:input {:type "hidden" :name "identifier" :value "https://www.google.com/accounts/o8/id"}]
                [:button.btn {:type "submit"} "Login with Google"])]]]))
