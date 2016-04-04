'use strict';

var gulp = require('gulp');
var zip = require('gulp-zip');

var $ = require('gulp-load-plugins')({
    pattern: ['gulp-*', 'main-bower-files', 'uglify-save-license', 'del']
});

module.exports = function(options) {
    gulp.task('partials', function() {
        return gulp.src([
                options.src + '/app/**/*.html',
                options.tmp + '/serve/app/**/*.html'
            ])
            .pipe($.minifyHtml({
                empty: true,
                spare: true,
                quotes: true
            }))
            .pipe($.angularTemplatecache('templateCacheHtml.js', {
                module: 'adminPortal',
                root: 'app'
            }))
            .pipe(gulp.dest(options.dist + '/partials/'));
    });

    gulp.task('html', ['inject', 'partials'], function() {
        var partialsInjectFile = gulp.src(options.dist + '/partials/templateCacheHtml.js', {
            read: false
        });
        var partialsInjectOptions = {
            ignorePath: [options.dist],
            starttag: '<!-- inject:partials -->',
            endtag: '<!-- endinject -->',
            addRootSlash: false
        };

        var htmlFilter = $.filter('**/*.html');
        var jsFilter = $.filter('**/*.js');
        var cssFilter = $.filter('**/*.css');
        var assets;

        return gulp.src(options.tmp + '/serve/**/*.html')
            .pipe($.inject(partialsInjectFile, partialsInjectOptions))
            .pipe(assets = $.useref.assets())
            .pipe($.rev())
            .pipe(jsFilter)
            .pipe($.ngAnnotate())
            .pipe(jsFilter.restore())
            .pipe(cssFilter)
            .pipe($.csso())
            .pipe(cssFilter.restore())
            .pipe(assets.restore())
            .pipe($.useref())
            .pipe($.revReplace())
            .pipe(htmlFilter)
            .pipe(htmlFilter.restore())
            .pipe(gulp.dest(options.dist + '/'))
            .pipe($.size({
                title: options.dist + '/',
                showFiles: true
            }));
    });

    // Only applies for fonts from bower dependencies
    // Custom fonts are handled by the "other" task
    gulp.task('build-fonts', function() {
        return gulp.src(options.dist + '/../bower_components/**/*.{otf,eot,svg,ttf,woff,woff2}')
            .pipe($.flatten())
            .pipe(gulp.dest(options.dist + '/fonts/'));
    });

    gulp.task('other', function() {
        return gulp.src([
                options.src + '/**/*',
                '!' + options.src + '/**/*.{html,css,js,less}'
            ])
            .pipe(gulp.dest(options.dist + '/'));
    });

    gulp.task('clean', function(done) {
        $.del([options.dist + '/', options.tmp + '/'], done);
    });

    gulp.task('zip', function() {
        return gulp.src('dist/*')
            .pipe(zip('dist.zip'))
            .pipe(gulp.dest(''));
    });

    gulp.task('build', ['html', 'build-fonts', 'other']);

};