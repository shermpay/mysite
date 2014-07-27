(ns mysite.core
  ;; (:require [reagent.core :as reagent :refer [atom]]
  ;;           [ajax.core :refer [POST]])
  (:require [clojure.string :as string]))

(defn toggle-class [element name]
  (let [classes (.split (.-className element) #"\s+")
        length  (.-length classes)
        i 0]
    (do
      (if (some #{name} classes)
       (.splice classes (.indexOf classes name) 1)
       (.push classes name))

      (set! (.-className element) (string/join " " classes)))
    
    (.log js/console classes)))

(defn init []
  (let [general (.getElementById js/document "general")
        side-menu (.getElementById js/document "sideMenu")
        menu-link (.getElementById js/document "menuLink")]
    (set! (.-onclick menu-link)
          (fn [e]
            (toggle-class general "active")
            (toggle-class side-menu "active")
            (toggle-class menu-link "active")))))


(set! (.-onload js/window) init)
