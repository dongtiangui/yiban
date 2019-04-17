/*
 * Author: Abdullah A Almsaeed
 * Date: 4 Jan 2014
 * Description:
 *      This is a demo file used only for the main dashboard (index.html)
 **/

$(function() {

	'use strict';

	// Make the dashboard widgets sortable Using jquery UI
	$('.connectedSortable').sortable({
		placeholder: 'sort-highlight',
		connectWith: '.connectedSortable',
		handle: '.box-header, .nav-tabs',
		forcePlaceholderSize: true,
		zIndex: 999999
	});
	$('.connectedSortable .box-header, .connectedSortable .nav-tabs-custom').css('cursor', 'move');

	// jQuery UI sortable for the todo list
	$('.todo-list').sortable({
		placeholder: 'sort-highlight',
		handle: '.handle',
		forcePlaceholderSize: true,
		zIndex: 999999
	});

	// bootstrap WYSIHTML5 - text editor
	$('.textarea').wysihtml5();

	$('.daterange').daterangepicker({
		ranges: {
			'Today': [moment(), moment()],
			'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			'Last 7 Days': [moment().subtract(6, 'days'), moment()],
			'Last 30 Days': [moment().subtract(29, 'days'), moment()],
			'This Month': [moment().startOf('month'), moment().endOf('month')],
			'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
		},
		startDate: moment().subtract(29, 'days'),
		endDate: moment()
	}, function(start, end) {
		window.alert('You chose: ' + start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
	});

	/* jQueryKnob */
	$('.knob').knob();

	// jvectormap data
	var visitorsData = {
		US: 398, // USA
		SA: 400, // Saudi Arabia
		CA: 1000, // Canada
		DE: 500, // Germany
		FR: 760, // France
		CN: 300, // China
		AU: 700, // Australia
		BR: 600, // Brazil
		IN: 800, // India
		GB: 320, // Great Britain
		RU: 3000 // Russia
	};
	// World map by jvectormap
	$('#world-map').vectorMap({
		map: 'world_mill_en',
		backgroundColor: 'transparent',
		regionStyle: {
			initial: {
				fill: '#e4e4e4',
				'fill-opacity': 1,
				stroke: 'none',
				'stroke-width': 0,
				'stroke-opacity': 1
			}
		},
		series: {
			regions: [{
				values: visitorsData,
				scale: ['#92c1dc', '#ebf4f9'],
				normalizeFunction: 'polynomial'
			}]
		},
		onRegionLabelShow: function(e, el, code) {
			if (typeof visitorsData[code] != 'undefined')
				el.html(el.html() + ': ' + visitorsData[code] + ' new visitors');
		}
	});

	// Sparkline charts
	var myvalues = [1000, 1200, 920, 927, 931, 1027, 819, 930, 1021];
	$('#sparkline-1').sparkline(myvalues, {
		type: 'line',
		lineColor: '#92c1dc',
		fillColor: '#ebf4f9',
		height: '50',
		width: '80'
	});
	myvalues = [515, 519, 520, 522, 652, 810, 370, 627, 319, 630, 921];
	$('#sparkline-2').sparkline(myvalues, {
		type: 'line',
		lineColor: '#92c1dc',
		fillColor: '#ebf4f9',
		height: '50',
		width: '80'
	});
	myvalues = [15, 19, 20, 22, 33, 27, 31, 27, 19, 30, 21];
	$('#sparkline-3').sparkline(myvalues, {
		type: 'line',
		lineColor: '#92c1dc',
		fillColor: '#ebf4f9',
		height: '50',
		width: '80'
	});

	// The Calender
	$('#calendar').datepicker();

	// SLIMSCROLL FOR CHAT WIDGET
	$('#chat-box').slimScroll({
		height: '250px'
	});
	$(function() {
		var sin = [],
		cos = [];
		for (var i = 2019; i < 2025; i++) {
			sin.push([i, Math.random()*100])
			cos.push([i, Math.random()*100])
		}
		var line_data1 = {
			data: sin,
			color: '#3c8dbc'
		}
		var line_data2 = {
			data: cos,
			color: '#00c0ef'
		}
		$.plot('#line-chart', [line_data1, line_data2], {
			grid: {
				hoverable: true,
				borderColor: '#f3f3f3',
				borderWidth: 1,
				tickColor: '#f3f3f3'
			},
			series: {
				shadowSize: 0,
				lines: {
					show: true
				},
				points: {
					show: true
				}
			},
			lines: {
				fill: true,
				color: ['#3c8dbc', '#f56954']
			},
			yaxis: {
				show: true
			},
			xaxis: {
				show: true
			}
		});
		//Initialize tooltip on hover
		$('<div class="tooltip-inner" id="line-chart-tooltip"></div>').css({
			position: 'absolute',
			display: 'none',
			opacity: 0.8
		}).appendTo('body')
		$('#line-chart').bind('plothover', function(event, pos, item) {
			if (item) {
				var x = item.datapoint[0].toFixed(2),
					y = item.datapoint[1].toFixed(2)
				$('#line-chart-tooltip').html(item.series.label + ' of ' + x + ' = ' + y)
					.css({
						top: item.pageY,
						left: item.pageX
					})
					.fadeIn(200)
			} else {
				$('#line-chart-tooltip').hide()
			}

		})
	})

	// Fix for charts under tabs
	$('.box ul.nav a').on('shown.bs.tab', function() {
		area.redraw();
		donut.redraw();
		line.redraw();
	});

	/* The todo list plugin */
	$('.todo-list').todoList({
		onCheck: function() {
			window.console.log($(this), 'The element has been checked');
		},
		onUnCheck: function() {
			window.console.log($(this), 'The element has been unchecked');
		}
	});

});
