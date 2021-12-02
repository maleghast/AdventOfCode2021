(ns Day2.main-test
  (:require [clojure.test :refer :all]
            [Day2.main :refer :all]))

(comment
  "Test Input Data will be ingested to the following structure"
  [["forward" 2]
   ["down" 2]
   ["forward" 6]
   ["forward" 8]
   ["down" 8]
   ["up" 2]
   ["forward" 7]
   ["forward" 8]
   ["down" 1]
   ["down" 8]])


(deftest simple-nav-test
  (testing "Simple Navigation Outcome"
    (is (= 527
           (do
             (zero-atoms)
             (simple-nav "test-input.txt")
             (* @horizontal-pos @depth-pos))))))

(deftest complex-nav-test
  (testing "Simple Navigation Outcome"
    (is (= 4588
           (do
             (zero-atoms)
             (complex-nav "test-input.txt")
             (* @horizontal-pos @depth-pos))))))
