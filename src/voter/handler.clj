(ns voter.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [cemerick.friend :as friend]
            [cemerick.friend.openid :as openid]
            [environ.core :as environ]
            [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]
            [voter.controllers.auth :as auth]
            [voter.controllers.topics :as topics]
            [voter.views.login :as login]))

(defn check-email-domain [routes]
  (fn [request]
    (if (auth/allowed-email-domain? request)
      (routes request)
      (response/redirect "/unauthorized"))))

(defn authorize-routes [routes]
  (if (System/getProperty "skip-friend-authorization")
    routes
    (friend/wrap-authorize (check-email-domain routes) #{::user})))

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
  (GET "/unauthorized" [] (login/unauthorized))
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
                                    :login-failure-handler (fn [_] (response/redirect "/")))]})))

(defn -main []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "8080"))]
    (jetty/run-jetty app {:port port :join? false})))
