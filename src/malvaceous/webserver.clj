(ns malvaceous.webserver
  (:require [server.socket :as s])
  (:require [clojure.java.io]))

(defn run [port f]
  (letfn [(server [i-stream o-stream]
            (let [line (.readLine (clojure.java.io/reader i-stream))]
              (if-let [[x method path]
                       (re-matches #"^(GET|POST|PUT|DELETE)\s([^ ]+).*" line)]
                (with-open [w (clojure.java.io/writer o-stream :append true)]
                  (let [[code body] (f method path)]
                    (.write w (format "HTTP/1.0 %d OK\n" code))
                    (.write w "Content-Type: text/plain; charset=UTF-8\n")
                    (.write w "Server: malvaceous\n")
                    (.write w "\n")
                    (.write w (str body))))
                nil)))]
    (s/create-server port server)))

(defn -main []
  (let [port 8080]
    (run port (fn [method path]
                [200 (format "Good! [%s]%s" method path)]))
    (prn :webserver-ready port)))
