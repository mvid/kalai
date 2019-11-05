(ns clj-icu-test.emit.util 
  (:require [clj-icu-test.common :refer [reset-indent-level]]
            [clj-icu-test.emit.api :as api :refer [emit]]))

;;
;; emitter helper fns
;;

(defn- emit-top-level-ast-opts
  "Emit the top-level AST contained in ast-opts"
  [ast-opts]
  (reset-indent-level)
  (emit ast-opts))

(defn emit-analyzed-ns-asts
  "Takes a seq of ASTs (such as is returned by tools.analyzer.jvm/analyze-ns),
  converts them each to AstOpts, and runs them through the emit fn (entry point)
  of the transpiler."
  [ast-seq target-lang]
  (let [ast-opts-seq (map #(assoc {} :ast % :lang target-lang) ast-seq)
        emitted-vals (map emit-top-level-ast-opts ast-opts-seq)
        strs (map str emitted-vals)]
    strs))
