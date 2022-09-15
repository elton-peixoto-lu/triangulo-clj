(ns triangulo.triangulo-test
  (:require [clojure.test :refer :all]
            [triangulo.core :refer :all]))



(def triangulos-teste
  [{:lados [60 51.96152 30],
    :perimetro 141.96152
    :agudo false,
    :isosceles false,
    :altura [25.980759999999748 29.99999999999971 51.961519999999496],
    :retangulo true,
    :angulos [90.00000807310232 59.99999192689801 29.999999999999673],
    :escaleno true,
    :area 779.4227999999924,
    :obtuso false,
    :equilateral false}
   {:lados [30 20 44],
    :perimetro 94
    :agudo false,
    :isosceles false,
    :altura [16.959952830123083 25.439929245184626 11.563604202356649],
    :retangulo false,
    :angulos [35.322649843393165 22.67189532884259 122.00545482776425],
    :escaleno true,
    :area 254.39929245184626,
    :obtuso true,
    :equilateral false}
   {:lados [15.14741 28.08887 30],
    :perimetro 73.23628
    :agudo true,
    :isosceles false,
    :altura [27.815512539195034 14.999997251271704 14.044432426377607],
    :retangulo false,
    :angulos [29.999993938186886 67.99998420642707 82.00002185538607],
    :escaleno true,
    :area 210.66648639566412,
    :obtuso false,
    :equilateral false}])

(deftest test-retangulo
  (doseq [triangulo triangulos-teste]
    (->> triangulo
         :lados
         (apply retangulo?)
         (= (:retangulo triangulo))
         is)))

(deftest test-obtuso
  (doseq [triangulo triangulos-teste]
    (->> triangulo
         :lados
         (apply obtuso?)
         (= (:obtuso triangulo))
         is)))

(deftest test-agudo
  (doseq [triangulo triangulos-teste]
    (->> triangulo
         :lados
         (apply agudo?)
         (= (:agudo triangulo))
         is)))

(deftest test-escaleno
  (doseq [triangulo triangulos-teste]
    (->> triangulo
         :lados
         (apply escaleno?)
         (= (:escaleno triangulo))
         is)))

(deftest test-isosceles
  (doseq [triangulo triangulos-teste]
    (->> triangulo
         :lados
         (apply isosceles?)
         (= (:isosceles triangulo))
         is)))

(deftest test-equilateral
  (doseq [triangulo triangulos-teste]
    (->> triangulo
         :lados
         (apply equilateral?)
         (= (:equilateral triangulo))
         is)))

(deftest test-perimetro
  (doseq [triangulo triangulos-teste]
    (->> triangulo
         :lados
         (apply calc-perimetro)
         (= (:perimetro triangulo))
         is)))

;; funções acima podem ser refatoradas.

(deftest test-area
  (doseq [triangulo triangulos-teste]
    (->> triangulo
         :lados
         (apply calc-area)
         (= (:area triangulo))
         is)))

(deftest test-altura
  (doseq [triangulo triangulos-teste]
    (let [lados (:lados triangulo)
          area (apply calc-area lados)
          fn (fn [lado] (calc-altura lado area))]
      (is (= (:altura triangulo)
             (mapv fn lados))))))

(deftest test-angulo
  (doseq [triangulo triangulos-teste]
    (let [cycle-fn (comp (partial take 3) (partial drop 1) cycle)
          lados-a (:lados triangulo)
          lados-b (cycle-fn lados-a)
          lados-c (cycle-fn lados-b)]
      (is (= (:angulos triangulo)
             (mapv #(apply calc-angulo %) [lados-a lados-b lados-c]))))))

(comment
  (run-tests *ns*)

  )
