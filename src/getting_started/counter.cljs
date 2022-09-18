(ns ^:figwheel-hooks getting-started.counter
  (:require
   [goog.dom :as gdom]
   [goog.events :as gevents]))

(def minus (gdom/getElement "minus"))
(def plus (gdom/getElement "plus"))
(def step-input (gdom/getElement "step-input"))
(def step-btn (gdom/getElement "step-btn"))
(def count-value (gdom/getElement "count-value"))


(defn get-current-count [] 
  (js/Number (gdom/getTextContent count-value))
)

(defn get-step-input-value [] 
  (js/Number (.. step-input -value))
)

(defn update-count-target [val] 
  (gdom/setTextContent count-value val)
)

(defn increase-count []   
  (update-count-target (inc (get-current-count)))
)

(defn decrease-count []  
  (update-count-target (dec (get-current-count)))
)

(defn step-count [] 
  (js/console.log "current count" (get-current-count))
  (js/console.log "input value" (get-step-input-value))
  (js/console.log (+ (get-current-count) (get-step-input-value)))
  ;; (update-count-target 9)
  (update-count-target (+ (get-current-count) (get-step-input-value)))
  )


(gevents/listen plus
                "click"
                increase-count)

(gevents/listen minus
                "click"
                decrease-count)

(gevents/listen step-btn
                "click"
                step-count)

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:text "Hello world!"}))

(defn get-app-element []
  (gdom/getElement "app"))



;; specify reload hook with ^:after-load metadata
(defn ^:after-load on-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
