(ns status-im.ui.screens.stickers.events
  (:require [re-frame.core :as re-frame]
            [status-im.data-store.stickers :as stickers]
            [status-im.ui.screens.wallet.db :as wallet-db]
            [status-im.ui.screens.navigation :as navigation]
            [status-im.utils.handlers :as handlers]
            [status-im.chat.models :as chat-model]
            [status-im.chat.commands.sending :as commands-sending]
            [status-im.utils.fx :as fx]
            [status-im.utils.money :as money]
            [cljs.reader :as edn]))

(handlers/register-handler-fx
 :stickers/load-sticker-pack-success
 (fn [{:keys [db]} [_ {{:keys [id] :as pack} 'meta}]]
   {:db (-> db (assoc-in [:stickers/packs id] pack))}))

(handlers/register-handler-fx
 :stickers/install-pack
 (fn [{:keys [db]} [_ id]]
   (let [pack (get-in db [:stickers/packs id])]
     {:db (-> db
              (assoc-in [:stickers/packs-installed id] pack)
              (assoc :stickers/selected-pack id))
      :data-store/tx [(stickers/save-sticker-pack pack)]})))

(handlers/register-handler-fx
 :stickers/load-packs
 (fn [{:keys [db]} _]
   {;;TODO request list of packs from contract
    :http-get-n (mapv (fn [uri] {:url                   uri
                                 :success-event-creator (fn [o]
                                                          [:stickers/load-sticker-pack-success (edn/read-string o)])
                                 :failure-event-creator (fn [o] nil)})
                      ["https://ipfs.infura.io/ipfs/QmeQGsEGAKNPyQRu4N1Byuk2dSmaAz4YKSC1xGcrRBnhbz/"
                       "https://ipfs.infura.io/ipfs/QmdJ5Z4gBkBjqJUxPnZc9zyWeys8g5EYsrjstbT7eVcC1g/"])}))

;; TODO IMPLEMENT WITH CONTRACT
#_((handlers/register-handler-fx
    :stickers/list
    (fn [{:keys [db]} [_]]
      {:db (-> db (assoc-in [:wallet :request-transaction :symbol] symbol))}))

   (handlers/register-handler-fx
    :stickers/list-owned
    (fn [{:keys [db]} [_ address]]
      {:db (-> db (assoc-in [:wallet :request-transaction :symbol] symbol))}))

   (handlers/register-handler-fx
    :stickers/register
    (fn [{:keys [db]} [_ pack]]
      {:db (-> db (assoc-in [:wallet :request-transaction :symbol] symbol))}))

   (handlers/register-handler-fx
    :stickers/unregister
    (fn [{:keys [db]} [_ id]]
      {:db (-> db (assoc-in [:wallet :request-transaction :symbol] symbol))}))

   (handlers/register-handler-fx
    :stickers/buy
    (fn [{:keys [db]} [_ id]]
      {:db (-> db (assoc-in [:wallet :request-transaction :symbol] symbol))})))
