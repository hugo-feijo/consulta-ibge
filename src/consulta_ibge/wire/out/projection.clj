(ns consulta-ibge.wire.out.projection
  (:require [schema.core :as s]))

(s/defschema Projection
  {:idEstado s/Int
   :siglaEstado s/Str
   :regiaoNome s/Str
   :nomeCidade s/Str
   :nomeMesorregiao s/Str
   :nomeFormatado s/Str})