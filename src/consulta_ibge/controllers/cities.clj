(ns consulta-ibge.controllers.cities
  (:require [schema.core :as s]
            [consulta-ibge.diplomat.http-client :as c.http-client]
            [consulta-ibge.wire.out.city :as out.city]))

(s/defn find-all-cities! :- [out.city/City]
  []
  (println "Making request to get all cities")
  (c.http-client/make-get-request! "https://servicodados.ibge.gov.br/api/v1/localidades/municipios"))
