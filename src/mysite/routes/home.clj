(ns mysite.routes.home
  (:require [compojure.core :refer :all]
            [compojure.response :refer [render]]
            [compojure.route :as route]
            [mysite.views.layout :as layout]
            [mysite.models :as model]
            [mysite.views.blog :as blog]
            [mysite.config :as config]))

(defn home []
  (let [posts (model/select-*-desc :blog)]
    (model/check-create-table :blog)
    (layout/home (first posts))))


(defn dev []
  (layout/dev))

(defn success [body]
  {:status 200, :headers {"Content-Type" "text/html"}, :body body})

(defroutes home-routes
  (GET "/" [] (success (home)))

  (GET "/projects" [] "hello")

  ;; Errors
  (GET "/dev" [] (success (dev))))
