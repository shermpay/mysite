(ns mysite.core
  "Core cljs functions"
  ;; (:require [reagent.core :as reagent :refer [atom]]
  ;;           [ajax.core :refer [POST]])
  (:require [clojure.string :as string]))

(defn toggle-class
  "Toggle a class for a specific DOM element. (Toggle: Add|Remove)"
  [element name]
  (let [classes (.split (.-className element) #"\s+")
        length  (.-length classes)
        i 0]
    (do
      (if (some #{name} classes)
       (.splice classes (.indexOf classes name) 1)
       (.push classes name))

      (set! (.-className element) (string/join " " classes)))))

(defn init []
  (let [main (.getElementById js/document "main")
        side-menu (.getElementById js/document "sideMenu")
        menu-link (.getElementById js/document "menuLink")
        mask (.getElementById js/document "mask")]
    (set! (.-onclick menu-link)
          (fn [e]
            (toggle-class main "active")
            (toggle-class side-menu "active")
            (toggle-class menu-link "active")
            (toggle-class mask "active")))))

(set! (.-onload js/window) init)
