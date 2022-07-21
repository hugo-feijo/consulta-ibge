(ns consulta-ibge.adapters.projection
  (:require [schema.core :as s]
            [consulta-ibge.wire.out.projection :as out.projection]
            [consulta-ibge.wire.out.city :as out.city]))

(s/defn city->projection :- out.projection/Projection
  [city :- out.city/City]
  {:idEstado        (-> city :microrregiao :mesorregiao :UF :id)
   :siglaEstado     (-> city :microrregiao :mesorregiao :UF :sigla)
   :regiaoNome      (-> city :microrregiao :mesorregiao :UF :nome)
   :nomeCidade      (-> city :nome)
   :nomeMesorregiao (-> city :microrregiao :mesorregiao :nome)
   :nomeFormatado   (str (-> city :nome) "/" (-> city :microrregiao :mesorregiao :UF :siglaEstado))})

(s/defn cities->projections :- [out.projection/Projection]
  [city :- [out.city/City]]
  (map #(city->projection %) city))