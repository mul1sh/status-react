(ns status-im.ui.screens.stickers.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :stickers/packs
 (fn [db]
   [{:id "id1"
     :name "Sticker pack 1"
     :thumbnail "https://ipfs.infura.io/ipfs/QmUJP281f6UpNJSXpLgMnpxERPnrshxyrLPLpXMWAjyF77"
     :author "Andrey Shovkoplyas"
     :preview "https://ipfs.infura.io/ipfs/QmW7bpVNE3Kkkx2JWThCXzpJ1JZvnnXrgDqaWxNNRUpFxY/"
     :price 0 ;; 0 means free
     :stickers [{:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}]
     :owned? true}
    {:id "id2"
     :name "Sticker pack 2"
     :thumbnail "https://ipfs.infura.io/ipfs/QmUJP281f6UpNJSXpLgMnpxERPnrshxyrLPLpXMWAjyF77"
     :author "Andrey Shovkoplyas"
     :preview "https://ipfs.infura.io/ipfs/QmW7bpVNE3Kkkx2JWThCXzpJ1JZvnnXrgDqaWxNNRUpFxY/"
     :price 100 ;; 0 means free
     :stickers [{:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}]}
    {:id "id3"
     :name "Sticker pack 3"
     :thumbnail "https://ipfs.infura.io/ipfs/QmUJP281f6UpNJSXpLgMnpxERPnrshxyrLPLpXMWAjyF77"
     :author "Salvador Dalí"
     :preview "https://ipfs.infura.io/ipfs/QmW7bpVNE3Kkkx2JWThCXzpJ1JZvnnXrgDqaWxNNRUpFxY/"
     :price 10000 ;; 0 means free
     :stickers [{:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}]}
    {:id "id4"
     :name "Sticker pack 4"
     :thumbnail "https://ipfs.infura.io/ipfs/QmUJP281f6UpNJSXpLgMnpxERPnrshxyrLPLpXMWAjyF77"
     :author "Salvador Dalí"
     :preview "https://ipfs.infura.io/ipfs/QmW7bpVNE3Kkkx2JWThCXzpJ1JZvnnXrgDqaWxNNRUpFxY/"
     :price 2500 ;; 0 means free
     :stickers [{:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}
                {:uri "https://ipfs.infura.io/ipfs/QmbCtJC2JCVRLCWFMqs5xE47UFarjwcSwLsa85UKxEkQfm/"}]
     :owned? true}]))

(re-frame/reg-sub
 :stickers/owned-packs
 :<- [:stickers/packs]
 (fn [packs]
   (filter :owned? packs)))
