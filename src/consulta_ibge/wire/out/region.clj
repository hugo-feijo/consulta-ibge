(ns consulta-ibge.wire.out.region
  (:require [schema.core :as s]))

(s/defschema Region
  {:id s/Int
   :sigla s/Str
   :nome s/Str})