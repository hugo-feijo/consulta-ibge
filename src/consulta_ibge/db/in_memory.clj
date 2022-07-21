(ns consulta-ibge.db.in-memory
  (:require [consulta-ibge.wire.out.state :as out.state]
            [clojure.core.cache.wrapped :as c]
            [consulta-ibge.controllers.states :as c.states]
            [consulta-ibge.controllers.cities :as c.cities]))

(def data {:estado [out.state/State]})

(def data (c/fifo-cache-factory {}))
(c/through-cache data :states (constantly (c.states/find-all-state!)))
(c/through-cache data :cities (constantly (c.cities/find-all-cities!)))