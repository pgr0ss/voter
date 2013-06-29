(ns voter.models.topic)

(def topics-by-id (atom {}))
(def topic-id-counter (atom 0))

(defn all []
  (vec (reverse (vals @topics-by-id))))

(defn create! [text]
  (let [id (swap! topic-id-counter inc)
        topic {:id id :text text :votes 1}]
    (swap! topics-by-id assoc id topic)))

(defn delete-all! []
  (reset! topics-by-id {}))

(defn reset-id! []
  (reset! topic-id-counter 0))

(defn- increment-vote [topics-map id]
  (let [topic (get topics-map id)
        current-votes (:votes topic)]
    (assoc topics-map id (assoc topic :votes (inc current-votes)))))

(defn vote! [topic-id]
  (swap! topics-by-id increment-vote topic-id))
