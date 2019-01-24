(ns status-im.data-store.realm.schemas.account.sticker)

(def v1 {:name       :sticker
         :primaryKey :uri
         :properties {:uri :string}})