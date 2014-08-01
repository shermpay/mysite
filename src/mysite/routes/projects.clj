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

(defn view-projects
  ([]
     (let [projs (model/select-*-desc :projects)]
       (projects/view projs)))
  ([success]
     (let [msg (if (boolean success) "\"Post success!\"" "\"Failed to post.\"")]
       (let [projs (model/select-*-desc :projects)]
         (projects/view projs)))))

(defn new-project []
  (projects/new))

(defn edit-project [id]
  (projects/edit id))


(defn post-project [id name description content version docs source tags username password]
  (if (config/user-validation :root username password)
    (let [project {:name name
                   :description description
                   :content content
                   :version version
                   :new_date (java.util.Date.)
                   :docs docs
                   :source source
                   :tags tags}]
      (if (empty? id)
        (model/insert-row! :projects (assoc project :start_date (java.util.Date.)))
        (model/update-row! :projects id project))
      true)
    (let [msg "Error posting project. Username/Password invalid."]
      (. System/err println msg)
      false)))

(defroutes projects-routes
  (GET "/projects" [] (success-response (view-projects)))
  (GET "/projects" [success] (success-response (view-projects success)))
  (GET "/projects/new" [] (success-response (new-project)))
  (GET "/projects/edit" [id] (success-response (edit-project id)))

  (POST "/projects/post" [id project-name project-description project-content 
                          project-version docs source tags username password]
        (if (post-project id project-name project-description project-content
                          project-version docs source tags username password)
          {:status 302, :headers {"Location" "/projects?success=true"}}
          {:status 302, :headers {"Location" "/projects?success=true"}})))
  
