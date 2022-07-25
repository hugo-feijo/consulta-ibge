(ns consulta-ibge.logic.json
  (:require [cheshire.core :as cheshire]
            [ring.util.response :as ring-resp]))

(defn object->json-string
  [object]
  (cheshire/generate-string object))

(defn add-header-content-type-json
  [response]
  (ring-resp/header response "Content-Type" "application/json"))

(defn response-json-content
  "Get a response body and transform to json content type"
  [response-body]
  (-> response-body
      (object->json-string)
      (ring-resp/response)
      (add-header-content-type-json)))