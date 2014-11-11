(ns mysite.routes.projects
  (:require [compojure.core :refer :all]
            [compojure.response :refer [render]]
            [compojure.route :as route]
            [mysite.views.layout :as layout]
            [mysite.models :as model]
            [mysite.views.projects :as projects]
            [mysite.config :as config]
            [mysite.routes.util :as util]))

(defn view-projects
  "Main project view
  Pass in success for alert message describing status of new/edit post.
  None -> [Hiccup]
  success :boolean -> [Hiccup]"
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

(defn show-project [proj]
  (projects/show (keyword proj)))

(defroutes projects-routes
  (GET "/projects" [] (util/success (view-projects)))
  (GET "/projects" [success] (util/success (view-projects success)))
  (GET "/projects/new" [] (util/success (new-project)))
  (GET "/projects/edit" [id] (util/success (edit-project id)))
  (GET "/projects/p/:proj" [proj] (util/success (show-project proj)))

  (POST "/projects/post" [id project-name project-description project-content 
                          project-version docs source tags username password]
        (if (post-project id project-name project-description project-content
                          project-version docs source tags username password)
          ;; 302 for redirect
          {:status 302, :headers {"Content-Type" "text/html; charset=utf-8"
                                  "Location" "/projects?success=true"}}
          {:status 302, :headers {"Content-type" "text/html; charset=utf-8"
                                  "Location" "/projects?success=true"}})))
  
