(ns malvaceous.example
  (:require [malvaceous.controller :as c])
  (:require [malvaceous.webserver :as w]))

(def routing
  {['get "/"] (fn [p params]
                (Thread/sleep 1000)
                (deliver p
                  [200 "OK"]))
   ['post "/"] (fn [p params]
                 (deliver p
                   [200 (format "welcome, %s!" (get params :name "somebody"))]))})

(defn -main []
  (w/run 8080 (c/make routing)))
