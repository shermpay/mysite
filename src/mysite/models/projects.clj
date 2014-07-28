(ns mysite.models.projects
  (:require [clojure.java.jdbc :as jdbc
             mysite.config :as config]))

(def spec {:database-spec config/*credentials*})

(defn check-table []
  (let [tables (jdbc/query spec ["show tables like 'blog'"])]
    (= 1 (count tables))))

(defn create-table []
  (jdbc/db-do-commands
   spec
   (jdbc/create-table-ddl :projects
                          [:id :int :primary :key :auto_increment]
                          [:name "varchar(64)"]
                          [:date :datetime]
                          [:version :int]
                          [:content :text]
                          [:github : "varchar(255)"]
                          [:tags "varchar(128)"])))
