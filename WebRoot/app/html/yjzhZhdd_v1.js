
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
			var yjzhZhddGrid = store = myDialog = null;
			
// 				dc.place(dc.create("span",{innerHTML:"地点",style:{"margin-right":"5px"}}),'yjzhZhddToolBar3');
// 				new TextBox({id:"zhdd_startTime",style:{"width":"8em","margin-right":"15px"}}).placeAt('yjzhZhddToolBar3');
// 				new Button({
// 				        label: "查  询",
// 				        onClick: function(){
// 				        	var postData = {"page":1,"pageSize":pageSize};
// 				        	postData["name"] = dojo.byId('yjzhZhdd_startTime').value;
// 				        	postData["baId"] = dojo.byId('yjzhZhdd_endTime').value;
// 				        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
// 				        }
// 				}).placeAt('yjzhZhddToolBar3');
				
				
			var xhrArgs = {
				url : basePath + "yjzh/sj/get",
				postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
				handleAs : "json",
				load : function(data) {
					console.log(data)
// 						for(var i=0; i<data.datas.length;  i++){
// 		    		    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
// 		    		    }
						dojo.byId('zhdd_sjmiaoshu').innerHTML =data.datas[0].miaoshu
									
				},
				error : function(error) {
					targetNode.innerHTML = "An unexpected error occurred: "
							+ error;
				}
			};

			var deferred = dojo.xhrPost(xhrArgs);
			//end
			});

