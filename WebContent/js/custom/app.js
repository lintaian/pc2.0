define(['angular', 'jquery'], function(angular, $) {
	return angular.module('app', ['resource'])
	.controller('mainCtrl', function($scope, Common) {
		Util.scopes.mainCtrl = $scope;
		$scope.title = '乐培生师生平台';
		Common.getSession({name: 'loginUser'}, function(data) {
			$scope.user = data; 
			console.log(data);
		});
		$scope.changePage = function(id, transition, ctrl, p1, p2, p3, p4, p5) {
			Util.scopes[ctrl].init(p1, p2, p3, p4, p5);
			$.mobile.changePage(id, {transition: transition || 'slide'});
		}
	}).controller('indexCtrl', function($scope, Statistic, $timeout) {
		Util.scopes.indexCtrl = $scope;
		var $parent = $scope.$parent;
		Statistic.loadStudentSubject(function(data) {
			$scope.subject = data;
		});
	}).controller('scoreCtrl', function($scope, Statistic) {
		Util.scopes.scoreCtrl = $scope;
		var $parent = $scope.$parent;
		$scope.init = function(id, name, last) {
			$scope.title = name;
			$scope.id = id;
			$scope.show.setType('data');
			if (last) {
				Statistic.loadCurrentBatch(function(data) {
					data = Util.fixedData([data], 2);
					$scope.setData(data);
				});
			} else {
				Statistic.loadStudentSubjectBatch({subjectId: id}, function(data) {
					data = Util.fixedData(data, 2);
					$scope.setData(data);
				});
			}
		}
		$scope.setData = function(data) {
			$scope.data = data;
			$scope.active.set(data[0].batchid, true);
			var title,
				legend = [],
				xAxis = [],
				series = [],
				boundaryGap;
			if (data.length == 1) {
				var d = data[0];
				title = '横向比较';
				legend = [];
				boundaryGap = true;
				xAxis = [ '试卷\n满分', '个人\n得分', '班级\n均分', '班级\n最高', '班级\n最低', '年级\n均分', '年级\n最高', '年级\n最低' ];
				series = [{
					name: '分数',
					type: 'bar',
					data: [d.totalscore, d.score, d.classavg, 
					       d.classmax, d.classmin, d.gradeavg, 
					       d.grademax, d.grademin]
				}];
			} else {
				boundaryGap = false;
				title = '纵向比较';
				legend = [ '试卷\n满分', '个人\n得分', '班级\n均分', '班级\n最高', '班级\n最低', '年级\n均分', '年级\n最高', '年级\n最低' ];
				for (var i = 0; i < legend.length; i++) {
					series.push({
						name: legend[i],
						type: 'line',
						data: []
					})
				}
				for (var i = 0; i < data.length; i++) {
					var d = data[i];
					xAxis.push(d.batchname);
					series[0].data.push(d.totalscore);
					series[1].data.push(d.score);
					series[2].data.push(d.classavg);
					series[3].data.push(d.classmax);
					series[4].data.push(d.classmin);
					series[5].data.push(d.gradeavg);
					series[6].data.push(d.grademax);
					series[7].data.push(d.grademin);
				}
			}
			Util.initChart(Util.getOption({
				title: title,
				legend: legend,
				xAxis: xAxis,
				series: series,
				boundaryGap: boundaryGap
			}), 'scoreChart');
		}
		$scope.active = {
			id: null,
			set: function(id, force) {
				$scope.active.id = (!force && $scope.active.id == id) ? null : id;
			}
		}
		$scope.show = {
			type: 'data',
			setType: function(type) {
				$scope.show.type = type;
			}
		}
	}).controller('scoreDetailCtrl', function($scope, Statistic) {
		Util.scopes.scoreDetailCtrl = $scope;
		var $parent = $scope.$parent;
		$scope.init = function(id, name) {
			$scope.title = name;
			$scope.show.setType('data');
			Statistic.loadExamScore({batchId: id}, function(data) {
				data = Util.fixedData(data, 2);
				$scope.data = data;
				$scope.active.set(data[0].examid, true);
				for (var i = 0; i < data.length; i++) {
					var d = data[i];
					Util.initChart(Util.getOption({
						title: d.examname,
						legend: [],
						xAxis: [ '试卷\n满分', '个人\n得分', '班级\n均分', '班级\n最高', '班级\n最低', '年级\n均分', '年级\n最高', '年级\n最低' ],
						series: [{
							name: '分数',
							type: 'bar',
							data: [d.totalscore, d.score, d.classavg, 
							       d.classmax, d.classmin, d.gradeavg, 
							       d.grademax, d.grademin]
						}],
						boundaryGap: true
					}), 'scoreDetailChart', 'scoreDetailChart' + i);
				}
			});
		}
		$scope.active = {
			id: null,
			set: function(id, force) {
				$scope.active.id = (!force && $scope.active.id == id) ? null : id;
			}
		}
		$scope.show = {
			type: 'data',
			setType: function(type) {
				$scope.show.type = type;
			}
		}
	}).controller('questionCtrl', function($scope, Statistic) {
		Util.scopes.questionCtrl = $scope;
		var $parent = $scope.$parent;
		$scope.init = function(id, name) {
			$scope.title = name;
			Statistic.loadQuestionScore({examId: id}, function(data) {
				data = Util.fixedData(data, 2);
				$scope.data = data;
				$scope.active.set(data[0].questionid, true);
			})
		}
		$scope.active = {
			id: null,
			set: function(id, force) {
				$scope.active.id = (!force && $scope.active.id == id) ? null : id;
			}
		}
	}).controller('questionImgCtrl', function($scope, Statistic) {
		Util.scopes.questionImgCtrl = $scope;
		var $parent = $scope.$parent;
		$scope.init = function(id, name) {
			$scope.title = '第 ' + name + ' 题';
			$scope.questionId = id;
			$scope.img = [{
				name: 'img/1.jpg'
			},{
				name: 'img/2.jpg'
			},{
				name: 'img/3.jpg'
			}]
		}
	}).controller('knowledgeCtrl', function($scope, Statistic) {
		Util.scopes.knowledgeCtrl = $scope;
		var $parent = $scope.$parent;
		$scope.init = function(id, name) {
			$scope.show.setType('data');
			Statistic.loadKnowledgeScore({examId: id}, function(data) {
				data = Util.fixedData(data, 2);
				$scope.data = data;
				$scope.active.set(data[0].knowledgeid, true);
				var polar = [{
	                 indicator : [],
	                 radius: '55%'
	             }];
				var myData = [{
					value: [],
					name: '知识点'
				}];
				for (var i = 0; i < data.length; i++) {
					var d = data[i];
					polar[0].indicator.push({
						text: d.knowledgeid,
						max: 100
					});
					myData[0].value.push((d.score/d.totalscore * 100).toFixed(2));
				}
				Util.initChart(Util.getOption({
					title: '知识点得分率',
					legend: [],
					polar: polar,
					series: [{
						name: '得分率',
						type: 'radar',
						itemStyle: {
			                normal: {
			                    areaStyle: {
			                        type: 'default'
			                    }
			                }
			            },
						data: myData
					}],
					type: 'radar',
					boundaryGap: false
				}), 'knowledgeChart', '', true);
			});
		}
		$scope.active = {
			id: null,
			set: function(id, force) {
				$scope.active.id = (!force && $scope.active.id == id) ? null : id;
			}
		}
		$scope.show = {
			type: 'data',
			setType: function(type) {
				$scope.show.type = type;
			}
		}
	}).controller('powerCtrl', function($scope, Statistic) {
		Util.scopes.powerCtrl = $scope;
		var $parent = $scope.$parent;
		$scope.init = function(id, name) {
			$scope.show.setType('data');
			Statistic.loadPowerScore({examId: id}, function(data) {
				data = Util.fixedData(data, 2);
				$scope.data = data;
				$scope.active.set(data[0].powerid, true);
				var polar = [{
	                 indicator : [],
	                 radius: '55%'
	             }];
				var myData = [{
					value: [],
					name: '能力值'
				}];
				for (var i = 0; i < data.length; i++) {
					var d = data[i];
					polar[0].indicator.push({
						text: d.powername,
						max: 100
					});
					myData[0].value.push((d.score/d.totalscore * 100).toFixed(2));
				}
				Util.initChart(Util.getOption({
					title: '能力值得分率',
					legend: [],
					polar: polar,
					series: [{
						name: '得分率',
						type: 'radar',
						itemStyle: {
			                normal: {
			                    areaStyle: {
			                        type: 'default'
			                    }
			                }
			            },
						data: myData
					}],
					type: 'radar',
					boundaryGap: false
				}), 'powerChart', '', true);
			});
		}
		$scope.active = {
			id: null,
			set: function(id, force) {
				$scope.active.id = (!force && $scope.active.id == id) ? null : id;
			}
		}
		$scope.show = {
			type: 'data',
			setType: function(type) {
				$scope.show.type = type;
			}
		}
	});
});