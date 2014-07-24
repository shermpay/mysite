(ns mysite.routes.home
  (:require [compojure.core :refer :all]
            [mysite.views.layout :as layout]))

(defn home []
  (layout/home))

(defroutes home-routes
  (GET "/" [] (home)))
