(ns mysite.model
  (:require [clojure.java.jdbc :as jdbc]))

(def spec {:classname "com.mysql.jdbc.Driver"
           :subprotocol "mysql"
           :subname "//127.0.0.1:3306/mysite"
           :user "shermpay"
           :password "qmonkey"})

(defn create-blog-table []
  (jdbc/db-do-commands
   spec
   (jdbc/create-table-ddl :blog
                             [:id :int :primary :key :auto_increment]
                             [:title "varchar(64)"]
                             [:entry_date :datetime]
                             [:contents :text]
                             [:tags "varchar(128)"])))

(defn select-*-blog []
  (jdbc/query spec ["select * from blog"]))

(defn select-*-blog-desc []
  (jdbc/query spec ["select * from blog order by id desc"]))

(defn drop-blog-table []
  (jdbc/db-do-commands
   spec
   (jdbc/drop-table-ddl :blog)))

(defn create-blog-post [blog-post]
  (jdbc/insert!
   spec :blog
   blog-post))

(defn delete-blog-post [id]
  (jdbc/delete! spec :blog ["id = ?" id]))