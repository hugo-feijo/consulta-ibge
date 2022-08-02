(ns consulta-ibge.unit.logic.cities-test
  (:require [clojure.test :refer :all]
            [consulta-ibge.logic.cities :refer :all]))

(def regiao-intermediaria {:id 1102,
                           :nome "Ji-Paraná",
                           :UF {:id 11,
                                :sigla "RO",
                                :nome "Rondônia",
                                :regiao {:id 1, :sigla "N", :nome "Norte"}}})

(def regiao-imediata {:id 110005,
                      :nome "Cacoal",
                      :regiao-intermediaria regiao-intermediaria})

(def mesorregiao {:id 1102,
                  :nome "Leste Rondoniense",
                  :UF {:id 11, :sigla "RO", :nome "Rondônia", :regiao {:id 1, :sigla "N", :nome "Norte"}}})

(def microrregiao {:id 11006,
                   :nome "Cacoal",
                   :mesorregiao mesorregiao})

(def valid-cities [{:id "1100015",
                    :nome "Alta Floresta D'Oeste",
                    :microrregiao microrregiao,
                    :regiao-imediata regiao-imediata}
                   {:id "1100023",
                    :nome "Ariquemes",
                    :microrregiao microrregiao,
                    :regiao-imediata regiao-imediata}])


(deftest get-city-by-name-test
  (testing "That passing valid cities returns the first match name being case-insensitive"
    (let [expect-result {:id              "1100023",
                         :nome            "Ariquemes",
                         :microrregiao    microrregiao,
                         :regiao-imediata regiao-imediata}]
      (is (= expect-result (get-city-by-name "Ariquemes" valid-cities)))
      (is (= expect-result (get-city-by-name "ariquemes" valid-cities)))
      (is (= expect-result (get-city-by-name "aRiQuEmEs" valid-cities)))))

  (testing "That passing a city name that doesn't exist in the database throw a error"
    (let [expect-result {:type    :invalid-input
                         :details {:reason         :city-not-found
                                   :message "City not found"}}]
      (is (try
            (get-city-by-name "Any Other Name" valid-cities)
            false
            (catch clojure.lang.ExceptionInfo e
              (println "catch ex" e)
              (= expect-result (ex-data e))))))))