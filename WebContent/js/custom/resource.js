define(['angular'], function(angular) {
	return angular.module('resource', ['ngResource']).constant('cfg', {
		baseUrl: ''
	}).factory('Common', ['$resource', 'cfg', function($resource, cfg) {
		return $resource(cfg.baseUrl + 'batch/:id',{},{
			'getSession':  {method: 'GET', url: cfg.baseUrl + "session"},
			'getApp':  {method: 'GET', url: cfg.baseUrl + "application"}
		});
	}]).factory('Statistic', ['$resource', 'cfg', function($resource, cfg) {
		return $resource(cfg.baseUrl + 'statistic/:id',{},{
			'loadBatchScore':  {method: 'GET', url: cfg.baseUrl + 'statistic/loadBatchScore', isArray: true},
			'loadExamScore':  {method: 'GET', url: cfg.baseUrl + 'statistic/loadExamScore', isArray: true},
			'loadQuestionScore':  {method: 'GET', url: cfg.baseUrl + 'statistic/loadQuestionScore', isArray: true},
			'loadKnowledgeScore':  {method: 'GET', url: cfg.baseUrl + 'statistic/loadKnowledgeScore', isArray: true},
			'loadPowerScore':  {method: 'GET', url: cfg.baseUrl + 'statistic/loadPowerScore', isArray: true},
			'loadStudentSubject':  {method: 'GET', url: cfg.baseUrl + 'statistic/loadStudentSubject', isArray: true},
			'loadStudentSubjectBatch':  {method: 'GET', url: cfg.baseUrl + 'statistic/loadStudentSubjectBatch', isArray: true},
			'loadCurrentBatch':  {method: 'GET', url: cfg.baseUrl + 'statistic/loadCurrentBatch'}
		});
	}]);
});
