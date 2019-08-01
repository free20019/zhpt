var tssxlGrid = null;
var tszxyylGrid = null;
var tsyxswyyGrid = null;
var tstszdclsGrid = null;
var tsystyGrid = null;
var sj;
require([ "app/util", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select","dijit/form/Textarea","dijit/form/FilteringSelect"
          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
          ,"app/createSyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
          "dojo/dom-construct", "dojo/domReady!"], function(util,
        		  Editor, Button,DateTextBox,Select,Textarea,FilteringSelect,Grid,TextBox
        		  , Pagination,Selection,Keyboard,ColumnResizer,createSyncStore,Memory,DijitRegistry
        		  ,registry, domStyle,declare, dc ) {
	//上线率表格
	var columns = [
		       		{field:'DATE', 
		    			label : "上线率" 
		    		},{field:'a0', 
		    			label : "0:00" 
		    		},{field:'a1', 
		    			label : "1:00" 
		    		},{field:'a2',
		    			label : "2:00"
			    	},{field:'a3',
		    			label : "3:00"
				    },{field:'a4',
		    			label : "4:00"
				    },{field:'a5',
		    			label : "5:00"
				    },{field:'a6',
		    			label : "6:00"
				    },{field:'a7',
		    			label : "7:00"
				    },{field:'a8',
		    			label : "8:00"
				    },{field:'a9',
		    			label : "9:00"
				    },{field:'a10', 
		    			label : "10:00" 
		    		},{field:'a11', 
		    			label : "11:00" 
		    		},{field:'a12',
		    			label : "12:00"
			    	},{field:'a13',
		    			label : "13:00"
				    },{field:'a14',
		    			label : "14:00"
				    },{field:'a15',
		    			label : "15:00"
				    },{field:'a16',
		    			label : "16:00"
				    },{field:'a17',
		    			label : "17:00"
				    },{field:'a18',
		    			label : "18:00"
				    },{field:'a19',
		    			label : "19:00"
				    },{field:'a20', 
		    			label : "20:00" 
		    		},{field:'a21', 
		    			label : "21:00" 
		    		},{field:'a22',
		    			label : "22:00"
			    	},{field:'a23',
		    			label : "23:00"
				    }
		    							    		
	];
	//查询按钮
	var xhrArgs = {
			url : basePath + "clqk/sxl",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				for (var i = 0; i < data.datas.length; i++) {
					data.datas[i] = dojo.mixin({
						id : i + 1
					}, data.datas[i]);
				}
				store = createSyncStore({
					data : data.datas
				});
				tssxlGrid.set('collection', store);
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	tssxlGrid = new CustomGrid({
	   					columns : columns
	   				}, "tssxl");
	
	dojo.xhrPost(xhrArgs);
	
	//在线营运率表格
	var columns1 = [
		       		{field:'DATE', 
		    			label : "在线营运率" 
		    		},{field:'a0', 
		    			label : "0:00" 
		    		},{field:'a1', 
		    			label : "1:00" 
		    		},{field:'a2',
		    			label : "2:00"
			    	},{field:'a3',
		    			label : "3:00"
				    },{field:'a4',
		    			label : "4:00"
				    },{field:'a5',
		    			label : "5:00"
				    },{field:'a6',
		    			label : "6:00"
				    },{field:'a7',
		    			label : "7:00"
				    },{field:'a8',
		    			label : "8:00"
				    },{field:'a9',
		    			label : "9:00"
				    },{field:'a10', 
		    			label : "10:00" 
		    		},{field:'a11', 
		    			label : "11:00" 
		    		},{field:'a12',
		    			label : "12:00"
			    	},{field:'a13',
		    			label : "13:00"
				    },{field:'a14',
		    			label : "14:00"
				    },{field:'a15',
		    			label : "15:00"
				    },{field:'a16',
		    			label : "16:00"
				    },{field:'a17',
		    			label : "17:00"
				    },{field:'a18',
		    			label : "18:00"
				    },{field:'a19',
		    			label : "19:00"
				    },{field:'a20', 
		    			label : "20:00" 
		    		},{field:'a21', 
		    			label : "21:00" 
		    		},{field:'a22',
		    			label : "22:00"
			    	},{field:'a23',
		    			label : "23:00"
				    }
		    							    		
	];
	//查询按钮
	var xhrArgs1 = {
			url : basePath + "clqk/zxyy",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				for (var i = 0; i < data.datas.length; i++) {
					data.datas[i] = dojo.mixin({
						id : i + 1
					}, data.datas[i]);
				}
				store = createSyncStore({
					data : data.datas
				});
				tszxyylGrid.set('collection', store);
			},
		};
	
	
	var CustomGrid1 = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	tszxyylGrid = new CustomGrid1({
	   					columns : columns1
	   				}, "tszxyyl");
	
	dojo.xhrPost(xhrArgs1);
	
	
	//一小时未营运车辆数表格
	var columns2 = [
		       		{field:'DATE', 
		    			label : "未营运数" 
		    		},{field:'a0', 
		    			label : "0:00" 
		    		},{field:'a1', 
		    			label : "1:00" 
		    		},{field:'a2',
		    			label : "2:00"
			    	},{field:'a3',
		    			label : "3:00"
				    },{field:'a4',
		    			label : "4:00"
				    },{field:'a5',
		    			label : "5:00"
				    },{field:'a6',
		    			label : "6:00"
				    },{field:'a7',
		    			label : "7:00"
				    },{field:'a8',
		    			label : "8:00"
				    },{field:'a9',
		    			label : "9:00"
				    },{field:'a10', 
		    			label : "10:00" 
		    		},{field:'a11', 
		    			label : "11:00" 
		    		},{field:'a12',
		    			label : "12:00"
			    	},{field:'a13',
		    			label : "13:00"
				    },{field:'a14',
		    			label : "14:00"
				    },{field:'a15',
		    			label : "15:00"
				    },{field:'a16',
		    			label : "16:00"
				    },{field:'a17',
		    			label : "17:00"
				    },{field:'a18',
		    			label : "18:00"
				    },{field:'a19',
		    			label : "19:00"
				    },{field:'a20', 
		    			label : "20:00" 
		    		},{field:'a21', 
		    			label : "21:00" 
		    		},{field:'a22',
		    			label : "22:00"
			    	},{field:'a23',
		    			label : "23:00"
				    }
		    							    		
	];
	//查询按钮
	var xhrArgs2 = {
			url : basePath + "clqk/yxswyy",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				for (var i = 0; i < data.datas.length; i++) {
					data.datas[i] = dojo.mixin({
						id : i + 1
					}, data.datas[i]);
				}
				store = createSyncStore({
					data : data.datas
				});
				tsyxswyyGrid.set('collection', store);
			},
		};
	
	
	var CustomGrid2 = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	tsyxswyyGrid = new CustomGrid2({
	   					columns : columns2
	   				}, "tswsx");
	
	dojo.xhrPost(xhrArgs2);
	
	//重点监控区域车辆数
	var columns3 = [
		       		{field:'DATE', 
		    			label : "未营运数" 
		    		},{field:'a0', 
		    			label : "0:00" 
		    		},{field:'a1', 
		    			label : "1:00" 
		    		},{field:'a2',
		    			label : "2:00"
			    	},{field:'a3',
		    			label : "3:00"
				    },{field:'a4',
		    			label : "4:00"
				    },{field:'a5',
		    			label : "5:00"
				    },{field:'a6',
		    			label : "6:00"
				    },{field:'a7',
		    			label : "7:00"
				    },{field:'a8',
		    			label : "8:00"
				    },{field:'a9',
		    			label : "9:00"
				    },{field:'a10', 
		    			label : "10:00" 
		    		},{field:'a11', 
		    			label : "11:00" 
		    		},{field:'a12',
		    			label : "12:00"
			    	},{field:'a13',
		    			label : "13:00"
				    },{field:'a14',
		    			label : "14:00"
				    },{field:'a15',
		    			label : "15:00"
				    },{field:'a16',
		    			label : "16:00"
				    },{field:'a17',
		    			label : "17:00"
				    },{field:'a18',
		    			label : "18:00"
				    },{field:'a19',
		    			label : "19:00"
				    },{field:'a20', 
		    			label : "20:00" 
		    		},{field:'a21', 
		    			label : "21:00" 
		    		},{field:'a22',
		    			label : "22:00"
			    	},{field:'a23',
		    			label : "23:00"
				    }
		    							    		
	];
	//查询按钮
	var xhrArgs3 = {
			url : basePath + "clqk/findzdqu",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				for (var i = 0; i < data.datas.length; i++) {
					data.datas[i] = dojo.mixin({
						id : i + 1
					}, data.datas[i]);
				}
				store = createSyncStore({
					data : data.datas
				});
				tstszdclsGrid.set('collection', store);
			},
		};
	
	
	var CustomGrid3 = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	tstszdclsGrid = new CustomGrid3({
	   					columns : columns3
	   				}, "tszdcls");
	
	dojo.xhrPost(xhrArgs3);
	
	//疑似停运车辆数
	var columns4 = [
		       		{field:'DATE', 
		    			label : "疑似停运数" 
		    		},{field:'a0', 
		    			label : "5:00" 
		    		},{field:'a1', 
		    			label : "6:00" 
		    		},{field:'a2',
		    			label : "7:00"
			    	},{field:'a3',
		    			label : "8:00"
				    },{field:'a4',
		    			label : "9:00"
				    },{field:'a5',
		    			label : "10:00"
				    },{field:'a6',
		    			label : "11:00"
				    },{field:'a7',
		    			label : "12:00"
				    },{field:'a8',
		    			label : "13:00"
				    },{field:'a9',
		    			label : "14:00"
				    },{field:'a10', 
		    			label : "15:00" 
		    		},{field:'a11', 
		    			label : "16:00" 
		    		},{field:'a12',
		    			label : "17:00"
			    	},{field:'a13',
		    			label : "18:00"
				    },{field:'a14',
		    			label : "19:00"
				    },{field:'a15',
		    			label : "20:00"
				    },{field:'a16',
		    			label : "21:00"
				    },{field:'a17',
		    			label : "22:00"
				    },{field:'a18',
		    			label : "23:00"
				    }
		    							    		
	];
	//查询按钮
	var xhrArgs4 = {
			url : basePath + "clqk/ysty",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				for (var i = 0; i < data.datas.length; i++) {
					data.datas[i] = dojo.mixin({
						id : i + 1
					}, data.datas[i]);
				}
				store = createSyncStore({
					data : data.datas
				});
				tsystyGrid.set('collection', store);
			},
		};
	
	
	var CustomGrid4 = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	tsystyGrid = new CustomGrid4({
	   					columns : columns4
	   				}, "tsysty");
	
	dojo.xhrPost(xhrArgs4);
	
});