(ns mysite.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to image]]
            [markdown.core :as markdown])
  (:use hiccup.core))

(def ^:dynamic *glyph-path* "/img/glyphicons/png/glyphicons-")

(defn- side-menu-heading [text]
  [:li.pure-menu-heading text])

(defn- side-menu-item [text link & {:keys [glyph] :or {glyph "433-plus.png"}}]
  [:li
   [:a {:href link}
    [:span {:class "glyph"}
     (image {:width 14, :height 14} (str *glyph-path* glyph) "*")]
    text]])

(defn- side-menu-contact [text link & {:keys [glyph] :or {glyph "433-plus.png"}}]
  [:li.contact
   [:a {:href link}
    [:span {:class "glyph"}
     (image {:width 10, :height 10} (str *glyph-path* glyph) "*")]
    text]])

(defn head
  "Head Tags, such as title, js, css, meta"
  []
  [:head
   [:title "Sherman Pay's Website"]
   (include-css "http://yui.yahooapis.com/pure/0.5.0/pure-min.css"
                "/css/common.css"
                "/css/sh/shCore.css"
                "/css/sh/shThemeDjango.css"
                "/css/sh/shCoreDjango.css"
                "/css/sh/shClojureExtra.css")
   (include-js "/js/main.js"
               "/js/sh/shCore.js"
               "/js/sh/shBrushJava.js"
               "/js/sh/shBrushClojure.js"
               "/js/sh/shBrushPython.js"
               "/js/sh/shBrushBash.js"
               "/js/sh/shBrushPlain.js")
   [:link {:rel "stylesheet" :type "text/css"
           :href "http://fonts.googleapis.com/css?family=Roboto"}]
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
         (side-menu-item "Blog" "/blog" :glyph "331-blog.png")
         (side-menu-item "Projects" "/projects" :glyph "591-folder-heart.png")
         (side-menu-item "About Me" "/about" :glyph "4-user.png")]]

       [:div {:id "sideMenuContact" :class ""}
        [:hr]
        [:ul#contact
         (side-menu-contact "Gmail" "mailto:shermanpay1991@gmail.com"
                            :glyph "11-envelope.png")
         (side-menu-contact "Github" "https://github.com/shermpay"
                            :glyph "423-git-branch.png")
         (side-menu-contact "Résumé" "/resumes/Resume_2014.pdf" :glyph "30-notes-2.png")]]]

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
