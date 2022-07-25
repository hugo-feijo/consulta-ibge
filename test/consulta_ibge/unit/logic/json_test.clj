(ns consulta-ibge.unit.logic.json-test
  (:require [clojure.test :refer :all]
            [consulta-ibge.logic.json :refer :all]))

(deftest object->json-string-test
  (testing "That passing a valid vector return a valid json"
    (let [valid-vector [{:id 15 :name "Hugo"}]
          valid-json "[{\"id\":15,\"name\":\"Hugo\"}]"]
      (is (= (object->json-string valid-vector) valid-json)))))

