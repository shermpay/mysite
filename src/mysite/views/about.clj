(ns mysite.views.about
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to]]
            [hiccup.form :refer [form-to label password-field submit-button text-area
                                 text-field with-group hidden-field]]
            [hiccup.core :refer :all]
            [markdown.core :as markdown]
            [mysite.views.layout :as layout]
            [mysite.util :as util]))

(defn general []
  [:div.content
    [:h3 "Hi!"]
    [:p "My name is Sherman and I am a Computer Scientist currently
   studying in the University of Washington. I am very passionate
   about CS and I am always seeking opportunities to learn more about
   it. This site serves to share a bit about myself and my projects."]])

(defn tech []
  [:div.content
    [:h3 "Tech stuff"]
    [:p "I have deep interest in Programming Languages, Types and
    Compilers. My other interests are Android and backend stuff."]
    [:p "To find out more go to my "
     [:a {:href "#"} "Resume"] " and "
     [:a {:href "/projects"} "Projects Page"] "."]
    ;; [:div#proglang
    ;;  [:h4 "Programming Languages"]
    ;;  [:div#spectrum
    ;;   [:span#spectrum-left "Best"]
    ;;   (take 10 (repeat [:span.spectrum-rest {} "xx"]))
    ;;   [:span#spectrum-right "Worst"]]
    ;;  [:hr#spectrum-line]
    ;;  [:div#pl
    ;;   [:span.tag [:span#java "Java"]]
    ;;   [:span.tag [:span#clojure [:span#clo "Clo"] [:span#jure "jure"]]]
    ;;   [:span.tag [:span#python [:span#pyt "Pyt"] [:span#hon "hon"]]]]]
    ;; [:div.frameworks
    ;;  [:h4 "Frameworks"]
    ;;  [:span.android "Android"]]
    ;; [:div.tools
    ;;  [:h4 "Tools"]
    ;;  [:div.linux "Linux"]
    ;;  [:div.emacs "Emacs"]
    ;;  [:div.aws "AWS"]]
    ])

(defn about []
  (layout/common
   [:title "About me"
    :subtitle "Sherman Pay"]
   (include-css "css/about.css")
   (general)
   (tech)
   ))
