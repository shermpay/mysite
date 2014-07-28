(ns mysite.models.model
  (:require [clojure.java.jdbc :as jdbc]
            [mysite.config :as config]))


(def spec (:database-spec config/*credentials*))

(defn check-table [table]
  (let [tables (jdbc/query spec [(str "show tables like '" table "'")])]
    (= 1 (count tables))))

(defn create-table [name & specs]
  (jdbc/db-do-commands
   spec
   (jdbc/create-table-ddl name
                          specs)))

(defn check-create-table [check-fn create-fn]
  (jdbc/with-db-connection [_ spec]
    (if (not (check-fn))
      (create-fn))))

(defn select-* [table]
  (jdbc/query spec [(str "select * from " table)]))

(defn select-id [table id]
  (jdbc/query spec [(str "select * from " table " where id = ?") id]))

(defn drop-table [table]
  (jdbc/db-do-commands
   spec
   (jdbc/drop-table-ddl table)))

(defn create-post [blog-post]
  (jdbc/insert!
   spec :blog
   blog-post))

(defn update-post [id blog-post]
  (jdbc/update!
   spec :blog
   blog-post ["id = ? " id]))

(defn delete-post [id]
  (jdbc/delete! spec :blog ["id = ?" id]))
