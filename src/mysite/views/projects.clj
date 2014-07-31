(ns mysite.views.projects
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to]]
            [hiccup.form :refer [form-to label password-field submit-button text-area
                                 text-field with-group hidden-field]]
            [hiccup.core :refer :all]
            [markdown.core :as markdown]

            [mysite.models :as model]
            [mysite.views.layout :as layout]
            [mysite.util :as util]))

(defn project-card [{:keys [name content id version start_date new_date docs source tags]}]
  [:div.content
   [:div.content-header
    [:h1 name]
    [:span [:h2 version] [:h3 " " (util/truncate-timestamp new_date :second)]]
    [:div.meta-data
     [:p
      [:span.project-id "#" id]
      [:span.project-date (util/truncate-timestamp start_date :second)]
      [:span.tags "Tags: " tags]]]
    [:hr]
    [:p (markdown/md-to-html-string content)]
    [:span
     [:a [:href docs] " Documentation" docs]
     [:a [:href source] "Source " source]]]])

(defn projects-common [header & body]
  (layout/common
   header
   (include-css "content.css")
   body))

(defn view [projects]
  (projects-common
   [:title "Projects"
    :subtitle "Code"]
   (println projects)
   (map (fn [proj] (project-card proj)) projects)))

(defn form [{:keys [id name content version tags docs source]}]
  (form-to
   {:id "projects-post" :class "pure-form pure-form-stacked"} [:post "/projects/post"]
   [:div
    [:div
     (hidden-field :id id)
     (label :project-name "Project Name: ") (text-field :project-name name)
     (label :project-version "Version: ") (text-field :project-version version)
     (label :project-content "Content: ")
     (text-area {:rows "10" :cols "100"} :project-content content)
     [:p#word-count " words"]
     (label :tags "Tags: ") (text-area {:cols "60"} :tags tags)]
    (label :docs "Documentation URL: ") (text-field :docs docs)
    (label :source "Source URL: ") (text-field :source source)
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

(defn edit [id]
  (projects-common
   [:title "Projects"
    :subtitle "(set! project)"]
   (form (first (model/select-id :projects id)))))
