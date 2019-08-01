	var qd_jwd="",zj_jwd="",zd_jwd="";
	var kt1=new Array(),kt2=new Array();
	var mydate = new Date();
	var smydate = new Date(mydate.getTime() - 1000 * 60 * 60*1);
	$("#qd_stime").val(setformatnewlc(smydate));
	$("#qd_etime").val("30");
	$("#zd_stime").val(setformatnewlc(smydate));
	$("#zd_etime").val("30");
	function setformatnewlc(obj){
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
	
	            var fxudmouseTool=null;
	            var polygonfk = null;
	            var polygonfks = [null,null,null];
	            var udpolygonOption = {
	                strokeColor:"",
	                strokeOpacity:0,
	                strokeWeight:1
	            };
	            var isStart = false, isEnd = false, isMiddle = false;
	            var shiwumap = null, mouseTool = null;
	            $(function() {
	            	var position = new AMap.LngLat(120.153576,30.287459);//创建中心点坐标
	            		shiwumap = new AMap.Map('AMap', {
	                	center : position,
	                    resizeEnable:true,
	                    zoom:15
	                });
	                $('.clearMap').hide().click(function() {
	                    if(polygonfk!=null) {shiwumap.clearMap();}
	                    qd_jwd="";zj_jwd="";zd_jwd="";
	                    isStart = false; isEnd = false; isMiddle = false; polygonfks = [null,null,null];
	                    $(this).hide();
	                });
	                $('.dwqdfw').click(function() {
	                    if (!isStart) isStart = arawAreaFun(shiwumap, isStart, 1);
	                });
	                $('.dwzdfw').click(function(){
	                    if (!isEnd) isEnd = arawAreaFun(shiwumap, isEnd, 2);
	                });
	                $('.tabFanweiTitle li').click(function() {
	                    $('.tabFanweiTitle li').removeAttr('data-checked');
	                    $(this).attr('data-checked', '');
	                    $('[id$=Panel]').css({'display':'none'});
	                    let dataId = $(this).attr('data-id');
	                    $('#' + dataId).css({'display':'block'});
	                });
	            });
	            function arawAreaFun(mapObj, isArea, num) {
	                console.info(num);
	                if(isArea) return false;
	                mapObj.plugin(["AMap.MouseTool"],function(){
	                    fxudmouseTool = new AMap.MouseTool(mapObj);
	                    fxudmouseTool.polygon(udpolygonOption);   //使用鼠标工具绘制多边形
	                    AMap.event.addListener(fxudmouseTool, "draw", function(e){
	                        var drawObj = e.obj;
	                        var pointsCount = e.obj.getPath().length;
	                        var a =  e.obj.getPath();
	                        var zbs = "";
	                        var polygonArr1 = [];//多边形覆盖物节点坐标数组
	                        for(var i=0;i<pointsCount;i++){
	                            polygonArr1.push([a[i].lng,a[i].lat]);
	                        }
	                        polygonfk = fanwei(polygonArr1, num);
	                        polygonfk.setMap(mapObj);
	                        if (num == 1) polygonfks[0] = polygonfk;
	                        else polygonfks[1] = polygonfk;
	                        fxudmouseTool.close(true);
	                        fxudmouseTool=null;
	                        $('.clearMap').show();
	                    });
	                });
	                return true
	            }
	            function fanwei(polygonArr1, num) {
	                if (1 == num) {
	                	qd_jwd="";
	                	for(var i =0;i<polygonArr1.length;i++){
	                		qd_jwd+=polygonArr1[i]+";";
	                		kt1.push(polygonArr1[i]);
	                	}
	                    return new AMap.Polygon({
	                        path: polygonArr1,//设置多边形边界路径
	                        strokeColor: "#1791fc", //线颜色
	                        strokeOpacity: 1, //线透明度
	                        strokeWeight: 2,    //线宽
	                        fillColor: "#1791fc", //填充色
	                        fillOpacity:.5//填充透明度
	                    });
	                }
	                if (2 == num) {
	                	zd_jwd="";
	                	for(var i =0;i<polygonArr1.length;i++){
	                		zd_jwd+=polygonArr1[i]+";";
	                		kt2.push(polygonArr1[i]);
	                	}
	                    return new AMap.Polygon({
	                        path: polygonArr1,//设置多边形边界路径
	                        strokeColor: "#1791fc", //线颜色
	                        strokeOpacity: 1, //线透明度
	                        strokeWeight: 2,    //线宽
	                        fillColor: "#1791fc", //填充色
	                        fillOpacity:.5//填充透明度
	                    });
	                }
	                return new AMap.Polygon({
	                    path: polygonArr1,//设置多边形边界路径
	                    strokeColor: "#1791fc", //线颜色
	                    strokeOpacity: 1, //线透明度
	                    strokeWeight: 2,    //线宽
	                    fillColor: "#1791fc", //填充色
	                    fillOpacity:.5//填充透明度
	                });
	            }
	            
	            function timestart(e,c){
	            	var ddate = new Date(e); 
	            	var result = new Date(ddate - parseInt(c)  * 60);
	            	console.log(ddate)
 	            	return setformatnewlc(result);
	            }
	            
	            function timeend(e,c){
	            	var ddate = new Date(e); 
	            	var result = new Date(ddate + parseInt(c)  * 60);
	            	return setformatnewlc(result);
	            }
	            
	            function xxdc(){
	            	if($('#clxxPanel table tbody').html()==""){
	            		alert("无数据无法导出信息!");
	            	}else{
	            		require(['dojo/_base/json'], function(dojo){
	            		var data= {
	            				"qd_stime" : timestart($("#qd_stime").val(),$("#qd_etime").val()),
	            				"qd_etime" : timeend($("#qd_stime").val(),$("#qd_etime").val()),
	            				"zd_stime" : timestart($("#zd_stime").val(),$("#zd_etime").val()),
	            				"zd_etime" : timeend($("#zd_stime").val(),$("#zd_etime").val()),
	            				"qd_jwd" : qd_jwd,
	            				"zd_jwd" : zd_jwd,
	            	        };
	            		url = "claq/findswczexcle?data="+dojo.toJson(data)
	        				, window.open(url)
	            		});
	            	}
	            }
	            //轨迹
	            function findlsgj(obj){
	            	layer.load(2);
	            	require([ "app/createSyncStore"], function(createSyncStore) {
	            		for(var i=0;i<shiwumap.getAllOverlays().length;i++){
	            			if(shiwumap.getAllOverlays()[i].CLASS_NAME !="AMap.Polygon"){
	            				shiwumap.getAllOverlays()[i].hide();
	            			}
	            		}
	            		var xhrArgs = {
	            				url : basePath + "clqk/findGj",
	            				postData : 'postData={"sTime":"'+timestart($("#qd_stime").val(),$("#qd_etime").val())+'","eTime":"'+timeend($("#qd_stime").val(),$("#qd_etime").val())+'","vehino":"'+zj[obj].vehi_no+'"}',
	            				handleAs : "json",
	            				load : function(data) {
	            					console.log('111',data)
	            					
//	            					flag=true;
	            					if(data.length==0){
	            						alert("当前时段没有历史轨迹");
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
//	            						markerMovingControl = new MarkerMovingControl(shiwumap, markerhistory, lineArr);
	            							completeEventHandler(data);
	            							data[i] = dojo.mixin({
	            								id : i + 1
	            							}, data[i]);
	            						}
	            					}
	            					console.log(new Date())
	            					layer.closeAll('loading');
	            				},
	            				error : function(error) {
	            					layer.closeAll('loading');
	            				}
	            			};
	            		dojo.xhrPost(xhrArgs);
	            	});
	            }
	            
	            
	            var zj;
	            var kt1,kt2;
//	            var flag=false;
	            function findswcz(){
	            	layer.load(2);
	            	$("#swcz_cx").attr('disabled',"true");
	            	$.ajax({
	            		 type: "POST",
	            	        url:"claq/findswcz",
	            	        data:{
	            				"qd_stime" : timestart($("#qd_stime").val(),$("#qd_etime").val()),
	            				"qd_etime" : timeend($("#qd_stime").val(),$("#qd_etime").val()),
//	            				"zj_stime" : $("#zj_stime").val(),
//	            				"zj_etime" : $("#zj_etime").val(),
	            				"zd_stime" : timestart($("#zd_stime").val(),$("#zd_etime").val()),
	            				"zd_etime" : timeend($("#zd_stime").val(),$("#zd_etime").val()),
	            				"qd_jwd" : qd_jwd,
	            				"zd_jwd" : zd_jwd,
//	            				"zj_jwd" : zj_jwd
	            	        },
	            	        dataType: 'json',
	            			timeout : 3600000,
	            		success:function(json){
	            			console.log(json)
//	            			if(flag){
//	            				polygonarea1=new AMap.Polygon({     
//	            					map:shiwumap,
//	            					path:kt1,//设置多边形边界路径  
//	            					strokeColor: "#1791fc", //线颜色
//	    	                        strokeOpacity: 1, //线透明度
//	    	                        strokeWeight: 2,    //线宽
//	    	                        fillColor: "#1791fc", //填充色
//	    	                        fillOpacity:.5//填充透明度
//	            				});
//	            				polygonarea1=new AMap.Polygon({     
//	            					map:shiwumap,
//	            					path:kt2,//设置多边形边界路径  
//	            					strokeColor: "#1791fc", //线颜色
//	    	                        strokeOpacity: 1, //线透明度
//	    	                        strokeWeight: 2,    //线宽
//	    	                        fillColor: "#1791fc", //填充色
//	    	                        fillOpacity:.5//填充透明度
//	            				});
//	            				flag=false;
//	            			}
	            			$('#qdfwPanel table tbody').empty();
                            $('#clxxPanel table tbody').empty();
                            $('#zdfwPanel table tbody').empty();
                            console.log(json[0].length+"   "+json[1].length
                            		+"   "+json[2].length
                            		)
                            if(json[0].length>0||json[1].length>0
                            		||json[2].length>0
                            		){
                            	console.log(json)
    	            			var qd = json[0];
    	            			var zd = json[1];
    	            			zj = json[2];
    	            			var html = "";
    	            			var html1="";
    	            			var html2="";
    	            			for(var i =0;i<qd.length;i++){
    	            				html+="<tr><td style='width:20%'>"+qd[i].vehi_no+"</td><td style='width:40%'>"
    	            				+qd[i].px.substr(0,qd[i].px.length-1)+"</td><td style='width:40%'>"+qd[i].stime.substr(0,qd[i].stime.length-1)+"</td></tr>";
    	            			}
    	            			for(var i =0;i<zd.length;i++){
    	            				html1+="<tr><td style='width:20%'>"+zd[i].vehi_no+"</td><td style='width:40%'>"
    	            				+zd[i].px.substr(0,zd[i].px.length-1)+"</td><td style='width:40%'>"+zd[i].stime.substr(0,zd[i].stime.length-1)+"</td></tr>";
    	            			}
    	            			
    	            			var icon="resources/images/c.png";
    	            			var marker1;
    	            			for(var i =0;i<zj.length;i++){
    	            				html2+="<tr><td style='width:20%'>"+zj[i].vehi_no+"</td><td style='width:42%'>"
    	            				+zj[i].px.substr(0,zj[i].px.length-1)+"</td><td style='width:38%'>"+zj[i].stime.substr(0,zj[i].stime.length-1)+"</td><td><button style='width: 44px;' class='guijichaxun' onclick='findlsgj("+i+");'>轨迹</button></td></tr>";
    	            				marker1 = new AMap.Marker({
	                 	  			    map:shiwumap,
	                 				    icon:icon,
	                 				    position:new AMap.LngLat(zj[i].px.substr(0,zj[i].px.length-1).split(",")[0],zj[i].px.substr(0,zj[i].px.length-1).split(",")[1]),
	                 				    offset:new AMap.Pixel(-8,-8), //相对于基点的偏移位置
	                 				    draggable:false
	                 				});
    	            				marker1.vehicle=zj[i];
    	            				AMap.event.addListener(marker1,"click",function(e){
    	            					console.log(11)
    	            					console.log(this)
    	            					var obj = this.vehicle;
	             				  	  	var txt = "<b style='color:#3399FF'>"+obj.vehi_no+"</b>";
	             				  	  	inforWindow.setContent(txt);
	             				  	  	inforWindow.open(shiwumap,new AMap.LngLat(obj.px.substr(0,obj.px.length-1).split(",")[0],obj.px.substr(0,obj.px.length-1).split(",")[1]));
	                 				});
	                                AMap.event.addListener(shiwumap,"click",function(e){
	                                	inforWindow.close();
	                 				});
    	                                
    	            			}
    	            			$('#qdfwPanel table tbody').append(html);
                                $('#clxxPanel table tbody').append(html2);
                                $('#zdfwPanel table tbody').append(html1);
                                $('#swcz_cx').removeAttr("disabled");
                                layer.closeAll('loading');
                            }else{
                            	$('#swcz_cx').removeAttr("disabled");
                            	layer.closeAll('loading');
                            	alert("该时间内起点与终点区域内没有符合条件的车辆");
                            }
	            		},
	            		error:function(){
	            			layer.closeAll('loading');
	            		}		
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
//	            				  +"<br/><b>[位置描述]</b>："+obj.ADDRESS;
	            	var marker1 = new AMap.Marker({
	            	    map:shiwumap,
	            	    position:new AMap.LngLat(obj.PX,obj.PY),
	            	    zIndex:102,
	            	    offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
	            	    draggable:false,  //是否可拖动
	            	    content:markerContent   //自定义点标记覆盖物内容
	            	});
	            	//$("#img"+obj.messageid).rotate(obj.direction-90);
	            	marker1.setMap(shiwumap);  //在地图上添加点

	            	//添加文本标记
	            	var info = [];
	            	info.push(txt);

	            	var inforWindow = new AMap.InfoWindow({
	            	  offset:new AMap.Pixel(0,0),
	            	  content:info.join("<br>")
	            	});
	            	  AMap.event.addListener(marker1,"click",function(e){
	            		  inforWindow.open(shiwumap,marker1.getPosition());
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
//	            				  +"<br/><b>[位置描述]</b>："+obj.address;
	            	var marker1 = new AMap.Marker({
	            	    map:shiwumap,
	            	    position:new AMap.LngLat(obj.PX,obj.PY),
	            	    zIndex:102,
	            	    offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
	            	    draggable:false,  //是否可拖动
	            	    content:markerContent   //自定义点标记覆盖物内容
	            	});
	            	//$("#img"+obj.messageid).rotate(obj.direction-90);
	            	marker1.setMap(shiwumap);  //在地图上添加点

	            	//添加文本标记
	            	var info = [];
	            	info.push(txt);

	            	var inforWindow = new AMap.InfoWindow({
	            	  offset:new AMap.Pixel(0,0),
	            	  content:info.join("<br>")
	            	});
	            	  AMap.event.addListener(marker1,"click",function(e){
	            		  inforWindow.open(shiwumap,marker1.getPosition());
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
//	            				  +"<br/><b>[位置描述]</b>："+obj.address;
	            				  var marker1 =null;
	            if(obj.STATE==0){
	            	var marker1 = new AMap.Marker({
	            	    map:shiwumap,
	            	    position:new AMap.LngLat(obj.PX,obj.PY),
	            	    offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
	            	    draggable:false,  //是否可拖动
	            	    icon:"resources/images/fx.jpg",
	            	  //  content:markerContent,   //自定义点标记覆盖物内容
	            	    angle:obj.DIRECTION-90
	            	});
	            	}else if(obj.STATE==1){
	            		var marker1 = new AMap.Marker({
	            		    map:shiwumap,
	            		    position:new AMap.LngLat(obj.PX,obj.PY),
	            		    offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
	            		    draggable:false,  //是否可拖动
	            		    icon:"resources/images/fx2.png",
	            		  //  content:markerContent,   //自定义点标记覆盖物内容
	            		    angle:obj.DIRECTION-90
	            		});
	            		}
	            	//$("#img"+obj.messageid).rotate(obj.direction-90);
	            	marker1.setMap(shiwumap);  //在地图上添加点

	            	//添加文本标记
	            	var info = [];
	            	info.push(txt);

	            	var inforWindow = new AMap.InfoWindow({
	            	  offset:new AMap.Pixel(0,0),
	            	  content:info.join("<br>")
	            	});
	            	  AMap.event.addListener(marker1,"click",function(e){
	            		  inforWindow.open(shiwumap,marker1.getPosition());
	            		});

	            	//添加角度
	            	//markerlist.push(marker1);
	            	});
	            }

	            //添加轨迹
	            function completeEventHandler(vehigps){
	            	if(vehigps[0].STATE==0){
	            		markerhistory = new AMap.Marker({
	            	        map:shiwumap,
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
	            	        map:shiwumap,
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
	                    map:shiwumap,
	                    path:lineArr,
	                    strokeColor:"#00A",//线颜色
	                    strokeOpacity:1,//线透明度
	                    strokeWeight:3,//线宽
	                    strokeStyle:"dashed"//线样式
	                });
	                shiwumap.setFitView();
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