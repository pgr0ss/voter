(ns voter.test.controllers.topics
  (:use clojure.test
        ring.mock.request
        voter.handler)
  (:require [voter.test.helpers :as helpers]
            [voter.models.topic :as topic]))

(use-fixtures :each helpers/clear-topics)

(deftest index
  (let [response (app (request :get "/"))]
    (is (= (:status response) 200))
    (is (true? (.contains (:body response) "List of Topics")))))

(deftest create
  (let [response (app (request :post "/topics" {:topic "topic one"}))]
    (is (= (:status response) 302))
    (is (= (get (:headers response) "Location") "/"))
    (is (= [{:id 1 :text "topic one" :votes 1}] (topic/all)))))

(deftest vote
  (topic/create! "one")
  (let [response (app (request :post (str "/topics/1/vote")))]
    (is (= (:status response) 302))
    (is (= (get (:headers response) "Location") "/"))
    (is (= 2 (:votes (first (topic/all)))))))

(deftest delete
  (topic/create! "junk")
  (let [response (app (request :delete "/topics"))]
    (is (= (:status response) 302))
    (is (= (get (:headers response) "Location") "/"))
    (is (= [] (topic/all)))))

(deftest reset-votes
  (let [topic-one (topic/create! "one")
        topic-two (topic/create! "two")]
    (topic/vote! (:id topic-one))
    (topic/vote! (:id topic-two))
    (is (= [2 2] (map :votes (topic/all))))
    (let [response (app (request :delete "/topics/votes"))]
      (is (= (:status response) 302))
      (is (= (get (:headers response) "Location") "/"))
      (is (= [0 0] (map :votes (topic/all)))))))
