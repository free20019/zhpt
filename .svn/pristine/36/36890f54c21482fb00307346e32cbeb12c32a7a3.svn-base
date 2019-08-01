
var qyjk,qyjkScroll=null;
var vehilist= null,l2=l3=l4=l5=l6=l7=null;
var map;
var lsgjmap,lsgjDialog=null;//dijit	;
var trafficLayer,layers;
var polygonfk;
var editor={};
var circularedit;
var markerarea=null
var fxudmouseTool=null;
var yxudmouseTool=null;
var yxcontrol=true;
var fangkuang=true;
var polygonarea=null;
var udpolygonOption = {
	    strokeColor:"",
	    strokeOpacity:0,
	    strokeWeight:1
};
function toggleElement(id){
	var element = dojo.byId(id)
	var isVisible = element.style.display != 'none'
	if(isVisible){
		element.style.display = 'none'
	}else{
		element.style.display = 'block'
	}

}

function dadian(){
		// console.log(map.getZoom())
		  var zoom = map.getZoom();
		  if(zoom >=17 && zoom <= 18 ){
			  addMark4(1) //1,管理员可视范围打点 2,全屏打点
		  }else if(zoom >=14 && zoom <= 16 ){
			  addMark3(3)
		  }else if(zoom >=12 && zoom <= 13 ){
			  addMark3(2)
		  }else if(zoom >=10 && zoom <= 11 ){
			  addMark3(4)
		  }else if(zoom >=8 && zoom <= 9 ){
			  addMark3(5)
		  }else if(zoom >=6 && zoom <= 7 ){
			  addMark3(6)
		  }else if(zoom <= 5 ){
			  addMark3(7)
		  }
	 }


var  monmks = [];

function addMark3(level){
	//map.clearMap();
	map.remove(monmks);
	monmks = [];
    var list ;
    if(level == 1){
        list = vehilist;
    }else if(level == 2){
        list = l2;
    }else if(level == 3){
        list = l3;
    }else if(level == 4){
        list = l4;
    }else if(level == 5){
        list = l5;
    }else if(level == 6){
        list = l6;
    }else if(level == 7){
        list = l7;
    }
	for(var i=0;i<list.length;i++){
	    var obj = v = list[i];
	    if(v.cluster > 1){
	         var point = new AMap.LngLat(v.longi, v.lati);

	        //自定义点标记内容
	        var markerContent = document.createElement("div");
	        markerContent.className = "markerContent";
	        var markerSpan = document.createElement("span");
	        markerSpan.innerHTML = v.cluster;
	        var length = v.cluster.toString().length;
	        var width = 7*length;
	        if (length==1) {
	            length = 4; //1位数时是背景太小，所以要加大一点
	        }
	        var tmp = "height:"+ width + "px;line-height:"+ width + "px;border-radius :"+ width + "px";
	        markerContent.setAttribute("style", 'padding:' + length + 'px; background-color: #ffbf00;'+tmp);
	        markerContent.appendChild(markerSpan);
	        var marker3=new AMap.Marker({
	            "position":point,
	            "content":markerContent   //自定义点标记覆盖物内容
	        });
	        marker3.setMap(map);  //在地图上添加点
	        monmks.push(marker3);
	    }else if(!v.deleteFlag){
	        var icon = ''
					if(obj.onoffstate=="1"){
						if(obj.carStatus=="0"){
							icon="resources/images/c.png";
						}else{
							icon="resources/images/h.png";
						}
					}else{
						icon="resources/images/d.png";
					}

			var marker1 = new AMap.Marker({
	  			   map:map,
				   icon:icon,
				    position:new AMap.LngLat(obj.longi,obj.lati),
				    offset:new AMap.Pixel(-8,-8), //相对于基点的偏移位置
				    draggable:false,
				    vehicle:obj
				});
			monmks.push(marker1);
			AMap.event.addListener(marker1,"click",function(e){
				//  console.log(this,e)
				  var obj = this.Ae.vehicle;
				  	var txt = "<b style='color:#3399FF'>"+obj.vehino+"</b><br/><b>[所属公司]</b>："+obj.compname+"<br/><b>[车辆类型]</b>："+obj.cartype+"<br/><b>[车辆颜色]</b>："+obj.color+"<br/><b>[SIM卡]</b>："+obj.vehisim+"<br/><b>[联系人]</b>："+obj.ownname
							  +"<br/><b>[联系电话]</b>："+obj.owntel+"<br/><b>[经度]</b>："+obj.longi+"<br/><b>[纬度]</b>："+obj.lati;
				  inforWindow.setContent(txt);
				  inforWindow.open(map,new AMap.LngLat(obj.longi,obj.lati));
				});
			  AMap.event.addListener(map,"click",function(e){
				  inforWindow.close();
				});

	    }
	}
}

/// 1，可视范围内打点  2，全屏打点
function addMark4(type){
	if(type == 1){
		if(map.getZoom() < 17){
			return;
		}
	}
	//|| inforWindow.getIsOpen()

// 	inforWindow.open(map,inforWindow.getPosition());
	//map.clearMap();
	map.remove(monmks);
	monmks = [];
	//if(marker!=""){
	//    marker.setMap(null);
	//}

	var containsCount = 0;
	var bounds = map.getBounds();
	for(var i=0;i<vehilist.length;i++){
	    var v = obj = vehilist[i];
	    if(bounds.contains(new AMap.LngLat(v.longi,v.lati))){
	        containsCount++;
	        var icon = '';
			if(obj.onoffstate=="1"){
				if(obj.carStatus=="0"){
					icon="resources/images/c.png";
				}else{
					icon="resources/images/h.png";
				}
			}else{
				icon="resources/images/d.png";
			}

			var marker1 = new AMap.Marker({
	  			   map:map,
				   icon:icon,
				    position:new AMap.LngLat(obj.longi,obj.lati),
				    offset:new AMap.Pixel(-8,-8), //相对于基点的偏移位置
				    draggable:false
				});
			marker1.vehicle=obj;
			monmks.push(marker1);
			var lnglatXY; 
			AMap.event.addListener(marker1,"click",function(e){
				  var obj = this.vehicle;
				  lnglatXY = [obj.longi,obj.lati];
				  console.log(lnglatXY)
				  var address = "";
				    var geocoder = new AMap.Geocoder({
				        radius: 1000,
				        extensions: "all"
				    });        
				    geocoder.getAddress(lnglatXY, function(status, result) {
				        if (status === 'complete' && result.info === 'OK') {
				        	$("#addre").text(result.regeocode.formattedAddress)
				        }
				    });
				  console.log(address)
				  	var txt = "<b style='color:#3399FF'>"+obj.vehino+"</b><br/><b>[所属公司]</b>："+obj.compname+"<br/><b>[车辆类型]</b>："+obj.cartype+"<br/><b>[车辆颜色]</b>："+obj.color+"<br/><b>[GPS时间]</b>："+obj.dateTime.substr(0,19)+"<br/><b>[SIM卡]</b>："+obj.vehisim+"<br/><b>[联系人]</b>："+obj.ownname
							  +"<br/><b>[联系电话]</b>："+obj.owntel+"<br/><b>[经度]</b>："+obj.longi+"<br/><b>[纬度]</b>："+obj.lati
							  +"<br/><b>[地址]</b>：<span id='addre'></span>";
				  inforWindow.setContent(txt);
				  inforWindow.open(map,new AMap.LngLat(obj.longi,obj.lati));
				});
		    AMap.event.addListener(map,"click",function(e){
			  inforWindow.close();
			});

	    }
	}
}
function regeocoder(jwd) {  //逆地理编码
	var address = "";
    var geocoder = new AMap.Geocoder({
        radius: 1000,
        extensions: "all"
    });        
    geocoder.getAddress( [116.396574, 39.992706], function(status, result) {
    	console.log(jwd,1)
        if (status === 'complete' && result.info === 'OK') {
//          document.getElementById("result").innerHTML = result.regeocode.formattedAddress;
        	console.log(address)
          address = result.regeocode.formattedAddress;
        }
    });
   return address;
}

function redenerQyjk(flag){
	var table = json2table(qyjk.arealist);
	dijit.byId('cp_qyjk').set('content',table);
 	noscroll(table);
// 	var div = dojo.create("div", {style:" overflow: auto; padding-right: 15px;width:115%;height: 100%;" });
// 	dojo.place(json2table(qyjk.arealist),div,'first')
// 	dojo.place(div,'cp_qyjk','first')
	dojo.query('#cp_qyjk tr').forEach(function(item,index){
		if(parseInt(dojo.query('#cp_qyjk tr')[index].children[2].innerText)<parseInt(dojo.query('#cp_qyjk tr')[index].children[1].innerText)){
			dojo.query('#cp_qyjk tr')[index].style.color='#FF0907';
			dojo.query('#cp_qyjk tr')[index].style.fontWeight='bold';
		}
		dojo.connect(item, "click", qyjk.arealist, function(){
			var qyzbz=qyjk.arealist[index-1].areazbs.split(';')[0].split(',');
			var qyzbjg=new AMap.LngLat(qyzbz[0],qyzbz[1]);
			if(polygonarea!=null){
				polygonarea.setMap(null);
				markerarea.setMap(null);
			}
			udaddPolygonvehi(qyjk.arealist[index-1].areazbs,qyjk.arealist[index-1].areaname,qyjk.arealist[index-1].areams,qyjk.arealist[index-1].areasize)
			map.setCenter(qyzbjg);
			var tmp='',vehicles = qyjk.arealist[index-1].all;
			for(var i=0;i<vehicles.length;i++){
				tmp +='<div>'+vehicles[i]+'</div>';
			}
			dijit.byId('cp_qyjk').set('content','');

			var button = dojo.create("button", {id:"returnButton", innerHTML: "返回" });
			var areaListDetail = dojo.create("div", {id:"areaListDetail", innerHTML: tmp });
			dojo.place(button,areaListDetail,'first');
			dojo.place(areaListDetail,'cp_qyjk','first');
			noscroll('areaListDetail');
			dojo.connect(button, "click", this, function(){
				redenerQyjk();
			})
			dojo.query('#areaListDetail div').forEach(function (item,index) {
				dojo.connect(item,'click', function () {
					for(var i=0;i<vehilist.length;i++){
						if(item.innerText.substr(0,7) == vehilist[i].vehino){
							markercar(vehilist[i])
						}
					}
				});
			});
		});
	})
}

function noscroll(node){
	console.log('###noscroll....')
	if (typeof node == "string") {
        node =    dojo.byId(node);
  }
	var parentNode = node.parentNode;
	var newWidth = (parseInt(dojo.getComputedStyle(node).width) + 15) +'px';
	console.log('###'+newWidth);
	dojo.setStyle(node,{'overflow':'hidden'})
//	node.style.width = newWidth
	var warperParent = dojo.create("div",{style:"overflow: hidden;  position: relative;height:100%"})
	var warperSub = dojo.create("div",{style:" overflow: auto; padding-right: 15px; height: 100%;width:"+newWidth})
	dojo.place(warperParent,parentNode,'first')
	dojo.place(warperSub,warperParent,'first')
	dojo.place(node,warperSub,'first')
}

function addmakerdw(obj){
	var py=qyjk.vehilist[obj].lati;
	var px=qyjk.vehilist[obj].longi;
	var vehino1=qyjk.vehilist[obj].vehino;
	map.setZoomAndCenter(18, [px, py]);
	var mar = map.getAllOverlays();
	for(var i=0; i<mar.length;i++){
		if(mar[i].hasOwnProperty('vehicle') && mar[i].vehicle.vehino==vehino1){
			AMap.event.trigger(mar[i],'click');
		}
	}
}

function addPolygon(obja,obj,name,ms,bjs,sz){
	var polygonArr=new Array();//多边形覆盖物节点坐标数组
	var zbs = obj.split(";");
	for(var i=0;i<zbs.length;i++){
		var zb = zbs[i].split(",");
		polygonArr.push(new AMap.LngLat(zb[0],zb[1]));
	}
	var polygon=new AMap.Polygon({
		map:map,
		path:polygonArr,//设置多边形边界路径
		strokeColor:"black", //线颜色
		// strokeOpacity:0.2, //线透明度
		strokeWeight:3,    //线宽
		fillColor: "#f5deb3", //填充色
		fillOpacity: 0//填充透明度
	});
	pos.push(polygon);
	//test(zbs);
}
//区域包含车辆
function test(){
	map.remove(pos);
	map.remove(pomks);
	pos = [];
	pomks=[];

	var areadata="";

	amap.isEmpty();
	for(var i=0;i<arealist.length;i++){
		addPolygon(arealist[i].id,arealist[i].areazbs,arealist[i].areaname,arealist[i].areams,arealist[i].areabjs,arealist[i].areasize);
		var zbs = arealist[i].areazbs.split(";");
		var carobj = arealist[i].all;
		if(carobj.length>=parseInt(arealist[i].areabjs)){
			areadata+="<tr style='color:#FF0000' ondblclick='gotoareamoni("+zbs[0].split(",")[0]+","+zbs[0].split(",")[1]+","+arealist[i].id+","+carobj.length+")'><td>"+arealist[i].areaname+"</td><td>"+carobj.length+"</td><td>"+arealist[i].areabjs+"</td></tr>";
			name = "<font color='red'>"+arealist[i].areaname+": "+carobj.length+"</font>/"+arealist[i].areabjs;
		}else{
			areadata+="<tr style='color:#009933' ondblclick='gotoareamoni("+zbs[0].split(",")[0]+","+zbs[0].split(",")[1]+","+arealist[i].id+","+carobj.length+")'><td>"+arealist[i].areaname+"</td><td>"+carobj.length+"</td><td>"+arealist[i].areabjs+"</td></tr>";
			name = arealist[i].areaname+": "+carobj.length+"/"+arealist[i].areabjs;
		}
		var areavehis="";
		for(var k=0;k<carobj.length;k++){
			areavehis+="'"+carobj[k]+"',";
		}
		amap.put(parseInt(arealist[i].id),areavehis);
	 	var markerContent = document.createElement("div");
	    markerContent.className = "txtstyle";
		var markerSpan = document.createElement("span");
		markerSpan.innerHTML = name;
		markerContent.appendChild(markerSpan);
		var ms = zpp(arealist[i].areams);
		//markerContent.onclick=function() { addTab(100,name,"find.action?time="+ntime+"&id="+id);};
	//	markerContent.onmouseover=function() {markerSpan.innerHTML =name+"<br/><br/>区域描述:</br>"+ms+"<br/><br/>区域面积:"+arealist[i].areasize};
		markerContent.onmouseout=function() {markerSpan.innerHTML = name;};
	    var markert = new AMap.Marker({
		    map:map,
		     zIndex:10001,
		    position:new AMap.LngLat( zbs[0].split(",")[0],zbs[0].split(",")[1]),
		    offset:new AMap.Pixel(-14,7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent  //自定义点标记覆盖物内容
		});
	    pomks.push(markert);
	}
	$("#area_data").html(areadata);

}

function json2table(jsonList){
	var config = {"name":"driver",
			"temp_parameter":"","url": basePath + "driver/get",
			"title":[
			         {'col':'areaname','name':'56区域','styleTitle':'width:50%','style':'text-align:left;padding-left:25px'}
			         ,{'col':'all','name':'车辆数','styleTitle':'width:25%','style':'text-align:center'}
			         ,{'col':'areabjs','name':'预报警数','styleTitle':'width:25%','style':'text-align:center'}
	             	]}

// 	             jsonList = [{"name":123,"sfz":456},{"name":123,"sfz":456},{"name":123,"sfz":456},{"name":123,"sfz":456}]
	             	var table = document.createElement('table');
		table.className = 'gridTable';
		var cellIndex = 0;
		//1 title
		var row = table.insertRow(0);
		row.className = 'gridTableTitle';
		var title = config['title'];
		for(var i=0;i<title.length;i++){
			var cell = row.insertCell(cellIndex++);
			cell.innerHTML = title[i]['name'];
			if(title[i]['styleTitle'] !=null){
				cell.style.cssText = title[i]['styleTitle']
			}
		}
		//2 body
		for(var i=0;i<jsonList.length;i++){
			cellIndex = 0;
			row = table.insertRow(i+1);
			if(i%2==0){
				row.className = 'gridTableBodyTr1';
			}else{
				row.className = 'gridTableBodyTr2';
			}
			for(var j=0;j<title.length;j++){
				var cell = row.insertCell(cellIndex++);
				cell.className = 'gridTableBodyTd';
				if(title[j]['col'] == 'all'){
					cell.innerHTML = jsonList[i][[title[j]['col']]].length;
				}else{
					cell.innerHTML = jsonList[i][[title[j]['col']]];
				}
				if(title[j]['style'] !=null){
					cell.style.cssText = title[j]['style'];
				}
			}

		}

		return table;
}

function toggleLeft(id){
// 	var isVisible = document.getElementById("panel_left").offsetHeight != 0;
	var isVisible = dojo.byId('panel_left_warpper').style.display != 'none'
	if(isVisible){
// 		dojo.fadeOut({node:'panel_left'}).play()
		dojo.byId('panel_left_warpper').style.display = 'none';
	}else{
// 		dojo.fadeIn({node:'panel_left'}).play()
		dojo.byId('panel_left_warpper').style.display = 'block';
	}
}


//查询轨迹
function findlsgj(){
	require([ "app/createSyncStore"], function(createSyncStore) {
		dijit.byId("findlsgj").showWait();
		dojo.query('#sou_rect1 li').remove();
		lsgjmap.clearMap();
		var xhrArgs = {
				url : basePath + "clqk/findGj",
				postData : 'postData={"sTime":"'+dojo.byId("lsgj_stime").value+'","eTime":"'+dojo.byId("lsgj_etime").value+'","vehino":"'+dojo.byId("lsgj_vhic").value+'"}',
				handleAs : "json",
				load : function(data) {
					console.log('111',data)
					dijit.byId("findlsgj").hideWait();
					if(data.length==0){
						alert("当前时段没有历史轨迹");
						dojo.byId('sou_rect1').style.display='none';
					}else{
						for (var i = 0; i < data.length; i++) {
							if(i==0){
								addmkshisstart(data[i]);
							}else if(i==data.length-1){
								addmkshisend(data[i]);
							}else{
								if(data[i-1].PX!=data[i].PX){
									addmkshis(data[i]);
								}
							}
//						markerMovingControl = new MarkerMovingControl(lsgjmap, markerhistory, lineArr);
							completeEventHandler(data);
							data[i] = dojo.mixin({
								id : i + 1
							}, data[i]);
						}
					}
					console.log(new Date())
				},
				error : function(error) {
				}
			};
		dojo.xhrPost(xhrArgs);
	});
}
//添加起点
function addmkshisstart(obj){
	require(["dijit/layout/TabContainer", "dijit/Dialog","dijit/layout/ContentPane", "app/util","dojo/on","dojo/domReady!"]
	, function(TabContainer,Dialog, ContentPane,util,on){
	var markerContent = document.createElement("div");
    markerContent.className = "markerContentStyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.src="resources/images/start.png";
	markerImg.id="img"+obj.MESSAGE_ID;
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
	    map:lsgjmap,
	    position:new AMap.LngLat(obj.PX,obj.PY),
	    zIndex:102,
	    offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    content:markerContent   //自定义点标记覆盖物内容
	});
	//$("#img"+obj.messageid).rotate(obj.direction-90);
	marker1.setMap(lsgjmap);  //在地图上添加点

	//添加文本标记
	var info = [];
	info.push(txt);

	var inforWindow = new AMap.InfoWindow({
	  offset:new AMap.Pixel(0,0),
	  content:info.join("<br>")
	});
	  AMap.event.addListener(marker1,"click",function(e){
		  inforWindow.open(lsgjmap,marker1.getPosition());
		});
	});
}
//添加终点
function addmkshisend(obj){
	require(["dijit/layout/TabContainer", "dijit/Dialog","dijit/layout/ContentPane", "app/util","dojo/on","dojo/domReady!"]
	, function(TabContainer,Dialog, ContentPane,util,on){
	var markerContent = document.createElement("div");
    markerContent.className = "markerContentStyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.src="resources/images/end.png";
	markerImg.id="img"+obj.MESSAGE_ID;
	markerContent.appendChild(markerImg);
	if(obj.STATE==1){
		state="重车";
	}else{
		state="空车";
	}
  var txt = "<b style='color:#3399FF'>"+obj.VEHICLE_NUM+"(起点)</b><br/><br/><b>[GPS时间]</b>："+util.formatYYYYMMDDHHMISS(obj.SPEED_TIME)
  				  +"<br/><b>[车辆状态]</b>："+state
				  +"<br/><b>[GPS速度]</b>："+obj.SPEED
				  +"<br/><b>[行驶方向]</b>："+dlwz(obj.DIRECTION)
				  +"<br/><b>[经纬度]</b>："+obj.PX+","+obj.PY;
//				  +"<br/><b>[位置描述]</b>："+obj.address;
	var marker1 = new AMap.Marker({
	    map:lsgjmap,
	    position:new AMap.LngLat(obj.PX,obj.PY),
	    zIndex:102,
	    offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    content:markerContent   //自定义点标记覆盖物内容
	});
	//$("#img"+obj.messageid).rotate(obj.direction-90);
	marker1.setMap(lsgjmap);  //在地图上添加点

	//添加文本标记
	var info = [];
	info.push(txt);

	var inforWindow = new AMap.InfoWindow({
	  offset:new AMap.Pixel(0,0),
	  content:info.join("<br>")
	});
	  AMap.event.addListener(marker1,"click",function(e){
		  inforWindow.open(lsgjmap,marker1.getPosition());
		});

	//添加角度
	//markerlist.push(marker1);
	});
}


//添加所有点
function addmkshis(obj){
	require(["dijit/layout/TabContainer", "dijit/Dialog","dijit/layout/ContentPane", "app/util","dojo/on","dojo/domReady!"]
	, function(TabContainer,Dialog, ContentPane,util,on){
	var markerContent = document.createElement("div");
    markerContent.className = "markerContentStyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.src="resources/images/fx.jpg";
	markerImg.id="img"+obj.MESSAGE_ID;
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
//				  +"<br/><b>[位置描述]</b>："+obj.address;
				  var marker1 =null;
if(obj.STATE==0){
	var marker1 = new AMap.Marker({
	    map:lsgjmap,
	    position:new AMap.LngLat(obj.PX,obj.PY),
	    offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    icon:"resources/images/fx.jpg",
	  //  content:markerContent,   //自定义点标记覆盖物内容
	    angle:obj.DIRECTION-90
	});
	}else if(obj.STATE==1){
		var marker1 = new AMap.Marker({
		    map:lsgjmap,
		    position:new AMap.LngLat(obj.PX,obj.PY),
		    offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    icon:"resources/images/fx2.png",
		  //  content:markerContent,   //自定义点标记覆盖物内容
		    angle:obj.DIRECTION-90
		});
		}
	//$("#img"+obj.messageid).rotate(obj.direction-90);
	marker1.setMap(lsgjmap);  //在地图上添加点

	//添加文本标记
	var info = [];
	info.push(txt);

	var inforWindow = new AMap.InfoWindow({
	  offset:new AMap.Pixel(0,0),
	  content:info.join("<br>")
	});
	  AMap.event.addListener(marker1,"click",function(e){
		  inforWindow.open(lsgjmap,marker1.getPosition());
		});

	//添加角度
	//markerlist.push(marker1);
	});
}

//添加轨迹
function completeEventHandler(vehigps){
	if(vehigps[0].STATE==0){
		markerhistory = new AMap.Marker({
	        map:lsgjmap,
	        //draggable:true, //是否可拖动
	        position:new AMap.LngLat(vehigps[0].PX,vehigps[0].PY),//基点位置
	        icon:"resources/images/car2.png", //marker图标，直接传递地址url
	        zIndex:101,
	        offset:new AMap.Pixel(-26,-14), //相对于基点的位置
	        angle:vehigps[0].DIRECTION-90,
	        autoRotation:true
	    });
	}else if(vehigps[0].STATE==1){
		markerhistory = new AMap.Marker({
	        map:lsgjmap,
	        //draggable:true, //是否可拖动
	        position:new AMap.LngLat(vehigps[0].PX,vehigps[0].PY),//基点位置
	        icon:"resources/images/car.png", //marker图标，直接传递地址url
	        zIndex:101,
	        offset:new AMap.Pixel(-26,-14), //相对于基点的位置
	        angle:vehigps[0].DIRECTION-90,
	        autoRotation:true
	    });
	}
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
		    	lineArr.push(new AMap.LngLat(lngX,lngY));
			}
	    }else{
	    	lineArr.push(new AMap.LngLat(lngX,lngY));
		}
    }
    //绘制轨迹
    polyline = new AMap.Polyline({
        map:lsgjmap,
        path:lineArr,
        strokeColor:"#00A",//线颜色
        strokeOpacity:1,//线透明度
        strokeWeight:3,//线宽
        strokeStyle:"dashed"//线样式
    });
    lsgjmap.setFitView();
    //alert(lineArr.length+"   "+vehigps.length);
   // var linea=0;
    AMap.event.addListener(markerhistory,'moving',function(e) {
		    for(var i=0;i<lineArr.length;i++){
		        var l = lineArr[i];
		        if(markerhistory.getPosition().distance(l)<=2){

		        	$('#vehigpsdata tr').each(function() {
		       	 		$(this).css('color','black');
		    		});
		           $("#his"+vehigps[i].MESSAGE_ID).css('color','red');
		           if(vehigps[i].STATE==0){
		        	   markerhistory.setIcon("resources/images/car2.png");
				    }else if(vehigps[i].STATE==1){
		        	   markerhistory.setIcon("resources/images/car.png");
				    }
				   // linea = i+1;
					break;
		        }else{

			    }
		    }
		});
  }
//轨迹回放
var MarkerMovingControl = function(map, marker, path) {
          this._map = map;
          this._marker = marker;
          this._path = path;
          this._currentIndex = 0;
          marker.setMap(map);
          marker.setPosition(path[0]);
      };
/**
 * 移动marker，会从当前位置开始向前移动
 */
MarkerMovingControl.prototype.move = function() {
    if (!this._listenToStepend) {
        this._listenToStepend = AMap.event.addListener(this, 'stepend', function() {
            this.step();
        }, this);
    }
    this.step();
};

/**
 * 停止移动marker，由于控件会记录当前位置，所以相当于暂停
 */
MarkerMovingControl.prototype.stop = function() {
    this._marker.stopMove();
};

/**
 * 重新开始，会把marker移动到路径的起点然后开始移动
 */
MarkerMovingControl.prototype.restart = function() {
    this.stop();
    this._marker.setPosition(this._path[0]);
    this._currentIndex = 0;
    this.move();
};

/**
 * 向前移动一步
 */
 var spc=500;
function quick(){
	spc=spc+200;
	 shezspc();
}
function slow(){
	if(spc-200<=0){
		alert("已到最小速度");
	}else{
		spc=spc-200;
	}
	shezspc();
}
function shezspc(){
	MarkerMovingControl.prototype.step = function(){
	    var nextIndex = this._currentIndex + 1;
	    if (nextIndex < this._path.length) {
	        if (!this._listenToMoveend) {
	            this._listenToMoveend = AMap.event.addListener(this._marker, 'moveend', function(){
	                this._currentIndex++;
	                AMap.event.trigger(this, 'stepend');
	            }, this);
	        }
	        // console.log(nextIndex);
	        this._marker.moveTo(this._path[nextIndex], spc);
	    }
	};
}

MarkerMovingControl.prototype.step = function(){
    var nextIndex = this._currentIndex + 1;
    if (nextIndex < this._path.length) {
        if (!this._listenToMoveend) {
            this._listenToMoveend = AMap.event.addListener(this._marker, 'moveend', function(){
                this._currentIndex++;
                AMap.event.trigger(this, 'stepend');
            }, this);
        }
        // console.log(nextIndex);
        this._marker.moveTo(this._path[nextIndex], 500);
    }
};
var markerMovingControl = null;

// 编写事件响应函数
function startAnimation() {
	spc=500;
	shezspc();
    markerMovingControl.restart();
//	markerhistory.moveAlong(lineArr,500);
}

function pauseAnimation() {
    markerMovingControl.stop();
}

function continueAnimation() {
    markerMovingControl.move();
}
//判断车辆空重车
function kzpd(obj){
	if(obj=="1"){
		return "重车";
	}else{
		return "空车";
	}
}
//时间
function setformat(obj){
	var y=(obj.getFullYear()).toString();
	var m=(obj.getMonth()+1).toString();
	if(m.length==1){
		m="0"+m;
	}
	var d=obj.getDate().toString();
	if(d.length==1){
		d="0"+d;
	}
	var h = obj.getHours().toString();
	if(h.length==1){
		h="0"+h;
	}
	var mi = obj.getMinutes().toString();
	if(mi.length==1){
		mi="0"+mi;
	}
	var s = obj.getSeconds().toString();
	if(s.length==1){
		s="0"+s;
	}
	var time=y+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
	return time;
}
//转换方向
function dlwz(obj){
	if(obj==0||obj==360){
		return "正北";
	}else if(obj==90){
		return "正东";
	}else if(obj==180){
		return "正南";
	}else if(obj==270){
		return "正西";
	}else if(obj>0&&obj<90){
		return "东北";
	}else if(obj>90&&obj<180){
		return "东南";
	}else if(obj>180&&obj<270){
		return "西南";
	}else if(obj>270&&obj<360){
		return "西北";
	}
}
//实时路况
var lkpd = false;
AMap.event.addDomListener(document.getElementById('mapsslk'), 'click', function() {
  if (lkpd) {
      trafficLayer.hide();
      lkpd = false;
  } else {
      trafficLayer.show();
      lkpd = true;
  }
}, false);
//卫星图
var wxtpd = false;
AMap.event.addDomListener(document.getElementById('mapwxt'), 'click', function() {
  if (wxtpd) {
 	 layers.hide();
      wxtpd = false;
  } else {
 	 layers.show();
      wxtpd = true;
  }
}, false);
//测距
var cejusj = true;
function startRuler1() {
	if(cejusj){
		ruler2.turnOff();
		ruler1.turnOn();
		cejusj = false;
	}else{
		cejusj = true;
	}
}
//编辑圆结束编辑圆
editor.startEditCircle=function(){
    editor._circleEditor.open();
};
editor.closeEditCircle=function(){
    editor._circleEditor.close();
};
function yxqxbjsj(){
	circularedit.on('dblclick', function() {
		  editor._circleEditor.close();
	   });
}
function yxqxbjyjsj(){
	circularedit.on('rightclick', function() {
		  editor._circleEditor.close();
	   });
}
function findvhiclsgj(obj){
        lsgjDialog.show().then(function(){
        	 dijit.byId("lsgj_stime").setValue(setformat(new Date((new Date()).getTime() - 1000 * 60 * 60*1)));
  		   dijit.byId("lsgj_etime").setValue(setformat(new Date()));
  		   lsgjmap = new AMap.Map('lsgjmapdiv', {
  		        animateEnable:false,
  		        jogEnable:false,
  				view: new AMap.View2D({
  					center: new AMap.LngLat(120.153576,30.287459),
  					zoom: 15
  				})
  			});

			dojo.byId('lsgj_vhic').setAttribute('value','');
        	var qyjkvehi=qyjk.vehilist;
        	var vhicno11=qyjkvehi[obj].vehino;
        	console.log(vhicno11);
			dojo.connect(dojo.byId('lsgj_vhic'),'keyup', function () {
				var neirong=this.value;
				if(neirong.length==0){
					dojo.byId('sou_rect1').style.display='none';
				}
				if(neirong.length>2){
					dojo.byId('sou_rect1').style.display='block';
					var xhrArgsgjcl = {
						url: basePath + "yjzh/findclsj?info=" + neirong,
						handleAs: "json",
						load: function (data) {
							gjcl2 = data;
							var li;
							for (var i = 0; i < gjcl2.length; i++) {
								li = "<li><span></span><i>" + gjcl2[i].COMP_NAME + "</i><a>" + gjcl2[i].VEHI_NO + "</a></li>";
								dojo.query('#sou_rect1 ul')[0].innerHTML += li;
							}
							dojo.query('#sou_rect1 li').forEach(function(item,index){
								dojo.connect(item, "click", function(){
									dojo.byId('lsgj_vhic').value=gjcl2[index].VEHI_NO;
									console.log(gjcl2[index].VEHI_NO)
									dojo.byId('sou_rect1').style.display='none';
								});
							});
						}
					};
					dojo.xhrPost(xhrArgsgjcl);
				}
			});
			dojo.byId('lsgj_vhic').value=vhicno11;
		});

}

var inforWindow = new AMap.InfoWindow({
	offset:new AMap.Pixel(0,0)
});
inforWindow.on('close',function(){
	if(inforWindow.getIsOpen()){

		console.log('close...');
	}
});
//添加多边形覆盖物
var areadata="";
var amap = null;
var pos = [];
var pomks=[];


var polyline="";
var markerhm="";
var lineArr=null;
var subArr=null;
var markerhistory;

dojo.ready(function(){
	map = new AMap.Map('map', {
		//resizeEnable: true,
		//rotateEnable: true,
		//dragEnable: true,
		//zoomEnable: true,
		//设置可缩放的级别
		//zooms: [3,18],
        animateEnable:false,
        jogEnable:false,
      //  mapStyle:'light',//'fresh',//'dark',
		//传入2D视图，设置中心点和缩放级别
       // features:['road','bg','building'],//,'point','bg',
		view: new AMap.View2D({
			center: new AMap.LngLat(120.153576,30.287459),
			zoom: 15
		})

	});
	 trafficLayer = new AMap.TileLayer.Traffic({
	        zIndex: 10
	    });
	    trafficLayer.setMap(map);
	    trafficLayer.hide();
	 layers=new AMap.TileLayer.Satellite();
	 layers.setMap(map);
	 layers.hide();
	 map.on('moveend',function(){
		 addMark4(1); //1,管理员可视范围打点 2,全屏打点
	 });
	 map.on('zoomend',function(){
		   dadian();
	 });

	 map.plugin(["AMap.RangingTool"], function() {
	        ruler1 = new AMap.RangingTool(map);
	        AMap.event.addListener(ruler1, "end", function(e) {
	            ruler1.turnOff();
	        });
	        var sMarker = {
	                icon: new AMap.Icon({    //复杂图标
	                    size: new AMap.Size(28, 37),//图标大小
	                    image: "http://webapi.amap.com/images/custom_a_j.png", //大图地址
	                    imageOffset: new AMap.Pixel(0, 0)//相对于大图的取图位置
	                })
	            };
	            var eMarker = {
	                icon: new AMap.Icon({    //复杂图标
	                    size: new AMap.Size(28, 37),//图标大小
	                    image: "http://webapi.amap.com/images/custom_a_j.png", //大图地址
	                    imageOffset: new AMap.Pixel(-28, 0)//相对于大图的取图位置
	                }),
	                offset: new AMap.Pixel(-16, -35)
	            };
	            var lOptions = {
	                strokeStyle: "solid",
	                strokeColor: "#FF33FF",
	                strokeOpacity: 1,
	                strokeWeight: 2
	            };
	            var rulerOptions = {startMarkerOptions: sMarker, endMarkerOptions: eMarker, lineOptions: lOptions};
	        });

	 //测距
	 map.plugin(["AMap.RangingTool"], function() {
	        ruler1 = new AMap.RangingTool(map);
	        AMap.event.addListener(ruler1, "end", function(e) {
	            ruler1.turnOff();
	        });
	        var sMarker = {
	            icon: new AMap.Icon({
	                size: new AMap.Size(19, 31),//图标大小
	                image: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b1.png"
	            })
	        };
	        var eMarker = {
	            icon: new AMap.Icon({
	                size: new AMap.Size(19, 31),//图标大小
	                image: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b2.png"
	            }),
	            offset: new AMap.Pixel(-9, -31)
	        };
	        var lOptions = {
	            strokeStyle: "solid",
	            strokeColor: "#FF33FF",
	            strokeOpacity: 1,
	            strokeWeight: 2
	        };
	        var rulerOptions = {startMarkerOptions: sMarker, endMarkerOptions: eMarker, lineOptions: lOptions};
	        ruler2 = new AMap.RangingTool(map, rulerOptions);
	    });

});

require(["dijit/layout/TabContainer", "dijit/Dialog","dijit/layout/ContentPane", "dojo/on","dojo/domReady!"]
, function(TabContainer,Dialog, ContentPane,on){

	mapTabContainer = new TabContainer({
			doLayout:false,
			style: "opacity: 0.9;" //height: 50%; width: 300px;
   }, "panel_left");

   var cp1 = new ContentPane({
	   		id:'cp_qyjk',
        title: "区域监控",
        style:"height:600px;overflow: hidden",
        content: ""
   });
   var cp2 = new ContentPane({
       title: "车辆监控",
       content: "<div id='live_query_box'></div><div id='live_query_box_deatil'></div></div>",
       style: "height: 280px" //height: 50%; width: 300px;
  });
   cp3 = new ContentPane({
       title: "车辆信息",
       content: "<div id='clxx_vhic_comp'></div>",
       style: "height: 600px;overflow-x:hidden" //height: 50%; width: 300px;
  });
   mapTabContainer.addChild(cp1);
   mapTabContainer.addChild(cp2);
   mapTabContainer.addChild(cp3);
   cp1.onShow = function(){
		tc.resize();
   };
   mapTabContainer.startup();


   //统计
   dojo.connect(dojo.byId('cl_tj'),'click',this,function(){
	   toggleElement('tj_pop');
   });
	//更多
	dojo.connect(dojo.byId('more'),'click',this,function(){
		toggleElement('more_left');
	});
   //测距
   dojo.connect(dojo.byId('map_cj'),'click',this,function(){
	   startRuler1();
   });
   //在地图上画圆
//   dojo.connect(dojo.byId('map_yx'),'click',this,function(){
//	   yxcontrol=true;
//	   if(circularedit!=null){
//		   circularedit.setMap();
//	   }
//	   if(polygonfk!=null){
//			polygonfk.setMap();
//		}
//	   var clickEventListener = map.on('click', function(e) {
//		   if(yxcontrol){
////		   e.lnglat.getLng() + ',' + e.lnglat.getLat()
//		    circularedit = editor._circle=(function(){
//		        var circle = new AMap.Circle({
//		            center: [e.lnglat.getLng(), e.lnglat.getLat()],// 圆心位置
//		            radius: 500, //半径
//		            strokeColor: "#F33", //线颜色
//		            strokeOpacity: 1, //线透明度
//		            strokeWeight: 1.5, //线粗细度
//		            fillColor: "#ee2200", //填充颜色
//		            fillOpacity: 0//填充透明度
//		        });
//		        circle.setMap(map);
//		        return circle;
//		    })();
//		    editor._circleEditor= new AMap.CircleEditor(map, editor._circle);
//		    editor._circleEditor.open();
//		    yxcontrol=false;
//		    yxqxbjsj();
//		    yxqxbjyjsj();
//		   }
//	   });
//   });
   //在地图上画方形
   dojo.connect(dojo.byId('map_fx'),'click',this,function(){
	   if(fangkuang){
		   fangkuang=false;
		   if(fxudmouseTool!=null){
			   alert("鼠标在地图上点击绘制多边形，单击右键或者双击左键结束绘制");
		   }else{
			   if(polygonfk!=null){
				   polygonfk.setMap();
			   }
			   if(circularedit!=null){
				   circularedit.setMap();
			   }
			   map.plugin(["AMap.MouseTool"],function(){
				   fxudmouseTool = new AMap.MouseTool(map);
				   fxudmouseTool.polygon(udpolygonOption);   //使用鼠标工具绘制多边形
				   AMap.event.addListener(fxudmouseTool, "draw", function(e){
					   var drawObj = e.obj;
					   var pointsCount = e.obj.getPath().length;
					   var a =  e.obj.getPath();
					   var zbs = "";
					   var polygonArr1 = new Array();//多边形覆盖物节点坐标数组
					   for(var i=0;i<pointsCount;i++){
						   polygonArr1.push([a[i].lng,a[i].lat]);
					   }
					   polygonfk = new AMap.Polygon({
						   path: polygonArr1,//设置多边形边界路径
						   strokeColor: "black", //线颜色
						   strokeOpacity: 0.3, //线透明度
						   strokeWeight: 1,    //线宽
						   fillColor: "#696969", //填充色
						   fillOpacity: 0.15//填充透明度
					   });
					   polygonfk.setMap(map);
					   mapTabContainer.selectChild(mapTabContainer.getChildren()[2]);//tab页跳转
					   var vhicinfo="<table>";
					   var vhiccount = "";
					   console.log(vehilist)
					   for(var j=0; j<vehilist.length; j++){
						   if(polygonfk.contains([vehilist[j].longi,vehilist[j].lati])){
//					console.log(vehilist[j].vehino);
							   vhiccount++;
							   vhicinfo += " <tr><td> "+vehilist[j].vehino+";"+vehilist[j].compname+" </td></tr> ";
						   }
					   }
					   dojo.byId("clxx_vhic_comp").innerHTML="<span style='font-size: 15px; color: red;'>" +
					   "车辆总数:"+ vhiccount +"</span></br>"+ vhicinfo + " </table> ";
					   fxudmouseTool.close(true);
					   fxudmouseTool=null;
					   map.setZoomAndCenter(17, [a[0].lng,a[0].lat]);


//					   for(var i=0;i<vehilist.length;i++){
//						   if(polygonfk.contains([vehilist[i].longi,vehilist[i].lati])){
//							   markercar(vehilist[i])
//						   }
//					   }
					   dojo.query('#clxx_vhic_comp table tr').forEach(function (item,index) {
						   dojo.connect(item,'click', function () {
							   var mar1 = map.getAllOverlays();
							   for(var i=0; i<mar1.length;i++){
									if(mar1[i].hasOwnProperty('vehicle') && mar1[i].vehicle.vehino==item.innerText.substr(0,7)){
										AMap.event.trigger(mar1[i],'click');
									}
								}
						   });
					   });
				   });
			   });
		   }
	   }else{
		   fangkuang=true;
		   if(polygonfk!=null){
			   polygonfk.setMap();
		   }
		   if(circularedit!=null){
			   circularedit.setMap();
		   }
	   }
   });
 //显示/隐藏 按钮:
 dojo.connect(dojo.byId('ssjk_lb'),'click',this,function(){
 	toggleElement('panel_left_warpper');
 	mapTabContainer.resize();
 });


   //历史轨迹，弹出窗口展现
   dojo.connect(dojo.byId('cl_lsgj'),'click',this,function(){
	   //新建弹出窗口，载入地图，
	   lsgjDialog.show().then(function(){
		   dojo.byId('lsgj_vhic').setAttribute('value','');
		   dijit.byId("lsgj_stime").setValue(setformat(new Date((new Date()).getTime() - 1000 * 60 * 60*1)));
		   dijit.byId("lsgj_etime").setValue(setformat(new Date()));
		   lsgjmap = new AMap.Map('lsgjmapdiv', {
		        animateEnable:false,
		        jogEnable:false,
				view: new AMap.View2D({
					center: new AMap.LngLat(120.153576,30.287459),
					zoom: 15
				})
			});	
		   
//		   queryVhic().then(function(data){
////				console.dir("#####"+data.datas)
//				dijit.byId("lsgj_vhic").set('store',new dojo.store.Memory({ data: data.datas}));
//			})
//			dijit.byId("lsgj_vhic").set('queryExpr', "*${0}*"  )
		   dojo.connect(dojo.byId('lsgj_vhic'),'keyup', function () {
			   var neirong=this.value;
			   if(neirong.length==0){
				   dojo.byId('sou_rect1').style.display='none';
			   }
			   if(neirong.length>2){
				   dojo.byId('sou_rect1').style.display='block';
				   dojo.query('#sou_rect1 li').remove();
				   var xhrArgsgjcl = {
					   url: basePath + "yjzh/findclsj?info=" + neirong,
					   handleAs: "json",
					   load: function (data) {
						   gjcl2 = data;
						   var li;
						   for (var i = 0; i < gjcl2.length; i++) {
							   li = "<li><span></span><i>" + gjcl2[i].COMP_NAME + "</i><a>" + gjcl2[i].VEHI_NO + "</a></li>";
							   dojo.query('#sou_rect1 ul')[0].innerHTML += li;
						   }
						   dojo.query('#sou_rect1 li').forEach(function(item,index){
							   dojo.connect(item, "click", function(){
								   dojo.byId('lsgj_vhic').value=gjcl2[index].VEHI_NO;
								   console.log(gjcl2[index].VEHI_NO)
								   dojo.byId('sou_rect1').style.display='none';
							   });
						   });
					   }
				   };
				   dojo.xhrPost(xhrArgsgjcl);
			   }
		   });
			
	   });
   });
   
   liveQuery 				= new LiveQuery(basePath+'clqk/onevhic?vehino=','live_query_box');//clqk/onevhic?vehino=12
   liveQuery.defaultMessage 	= "请输入车牌号/公司名";
   liveQuery.liveQueryBoxDeatil = 'live_query_box_deatil';
   liveQuery.setDetail = function(){
// 	   live_query_box_deatil,this.currentSelect
			//dojo.byId('live_query_box_deatil').innerHTML = ;
			//var cxcl=dojo.byId('live_query_box_deatil').innerHTML;
			var qyjkvehi=qyjk.vehilist;
			for(var i=0;i<qyjkvehi.length;i++){
				if(qyjkvehi[i].vehino==this.currentSelect.title){
					console.log(qyjkvehi[i])
					var tab="<table class='fontsize'><tr><td>所属公司:</td><td>"+qyjkvehi[i].compname
					+"</td></tr><tr><td>车辆类型:</td><td>"+qyjkvehi[i].cartype
					+"</td></tr><tr><td>车辆颜色:</td><td>"+qyjkvehi[i].color
					+"</td></tr><tr><td>车辆速度:</td><td>"+qyjkvehi[i].speed
					+"</td></tr><tr><td>车辆状态:</td><td>"+kzpd(qyjkvehi[i].state)
					+"</td></tr><tr><td>SIM卡:</td><td>"+qyjkvehi[i].vehisim
					+"</td></tr><tr><td>联系人:</td><td>"+qyjkvehi[i].ownname
					+"</td></tr><tr><td>联系电话:</td><td>"+qyjkvehi[i].owntel
					+"</td></tr><tr><td>经度:</td><td>"+qyjkvehi[i].lati
					+"</td></tr><tr><td>纬度:</td><td>"+qyjkvehi[i].longi
					+"</td></tr><tr><td>GPS时间:</td><td>"+qyjkvehi[i].dateTime
					+"</td></tr><tr><td>操作:</td><td><button onclick='addmakerdw("+i+");'>定位</button><button onclick='findvhiclsgj("+i+");'>历史轨迹</button></td></tr></table>";
					dojo.byId('live_query_box_deatil').innerHTML= tab;
				}
			}
   };
   monitor();

//    cp1.set('content',json2table(qyjk.arealist))





});
function monitor(){
	dojo.xhrGet({
		url : basePath + "clqk/qyjk",//clqk/qyjk
		handleAs : "json",
		load : function(data) {
			qyjk = data;
			vehilist = qyjk.vehilist;
			l2=qyjk.l2;
			l3=qyjk.l3;
			l4=qyjk.l4;
			l5=qyjk.l5;
			l6=qyjk.l6;
			l7=qyjk.l7;
			console.log(vehilist);
			redenerQyjk()
			
			
			document.getElementById('cl_zs').innerText=" 总数:"+qyjk.num.total;
			document.getElementById('cl_sx').innerText=" 在线:"+qyjk.num.onnum;
			document.getElementById('cl_xx').innerText=" 下线:"+qyjk.num.offnum;
			document.getElementById('cl_zc').innerText=" 重车:"+qyjk.num.hnum;
			document.getElementById('cl_kc').innerText=" 空车:"+qyjk.num.nnum;
			dadian();
		}
	})
//	setTimeout("monitor()",30000);
}
function markercar(vehicle){
	console.log(vehicle)
	if(vehicle.PX =='' || vehicle.PY==''){
		vehicle.PX=vehicle.PY=0;
	}
	var markerContent = document.createElement("div");
	markerContent.className = "txtstyle";
	//点标记中的图标
	var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	if(vehicle.STATUS=="1"){
		markerImg.src="resources/images/h.png";
	}else{
		markerImg.src="resources/images/d.png";
	}
	markerContent.appendChild(markerImg);

	marker1 = new AMap.Marker({
		map:map,
		position:new AMap.LngLat(vehicle.longi,vehicle.lati),
		offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
		draggable:false,  //是否可拖动
		content:markerContent   //自定义点标记覆盖物内容
	});
	map.setCenter(new AMap.LngLat(vehicle.longi,vehicle.lati));
	var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.vehino+"</b></td></tr><tr><td><b>[所属公司]</b>："+vehicle.compname+"</tr></td><tr><td><b>[SIM卡]</b>："+vehicle.vehisim+"</tr></td><tr><td><b>[车辆所属人]</b>："+vehicle.ownname
		+"</tr></td><tr><td><b>[联系电话]</b>："+vehicle.owntel+"</tr></td><tr><td><b>[经度]</b>："+vehicle.longi+"</tr></td><tr><td><b>[纬度]</b>："+vehicle.lati;
	var info = [];
	info.push(txt);
	inforWindowone1 = new AMap.InfoWindow({
		offset:new AMap.Pixel(5,10),
		content:info.join("</tr></td></table>")
	});
	inforWindowone1.open(map,marker1.getPosition());
	AMap.event.addListener(marker1,"click",function(e){
		inforWindowone1.open(map,this.getPosition());
	});
}


//右上角按钮显示隐藏
function   lb_open(o){
    var node=o.childNodes[0];
    node.src="resources/images/index/"+node.id+"1.png";

}

function   lb_close(o){
    var node=o.childNodes[0];
    node.src="resources/images/index/"+node.id+"2.png";
}
var open1=false;
function   lb_open1(o){
	if(open1){
		var node=o.childNodes[0];
		node.src="resources/images/index/"+node.id+"2.png";
		o.childNodes[2].style.color='#535353';
		open1=false;
	}else {
		var node=o.childNodes[0];
		node.src="resources/images/index/"+node.id+"1.png";
		o.childNodes[2].style.color='#0C88E8';
		open1=true;
	}
}

//区域
function udaddPolygonvehi(obj,name,ms,sz){  
	var polygonArr=new Array();//多边形覆盖物节点坐标数组   
	var zbs = obj.split(";");
	for(var i=0;i<zbs.length;i++){
		var zb = zbs[i].split(",");
		polygonArr.push(new AMap.LngLat(zb[0],zb[1]));   
	}
	polygonarea=new AMap.Polygon({     
		path:polygonArr,//设置多边形边界路径  
		strokeColor:"#57a993", //线颜色  
		// strokeOpacity:0.2, //线透明度   
		strokeWeight:1,    //线宽   
		fillColor: "#5f5757", //填充色
        fillOpacity: 0.35//填充透明度
	});   
	polygonarea.setMap(map);  

	 	var markerContent = document.createElement("div");
	    markerContent.className = "txtstyle";
		var markerSpan = document.createElement("span");
		markerSpan.innerHTML = "区域名:"+name;
		markerContent.appendChild(markerSpan);
		ms = ppvehi(ms);
		markerContent.onmouseover=function() {markerSpan.innerHTML = "区域名:"+name+" : <br/>区域描述:<br/>"+ms+"<br/>区域面积:"+sz};
		markerContent.onmouseout=function() {markerSpan.innerHTML = "区域名:"+name;};
	    markerarea = new AMap.Marker({
		    map:map,
		     zIndex:10001, 
		    position:new AMap.LngLat( zbs[0].split(",")[0],zbs[0].split(",")[1]),     
		    offset:new AMap.Pixel(-14,7), //相对于基点的偏移位置
		    draggable:false,  //是否可拖动
		    content:markerContent  //自定义点标记覆盖物内容
		    
		});

}
function ppvehi(obj){
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

