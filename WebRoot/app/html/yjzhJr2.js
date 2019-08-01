var yjzhJrGrid = null;
require(
		[ "dijit/Dialog", "dgrid/Editor", "dijit/form/Button",
				"dijit/form/DateTextBox", "dijit/form/TimeTextBox",
				"dijit/form/SimpleTextarea", "dgrid/OnDemandGrid",
				"dijit/form/TextBox", "dgrid/extensions/Pagination",
				"dgrid/Selection", "dgrid/Keyboard",
				"dgrid/extensions/ColumnResizer", "app/createAsyncStore",
				"dstore/Memory", "dgrid/extensions/DijitRegistry",
				"dijit/registry", "dojo/dom-style", "dojo/_base/declare",
				"dojo/dom-construct", "dojo/on", "app/util", "dojo/domReady!" ],
		function(Dialog, Editor, Button, DateTextBox, TimeTextBox,
				SimpleTextarea, Grid, TextBox, Pagination, Selection, Keyboard,
				ColumnResizer, createAsyncStore, Memory, DijitRegistry,
				registry, domStyle, declare, dc, on, util) {
			var store = myDialog = null;
			var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
					Selection, ColumnResizer ]);
			var columns = {
				dojoId : "序号",
				sj : {
					label : "时间",
					formatter : util.formatYYYYMMDD
				},
				bjr : {
					label : "报警人姓名"
				},
				bjrdh : {
					label : "报警人电话"
				},
				address : {
					label : "地点"
				},
				lb : {
					label : "类别"
				},
				status : {
					label : "状态"
				}
			};

			dc.place(dc.create("span", {
				innerHTML : "地点",
				style : {
					"margin-right" : "5px"
				}
			}), 'yhToolBar');
			new TextBox({
				id : "query_address",
				style : {
					"width" : "8em",
					"margin-right" : "15px"
				}
			}).placeAt('yhToolBar');
			new Button({
				label : "查  询",
				onClick : function() {
					var postData = {
						"page" : 1,
						"pageSize" : 100
					};
					postData["address"] = dojo.byId('query_address').value;
					dojo.xhrPost(dojo.mixin(xhrArgs, {
						"postData" : 'postData=' + dojo.toJson(postData)
					}));
				}
			}).placeAt('yhToolBar');
			new Button({
				label : "添  加",
				onClick : function() {
					yjzh_jr_dialog.show();

					dijit.byId('form_sj').setValue(null);
					dijit.byId('form_bjr').setValue(null);
					dijit.byId('form_bjrdh').setValue(null);
					dijit.byId('form_address').setValue(null);
					dijit.byId('form_lb').setValue(null);

				}
			}).placeAt('yhToolBar');
			new Button({
				label : "核  实",
				onClick : function() {
					dojo.forEach(yjzhJrGrid.collection.data, function(item,
							index) {
						console.log('核  实 -- -');
						if (yjzhJrGrid.isSelected(item)) {
							var postData = 'postData={"id":"' + item.id + '"}';
							console.log('##' + postData)
						}
					});
				}
			}).placeAt('yhToolBar');
			new Button({
				label : "发  送",
				onClick : function() {
					dojo.forEach(yjzhJrGrid.collection.data, function(item,
							index) {
						console.log('delete -- -');
						if (yjzhJrGrid.isSelected(item)) {
							var postData = 'postData={"id":"' + item.id + '"}';
							console.log('##' + postData)
						}
					});
				}
			}).placeAt('yhToolBar');

			new Button({
				label : "删  除",
				onClick : function() {
					if(window.confirm("确定删除该信息吗？")) {
						dojo.forEach(yjzhJrGrid.collection.data, function (item,
																		   index) {
							console.log('delete -- -');
							if (yjzhJrGrid.isSelected(item)) {
								var postData = 'postData={"id":"' + item.id + '"}';
								console.log('##' + postData)
								dojo.xhrPost({
									postData: postData,
									url: encodeURI(basePath + 'yjzh/sj/del'),
									load: function (data) {
										console.log('delete 22' + data)
										deferred = dojo.xhrPost(xhrArgs);
									}
								});
							}
						});
					}
				}
			}).placeAt('yhToolBar');

			var xhrArgs = {
				url : basePath + "yjzh/sj/get",
				postData : 'postData={"page":1,"pageSize":100}',
				handleAs : "json",
				load : function(data) {
					console.log(data)
					for (var i = 0; i < data.datas.length; i++) {
						data.datas[i] = dojo.mixin({
							dojoId : i + 1
						}, data.datas[i]);
					}
					console.log(data.datas)
					store = new Memory({
						data : {
							identifier : 'dojoId',
							label : 'dojoId',
							items : data.datas
						}
					});

					// new Grid({ columns : columns },
					// "yjzhJrGridDiv").renderArray(data.datas);
					if (yjzhJrGrid) {
						yjzhJrGrid = null;
						dojo.empty('yjzhJrGridDiv')
						// yjzhJrGrid.destroy();
					}
					yjzhJrGrid = new CustomGrid({
						collection : store,
						columns : columns
					}, "yjzhJrGridDiv");

					// yjzhJrGrid.set('collection',store);
					console.log(yjzhJrGrid);
					yjzhJrGrid.on('.dgrid-row:click', function(event) {
						var row = yjzhJrGrid.row(event);
						console.log('Row clicked:', row.id + '\t' + event);
						dojo.byId('sjmiaoshu').innerHTML = row.data['miaoshu']
					});
					yjzhJrGrid.save2 = function(json) {
						var formTemp = {};
						var formSrc = dijit.byId('yjzh_jr_dialog').getValues();
						for ( var key in json) {
							formTemp[key.substr(5)] = json[key]
						}
						var jsonString = dojo.toJson(formTemp)
						console.log(jsonString)
						var xhrArgs2 = {
							url : basePath + "yjzh/sj/save",
							postData : 'postData={"page":1,"pageSize":100}',
							handleAs : "json",
							load : function(data) {
								console.log(data)
								dojo.xhrPost(xhrArgs);
							}
						}
						dojo.xhrPost(dojo.mixin(xhrArgs2, {
							"postData" : 'postData=' + jsonString
						}));
					}

					dojo.byId('sjmiaoshu').innerHTML = yjzhJrGrid.collection.data[0].miaoshu == '' ? ''
							: yjzhJrGrid.collection.data[0].miaoshu
				},
				error : function(error) {
					targetNode.innerHTML = "An unexpected error occurred: "
							+ error;
				}
			};

			var deferred = dojo.xhrPost(xhrArgs);

			var map = new AMap.Map('sjmiaoshu_map', {
				// resizeEnable: true,
				// rotateEnable: true,
				// dragEnable: true,
				// zoomEnable: true,
				// 设置可缩放的级别
				// zooms: [3,18],
				// 传入2D视图，设置中心点和缩放级别
				view : new AMap.View2D({
					center : new AMap.LngLat(120.141408, 30.299043),
					zoom : 15
				})
			});
		});

function addSj(form) {
	var jsonString = dojo.toJson(form, true);
	console.log(jsonString)
	var xhrArgs2 = {
		url : basePath + "yjzh/sj/save",
		postData : 'postData={"page":1,"pageSize":100}',
		handleAs : "json",
		load : function(data) {
			console.log(data)
			dojo.xhrPost(xhrArgs);// 刷新grid，重新请求,和del一样
		}
	}
	dojo.xhrPost(dojo.mixin(xhrArgs2, {
		"postData" : 'postData=' + dojo.toJson(form)
	}));
}

function saveYjzhJr(json) {
	yjzhJrGrid.save2(json)
}