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
            [mysite.config :as config]
            [mysite.routes.util :as util]))

(defn home []
  (let [posts (model/select-*-desc :blog)]
    (home/home (first posts))))

(defn about []
  (about/about))

(defn dev []
  (layout/dev))


(defroutes home-routes
  (GET "/" [] (util/success (home)))
  (GET "/about" [] (util/success (about)))
  (GET "/dev" [] (util/success (dev))))
