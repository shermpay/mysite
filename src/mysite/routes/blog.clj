(ns mysite.routes.blog
  (:require [compojure.core :refer :all]
            [compojure.response :refer [render]]
            [compojure.route :as route]
            [mysite.views.layout :as layout]
            [mysite.models :as model]
            [mysite.views.blog :as blog]
            [mysite.config :as config]))

(defn- success-response [body]
  {:status 200, :headers {"Content-Type" "text/html"}, :body body})

(defn view-blog
  ([]
     (let [posts (model/select-*-desc :blog)]
       (apply blog/view (take blog/show-count posts))))
  ([success]
     (let [msg (if (boolean success) "\"Post success!\"" "\"Failed to post.\"")
           posts (model/select-*-desc :blog)]
       (str "<script>alert(" msg ")</script> "
            (apply blog/view (take blog/show-count posts))))))

(defn new-blog []
  (blog/new))

(defn edit-blog [id]
  (blog/edit id))

(defn post-blog [id title content tags username password]
  (if (config/user-validation :root username password)
    (let [post {:title title
                :content content
                :tags tags}]
      (if (empty? id)
        (model/insert-row! :blog (assoc post :entry_date (java.util.Date.)))
        (model/update-row! :blog id (assoc post :edited (java.util.Date.))))
      true)
    (let [msg "Error posting blog. Username/Password invalid."]
      (. System/err println msg)
      false)))

(defroutes blog-routes
  (GET "/blog" [] (success-response (view-blog)))
  (GET "/blog" [success] (success-response (view-blog success)))
  (GET "/blog/new" [] (success-response (new-blog)))
  (GET "/blog/edit" [id] (success-response (edit-blog id)))
  (POST "/blog/post" [id blog-title blog-content tags username password]
        (if (post-blog id blog-title blog-content tags username password)
          {:status 302, :headers {"Location" "/blog?success=true"}}
          {:status 302, :headers {"Location" "/blog?success=false"}})))
