(ns
  ^{:author tomaszlelek}
  clojure_in_action_examples.ch1.LazySeq)

(defn next-terms [term-1 term-2]
  (let [term-3 (+ term-1 term-2)]
    (lazy-seq
      (cons term-3    ;Returns a new seq where x is the first element and seq is the rest.
         (next-terms term-2 term-3)))))
;First, let’s talk about the magic ingredient here, lazy-seq.
; It’s a macro that doesn’t evaluate its body immediately but returns a sequence-like object.
; This object will eval- uate the body only when needed (and will also cache the result for subsequent uses, saving CPU cycles).

(defn fibonacci [t1 t2]
  (concat [t1 t2]
    (next-terms t1 t2)))

;(println (concat [ 0 1 ] ) )
(take 15 (fibonacci 0 1))

(defn firstN [n] ( take n (fibonacci 0 1)) )
(firstN 10)