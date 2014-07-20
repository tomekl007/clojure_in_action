(ns
  ^{:author tomaszlelek}
  clojure_in_action_examples.ch1.duplication)

(defn get-expenses [user-id start-date end-date]
  (create-audit-log user-id GET-EXPENSES)
  (let [connection (connect-to-expenses-db user-id)
        expenses (find-all-between connection start-date end-date)]
    (close-connection connection)
    (flush-connection connection)
    expenses))
(defn add-expense [user-id date amount]
  (create-audit-log user-id ADD-EXPENSE)
  (let [connection (connect-to-expenses-db user-id)]
    (save-new-expense connection date amount)
    (flush-connection connection)
    (close-connection connection)))


