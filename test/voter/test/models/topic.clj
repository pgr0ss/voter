(ns voter.test.models.topic
  (:use clojure.test)
  (:require [voter.test.helpers :as helpers]
            [voter.models.topic :as topic]))

(use-fixtures :once helpers/clear-topics)
(use-fixtures :each helpers/wrap-test-in-transaction)

(defn- remove-id [topic]
  (dissoc topic :id))

(deftest create
  (is (= [] (topic/all)))
  (topic/create! "topic one")
  (is (= [{:text "topic one" :votes 1}] (map remove-id (topic/all))))
  (topic/create! "topic two")
  (is (= [{:text "topic one" :votes 1},
          {:text "topic two" :votes 1}]
         (map remove-id (topic/all)))))

(deftest vote
  (let [topic (topic/create! "topic one")]
    (is (= [{:text "topic one" :votes 1}] (map remove-id (topic/all))))
    (topic/vote! (:id topic))
    (is (= [{:text "topic one" :votes 2}] (map remove-id (topic/all))))))

(deftest delete-all
  (topic/create! "one")
  (topic/delete-all!)
  (is (= [] (topic/all))))

(deftest reset-votes
  (let [topic-one (topic/create! "one")
        topic-two (topic/create! "two")]
    (topic/vote! (:id topic-one))
    (topic/vote! (:id topic-one))
    (topic/vote! (:id topic-two))
    (is (= [3 2] (map :votes (topic/all))))
    (topic/reset-votes!)
    (is (= [0 0] (map :votes (topic/all))))))

