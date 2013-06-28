(ns voter.test.handler
  (:use clojure.test
        ring.mock.request  
        voter.handler))

(deftest test-app
  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
