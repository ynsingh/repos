/**
 * @license
 * Copyright 2011 Dan Vanderkam (danvdk@gmail.com)
 * MIT-licensed (http://opensource.org/licenses/MIT)
 */

// A dygraph "auto-loader".

// Check where this script was sourced from. If it was sourced from
// '../dygraph-dev.js', then we should source all the other scripts with the
// same relative path ('../dygraph.js', '../dygraph-canvas.js', ...)
(function() {
  var src=document.getElementsByTagName('script');
  var script = src[src.length-1].getAttribute("src");

  // This list needs to be kept in sync w/ the one in generate-combined.sh
  // and the one in jsTestDriver.conf.
  var source_files = [
    "Chartjs/strftime/strftime-min.js",
    "Chartjs/rgbcolor/rgbcolor.js",
    "Chartjs/dygraph-layout.js",
    "Chartjs/dygraph-canvas.js",
    "Chartjs/dygraph.js",
    "Chartjs/dygraph-utils.js",
    "Chartjs/dygraph-tickers.js",
    "Chartjs/plugins/base.js",
    "Chartjs/plugins/legend.js",
    "Chartjs/plugins/install.js",
  ];

  for (var i = 0; i < source_files.length; i++) {
    document.write('<script type="text/javascript" src="' + script.replace('dygraph-dev.js', source_files[i]) + '"></script>\n');
  }
})();
