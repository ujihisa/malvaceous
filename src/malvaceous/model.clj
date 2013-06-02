(ns malvaceous.model)

(defprotocol CRUD
  (create [this columns]))

(defn memory [column-names]
  (let [storage (agent [0 {}])]
    (reify CRUD
      (create [this items]
        (let [p (promise)]
          (send-off storage
                    (fn [[latest-id hmap]]
                      (let [new-id (inc latest-id)
                            tuples (map vector
                                        (cons :id column-names)
                                        (cons new-id items))
                            record (apply hash-map
                                          (flatten tuples))]
                        (deliver p record)
                        [new-id (assoc hmap new-id record)])))
          p)))))
