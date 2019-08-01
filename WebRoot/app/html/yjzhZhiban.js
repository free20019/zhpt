var week;
require(["dijit/Dialog", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox"
		 ,"dijit/form/CheckBox"
		 ,"dijit/form/TimeTextBox","dijit/form/SimpleTextarea"
		  ,"dgrid/OnDemandGrid","dijit/form/TextBox","dgrid/extensions/Pagination"
		  ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
		  ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
		  ,"dijit/registry", "dojo/dom-style","dojo/_base/declare"
		  ,"cbtree/Tree","cbtree/models/ForestStoreModel","dojo/data/ItemFileWriteStore"
		  ,"dojo/dom-construct","dojo/on", "app/util","dojo/domReady!"], function(
				  Dialog,Editor, Button,DateTextBox,CheckBox,TimeTextBox,SimpleTextarea,Grid,TextBox
				  , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
				  ,registry, domStyle,declare
				  ,Tree, ForestStoreModel,ItemFileWriteStore
				  , dc,on,util ) {
	var yjzhZbGrid = store = myDialog = null;

// 				dc.place(dc.create("span",{innerHTML:"地点",style:{"margin-right":"5px"}}),'yjzhZbToolBar3');
// 				new TextBox({id:"qd_startTime",style:{"width":"8em","margin-right":"15px"}}).placeAt('yjzhZbToolBar3');
// 				new Button({
// 				        label: "查  询",
// 				        onClick: function(){
// 				        	var postData = {"page":1,"pageSize":pageSize};
// 				        	postData["name"] = dojo.byId('yjzhZb_startTime').value;
// 				        	postData["baId"] = dojo.byId('yjzhZb_endTime').value;
// 				        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
// 				        }
// 				}).placeAt('yjzhZbToolBar3');


	var xhrArgs = {
		//url : "http://192.168.0.226:8080/zhpt/jyxx/findzbb",
		url : basePath + "jyxx/findzbb",

		postData : 'postData={"page":1,"pageSize":100}',
		handleAs : "json",
		load : function(data) {
			week = data.WEEK;
			now = data.NOW;
			//今日值班
			console.log(data)
			dojo.query('.zongzhibanStaff')[0].innerText = "金俊";
//			for (var i = 0; i < week.length; i++) {
				dojo.query('.zhibanStaff')[0].innerText = data.NOW[0].WBNAME+" "+data.NOW[0].WEEK;
//			}
			dojo.query('.dh_tel')[0].innerText = "0571-86445781";
			//本周值班


			for (var i = 0; i < week.length; i++) {
				dojo.query('.name1')[i].innerText =week[i].WBNAME;
				dojo.query('.telephone1')[i].innerText = week[i].WEEK;
			}
			var columns = {
				dojoId : "序号", // give column a custom name
				//name : "姓名",
				name : { label : "姓名" },
				zw : { label : "职务" },
				//miaoshu : { label : "描述" },
				tel : { label : "电话" }
			};


				var CustomGrid = declare([DijitRegistry,Grid, Keyboard, Selection ,ColumnResizer]);
				for(var i=0; i<data.datas.length;  i++){
					data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
			}
				console.log(data.datas)
				store  = new Memory({ data: {
					identifier: 'dojoId',
					label: 'dojoId',
					items: data.datas
				} });

				//new Grid({ columns : columns }, "yjzhZbGridDiv").renderArray(data.datas);
				if(yjzhZbGrid){
					yjzhZbGrid = null;
					dojo.empty('yjzhZbGridDiv')
// 							yjzhZbGrid.destroy();
				}
				yjzhZbGrid= new CustomGrid({
					//className: "dgrid-autoheight",
					collection:  store,
					columns: columns
				},"yjzhZbGridDiv");

		},
		error : function(error) {
			targetNode.innerHTML = "An unexpected error occurred: "
					+ error;
		}
	};

	var deferred = dojo.xhrPost(xhrArgs);
	newDate();
	//todayWatch(jrzb);
	//thisweekWatch(bzzb);
});
//var jrzb = "";//{zzbname: '王五',zbname: '小明  小卢', tel: '0571-86445781', phone: '0571-85461212'};
//var bzzb = [{zbname: '小王', wanname: '小牛'},
//	{zbname: '小牛', wanname: '小刚'},
//	{zbname: '', wanname: ''},
//	{zbname: '小明', wanname: '小吴'},
//	{zbname: '小吴', wanname: '小许'}]
function newDate() {
	var _date = new Date();
	var week = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
	var year = _date.getFullYear();
	var month = _date.getMonth()+1;
	var date = _date.getDate();
	var xq = _date.getDay();
	dojo.query('.year')[0].innerText = year;
	dojo.query('.month')[0].innerText = month;
	dojo.query('.date')[0].innerText = date;
	dojo.query('.week')[0].innerText = week[xq];
}

//function thisweekWatch(data) {
//	for (var i = 0; i < data.length; i++) {
//		dojo.query('.name1')[i].innerText = data[i].zbname;
//		dojo.query('.telephone1')[i].innerText = data[i].wanname;
//	}
//}
	