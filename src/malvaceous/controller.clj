(ns malvaceous.controller)

(defn- http-request [routing method path params]
  (let [p (promise)]
    (if-let [handler (routing [method path])]
      (future
        (try
          (handler p params)
          (catch Exception e [500 e])))
      (deliver p [404 nil]))
    p))

(def METHOD->method
  {"GET" 'get "POST" 'post "PUT" 'put "DELETE" 'delete})

(defn make [routing]
  (fn [method path]
    (let [p (http-request routing (METHOD->method method) path {})]
      (deref p 2000 [500 "timeout"]))))
