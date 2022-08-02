(ns consulta-ibge.wire.out.city
  (:require [schema.core :as s]
            [consulta-ibge.wire.out.microregion :as out.microregion]
            [consulta-ibge.wire.out.uf :as out.uf]))

(s/defschema City
  {:id              s/Str
   :nome            s/Str
   :microrregiao    out.microregion/Microregion
   :regiao-imediata {:id                  s/Int
                     :nome                s/Str
                     :regiao-intermediara {:id   s/Int
                                           :nome s/Str
                                           :UF   out.uf/UF}}})