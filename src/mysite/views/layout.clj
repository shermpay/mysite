(ns mysite.views.layout
  (:require [hiccup.page :refer [html5 include-css]])
  (:use hiccup.core))

(defn- side-menu-heading [text]
  [:li.pure-menu-heading text])

(defn- side-menu-item [text link]
  [:li [:a {:href link} text]])

(defn home []
  (let [wd (System/getProperty "user.dir")]
   (html
    [:html
     [:head
      [:title "Sherman Pay's Website"]
      [:link {:rel "stylesheet", :href "http://yui.yahooapis.com/pure/0.5.0/pure-min.css"}]
      [:link {:rel "stylesheet", :href "/css/index.css"}]
      [:link {:rel "icon", :type "image/ico", :href "/img/icons/favicon.ico"}]
      [:link {:rel "stylesheet", :href (str wd "/resources/public/css/index.css")}]
      [:link {:rel "icon", :type "image/ico", :href (str wd "/resources/public/img/icons/favicon.ico")}]]

     [:script {:src "/js/main.js" :type "text/javascript"}]
     [:body
      [:div#general
       [:a {:id "menuLink", :class "menu-link", :href "#"} [:span]]

       [:div#sideMenu
        [:div {:class "pure-menu pure-menu-open"}
         [:div#home
          [:a {:href "http:/shermanpay.com"} [:h2 "(main)"]]]
         [:hr]
         [:ul#sideMenuItems
          (side-menu-heading "Blog")
          (side-menu-item "Archive" "#")
          (side-menu-heading "Projects")
          (side-menu-item "All" "#")
          (side-menu-heading "About Me")
          (side-menu-item "Technology" "#")
          (side-menu-item "Misc" "#")]]

        [:div {:class "pure-menu pure-menu-open"}
         [:ul#contact
          [:li.contact "Gmail: " [:a {:href "mailto:shermanpay1991@gmail.com"} "shermanpay1991"]]
          [:li.contact "Github: " [:a {:href "https://github.com/shermpay"} "shermpay"]]]]]

       [:div#main
        [:div#mainHead
         [:span#title [:h1 "Home"]]
         [:span#subtitle [:h2 "Welcome to my site!"]]]
        [:hr]
        [:div#mainContent
         [:p ]]]]]])))
(defn common [& body]
  (html5
    [:head
     [:title "Welcome to mysite"]
     (include-css "/css/screen.css")]
    [:body body]))
