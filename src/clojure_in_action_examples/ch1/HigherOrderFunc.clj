(ns
  ^{:author tomaszlelek}
  clojure_in_action_examples.ch1.HigherOrderFunc)

(def post-headers [{:title "first one ever" :length 430}
                   {:title "second baby step" :length 650}
                   {:title "three is company" :length 720}
                   {:title "fourth for the road" :length 190}
                   {:title "five again" :length 280}])
(defn long-post-headers [threshold-length headers]
  (println headers)
  (let [is-long? (fn [header]
                   (> (header :length) threshold-length))]
     (filter is-long? headers)))

(defn long-post-titles [threshold-length headers]
  (map :title (long-post-headers threshold-length headers)))     ;(long-post-headers threshold-length headers) - returns map
  ;map :title - grab only titles from that map

(long-post-titles 300 post-headers)

;(defn print-map_elem [elem] (println elem))

;(map print-map_elem post-headers)

;(map :title ["first one ever"])


(def expenses [{:amount 12.99 :merchant "amazon"}])
(def updated-expenses (conj expenses {:amount 199.95 :merchant "frys"}))
(println updated-expenses)
