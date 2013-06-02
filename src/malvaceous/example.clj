(ns malvaceous.example
  (:require [malvaceous.webserver :as w]))

(def routing
  {['get "/"] (fn [p params]
                (Thread/sleep 1000)
                (deliver p
                  [200 "OK"]))
   ['post "/"] (fn [p params]
                 (deliver p
                   [200 (format "welcome, %s!" (get params :name "somebody"))]))})

(defn http-request [method path params]
  (let [p (promise)]
    (if-let [handler (routing [method path])]
      (future
        (handler p params))
      (deliver p [404 nil]))
    p))

(def METHOD->method
  {"GET" 'get "POST" 'post "PUT" 'put "DELETE" 'delete})

(defn -main []
  (w/run 8080 (fn [method path]
                (prn [:method method :path path])
                (let [p (http-request (METHOD->method method) path {})]
                  (deref p 2000 [500 "timeout"]))))
  #_(let [p (http-request 'get "/" {})]
    (let [[code body] (deref p 2000 [500 :timeout])]
      (prn :code code :body body)))
  #_(let [p (http-request 'post "/" {:name "uj"})]
    (let [[code body] (deref p 2000 [500 :timeout])]
      (prn :code code :body body)))
  #_(shutdown-agents))
