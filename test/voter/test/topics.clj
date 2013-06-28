(ns voter.test.topics
  (:use clojure.test
        ring.mock.request
        voter.handler))

(deftest topics
  (testing "index"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (true? (.contains (:body response) "List of Topics"))))))
