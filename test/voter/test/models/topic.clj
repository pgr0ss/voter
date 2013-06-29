(ns voter.test.models.topic
  (:use clojure.test)
  (:require [voter.test.helpers :as helpers]
             voter.models.topic :as topic]))

(use-fixtures :each helpers/clear-topics)

(deftest create
  (is (= [] (topic/all)))
  (topic/create! "topic one")
  (is (= [{:id 1 :text "topic one" :votes 1}] (topic/all)))
  (topic/create! "topic two")
  (is (= [{:id 1 :text "topic one" :votes 1}, 
          {:id 2 :text "topic two" :votes 1}]
         (topic/all))))

(deftest vote
  (topic/create! "topic one")
  (is (= [{:id 1 :text "topic one" :votes 1}] (topic/all)))
  (topic/vote! 1)
  (is (= [{:id 1 :text "topic one" :votes 2}] (topic/all))))

(deftest delete-all
  (topic/create! "one")
  (topic/delete-all!)
  (is (= [] (topic/all))))
