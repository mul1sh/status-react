(ns status-im.ui.screens.chat.stickers.events
  (:require [status-im.utils.handlers :as handlers]))

(handlers/register-handler-fx
 :stickers/select-pack
 (fn [{:keys [db]} [_ id]]
   {:db (-> db (assoc-in [:stickers :selected-pack] id))}))
