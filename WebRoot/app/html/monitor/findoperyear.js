var findoperyearGrid = null;
require([ "app/util", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select","dijit/form/Textarea","dijit/form/FilteringSelect"
          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
          ,"app/createSyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
          "dojo/dom-construct", "dojo/domReady!"], function(util,
        		  Editor, Button,DateTextBox,Select,Textarea,FilteringSelect,Grid,TextBox
        		  , Pagination,Selection,Keyboard,ColumnResizer,createSyncStore,Memory,DijitRegistry
        		  ,registry, domStyle,declare, dc ) {
	//加载查询条件按钮
	var findoperyear_stime= new TextBox({id:"findoperyear_stime",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y',dateFmt:'yyyy',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('findoperyeardiv');
	var queryCondition = {"findoperyear_stime":findoperyear_stime};
	var findoperyearbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
				for(var o in queryCondition){
					if(o == "findoperyear_stime"){
						postData[o] = dojo.byId(queryCondition[o].id).value;
					}else{
						postData[o] = queryCondition[o].value;
					}
				}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'time='+dojo.byId("findoperyear_stime").value}));
	        }
	}).placeAt('findoperyeardiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				if(o == "findoperyear_stime"){
					postData[o] = dojo.byId(queryCondition[o].id).value;
				}else{
					postData[o] = queryCondition[o].value;
				}
			}
			url = "hygl/findoperyearexcle?time="
				+ dojo.byId("findoperyear_stime").value, window.open(url);
		}
	}).placeAt('findoperyeardiv');
	//添加表格
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'REPORT_TIME',
		    			label : "月份" 
		    		},{field:'JDDSB',
		    			label : "总车辆数"
		    		},{field:'PJREPORE_VHIC',
		    			label : "日参与营运平均车辆数"
		    		},{field:'REPORE_NO',
		    			label : "总周转次数"
		    		},{field:'MONTHREPORE_NO',
		    			label : "月平均单车周转次数"
		    		},{field:'DAYREPORE_NO',
		    			label : "日平均单车周转次数"
		    		},{field:'REPORE_RADE',
		    			label : "平均营运率"
		    		},{field:'REPORE_CAR_TIME',
		    			label : "总出车时间(天)"
		    		},{field:'PJREPORE_CAR_TIME',
		    			label : "平均出车时间(天)"
		    		},{field:'REPORE_AMOUNT_REVENUE',
		    			label : "营收金额"
		    		},{field:'MONTHREPORE_AMOUNT_REVENUE',
		    			label : "月平均单车营收金额"
		    		},{field:'DAYREPORE_AMOUNT_REVENUE',
		    			label : "日平均单车营收金额"
		    		},{field:'REPORE_MILEAGE',
		    			label : "总里程(公里)"
		    		},{field:'MONTHREPORE_MILEAGE',
		    			label : "月平均单车总里程(公里)"
		    		},{field:'DAYREPORE_MILEAGE',
		    			label : "日平均单车总里程(公里)"
		    		},{field:'REPORE_ACTUAL_MILEAGE',
		    			label : "实载总里程(公里)"
		    		},{field:'MONTHREPORE_ACTUAL_MILEAGE',
		    			label : "月平均单车实载里程(公里)"
		    		},{field:'DAYREPORE_ACTUAL_MILEAGE',
		    			label : "日平均单车实载里程(公里)"
		    		},{field:'REPORE_EMPTY_MILEAGE',
		    			label : "空驶总里程(公里)"
		    		},{field:'MONTHREPORE_EMPTY_MILEAGE',
		    			label : "月平均单车空驶里程(公里)"
		    		},{field:'DAYREPORE_EMPTY_MILEAGE',
		    			label : "日平均单车空驶里程(公里)"
		    		},{field:'PEPORE_ACTUAL_LOADING',
		    			label : "平均实载率(公里)"
		    		}
	];
	
	
	var day_time=dojo.byId("findoperyear_stime").value;
	//查询按钮
	var xhrArgs = {
			url : basePath + "hygl/findoperyear",
			postData : "time="+day_time,
			handleAs : "json",
			load : function(data) {
				findoperyearbutton.hideWait();
				console.log(data)
				for (var i = 0; i < data.datas.length; i++) {
					data.datas[i] = dojo.mixin({
						id : i + 1
					}, data.datas[i]);
				}
				store = createSyncStore({
					data : data.datas
				});
				findoperyearGrid.set('collection', store);
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	findoperyearGrid = new CustomGrid({
	   					columns : columns
	   				}, "findoperyeartable");
	dojo.xhrPost(xhrArgs);
});
//时间
function setformat(obj){
	var y=(obj.getFullYear()).toString();
	var m=(obj.getMonth()+1).toString();
	if(m.length==1){
		m="0"+m;
	}
	var d=obj.getDate().toString();
	if(d.length==1){
		d="0"+d;
	}
	var h = obj.getHours().toString();
	if(h.length==1){
		h="0"+h;
	}
	var mi = obj.getMinutes().toString();
	if(mi.length==1){
		mi="0"+mi;
	}
	var s = obj.getSeconds().toString();
	if(s.length==1){
		s="0"+s;
	}
	var time=y; 
	return time;
}
