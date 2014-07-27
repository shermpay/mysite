(ns mysite.views.blog
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to]]
            [hiccup.form :refer [form-to label password-field submit-button text-area
                                 text-field with-group]]
            [hiccup.core :refer :all]
            [markdown.core :as markdown]

            [mysite.views.layout :as layout]))

(defn view [& blog-posts]
  (layout/common
   [:title "Blog"
    :subtitle "Computers? LOL!"]
   (map (fn [post] (layout/content-card (:title post)
                                        (markdown/md-to-html-string (:content post))
                                        :tags (:tags post)))
        blog-posts))

  )

(defn new []
  (layout/common
   [:h2 "Editing "]
   (form-to
    {:id "blog-post" :class "pure-form pure-form-stacked"} [:post "/blog/post"]
    [:div {:class ""}
     [:div {:class ""}
      (label :blog-title "Blog Title: ") (text-field :blog-title)
      (label :blog-content "Blog Entry: ") (text-area {:rows "10" :cols "100"} :blog-content)
      [:p#word-count  " words"]
      (label :tags "Tags: ") (text-area {:cols "60"} :tags)]
     [:div {:class "pure-group", :style ""}
      (label :username "Credentials: ")
      (text-field {:placeholder "username"} :username)
      (password-field {:placeholder "password"} :password)]
     [:br]
     (submit-button {:class "pure-button pure-button-primary"} "Post!")])))
