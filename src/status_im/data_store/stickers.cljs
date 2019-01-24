(ns status-im.data-store.stickers
  (:require [re-frame.core :as re-frame]
            [status-im.data-store.realm.core :as core]))

(re-frame/reg-cofx
 :data-store/sticker-packs
 (fn [cofx _]
   (assoc cofx :sticker-packs (map
                               #(update % :stickers vals)
                               (-> @core/account-realm
                                   (core/get-all :sticker-pack)
                                   (core/all-clj :sticker-pack))))))

(defn save-sticker-pack
  "Returns tx function for saving installed sticker pack"
  [pack]
  (fn [realm]
    (core/create realm :sticker-pack pack true)))