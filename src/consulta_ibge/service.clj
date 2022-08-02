(ns consulta-ibge.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [consulta-ibge.controllers.cities :as c.cities]
            [consulta-ibge.adapters.json :as adapters.json]))

(defn get-city-by-name
  [request]
  (if-let [city-name (get-in request [:query-params :nomeCidade])]
    (try
      (adapters.json/response-json-content (c.cities/get-city-by-name city-name) ring-resp/response)
      (catch clojure.lang.ExceptionInfo e
        (adapters.json/response-json-content (ex-data e) ring-resp/not-found)))
    (ring-resp/bad-request "Provide query nomeCidade")))

(defn get-all-cities
  [_]
  (adapters.json/response-json-content (c.cities/get-all-cities) ring-resp/response))

(def common-interceptors [(body-params/body-params) http/html-body])

(def routes #{["/" :get (conj common-interceptors `get-city-by-name)]
              ["/json" :get (conj common-interceptors `get-all-cities)]})

(def service {:env :prod
              ::http/routes routes

              ::http/resource-path "/public"

              ::http/type :jetty

              ::http/port 8080

              ::http/container-options {:h2c? true
                                        :h2? false
                                        :ssl? false}})

