(ns
  ^{:author tomaszlelek}
  clojure_in_action_examples.ch2.GettingStarted)



(def users {"kyle" {:password "secretk" :number-pets 2}
            "siva" {:password "secrets" :number-pets 4}
            "rob" {:password "secretr" :number-pets 6}
            "george" {:password "secretg" :number-pets 8}})

(defn check-login [username password]
  (let [actual-password ((users username) :password)]
    (= actual-password password)))

(check-login "siva" "secrets")

;(check-login "kyle" "blah")

(defn ifs [x] (cond
  (> x 0) (println "greater!")
  (= x 0) (println "zero! ")
  :default (println "neither!")))

(ifs -1)

(let [x 1
      y 2
      z (+ x y)
      weird "string "]
  (println z) (println weird))


(defn average-pets []
  (let [user-data (vals users)
        _ (println user-data)
        number-pets (map :number-pets user-data)
        _ (println "total  pets:" number-pets)          ;_ - we do not care about name of that returned value ( not need
        ;to assing to let )
        total (apply + number-pets)]
    (println "total : " total)
    (/ total (count users))))

(average-pets)

(defn average-pets [users]
  (try
    (let [user-data (vals users)
          number-pets (map :number-pets user-data)
          total (apply + number-pets)]
      (/ total (count users)))
    (catch Exception e
      (println "Error!")
      0)))


'(add 1 2 3 4 5)    ; using quote macro ( ' ) not evaluete exression, returns it as it is

(defn fact-loop [n]
  (loop [current n fact 1];current gets bound to the value of n, and fact gets bound to a value of 1.
    (if (= current 1)
      fact
      (recur (dec current) (* fact current)))));Execution then returns to the start of the loop body.
      ; In this example, recur has two binding values, (dec current) and (* fact current),
      ; which are computed and rebound to current and fact


(defn run-report [user]
  (println "Running report for" user))

;;;Here, the form of interest is doseq.
;;; The simplest form accepts a vector containing two terms
;;; , where the first term is a new symbol, which will be sequentially
;;; bound to each element in the second term (which must be a sequence)
;;; The body will be executed for each element in the sequence. In this case,
;;; dispatch-reporting-jobs will call run-reports for each user present in the sequence all-users.
(defn dispatch-reporting-jobs [all-users]
  (doseq [user all-users]
    (run-report user)))

(dispatch-reporting-jobs [:first :second])


(defn factorial [n]
  (let [numbers (range 1 (+ n 1))]
  (reduce * numbers)))

(dotimes [x 5]
  (println "Factorial of " x "is =" (factorial x)))

(factorial 3)


(defn chessboard-labels []
  (for [alpha "abcdefgh"
        num (range 1 9)]
    (str alpha num)))

(chessboard-labels)


(defn prime? [x]
  (let [divisors (range 2 (inc (int (Math/sqrt x))))
        remainders (map #(rem x %) divisors)]
    (not (some zero? remainders))))

(defn primes-less-than [n]
  (for [x (range 2 (inc n))
        :when (prime? x)]
    x))
(primes-less-than 30)


(defn pairs-for-primes [n] (let [z (range 2 (inc n))]
                             (for [x z y z :when (prime? (+ x y))]   ;x and y are taken from range ( z )
                               (list x y))
                             ))

(pairs-for-primes 5)

(defn final-amount [principle rate time-periods]
  (* (Math/pow (+ 1 (/ rate 100)) time-periods) principle))

(final-amount 100 20 1)
;equivalent using macro thread first
(defn final-amount-> [principle rate time-periods]
  (-> rate
    (/ 100)
    (+ 1)
    (Math/pow time-periods)
    (* principle)))

(final-amount-> 120 20 1)


(defn factorial [n]
  (apply *
    (range 1
      (+ 1
        n))))

;equivalent using thread-last macro
(defn factorial->> [n]
  (->> n
    (+ 1)
    (range 1)
    (apply *)))

(factorial->> 5)


(.split "clojure-in-action" "-")

(def three-numbers (list 1 2 3))
(def three-numbers '(1 2 3))

(def the-map {:a 1 :b 2 :c 3})

(def updated-map (assoc the-map :d 4))
(println updated-map)
(dissoc updated-map :a)

(def users {:kyle {
                    :date-joined "2009-01-01"
                    :summary {
                               :average {
                                          :monthly 1000
                                          :yearly 12000}}}})

(assoc-in users [:kyle :summary :average :monthly] 3000)

(get-in users [:kyle :summary :average :monthly])  ;remember that users is immutable

(def monthly-updated (update-in users [:kyle :summary :average :monthly] + 500) )
(println "new monthly : " monthly-updated)
