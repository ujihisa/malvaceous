(ns malvaceous.view
  (:use [clojure.string :only (join)]))

(defn- html1 [x]
  (cond
    (seq? x)
    (if-let [[tag content] x]
      (format "<%s>%s</%s>" tag (html1 content) tag)
      "--")
    :else (str x)))

(defn html [& xs]
  (format "<html>%s</html>" (join "\n" (map html1 xs))))

(defn -main []
  (println (html '(head
                    (title "hi!"))
                 '(body
                    (h1 "hello, world")
                    (p "welcome to my page!")))))
