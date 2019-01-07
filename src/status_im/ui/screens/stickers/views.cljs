(ns status-im.ui.screens.stickers.views
  (:require-macros [status-im.utils.views :refer [defview letsubs]])
  (:require [re-frame.core :as re-frame]
            [status-im.i18n :as i18n]
            [status-im.ui.components.react :as react]
            [status-im.ui.screens.stickers.styles :as styles]
            [status-im.ui.components.status-bar.view :as status-bar]
            [status-im.ui.components.styles :as components.styles]
            [status-im.ui.components.toolbar.view :as toolbar]
            [status-im.ui.components.colors :as colors]
            [status-im.ui.components.icons.vector-icons :as icons]
            [status-im.utils.money :as money]))

(def thumbnail-icon-size 40)

(defn- thumbnail-icon [uri]
  [react/image {:style  {:width thumbnail-icon-size :height thumbnail-icon-size :border-radius (/ thumbnail-icon-size 2)}
                :source {:uri uri}}])

(defview price-badge [price]
  (letsubs [balance [:balance]]
    (let [snt             (money/wei-> :eth (:SNT balance))
          not-enough-snt? (> price snt)
          no-snt?         (nil? snt)]
      [react/view {:background-color (if not-enough-snt? colors/gray colors/blue)
                   :border-radius 14 :flex-direction :row :padding-horizontal 8 :height 28 :align-items :center}
       [icons/icon :icons/logo {:color colors/white :width 12 :height 12}]
       [react/text {:style {:margin-left 8 :font-size 15 :color colors/white}}
        (cond no-snt?
              "Buy with SNT"
              (zero? price)
              "Free"
              :else
              (str price))]])))

(defn pack-badge [{:keys [name author price thumbnail preview] :as pack}]
  [react/view {:margin-bottom 27}
   [react/touchable-highlight {:on-press #(re-frame/dispatch [:navigate-to :stickers-pack pack])}
    [react/image {:style {:height 200 :border-radius 20} :source {:uri preview}}]]
   [react/view {:height 64 :align-items :center :flex-direction :row}
    [thumbnail-icon thumbnail]
    [react/view {:padding-horizontal 16 :flex 1}
     [react/text {:style {:font-size 15}} name]
     [react/text {:style {:font-size 15 :color colors/gray :margin-top 6}} author]]
    [price-badge price]]])

(defview packs []
  (letsubs [packs [:stickers/packs]]
    [react/view styles/screen
     [status-bar/status-bar]
     [react/keyboard-avoiding-view components.styles/flex
      [toolbar/simple-toolbar "Sticker market"]
      [react/view {:style {:padding-top 8 :flex 1}}
       [react/scroll-view {:keyboard-should-persist-taps :handled :style {:flex 1 :padding 16}}
        [react/view
         (for [pack packs]
           ^{:key pack}
           [pack-badge pack])]]]]]))

(def sticker-icon-size 60)

(defview pack []
  (letsubs [{:keys [name author price thumbnail stickers]} [:get-screen-params]]
    [react/view styles/screen
     [status-bar/status-bar]
     [react/keyboard-avoiding-view components.styles/flex
      [toolbar/simple-toolbar]
      [react/view {:height 94 :align-items :center :flex-direction :row :padding-horizontal 16}
       [thumbnail-icon thumbnail]
       [react/view {:padding-horizontal 16 :flex 1}
        [react/text {:style {:font-size 22 :font-weight :bold}} name]
        [react/text {:style {:font-size 15 :color colors/gray :margin-top 6}} author]]
       [price-badge price]]
      [react/view {:style {:padding-top 8 :flex 1}}
       [react/scroll-view {:keyboard-should-persist-taps :handled :style {:flex 1}}
        [react/view {:flex-direction :row :flex-wrap :wrap}
         (for [{:keys [uri]} stickers]
           ^{:key uri}
           [react/image {:style  {:margin 16 :width sticker-icon-size :height sticker-icon-size :border-radius (/ sticker-icon-size 2)}
                         :source {:uri uri}}])]]]]]))
