(ns consulta-ibge.controllers.request
  (:require [clj-http.client :as client]
            [cheshire.core :as cheshire]))

(defn make-get-request!
  [url]
  (cheshire/parse-string-strict (:body (client/get url)) true))