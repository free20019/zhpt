var lctjGrid = null;
var zLC=0;
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
	dc.place(dc.create("span",{innerHTML:"起止时间",style:{"margin-right":"5px"}}),'lctjdiv');
	var lctj_stime= new TextBox({id:"lctj_stime",value:setformat(new Date((new Date()).getTime() - 1000 * 60 * 60*2)),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('lctjdiv');
	dc.place(dc.create("span",{innerHTML:"至",style:{"margin-right":"5px"}}),'lctjdiv');
	var lctj_etime= new TextBox({id:"lctj_etime",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('lctjdiv');
	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'lctjdiv');
	var lctj_comp= new FilteringSelect({id:"lctj_comp",
		onChange:function(){
		queryVhicById(this.value).then(function(data){
			dijit.byId('lctj_vhic').set('value',"");
//					console.dir("#####"+data.datas)
			lctj_vhic.set('store',new dojo.store.Memory({ data: data.datas}));
				})
		},
		options: [],
	                                       style:{"width":"8em","margin-right":"15px"}}).placeAt('lctjdiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'lctjdiv');
	var lctj_vhic= new FilteringSelect({id:"lctj_vhic",options: [],
	                                       style:{"width":"8em","margin-right":"15px"}}).placeAt('lctjdiv');
	
	var queryCondition = {"speed_stime":lctj_stime,"speed_etime":lctj_etime,"speed_comp":lctj_comp,"speed_vhic":lctj_vhic,"speed_value":"0"};
	var lctjbutton=new Button({
	        label: "查询",
	        onClick: function(){
				var stime = new Date(dojo.byId('lctj_stime').value), etime = new Date(dojo.byId('lctj_etime').value);
				var vhic = dojo.byId("lctj_vhic").value;
				if(stime.getFullYear()!=etime.getFullYear()) {
					alert('拒绝跨年查询,请查询当月的信息');
					return false;
				}
				if (stime.getMonth()!=etime.getMonth()) {
					alert('拒绝跨月查询,请查询当月的信息');
					return false;
				}
				if ('' == vhic || ' ' == vhic) {
					alert('请输入车号,\n在输入车号前先选着公司');
					return false;
				}
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        		if(o == "speed_stime" || o == "speed_etime" ){
	        			postData[o] = dojo.byId(queryCondition[o].id).value;
	        		}else{
	        			postData[o] = queryCondition[o].value;
	        		}
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('lctjdiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var stime = new Date(dojo.byId('lctj_stime').value), etime = new Date(dojo.byId('lctj_etime').value);
			var vhic = dojo.byId("lctj_vhic").value;
			if(stime.getFullYear()!=etime.getFullYear()) {
				alert('拒绝跨年查询,请查询当月的信息');
				return false;
			}
			if (stime.getMonth()!=etime.getMonth()) {
				alert('拒绝跨月查询,请查询当月的信息');
				return false;
			}
			if ('' == vhic || ' ' == vhic) {
				alert('请输入车号,\n在输入车号前先选着公司');
				return false;
			}
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				if(o == "speed_stime" || o == "speed_etime" ){
					postData[o] = dojo.byId(queryCondition[o].id).value;
				}else{
					postData[o] = queryCondition[o].value;
				}
			}
			url = "claq/lctjexcle?postData="
				+ dojo.toJson(postData), window.open(url);
		}
	}).placeAt('lctjdiv');
	var lctj_zlc=dc.place(dc.create("span",{id:"zlc",style:{"margin-right":"5px"}}),'lctjdiv');
	queryGs().then(function(data){
//		console.dir("#####"+data.datas)
		lctj_comp.set('store',new dojo.store.Memory({ data: data.datas}));
	})
	queryVhic().then(function(data){
//			console.dir("#####11  "+data.datas)
		lctj_vhic.set('store',new dojo.store.Memory({ data: data.datas}));
		})
		lctj_comp.set('queryExpr', "*${0}*"  )
		lctj_vhic.set('queryExpr', "*${0}*"  )
	//添加表格
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'VEHICLE_NUM', 
		    			label : "车号" 
		    		},{field:'PX',
		    			label : "经度"
		    		},{field:'PY',
		    			label : "纬度"
		    		},{field:'SPEED',
		    			label : "速度(KM/H)"
		    		},{field:'DIRECTION',
		    			label : "方向"
		    			,formatter : util.fxzh
		    		},{field:'SPEED_TIME',
		    			label : "时间"
		    			,formatter : util.formatYYYYMMDDHHMISS
		    		}
		    		,{field:'',
		    			label : "位置信息"
		    		}
	];
	
	

	//查询按钮
	var xhrArgs = {
			url : basePath + "claq/clsdqx",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
			if(data.length==0){
				alert("该车在该时段内没有GPS数据");
			}else{
				lctjbutton.hideWait();
				var speed=[];
				var speed_time=[];
				zLC=0;
				for (var i = 0; i < data.length; i++) {
					if(i!=0){
						var l1=new AMap.LngLat(data[i].PX,data[i].PY);
						var l2=new AMap.LngLat(data[i-1].PX,data[i-1].PY);
						zLC += l1.distance(l2);
					}
					data[i] = dojo.mixin({
						id : i + 1
					}, data[i]);
				}
				dijit.byId(zlc).innerHTML="总里程:"+parseFloat((zLC/1000).toFixed(2))+"公里";
				store = createSyncStore({
					data : data
				});
				lctjGrid.set('collection', store);
			}
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	lctjGrid = new CustomGrid({
	   					columns : columns
	   				}, "lctjtable");
	
	
	
});
