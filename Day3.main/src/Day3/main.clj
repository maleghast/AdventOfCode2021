(ns Day3.main
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str])
  (:gen-class))

(defn import-fuel-data
  [filename]
  (into
   []
   (str/split-lines
    (slurp (io/resource filename)))))

(defn transform-fuel-data-to-matrix
  [fuel-data]
  (into
   []
   (map
    (fn [vec]
      (into []
            (map #(Character/digit % 10) vec)))
    fuel-data)))

(defn col
  [n m]
  (map #(get % n) m))

(defn
  count-col
  [n val m]
  (->> m
       (col n)
       (filter #(= val %))
       count))

(defn count-freqs-in-col
  [subj coll]
  (->> (range 0 (count (first coll)))
       (map #(count-col % subj coll))
       (into [])))

(defn get-power-consumption
  [input-data]
  (let [data (transform-fuel-data-to-matrix (import-fuel-data input-data))
        numrows (count data)
        zeroes (count-freqs-in-col 0 data)
        gamma (into [] (map #(if (true? %) 0 1) (map #(<= (quot numrows 2) %) zeroes)))
        epsilon (into [] (map #(if (= 0 %) 1 0) gamma))
        str-gamma (str/join gamma)
        str-epsilon (str/join epsilon)]
    (println gamma)
    (*
     (Long/parseLong str-gamma 2)
     (Long/parseLong str-epsilon 2))))

(defn -main
  []
  (println "Power Consumption -> " (get-power-consumption "input.txt")))
