(ns mysite.views.home
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to]]
            [hiccup.form :refer [form-to label password-field submit-button text-area
                                 text-field with-group hidden-field]]
            [hiccup.core :refer :all]
            [markdown.core :as markdown]

            [mysite.models :as model]
            [mysite.views.layout :as layout]
            [mysite.views.blog :as blog]
            [mysite.views.projects :as projects]
            [mysite.views.about :as about]
            [mysite.util :as util]))

(defn home [latest-post]
  (layout/common
   [:title "Home"
    :subtitle "of my [Blog | Portfolio]"]
   (include-css "css/content.css")
   [:h2 "Latest Post"]
   [:hr]
   (blog/blog-card (:title latest-post)
                   (markdown/md-to-html-string (:content latest-post))
                   (:id latest-post)
                   (:entry_date latest-post)
                   :tags (:tags latest-post))
   (link-to "/blog" "More posts...")
   [:h2 "About Me"]
   [:hr]
   (include-css "css/about.css")
   (include-css "css/common.css")
   (about/general)
   (about/tech)))
