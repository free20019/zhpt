
define([
"dijit/Dialog", "dgrid/Editor", "dijit/form/Button",
"dijit/form/DateTextBox", "dijit/form/TimeTextBox",
"dijit/form/SimpleTextarea", "dgrid/OnDemandGrid",
"dijit/form/TextBox", "dgrid/extensions/Pagination",
"dgrid/Selection", "dgrid/Keyboard",
"dgrid/extensions/ColumnResizer", "app/createAsyncStore",
"dstore/Memory", "dgrid/extensions/DijitRegistry",
"dijit/registry", "dojo/dom-style", "dojo/_base/declare",
"dojo/dom-construct", "dojo/on", "app/util"
    ], function(Dialog, Editor, Button, DateTextBox, TimeTextBox,
			SimpleTextarea, Grid, TextBox, Pagination, Selection, Keyboard,
			ColumnResizer, createAsyncStore, Memory, DijitRegistry,
			registry, domStyle, declare, dc, on, util){
	var yjzhJrGrid = null,store = myDialog = null;
	var queryCondition =null;
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,Selection, ColumnResizer ]);
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
		
		var xhrArgs = {
				url : basePath + "jyxx/jr",
				//url:"http://localhost:8080/zhpt/jyxx/jr",
				url:"http://localhost:8080/zhpt/jyxx/jr",
				postData : 'postData={"page":1,"pageSize":100}',
				handleAs : "json",
				load : function(data) {
					//yjzhJrbutton.hideWait();
					console.log(data)
					if(!data.datas || data.datas.length ==0){
						return;
					}
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

					if (yjzhJrGrid) {
						yjzhJrGrid = null;
						dojo.empty('yjzhJrGridDiv')
					}
					yjzhJrGrid = new CustomGrid({
						collection : store,
						columns : columns
					}, "yjzhJrGridDiv");

					yjzhJrGrid.on('.dgrid-row:click', function(event) {
						var row = yjzhJrGrid.row(event);
						console.log('Row clicked:', row.id + '\t' + event);
						dojo.byId('sjmiaoshu').innerHTML = row.data['miaoshu']
						if(yjsjMapShow != null && row.data.jd != '' && row.data.wd != ''){
							if(yjsjMapShow.getAllOverlays().length ==0 ){
								var marker = new AMap.Marker({
									position: new AMap.LngLat(parseFloat(row.data.jd),parseFloat(row.data.wd))
								});
								marker.setMap(yjsjMapShow);	
								yjsjMapShow.setCenter(marker.getPosition());
							}else{
								var marker = yjsjMapShow.getAllOverlays()[0];
								marker.setPosition(
										new AMap.LngLat(parseFloat(row.data.jd),parseFloat(row.data.wd)))
								yjsjMapShow.setCenter(marker.getPosition());		
							}
						}
					});
//					
					dojo.byId('sjmiaoshu').innerHTML = yjzhJrGrid.collection.data[0].miaoshu == '' ? ''
							: yjzhJrGrid.collection.data[0].miaoshu
						
					var data0 = data.datas[0]
					if(yjsjMapShow != null && data0.jd != '' && data0.wd != ''){
						var marker = yjsjMapShow.getAllOverlays()[0];
						if(marker == null){
							var marker = new AMap.Marker({
								position: new AMap.LngLat(parseFloat(data0.jd),parseFloat(data0.wd))
							});
						}
						marker.setMap(yjsjMapShow);		
					}

					for (var i = 0; i < data.datas.length; i++) {
						if(data.datas[i].status =="未审核"){
							var a = document.getElementById("yjzhJrGridDiv-row-"+data.datas[i].dojoId).childNodes[0].childNodes[0];
							for (var j=0; j< a.childNodes.length; j++) {
								if(a.childNodes[j].innerText=='未审核'){
									a.childNodes[j].style.color = '#ff0000';
								}
							}
						}
					}

				},
				error : function(error) {
					console.log(error)
				}
			};
		
	return declare( null,{
		constructor:function(){
			this.initToolBar();
			this.get();
		},
		del:function(){
			
		},
		add:function(json){
			var formTemp = {};
			var formSrc = dijit.byId('yjzh_jr_dialog').getValues();
			var markerPosition = sjlrMapVar.getAllOverlays()[0].getPosition();
			
			for ( var key in json) {
				formTemp[key.substr(5)] = json[key]
			}
			formTemp.wd = markerPosition.lat;
			formTemp.jd = markerPosition.lng;
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
		},
		update:function(){
			
		},
		get:function(){
			dojo.xhrPost(xhrArgs);
		},
		initToolBar:function(){
			var address = new TextBox({
				id : "query_address",
				style : {
					"width" : "8em",
					"margin-right" : "15px"
				}
			}).placeAt('yhToolBar');
			var yjzhJrbutton=new Button({
				label : "查  询",
				onClick : function() {
//					this.showWait();
					var postData = {
						"page" : 1,
						"pageSize" : 100
					};
		        	for(var o in queryCondition){
			        	postData[o] = queryCondition[o].value;
		        	}
		        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
				}
			}).placeAt('yhToolBar');
			new Button({
				label : "添  加",
				onClick : function() {
					yjzh_jr_dialog.show().then(function(){
						dojo.byId("yjzh_jr_dialog_title").innerText = '添加事件';
						console.log('#1',this)
						sjlrMapVar = new AMap.Map('sjlrMap', {
							view : new AMap.View2D({
								center : new AMap.LngLat(120.141408, 30.299043),
								zoom : 15
							})
						});
						
						 var marker = new AMap.Marker({
						        position: sjlrMapVar.getCenter(),
						        draggable: true,
						        cursor: 'move',
						        raiseOnDrag: true
						    });
						    marker.setMap(sjlrMapVar);
						    yjsjMapShow.setCenter(marker.getPosition());	
					});

					dijit.byId('form_sj').setValue(null);
					dijit.byId('form_bjr').setValue(null);
					dijit.byId('form_bjrdh').setValue(null);
					dijit.byId('form_address').setValue(null);
					dijit.byId('form_miaoshu').setValue(null);

				}
			}).placeAt('yhToolBar');

			new Button({
				label : "修  改",
				onClick : function() {
					var hs=0;
					dojo.forEach(yjzhJrGrid.collection.data, function(item,index) {
						if (yjzhJrGrid.isSelected(item)) {
							hs++;
						}
					});
					if(hs==0){
						alert("请选择一行进行修改");
					}else if(hs>1){
						alert("只能选择一行进行修改");
					}else{
						yjzh_jr_dialog.show().then(function(){
							console.log('#2',this);
							dojo.forEach(yjzhJrGrid.collection.data, function (item, index) {
								if (yjzhJrGrid.isSelected(item)) {
									dojo.byId("yjzh_jr_dialog_title").innerText = '修改事件';
									dojo.xhrPost({
										url : basePath + 'scjg/findAccountID',
										postData : 'id=' + item.ID,
										handleAs : "json",
										load : function(data) {
											dijit.byId('form_sj').setValue(null);
											dijit.byId('form_bjr').setValue(null);
											dijit.byId('form_bjrdh').setValue(null);
											dijit.byId('form_address').setValue(null);
											dijit.byId('form_miaoshu').setValue(null);
										},
										error : function(error) {
											console.log(error)
											targetNode.innerHTML = "An unexpected error occurred: " + error;
										}
									});

									sjlrMapVar = new AMap.Map('sjlrMap', {
										view: new AMap.View2D({
											center: new AMap.LngLat(120.141408, 30.299043),
											zoom: 15
										})
									});

									var marker = new AMap.Marker({
										position: sjlrMapVar.getCenter(),
										draggable: true,
										cursor: 'move',
										raiseOnDrag: true
									});
									marker.setMap(sjlrMapVar);
									yjsjMapShow.setCenter(marker.getPosition());
								}
							});
						})
					}
				}
			}).placeAt('yhToolBar');

			new Button({
				label : "核  实",
				onClick : function() {
					var hs=0;
					dojo.forEach(yjzhJrGrid.collection.data, function(item,index) {
						if (yjzhJrGrid.isSelected(item)) {
							hs++;
						}
					});
					if(hs==0){
						alert("请选择一行进行核实");
					}else if(hs>1){
						alert("只能选择一行进行核实");
					}else{
						yjzh_sc_dialog.show().then(function() {
							dojo.byId("yjzh_sc_dialog_title").title='核实事件';
							dojo.byId("yjzh_sc_dialog_title").innerText='核实事件';
							dojo.byId("id_nr_sj").innerText= '确定核实该信息吗？';
							on(dojo.byId("id_del_sj"),'click',function (data) {
								dojo.forEach(yjzhJrGrid.collection.data, function (item, index) {
									console.log('核  实 -- -');
									if (yjzhJrGrid.isSelected(item)) {
										var postData = 'postData={"id":"' + item.id + '"}';
										console.log('##' + postData)
										dojo.xhrPost({
											postData: postData,
											url: encodeURI(basePath + 'yjzh/sj/heshi'),
											load: function () {
												deferred = dojo.xhrPost(xhrArgs);

											}
										});
									}
								});
							});
						})
					}
				}
			}).placeAt('yhToolBar');
			new Button({
				label : "删  除",
				onClick : function() {
					var hs=0;
					dojo.forEach(yjzhJrGrid.collection.data, function(item,index) {
						if (yjzhJrGrid.isSelected(item)) {
							hs++;
						}
					});
					if(hs==0){
						alert("请选择一行进行删除");
					}else if(hs>1){
						alert("只能选择一行进行删除");
					}else{
						yjzh_sc_dialog.show().then(function() {
							dojo.byId("yjzh_sc_dialog_title").title = '删除事件';
							dojo.byId("yjzh_sc_dialog_title").innerText='删除事件';
							dojo.byId("id_nr_sj").innerText= '确定删除该信息吗？';
							on(dojo.byId("id_del_sj"),'click',function () {
								dojo.forEach(yjzhJrGrid.collection.data, function (item, index) {
									console.log('delete -- -');
									if (yjzhJrGrid.isSelected(item)) {
										var postData = 'postData={"id":"' + item.id + '"}';
										console.log('##' + postData)
										dojo.xhrPost({
											postData: postData,
											url: encodeURI(basePath + 'yjzh/sj/del'),
											load:
												//console.log('delete 22' + data)
												function () {
													deferred = dojo.xhrPost(xhrArgs);
												}

										});
									}
								});
							});
						})
					}
				}
			}).placeAt('yhToolBar');
			
			queryCondition = {"address":address}
		}
	})
})
