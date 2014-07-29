(ns mysite.views.projects
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [hiccup.element :refer [unordered-list ordered-list link-to]]
            [hiccup.form :refer [form-to label password-field submit-button text-area
                                 text-field with-group hidden-field]]
            [hiccup.core :refer :all]
            [markdown.core :as markdown]

            [mysite.models :as model]
            [mysite.views.layout :as layout]))

(defn project-card [{:keys [name content id version date docs github tags]}]
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
     [:a [:href github] "Github "]
     [:a [:href docs] " Documentation"]]]])

(defn view [& projects]
  (layout/common
   [:title "Projects"
    :subtitle "Code"]
   (map (fn [proj] (project-card proj)) projects)))
