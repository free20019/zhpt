var AlkEditDialog;
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
			currentAlkId = '';
			new Button({
				label : "添加法律法规",
				onClick : function() {
//					dijit.byId('editor1').setValue('');
					if(AlkEditDialog != null){
						AlkEditDialog.title = '添加法律法规';
						yjzhAlkEditorName.value = '';
						AlkEditDialog.status = 'new';
						dijit.byId('yjzhAlkEditorContent').setValue('');
						AlkEditDialog.show();
						return;
					}
					AlkEditDialog = new Dialog({
						title:'添加法律法规',
//						style:"width:100px; height:200px",
						'href':'app/html/yjzhAlkEditor.html'

					});
					AlkEditDialog.status = 'new';
					AlkEditDialog.show().then(saveOrUpdate);
				}
			}).placeAt('yjzhAlkToolBar');
			
			function saveOrUpdate(){
				function _cleanAlk(){
					AlkEditDialog.hide();
					getAlkList();
					yjzhAlkEditorName.value = '';
					dijit.byId('yjzhAlkEditorContent').setValue('');
				}
				var button = new Button({
					label : "保存",
					onClick : function() {
						console.log('as');
						if(AlkEditDialog.status == 'new'){
							var name = dijit.byId('yjzhAlkEditorName').getValue();
							var content = encodeURI(dijit.byId('yjzhAlkEditorContent').getValue());
							dojo.xhrPost({
								postData : {"name":name,"content":content},
								url : basePath + "yjzh/Alk/save",
//								url : "http://localhost:8080/zhpt/yjzh/Alk/save",
								handleAs : "json",
								load : function(data) {
									_cleanAlk();
								}
							});
						}else{
							var name = dijit.byId('yjzhAlkEditorName').getValue();
							var content = encodeURI(dijit.byId('yjzhAlkEditorContent').getValue());
							dojo.xhrPost({
								postData : {"id":currentAlkId,"content":content},
								url : basePath + "yjzh/Alk/update",
//								url : "http://localhost:8080/zhpt/yjzh/Alk/update",
								handleAs : "json",
								load : function(data) {
									_cleanAlk();
								}
							});
						}
					}
				},'saveYjzhAlk')
				AlkEditDialog.button = button;
			}
			
//			new Button({
//				label : "删除预案",
//				onClick : function() {
//					dojo.xhrPost({
//						url : basePath + 'yjzh/Alk/del?id='+currentAlkId,
//						load : function(content) {
//							getAlkList();
//							
//						}
//					})
//				}
//			}).placeAt('yjzhAlkToolBar');
			
			
			new Button({
				label : "更新预案",
				onClick : function() {
					if(AlkEditDialog != null){
						yjzhAlkEditorName.value = '';
						AlkEditDialog.status = 'update';
						dijit.byId('yjzhAlkEditorContent').setValue('');
						
					}else{
						AlkEditDialog = new Dialog({
							title:'更新 应急预案',
							'href':'app/html/yjzhAlkEditor.html'
						});
					}
					AlkEditDialog.title = '更新 应急预案';
					AlkEditDialog.status = 'update';
					AlkEditDialog.show().then(function(){
						dojo.xhrPost({
							url : basePath + 'yjzh/Alk/getById?id='+currentAlkId,
//							url : "http://localhost:8080/zhpt/yjzh/Alk/getById?id="+currentAlkId,
							handleAs : "json",
							load : function(data) {
								yjzhAlkEditorName.value = data.name;
								dijit.byId('yjzhAlkEditorContent').setValue(decodeURI(data.content));
							}
						})
						;
						saveOrUpdate();
					});
				}
			}).placeAt('yjzhAlkToolBar');
			
			//预案列表
			getAlkList = function(){
				dojo.xhrPost({
					url : basePath + "yjzh/Alk/getAllNames",
//					url : "http://localhost:8080/zhpt/yjzh/Alk/getAllNames",
					handleAs : "json",
					load : function(data) {
						dojo.byId('yjzhAlkContent').innerHTML='';
						console.log(data);
						dojo.empty(AlkList);
						for(var i=0;i<data.length;i++){
							var li = dc.create('li',{innerHTML:data[i][1],id:data[i][0]});
							dojo.place(li,'AlkList');
							dojo.connect(li, "click",  function(){
								currentAlkId=this.id;
								dojo.xhrPost({
									url : basePath + 'yjzh/Alk/getContent?id='+this.id,
//									url : "http://localhost:8080/zhpt/yjzh/Alk/getContent?id="+this.id,
									load : function(content) {
										dijit.byId('yjzhAlkContent').set('content',decodeURI(content));
										dojo.byId(currentAlkId).style.background='rgba(51, 133, 255, 0.19)';
									}
								});
							});
						}
						currentAlkId=data[0][0];
						dojo.xhrPost({
							url : basePath + 'yjzh/Alk/getContent?id='+data[0][0],
//							url : "http://localhost:8080/zhpt/yjzh/Alk/getContent?id="+data[0][0],
							load : function(content) {
								dijit.byId('yjzhAlkContent').set('content',decodeURI(content));
								
							}
						});
					}
				});
				
			};
			
			getAlkList();
		});
