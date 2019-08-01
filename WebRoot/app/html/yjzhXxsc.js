require(["dijit/Dialog", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox"
			         ,"dijit/form/CheckBox"
			         ,"dijit/form/TimeTextBox","dijit/form/SimpleTextarea"
			          ,"dgrid/OnDemandGrid","dijit/form/TextBox","dgrid/extensions/Pagination"
			          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
			          ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
			          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare"
			          ,"cbtree/Tree","cbtree/models/ForestStoreModel","dojo/data/ItemFileWriteStore"
			          ,"dojo/dom-construct","dojo/on", "app/util","app/createSyncStore","dojo/domReady!"], function(
			        		  Dialog,Editor, Button,DateTextBox,CheckBox,TimeTextBox,SimpleTextarea,Grid,TextBox
			        		  , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
			        		  ,registry, domStyle,declare
			        		  ,Tree, ForestStoreModel,ItemFileWriteStore
			        		  , dc,on,util,createSyncStore ) {
			var yjzhXxscGrid = store = myDialog = null;
			
				dc.place(dc.create("span",{innerHTML:"主题",style:{"margin-right":"5px"}}),'yjzhXxscToolBar3');
				var sjzt=new TextBox({id:"xxsc_sjzt",style:{"width":"8em","margin-right":"15px"}}).placeAt('yjzhXxscToolBar3');
				var queryCondition = {"sjzt":sjzt};
				var yjzhXxscbutton=new Button({
			        label: "查  询",
			        onClick: function(){
						this.showWait();
						var postData = {"page":1,"pageSize":pageSize};
						for(var o in queryCondition){
							if(o == "xxsc_sjzt" ){
								postData[o] = dojo.byId(queryCondition[o].id).value;
							}else{
								postData[o] = queryCondition[o].value;
							}
						}
			        	dojo.xhrPost(dojo.mixin(xhrArgsyjsjjr,{"postData":'sjzt='+dijit.byId("xxsc_sjzt").value}));
			        }
			}).placeAt('yjzhXxscToolBar3');
//	new Button({
//		label : "导  出",
//		onClick : function() {
//			var postData = {
//				"page" : 1,
//				"pageSize" : 10000
//			};
//			for(var o in queryCondition){
//				if(o == "xxsc_startTime" ){
//					postData[o] = dojo.byId(queryCondition[o].id).value;
//				}else{
//					postData[o] = queryCondition[o].value;
//				}
//			}
//			url = "yjzh/sj/getexcle?postData="
//				+ dojo.toJson(postData), window.open(url)
//		}
//	}).placeAt('yjzhXxscToolBar3');
			//queryCondition = {"address":address}

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
		SJJB : {
			label : "事件级别"
		},
		SH : {
			label : "状态"
			,formatter : util.formatSH_STATUS
		}
	};
	var sjzt=dijit.byId("xxsc_sjzt").value;
			//var xhrArgs = {
				//url : basePath + "yjzh/sj/get",
				//postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
				//handleAs : "json",
				//load : function(data) {
				//	console.log(data)
				//	var columns = {
				//		dojoId : "序号", // give column a custom name
				//		//name : "姓名",
				//		sj : { label : "时间" ,formatter:util.formatYYYYMMDD},
				//		address : { label : "地点" },
				//		//miaoshu : { label : "描述" },
				//		lb : { label : "类别" },
				//		status : { label : "状态" }
				//};
			//};
	xhrArgsyjsjjr = {
		url : basePath + "jyxx/jr",
//		url:"http://localhost:8080/zhpt/jyxx/jr",
		postData : "sjzt="+sjzt,
		handleAs : "json",
		load : function(data) {
			yjzhXxscbutton.hideWait();
			console.log(data)
			for (var i = 0; i < data.length; i++) {
				data[i] = dojo.mixin({
					id : i + 1
				}, data[i]);
			}
			store = createSyncStore({
				data : data
			});
			yjzhXxscGrid.set('collection', store);
			var length=data.length-1;
			dojo.byId("xxsc_sjmiaoshu").innerHTML=data[length].SJJL;
			dojo.byId("xxsc_content").innerHTML=data[length].SJNR;
		},
	};
						
						var CustomGrid = declare([DijitRegistry,Grid, Keyboard, Selection ,ColumnResizer]);
//						for(var i=0; i<data.datas.length;  i++){
//		    		    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
//		    		    }
//						console.log(data.datas)
//						store  = new Memory({ data: {
//							identifier: 'dojoId',
//							label: 'dojoId',
//							items: data.datas
//						} });
//
//						//new Grid({ columns : columns }, "yjzhXxscGridDiv").renderArray(data.datas);
//						if(yjzhXxscGrid){
//							yjzhXxscGrid = null;
//							dojo.empty('yjzhXxscGridDiv3')
//// 							yjzhXxscGrid.destroy();
//						}
						yjzhXxscGrid= new CustomGrid({
							//className: "dgrid-autoheight",
							//collection:  store,
							columns: columns
						},"yjzhXxscGridDiv3");
						console.log(yjzhXxscGrid);
						yjzhXxscGrid.on('.dgrid-row:click', function (event) {
						    var row = yjzhXxscGrid.row(event);
						    console.log('Row clicked:', row.id+'\t'+event);
						    dojo.byId('xxsc_content').innerHTML = row.data.SJNR;
						    dojo.byId('xxsc_sjmiaoshu').innerHTML = row.data.SJJL;
						});
						//dojo.byId('xxsc_sjmiaoshu').innerHTML =
						//	yjzhXxscGrid.collection.data[0].miaoshu ==''?'':yjzhXxscGrid.collection.data[0].miaoshu
			dojo.xhrPost(xhrArgsyjsjjr);


	//var xhrArgssjnr = {
	//	url: basePath + "yjzh/findjtsj ",
	//	handleAs: "json",
	//	load: function (data) {
	//		sjclqyjk=data;
	//		dojo.byId('xxsc_content').innerText=sjclqyjk[0].SJNR;
	//	}
	//}
	//dojo.xhrPost(xhrArgssjnr);
			new Button({
		        label: "保存",
		        onClick: function(){
		            // Do something:
// 		            dom.byId("result1").innerHTML += "Thank you! ";
		        }
		    }, "xxsc_bc").startup();
			new Button({
		        label: "审核",
		        onClick: function(){
		            // Do something:
// 		            dom.byId("result1").innerHTML += "Thank you! ";
		        }
		    }, "xxsc_sh").startup();
			
			//end
			});