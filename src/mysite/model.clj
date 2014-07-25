(ns mysite.model
  (:require [clojure.java.jdbc :as jdbc]
            [korma.db :as db]))

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
                             [:content :text]
                             [:tags "varchar(128)"])))

(defn drop-blog-table []
  (jdbc/db-do-commands
   spec
   (jdbc/drop-table-ddl :blog)))

(defn create-blog-post [properties]
  (jdbc/insert! spec :blog properties))

(defn delete-blog-post [id]
  (jdbc/delete! spec :blog ["id = ?" id]))
