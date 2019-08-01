var sjsbxxGrid;
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
	dc.place(dc.create("span",{innerHTML:"从业资格证",style:{"margin-right":"5px"}}),'sjsbxxdiv');
	var sb_cyzgz= new TextBox({id:"sb_cyzgz",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjsbxxdiv');
	dc.place(dc.create("span",{innerHTML:"经营许可证",style:{"margin-right":"5px"}}),'sjsbxxdiv');
	var sb_jyxkz= new TextBox({id:"sb_jyxkz",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjsbxxdiv');
	dc.place(dc.create("span",{innerHTML:"上下班状态",style:{"margin-right":"5px"}}),'sjsbxxdiv');
	var sb_sxbzt= new TextBox({id:"sb_sxbzt",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjsbxxdiv');
	var queryCondition = {"sb_cyzgz":sb_cyzgz,"sb_jyxkz":sb_jyxkz,"sb_sxbzt":sb_sxbzt};
	var sjsbxxbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":30};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('sjsbxxdiv');
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
			url = "scjg/sjsbexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('sjsbxxdiv');
	
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
		    		},{field:'KJSJ', 
		    			label : "开机时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		}
		];

		var xhrArgs = {
			url : basePath + "scjg/sjsb",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				sjsbxxbutton.hideWait();
				console.log(data.count)
					sjsbxxGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					sjsbxxGrid.set('collection',store);
					sjsbxxGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		sjsbxxGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"sjsbxxtable");
		sjsbxxGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(sjsbxxGrid,xhrArgs,queryCondition,30);
		
		dc.place(pageii.first(),'sjsbxxtable','after');
});
