(ns mysite.models.blog
  (:require [clojure.java.jdbc :as jdbc]
            [mysite.config :as config]
            [mysite.models.model :as model]))

(def table-name "blog")
(def table-key (keyword table-name))

(defn check-table []
  (model/check-table table-name))

(defn create-table []
  (model/create-table (keyword table-name)
                      [:id :int :primary :key :auto_increment]
                      [:title "varchar(64)"]
                      [:entry_date :datetime]
                      [:content :text]
                      [:tags "varchar(128)"]))

(defn check-create-table []
  (model/check-create-table check-table create-table))

(defn select-* []
  (model/select-* table-name))

(defn select-id [id]
  (model/select-id table-name id))

(defn select-*-desc []
  (jdbc/query model/spec ["select * from blog order by id desc"]))

(defn drop-table []
  (model/drop-table table-key))

(defn create-post [blog-post]
  (jdbc/insert!
   model/spec table-key
   blog-post))

(defn update-post [id blog-post]
  (jdbc/update!
   model/spec table-key
   blog-post ["id = ? " id]))

(defn delete-post [id]
  (jdbc/delete! model/spec table-key ["id = ?" id]))
