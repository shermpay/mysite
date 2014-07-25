(ns mysite.routes.home
  (:require [compojure.core :refer :all]
            [mysite.views.layout :as layout]))

(defn home []
  (layout/home))

(defn dev []
  (layout/dev))

(defroutes home-routes
  (GET "/" [] (home))
  (GET "/dev" [] (dev)))
