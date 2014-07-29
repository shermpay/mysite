(ns mysite.routes.projects
  (:require [compojure.core :refer :all]
            [compojure.response :refer [render]]
            [compojure.route :as route]
            [mysite.views.layout :as layout]
            [mysite.models :as model]
            [mysite.views.projects :as projects]
            [mysite.config :as config]))

(defn- success-response [body]
  {:status 200, :headers {"Content-Type" "text/html"}, :body body})

(defn view-projects []
  (do
    (model/check-create-table :projects)
    (let [projs (model/select-*-desc :projects)]
      (projects/view projs))))

(defn new-project []
  (projects/new))

(defn edit-project [id] '())

(defroutes projects-routes
  (GET "/projects" [] (success-response (view-projects)))
  (GET "/projects/new" [] (success-response (new-project)))
  (GET "/projects/edit" [id] (success-response (edit-project id)))

  (POST "/projects/post" [id name content source docs tags username password]))
  
