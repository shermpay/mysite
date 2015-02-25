goog.addDependency("base.js", ['goog'], []);
goog.addDependency("../cljs/core.js", ['cljs.core'], ['goog.string', 'goog.object', 'goog.string.StringBuffer', 'goog.array']);
goog.addDependency("../clojure/string.js", ['clojure.string'], ['goog.string', 'cljs.core', 'goog.string.StringBuffer']);
goog.addDependency("../mysite/core.js", ['mysite.core'], ['cljs.core', 'clojure.string']);
goog.addDependency("../mysite/blog.js", ['mysite.blog'], ['cljs.core']);