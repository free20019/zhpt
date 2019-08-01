var findrenyuanGrid;
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
	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'findrenyuandiv');
	var rygs_id= new FilteringSelect({id:"rygs_id",
		onChange:function(){
			dijit.byId('ryvehi_comp').set('value',"");
			query_sjzx_VhicById(this.value).then(function(data){
//						console.dir("#####"+data.datas)
				ryvehi_comp.set('store',new dojo.store.Memory({ data: data.datas}));
					})
			},
		options: [],style:{"width":"8em","margin-right":"15px"}}).placeAt('findrenyuandiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'findrenyuandiv');
	var ryvehi_comp= new FilteringSelect({id:"ryvehi_comp",
		options: [],style:{"width":"8em","margin-right":"15px"}
	,queryExpr:"*${0}*"
	,store:new dojo.store.Memory({ data: []})
	,validator: function(value, constraints){
        return true;
    }
	,onKeyUp:function(){
		console.log('##'+this.textbox.value,this)
		if(this.textbox.value.length >= 2){
			query_sjzx_Vhics(this.textbox.value).then(function(data){
				ryvehi_comp.set('store',new dojo.store.Memory({ data: data.datas}));
			})
		}
	}
	}).placeAt('findrenyuandiv');
	dc.place(dc.create("span",{innerHTML:"司机姓名",style:{"margin-right":"5px"}}),'findrenyuandiv');
	var ren_no= new TextBox({id:"ren_no",style:{"width":"8em","margin-right":"15px"}}).placeAt('findrenyuandiv');
	var queryCondition = {"gs_id":rygs_id,"vehi_no":ryvehi_comp,"ren_no":ren_no};
	var findrenyuanbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('findrenyuandiv');
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
			url = "hygl/findrenyuanexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('findrenyuandiv');
	query_sjzx_Gs().then(function(data){
//		console.dir("#####"+data.datas)
		rygs_id.set('store',new dojo.store.Memory({ data: data.datas}));
	})
		rygs_id.set('queryExpr', "*${0}*"  )
//	query_sjzx_Vhic().then(function(data){
//		ryvehi_comp.set('store',new dojo.store.Memory({ data: data.datas}));
//	})
//	ryvehi_comp.set('queryExpr', "*${0}*"  );
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		       		{field:'COMPANY_NAME', 
		    			label : "公司名" 
		    		},{field:'PLATE_NUMBER',
		    			label : "车号"
		    		},{field:'NAME',
		    			label : "联系人"
		    		},{field:'MOBILE_PHONE',
		    			label : "联系电话"
		    		}
		];

		var xhrArgs = {
			url : basePath + "hygl/findrenyuan",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				findrenyuanbutton.hideWait();
				console.log(data.count)
					findrenyuanGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					findrenyuanGrid.set('collection',store);
					findrenyuanGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		findrenyuanGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"findrenyuantable");
		findrenyuanGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(findrenyuanGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'findrenyuantable','after');
});


