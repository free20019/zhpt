var drofflineGrid = null;
require([ "app/util", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select","dijit/form/Textarea","dijit/form/FilteringSelect"
          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
          ,"app/createSyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
          "dojo/dom-construct", "dojo/domReady!"], function(util,
        		  Editor, Button,DateTextBox,Select,Textarea,FilteringSelect,Grid,TextBox
        		  , Pagination,Selection,Keyboard,ColumnResizer,createSyncStore,Memory,DijitRegistry
        		  ,registry, domStyle,declare, dc ) {
	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'drofftjdiv');
	var droff_comp= new FilteringSelect({id:"droff_comp",options: [],style:{"width":"8em","margin-right":"15px"}}).placeAt('drofftjdiv');
	var droff_mins= new TextBox({id:"droff_mins",value:"3",style:{"width":"8em","margin-right":"15px"}}).placeAt('drofftjdiv');
	dc.place(dc.create("span",{innerHTML:"日未上线车辆",style:{"margin-right":"5px"}}),'drofftjdiv');
	
	var queryCondition = {"droff_comp":droff_comp,"droff_mins":droff_mins};
	var drwsxclbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('drofftjdiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				postData[o] = queryCondition[o].value;
			}
			url = "claq/offlineexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('drofftjdiv');
	var drwsx_cls=dc.place(dc.create("span",{id:"drwsx_cls",style:{"margin-right":"5px"}}),'drofftjdiv');
	queryGs().then(function(data){
		droff_comp.set('store',new dojo.store.Memory({ data: data.datas}));
	})
	droff_comp.set('queryExpr', "*${0}*"  );
	//添加表格
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'COMP_NAME', 
		    			label : "公司" 
		    		},{field:'VEHI_NO', 
		    			label : "车号" 
		    		},
		    		{field:'STIME', 
		    			label : "汇报时间" 
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'SIM_NUM', 
		    			label : "SIM卡号"
		    		},{field:'OWN_NAME', 
		    			label : "联系人" 
		    		},{field:'OWN_TEL', 
		    			label : "联系电话" 
		    		}
	];
	
	

	//查询按钮
	var xhrArgs = {
			url : basePath + "claq/offline",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				drwsxclbutton.hideWait();
				if(data.length==0){
					alert("没有该天数未上线的车辆");
				}else{
					for (var i = 0; i < data.length; i++) {
						data[i] = dojo.mixin({
							id : i + 1
						}, data[i]);
					}
				}
				store = createSyncStore({
					data : data
				});
				drofflineGrid.set('collection', store);
				dijit.byId(drwsx_cls).innerHTML="总数为:"+data.length+"辆";
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	drofflineGrid = new CustomGrid({
	   					columns : columns
	   				}, "drofflinetable");
});
