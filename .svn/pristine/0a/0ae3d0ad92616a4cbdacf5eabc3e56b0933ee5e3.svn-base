var zcyyGrid;
require([ "app/util","dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select"
	          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
	          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
	          ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
	          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
	          "dojo/dom-construct", "dojo/domReady!"], function(util,
	        		  Editor, Button,DateTextBox,Select,Grid,TextBox
	        		  , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
	        		  ,registry, domStyle,declare, dc ) {

		var xhrArgscomp = {
			url : basePath + "jyxx/findcomp",
//			url:"http://localhost:8080/zhpt/jyxx/findcomp",
			handleAs : "json",
			load : function(data) {
				console.log(data)
				dojo.query('#ysb1 a')[0].innerHTML='车辆数'+ data[4].COUNT+'辆';
				dojo.query('#ysb2 a')[0].innerHTML='车辆数'+ data[3].COUNT+'辆';
				dojo.query('#ysb3 a')[0].innerHTML='车辆数'+ data[5].COUNT+'辆';
				dojo.query('#ysb4 a')[0].innerHTML='车辆数'+ data[1].COUNT+'辆';
				dojo.query('#ysb5 a')[0].innerHTML='车辆数'+ data[2].COUNT+'辆';
				dojo.query('#ysb6 a')[0].innerHTML='车辆数'+ data[0].COUNT+'辆';
				var count=data[0].COUNT+data[1].COUNT+data[2].COUNT+data[3].COUNT+data[4].COUNT+data[5].COUNT;
				dojo.query('#ysb7 a')[0].innerHTML='车辆数'+ count+'辆';
			},
		};
		dojo.xhrPost(xhrArgscomp);
		
		var xhrArgsname = {
				url : basePath + "jyxx/findname",
//				url:"http://localhost:8080/zhpt/jyxx/findname",
				handleAs : "json",
				load : function(data) {
					console.log(data)
					dojo.query('#ysb1 i')[0].innerHTML='司机数'+ data[4].COUNT+'人';
					dojo.query('#ysb2 i')[0].innerHTML='司机数'+ data[3].COUNT+'人';
					dojo.query('#ysb3 i')[0].innerHTML='司机数'+ data[5].COUNT+'人';
					dojo.query('#ysb4 i')[0].innerHTML='司机数'+ data[1].COUNT+'人';
					dojo.query('#ysb5 i')[0].innerHTML='司机数'+ data[2].COUNT+'人';
					dojo.query('#ysb6 i')[0].innerHTML='司机数'+ data[0].COUNT+'人';
					var count=data[0].COUNT+data[1].COUNT+data[2].COUNT+data[3].COUNT+data[4].COUNT+data[5].COUNT;
					dojo.query('#ysb8 a')[0].innerHTML='司机数'+ count+'人';
				},
			};
			dojo.xhrPost(xhrArgsname);
		
			

	dojo.connect(dojo.byId('ysj1'),'dblclick', function () {
		fireNavItem('首汽')
	});
	dojo.connect(dojo.byId('ysj2'),'dblclick', function () {
		fireNavItem('曹操')
	});
	dojo.connect(dojo.byId('ysj3'),'dblclick', function () {
		fireNavItem('滴滴')
	});
	dojo.connect(dojo.byId('ysj4'),'dblclick', function () {
		fireNavItem('优步')
	});
	dojo.connect(dojo.byId('ysj5'),'dblclick', function () {
		fireNavItem('易到')
	});
	dojo.connect(dojo.byId('ysj6'),'dblclick', function () {
		fireNavItem('神州')
	});

});
function fireNavItem(title){
	dojo.query('#mainSplitterLeft_ulzcyy .li2').forEach(function(item,index){
		if(item.innerText == title){item.click()}
	})}