(ns mysite.models.blog
  (:require [clojure.java.jdbc :as jdbc]
            [mysite.config :as config]))

(def spec (:database-spec config/*credentials*))

(defn create-table []
  (jdbc/db-do-commands
   spec
   (jdbc/create-table-ddl :blog
                             [:id :int :primary :key :auto_increment]
                             [:title "varchar(64)"]
                             [:entry_date :datetime]
                             [:content :text]
                             [:tags "varchar(128)"])))

(defn select-* []
  (jdbc/query spec ["select * from blog"]))

(defn select-*-desc []
  (jdbc/query spec ["select * from blog order by id desc"]))

(defn drop-table []
  (jdbc/db-do-commands
   spec
   (jdbc/drop-table-ddl :blog)))

(defn create-post [blog-post]
  (jdbc/insert!
   spec :blog
   blog-post))

(defn delete-post [id]
  (jdbc/delete! spec :blog ["id = ?" id]))
