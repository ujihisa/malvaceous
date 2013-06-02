(defproject malvaceous "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [server-socket "1.0.0"]]
  :license {:name "GPL-3+"
            :url "http://www.gnu.org/licenses/gpl-3.0.en.html"}
  ;:min-lein-version "2.1.0"
  :jvm-opts ["-XX:+TieredCompilation" "-XX:TieredStopAtLevel=1"]
  :main malvaceous.example)
