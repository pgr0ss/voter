(ns voter.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [cemerick.friend :as friend]
            [cemerick.friend.openid :as openid]
            [ring.util.response :as response]
            [voter.controllers.topics :as topics]
            [voter.views.login :as login]))

(defn authorize-routes [routes]
  (if (System/getProperty "skip-friend-authorization")
    routes
    (friend/wrap-authorize routes #{::user})))

(defroutes topic-routes
  (GET "/" [] topics/index)
  (POST "/" [] topics/create)
  (POST "/:id/vote" [] topics/vote)
  (DELETE "/" [] topics/delete)
  (DELETE "/votes" [] topics/reset-votes))

(defroutes app-routes
  (context "/topics" request (authorize-routes topic-routes))
  (GET "/" [] (login/required))
  (GET "/logout" request (friend/logout* (response/redirect "/")))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site
    (friend/authenticate
      app-routes
      {:allow-anon? true
       :default-landing-uri "/topics"
       :workflows [(openid/workflow :openid-uri "/login"
                                    :credential-fn #(assoc % :roles #{::user})
                                    :login-failure-handler (fn [_] (response/redirect "/")))]})
    ))
