(ns mysite.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to]]
            [markdown.core :as markdown])
  (:use hiccup.core))

(defn- side-menu-heading [text]
  [:li.pure-menu-heading text])

(defn- side-menu-item [text link]
  [:li [:a {:href link} text]])

(defn head
  "Head Tags, such as title, js, css, meta"
  []
  [:head
   [:title "Sherman Pay's Website"]
   (include-css "http://yui.yahooapis.com/pure/0.5.0/pure-min.css"
                "http://fonts.googleapis.com/css?family=Roboto"
                "/css/common.css"
                "/css/sh/shCore.css"
                "/css/sh/shThemeDjango.css"
                "/css/sh/shCoreDjango.css"
                "/css/sh/shClojureExtra.css")
   (include-js "/js/main.js"
               "/js/sh/shCore.js"
               "/js/sh/shBrushJava.js"
               "/js/sh/shBrushClojure.js"
               "js/sh/shBrushPython.js")
   [:link {:rel "icon", :type "image/ico", :href "/img/icons/favicon.ico"}]
   [:meta {:http-equiv "Content-Type" :content "text/html;charset=utf-16"}]])

(defn foot
  "Footer at bottom of page"
  []
  [:div#mainFoot
   [:div "Copyright © 2014. Sherman Pay Jing Hao."]])

(defn common-body
  "Main content body"
  [title subtitle & body]
  [:body
     [:div#general
      [:a {:id "menuLink", :class "menu-link", :href "#"} [:span]]

      [:div#sideMenu
       [:div {:id "sideMenuTop", :class "pure-menu pure-menu-open"}
       [:div#home
        [:a {:href "/"} [:h2 "白"]]]
        [:hr]
        [:ul#sideMenuItem
         (side-menu-item "Blog" "/blog")
         (side-menu-item "Projects" "/projects")
         (side-menu-item "About Me" "/about")]]

       [:div {:id "sideMenuContact" :class ""}
        [:hr]
        [:ul#contact
         [:li.contact [:a {:href "mailto:shermanpay1991@gmail.com"} "Gmail"]]
         [:li.contact [:a {:href "https://github.com/shermpay"} "Github"]]
         [:li.contact [:a {:href "/resumes/Resume_2014.pdf"} "Résumé"]]]]]

      [:div#main
       [:div#mainHead
        [:div#headText
        [:span#title [:h1 title]]
         [:span#subtitle [:h2 subtitle]]]]
       [:hr]
       [:div#mainContent
        body]
       (foot)]]
   [:div#mask]
   [:script {:type "text/javascript"} "SyntaxHighlighter.all()"]])


(defn common
  "Common elements of page. Title is the main header text."
  [[:title title, :subtitle subtitle] & body]
  (html5
   [:html
    (head)
    (common-body title subtitle body)]))

(defn dev []
  (common
   [:div.content
    [:h2 "Current"]
    (unordered-list ["Document It"
                     "Quality Assurance"])]

   [:div.content
    [:h2 "Future"]
    (unordered-list ["Tags Coloring"])]))
