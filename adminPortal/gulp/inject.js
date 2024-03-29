'use strict';

var gulp = require('gulp');

var $ = require('gulp-load-plugins')();

var wiredep = require('wiredep').stream;

var fileinclude = require('gulp-file-include');

module.exports = function(options) {

    gulp.task('inject', ['scripts', 'styles'], function() {
        var injectStyles = gulp.src([
            options.tmp + '/serve/app/**/*.css',
            '!' + options.tmp + '/serve/app/vendor.css'
        ], {
            read: false
        });
        var injectHtmls = gulp.src([options.src + '/**/*.html'])
            .pipe(fileinclude())
            .pipe(gulp.dest(options.tmp + '/serve/src'));
        var injectScripts = gulp.src([
                options.src + '/app/**/*.js',
                '!' + options.src + '/app/**/*.spec.js',
                '!' + options.src + '/app/**/*.mock.js'
            ])
            .pipe($.angularFilesort()).on('error', options.errorHandler('AngularFilesort'));
        var injectOptions = {
            ignorePath: [options.src, options.tmp + '/serve'],
            addRootSlash: false
        };
        return gulp.src(options.src + '/**/*.html')
            .pipe($.inject(injectHtmls, injectOptions))
            .pipe($.inject(injectStyles, injectOptions))
            .pipe($.inject(injectScripts, injectOptions))
            .pipe(wiredep(options.wiredep))
            .pipe(gulp.dest(options.tmp + '/serve'));
    });
};