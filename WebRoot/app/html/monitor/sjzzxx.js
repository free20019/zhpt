var sjzzxxGrid;
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
	dc.place(dc.create("span",{innerHTML:"姓名",style:{"margin-right":"5px"}}),'sjzzxxdiv');
	var zz_name= new TextBox({id:"zz_name",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjzzxxdiv');
	dc.place(dc.create("span",{innerHTML:"从业资格证",style:{"margin-right":"5px"}}),'sjzzxxdiv');
	var zz_no= new TextBox({id:"zz_no",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjzzxxdiv');
	dc.place(dc.create("span",{innerHTML:"业户",style:{"margin-right":"5px"}}),'sjzzxxdiv');
	var zz_yehu= new TextBox({id:"zz_yehu",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjzzxxdiv');
	var queryCondition = {"zz_name":zz_name,"zz_no":zz_no,"zz_yehu":zz_yehu};
	var sjzzxxbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":30};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	console.log(xhrArgs)
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        	console.log(xhrArgs)
	        }
	}).placeAt('sjzzxxdiv');
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
			url = "scjg/sjzzexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('sjzzxxdiv');
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'NAME', 
		    			label : "司机姓名" 
		    		},{field:'ID_NUMBER', 
		    			label : "身份证号"
		    		},{field:'COMPANY_NAME', 
		    			label : "业户"
		    		},{field:'CERTIFICATE_NUMBER',
		    			label : "从业资格证"
		    		},{field:'VALID_PERIOD_END', 
		    			label : "从业资格证有效期"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'CHECK_END_DATE', 
		    			label : "年审有效期"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'MOBILE_PHONE',
		    			label : "手机号"
		    		}
		];
	

		var xhrArgs = {
			url : basePath + "scjg/sjzz",
			postData : 'postData={"page":1,"pageSize":30}',
			handleAs : "json",
			load : function(data) {
				sjzzxxbutton.hideWait();
				console.log(data.count)
					sjzzxxGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					sjzzxxGrid.set('collection',store);
					sjzzxxGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		sjzzxxGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"sjzzxxtable");
		sjzzxxGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(sjzzxxGrid,xhrArgs,queryCondition,30);
		
		dc.place(pageii.first(),'sjzzxxtable','after');
});
