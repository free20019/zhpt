var findvehinfoGrid;
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
	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'findvehinfodiv');
	var gs_id= new TextBox({id:"gs_id" }).placeAt('findvehinfodiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'findvehinfodiv');
	var vehi_comp= new TextBox({id:"vehi_comp"}).placeAt('findvehinfodiv');
	var queryCondition = {"gs_id":gs_id,"vehi_no":vehi_comp};
	var findvehinfobutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('findvehinfodiv');
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
			url = "hygl/findvehinfoexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('findvehinfodiv');
//	query_sjzx_Gs().then(function(data){
//		gs_id.set('store',new dojo.store.Memory({ data: data.datas}));
//	})
//	gs_id.set('queryExpr', "*${0}*"  )
//	query_sjzx_Vhic().then(function(data){
//		vehi_comp.set('store',new dojo.store.Memory({ data: data.datas}));
//	})
//	vehi_comp.set('queryExpr', "*${0}*"  );
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		       		{field:'COMPANY_NAME', 
		    			label : "公司名" 
		    		},{field:'PLATE_NUMBER',
		    			label : "车号"
		    		},{field:'BRAND',
		    			label : "车辆品牌"
		    		},{field:'COLOR',
		    			label : "车辆颜色"
		    		},{field:'LICENSE_VALID_PERIOD_FROM',
		    			label : "道路运输证开始时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'LICENSE_VALID_PERIOD_END',
		    			label : "道路运输证结束时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		}
		];

		var xhrArgs = {
			url : basePath + "hygl/findvehinfo",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				findvehinfobutton.hideWait();
				console.log(data.count)
					findvehinfoGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					findvehinfoGrid.set('collection',store);
					findvehinfoGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		findvehinfoGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"findvehinfotable");
		findvehinfoGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(findvehinfoGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'findvehinfotable','after');
});
/**
 * 公司
 * @returns
 */
function queryGs(){
	var xhrArgs = {
			url : basePath + "common/findcomp",
			postData : 'postData={"page":1,"pageSize":9999}',
			handleAs : "json"
	};
	return dojo.xhrPost(xhrArgs);
}

/**
 * 车辆 下拉框
 * @returns
 */
function queryVhicById(compName){
	if(compName ==""){return}
	var xhrArgs = {
			url : basePath + "common/findvhic",
			postData : 'postData={"ownid":"'+compName+'"}',
			handleAs : "json"
		};
	return dojo.xhrPost(xhrArgs);
}


