var fwpjxxGrid;
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
//	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'fwpjxxdiv');
//	var fwpjxxcomp_id= new FilteringSelect({id:"fwpjxxcomp_id",
//		onChange:function(){
//			queryVhicById(this.value).then(function(data){
////						console.dir("#####"+data.datas)
//				fwpjxxvhic_id.set('store',new dojo.store.Memory({ data: data.datas}));
//					})
//			},
//		options: [],style:{"width":"8em","margin-right":"15px"}}).placeAt('fwpjxxdiv');
//	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'fwpjxxdiv');
//	var fwpjxxvhic_id= new FilteringSelect({id:"fwpjxxvhic_id",
//		options: [],style:{"width":"8em","margin-right":"15px"}}).placeAt('fwpjxxdiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'fwpjxxdiv');
	var fwpjxxvhic_id= new TextBox({id:"fwpjxxvhic_id",style:{"width":"8em","margin-right":"15px"}}).placeAt('fwpjxxdiv');
	dc.place(dc.create("span",{innerHTML:"司机姓名",style:{"margin-right":"5px"}}),'fwpjxxdiv');
	var fwpjxxname_id= new TextBox({id:"fwpjxxname_id",style:{"width":"8em","margin-right":"15px"}}).placeAt('fwpjxxdiv');
	var queryCondition = {"fwpjxxvhic_id":fwpjxxvhic_id,"fwpjxxname_id":fwpjxxname_id};
	var fwpjxxbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('fwpjxxdiv');
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
			url = "hygl/fwpjxxexcle?postData="
				+ dojo.toJson(postData), window.open(url);
		}
	}).placeAt('fwpjxxdiv');
//	queryGs().then(function(data){
////		console.dir("#####"+data.datas)
//		fwpjxxcomp_id.set('store',new dojo.store.Memory({ data: data.datas}));
//	});
//	fwpjxxcomp_id.set('queryExpr', "*${0}*"  );
		
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		       		{field:'vhic',
		    			label : "车号"
		    		},{field:'color',
		    			label : "车辆颜色"
		    		},{field:'time',
		    			label : "评价时间"
		    			,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'advice',
		    			label : "评价意见"
		    		}
		];

		var xhrArgs = {
			url : basePath + "hygl/fwpjxx",
//			url : "http://localhost:8080/zhpt/hygl/fwpjxx",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				fwpjxxbutton.hideWait();
				console.log(data.count)
					fwpjxxGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					fwpjxxGrid.set('collection',store);
					fwpjxxGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		fwpjxxGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"fwpjxxtable");
		fwpjxxGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(fwpjxxGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'fwpjxxtable','after');
});


