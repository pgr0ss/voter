(ns voter.test.models.topic
  (:use clojure.test)
  (:require [voter.models.topic :as topic]))

(defn clear-topics [f]
  (topic/delete-all)
  (f))

(use-fixtures :each clear-topics)

(deftest topic
  (testing "create"
    (is (= [] (topic/all)))
    (topic/create "topic one")
    (is (= ["topic one"] (topic/all)))
    (topic/create "topic two")
    (is (= ["topic one", "topic two"] (topic/all))))

  (testing "delete-all"
    (topic/create "one")
    (topic/delete-all)
    (is (= [] (topic/all)))))
