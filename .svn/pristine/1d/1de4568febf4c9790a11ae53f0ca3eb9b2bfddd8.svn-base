var czsbxxGrid;
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
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'czsbxxdiv');
	var czsb_vhic= new TextBox({id:"czsb_vhic",style:{"width":"8em","margin-right":"15px"}}).placeAt('czsbxxdiv');
	dc.place(dc.create("span",{innerHTML:"终端类型",style:{"margin-right":"5px"}}),'czsbxxdiv');
	var czsb_mdttype= new TextBox({id:"czsb_mdttype",style:{"width":"8em","margin-right":"15px"}}).placeAt('czsbxxdiv');
	var queryCondition = {"czsb_vhic":czsb_vhic,"czsb_mdttype":czsb_mdttype};
	var czsbxxbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('czsbxxdiv');
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
			url = "scjg/czsbxxexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('czsbxxdiv');
	
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'VEHI_NO', 
		    			label : "车号" 
		    		},{field:'VEHI_SIM', 
		    			label : "SIM卡号"
		    		},{field:'MDT_SUB_TYPE', 
		    			label : "终端类型"
		    		},{field:'MDT_NO', 
		    			label : "终端编号"
		    		}
		];

		var xhrArgs = {
			url : basePath + "scjg/czsbxx",
			postData : 'postData={"page":1,"pageSize":pageSize}',
			handleAs : "json",
			load : function(data) {
				czsbxxbutton.hideWait();
				console.log(data.count)
					czsbxxGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					czsbxxGrid.set('collection',store);
					czsbxxGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		czsbxxGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"czsbxxtable");
		czsbxxGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(czsbxxGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'czsbxxtable','after');
});
