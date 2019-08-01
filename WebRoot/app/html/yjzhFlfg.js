var FlfgEditDialog;
require(
		[ "dijit/Dialog", "dgrid/Editor", "dijit/form/Button",
				"dijit/form/DateTextBox", "dijit/form/CheckBox",
				"dijit/form/TimeTextBox", "dijit/form/SimpleTextarea",
				"dgrid/OnDemandGrid", "dijit/form/TextBox",
				"dgrid/extensions/Pagination", "dgrid/Selection",
				"dgrid/Keyboard", "dgrid/extensions/ColumnResizer",
				"app/createAsyncStore", "dstore/Memory",
				"dgrid/extensions/DijitRegistry", "dijit/registry",
				"dojo/dom-style", "dojo/_base/declare", "cbtree/Tree",
				"cbtree/models/ForestStoreModel",
				"dojo/data/ItemFileWriteStore", "dojo/dom-construct",
				"dojo/on", "app/util", "dojo/domReady!" ],
		function(Dialog, Editor, Button, DateTextBox, CheckBox, TimeTextBox,
				SimpleTextarea, Grid, TextBox, Pagination, Selection, Keyboard,
				ColumnResizer, createAsyncStore, Memory, DijitRegistry,
				registry, domStyle, declare, Tree, ForestStoreModel,
				ItemFileWriteStore, dc, on, util) {
			currentFlfgId = '';
			new Button({
				label : "添加法律法规",
				onClick : function() {
//					dijit.byId('editor1').setValue('');
					if(FlfgEditDialog != null){
						FlfgEditDialog.title = '添加法律法规';
						yjzhFlfgEditorName.value = '';
						FlfgEditDialog.status = 'new';
						dijit.byId('yjzhFlfgEditorContent').setValue('');
						FlfgEditDialog.show();
						return;
					}
					FlfgEditDialog = new Dialog({
						title:'添加法律法规',
//						style:"width:100px; height:200px",
						'href':'app/html/yjzhFlfgEditor.html'

					});
					FlfgEditDialog.status = 'new';
					FlfgEditDialog.show().then(saveOrUpdate);
				}
			}).placeAt('yjzhFlfgToolBar');
			
			function saveOrUpdate(){
				function _cleanFlfg(){
					FlfgEditDialog.hide();
					getFlfgList();
					yjzhFlfgEditorName.value = '';
					dijit.byId('yjzhFlfgEditorContent').setValue('');
				}
				var button = new Button({
					label : "保存",
					onClick : function() {
						console.log('as');
						if(FlfgEditDialog.status == 'new'){
							var name = dijit.byId('yjzhFlfgEditorName').getValue();
							var content = encodeURI(dijit.byId('yjzhFlfgEditorContent').getValue());
							dojo.xhrPost({
								postData : {"name":name,"content":content},
								url : basePath + "yjzh/Flfg/save",
//								url : "http://localhost:8080/zhpt/yjzh/Flfg/save",
								handleAs : "json",
								load : function(data) {
									_cleanFlfg();
								}
							});
						}else{
							var name = dijit.byId('yjzhFlfgEditorName').getValue();
							var content = encodeURI(dijit.byId('yjzhFlfgEditorContent').getValue());
							dojo.xhrPost({
								postData : {"id":currentFlfgId,"content":content},
								url : basePath + "yjzh/Flfg/update",
//								url : "http://localhost:8080/zhpt/yjzh/Flfg/update",
								handleAs : "json",
								load : function(data) {
									_cleanFlfg();
								}
							});
						}
					}
				},'saveYjzhFlfg')
				FlfgEditDialog.button = button;
			}
			
//			new Button({
//				label : "删除预案",
//				onClick : function() {
//					dojo.xhrPost({
//						url : basePath + 'yjzh/Flfg/del?id='+currentFlfgId,
//						load : function(content) {
//							getFlfgList();
//							
//						}
//					})
//				}
//			}).placeAt('yjzhFlfgToolBar');
			
			
			new Button({
				label : "更新预案",
				onClick : function() {
					if(FlfgEditDialog != null){
						yjzhFlfgEditorName.value = '';
						FlfgEditDialog.status = 'update';
						dijit.byId('yjzhFlfgEditorContent').setValue('');
						
					}else{
						FlfgEditDialog = new Dialog({
							title:'更新 应急预案',
							'href':'app/html/yjzhFlfgEditor.html'
						});
					}
					FlfgEditDialog.title = '更新 应急预案';
					FlfgEditDialog.status = 'update';
					FlfgEditDialog.show().then(function(){
						dojo.xhrPost({
							url : basePath + 'yjzh/Flfg/getById?id='+currentFlfgId,
//							url : "http://localhost:8080/zhpt/yjzh/Flfg/getById?id="+currentFlfgId,
							handleAs : "json",
							load : function(data) {
								yjzhFlfgEditorName.value = data.name;
								dijit.byId('yjzhFlfgEditorContent').setValue(decodeURI(data.content));
							}
						})
						;
						saveOrUpdate();
					});
				}
			}).placeAt('yjzhFlfgToolBar');
			
			//预案列表
			getFlfgList = function(){
				dojo.xhrPost({
					url : basePath + "yjzh/Flfg/getAllNames",
//					url : "http://localhost:8080/zhpt/yjzh/Flfg/getAllNames",
					handleAs : "json",
					load : function(data) {
						dojo.byId('yjzhFlfgContent').innerHTML='';
						console.log(data);
						dojo.empty(FlfgList);
						for(var i=0;i<data.length;i++){
							var li = dc.create('li',{innerHTML:data[i][1],id:data[i][0]});
							dojo.place(li,'FlfgList');
							dojo.connect(li, "click",  function(){
								currentFlfgId=this.id;
								dojo.xhrPost({
									url : basePath + 'yjzh/Flfg/getContent?id='+this.id,
//									url : "http://localhost:8080/zhpt/yjzh/Flfg/getContent?id="+this.id,
									load : function(content) {
										dijit.byId('yjzhFlfgContent').set('content',decodeURI(content));
										dojo.byId(currentAlkId).style.background='rgba(51, 133, 255, 0.19)';
									}
								});
							});
						}
						currentFlfgId=data[0][0];
						dojo.xhrPost({
							url : basePath + 'yjzh/Flfg/getContent?id='+data[0][0],
//							url : "http://localhost:8080/zhpt/yjzh/Flfg/getContent?id="+data[0][0],
							load : function(content) {
								dijit.byId('yjzhFlfgContent').set('content',decodeURI(content));
							}
						});
					}
				});
				
			};
			
			getFlfgList();
		});
