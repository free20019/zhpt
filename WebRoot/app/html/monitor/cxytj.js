var cxytjGrid;
require([ "app/util", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select","dijit/form/Textarea","dijit/form/FilteringSelect"
          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
          ,"app/createSyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
          "dojo/dom-construct", "dojo/domReady!"], function(util,
        		  Editor, Button,DateTextBox,Select,Textarea,FilteringSelect,Grid,TextBox
        		  , Pagination,Selection,Keyboard,ColumnResizer,createSyncStore,Memory,DijitRegistry
        		  ,registry, domStyle,declare, dc ) {
	var  store = null ,count = 0;
	console.log(111);
	dc.place(dc.create("span",{innerHTML:"主题",style:{"margin-right":"5px"}}),'cxytjdiv');
	var cxytj_SJZT= new TextBox({id:"cxytj_sjzt",style:{"width":"8em","margin-right":"15px"}}).placeAt('cxytjdiv');
	var queryCondition = {"sjzt":cxytj_sjzt};
	var cxytjbutton=new Button({
		label: "查询",
        onClick: function(){
			this.showWait();
			var postData = {"page":1,"pageSize":pageSize};

			for(var o in queryCondition){
				postData[o] = queryCondition[o].value;
			}
        	dojo.xhrPost(dojo.mixin(xhrArgsyjsjjrl,{"postData":'sjzt='+dijit.byId("cxytj_sjzt").value}));
        }
	}).placeAt('cxytjdiv');
//	new Button({
//		label : "导  出",
//		onClick : function() {
//			var postData = {
//				"page" : 1,
//				"pageSize" : 10000
//			};
//			for(var o in queryCondition){
//				postData[o] = queryCondition[o].value;
//			}
//			url = "yjzh/sj/getexcle?postData="
//				+ dojo.toJson(postData), window.open(url)
//		}
//	}).placeAt('cxytjdiv');
	
	var columns = {
			id : "序号",
			TIME : {
				label : "时间",
				formatter : util.formatYYYYMMDD
			},
			SJZT : {
				label : "主题"
			},
			BJR : {
				label : "报警人"
			},
			DJDH : {
				label : "报警人电话"
			},
			JJR : {
				label : "接警人"
			},
			SH : {
				label : "状态"
					,formatter : util.formatSH_STATUS
			}
		};
	

	var sjzt=dijit.byId("cxytj_sjzt").value;
	//查询按钮
	xhrArgsyjsjjrl = {
			url : basePath + "jyxx/jr",
//			url:"http://localhost:8080/zhpt/jyxx/jr",
			postData : "sjzt="+sjzt,
			handleAs : "json",
			load : function(data) {
				cxytjbutton.hideWait();
				console.log(data)
				for (var i = 0; i < data.length; i++) {
					data[i] = dojo.mixin({
						id : i + 1
					}, data[i]);
				}
				store = createSyncStore({
					data : data
				});
				cxytjGrid.set('collection', store);
				var length=data.length-1;
				dojo.byId("sjmiaoshu_cxytj").innerHTML=data[length].SJJL;
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	cxytjGrid = new CustomGrid({
	   					columns : columns
	   				}, "cxytjtable");
	
	cxytjGrid.on('.dgrid-row:click', function(event) {
		var row = cxytjGrid.row(event);
		console.log(row.data.SJNR)
		dojo.byId("sjmiaoshu_cxytj").innerHTML=row.data.SJJL;
	});	
	dojo.xhrPost(xhrArgsyjsjjrl);
});
