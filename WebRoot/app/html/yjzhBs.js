
require(
	[ "dijit/layout/TabContainer", "dijit/layout/ContentPane",
		"dijit/Dialog", "dgrid/Editor", "dijit/form/Button",
		"dijit/form/DateTextBox", "dijit/form/CheckBox",
		"dijit/form/TimeTextBox", "dijit/form/SimpleTextarea",
		"dgrid/OnDemandGrid", "dijit/form/TextBox",
		"dgrid/extensions/Pagination", "dgrid/Selection", "dgrid/Keyboard",
		"dgrid/extensions/ColumnResizer", "app/createAsyncStore",
		"dstore/Memory","dojo/store/Memory", "dgrid/extensions/DijitRegistry",
		"dijit/registry", "dojo/dom-style", "dojo/_base/declare",
		"cbtree/Tree", "cbtree/models/ForestStoreModel","cbtree/model/TreeStoreModel",
		"dojo/data/ItemFileWriteStore", "dojo/dom-construct", "dojo/on",
		"app/util","app/createSyncStore", "dojo/domReady!"
	],
			function(TabContainer, ContentPane, Dialog, Editor, Button,
					DateTextBox, CheckBox, TimeTextBox, SimpleTextarea, Grid,
					TextBox, Pagination, Selection, Keyboard, ColumnResizer,
					createAsyncStore, Memory,Memory2, DijitRegistry, registry,
					domStyle, declare, Tree, ForestStoreModel,TreeStoreModel,
					ItemFileWriteStore, dc, on, util,createSyncStore) {
				var yhGrid = store = myDialog = queryCondition= null;
				dc.place(dc.create("span", {
					innerHTML : "主题",
					style : {
						"margin-right" : "5px"
					}
				}), 'yhToolBar3');
				var address = new TextBox({
					id : "bs_sjzt",
					style : {
						"width" : "8em",
						"margin-right" : "15px"
					}
				}).placeAt('yhToolBar3');
				var yjzhBsbutton=new Button({
					label : "查  询",
					onClick : function() {
						this.showWait();
						var postData = {
							"page" : 1,
							"pageSize" : 100
						};
						for(var o in queryCondition){
				        	postData[o] = queryCondition[o].value;
			        	}
			        	dojo.xhrPost(dojo.mixin(xhrArgsyjsjjr,{"postData":'sjzt='+dijit.byId("bs_sjzt").value}));
					}
				}).placeAt('yhToolBar3');
//				new Button({
//					label : "导  出",
//					onClick : function() {
//						var postData = {
//							"page" : 1,
//							"pageSize" : 10000
//						};
//						for(var o in queryCondition){
//							postData[o] = queryCondition[o].value;
//						}
//						url = "yjzh/sj/getexcle?postData="
//							+ dojo.toJson(postData), window.open(url)
//					}
//				}).placeAt('yhToolBar3');
				//queryCondition = {"address":address}
				
				//var columns = {
				//		dojoId : "序号", // give column a custom name
				//		//name : "姓名",
				//		sj : {
				//			label : "时间",
				//			formatter : util.formatYYYYMMDD
				//		},
				//		address : {
				//			label : "地点"
				//		},
				//		lb : {
				//			label : "类别"
				//		},
				//		status : {
				//			label : "状态"
				//		}
				//};
				//
				//var xhrArgs = {
				//	url : basePath + "yjzh/sj/get",
				//	postData : 'postData={"page":1,"pageSize":100}',
				//	handleAs : "json",
				//	load : function(data) {
				//		yjzhBsbutton.hideWait();
				//		console.log(data)
				//		var CustomGrid = declare([ DijitRegistry, Grid,
				//				Keyboard, Selection, ColumnResizer ]);
				//		for (var i = 0; i < data.datas.length; i++) {
				//			data.datas[i] = dojo.mixin({
				//				dojoId : i + 1
				//			}, data.datas[i]);
				//		}
				//		console.log(data.datas)
				//		store = new Memory({
				//			data : {
				//				identifier : 'dojoId',
				//				label : 'dojoId',
				//				items : data.datas
				//			}
				//		});
                //
				//		//new Grid({ columns : columns }, "yhGridDiv").renderArray(data.datas);
				//		if (yhGrid) {
				//			yhGrid = null;
				//			dojo.empty('yhGridDiv3')
				//			// 							yhGrid.destroy();
				//		}
				//		yhGrid = new CustomGrid({
				//			//className: "dgrid-autoheight",
				//			collection : store,
				//			columns : columns
				//		}, "yhGridDiv3");
				//		console.log(yhGrid);
				//		yhGrid
				//				.on(
				//						'.dgrid-row:click',
				//						function(event) {
				//							var row = yhGrid.row(event);
				//							console.log('Row clicked:', row.id
				//									+ '\t' + event);
				//							dojo.byId('bs_sjmiaoshu').innerHTML = row.data['miaoshu']
				//						});
				//		dojo.byId('bs_sjmiaoshu').innerHTML
				//			= yhGrid.collection.data[0].miaoshu == '' ? ''
				//				: yhGrid.collection.data[0].miaoshu
                //
				//	},
				//	error : function(error) {
				//		targetNode.innerHTML = "An unexpected error occurred: "
				//				+ error;
				//	}
				//};
                //
				//var deferred = dojo.xhrPost(xhrArgs);
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

				var sjzt=dijit.byId("bs_sjzt").value;
				xhrArgsyjsjjr = {
					url : basePath + "jyxx/jr",
//					url:"http://localhost:8080/zhpt/jyxx/jr",
					postData : "sjzt="+sjzt,
					handleAs : "json",
					load : function(data) {
						yjzhBsbutton.hideWait();
						console.log(data)
						for (var i = 0; i < data.length; i++) {
							data[i] = dojo.mixin({
								id : i + 1
							}, data[i]);
						}
						store = createSyncStore({
							data : data
						});
						yhGrid.set('collection', store);
						var length=data.length-1;
						dojo.byId("bs_sjmiaoshu").innerHTML=data[length].SJJL;
					},
				};
				var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
					Selection, ColumnResizer, Editor ]);
				yhGrid = new CustomGrid({
					columns : columns
				}, "yhGridDiv3");
				yhGrid.on('.dgrid-row:click', function(event) {
					var row = yhGrid.row(event);
					console.log(row.data.SJNR)
					dojo.byId("bs_sjmiaoshu").innerHTML=row.data.SJJL;
				});
				dojo.xhrPost(xhrArgsyjsjjr);


				//tree start
				function checkBoxClicked(item, nodeWidget, evt) {
 					console.log("The new state for " + this.getLabel(item) + " is: "
 							+ nodeWidget.get("checked"));
				}
//				var storeTree = new ItemFileWriteStore({
//					data : {
//						"identifier" : "name",
//						"label" : "name",
//						"items" : dojo.clone(sendMessageConfig)
//
//					}
//				});
				var storeTree = new Memory2( { data: dojo.clone(sendMessageConfig) });
				var model = new TreeStoreModel({
					store : storeTree,
					query : {
						parent : ''
					}
				});
				console.log('init tree...')
				if(!fstree){
					
				}
				fstree= new Tree({
					model : model,
					branchIcons : true,
					branchReadOnly : false,
					checkBoxes : true,
					nodeIcons : true
				}).placeAt('sj_bs','last');
				fstree.expandAll();
				dojo.connect(fstree, "onCheckBoxClick", model, checkBoxClicked);
				//tree end
				g_tree= fstree
				if(!fstree){
					
				}else{
//					fstree.set('model',model);
//					fstree.model.store.close();
//					fstree.store.fetch({query: {docName: "*"}});
//					fstree._refresh();
				};
				
				g_model = model,g_store = storeTree;

				var checkBox1 = new CheckBox({
					name : "checkBox",
					value : "yj",
					checked : false,
					onChange : function(b) {
//			 						alert('onChange called with parameter = ' + b
//			 								+ ', and widget value = ' + this.get('value'));
					}
				}, "bs_checkBox1").startup();

				var checkBox2 = new CheckBox({
					name : "checkBox",
					value : "yj",
					checked : false,
					onChange : function(b) {
// 						alert('onChange called with parameter = ' + b
// 								+ ', and widget value = ' + this.get('value'));
					}
				}, "bs_checkBox2").startup();

				var fs = new Button({
					label : "发送",
					onClick : function() {
						var tels = "";
						var mail = "";
						dojo.query('.cbtreeCheckBoxChecked').forEach(function(node){
							var aa = dojo.query('input',node);
							for(var i=0;i<aa.length;i++){
								var item = dijit.getEnclosingWidget(aa[i]);
								console.log(item)
								console.log(item.value);
								if(item.item.tel){
									tels+=item.item.tel+",";
									console.log(tels);
								}
								if(item.item.mail){
									mail+=item.item.mail+",";
									console.log(mail);
								}
							}
						});
						if((document.getElementById('bs_checkBox1').getAttribute('aria-checked'))==="true"){
							console.log(222)
							var xhrArgs = {
									url : basePath + "common/sendInfo",
									postData : 'tels='+tels+'&message=短信test123',
									handleAs : "json",
									load : function(data) {
										alert('短信发送成功');
									}
								};
								dojo.xhrPost(xhrArgs);
						}
						if((document.getElementById('bs_checkBox2').getAttribute('aria-checked'))==="true"){
							console.log(111)
							var mailxhrArgs = {
									url : basePath + "common/sendMail",
//									url : "http://localhost:8080/zhpt/common/sendMail",
									postData : 'mail=1186927067@qq.com&message=邮件test',
									handleAs : "json",
									load : function(data) {
										alert('邮件发送成功');
									}
							};
							dojo.xhrPost(mailxhrArgs);
							}	
						}
				}, "bs_fs").startup();

				//end
			});
