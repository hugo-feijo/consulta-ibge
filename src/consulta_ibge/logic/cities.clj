(ns consulta-ibge.logic.cities
  (:require [schema.core :as s]
            [consulta-ibge.wire.out.city :as out.city]
            [clojure.string :refer (upper-case)]))

(s/defn get-city-by-name :- out.city/City
  "Filter cities by name (case-insensitive), and return wire.out.City"
  [city-name :- s/Str
   cities :- [out.city/City]]
  (if-let [city (->> cities
                     (filter #(= (upper-case city-name) (upper-case (:nome %))))
                     (first))]
    city
    (throw (ex-info "Requisition is already answered"
                    {:type    :invalid-input
                     :details {:reason         :city-not-found
                               :message "City not found"}}))))