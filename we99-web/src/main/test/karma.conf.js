// Karma configuration
// http://karma-runner.github.io/0.12/config/configuration-file.html
// Generated on 2015-03-08 using
// generator-karma 0.9.0

module.exports = function(config) {
  'use strict';

  config.set({
    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,

    // base path, that will be used to resolve files and exclude
    basePath: '../',

    // testing framework to use (jasmine/mocha/qunit/...)
    frameworks: ['jasmine'],

    // list of files / patterns to load in the browser
    files: [
      // bower:js
      'webapp/bower_components/jquery/dist/jquery.js',
      'webapp/bower_components/angular/angular.js',
      'webapp/bower_components/bootstrap/dist/js/bootstrap.js',
      'webapp/bower_components/angular-animate/angular-animate.js',
      'webapp/bower_components/angular-cookies/angular-cookies.js',
      'webapp/bower_components/angular-resource/angular-resource.js',
      'webapp/bower_components/angular-route/angular-route.js',
      'webapp/bower_components/angular-sanitize/angular-sanitize.js',
      'webapp/bower_components/angular-touch/angular-touch.js',
      'webapp/bower_components/d3/d3.js',
      'webapp/bower_components/angular-smart-table/dist/smart-table.js',
      'webapp/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
      'webapp/bower_components/ng-file-upload/angular-file-upload.js',
      'webapp/bower_components/lodash/lodash.js',
      'webapp/bower_components/ng-joyride/ng-joyride.js',
      'webapp/bower_components/checklist-model/checklist-model.js',
      'webapp/bower_components/uri.js/src/URI.js',
      'webapp/bower_components/uri.js/src/IPv6.js',
      'webapp/bower_components/uri.js/src/SecondLevelDomains.js',
      'webapp/bower_components/uri.js/src/punycode.js',
      'webapp/bower_components/uri.js/src/URITemplate.js',
      'webapp/bower_components/uri.js/src/jquery.URI.js',
      'webapp/bower_components/uri.js/src/URI.min.js',
      'webapp/bower_components/uri.js/src/jquery.URI.min.js',
      'webapp/bower_components/uri.js/src/URI.fragmentQuery.js',
      'webapp/bower_components/uri.js/src/URI.fragmentURI.js',
      'webapp/bower_components/angular-loading-bar/build/loading-bar.js',
      'webapp/bower_components/angular-mocks/angular-mocks.js',
      // endbower
      'webapp/scripts/**/*.js',
      'test/mock/**/*.js',
      'test/spec/**/*.js'
    ],

    // list of files / patterns to exclude
    exclude: [
    ],

    // web server port
    port: 8080,

    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: [
      'PhantomJS'
    ],

    // Which plugins to enable
    plugins: [
      'karma-phantomjs-launcher',
      'karma-jasmine',
	  'karma-htmlfile-reporter',
	  'karma-coverage'
    ],
	
	reporters: ['progress', 'html','coverage'],
	
	preprocessors: {
	'webapp/scripts/**/*.js': 'coverage'
	},
 
    htmlReporter: {
      outputFile: '../../target/test-report/front-end/units.html'
    },
	
	coverageReporter: {
		type : 'html',
		dir : '../../target/test-report/front-end/'
	},

    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: false,

    colors: true,

    // level of logging
    // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
    logLevel: config.LOG_INFO

    // Uncomment the following lines if you are using grunt's server to run the tests
    // proxies: {
    //   '/': 'http://localhost:9000/'
    // },
    // URL root prevent conflicts with the site root
    // urlRoot: '_karma_'
  });
};
