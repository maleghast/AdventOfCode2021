(ns Day2.main
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str])
  (:gen-class))

(def horizontal-pos (atom 0))
(def depth-pos (atom 0))
(def aim (atom 0))

(defn import-instructions-data
  []
  (into
   []
   (map
    #(str/split (first %) #" ")
    (partition
     1
     (str/split
      (slurp (io/resource "input.txt"))
      #"\n")))))

(defn prepare-instructions
  [data]
  (into
   []
   (map
    (fn
      [vec]
      (update
       vec
       1
       #(Integer/parseInt %)))
    data)))

(defn simple-nav
  []
  (doseq [instruction (prepare-instructions (import-instructions-data))]
    (cond
      (= "forward" (first instruction)) (swap! horizontal-pos + (last instruction))
      (= "up" (first instruction)) (swap! depth-pos - (last instruction))
      (= "down" (first instruction)) (swap! depth-pos + (last instruction)))))

(defn complex-nav
  []
  (doseq [instruction (prepare-instructions (import-instructions-data))]
    (cond
      (= "forward" (first instruction)) (do
                                          (swap! horizontal-pos + (last instruction))
                                          (swap! depth-pos + (* (last instruction) @aim)))
      (= "up" (first instruction)) (swap! aim - (last instruction))
      (= "down" (first instruction)) (swap! aim + (last instruction)))))

(defn zero-atoms
  []
  (do
    (reset! horizontal-pos 0)
    (reset! depth-pos 0)
    (reset! aim 0)))

(defn -main
  []
  (zero-atoms)
  (simple-nav)
  (println "Simple Nav Result -> " (* @horizontal-pos @depth-pos))
  (zero-atoms)
  (complex-nav)
  (println "Complex Nav Result -> " (* @horizontal-pos @depth-pos)))
