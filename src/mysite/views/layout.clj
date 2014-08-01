(ns mysite.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to]]
            [markdown.core :as markdown])
  (:use hiccup.core))

(defn- side-menu-heading [text]
  [:li.pure-menu-heading text])

(defn- side-menu-item [text link]
  [:li [:a {:href link} text]])

(defn head []
  [:head
     [:title "Sherman Pay's Website"]
     (include-css "http://yui.yahooapis.com/pure/0.5.0/pure-min.css"
                  "/css/common.css"
                  "/css/sh/shCore.css"
                  "/css/sh/shCoreEmacs.css"
                  "/css/sh/shThemeEmacs.css")
     (include-js "/js/main.js"
                 "/js/sh/shCore.js"
                 "/js/sh/shBrushJava.js")
     [:link {:rel "icon", :type "image/ico", :href "/img/icons/favicon.ico"}]])

(defn foot []
  [:div#mainFoot
   [:div "Copyright © 2014. Sherman Pay Jing Hao."]])

(defn common-body [title subtitle & body]
  [:body
     [:div#general
      [:a {:id "menuLink", :class "menu-link", :href "#"} [:span]]

      [:div#sideMenu
       [:div {:id "sideMenuTop", :class "pure-menu pure-menu-open"}
       [:div#home
        [:a {:href "/"} [:h2 "(main)"]]]
        [:hr]
        [:ul#sideMenuItem
         (side-menu-item "Blog" "/blog")
         (side-menu-item "Projects" "/projects")
         (side-menu-item "About Me" "/about")]]

       [:div {:id "sideMenuContact" :class "pure-menu pure-menu-open"}
        [:hr]
        [:ul#contact
         [:li.contact [:a {:href "mailto:shermanpay1991@gmail.com"} "Gmail"]]
         [:li.contact [:a {:href "https://github.com/shermpay"} "Github"]]
         [:li.contact [:a {:href "misc/resume.pdf"} "Résumé"]]]]]

      [:div#main
       [:div#mainHead
        [:div#headText
        [:span#title [:h1 title]]
         [:span#subtitle [:h2 subtitle]]]]
       [:hr]
       [:div#mainContent
        body]
       (foot)
       [:script {:type "text/javascript"} "SyntaxHighlighter.all()"]]]])


(defn common [[:title title, :subtitle subtitle] & body]
  (html5
   [:html
    (head)
    (common-body title subtitle body)]))

(defn dev []
  (common
   [:div.content
    [:h2 "Beta"]
    (unordered-list ["Simple UI Design."
                     "MySQL database to store Blog Posts."
                     "Blog pulled directly from DB"
                     "Blog post written in markdown. (Use markdown-clj)"])]
   [:div.content
    [:h2 "Current"]
    (unordered-list ["Project hosting"
                     "Project database"])]

   [:div.content
    [:h2 "Future"]
    (unordered-list ["Tags Coloring"])]))
