(ns mysite.routes.home
  (:require [compojure.core :refer :all]
            [compojure.response :refer [render]]
            [compojure.route :as route]
            [mysite.views.layout :as layout]
            [mysite.models :as model]
            [mysite.views.blog :as blog]
            [mysite.views.projects :as projects]
            [mysite.config :as config]))

(defn home []
  (do
    (model/check-create-table :blog)
    (let [posts (model/select-*-desc :blog)]
     (layout/home (first posts)))))

(defn projects []
  (do
    (model/check-create-table :projects)
    (let [projs (model/select-*-desc :projects)]
      (projects/view projs))))


(defn dev []
  (layout/dev))

(defn success [body]
  {:status 200, :headers {"Content-Type" "text/html"}, :body body})

(defroutes home-routes
  (GET "/" [] (success (home)))

  (GET "/projects" [] (projects))

  ;; Errors
  (GET "/dev" [] (success (dev))))
