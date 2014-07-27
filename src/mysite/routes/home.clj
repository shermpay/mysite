(ns mysite.routes.home
  (:require [compojure.core :refer :all]
            [compojure.response :refer [render]]
            [mysite.views.layout :as layout]
            [mysite.models.blog :as blog-model]
            [mysite.views.blog :as blog]
            [mysite.config :as config]))

(defn home []
  (layout/home))

(defn view-blog []
  (let [posts (blog-model/select-*-desc)
        latest (first posts)
        latest-2 (second posts)]
    (blog-model/check-create-table)
    (blog/view latest latest-2)))

(defn new-blog []
  (blog/new))

(defn post-blog [title content tags username password]
  (if (config/user-validation :root username password)
    (let [post {:title title
                :content content
                :entry_date (java.util.Date.)
                :tags tags}]
      (blog-model/create-post post)
      post)
    (. System/err println "Error posting blog. Username/Password invalid.")))

(defn dev []
  (layout/dev))

(defroutes home-routes
  (GET "/" [] (home))
  (GET "/blog" [] (view-blog))
  (GET "/blog/new" [] (new-blog))
  (POST "/blog/post" [blog-title blog-content tags username password]
        (post-blog blog-title blog-content tags username password))
  (GET "/dev" [] (dev)))
