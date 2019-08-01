var raodaoGrid = null;
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
	dc.place(dc.create("span",{innerHTML:"起止时间",style:{"margin-right":"5px"}}),'raodaodiv');
	var raodao_stime= new TextBox({id:"raodao_stime",value:setformat(new Date((new Date()).getTime() - 1000 * 60 * 60*50)),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('raodaodiv');
	dc.place(dc.create("span",{innerHTML:"至",style:{"margin-right":"5px"}}),'raodaodiv');
	var raodao_etime= new TextBox({id:"raodao_etime",value:setformat(new Date((new Date()).getTime() - 1000 * 60 * 60*48)),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('raodaodiv');
	dc.place(dc.create("span",{innerHTML:"车牌",style:{"margin-right":"5px"}}),'raodaodiv');
	var raodao_vhic= new TextBox({id:"raodao_vhic",style:{"width":"8em","margin-right":"15px"}}).placeAt('raodaodiv');
	var queryCondition = {"raodao_stime":raodao_stime,"raodao_etime":raodao_etime,"raodao_vhic":raodao_vhic};
	var raodaobutton=new Button({
	        label: "查询",
	        onClick: function(){
//				var stime = new Date(dojo.byId('raodao_stime').value), etime = new Date(dojo.byId('raodao_etime').value);
//				var vhic = dojo.byId("raodao_vhic").value;
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
	        		if(o == "raodao_stime" || o == "raodao_etime" ){
	        			postData[o] = dojo.byId(queryCondition[o].id).value;
	        		}else{
	        			postData[o] = queryCondition[o].value;
	        		}
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('raodaodiv');
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'VHIC', 
		    			label : "车牌号码" 
		       		},{field:'S_TIME',
		    			label : "开始时间"
		    				,formatter:util.formatYYYYMMDDHHMISS
		       		},{field:'E_TIME',
		    			label : "结束时间"
		    				,formatter:util.formatYYYYMMDDHHMISS
		    		},{field:'S_MILE',
		    			label : "GPS里程"
		    		},{field:'R_MILE_2',
		    			label : "最短距离里程"
		    		},{field:'R_MILE',
		    			label : "最短时间里程"
		    		},{field:'RDONE',
		    			label : "GPS里程/最短距离里程"
//		    				,formatter : util.raodao
		    		},{field:'RDTWO',
		    			label : "GPS里程/最短时间里程"
//		    				,formatter : util.raodao
		    		}
	];
	

	//查询按钮
	var xhrArgs = {
			url : "clqk/raodao",
			postData : 'postData={"page":1,"pageSize":30}',
			handleAs : "json",
			load : function(data) {
				raodaobutton.hideWait();
				console.log(data.count)
					raodaoGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					raodaoGrid.set('collection',store);
					raodaoGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		raodaoGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"raodaotable");
		raodaoGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(raodaoGrid,xhrArgs,queryCondition,30);
		
		dc.place(pageii.first(),'raodaotable','after');
	
});
