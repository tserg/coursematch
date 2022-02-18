if (process.env.NODE_ENV === "production") {
    const opt = require("./course_match-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./course_match-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./course_match-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
