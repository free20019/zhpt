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
			currentYjyaId = '';
			new Button({
				label : "新建预案",
				onClick : function() {
//					dijit.byId('editor1').setValue('');
					if(yjyaEditDialog != null){
						yjyaEditDialog.title = '添加 应急预案'
						yjzhYjyaEditorName.value = '';
						yjyaEditDialog.status = 'new'
						dijit.byId('yjzhYjyaEditorContent').setValue('')
						yjyaEditDialog.show();
						return;
					}
					yjyaEditDialog = new Dialog({
						title:'添加 应急预案',
//						style:"width:100px; height:200px",
						'href':'app/html/yjzhYjyaEditor.html'

					});
					yjyaEditDialog.status = 'new';
					yjyaEditDialog.show().then(saveOrUpdate);
				}
			}).placeAt('yjzhYaToolBar');
			
			function saveOrUpdate(){
				function _cleanYjya(){
					yjyaEditDialog.hide();
					getYjyaList();
					yjzhYjyaEditorName.value = '';
					dijit.byId('yjzhYjyaEditorContent').setValue('')
				}
				var button = new Button({
					label : "保存",
					onClick : function() {
						console.log('as')
						if(yjyaEditDialog.status == 'new'){
							var name = dijit.byId('yjzhYjyaEditorName').getValue();
							var content = encodeURI(dijit.byId('yjzhYjyaEditorContent').getValue());
							dojo.xhrPost({
								postData : {"name":name,"content":content},
								url : basePath + "yjzh/yjya/save",
								handleAs : "json",
								load : function(data) {
									_cleanYjya();
								}
							})
						}else{
							var name = dijit.byId('yjzhYjyaEditorName').getValue();
							var content = encodeURI(dijit.byId('yjzhYjyaEditorContent').getValue());
							dojo.xhrPost({
								postData : {"id":currentYjyaId,"content":content},
								url : basePath + "yjzh/yjya/update",
								handleAs : "json",
								load : function(data) {
									_cleanYjya();
								}
							})
						}
					}
				},'saveYjzhYjya')
				yjyaEditDialog.button = button;
			}
			
//			new Button({
//				label : "删除预案",
//				onClick : function() {
//					dojo.xhrPost({
//						url : basePath + 'yjzh/yjya/del?id='+currentYjyaId,
//						load : function(content) {
//							getYjyaList();
//							
//						}
//					})
//				}
//			}).placeAt('yjzhYaToolBar');
			
			
			new Button({
				label : "更新预案",
				onClick : function() {
					if(yjyaEditDialog != null){
						yjzhYjyaEditorName.value = '';
						yjyaEditDialog.status = 'update'
						dijit.byId('yjzhYjyaEditorContent').setValue('')
						
					}else{
						yjyaEditDialog = new Dialog({
							title:'更新 应急预案',
							'href':'app/html/yjzhYjyaEditor.html'
						});
					}
					yjyaEditDialog.title = '更新 应急预案'
					yjyaEditDialog.status = 'update'
					yjyaEditDialog.show().then(function(){
						dojo.xhrPost({
							url : basePath + 'yjzh/yjya/getById?id='+currentYjyaId,
							handleAs : "json",
							load : function(data) {
								yjzhYjyaEditorName.value = data.name;
								dijit.byId('yjzhYjyaEditorContent').setValue(decodeURI(data.content))
							}
						})
						
						saveOrUpdate();
					});
				}
			}).placeAt('yjzhYaToolBar');
			
			//预案列表
			getYjyaList = function(){
				dojo.xhrPost({
					url : basePath + "yjzh/yjya/getAllNames",
					handleAs : "json",
					load : function(data) {
						dojo.byId('yjzhYjyaContent').innerHTML='';
						console.log(data)
						dojo.empty(yjyaList);
						for(var i=0;i<data.length;i++){
							var li = dc.create('li',{innerHTML:data[i][1],id:data[i][0]});
							dojo.place(li,'yjyaList');
							dojo.connect(li, "click",  function(){
								currentYjyaId=this.id
								dojo.xhrPost({
									url : basePath + 'yjzh/yjya/getContent?id='+this.id,
									load : function(content) {
										dijit.byId('yjzhYjyaContent').set('content',decodeURI(content));
										dojo.byId(currentAlkId).style.background='rgba(51, 133, 255, 0.19)';
									}
								})
							})
						}
						currentYjyaId=data[0][0];
						dojo.xhrPost({
							url : basePath + 'yjzh/yjya/getContent?id='+data[0][0],
							load : function(content) {
								dijit.byId('yjzhYjyaContent').set('content',decodeURI(content))
							}
						})
					}
				})
				
			}
			
			getYjyaList();
		});
