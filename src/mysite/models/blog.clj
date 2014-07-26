(ns mysite.models.blog
  (:require [clojure.java.jdbc :as jdbc]))

(def spec {:classname "com.mysql.jdbc.Driver"
           :subprotocol "mysql"
           :subname "//127.0.0.1:3306/mysite"
           :user "shermpay"
           :password "qmonkey"})

(defn create-table []
  (jdbc/db-do-commands
   spec
   (jdbc/create-table-ddl :blog
                             [:id :int :primary :key :auto_increment]
                             [:title "varchar(64)"]
                             [:entry_date :datetime]
                             [:contents :text]
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
