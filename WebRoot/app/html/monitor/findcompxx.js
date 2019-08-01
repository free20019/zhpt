var findcompxxGrid;
require([ "app/util","dgrid/Editor","dijit/form/Button","dijit/form/FilteringSelect" ,"dijit/form/DateTextBox","dijit/form/Select"
	          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
	          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
	          ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
	          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
	          "dojo/dom-construct", "dojo/domReady!"], function(util,
	        		  Editor, Button,FilteringSelect,DateTextBox,Select,Grid,TextBox
	        		  , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
	        		  ,registry, domStyle,declare, dc ) {
	var  store = null ,count = 0;
	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'findcompxxdiv');
	var compxx_comp= new TextBox({id:"compxx_comp" }).placeAt('findcompxxdiv');
	var queryCondition = {"compxx_comp":compxx_comp};
	var findcompxxbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('findcompxxdiv');
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
			url = "hygl/findcompallexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('findcompxxdiv');
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		       		{field:'NAME', 
		    			label : "公司名" 
		    		},{field:'AREA_NAME',
		    			label : "所在区域"
		    		},{field:'ADDRESS',
		    			label : "地址"
		    		},{field:'LICENSE_VALID_PERIOD_FROM',
		    			label : "道路运输证开始时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'LICENSE_VALID_PERIOD_END',
		    			label : "道路运输证结束时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		}
		];

		var xhrArgs = {
			url : basePath + "hygl/findcompall",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				findcompxxbutton.hideWait();
				console.log(data.count)
					findcompxxGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					findcompxxGrid.set('collection',store);
					findcompxxGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		findcompxxGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"findcompxxtable");
		findcompxxGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(findcompxxGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'findcompxxtable','after');
});
