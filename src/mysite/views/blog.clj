(ns mysite.views.blog
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to]]
            [hiccup.form :refer [form-to label password-field submit-button text-area
                                 text-field with-group hidden-field]]
            [hiccup.core :refer :all]
            [markdown.core :as markdown]

            [mysite.models :as model]
            [mysite.views.layout :as layout]))

(def show-count 5)

(defn blog-card [header content id date & {tags :tags edited :edited}]
  [:div.content
   [:div.content-header
    [:h1 header]
    [:div.meta-data
     [:p
      [:span.post-id "#" id " posted on "]
      [:span.entry-date  date " "]
      [:span.tags "Tags: " tags]]]]
   [:hr]
   [:p (markdown/md-to-html-string content)]])

(defn view [& blog-posts]
  (layout/common
   [:title "Blog"
    :subtitle "Code blabbering"]
   (map (fn [post] (blog-card (:title post)
                              (:content post)
                              (:id post)
                              (:entry_date post)
                              :tags (:tags post)))
        blog-posts)))

(defn form [{id :id, blog-title :title, blog-content :content, tags :tags}]
  (form-to
   {:id "blog-post" :class "pure-form pure-form-stacked"} [:post "/blog/post"]
   [:div {:class ""}
    [:div {:class ""}
     (hidden-field :id id)
     (label :blog-title "Blog Title: ") (text-field :blog-title blog-title)
     (label :blog-content "Blog Entry: ")
     (text-area {:rows "10" :cols "100"} :blog-content blog-content)
     [:p#word-count  " words"]
     (label :tags "Tags: ") (text-area {:cols "60"} :tags tags)]
    [:div {:class "pure-group", :style ""}
     (label :username "Credentials: ")
     (text-field {:placeholder "username"} :username)
     (password-field {:placeholder "password"} :password)]
    [:br]
    (submit-button {:class "pure-button pure-button-primary"} "Post!")]))

(defn edit [id]
  (layout/common
   [:title "Blog"
    :subtitle "Editing"]
   (let [post (model/select-id :blog id)]
    (form (first post)))))

(defn new []
  (layout/common
   [:title "Blog"
   :subtitle "New Post"]
   (form {})))
