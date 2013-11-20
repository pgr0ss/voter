(ns voter.test.models.category
  (:use clojure.test)
  (:require [voter.test.helpers :as helpers]
            [voter.models.category :as category]))

(use-fixtures :once helpers/clear-categories)
(use-fixtures :each helpers/wrap-test-in-transaction)

(deftest create
  (is (= [] (category/all)))
  (category/create! "category one")
  (is (= ["category one"] (map :name (category/all))))
  (category/create! "category two")
  (is (= ["category one", "category two"] (map :name (category/all)))))

(deftest delete-all
  (category/create! "one")
  (category/delete-all!)
  (is (= [] (category/all))))
