(ns mysite.routes.util
  (:require [compojure.core :refer :all]
            [compojure.response :refer [render]]
            [compojure.route :as route]))

(defn success [body]
  {:status 200, :headers {"Content-Type" "text/html; charset=utf-8"}, :body body})
