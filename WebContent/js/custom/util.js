define([ 'jquery', 'echarts' ], function(jquery, echarts) {
	(function($, echarts) {
		var Util = function() {

		};
		Util.chartIds = [];
		Util.scopes = {};
		Util.resizeChart = function() {
//			alert(window.orientation);
//			for ( var i=0; i < Util.chartIds.length; i++) {
//				var id = Util.chartIds[i];
//				if ($('#' + id).data('echart')) {
//					if(window.orientation == 0) {
//						Util.resizeDiv(id);
//					} else {
//						Util.resizeDiv(id, 1.0, 0.9);
//					}
//					$('#' + id).data('echart').resize();
//					$('#' + id).data('echart').refresh();
//				}
//			}
		}
		Util.getOption = function(config) {
			var option = {
				title : {
					text : config.title,
					x:'center'
				},
				tooltip : {
					trigger : 'axis',
					position: function(p) {
						return [p[0] -40, p[1]]
					}
				},
				legend : {
					 y: 'bottom',
					data : config.legend
				},
				toolbox : {
					show : false,
					feature : {
						saveAsImage : {
							show : true
						}
					}
				},
				series : config.series
			}
			switch (config.type) {
			case 'radar':
				option.polar = config.polar;
				break;
			default:
				option.xAxis = [{
					type : 'category',
					boundaryGap : config.boundaryGap,
					axisLabel : {
						interval : 0
					},
					data : config.xAxis
				}];
				option.yAxis = [{
					type : 'value'
				}];
				break;
			}
			return option;
		}
		Util.resizeDiv = function(ele, wRate, hRate) {
			 if (arguments.length != 3) {
					wRate = 1.25;
					hRate = 0.7;
			 }
			 var pagewidth = $(window).width();
			 var pageheight = $(window).height();
			 $('#' + ele).height(pageheight * hRate);
			 $('#' + ele).width(pagewidth * wRate);
		 };
		Util.getPageHeight = function() {
			return $(window).height();
		}
		Util.initChart = function(option, id, cloneId, resizeOption) {
			if (resizeOption) {
				Util.resizeDiv(id, resizeOption.wRate, resizeOption.hRate);
			} else {
				Util.resizeDiv(id);
			}
			if(arguments.length == 3) {
				var clone = $('#' + id).clone();
				clone.attr('id', cloneId);
				clone.show();
				$('#' + id).after(clone);
				id = cloneId;
			}
			var myChart = echarts.init(document.getElementById(id));
			myChart.setOption(option);
//			$('#' + id).data('echart', myChart);
//			Util.chartIds.push(id);
		}
		
		Util.fixedData = function(list, fix) {
			for (var i = 0; i < list.length; i++) {
				list[i].score = list[i].score.toFixed(fix);
				list[i].totalscore = list[i].totalscore.toFixed(fix);
				list[i].classavg = list[i].classavg.toFixed(fix);
				list[i].classmax = list[i].classmax.toFixed(fix);
				list[i].classmin = list[i].classmin.toFixed(fix);
				list[i].gradeavg = list[i].gradeavg.toFixed(fix);
				list[i].grademax = list[i].grademax.toFixed(fix);
				list[i].grademin = list[i].grademin.toFixed(fix);
			}
			return list;
		}
		
		if (!window.Util) {
			window['Util'] = Util;
		}
	})(jquery, echarts);
});