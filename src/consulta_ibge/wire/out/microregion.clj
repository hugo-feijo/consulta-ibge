(ns consulta-ibge.wire.out.microregion
  (:require [schema.core :as s]
            [consulta-ibge.wire.out.mesoregion :as out.mesoregion]))

(s/defschema Microregion
  {:id          s/Int
   :nome        s/Str
   :mesorregiao out.mesoregion/Mesoregion})