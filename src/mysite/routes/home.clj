(ns mysite.routes.home
  (:require [compojure.core :refer :all]
            [compojure.response :refer [render]]
            [compojure.route :as route]
            [mysite.views.layout :as layout]
            [mysite.models :as model]
            [mysite.views.home :as home]
            [mysite.views.blog :as blog]
            [mysite.views.projects :as projects]
            [mysite.views.about :as about]
            [mysite.config :as config]))

(defn home []
  (let [posts (model/select-*-desc :blog)]
    (home/home (first posts))))

(defn about []
  (about/about))

(defn dev []
  (layout/dev))

(defn success [body]
  {:status 200, :headers {"Content-Type" "text/html; charset=utf-16"}, :body body})

(defroutes home-routes
  (GET "/" [] (success (home)))
  (GET "/about" [] (success (about)))
  (GET "/dev" [] (success (dev))))
