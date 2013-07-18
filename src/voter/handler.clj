(ns voter.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [cemerick.friend :as friend]
            [cemerick.friend.openid :as openid]
            [ring.util.response :as response]
            [voter.controllers.topics :as topics]))

(defroutes app-routes
  (GET "/" [] topics/index)
  (POST "/topics" [] topics/create)
  (POST "/topics/:id/vote" [] topics/vote)
  (DELETE "/topics" [] topics/delete)
  (DELETE "/topics/votes" [] topics/reset-votes)
  (GET "/logout" request (friend/logout* (response/redirect "/")))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site
    (friend/authenticate
      app-routes
      {:allow-anon? true
       :default-landing-uri "/"
       :workflows [(openid/workflow :openid-uri "/login" :credential-fn identity)]})))
