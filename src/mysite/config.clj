(ns mysite.config
  "Used for manipulating a private configuration file that contains
  informaiton to web site"
  [:require [clojure.edn]])

(def ^:dynamic *credentials-path* "private/config.edn")

(def ^:dynamic *credentials*
  (-> *credentials-path*
      (clojure.java.io/resource)
      (slurp)
      (clojure.edn/read-string)))

(defn check-credentials
  [cred-type validation-fn]
  "Check a particular credential-type in *credentials* with a validation-fn"
  (validation-fn (cred-type *credentials*)))

(defn user-validation
  [user username password]
  (let [details (user (:users *credentials*))]
    (and (= (:username details) username)
         (= (:password details) password))))
