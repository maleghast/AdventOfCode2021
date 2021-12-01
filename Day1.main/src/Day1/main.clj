(ns Day1.main
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str])
  (:gen-class))

(def test-data [1 2 1 3 4 5 1 1 2 2 4])
(def test-data-partitioned (partition 2 1 test-data))
(def test-data-3-measurement-sliding-windows (partition 3 1 test-data))

(def actual-data (map #(Integer/parseInt %) (str/split (slurp (io/resource "input.txt")) #"\n")))
(def actual-data-partitioned (partition 2 1 actual-data))
(def actual-data-3-measurement-sliding-windows (partition 3 1 actual-data))

(defn test-pairs
  [data]
  (reduce
   (fn [acc coll]
     (if (< (first coll) (last coll))
       (conj acc true)
       (conj acc false)))
   []
   data))

(defn create-window-totals
  [data]
  (reduce
   (fn [acc coll]
     (conj acc (reduce + coll)))
   []
   data))

(defn -main
  []
  (println "Number of readings deeper than last -> "
           (count
            (filter
             true?
             (test-pairs actual-data-partitioned))))
  (println "Number of Increased 3-reading window sums -> "
           (count
            (filter
             true?
             (test-pairs
              (partition
               2
               1
               (create-window-totals actual-data-3-measurement-sliding-windows)))))))
