(ns malvaceous.example
  (:require [malvaceous.controller :as c]
            [malvaceous.model :as m]
            [malvaceous.webserver :as w]
            [malvaceous.util :as u]))

(def user (m/memory [:name]))

(def routing
  {['get "/"] (fn [p params]
                (u/schedule 1000
                  (deliver p
                    [200 "OK"])))
   ['post "/"] (fn [p params]
                 (prn (deref (m/create user ["ujihisa"]) 100 :timeout-create))
                 (deliver p
                   [200 (format "welcome, %s!" (get params :name "somebody"))]))})

(defn -main []
  (w/run 8080 (c/make routing)))
; vim: set lispwords+=,deliver,schedule :
