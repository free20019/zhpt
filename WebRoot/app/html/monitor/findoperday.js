var findoperdayGrid = null;
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
	var findoperday_stime= new TextBox({id:"findoperday_stime",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('findoperdaydiv');
	var queryCondition = {"time":findoperday_stime};
	var findoperdaybutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};

				for(var o in queryCondition){
					if(o == "findoperday_stime"){
						postData[o] = dojo.byId(queryCondition[o].id).value;
					}else{
						postData[o] = queryCondition[o].value;
					}
				}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'time='+dojo.byId("findoperday_stime").value}));
	        }
	}).placeAt('findoperdaydiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				if(o == "findoperday_stime"){
					postData[o] = dojo.byId(queryCondition[o].id).value;
				}else{
					postData[o] = queryCondition[o].value;
				}
			}
			url = "hygl/findoperdayexcle?time="
				+ dojo.byId("findoperday_stime").value, window.open(url)
		}
	}).placeAt('findoperdaydiv');
	//添加表格
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'DB_TIME', 
		    			label : "日期" 
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'JDDBD',
		    			label : "总车辆数"
		    		},{field:'JDDSB',
		    			label : "平均营运车辆数"
		    		},{field:'RUN_TIMES',
		    			label : "平均周转次数"
		    		},{field:'RUN_PROFIT',
		    			label : "平均营收金额"
		    		},{field:'ACTUAL_LOAD_RATE',
		    			label : "平均实载率"
		    		},{field:'RUN_TIME',
		    			label : "平均重车时间(分)"
		    		},{field:'WAITTING_TIME',
		    			label : "平均等候时间(分)"
		    		},{field:'ACTUAL_LOAD_MILEAGE',
		    			label : "平均实载里程(分)"
		    		},{field:'NO_LOAD_MILEAGE',
		    			label : "平均空驶里程(公里)"
		    		}
	];
	
	
	var day_time=dojo.byId("findoperday_stime").value;
	//查询按钮
	var xhrArgs = {
			url : basePath + "hygl/findoperday",
			postData : "time="+day_time,
			handleAs : "json",
			load : function(data) {
				findoperdaybutton.hideWait();
				console.log(data)
				for (var i = 0; i < data.length; i++) {
					data[i] = dojo.mixin({
						id : i + 1
					}, data[i]);
				}
				store = createSyncStore({
					data : data
				});
				findoperdayGrid.set('collection', store);
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	findoperdayGrid = new CustomGrid({
	   					columns : columns
	   				}, "findoperdaytable");
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
	var time=y+"-"+m+"-"+d; 
	return time;
}
