(ns voter.models.topic)

(def topics-list (atom []))

(defn all []
  @topics-list)

(defn create [text]
  (swap! topics-list conj text))

(defn delete-all []
  (reset! topics-list []))
