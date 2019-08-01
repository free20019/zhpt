var area_manageGrid = null; 
var udmouseTool=null;
var manageDiv;
var xhrArgsym;
var udpolygonOption = {
	    strokeColor:"#000033",	
	    strokeOpacity:1,
	    strokeWeight:2
	};
require([ "app/util", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select","dijit/form/Textarea","dijit/form/FilteringSelect"
          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
          ,"app/createSyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
          "dojo/dom-construct", "dojo/domReady!"], function(util,
        		  Editor, Button,DateTextBox,Select,Textarea,FilteringSelect,Grid,TextBox
        		  , Pagination,Selection,Keyboard,ColumnResizer,createSyncStore,Memory,DijitRegistry
        		  ,registry, domStyle,declare, dc ) {
	manageDiv=mapsdiv(area_manageDiv);
	
	
	
	new Button({
	    label: "添加",
	    onClick: function(){
			if(udmouseTool!=null){
				alert("鼠标在地图上点击绘制多边形，单击右键或者双击左键结束绘制");
			}else{
				manageDiv.plugin(["AMap.MouseTool"],function(){ 
				udmouseTool = new AMap.MouseTool(manageDiv); 
				udmouseTool.polygon(udpolygonOption);   //使用鼠标工具绘制多边形
				AMap.event.addListener(udmouseTool, "draw", function(e){
					var drawObj = e.obj;  
					var pointsCount = e.obj.getPath().length; 
					var a =  e.obj.getPath();
					var zbs = "";
					for(var i=0;i<pointsCount;i++){
						if(i==pointsCount-1){
							zbs+=a[i]
							}else{
						zbs+=a[i]+";";}
					}
					AddAreaDialog.show().then(function(){
						udmouseTool.close(true);
						dijit.byId("area_size").setValue(drawObj.getArea()+"平方米");
						dijit.byId("area_zb").setValue(zbs)
						udmouseTool=null;
					})
	//				$("#udareazbs").val(zbs);
	//				$("#udareasize").val(drawObj.getArea()+"平方米");
				});
				});
			}
	    }
	}).placeAt('buttonadmin');
	new Button({
	    label: "修改",
	    onClick: function(){
		var hs=0;
		dojo.forEach(area_manageGrid.collection.data, function(item,index) {
			if (area_manageGrid.isSelected(item)) {
				hs++;
			}
		});
			if(hs==0){
				alert("请选择一行进行修改");
			}else if(hs>1){
				alert("只能选择一行进行修改");
			}else{
				EidtAreaDialog.show().then(function(){
						dojo.forEach(area_manageGrid.collection.data, function(item,index) {
							if (area_manageGrid.isSelected(item)) {
								dojo.xhrPost({
									postData : "areaid="+item.AREA_ID,
									url : basePath + "clqk/editAreaID",
									handleAs : "json",
									load : function(data) {
									dijit.byId("area_id_e").setValue( data.datas[0].AREA_ID);
									dijit.byId("area_name_e").setValue( data.datas[0].AREA_NAME);
									dijit.byId("area_ms_e").setValue( data.datas[0].AREA_DESCRIBE);
									dijit.byId("area_size_e").setValue( data.datas[0].AREA_SIZE);	  
									dijit.byId("area_ybj_e").setValue( data.datas[0].ALARMNUM);
									dijit.byId("area_zb_e").setValue( data.datas[0].AREA_COORDINATES);	               
//									deferred = dojo.xhrPost(xhrArgs);
								}
								});
							}
						});
						EidtAreaDialog.reset()
		    	});
			}
	    }
	}).placeAt('buttonadmin');
	new Button({
        label: "删 除",
        onClick: function(){
		if(window.confirm("确定删除该区域吗？")){
        	dojo.forEach(area_manageGrid.collection.data, function(item,index) {
					if (area_manageGrid.isSelected(item)) {
						dojo.xhrPost({
							postData : "areaid="+item.AREA_ID,
							url : basePath + "clqk/delArea",
							handleAs : "json",
							load : function(data) {
							console.log(data);
							alert(data.info);
							deferred = dojo.xhrPost(xhrArgsym);
						}
						});
					}
			});
        	}
        }
	}).placeAt('buttonadmin');
	//添加表格
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'AREA_NAME', 
		    			label : "区域名称" 
		    		},{field:'AREA_DESCRIBE',
		    			label : "区域描述"
		    		},{field:'AREA_SIZE',
		    			label : "区域面积"
		    		},{field:'ALARMNUM',
		    			label : "预报警数"
		    		}			    		
	];
	
	

	//查询按钮
	xhrArgsym = {
			url : basePath + "clqk/findArea",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				for (var i = 0; i < data.datas.length; i++) {
					udaddPolygon1(data.datas[i].AREA_COORDINATES,data.datas[i].AREA_NAME,data.datas[i].AREA_DESCRIBE,data.datas[i].AREA_SIZE);
					data.datas[i] = dojo.mixin({
						id : i + 1
					}, data.datas[i]);
				}
				store = createSyncStore({
					data : data.datas
				});
				area_manageGrid.set('collection', store);
			},
		};
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	area_manageGrid = new CustomGrid({
	   					columns : columns
	   				}, "area_manage_table");
	dojo.xhrPost(xhrArgsym);
	
	area_manageGrid.on('.dgrid-row:click', function(event) {
		var row = area_manageGrid.row(event);
		var zbs = row.data['AREA_COORDINATES'].split(";");
		var lo = zbs[0].split(",")[0];
		var la = zbs[0].split(",")[1];
		gotoudarea(lo,la);
	});	
});
//增加区域
function newaddArea(){
	var AREA_NAME=dijit.byId("area_name").value;
	var AREA_DESCRIBE=dijit.byId("area_ms").value;
	var AREA_SIZE=dijit.byId("area_size").value;
	var ALARMNUM=dijit.byId("area_ybj").value;
	var AREA_COORDINATES=dijit.byId("area_zb").value;
	if(AREA_NAME.length<=0){
		alert("请输入区域名称");
	}else if(AREA_DESCRIBE.length<=0){
		alert("请输入区域描述");
	}else{
		var xhrArgs = {
				url : basePath + "clqk/addArea",
				postData : 'postData={"AREA_NAME":'+"'"+AREA_NAME+"'"+',"AREA_DESCRIBE":'+"'"+AREA_DESCRIBE+"'"+',"AREA_COORDINATES":'+"'"+AREA_COORDINATES+"'"+',"ALARMNUM":'+"'"+ALARMNUM+"'"+',"AREA_SIZE":'+"'"+AREA_SIZE+"'"+'}',
				handleAs : "json",
				load : function(data) {
					alert(data.info);
					AddAreaDialog.hide();
					dojo.xhrPost(xhrArgsym);
//					area_manageGrid.refresh2();
		},
			error : function(error) {
			}
		};
		
		dojo.xhrPost(xhrArgs);
	}
}

//修改区域
function neweditArea(){
	var AREA_NAME=dijit.byId("area_name_e").value;
	var AREA_DESCRIBE=dijit.byId("area_ms_e").value;
	var AREA_ID=dijit.byId("area_id_e").value;
	if(AREA_NAME.length<=0){
		alert("请输入区域名称");
	}else if(AREA_DESCRIBE.length<=0){
		alert("请输入区域描述");
	}else{
		var xhrArgs = {
				url : basePath + "clqk/editArea",
				postData : 'postData={"AREA_NAME":'+"'"+AREA_NAME+"'"+',"AREA_DESCRIBE":'+"'"+AREA_DESCRIBE+"'"+',"AREA_ID":'+"'"+AREA_ID+"'"+'}',
				handleAs : "json",
				load : function(data) {
					console.log(data);
					alert(data.info);
					EidtAreaDialog.hide();
					dojo.xhrPost(xhrArgsym);
//					area_manageGrid.refresh2();
		},
			error : function(error) {
//				targetNode.innerHTML = "An unexpected error occurred: "
//					+ error;
			}
		};
		
		dojo.xhrPost(xhrArgs);
	}
}


function mapsdiv(domId) {
	 var position=new AMap.LngLat(120.16378,30.25840);//创建中心点坐标
	var bjtypeMap=new AMap.Map(domId,{center:position,level:15,resizeEnable:true});//创建地图实例
	 bjtypeMap.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){
		  tool=new AMap.ToolBar({
		    direction:false,//隐藏方向导航
		    ruler:false,//隐藏视野级别控制尺
		    autoPosition:false//禁止自动定位
		  });
		  bjtypeMap.addControl(tool);
		  view=new AMap.OverView();
		  bjtypeMap.addControl(view);
		  scale=new AMap.Scale();
		  bjtypeMap.addControl(scale);
		});

//	 bjtypeMap.plugin(["AMap.MapType"], function() {
//		var type = new AMap.MapType({defaultType:0});//初始状态使用2D地图
//		bjtypeMap.addControl(type);
//	});
	 
	 return bjtypeMap;
}

function udaddPolygon1(obj,name,ms,sz){  
	var polygonArr=new Array();//多边形覆盖物节点坐标数组   
	var zbs = obj.split(";");
	for(var i=0;i<zbs.length;i++){
		var zb = zbs[i].split(",");
		polygonArr.push(new AMap.LngLat(zb[0],zb[1]));   
	}
	var polygon=new AMap.Polygon({     
		path:polygonArr,//设置多边形边界路径  
		strokeColor:"black", //线颜色  
		// strokeOpacity:0.2, //线透明度   
		strokeWeight:3,    //线宽   
		fillColor: "#f5deb3", //填充色  
		fillOpacity: 0//填充透明度  
	});   
	polygon.setMap(manageDiv);  

	 	var markerContent = document.createElement("div");
	    markerContent.className = "txtstyle";
		var markerSpan = document.createElement("span");
		markerSpan.innerHTML = name;
		markerContent.appendChild(markerSpan);
		ms = pp(ms);
		markerContent.onmouseover=function() {markerSpan.innerHTML =name+" : <br/>区域描述:<br/>"+ms+"<br/>区域面积:"+sz};
		markerContent.onmouseout=function() {markerSpan.innerHTML = name;};
	    var marker = new AMap.Marker({
		    map:manageDiv,
		     zIndex:10001, 
		    position:new AMap.LngLat( zbs[0].split(",")[0],zbs[0].split(",")[1]),     
		    offset:new AMap.Pixel(-14,7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent  //自定义点标记覆盖物内容
		});
}
function pp(obj){
	var num = parseInt(obj.length/10);
	if(num<1){
		return obj;
	}else{
		var rs="";
		for(var i=0;i<num;i++){
			rs+=obj.substr(i*10,10)+"<br/>";
		}
		rs+=obj.substr(num*10,obj.length);
		return rs;
	}
}
function gotoudarea(lo,la){
	var po = new AMap.LngLat(lo,la);
	manageDiv.setZoomAndCenter(14,po);
}