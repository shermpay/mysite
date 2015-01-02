(ns mysite.views.blog
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to image]]
            [hiccup.form :refer [form-to label password-field submit-button text-area
                                 text-field with-group hidden-field]]
            [hiccup.core :refer :all]
            [markdown.core :as markdown]

            [mysite.models :as model]
            [mysite.views.layout :as layout]
            [mysite.util :as util]))

(def show-count 5)

(defn blog-common [header & body]
  (layout/common
   header
   (include-css "css/content.css")
   body))


(def color-mapping {:red "#f44336"
                    :pink "#e91e63"
                    :purple "#9c27b0"
                    :indigo "#3f51bf"
                    :blue "#2196f3"})

(defn blog-card [header content id date & {tags :tags edited :edited}]
  [:div.blog
   [:div.blog-title
    [:h1 header]
    [:div.blog-meta
     [:p
      [:span.post-id "#" id " posted on "]
      [:span.entry-date  (util/truncate-timestamp date :second) " "]
      (if edited [:span.edited-date "edited on "
                  (util/truncate-timestamp edited :second)])

      [:span.tags
       (map #(vector :span.tag (clojure.string/trim %))
            (clojure.string/split tags #","))
       [:span.tag-glyph (image {:width 16, :height 16 :class "glyph"}
                               (str layout/*glyph-path* "67-tags.png"))]]]]]
   [:hr]
   [:p (markdown/md-to-html-string content)]])

(defn view [& blog-posts]
  (blog-common
   [:title "Blog"
    :subtitle "Code blabbering"]
   (map (fn [post] (blog-card (:title post)
                              (:content post)
                              (:id post)
                              (:entry_date post)
                              :edited (:edited post)
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
     (text-area {:rows "20" :cols "100"} :blog-content blog-content)
     [:p#word-count  " words"]
     (label :tags "Tags: ") (text-area {:cols "60"} :tags tags)]
    [:div {:class "pure-group", :style ""}
     (label :username "Credentials: ")
     (text-field {:placeholder "username"} :username)
     (password-field {:placeholder "password"} :password)]
    [:br]
    (submit-button {:class "pure-button pure-button-primary"} "Post!")]))

(defn edit [id]
  (blog-common
   [:title "Blog"
    :subtitle "Editing"]
   (let [post (model/select-id :blog id)]
    (form (first post)))))

(defn new []
  (blog-common
   [:title "Blog"
   :subtitle "New Post"]
   (form {})))
