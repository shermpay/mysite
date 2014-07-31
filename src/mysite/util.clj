(ns mysite.util
  (:require [clojure.string :as str]))

(defn truncate-timestamp [ts key]
  {:pre (keyword? key)}
  (let [v (str/split (.toString ts) #":")]
   (case key
     :hour (first (str/split (first v) #" "))
     :minute (first v)
     :second (str/join ":" (subvec v 0 2)))))
