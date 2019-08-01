var sjxbxxGrid;
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
	dc.place(dc.create("span",{innerHTML:"从业资格证",style:{"margin-right":"5px"}}),'sjxbxxdiv');
	var xb_cyzgz= new TextBox({id:"xb_cyzgz",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjxbxxdiv');
	dc.place(dc.create("span",{innerHTML:"经营许可证",style:{"margin-right":"5px"}}),'sjxbxxdiv');
	var xb_jyxkz= new TextBox({id:"xb_jyxkz",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjxbxxdiv');
	dc.place(dc.create("span",{innerHTML:"上下班状态",style:{"margin-right":"5px"}}),'sjxbxxdiv');
	var xb_sxbzt= new TextBox({id:"xb_sxbzt",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjxbxxdiv');
	var queryCondition = {"xb_cyzgz":xb_cyzgz,"xb_jyxkz":xb_jyxkz,"xb_sxbzt":xb_sxbzt};
	var sjxbxxbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('sjxbxxdiv');
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
			url = "scjg/sjxbexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('sjxbxxdiv');
	
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'CLZT',
		    			label : "车辆状态"
		    		},{field:'JD', 
		    			label : "经度"
		    		},{field:'WD', 
		    			label : "纬度"
		    		},{field:'SD', 
		    			label : "速度"
		    		},{field:'FX', 
		    			label : "方向"
		    		},{field:'SJ', 
		    			label : "GPS时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'QYJYXKZH', 
		    			label : "企业经营许可证"
		    		},{field:'JSYCYZGZH', 
		    			label : "从业资格证"
		    		},{field:'CPHM', 
		    			label : "车号"
		    		},{field:'JJQKZ', 
		    			label : "计价器K值"
		    		},{field:'DBKJSJ', 
		    			label : "当班开机时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'DBGJSJ', 
		    			label : "当班关机时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'DBLC', 
		    			label : "当班里程"
		    		},{field:'DBYYLC', 
		    			label : "当班营运里程"
		    		},{field:'CC', 
		    			label : "车次"
		    		},{field:'JSSJ', 
		    			label : "计时时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'ZJJE', 
		    			label : "总计金额"
		    		},{field:'KSJE', 
		    			label : "卡收金额"
		    		},{field:'KC', 
		    			label : "卡次"
		    		},{field:'BJLC', 
		    			label : "当班里程"
		    		},{field:'ZJLC', 
		    			label : "总计里程"
		    		},{field:'ZYYLC', 
		    			label : "总营运里程"
		    		},{field:'DJ', 
		    			label : "单价"
		    		},{field:'ZYYCS', 
		    			label : "总营运次数"
		    		},{field:'QTFS', 
		    			label : "签退方式"
		    		}
		];

		var xhrArgs = {
			url : basePath + "scjg/sjxb",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				sjxbxxbutton.hideWait();
				console.log(data.count)
					sjxbxxGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					sjxbxxGrid.set('collection',store);
					sjxbxxGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		sjxbxxGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"sjxbxxtable");
		sjxbxxGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(sjxbxxGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'sjxbxxtable','after');
});
