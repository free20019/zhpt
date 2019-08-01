

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
				var yjzsGrid = store = myDialog = null;
			
				//new TextBox({id:"yjzs_startTime",style:{"width":"8em","margin-right":"15px"}}).placeAt('yjzsToolBar3');
				//var yjzhYjzsbutton=new Button({
				//        label: "查  询",
				//        onClick: function(){
				//			this.showWait();
				//			var postData = {"page":1,"pageSize":pageSize};
				//        	postData["name"] = dojo.byId('yjzs_startTime').value;
				//        	postData["baId"] = dojo.byId('yjzs_endTime').value;
				//        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
				//        }
				//}).placeAt('yjzsToolBar3');
				new Button({
			        label: "添  加",
			        onClick: function(){
// 			        	var postData = {"page":1,"pageSize":pageSize};
// 			        	postData["name"] = dojo.byId('yjzs_startTime').value;
// 			        	postData["baId"] = dojo.byId('yjzs_endTime').value;
// 			        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
			        }
				}).placeAt('yjzsToolBar3');
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
						url = "yjzh/sj/getexcle?postData="
							+ dojo.toJson(postData), window.open(url)
					}
				}).placeAt('yjzsToolBar3');
				
				
			var xhrArgs = {
				url : basePath + "yjzh/sj/get",
				postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
				handleAs : "json",
				load : function(data) {
					yjzhYjzsbutton.hideWait();
					console.log(data)
									
				},
				error : function(error) {
					targetNode.innerHTML = "An unexpected error occurred: "
							+ error;
				}
			};

// 			var deferred = dojo.xhrPost(xhrArgs);

									
			//end
			});

	