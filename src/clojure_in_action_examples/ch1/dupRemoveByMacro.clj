(ns clojure_in_action_examples.ch1.dupRemoveByMacro)

(defn add-expense [user-id date amount]
  (with-audited-connection [user-id connection]
    (save-new-expense connection date amount)))

