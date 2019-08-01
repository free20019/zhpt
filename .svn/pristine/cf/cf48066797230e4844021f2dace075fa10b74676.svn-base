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
	dc.place(dc.create("span",{innerHTML:"姓名",style:{"margin-right":"5px"}}),'findrenyuandiv');
	var rygs_id= new TextBox({id:"rygs_id"}).placeAt('findrenyuandiv');
	dc.place(dc.create("span",{innerHTML:"身份证号",style:{"margin-right":"5px"}}),'findrenyuandiv');
	var ren_no= new TextBox({id:"ren_no",style:{"width":"8em","margin-right":"15px"}}).placeAt('findrenyuandiv');
	var queryCondition = {"name":rygs_id,"id_number":ren_no};
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
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		       		{field:'NAME', 
		    			label : "姓名" 
		    		},{field:'SEX',
		    			label : "性别"
		    		},{field:'ID_NUMBER',
		    			label : "身份证号"
		    		},{field:'MOBILE_PHONE',
		    			label : "手机号码"
		    		},{field:'CURRENT_ADDRESS',
		    			label : "现居住地"
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


