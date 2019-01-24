(ns status-im.stickers.core
  (:require [status-im.utils.fx :as fx]
            [re-frame.core :as re-frame]
            [status-im.utils.http :as http]
            [cljs.reader :as edn]))

(fx/defn init-stickers-packs [{:keys [db sticker-packs]}]
  {:db (assoc db :stickers/packs-installed (into {} (map #(vector (:id %) %) sticker-packs)))})

;;TODO IMPLEMENT WITH CONTRACT
#_((defn list [cb])

   (defn list-owned [address cb])

   (defn buy [id cb])

   (defn register [pack cb])

   (defn unregister [pack cb]))