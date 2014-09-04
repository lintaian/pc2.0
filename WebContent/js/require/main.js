require.config({
	baseUrl: 'js',
	paths: {
		jquery: 'lib/jquery',
		jqueryMobile: 'lib/jquery.mobile-1.4.3',
		angular: 'lib/angular',
		angularResource: 'lib/angular-resource',
		echarts: 'lib/echarts',
		'echarts/chart/bar': 'lib/echarts',
    	'echarts/chart/line': 'lib/echarts',
    	'echarts/chart/pie': 'lib/echarts',
    	'echarts/chart/radar': 'lib/echarts',
    	util: 'custom/util',
    	cMain: 'custom/main',
    	resource: 'custom/resource',
    	app: 'custom/app'
	},
	shim: {
		angular: {
			exports: 'angular'
		},
		angularResource: {deps: ['angular']},
		jqueryMobile: {deps: ['jquery']}
	}
});
require(['jquery', 'angular', 'angularResource', 'echarts', 'echarts/chart/bar', 'echarts/chart/radar',
         'echarts/chart/line', 'echarts/chart/pie', 'jqueryMobile',
         'cMain', 'util', 'resource', 'app'], function($, angular) {
	angular.bootstrap(document, ['app']);
	Util.scopes.mainCtrl.changePage('#score', '', 'scoreCtrl', 'total', '考试总分', true);
});
