(ns mysite.models
  "Namespace for all model interaction. Tables are mention via keyword, (eg. :blog for the blog table)"
  (:require [clojure.java.jdbc :as jdbc]
            [mysite.config :as config]
            [mysite.util :as util]))


(def spec (:database-spec config/*credentials*))

(def table-specs {:blog [[:id :int :primary :key :auto_increment]
                         [:title "varchar(64)"]
                         [:entry_date :datetime]
                         [:edited :datetime]
                         [:content :text]
                         [:tags "varchar(128)"]]

                  :projects [[:id :int :primary :key :auto_increment]
                             [:name "varchar(64)"]
                             [:version "varchar(32)"]
                             [:start_date :datetime]
                             [:new_date :datetime]
                             [:description :text]
                             [:content :text]
                             [:docs "varchar(255)"]
                             [:source "varchar(255)"]
                             [:tags "varchar(128)"]]})

(defn check-table
  "Check if Table exists"
  [table]
  {:pre (keyword? table)}
  (let [tables (jdbc/query spec [(str "show tables like '" (name table) "'")])]
    (= 1 (count tables))))

(defn describe-table
  "Describe table specification"
  [table]
  {:pre (keyword? table)}
  (jdbc/query spec [(str "describe " (name table))]))

(defn create-table
  "Create a new table given a table name, and table spec defined in the table-specs var"
  [table]
  {:pre (keyword? table)}
  (jdbc/db-do-commands
   spec
   (let [table-spec (table table-specs)]
       (apply (partial jdbc/create-table-ddl table) table-spec))))

(defn check-create-table
  "Convenience method to check for table and create if doesn't exist. Used during startup"
  [table]
  (jdbc/with-db-connection [_ spec]
    (if (not (check-table table))
      (create-table table (get table-specs table)))))

(defn select-*
  [table]
  {:pre (keyword? table)}
  (jdbc/query spec [(str "select * from " (name table))]))

(defn select-*-desc
  "Select * from table order by id desc"
  [table]
  {:pre (keyword? table)}
  (jdbc/query spec [(str "select * from " (name table)  " order by id desc")]))

(defn select-id
  "Select * from table where id = ?"
  [table id]
  {:pre (keyword? table)}
  (jdbc/query spec [(str "select * from " (name table) " where id = ?") id]))

(defn drop-table
  [table]
  (jdbc/db-do-commands
   spec
   (jdbc/drop-table-ddl table)))

(defn insert-row! [table item]
  (jdbc/insert! spec table item))

(defn update-row! [table id item]
  (jdbc/update!
   spec table
   item ["id = ? " id]))

(defn delete-row! [table id]
  (jdbc/delete! spec table ["id = ?" id]))
