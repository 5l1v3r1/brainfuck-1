(ns brainfuck.compiler)

(defn- deep-str
  [& x]
  (apply str (flatten x)))

(defn- seek
  "Generate a string of <'s or >'s."
  [start end]
  (let [x (- end start)]
    (deep-str (repeat x ">") (repeat (- x) "<"))))

(defn- remove-seek-redundancies
  [code]
  (let [reduced (clojure.string/replace code #"(<>|><)" "")]
    (if (< (count reduced) (count code))
        (recur reduced)
        code)))
