(ns consulta-ibge.wire.out.mesoregion
  (:require [schema.core :as s]
            [consulta-ibge.wire.out.uf :as out.uf]))

(s/defschema Mesoregion
  {:id   s/Int
   :nome s/Str
   :UF   out.uf/UF})