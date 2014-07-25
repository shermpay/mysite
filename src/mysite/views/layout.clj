(ns mysite.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]])
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
      (include-css "http://yui.yahooapis.com/pure/0.5.0/pure-min.css" "/css/index.css")
      (include-js "/js/main.js")
      [:link {:rel "icon", :type "image/ico", :href "/img/icons/favicon.ico"}]]

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
         [:span#title [:h1 "Sherman Pay"]]
         [:span#subtitle [:h2 "Blog | Portfolio"]]]
        [:hr]
        [:div#mainContent
          [:div.content 
           [:a {:href "#"} [:h2 "Hello World!" ]]
           [:p
            "This is the first official blog post on "
            [:a {:href "http://shermanpay.com"} "shermanpay.com"] ". "
            "I finally found time and motivation to do this. Feel free to browse around!"]]]]]]])))
(defn common [& body]
  (html5
    [:head
     [:title "Welcome to mysite"]
     (include-css "/css/screen.css")]
    [:body body]))
