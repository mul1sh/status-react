(ns status-im.data-store.realm.schemas.account.sticker-pack)

(def v1 {:name       :sticker-pack
         :primaryKey :id
         :properties {:id        :string
                      :name      :string
                      :thumbnail :string
                      :author    :string
                      :preview   :string
                      :price     {:type    :int
                                  :default 0}
                      :stickers  {:type       :list
                                  :objectType :sticker}}})