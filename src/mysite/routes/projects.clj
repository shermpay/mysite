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

(defn post-project [id name content version docs source tags username password]
  (if (config/user-validation :root username password)
    (let [project {:name name
                   :content content
                   :version version
                   :docs docs
                   :source source
                   :tags tags}]
      (if id
        (model/update-row! :projects id (assoc project :new-date (java.util.Date.)))
        (model/insert-row! (assoc project :start-date (java.util.Date.)))))))

(defroutes projects-routes
  (GET "/projects" [] (success-response (view-projects)))
  (GET "/projects/new" [] (success-response (new-project)))
  (GET "/projects/edit" [id] (success-response (edit-project id)))

  (POST "/projects/post" [id name content docs source tags username password]))
  
