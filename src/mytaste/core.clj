(ns mytaste.core)

;; see https://cider.readthedocs.io/en/latest/up_and_running/
;; C-x 5 2 learncider.org

;; Starting
;;
;; Jack-in: C-c M-j
;; Set REPL namespace: C-c M-n
;; Toggle file/repl: C-c C-z
;; Quit: C-c C-q

;; CIDER Eval menu

(+ 1 2 (* 3 4))
; 15

;; current sexp: C-M-x
;; last sexp: C-x C-e or C-c C-e
;; selected region: C-c C-v r
;; ns form: C-c C-v n
;; last sexp and insert: C-u C-x C-e
;; insert last sexp in REPL: C-c M-p
;; Load buffer C-c C-k
;; Load file C-c C-l

;; CIDER Interactions menu

;; Inspect C-c M-i
;; TAB=next, Enter=operate, l=pop, q=quit
(def xxx 3)
{:a 1 :b xxx}

;; Tracing C-c M-t v
(defn fibonacci
  "this is the fibo doc"
  [x]
  (cond 
    (= x 0) 0
    (= x 1) 1
    :else (+ (fibonacci (dec x)) (fibonacci (- x 2)))))

(fibonacci 3)

;; Documenation
(def foo [2 "a" \b])
(+ 1 2)
(java.util.Date.)

;; cider-doc: C-c C-d d
;;   place cursor on +. q to quit
;; cider-javadoc: C-c C-d j
;; cider-grimoire: C-c C-d r
;; cider-grimoire web: C-c C-d w
;; cider-apropos (e.g. "inter")
;; cider-apropos-doc: C-c C-d f

;; Jump
(map inc [1 2 3])
(defn mysq [n] (* n n))
;("foo.dat") ; a resource
(+ 3.14 Math/PI (Math/cos 2))

;; Macro Expand
(defmacro unless [pred a b]
  `(if (not ~pred) ~a ~b))
(unless true "foo" "bar")
(-> 2 (* 3) (+ 2))

;; Test
;; run tests, e.g. C-c C-t p

;; Debug C-u C-M-x
(defn fibo-iter
  ([n] (fibo-iter 0 1 n))
  ([curr nxt n]
   (cond
     (zero? n) curr
     :else (recur nxt (+ curr nxt) (dec n)))))

(fibo-iter 10)
(fibo-iter 0 1 10)

;; conditional (eval this form)
(dotimes [i 10]
  #dbg ^{:break/when (= i 7)}
  (prn i))

;; insert conditional C-u C-u C-M-x
(dotimes [i 10]
  (prn i))

;; stacktrace
(/ 1 0)

;; Browse
;; use CIDER Interactions browse menu
(java.util.Date.)           ; get the date
;; #inst "2017-04-24T01:23:25.062-00:00"

;; see https://github.com/clojure-emacs/clojure-mode
;; see https://github.com/clojure-emacs/clj-refactor.el/wiki for animations


;; Refactoring

;; C-: toggle str/keyword
;("foo") [:foo]

;; C-c C-r [ convert to vector
(:foo 'bar) ; => [:foo :bar]

;; C-c C-r { convert to map
(:foo 'bar) ; => {:foo 'bar}

;; C-c C-r # convert to set
(:foo 'bar) ; => #{:foo 'bar}

;; C-c C-r ( convert to list
[:foo :bar] ; => (:foo :bar)

;; C-c C-r ' convert to quoted list
(:foo :bar) ; => '(:foo :bar)

;; threading refactoring
(filter even? (map inc (range 10)))

;; after C-c C-r l (thread last all)
(->> 10
     range
     (map inc)
     (filter even?))

;; thread first
(first (.split (.replace (.toUpperCase "a b c d") "A" "X") " "))

;; after C-c C-r f thread first all
(-> "a b c d"
    .toUpperCase
    (.replace "A" "X")
    (.split " ")
    first)

;; C-c C-r a unwind threaded form
(->> 10
     range
     (map inc)
     (filter even?))

;; (filter even? (map inc (range 10)))

;; let
(defn handle-request
  {:status 200
   :body (find-body abc)})

;; C-c C-r s i  introduce let
;; position cursor at (find-body..)
(defn handle-request
  {:status 200
   :body (let [x (find-body abc)]
           x)})

;; before move to let
(defn handle-request [abc]
  (let [body (find-body abc)]
    {:status 200
     :body body}))

;; after: C-c C-r s m  move to let
(defn handle-request [abc]
  (let [body (find-body abc)
        x 200]
    {:status x
     :body body}))
