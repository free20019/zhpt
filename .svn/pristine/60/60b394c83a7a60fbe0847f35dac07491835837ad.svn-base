var storeTree,model,tree,map,checkBoxClicked;
require(
			imports_1,
			function(TabContainer, ContentPane, Dialog, Editor, Button,
					DateTextBox, CheckBox, TimeTextBox, SimpleTextarea, Grid,
					TextBox, Pagination, Selection, Keyboard, ColumnResizer,
					createAsyncStore, Memory, DijitRegistry, registry,
					domStyle, declare, Tree, ForestStoreModel,
					ItemFileWriteStore, dc, on, util) {
				//do_login();
				var yhGrid = store = myDialog = queryCondition= null;
				
				
				var xhrArgs = {
					url : basePath + "yjzh/cljkCx",
					postData : 'postData={"page":1,"pageSize":100}',
					handleAs : "json",
					load : function(data) {
						console.log(data)
						for(var i=0;i<data.length;i++){
							data[i].type = 'parent' ;
							data[i].checked = false;
						}
						storeTree = new ItemFileWriteStore({
							data : {
								"identifier" : "VEHI_NO",
								"label" : "VEHI_NO",
								"items" : data
							}
						});
						 model = new ForestStoreModel({
							store : storeTree,
							query : {
								type : 'parent'
							},
							rootLabel : '根目录'
						});
						tree = new Tree({
							model : model,
//							branchIcons : true,
							branchReadOnly : false,
							checkBoxes : true,
							nodeIcons : true,
							showRoot:false
						}, "sj_bs");
//						dojo.connect(tree, "onCheckBoxClick", model, checkBoxClicked);
						//tree end
						
						require([
						         "dijit/Menu",
						         "dijit/MenuItem",
						         "dijit/CheckedMenuItem",
						         "dijit/MenuSeparator",
						         "dijit/PopupMenuItem",
						         "dojo/domReady!"
						     ], function(Menu, MenuItem, CheckedMenuItem, MenuSeparator, PopupMenuItem){
						         var pMenu;
						         pMenu = new Menu({
						             targetNodeIds: ["sj_bs"]
						             ,selector: ".dijitTreeNode"
						         });
						         pMenu.addChild(new MenuItem({
						             label: "删除"
						            ,onClick:function(){
						            	var tn = registry.byNode(this.getParent().currentTarget);
						            	var no = tn.label;
						            	console.log(tn.label)
						            	model.deleteItem(tn.item)
						            	tn.destroy();
						            	dojo.xhrPost({
						            		url : basePath + "yjzh/cljkDel?vehicleNo="+encodeURI(no)
						            	});
						            }
						         }));

						         pMenu.startup();
						     });

					},
					error : function(error) {
						targetNode.innerHTML = "An unexpected error occurred: "
								+ error;
					}
				};

				var deferred = dojo.xhrPost(xhrArgs);

				//tree start
				  checkBoxClicked =function(vhic) {
// 					alert("The new state for " + this.getLabel(item) + " is: "
// 							+ nodeWidget.get("checked"));
// 					var no = this.getLabel(item);
 					var xhrArgs = {
 							url : basePath + "yjzh/cljkLsgj?vehicleNo="+encodeURI(vhic),
 							handleAs : "json",
 							load : function(data) {
 								console.log(data)
 								for(var i=0;i<data.length;i++){
 									if(i==0){
 										addmkshisstart(data[i]);
 									}else if(i==data.length-1){
 										addmkshisend(data[i]);
 									}else{
 										if(data[i-1].PX!=data[i].PX){
 											addmkshis(data[i]);
 										}
 									}
 								}
 								completeEventHandler(data);
 							}
 					}
 					dojo.xhrPost(xhrArgs);
				}
				
				
				map = new AMap.Map('map', {
					// resizeEnable: true,
					// rotateEnable: true,
					// dragEnable: true,
					// zoomEnable: true,
					// 设置可缩放的级别
					// zooms: [3,18],
					// 传入2D视图，设置中心点和缩放级别
					view : new AMap.View2D({
						center : new AMap.LngLat(120.141408, 30.299043),
						zoom : 15
					})
				});
				
				
				
				
				//添加起点
				var jh=[];
				function addmkshisstart(obj){
					var markerContent = document.createElement("div");
					markerContent.className = "markerContentStyle";
					//点标记中的图标
					var markerImg= document.createElement("img");
					markerImg.className="markerlnglat";
					markerImg.src="resources/images/start.png";   
					markerContent.appendChild(markerImg);
					var state="";
					if(obj.STATE==1){
						state="重车";
					}else{
						state="空车";
					}
					
					var txt = "<b style='color:#3399FF'>"+obj.VEHICLE_NUM+"(起点)</b><br/><br/><b>[GPS时间]</b>："+util.formatYYYYMMDDHHMISS(obj.SPEED_TIME)
					+"<br/><b>[车辆状态]</b>："+state
					+"<br/><b>[GPS速度]</b>："+obj.SPEED
					+"<br/><b>[行驶方向]</b>："+dlwz(obj.DIRECTION)
					+"<br/><b>[经纬度]</b>："+obj.PX+","+obj.PY
					//				  +"<br/><b>[位置描述]</b>："+obj.ADDRESS;
					var marker1 = new AMap.Marker({
						map:map,
						position:new AMap.LngLat(obj.PX,obj.PY),
						zIndex:102,
						offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
						draggable:false,  //是否可拖动
						content:markerContent   //自定义点标记覆盖物内容
					});
					jh.push(marker1);
					//$("#img"+obj.messageid).rotate(obj.direction-90);
					marker1.setMap(map);  //在地图上添加点
					
					//添加文本标记
					var info = [];
					info.push(txt);
					
					var inforWindow = new AMap.InfoWindow({                 
						offset:new AMap.Pixel(0,0),                 
						content:info.join("<br>")                 
					});                 
					AMap.event.addListener(marker1,"click",function(e){                 
						inforWindow.open(map,marker1.getPosition());                 
					});
				}
				
//添加终点
				function addmkshisend(obj){
					var markerContent = document.createElement("div");
					markerContent.className = "markerContentStyle";
					//点标记中的图标
					var markerImg= document.createElement("img");
					markerImg.className="markerlnglat";
					markerImg.src="resources/images/end.png";   
					markerImg.id="img"+obj.MESSAGE_ID
					markerContent.appendChild(markerImg);
					if(obj.STATE==1){
						state="重车";
					}else{
						state="空车";
					}
					var txt = "<b style='color:#3399FF'>"+obj.VEHICLE_NUM+"(终点)</b><br/><br/><b>[GPS时间]</b>："+util.formatYYYYMMDDHHMISS(obj.SPEED_TIME)
					+"<br/><b>[车辆状态]</b>："+state
					+"<br/><b>[GPS速度]</b>："+obj.SPEED
					+"<br/><b>[行驶方向]</b>："+dlwz(obj.DIRECTION)
					+"<br/><b>[经纬度]</b>："+obj.PX+","+obj.PY
//					  +"<br/><b>[位置描述]</b>："+obj.address;
					var marker1 = new AMap.Marker({
						map:map,
						position:new AMap.LngLat(obj.PX,obj.PY),     
						zIndex:102,  
						offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
						draggable:false,  //是否可拖动
						content:markerContent   //自定义点标记覆盖物内容
					});
					jh.push(marker1);
					//$("#img"+obj.messageid).rotate(obj.direction-90);
					//marker1.setMap(map);  //在地图上添加点
					
					//添加文本标记
					var info = [];                 
					info.push(txt);                 
					
					var inforWindow = new AMap.InfoWindow({                 
						offset:new AMap.Pixel(0,0),                 
						content:info.join("<br>")                 
					});                 
					AMap.event.addListener(marker1,"click",function(e){                 
						inforWindow.open(map,marker1.getPosition());                 
					});
					
					//添加角度		
					//markerlist.push(marker1);
				}
//添加所有点
				function addmkshis(obj){
					var markerContent = document.createElement("div");
					markerContent.className = "markerContentStyle";
					//点标记中的图标
					var markerImg= document.createElement("img");
					markerImg.className="markerlnglat";
					markerImg.src="resources/images/fx.jpg";   
					markerImg.id="img"+obj.MESSAGE_ID
					markerContent.appendChild(markerImg);
					if(obj.STATE==1){
						state="重车";
					}else{
						state="空车";
					}
					var txt = "<b style='color:#3399FF'>"+obj.VEHICLE_NUM+"</b><br/><br/><b>[GPS时间]</b>："+util.formatYYYYMMDDHHMISS(obj.SPEED_TIME)
					+"<br/><b>[车辆状态]</b>："+state
					+"<br/><b>[GPS速度]</b>："+obj.SPEED
					+"<br/><b>[行驶方向]</b>："+dlwz(obj.DIRECTION)
					+"<br/><b>[经纬度]</b>："+obj.PX+","+obj.PY
//					  +"<br/><b>[位置描述]</b>："+obj.address;
					var marker1 =null;
					if(obj.STATE==0){
						var marker1 = new AMap.Marker({
							//map:map,
							position:new AMap.LngLat(obj.PX,obj.PY),     
							offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
							draggable:false,  //是否可拖动
							icon:"resources/images/fx.jpg",
							//  content:markerContent,   //自定义点标记覆盖物内容
							angle:obj.direction-90
						});
					}else if(obj.STATE==1){
						var marker1 = new AMap.Marker({
							//map:map,
							position:new AMap.LngLat(obj.PX,obj.PY),
							offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
							draggable:false,  //是否可拖动
							icon:"resources/images/fx2.png",
							//  content:markerContent,   //自定义点标记覆盖物内容
							angle:obj.direction-90
						});
					}
					jh.push(marker1);
					//$("#img"+obj.messageid).rotate(obj.direction-90);
					marker1.setMap(map);  //在地图上添加点
					
					//添加文本标记
					var info = [];                 
					info.push(txt);                 
					
					var inforWindow = new AMap.InfoWindow({                 
						offset:new AMap.Pixel(0,0),                 
						content:info.join("<br>")                 
					});                 
					AMap.event.addListener(marker1,"click",function(e){                 
						inforWindow.open(map,marker1.getPosition());                 
					});
					
					//添加角度		
					//markerlist.push(marker1);
				}
				var polyline="";
				var markerhm="";
				var lineArr=null;
				var subArr=null;
				var markerhistory;
//添加轨迹
				function completeEventHandler(vehigps){
					lineArr = new Array();
					for (var i = 0; i <vehigps.length; i++){
						var lngX=vehigps[i].PX;
						var lngY=vehigps[i].PY;
						if(i>0){
							var longi0 = vehigps[i-1].PX;
							var lati0 = vehigps[i-1].PY;
							if(lngX!=longi0||lngY!=lati0){
								lineArr.push(new AMap.LngLat(lngX,lngY));
							}else{
								//  alert(lngY+"   "+(parseFloat(vehigps[i].lati)+0.00001));
								lngY=parseFloat(vehigps[i].PY)+0.00001*i;
								lineArr.push(new AMap.LngLat(lngX,lngY));
							}
						}else{
							lineArr.push(new AMap.LngLat(lngX,lngY));
						}
					}
					//绘制轨迹
					polyline = new AMap.Polyline({
						map:map,
						path:lineArr,
						strokeColor:"#00A",//线颜色
						strokeOpacity:1,//线透明度
						strokeWeight:3,//线宽
						strokeStyle:"dashed"//线样式
					});
					//  map.setFitView();
				}
			});
			
	
function addVehicle(){
	var vehicles = dojo.byId('vehicles').value
	if(vehicles){
		var vs = vehicles.split(',')
		for(var i=0;i<vs.length;i++){
			model.newItem({
				"VEHI_NO" : vs[i],
				"type" : "parent",
				"checked" : false
			})
			dojo.xhrPost({
        		url : basePath + "yjzh/cljkAdd?vehicleNo="+encodeURI(vs[i])
        	});
		}
		
		require([
		         "dijit/Menu",
		         "dijit/MenuItem",
		         "dijit/CheckedMenuItem",
		         "dijit/MenuSeparator",
		         "dijit/PopupMenuItem",
		         "dijit/registry",
		         "dojo/domReady!"
		     ], function(Menu, MenuItem, CheckedMenuItem, MenuSeparator, PopupMenuItem,registry){
		         var pMenu;
		         pMenu = new Menu({
		             targetNodeIds: ["sj_bs"]
		             ,selector: ".dijitTreeNode"
		         });
		         pMenu.addChild(new MenuItem({
		             label: "删除"
		            ,onClick:function(){
		            	var tn = registry.byNode(this.getParent().currentTarget);
		            	console.log(tn.label)
		            	var no = tn.label
		            	model.deleteItem(tn.item);
		            	tn.destroy();
		            	dojo.xhrPost({
		            		url : basePath + "yjzh/cljkDel?vehicleNo="+encodeURI(no)
		            	});
		            }
		         }));

		         pMenu.startup();
		     });
		
		
//		getgjhf();
	}
}

function getgjhf(){
	require(["dojo/query", "dijit/registry", "cbtree/Tree" ], function (query, registry, cbTree ) {

	    function checkboxState(nodeWidget) {
	       var state = nodeWidget.get("checked");
	       var label = tree.model.getLabel(item);

	       alert( "The state for " + label + " is: " + state );
	    }

	    query(".dijitTreeRow").forEach(function (domNode) {
	        checkboxState(registry.getEnclosingWidget(domNode));
	    });
	});
}

//获取选中车辆的经纬度
function getVhicleXY(){
	map.clearMap(null);
	dojo.query('.cbtreeCheckBoxChecked').forEach(function(node){
		var aa = dojo.query('input',node);
        for(var i=0;i<aa.length;i++){
            console.log(aa[i].value)
            checkBoxClicked(aa[i].value);
        }
	});
}

setInterval("getVhicleXY()", 10000);