(ns mysite.models.model
  (:require [clojure.java.jdbc :as jdbc]
            [mysite.config :as config]))


(def spec (:database-spec config/*credentials*))

(def table-specs {:blog [[:id :int :primary :key :auto_increment]
                         [:title "varchar(64)"]
                         [:entry_date :datetime]
                         [:content :text]
                         [:tags "varchar(128)"]]

                  })

(defn check-table [table]
  {:pre (keyword? table)}
  (let [tables (jdbc/query spec [(str "show tables like '" (name table) "'")])]
    (= 1 (count tables))))

(defn create-table [table [table-spec]]
  (jdbc/db-do-commands
   spec
   (jdbc/create-table-ddl table
                          table-spec)))

(defn check-create-table [table]
  (jdbc/with-db-connection [_ spec]
    (if (not (check-table table))
      (create-create table (get table-specs table)))))

(defn select-* [table]
  {:pre (keyword? table)}
  (jdbc/query spec [(str "select * from " (name table))]))

(defn select-*-desc [table]
  {:pre (keyword? table)}
  (jdbc/query spec [(str "select * from " (name table)  " order by id desc")]))

(defn select-id [table id]
  {:pre (keyword? table)}
  (jdbc/query spec [(str "select * from " (name table) " where id = ?") id]))

(defn drop-table [table]
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
