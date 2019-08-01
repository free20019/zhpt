
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
// 			var yjzhQdGrid = store = myDialog = null;
			
// 				dc.place(dc.create("span",{innerHTML:"地点",style:{"margin-right":"5px"}}),'yjzhQdToolBar3');
// 				new TextBox({id:"qd_startTime",style:{"width":"8em","margin-right":"15px"}}).placeAt('yjzhQdToolBar3');
// 				new Button({
// 				        label: "查  询",
// 				        onClick: function(){
// 				        	var postData = {"page":1,"pageSize":pageSize};
// 				        	postData["name"] = dojo.byId('yjzhQd_startTime').value;
// 				        	postData["baId"] = dojo.byId('yjzhQd_endTime').value;
// 				        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
// 				        }
// 				}).placeAt('yjzhQdToolBar3');
				
				
			var xhrArgs = {
				url : basePath + "yjzh/sj/get",
				postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
				handleAs : "json",
				load : function(data) {
					console.log(data)
					var columns = {
						dojoId : "序号", // give column a custom name
						//name : "姓名",
						sj : { label : "时间" ,formatter:util.formatYYYYMMDD},
						address : { label : "地点" },
						//miaoshu : { label : "描述" },
						lb : { label : "类别" },
						status : { label : "状态" }
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
						
						if(yjzhQdGrid){
							yjzhQdGrid = null;
							dojo.empty('yjzhQdGridDiv')
						}
						yjzhQdGrid= new CustomGrid({
							collection:  store,
							columns: columns
						},"yjzhQdGridDiv3");
						console.log(yjzhQdGrid);
						yjzhQdGrid.on('.dgrid-row:click', function (event) {
						    var row = yjzhQdGrid.row(event);
						    console.log('Row clicked:', row.id+'\t'+event);
						    dojo.byId('qd_sjmiaoshu').innerHTML = row.data['miaoshu']
						});
						dojo.byId('qd_sjmiaoshu').innerHTML = 
							yjzhQdGrid.collection.data[0].miaoshu ==''?'':yjzhQdGrid.collection.data[0].miaoshu
									
									
				},
				error : function(error) {
					targetNode.innerHTML = "An unexpected error occurred: "
							+ error;
				}
			};

			var deferred = dojo.xhrPost(xhrArgs);

			var qdYj = new Button({
		        label: "启动预警",
		        onClick: function(){
		            // Do something:
// 		            dom.byId("result1").innerHTML += "Thank you! ";
		        }
		    }, "qd_start_yj").startup();
			
			var saveYj = new Button({
		        label: "保存方案",
		        onClick: function(){
		            // Do something:
// 		            dom.byId("result1").innerHTML += "Thank you! ";
		        }
		    }, "qd_save_yj").startup();
			
			for(var ii = 1; ii< 7;ii++){
				new CheckBox({
			        name: "checkBox",
			        value: "yj"+ii,
			        checked: false,
			        onChange: function(b){ alert('onChange called with parameter = ' + b + ', and widget value = ' + this.get('value') ); }
			    }, "qd_checkBox"+ii).startup();
			}
			//end
			});