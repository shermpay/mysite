(ns mysite.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [mysite.routes.home :refer [home-routes]]
            [mysite.routes.blog :refer [blog-routes]]))

(defn init []
  (println "mysite is starting"))

(defn destroy []
  (println "mysite is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes blog-routes app-routes)
      (handler/site)
      (wrap-base-url)))


