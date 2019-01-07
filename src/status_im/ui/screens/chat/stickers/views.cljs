(ns status-im.ui.screens.chat.stickers.views
  (:require-macros [status-im.utils.views :refer [defview letsubs]])
  (:require [re-frame.core :as re-frame]
            [status-im.ui.components.react :as react]
            [status-im.ui.components.icons.vector-icons :as vector-icons]
            [status-im.ui.components.colors :as colors]))

(defn- no-stickers-yet-panel []
  [react/view {:style {:flex 1  :align-items :center :justify-content :center}}
   [vector-icons/icon :icons/stickers-big {:color colors/gray}]
   [react/text {:style {:margin-top 8 :font-size 17}} "You don’t have any stickers yet"]
   [react/touchable-highlight {:on-press #(re-frame/dispatch [:navigate-to :stickers])}
    [react/text {:style {:margin-top 17 :font-size 15 :color colors/blue}} "Get Stickers"]]])

(defn- recent-stickers-panel []
  [react/view {:style {:flex 1 :align-items :center :justify-content :center}}
   [vector-icons/icon :icons/stickers-big {:color colors/gray}]
   [react/text {:style {:margin-top 8 :font-size 17}} "Recently used stickers will appear here"]])

(defn- on-sticker-click [uri]
  (re-frame/dispatch [:chat.ui/set-chat-ui-props {:show-stickers? false}])
  (re-frame/dispatch [:chat/send-sticker uri])
  (react/dismiss-keyboard!))

(defn- stickers-panel [{:keys [stickers]}]
  [react/scroll-view {:style {:flex 1} :condtent-container-style {:flex 1}}
   [react/view {:style {:flex 1 :margin 5 :flex-direction :row :justify-content :flex-start :flex-wrap :wrap}}
    (for [{:keys [uri]} stickers]
      ^{:key uri}
      [react/touchable-highlight {:style    {:height 75 :width 75 :margin 5}
                                  :on-press #(on-sticker-click uri)}
       [react/image {:style {:resize-mode :cover :width "100%" :height "100%"} :source {:uri uri}}]])]])

(def icon-size 28)

(defn pack-icon [{:keys [id on-press selected? background-color] :or {background-color colors/gray
                                                                      on-press #(re-frame/dispatch [:stickers/select-pack id])}} icon]
  [react/touchable-highlight {:on-press on-press}
   [react/view {:style {:align-items :center}}
    [react/view {:style {:background-color background-color :margin 5 :height icon-size :width icon-size
                         :border-radius (/ icon-size 2) :align-items :center :justify-content :center}}
     icon]
    (when selected?
      [react/view {:style {:margin-bottom 5 :height 2 :width 16 :background-color colors/blue}}])]])

;; TODO handle first use ('no stickers yet screen', 'say more with stickers' panel)

(defn pack-for [packs id]
  (some #(when (= id (:id %)) %) packs))

(defview stickers-view []
  (letsubs [selected-pack [:stickers/selected-pack]
            owned-packs   [:stickers/owned-packs]]
    [react/view {:style {:background-color :white :height "40%"}}
     (case selected-pack
       :recent [recent-stickers-panel]
       [stickers-panel (pack-for owned-packs selected-pack)])
     [react/view {:style {:flex-direction :row :padding-horizontal 4}} ;; TODO make scrollable
      [pack-icon {:on-press #(re-frame/dispatch [:navigate-to :stickers]) :selected? false :background-color colors/blue}
       [vector-icons/icon :icons/add {:width 20 :height 20 :color colors/white}]]
      [pack-icon {:id :recent :selected? (= :recent selected-pack)}
       [vector-icons/icon :icons/clock]]
      (for [{:keys [id thumbnail]} owned-packs]
        ^{:key id}
        [pack-icon {:id id :selected? (= id selected-pack)}
         [react/image {:style {:width icon-size :height icon-size :border-radius (/ icon-size 2)} :source {:uri thumbnail}}]])]]))
