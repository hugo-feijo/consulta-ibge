(ns consulta-ibge.db.in-memory
  (:require [clojure.core.cache.wrapped :as c]
            [consulta-ibge.controllers.states :as c.states]))

(def data (c/fifo-cache-factory {}))
(c/through-cache data :states (constantly (c.states/find-all-state!)))