(ns
  ^{:author tomaszlelek}
  clojure_in_action_examples.ch1.stm)


(def total-expenditure (ref 0))
;; The following will throw a "No transaction running"
;; IllegalStateException exception
(defn add-amount [amount]
  (ref-set total-expenditure (+ amount @total-expenditure)))
;; The following will work fine because it will do the update inside a
;; transaction
(defn add-amount [amount]
  (dosync
    (ref-set total-expenditure (+ amount @total-expenditure))))
