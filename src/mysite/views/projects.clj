(ns mysite.views.projects
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to]]
            [hiccup.form :refer [form-to label password-field submit-button text-area
                                 text-field with-group hidden-field]]
            [hiccup.core :refer :all]
            [markdown.core :as markdown]

            [mysite.models :as model]
            [mysite.views.layout :as layout]))

(defn project-card [{:keys [name content id version date docs source tags]}]
  [:div.content
   [:div.content-header
    [:h1 name]
    [:h2 "Ver: " version]
    [:div.meta-data
     [:p
      [:span.project-id "#" id]
      [:span.project-date date]
      [:span.tags "Tags: " tags]]]
    [:hr]
    [:p (markdown/md-to-html-string content)]
    [:span
     [:a [:href source] "Source "]
     [:a [:href docs] " Documentation"]]]])

(defn projects-common [header & body]
  (layout/common
   header
   (include-css "content.css")
   body))

(defn view [& projects]
  (projects-common
   [:title "Projects"
    :subtitle "Code"]
   (map (fn [proj] (project-card proj)) projects)))

(defn form [{:keys [id project-name project-content project-version tags]}]
  (form-to
   {:id "projects-post" :class "pure-form pure-form-stacked"} [:post "/projects/post"]
   [:div
    [:div
     (hidden-field :id id)
     (label :project-name "Project Name: ") (text-field :project-name project-name)
     (label :project-version "Version: ") (text-field :project-version project-version)
     (label :project-content "Content: ")
     (text-area {:rows "10" :cols "100"} :project-content project-content)
     [:p#word-count " words"]
     (label :tags "Tags: ") (text-area {:cols "60"} :tags tags)]
    (label :docs "Documentation URL: ") (text-field :docs )
    [:div {:class "pure-group"}
     (label :username "Credentials")
     (text-field {:placeholder "username"} :username)
     (password-field {:placeholder "password"} :password)]
    [:br]
    (submit-button {:class "pure-button pure-button-primary"} "Post!")]))

(defn new []
  (projects-common
   [:title "Projects"
    :subtitle "defproject"]
   (form {})))
