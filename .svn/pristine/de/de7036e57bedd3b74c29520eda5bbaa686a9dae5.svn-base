var findopermonthGrid = null;
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
	var findopermonth_stime= new TextBox({id:"findopermonth_stime",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M',dateFmt:'yyyy-MM',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('findopermonthdiv');
	var queryCondition = {"findopermonth_stime":findopermonth_stime};
	var findopermonthbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
				for(var o in queryCondition){
					if(o == "findopermonth_stime"){
						postData[o] = dojo.byId(queryCondition[o].id).value;
					}else{
						postData[o] = queryCondition[o].value;
					}
				}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'time='+dojo.byId("findopermonth_stime").value}));
	        }
	}).placeAt('findopermonthdiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				if(o == "findopermonth_stime"){
					postData[o] = dojo.byId(queryCondition[o].id).value;
				}else{
					postData[o] = queryCondition[o].value;
				}
			}
			url = "hygl/findopermonthexcle?time="
				+ dojo.byId("findopermonth_stime").value, window.open(url)
		}
	}).placeAt('findopermonthdiv');
	//添加表格
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'REPORT_TIME',
		    			label : "日期" 
		    		},{field:'REPORE_VHICNO',
		    			label : "总车辆数"
		    		},{field:'REPORE_VHIC',
		    			label : "参与营运车辆数"
		    		},{field:'REPORE_NO',
		    			label : "周转总次数"
		    		},{field:'REPORE_TURNOVER',
		    			label : "平均周转次数"
		    		},{field:'REPORE_RADE',
		    			label : "平均营运率"
		    		},{field:'REPORE_AMOUNT_REVENUE',
		    			label : "平均营收金额"
		    		},{field:'REPORE_ACTUAL_LOADING',
		    			label : "平均实载率"
		    		},{field:'REPORE_CAR_TIME',
		    			label : "平均出车时间(时)"
		    		},{field:'REPROE_REVENUE_TIME',
		    			label : "平均重车时间(时)"
		    		},{field:'REPORE_WAIT_TIME',
		    			label : "平均等候时间(时)"
		    		},{field:'REPORE_MILEAGE',
		    			label : "平均总里程(公里)"
		    		},{field:'REPORE_ACTUAL_MILEAGE',
		    			label : "平均实载里程(公里)"
		    		},{field:'REPORE_EMPTY_MILEAGE',
		    			label : "平均空驶里程(公里)"
		    		}
	];
	
	
	var day_time=dojo.byId("findopermonth_stime").value;
	//查询按钮
	var xhrArgs = {
			url : basePath + "hygl/findopermonth",
			postData : "time="+day_time,
			handleAs : "json",
			load : function(data) {
				findopermonthbutton.hideWait();

				console.log(data)
				for (var i = 0; i < data.length; i++) {
					data[i] = dojo.mixin({
						id : i + 1
					}, data[i]);
				}
				store = createSyncStore({
					data : data
				});
				findopermonthGrid.set('collection', store);
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	findopermonthGrid = new CustomGrid({
	   					columns : columns
	   				}, "findopermonthtable");
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
	var time=y+"-"+m; 
	return time;
}
