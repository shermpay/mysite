(ns mysite.routes.home
  (:require [compojure.core :refer :all]
            [compojure.response :refer [render]]
            [mysite.views.layout :as layout]
            [mysite.models.blog :as blog-model]
            [mysite.views.blog :as blog]
            [mysite.config :as config]))

(defn home []
  (let [posts (blog-model/select-*-desc)]
    (blog-model/check-create-table)
    (layout/home (first posts))))


(defn view-blog []
  (let [posts (blog-model/select-*-desc)]
    (blog-model/check-create-table)
        (apply blog/view (take blog/show-count posts))))

(defn new-blog []
  (blog/new))

(defn edit-blog [id]
  (blog/edit id))

(defn post-blog [id title content tags username password]
  (if (config/user-validation :root username password)
    (let [post {:title title
                :content content
                :entry_date (java.util.Date.)
                :tags tags}]
      (if id
        (blog-model/update-post id post)
        (blog-model/create-post post))
      post)
    (. System/err println "Error posting blog. Username/Password invalid.")))

(defn dev []
  (layout/dev))

(defroutes home-routes
  (GET "/" [] (home))
  (GET "/blog" [] (view-blog))
  (GET "/blog/new" [] (new-blog))
  (GET "/blog/edit" [id] (edit-blog id))
  (POST "/blog/post" [id blog-title blog-content tags username password]
        (post-blog id blog-title blog-content tags username password))
  (GET "/dev" [] (dev)))
