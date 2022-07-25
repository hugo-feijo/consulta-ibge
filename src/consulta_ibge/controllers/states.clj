(ns consulta-ibge.controllers.states
  (:require [schema.core :as s]
            [consulta-ibge.wire.out.state :as out.state]
            [consulta-ibge.diplomat.http-client :as c.http-client]))


(s/defn find-all-state! :- [out.state/State]
        []
        (println "Making request to get all states")
        (c.http-client/make-get-request! "https://servicodados.ibge.gov.br/api/v1/localidades/estados"))