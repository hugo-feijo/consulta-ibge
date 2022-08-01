(ns consulta-ibge.logic.cities
  (:require [schema.core :as s]
            [consulta-ibge.wire.out.city :as out.city]
            [clojure.string :refer (upper-case)]))

(s/defn get-city-by-name :- out.city/City
  "Filter cities by name (case insensitive), and return wire.out.City"
  [city-name cities]
  (let [city-name-upper-case (upper-case city-name)]
    (->> cities
         (filter #(= city-name-upper-case (upper-case (:nome %))))
         (first))))