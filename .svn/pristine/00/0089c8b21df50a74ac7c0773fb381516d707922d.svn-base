var clxxGrid;
require([ "app/util","dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select"
	          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
	          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
	          ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
	          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
	          "dojo/dom-construct", "dojo/domReady!"], function(util,
	        		  Editor, Button,DateTextBox,Select,Grid,TextBox
	        		  , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
	        		  ,registry, domStyle,declare, dc ) {
	var  store = null ,count = 0;
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'clxxdiv');
	var clxx_vhic= new TextBox({id:"clxx_vhic",style:{"width":"8em","margin-right":"15px"}}).placeAt('clxxdiv');
	dc.place(dc.create("span",{innerHTML:"业户",style:{"margin-right":"5px"}}),'clxxdiv');
	var clxx_yehu= new TextBox({id:"clxx_yehu",style:{"width":"8em","margin-right":"15px"}}).placeAt('clxxdiv');
	var queryCondition = {"clxx_vhic":clxx_vhic,"clxx_yehu":clxx_yehu};
	var clxxbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('clxxdiv');
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
			url = "scjg/clxxexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('clxxdiv');
	
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'VEHI_NO', 
		    			label : "车号"
		    		},{field:'DL_NO', 
		    			label : "道路运输证"
		    		},{field:'YX_TIME', 
		    			label : "有效日期"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'COMP_NAME', 
		    			label : "业户名称"
		    		},{field:'JY_NO', 
		    			label : "经营许可证"
		    		},{field:'JY_FW', 
		    			label : "经营范围"
		    		}
		];
	

		var xhrArgs = {
			url : basePath + "scjg/clxx",
			postData : 'postData={"page":1,"pageSize":pageSize}',
			handleAs : "json",
			load : function(data) {
				clxxbutton.hideWait();

				console.log(data.count)
					clxxGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					clxxGrid.set('collection',store);
					clxxGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		clxxGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"clxxtable");
		clxxGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(clxxGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'clxxtable','after');
});
