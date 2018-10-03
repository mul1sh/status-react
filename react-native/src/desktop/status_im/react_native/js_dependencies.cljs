(ns status-im.react-native.js-dependencies)

(def config                 (js/require "react-native-config"))
(def fs                     (js/require "react-native-fs"))
(def http-bridge            (js/require "react-native-http-bridge"))
(def keychain               (js/require "react-native-keychain"))
(def qr-code                (js/require "react-native-qrcode"))
(def react-native           (js/require "react-native"))
(def realm                  (js/require "realm"))
(def webview-bridge         (js/require "react-native-webview-bridge"))
(def secure-random          (.-generateSecureRandom (js/require "react-native-securerandom")))
(def EventEmmiter           (js/require "react-native/Libraries/vendor/emitter/EventEmitter"))
(def fetch                  (.-default (js/require "react-native-fetch-polyfill")))
(def i18n                   (js/require "react-native-i18n"))
(def desktop-linking        (.-DesktopLinking (.-NativeModules react-native)))

(def react-native-firebase  #js {})
(def nfc-manager            #js {})
(def camera                 #js {:default #js {:constants {:Aspect "Portrait"}}})
(def dialogs                #js {})
(def dismiss-keyboard       #js {})
(def image-crop-picker      #js {})
(def image-resizer          #js {})
(def nfc                    #js {})
(def svg                    #js {})
(def snoopy                 #js {})
(def snoopy-filter          #js {})
(def snoopy-bars            #js {})
(def snoopy-buffer          #js {})
(def background-timer       #js {:setTimeout (fn [cb ms] (js/setTimeout cb ms))})
(def react-navigation       #js {:NavigationActions #js {}})
(def context-menu           (js/require "react-native-qt-context-menu"))
