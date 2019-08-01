var vehposGrid;
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
	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'vehposdiv');
	var vpsgs_id= new TextBox({id:"vpsgs_id" }).placeAt('vehposdiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'vehposdiv');
	var vpsvehi_comp= new TextBox({id:"vpsvehi_comp" }).placeAt('vehposdiv');
	var queryCondition = {"gs_id":vpsgs_id,"vehi_no":vpsvehi_comp};
	var vehposbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('vehposdiv');
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
			url = "hygl/vehposexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('vehposdiv');
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		       		{field:'SPEED', 
		    			label : "速度" 
		    		},{field:'STIME',
		    			label : "时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'STATUS',
		    			label : "状态"
		    		},{field:'HEADING',
		    			label : "方向"
		    		},{field:'PY',
		    			label : "纬度"
		    		},{field:'PX',
		    			label : "经度"
		    		},{field:'VEHI_NO',
		    			label : "车牌号"
		    		},{field:'VEHI_SIM',
		    			label : "SIM卡"
		    		},{field:'COMP_NAME',
		    			label : "公司名称"
		    		},{field:'OWN_NAME',
		    			label : "司机姓名"
		    		},{field:'OWN_TEL',
		    			label : "司机电话"
		    		}
		];

		var xhrArgs = {
			url : basePath + "hygl/vehpos",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				vehposbutton.hideWait();
				console.log(data.count)
					vehposGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					vehposGrid.set('collection',store);
					vehposGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		vehposGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"vehpostable");
		vehposGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(vehposGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'vehpostable','after');
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



