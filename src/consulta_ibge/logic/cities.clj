(ns consulta-ibge.logic.cities
  (:require [schema.core :as s]
            [consulta-ibge.db.in-memory :as db.in-memory]
            [consulta-ibge.adapters.projection :as adapters.projection]
            [consulta-ibge.wire.out.projection :as out.projection]
            [clojure.string :refer (upper-case)]))

(s/defn get-city-by-name :- out.projection/Projection
  "Filter cities name (cas insensitive) in in-memory db by parameter provide,
  and return dados_formatado"
  [city-name]
  (let [cities (:cities @db.in-memory/data)
        city-name-upper-case (upper-case city-name)]
    (->> cities
         (filter #(= city-name-upper-case (upper-case (:nome %))))
         (first)
         (adapters.projection/city->projection))))