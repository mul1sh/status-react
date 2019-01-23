(ns status-im.ui.screens.stickers.events
  (:require [re-frame.core :as re-frame]
            [status-im.ui.screens.wallet.db :as wallet-db]
            [status-im.ui.screens.navigation :as navigation]
            [status-im.utils.handlers :as handlers]
            [status-im.chat.models :as chat-model]
            [status-im.chat.commands.sending :as commands-sending]
            [status-im.utils.fx :as fx]
            [status-im.utils.money :as money]))

(handlers/register-handler-fx
 :stickers/load-sticker-pack-success
 (fn [{:keys [db]} [_ {{:keys [id] :as pack} 'meta}]]
   {:db (-> db (assoc-in [:stickers/packs id] pack))}))

(handlers/register-handler-fx
 :stickers/install-pack
 (fn [{:keys [db]} [_ id]]
   {:db (-> db
            (assoc-in [:stickers/packs-installed id] (get-in db [:stickers/packs id]))
            (assoc :stickers/selected-pack id))}))

(handlers/register-handler-fx
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
   {:db (-> db (assoc-in [:wallet :request-transaction :symbol] symbol))}))
