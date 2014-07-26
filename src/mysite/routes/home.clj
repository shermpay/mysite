(ns mysite.routes.home
  (:require [compojure.core :refer :all]
            [mysite.views.layout :as layout]
            [mysite.models.blog :as blog]))

(defn home []
  (layout/home))

(defn blog []
  (let [posts (blog/select-*-desc)
        latest (first posts)
        latest-2 (second posts)]
   (layout/blog latest latest-2)))

(defn dev []
  (layout/dev))

(defroutes home-routes
  (GET "/" [] (home))
  (GET "/blog" [] (blog))
  (GET "/dev" [] (dev)))
