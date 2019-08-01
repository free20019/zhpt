var yddcGrid;
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
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'yddcdiv');
	var yddc_vhic= new TextBox({id:"yddc_vhic",style:{"width":"8em","margin-right":"15px"}}).placeAt('yddcdiv');
	var queryCondition = {"vhic":yddc_vhic,"type":"易到"};
	var yddcbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":30};
	        	for(var o in queryCondition){
	        		if(o=='type'){
	        			postData[o] = queryCondition[o];
	        		}else{
	        			postData[o] = queryCondition[o].value;
	        		}
	        	}
	        	console.log(xhrArgs)
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        	console.log(xhrArgs)
	        }
	}).placeAt('yddcdiv');
//	new Button({
//		label : "导  出",
//		onClick : function() {
//			var postData = {
//				"page" : 1,
//				"pageSize" : 10000
//			};
//			for(var o in queryCondition){
//				postData[o] = queryCondition[o].value;
//			}
//			url = "scjg/sjzzexcle?postData="
//				+ dojo.toJson(postData), window.open(url)
//		}
//	}).placeAt('yddcdiv');
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'COMPNAME', 
		    			label : "平台名称" 
		    		},{field:'PLATENUMBER', 
		    			label : "车牌号码"
		    		},{field:'VIN', 
		    			label : "车辆识别码VIN"
		    		},{field:'NAME', 
		    			label : "车主姓名"
		    		},{field:'IDCARDNUMBER', 
		    			label : "车主身份证号"
		    		},{field:'TRANSACTION', 
		    			label : "交易"
		    		},{field:'COMPLAINTS', 
		    			label : "投诉量"
		    		}
		];
	

		var xhrArgs = {
			url : basePath + "jyxx/findchgs",
			postData : 'postData={"page":1,"pageSize":30,"type":"易到"}',
			handleAs : "json",
			load : function(data) {
				yddcbutton.hideWait();
				console.log(data.count)
					yddcGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					yddcGrid.set('collection',store);
					yddcGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		yddcGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"yddctable");
		yddcGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(yddcGrid,xhrArgs,queryCondition,30);
		
		dc.place(pageii.first(),'yddctable','after');
});
