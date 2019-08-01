var wxjyGrid;
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
	dc.place(dc.create("span",{innerHTML:"起止时间",style:{"margin-right":"5px"}}),'wxjydiv');
	var wxjy_stime= new TextBox({id:"wxjy_stime",value:setformatday(new Date((new Date()).getTime() - 1000 * 60 * 60*2))+" 00:00:00",onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('wxjydiv');
	dc.place(dc.create("span",{innerHTML:"至",style:{"margin-right":"5px"}}),'wxjydiv');
	var wxjy_etime= new TextBox({id:"wxjy_etime",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('wxjydiv');
	dc.place(dc.create("span",{innerHTML:"订单标题",style:{"margin-right":"5px"}}),'wxjydiv');
	var wxjy_type= new TextBox({id:"wxjy_type",style:{"width":"8em","margin-right":"15px"}}).placeAt('wxjydiv');
	var queryCondition = {"wxjy_stime":wxjy_stime,"wxjy_etime":wxjy_etime,"wxjy_type":wxjy_type};
	var wxjybutton=new Button({
	        label: "查询",
	        onClick: function(){
				var stime = new Date(dojo.byId('wxjy_stime').value), etime = new Date(dojo.byId('wxjy_etime').value);
				if(stime.getFullYear()!=etime.getFullYear()) {
					alert('拒绝跨年查询,请查询当月的信息');
					return false;
				}
				if (stime.getMonth()!=etime.getMonth()) {
					alert('拒绝跨月查询,请查询当月的信息');
					return false;
				}
				this.showWait();
				var postData = {"page":1,"pageSize":30};
				for(var o in queryCondition){
	        		if(o == "wxjy_stime" || o == "wxjy_etime" ){
	        			postData[o] = dojo.byId(queryCondition[o].id).value;
	        		}else{
	        			postData[o] = queryCondition[o].value;
	        		}
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('wxjydiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var stime = new Date(dojo.byId('wxjy_stime').value), etime = new Date(dojo.byId('wxjy_etime').value);
			if(stime.getFullYear()!=etime.getFullYear()) {
				alert('拒绝跨年导出,请导出当月的信息');
				return false;
			}
			if (stime.getMonth()!=etime.getMonth()) {
				alert('拒绝跨月导出,请导出当月的信息');
				return false;
			}
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				if(o == "wxjy_stime" || o == "wxjy_etime" ){
					postData[o] = dojo.byId(queryCondition[o].id).value;
				}else{
					postData[o] = queryCondition[o].value;
				}
			}
			url = "wxjy/findzfbexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('wxjydiv');
	
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'SUBJECT', 
		    			label : "订单标题" 
		    		},{field:'GMT_CREATE', 
		    			label : "交易创建时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'GMT_PAYMENT', 
		    			label : "交易付款时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'SELLER_EMAIL', 
		    			label : "卖家支付账号"
		    		},{field:'BUYER_EMAIL', 
		    			label : "买家支付账号"
		    		},{field:'PRICE', 
		    			label : "商品单价"
		    		},{field:'QUANTITY', 
		    			label : "购买数量"
		    		},{field:'TOTAL_FEE', 
		    			label : "交易金额"
		    		}
		];
	

		var xhrArgs = {
			url : basePath + "jyxx/findzfb",
			postData : 'postData={"page":1,"pageSize":30}',
			handleAs : "json",
			load : function(data) {
				wxjybutton.hideWait();
					wxjyGrid.totalCount = data.count;
//					for(var i=0; i<data.datas.length;  i++){
//				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
//				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
//						items: data.datas
					} });
					wxjyGrid.set('collection',store);
					wxjyGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		wxjyGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"wxjytable");
		wxjyGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(wxjyGrid,xhrArgs,queryCondition,30);
		
		dc.place(pageii.first(),'wxjytable','after');
});
