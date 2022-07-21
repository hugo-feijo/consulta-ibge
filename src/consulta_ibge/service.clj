(ns consulta-ibge.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [consulta-ibge.logic.cities :as l.cities]))

(defn get-city-by-name
  [request]
  (if-let [city-name (get-in request [:query-params :nomeCidade])]
    (ring-resp/header (ring-resp/response (l.cities/get-city-by-name city-name)) "Content-Type" "application/json")
    (ring-resp/bad-request "Provide query nomeCidade")))

(def common-interceptors [(body-params/body-params) http/html-body])

(def routes #{["/" :get (conj common-interceptors `get-city-by-name)]})

(def service {:env :prod
              ::http/routes routes

              ::http/resource-path "/public"

              ::http/type :jetty

              ::http/port 8080

              ::http/container-options {:h2c? true
                                        :h2? false
                                        :ssl? false}})

