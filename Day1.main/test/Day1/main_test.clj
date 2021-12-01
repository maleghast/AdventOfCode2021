(ns Day1.main-test
  (:require [clojure.test :refer :all]
            [Day1.main :refer :all]))

(def dummy-data [1 2 1 3 4 5 1 1 2 2 4])
(def dummy-data-partitioned (partition 2 1 dummy-data))
(def dummy-data-3-measurement-sliding-windows (partition 3 1 dummy-data))

(deftest test-part-one
  (testing "Part One of the Puzzle - Number of readings higher than the previous reading"
    (is (= 6 (count
              (filter
               true?
               (test-pairs dummy-data-partitioned)))))))

(deftest test-part-two
  (testing "Part Two of the Puzzle - Number of window sums higher than previous window sum"
    (is (= 5 (count
              (filter
               true?
               (test-pairs
                (partition
                 2
                 1
                 (create-window-totals dummy-data-3-measurement-sliding-windows)))))))))
