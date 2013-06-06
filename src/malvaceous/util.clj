(ns malvaceous.util)

(defn fn-schedule [msec f]
  (future
    (Thread/sleep msec)
    (f)))

(defmacro schedule [msec & body]
  `(fn-schedule ~msec (fn [] ~@body)))
