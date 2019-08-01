var fgslctjGrid = null;
require([ "app/util", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select","dijit/form/Textarea","dijit/form/FilteringSelect"
          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
          ,"app/createSyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
          "dojo/dom-construct", "dojo/domReady!"], function(util,
        		  Editor, Button,DateTextBox,Select,Textarea,FilteringSelect,Grid,TextBox
        		  , Pagination,Selection,Keyboard,ColumnResizer,createSyncStore,Memory,DijitRegistry
        		  ,registry, domStyle,declare, dc ) {
	//加载查询条件按钮
	dc.place(dc.create("span",{innerHTML:"起止时间",style:{"margin-right":"5px"}}),'fgslctjdiv');
	var zlctj_stime= new TextBox({id:"zlctj_stime",value:setformat(new Date((new Date()).getTime() - 1000 * 60 * 60*2)),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('fgslctjdiv');
	dc.place(dc.create("span",{innerHTML:"至",style:{"margin-right":"5px"}}),'fgslctjdiv');
	var zlctj_etime= new TextBox({id:"zlctj_etime",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('fgslctjdiv');
	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'fgslctjdiv');
	var zlctj_comp= new FilteringSelect({id:"zlctj_comp",
		onChange:function(){
		},
		options: [],
	                                       style:{"width":"8em","margin-right":"15px"}}).placeAt('fgslctjdiv');
	var queryCondition = {"zspeed_stime":zlctj_stime,"zspeed_etime":zlctj_etime,"zspeed_comp":zlctj_comp};
	var fgslctjbutton=new Button({
	        label: "查询",
	        onClick: function(){
				var stime = new Date(dojo.byId('zlctj_stime').value), etime = new Date(dojo.byId('zlctj_etime').value);
				if(stime.getFullYear()!=etime.getFullYear()) {
					alert('拒绝跨年查询,请查询当月的信息');
					return false;
				}
				if (stime.getMonth()!=etime.getMonth()) {
					alert('拒绝跨月查询,请查询当月的信息');
					return false;
				}
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        		if(o == "zspeed_stime" || o == "zspeed_etime" ){
	        			postData[o] = dojo.byId(queryCondition[o].id).value;
	        		}else{
	        			postData[o] = queryCondition[o].value;
	        		}
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('fgslctjdiv');

	new Button({
		label : "导  出",
		onClick : function() {
			var stime = new Date(dojo.byId('zlctj_stime').value), etime = new Date(dojo.byId('zlctj_etime').value);
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
				if(o == "zspeed_stime" || o == "zspeed_etime" ){
					postData[o] = dojo.byId(queryCondition[o].id).value;
				}else{
					postData[o] = queryCondition[o].value;
				}
			}
			url = "claq/fgslctjexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('fgslctjdiv');
	var lctj_zlc=dc.place(dc.create("span",{id:"zlc",style:{"margin-right":"5px"}}),'fgslctjdiv');
	queryGs().then(function(data){
//		console.dir("#####"+data.datas)
		zlctj_comp.set('store',new dojo.store.Memory({ data: data.datas}));
	})
		zlctj_comp.set('queryExpr', "*${0}*"  )
	//添加表格
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'VHIC', 
		    			label : "车号" 
		    		},{field:'LICHENG',
		    			label : "里程(公里)"
		    		}
	];

	//查询按钮
	var xhrArgs = {
			url : basePath + "claq/lctj",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				fgslctjbutton.hideWait();
				var speed=[];
			var speed_time=[];
				for (var i = 0; i < data.length; i++) {
					data[i] = dojo.mixin({
						id : i + 1
					}, data[i]);
				}
				store = createSyncStore({
					data : data
				});
				fgslctjGrid.set('collection', store);
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	fgslctjGrid = new CustomGrid({
	   					columns : columns
	   				}, "fgslctjtable");
	
	
	
});
