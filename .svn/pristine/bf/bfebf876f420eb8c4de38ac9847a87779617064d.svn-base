var khxxwzGrid;
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
	dc.place(dc.create("span",{innerHTML:"起止时间",style:{"margin-right":"5px"}}),'khxxwzdiv');
	var wz_stime= new TextBox({id:"wz_stime",value:setformat(new Date((new Date()).getTime() - 1000 * 60 * 60*720)),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('khxxwzdiv');
	dc.place(dc.create("span",{innerHTML:"至",style:{"margin-right":"5px"}}),'khxxwzdiv');
	var wz_etime= new TextBox({id:"wz_etime",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('khxxwzdiv');
	dc.place(dc.create("span",{innerHTML:"业户",style:{"margin-right":"5px"}}),'khxxwzdiv');
	var wz_slbh= new TextBox({id:"wz_yhmc",style:{"width":"8em","margin-right":"15px"}}).placeAt('khxxwzdiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'khxxwzdiv');
	var wz_tszt= new TextBox({id:"wz_cheh",style:{"width":"8em","margin-right":"15px"}}).placeAt('khxxwzdiv');
	var queryCondition = {"wz_stime":wz_stime,"wz_etime":wz_etime,"wz_yhmc":wz_slbh,"wz_cheh":wz_tszt};
	var khxxwzbutton=new Button({
	        label: "查询",
	        onClick: function(){
//				var stime = new Date(dojo.byId('wz_stime').value), etime = new Date(dojo.byId('wz_etime').value);
//				if(stime.getFullYear()!=etime.getFullYear()) {
//					alert('拒绝跨年查询,请查询当月的信息');
//					return false;
//				}
//				if (stime.getMonth()!=etime.getMonth()) {
//					alert('拒绝跨月查询,请查询当月的信息');
//					return false;
//				}
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        		if(o == "wz_stime" || o == "wz_etime" ){
	        			postData[o] = dojo.byId(queryCondition[o].id).value;
	        		}else{
	        			postData[o] = queryCondition[o].value;
	        		}
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('khxxwzdiv');
	new Button({
		label : "导  出",
		onClick : function() {
//			var stime = new Date(dojo.byId('wz_stime').value), etime = new Date(dojo.byId('wz_etime').value);
//			if(stime.getFullYear()!=etime.getFullYear()) {
//				alert('拒绝跨年导出,请导出当月的信息');
//				return false;
//			}
//			if (stime.getMonth()!=etime.getMonth()) {
//				alert('拒绝跨月导出,请导出当月的信息');
//				return false;
//			}
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				if(o == "wz_stime" || o == "wz_etime" ){
					postData[o] = dojo.byId(queryCondition[o].id).value;
				}else{
					postData[o] = queryCondition[o].value;
				}
			}
			url = "scjg/zfwzexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('khxxwzdiv');
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'ILLEGAL_FACT', 
		    			label : "事件名称" 
		    		},{field:'ILLEGAL_TIME', 
		    			label : "违章时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'VEHICLE_PLATE_NUMBER', 
		    			label : "车号"
		    		},{field:'COMPANY_NAME', 
		    			label : "企业名称"
		    		},{field:'PARTY_NAME', 
		    			label : "当事人姓名"
		    		},{field:'COMPANY_LICENSE_NUMBER', 
		    			label : "经营许可证"
		    		},{field:'FINE', 
		    			label : "罚款"
		    		},{field:'DEDUCTION_SCORE', 
		    			label : "扣分"
		    		},{field:'PUNISH_UNIT', 
		    			label : "执法单位"
		    		},{field:'ILLEGAL_LOCATION', 
		    			label : "执法地点"
		    		}
		];

		var xhrArgs = {
			url : basePath + "scjg/zfwz",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				khxxwzbutton.hideWait();
				console.log(data.count)
					khxxwzGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					khxxwzGrid.set('collection',store);
					khxxwzGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		khxxwzGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"khxxwztable");
		khxxwzGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(khxxwzGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'khxxwztable','after');
});
//时间
function setformat(obj){
	var y=(obj.getFullYear()).toString();
	var m=(obj.getMonth()+1).toString();
	if(m.length==1){
		m="0"+m;
	}
	var d=obj.getDate().toString();
	if(d.length==1){
		d="0"+d;
	}
	var h = obj.getHours().toString();
	if(h.length==1){
		h="0"+h;
	}
	var mi = obj.getMinutes().toString();
	if(mi.length==1){
		mi="0"+mi;
	}
	var s = obj.getSeconds().toString();
	if(s.length==1){
		s="0"+s;
	}
	var time=y+"-"+m+"-"+d+" "+h+":"+mi+":"+s; 
	return time;
}