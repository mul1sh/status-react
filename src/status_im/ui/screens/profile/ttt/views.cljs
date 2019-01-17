(ns status-im.ui.screens.profile.ttt.views
  (:require-macros [status-im.utils.views :refer [defview letsubs]])
  (:require [status-im.ui.components.react :as react]
            [status-im.ui.components.status-bar.view :as status-bar]
            [status-im.ui.components.toolbar.view :as toolbar]
            [status-im.ui.components.toolbar.actions :as actions]
            [status-im.ui.components.colors :as colors]
            [status-im.react-native.js-dependencies :as js-dependencies]
            [status-im.react-native.resources :as resources]
            [status-im.ui.components.common.common :as components.common]
            [re-frame.core :as re-frame]
            [reagent.core :as reagent]
            [taoensso.timbre :as log]
            [status-im.ui.components.text-input.view :as text-input]
            [status-im.ui.components.icons.vector-icons :as icons]
            [status-im.ui.components.common.common :as components.common]
            [status-im.ui.components.common.styles :as components.common.styles]
            [clojure.string :as string]
            [status-im.utils.config :as config]
            [status-im.utils.utils :as utils]
            [status-im.ui.screens.profile.ttt.styles :as styles]
            [status-im.i18n :as i18n]
            [status-im.ui.components.styles :as common.styles]
            [status-im.utils.platform :as platform]))

(def steps-numbers
  {:intro          1
   :set-snt-amount 2
   :personalized-message 3
   :finish 3})

(defn step-back [step]
  (case step
    :intro (re-frame/dispatch [:navigate-back])
    :set-snt-amount (re-frame/dispatch [:set-in [:my-profile/tribute-to-talk :step] :intro])
    :personalized-message (re-frame/dispatch [:set-in [:my-profile/tribute-to-talk :step] :set-snt-amount])
    :finish (re-frame/dispatch [:set-in [:my-profile/tribute-to-talk :step] :personalized-message])))

(defn intro []
  [react/view {:style styles/intro-container}
   (when-not platform/desktop?
     [components.common/image-contain {:container-style styles/intro-image}
      (:ttt-logo resources/ui)])
   [react/i18n-text {:style styles/intro-text
                     :key   :tribute-to-talk}]
   [react/i18n-text {:style styles/description-label
                     :key   :tribute-to-talk-desc}]
   [components.common/button {:button-style styles/intro-button
                              :on-press     #(re-frame/dispatch [:set-in [:my-profile/tribute-to-talk :step] :set-snt-amount])

                              :label        (i18n/label :t/get-started)}]])

(defview snt-amount-label []
  (letsubs [snt-amount [:get-in [:my-profile/tribute-to-talk :snt-amount]]]
    [react/view {:style styles/snt-amount-container}
     [react/text {:style styles/snt-amount-label}
      [react/text {:style styles/snt-amount} snt-amount]
      " SNT"]]))

(defview number-view [n]
  (letsubs [snt-amount [:get-in [:my-profile/tribute-to-talk :snt-amount]]]
    (let [new-snt-amount (if (= n :remove)
                           (let [len (count snt-amount)
                                 s (subs snt-amount 0 (dec len))]
                             (if (string/ends-with? s ".")
                               (subs s 0 (- len 2))
                               s))
                           (cond (and (string/ends-with? snt-amount ".") (= n "."))
                                 snt-amount
                                 (and (= snt-amount "0") (not= n ".")) (str n)
                                 :else (str snt-amount n)))
          new-snt-amount (if (string/blank? new-snt-amount) "0" new-snt-amount)]
      [react/touchable-highlight
       {:on-press #(re-frame/dispatch [:set-in [:my-profile/tribute-to-talk :snt-amount]
                                       new-snt-amount])}
       [react/view {:style styles/number-container}
        (if (= n :remove)
          [icons/icon :icons/remove {:color colors/blue}]
          [react/text {:style styles/number} n])]])))

(defview number-row [elements]
  [react/view {:style {:flex-direction :row}}
   elements])

(defview number-pad []
  [react/view
   (seq (mapv
         (fn [elements]
           ^{:key elements} [number-row elements])
         (partition 3 (map (fn [n] ^{:key n} [number-view n]) (into (vec (range 1 10))
                                                                    ["." 0 :remove])))))])
(defview set-snt-amount []
  [react/view {:style styles/intro-container}
   [snt-amount-label]
   [number-pad]
   [react/i18n-text {:style styles/description-label
                     :key   :tribute-to-talk-set-snt-amount}]
   [components.common/button {:button-style styles/intro-button
                              :on-press     #(re-frame/dispatch [:set-in [:my-profile/tribute-to-talk :step] :personalized-message])

                              :label        (i18n/label :t/continue)}]])

(defview personalized-message []
  [react/view {:style styles/intro-container}
   [react/text {:style (assoc styles/description-label :color colors/black)} (i18n/label :t/personalized-message)
    [react/text {:style styles/description-label} (str " (" (i18n/label :t/optional) ")")]]
   [react/text-input {:style styles/personalized-message-input
                      :default-value (i18n/label :t/tribute-to-talk-message-placeholder)}]
   [react/text {:style styles/description-label}
    (i18n/label :t/tribute-to-talk-you-can-leave-a-message)]
   [components.common/button {:button-style styles/intro-button
                              :on-press     #(re-frame/dispatch [:set-in [:my-profile/tribute-to-talk :step] :finish])

                              :label        (i18n/label :t/tribute-to-talk-sign-and-set-tribute)}]])
(defview finish []
  (letsubs [amount [:get-in [:my-profile/tribute-to-talk :snt-amount]]]
    [react/view {:style (assoc styles/intro-container :margin-top 100)}
     [react/view {:style (styles/finish-circle colors/green-transparent-10 80)}
      [react/view {:style (styles/finish-circle colors/white 40)}
       [icons/icon :icons/check {:color colors/green
                                 :width 22
                                 :height 22}]]]
     [react/text {:style (assoc styles/finish-label
                                :margin-top 140)} (i18n/label :t/you-are-all-set)]
     [react/text {:style styles/description-label}
      (i18n/label :t/tribute-to-talk-finish-desc {:amount amount})]
     [components.common/button {:button-style styles/intro-button
                                :on-press     #(re-frame/dispatch [:navigate-back])
                                :label        (i18n/label :t/ok-got-it)}]]))

(defview tribute-to-talk []
  (letsubs [current-account [:account/account]
            {:keys [step first-word second-word error word]} [:my-profile/tribute-to-talk]]
    [react/keyboard-avoiding-view {:style styles/ttt-container}
     [status-bar/status-bar]
     [toolbar/toolbar
      nil
      (when-not (= :finish step)
        (toolbar/nav-button (actions/back #(step-back step))))
      [react/view
       [react/text {:style styles/tribute-to-talk}
        (i18n/label :t/tribute-to-talk)]
       [react/text {:style styles/step-n}
        (i18n/label :t/step-i-of-n {:step (steps-numbers step) :number 3})]]]
     [components.common/separator]
     (case step
       :intro [intro]
       :set-snt-amount [set-snt-amount]
       :personalized-message [personalized-message]
       :finish [finish])]))
