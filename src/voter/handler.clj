(ns voter.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [voter.controllers.topics :as topics]))

(defroutes app-routes
  (GET "/" [] topics/index)
  (POST "/topics" [] topics/create)
  (POST "/topics/:id/vote" [] topics/vote)
  (DELETE "/topics" [] topics/delete)
  (DELETE "/topics/votes" [] topics/reset-votes)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
