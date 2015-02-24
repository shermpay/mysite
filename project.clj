(defproject mysite "1.1.2"
  :description "Sherman Pay's personal website."
  :url "http://shermanpay.com/"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [hiccup "1.0.5"]
                 [ring-server "0.4.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [mysql/mysql-connector-java "5.1.34"]
                 [markdown-clj "0.9.62"]]
  :resource-paths ["resources"]
  :plugins [[lein-ring "0.8.13"]
            [lein-cljsbuild "1.0.3"]
            [lein-figwheel "0.2.0-SNAPSHOT"]]
  :ring {:handler mysite.handler/app
         :init mysite.handler/init
         :destroy mysite.handler/destroy}
  :cljsbuild {:builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/js/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.2"]
                   [org.clojure/clojurescript "0.0-2727"]
                   [figwheel "0.2.0-SNAPSHOT"]]}})
