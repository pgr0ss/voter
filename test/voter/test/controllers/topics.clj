(ns voter.test.controllers.topics
  (:use clojure.test
        ring.mock.request
        voter.handler)
  (:require [voter.models.topic :as topic]))

(deftest topics
  (testing "index"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (true? (.contains (:body response) "List of Topics")))))

  (testing "create"
    (let [response (app (request :post "/topics" {:topic "topic one"}))]
      (is (= (:status response) 302))
      (is (= (get (:headers response) "Location") "/"))
      (is (= ["topic one"] (topic/all))))))
