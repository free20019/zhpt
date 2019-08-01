var offlineGrid = null;
require([ "app/util", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select","dijit/form/Textarea","dijit/form/FilteringSelect","dijit/form/ComboBox"
          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
          ,"app/createSyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
          "dojo/dom-construct", "dojo/domReady!"], function(util,
        		  Editor, Button,DateTextBox,Select,Textarea,FilteringSelect,ComboBox,Grid,TextBox
        		  , Pagination,Selection,Keyboard,ColumnResizer,createSyncStore,Memory,DijitRegistry
        		  ,registry, domStyle,declare, dc ) {
	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'offtjdiv');
	var off_comp= new FilteringSelect({id:"off_comp",
		onChange:function(){
		queryVhicById(this.value).then(function(data){
//					console.dir("#####"+data.datas)
			dijit.byId('off_vhic').set('value',"");
			off_vhic.set('store',new dojo.store.Memory({ data: data.datas}));
				})
		},
		options: [],
	                                       style:{"width":"8em","margin-right":"15px"}}).placeAt('offtjdiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'offtjdiv');
	var off_vhic= new FilteringSelect({id:"off_vhic",options: [],required:false,
	                                       style:{"width":"8em","margin-right":"15px"}}).placeAt('offtjdiv');
	var queryCondition = {"off_comp":off_comp,"off_vhic":off_vhic};
	var wsxclbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var comp = dojo.byId("off_comp").value;
				if ('' == comp || ' ' == comp) {
					alert('请输入公司');
					return false;
				}
	        	var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('offtjdiv');
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
			url = "claq/zxcltjexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('offtjdiv');
	var wsx_cls=dc.place(dc.create("span",{id:"wsx_cls",style:{"margin-right":"5px"}}),'offtjdiv');
	queryGs().then(function(data){
		off_comp.set('store',new dojo.store.Memory({ data: data.datas}));
	})
	off_comp.set('queryExpr', "*${0}*"  );
	//添加表格
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'COMP_NAME', 
		    			label : "公司" 
		    		},{field:'VEHI_NO', 
		    			label : "车号" 
		    		},
		    		{field:'STIME', 
		    			label : "汇报时间" 
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'SIM_NUM', 
		    			label : "SIM卡号"
		    		},{field:'OWN_NAME', 
		    			label : "联系人" 
		    		},{field:'OWN_TEL', 
		    			label : "联系电话" 
		    		},{field:'z_state', 
		    			label : "状态" 
		    		}
	];
	
	

	//查询按钮
	var xhrArgs = {
			url : basePath + "claq/zxcltj",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				wsxclbutton.hideWait();
				var on=0;
				for (var i = 0; i < data.length; i++) {
					if(data[i].z_state=='在线'){
						on++;
					}
					data[i] = dojo.mixin({
						id : i + 1
					}, data[i]);
				}
				console.log(new Date())
				store = createSyncStore({
					data : data
				});
				offlineGrid.set('collection', store);
				dijit.byId(wsx_cls).innerHTML="上线率:"+parseFloat(((on/(data.length==0?1:data.length))*100).toFixed(2))+"%";
			}
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	offlineGrid = new CustomGrid({
	   					columns : columns
	   				}, "offlinetable");
});
