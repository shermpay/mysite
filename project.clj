(defproject mysite "1.2.0"
  :description "Sherman Pay's personal website."
  :url "http://shermanpay.com/"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [hiccup "1.0.5"]
                 [ring-server "0.4.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [mysql/mysql-connector-java "5.1.34"]
                 [markdown-clj "0.9.62"]
                 [org.clojure/clojurescript "0.0-2913"]]
  :resource-paths ["resources"]
  :clean-targets ^{:protect false} ["resources/public/js/out"
                                    "resources/public/main.js"]
  :plugins [[lein-ring "0.8.13"]
            [lein-cljsbuild "1.0.5"]
            [lein-figwheel "0.2.0-SNAPSHOT"]]
  :ring {:handler mysite.handler/app
         :init mysite.handler/init
         :destroy mysite.handler/destroy}
  :cljsbuild {:builds [{:id "prod"
                        :source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/js/main.js"
                                   :optimizations :advanced
                                   :pretty-print false}}
                       {:id "dev"
                        :source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/js/main.js"
                                   ;; Intermediate compilation files
                                   :output-dir "resources/public/js/out"
                                   :optimizations :none
                                   :pretty-print true}}]}
  :figwheel {
             :http-server-root "public" ;; this will be in resources/
             :server-port 3449          ;; default

             ;; CSS reloading (optional)
             ;; :css-dirs has no default value 
             ;; if :css-dirs is set figwheel will detect css file changes and
             ;; send them to the browser
             :css-dirs ["resources/public/css"]

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server
             :ring-handler mysite.handler/app

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             :open-file-command "myfile-opener"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log" 

             } 
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.2"]
                   [org.clojure/clojurescript "0.0-2727"]
                   [figwheel "0.2.0-SNAPSHOT"]]}})
