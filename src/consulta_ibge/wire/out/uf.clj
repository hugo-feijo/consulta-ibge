(ns consulta-ibge.wire.out.uf
  (:require [schema.core :as s]))

(s/defschema UF
  {:id s/Int
   :nome s/Str
   :sigla s/Str
   :regiao {:id s/Int
            :nome s/Str
            :sigla s/Str}})
