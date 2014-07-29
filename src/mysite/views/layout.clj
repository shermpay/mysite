(ns mysite.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to]]
            [markdown.core :as markdown])
  (:use hiccup.core))

(defn- side-menu-heading [text]
  [:li.pure-menu-heading text])

(defn- side-menu-item [text link]
  [:li [:a {:href link} text]])

(defn common [[:title title, :subtitle subtitle] & body]
  (html5
   [:html
    [:head
     [:title "Sherman Pay's Website"]
     (include-css "http://yui.yahooapis.com/pure/0.5.0/pure-min.css"
                  "/css/index.css"
                  "/css/sh/shCore.css"
                  "/css/sh/shCoreEmacs.css"
                  "/css/sh/shThemeEmacs.css")
     (include-js "/js/main.js"
                 "/js/sh/shCore.js"
                 "/js/sh/shBrushJava.js")
     [:link {:rel "icon", :type "image/ico", :href "/img/icons/favicon.ico"}]]

    [:body
     [:div#general
      [:a {:id "menuLink", :class "menu-link", :href "#"} [:span]]

      [:div#sideMenu
       [:div {:id "sideMenuTop", :class "pure-menu pure-menu-open"}
       [:div#home
        [:a {:href "/"} [:h2 "(main)"]]]
        [:hr]
        [:ul#sideMenuItem
         (side-menu-heading "Blog")
         (side-menu-item "Archive" "/blog")
         (side-menu-heading "Projects")
         (side-menu-item "All" "/projects")
         (side-menu-heading "About Me")
         (side-menu-item "Technology" "#")
         (side-menu-item "Misc" "#")]]

       [:div {:id "sideMenuContact" :class "pure-menu pure-menu-open"}
        [:ul#contact
         [:li.contact "Gmail: " [:a {:href "mailto:shermanpay1991@gmail.com"} "shermanpay1991"]]
         [:li.contact "Github: " [:a {:href "https://github.com/shermpay"} "shermpay"]]]]]

      [:div#main
       [:div#mainHead
        [:div#headText
        [:span#title [:h1 title]]
         [:span#subtitle [:h2 subtitle]]]]
       [:hr]
       [:div#mainContent
        body]
       [:script {:type "text/javascript"} "SyntaxHighlighter.all()"]]]]]))

(defn home [latest-post]
  (common
   [:title "Sherman Pay"
    :subtitle "Home of my [Blog | Portfolio]"]
   [:h2 "Latest Post"]
   [:hr]
   ;; (content-card (:title latest-post)
   ;;             (markdown/md-to-html-string (:content latest-post))
   ;;             (:id latest-post)
   ;;             (:entry_date latest-post)
   ;;             :tags (:tags latest-post))
   (link-to "/blog" "More posts...")
   [:h2 "Latest Project"]
   [:hr]))

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
