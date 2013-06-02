## An MVC framework from scratch in Clojure

see check src/malvaceous/example.clj

* purpose: learn
* way: no dependency third-party libraries
    * TODO: don't use `[server-socket "1.0.0"]` originally from clojure-contrib
* highly asynchronous
    * e.g. Model's create doesn't return a value of record but a promise
    * ditto in Controller
* full stack
    * web server implementation in pure Clojure code
