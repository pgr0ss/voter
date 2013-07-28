(ns voter.controllers.auth
  (:require [cemerick.friend :as friend]
            [environ.core :as environ]))

(defn email-domain [email]
  (second (re-matches #"\A.*@(.*)\Z" email)))

(defn allowed-email-domains []
  (seq (.split (:allowed-email-domains environ/env) ",")))

(defn allowed-email-domain? [request]
  (let [auth (friend/current-authentication request)
        domain (email-domain (:email auth))]
    (some #{domain} (allowed-email-domains))))
