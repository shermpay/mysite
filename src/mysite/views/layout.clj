(ns mysite.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to image]]
            [markdown.core :as markdown])
  (:use hiccup.core))

(def ^:const urls
  {:resume "/resumes/Resume_2014.pdf"
   :email  "mailto:shermanpay1991@gmail.com"
   :github "https://github.com/shermpay"
   :projects "/projects"
   :blog "/blog"
   :about "about"})

(def ^:dynamic *glyph-path* "/img/glyphicons/png/glyphicons-")
(def ^:dynamic *glyph-social-path* "/img/glyphicons_social/png/social-")

(defn- side-menu-heading [text]
  [:li.pure-menu-heading text])

(defn- side-menu-item [text link & {:keys [glyph] :or {glyph "433-plus.png"}}]
  [:li
   [:a {:href link}
    [:span {:class "glyph"}
     (image {:width 14, :height 14} (str *glyph-path* glyph) "*")]
    text]])

(defn- side-menu-contact [text link & {:keys [glyph] :or {glyph (str *glyph-path* "433-plus.png")}}]
  [:li.contact
   [:a {:href link}
    [:span {:class "glyph"}
     (image {:width 12, :height 12} glyph "*")]
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
   (include-js "/js/out/goog/base.js"
               "/js/main.js"
               "/js/out/mysite/core.js"
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

(defn ad-sense-wide []
;;   <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
;; <!-- Wide Colorful -->
;; <ins class="adsbygoogle"
;;      style="display:block"
;;      data-ad-client="ca-pub-2319654658963767"
;;      data-ad-slot="9830314730"
;;      data-ad-format="auto"></ins>
;; <script>
;; (adsbygoogle = window.adsbygoogle || []).push({});
  ;; </script>
  [:div
   [:script {:async true
            :src "//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"}]
   [:ins {:class "adsbygoogle"
          :style "display:block"
          :data-ad-client "ca-pub-2319654658963767"
          :data-ad-slot "9830314730"
          :data-ad-format "auto"}]
   [:script "(adsbygoogle = window.adsbygoogle || []).push({});"]])


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
         (side-menu-item "Blog" (urls :blog) :glyph "331-blog.png")
         (side-menu-item "Projects" (urls :projects) :glyph "591-folder-heart.png")
         (side-menu-item "About Me" (urls :about) :glyph "4-user.png")]]

       [:div {:id "sideMenuContact" :class ""}
        [:hr]
        [:ul#contact
         (side-menu-contact "Gmail" (urls :email)
                            :glyph (str *glyph-path* "11-envelope.png"))
         (side-menu-contact "Github" (urls :github) 
                            :glyph (str *glyph-social-path* "22-github.png"))
         (side-menu-contact "Résumé" (urls :resume) 
                            :glyph (str *glyph-path* "30-notes-2.png"))]]]

      [:div#main
       [:div#mainHead
        [:div#headText
        [:span#title [:h1 title]]
         [:span#subtitle [:h2 subtitle]]]]
       [:hr]
       [:div#mainContent
        body]
       (ad-sense-wide)
       (foot)]]
   [:div#mask]
   ;; [:script {:type "text/javascript"} "SyntaxHighlighter.all()"]
   ])

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
