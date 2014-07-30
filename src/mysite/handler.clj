(ns mysite.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [mysite.routes.home :refer [home-routes]]
            [mysite.routes.blog :refer [blog-routes]]
            [mysite.routes.projects :refer [projects-routes]]
            [mysite.models :as models]))

(defn init []
  (do
    (println "mysite initializing")
    (doseq [table (keys models/table-specs)]
      (println "Checking/Creating table " table)
      (models/check-create-table table))
    (println "mysite is starting")))

(defn destroy []
  (println "mysite is shutting down"))


(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes blog-routes projects-routes app-routes)
      (handler/site)
      (wrap-base-url)))


