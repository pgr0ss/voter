(ns voter.test.controllers.auth
  (:use clojure.test
        ring.mock.request
        voter.handler)
  (:require [voter.controllers.auth :as auth]))

(deftest email-domain
  (is (= "bar.com" (auth/email-domain "foo@bar.com"))))

(deftest allowed-email-domains
  (is (= ["gmail.com", "example.com"] (auth/allowed-email-domains))))
