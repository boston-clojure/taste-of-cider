(ns mytaste.core-test
  (:require [clojure.test :refer :all]
            [mytaste.core :refer :all]))

(deftest a-test
  (testing "fixme, I fail"
    (is (= 1 2))))

(deftest another-test
  (testing "silly test"
    (is (= (mysq 3) 9))))
