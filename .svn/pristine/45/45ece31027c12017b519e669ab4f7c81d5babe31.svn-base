var khxxcfGrid;
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
	dc.place(dc.create("span",{innerHTML:"企业名称",style:{"margin-right":"5px"}}),'khxxcfdiv');
	var cf_qymc= new TextBox({id:"cf_qymc",style:{"width":"8em","margin-right":"15px"}}).placeAt('khxxcfdiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'khxxcfdiv');
	var cf_vhic= new TextBox({id:"cf_vhic",style:{"width":"8em","margin-right":"15px"}}).placeAt('khxxcfdiv');
	var queryCondition = {"cf_qymc":cf_qymc,"cf_vhic":cf_vhic};
	var khxxcfbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('khxxcfdiv');
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
			url = "scjg/zfwzexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('khxxcfdiv');
	
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'REGISTRATION_NUMBER', 
		    			label : "分类编号" 
		    		},{field:'PUNISH_BASIS', 
		    			label : "处罚依据"
		    		},{field:'PUNISH_FLAG', 
		    			label : "依据代码"
		    		},{field:'CLOSED_DATE', 
		    			label : "决定日期"
		    		},{field:'FINE', 
		    			label : "处罚金额"
		    		},{field:'AREA_NAME', 
		    			label : "执行部门"
		    		},{field:'AREA_CODE', 
		    			label : "状态代码"
		    		}
		];

		var xhrArgs = {
			url : basePath + "scjg/zfwz",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				khxxcfbutton.hideWait();
				console.log(data.count)
					khxxcfGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					khxxcfGrid.set('collection',store);
					khxxcfGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		khxxcfGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"khxxcftable");
		khxxcfGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(khxxcfGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'khxxcftable','after');
});
