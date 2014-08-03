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

(defn home [blog-post]
  (layout/common
   [:title "Home"
    :subtitle "of my [Blog | Portfolio]"]
   ;; (include-css "css/content.css")
   [:h2 "Latest Post"]
   [:hr]
   (if (seq blog-post)
    (blog/blog-card (:title blog-post)
                    (markdown/md-to-html-string (:content blog-post))
                    (:id blog-post)
                    (:entry_date blog-post)
                    :tags (:tags blog-post)))
   (link-to "/blog" "More posts...")
   [:h2 "About Me"]
   [:hr]
   ;; (include-css "css/about.css")
   ;; (include-css "css/common.css")
   (about/general)
   (about/tech)))
