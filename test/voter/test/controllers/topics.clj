(ns voter.test.controllers.topics
  (:use clojure.test
        ring.mock.request
        voter.handler)
  (:require [voter.test.helpers :as helpers]
            [voter.models.topic :as topic]))

(use-fixtures :once helpers/clear-topics)

(defn- setup [f]
  (java.lang.System/setProperty "skip-friend-authorization" "true")
  (helpers/wrap-test-in-transaction f)
  (java.lang.System/clearProperty "skip-friend-authorization"))

(use-fixtures :each setup)


(deftest index
  (let [response (app (request :get "/topics"))]
    (is (= (:status response) 200))
    (is (true? (.contains (:body response) "List of Topics")))))

(deftest create
  (let [response (app (request :post "/topics" {:topic "topic one"}))]
    (is (= (:status response) 302))
    (is (= (get (:headers response) "Location") "/topics"))
    (is (= ["topic one"] (map :text (topic/all))))))

(deftest vote
  (let [topic (topic/create! "one")
        response (app (request :post (str "/topics/" (:id topic) "/vote")))]
    (is (= (:status response) 302))
    (is (= (get (:headers response) "Location") "/topics"))
    (is (= 2 (:votes (first (topic/all)))))))

(deftest delete
  (topic/create! "junk")
  (let [response (app (request :delete "/topics"))]
    (is (= (:status response) 302))
    (is (= (get (:headers response) "Location") "/topics"))
    (is (= [] (topic/all)))))

(deftest reset-votes
  (let [topic-one (topic/create! "one")
        topic-two (topic/create! "two")]
    (topic/vote! (:id topic-one))
    (topic/vote! (:id topic-two))
    (is (= [2 2] (map :votes (topic/all))))
    (let [response (app (request :delete "/topics/votes"))]
      (is (= (:status response) 302))
      (is (= (get (:headers response) "Location") "/topics"))
      (is (= [0 0] (map :votes (topic/all)))))))
