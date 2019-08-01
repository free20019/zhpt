var tbxxGrid;
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
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'tbxxdiv');
	var tb_vhic= new TextBox({id:"tb_vhic",style:{"width":"8em","margin-right":"15px"}}).placeAt('tbxxdiv');
	var queryCondition = {"tb_vhic":tb_vhic};
	var tbxxbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('tbxxdiv');
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
			url = "scjg/sjtbexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('tbxxdiv');
	
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'TBSJQ', 
		    			label : "替班时间起" 
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'TBSJZ', 
		    			label : "替班时间止"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'DBSJBH', 
		    			label : "当班司机编号"
		    		},{field:'DBSJXM', 
		    			label : "当班司机姓名"
		    		},{field:'TBSJBH', 
		    			label : "替班司机编号"
		    		},{field:'TBSJXM', 
		    			label : "替班司机姓名"
		    		},{field:'CPHM', 
		    			label : "车号"
		    		},{field:'SHRY', 
		    			label : "公司审核人员"
		    		},{field:'QYMC', 
		    			label : "企业名称"
		    		}
		];

		var xhrArgs = {
			url : basePath + "scjg/sjtb",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				tbxxbutton.hideWait();
				console.log(data.count)
					tbxxGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					tbxxGrid.set('collection',store);
					tbxxGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		tbxxGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"tbxxtable");
		tbxxGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(tbxxGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'tbxxtable','after');
});
