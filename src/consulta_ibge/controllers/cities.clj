(ns consulta-ibge.controllers.cities
  (:require [schema.core :as s]
            [consulta-ibge.controllers.request :as c.request]
            [consulta-ibge.wire.out.city :as out.city]))

(s/defn find-all-cities :- [out.city/City]
  []
  (println "Making request to get all cities")
  (c.request/make-get-request "https://servicodados.ibge.gov.br/api/v1/localidades/municipios"))
