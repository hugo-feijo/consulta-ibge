(ns consulta-ibge.controllers.cities
  (:require [schema.core :as s]
            [clojure.core.cache.wrapped :as c]
            [consulta-ibge.diplomat.http-client :as c.http-client]
            [consulta-ibge.wire.out.city :as out.city]
            [consulta-ibge.wire.out.projection :as out.projection]
            [consulta-ibge.adapters.projection :as adapters.projection]
            [consulta-ibge.logic.cities :as logic.cities]))

(def data (c/fifo-cache-factory {}))

(s/defn fetch-all-cities! :- [out.city/City]
  []
  (println "Making request to get all cities")
  (c.http-client/make-get-request! "https://servicodados.ibge.gov.br/api/v1/localidades/municipios"))

(c/through-cache data :cities (constantly (fetch-all-cities!)))

(s/defn get-city-by-name :- out.projection/Projection
  [city-name]
  (if-let [city (logic.cities/get-city-by-name city-name (:cities @data))]
    (-> city
        (adapters.projection/city->projection))
    {:error-message "City not found"}))


(s/defn get-all-cities :- [out.projection/Projection]
  "Find all cities and transform to Projection"
  []
  (let [cities (:cities @data)]
    (-> cities
        (adapters.projection/cities->projections))))