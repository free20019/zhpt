var jyxxGrid;
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

		var xhrArgs = {
			url : basePath + "jyxx/findnowmonthall",
			handleAs : "json",
			load : function(data) {
				console.log(data)
				dojo.query('#title-img #ysj2 a')[0].innerHTML='交易'+data.NOW.split(',')[1]+'笔';
				dojo.query('#title-img #ysj2 a')[1].innerHTML='交易'+data.NOW.split(',')[0]+'元';
				
				dojo.query('#title-img #ysj3 a')[0].innerHTML='交易'+data.NOW.split(',')[1]+'笔';
				dojo.query('#title-img #ysj7 a')[0].innerHTML='交易'+data.NOW.split(',')[0]+'元';
				
				dojo.query('#title-img #ysj4 a')[0].innerHTML='交易'+data.MONTH.split(',')[1]+'笔';
				dojo.query('#title-img #ysj6 a')[0].innerHTML='交易'+data.MONTH.split(',')[0]+'元';
			}
		};
		dojo.xhrPost(xhrArgs);
});
