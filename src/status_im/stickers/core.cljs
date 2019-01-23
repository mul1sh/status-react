(ns status-im.stickers.core
  (:require [status-im.utils.fx :as fx]
            [re-frame.core :as re-frame]
            [status-im.utils.http :as http]
            [cljs.reader :as edn]))

(defn list [cb])

(defn list-owned [address cb])

(defn buy [id cb])

(defn register [pack cb])

(defn unregister [pack cb])

(fx/defn load-stickers-packs [cofx]
  ;;TODO request list of packs from contract
  {:http-get {:url
              "https://ipfs.infura.io/ipfs/QmeQGsEGAKNPyQRu4N1Byuk2dSmaAz4YKSC1xGcrRBnhbz/"
              :success-event-creator
              (fn [o]
                [:stickers/load-sticker-pack-success (edn/read-string o)])
              :failure-event-creator (fn [o] nil)}})