var zdcljkmap;
var clgzvehilist;
var vhicsz=new Array();
dojo.ready(function(){
	zdcljkmap = new AMap.Map('map2', {
        animateEnable:false,
        jogEnable:false,
		view: new AMap.View2D({
			center: new AMap.LngLat(120.153576,30.287459),
			zoom: 15
		})
	});	
	dojo.xhrGet({
		url : basePath + "clqk/qyjk",//clqk/qyjk
		handleAs : "json",
		load : function(data) {
			clgzvehilist = data.vehilist;
		}
	})
});

require(["dijit/layout/TabContainer", "dijit/Dialog","dijit/layout/ContentPane", "dijit/form/FilteringSelect", "dijit/form/Button", "dojo/on","dojo/domReady!"]
, function(TabContainer,Dialog, ContentPane,FilteringSelect,Button,on){
	
	var mapTabContainer9 = new TabContainer({
			doLayout:false,
			style: "opacity: 0.9;" //height: 50%; width: 300px;
   }, "panel_left3");

   var cp9 = new ContentPane({
	   	id:'cp_zdcl',
        title: "车辆跟踪",
        style:"height:600px;overflow: hidden",
        content: "<div id='live_query_box_clgz'></div><div id='live_query_box_deatil_clgz'></div></div>"
   });
   mapTabContainer9.addChild(cp9);
   cp9.onShow = function(){
		tc.resize();
   };
   mapTabContainer9.startup();
   liveQuery = new LiveQuery(basePath+'clqk/onevhic?vehino=','live_query_box_clgz');//clqk/onevhic?vehino=12
   liveQuery.defaultMessage 	= "请输入车牌号/公司名";
   liveQuery.liveQueryBoxDeatil = 'live_query_box_deatil_clgz';
   liveQuery.setDetail = function(){
   			var qyjkvehi=clgzvehilist;
   			for(var i=0;i<qyjkvehi.length;i++){
   				if(qyjkvehi[i].vehino==this.currentSelect.title){
   					var index = $.inArray(qyjkvehi[i].vehino, vhicsz);
   					console.log(index)
   					if(vhicsz.length>0){
   						if(index==-1){
   							vhicsz.push(qyjkvehi[i].vehino);
   						}else{
   							alert('该车牌号已存在');
   						}
   					}else{
   						vhicsz.push(qyjkvehi[i].vehino);
   					}
   				}
   			}
			var tab="<table class='fontsize'>";
   			for(var i=0; i<vhicsz.length; i++){
   				tab += "<tr class='types'><td style='width:90px' onclick='zdcldd(\""+vhicsz[i]+"\");'>"+vhicsz[i]
					+"</td><td><button onclick='clgz(\""+vhicsz[i]+"\");'>车辆跟踪"
					+"</button><button>车辆轨迹</button></td></tr>";
   			}
   			dojo.byId('live_query_box_deatil_clgz').innerHTML= tab+"</table>";
   };
}); 
//打点
function zdcldd(info){
	zdcljkmap.clearMap();
	if(st1){
		clearInterval(st1);
		arrline1=[];
		marker1=null;
		polyline1=null;
	}
	var obj;
	for(var i=0; i<clgzvehilist.length; i++){
		if(clgzvehilist[i].vehino == info){
			obj = clgzvehilist[i];
		}
	}
	console.log(obj)
    var icon = '';
	if(obj.onoffstate=="1"){
		if(obj.carStatus=="1"){
			icon="resources/images/h.png";   
		}else{
			icon="resources/images/c.png";   
		}
	}else{
		icon="resources/images/d.png";   
	}
	var marker1 = new AMap.Marker({
		   map:zdcljkmap,
		   icon:icon,
		    position:new AMap.LngLat(obj.longi,obj.lati),
		    offset:new AMap.Pixel(-8,-8), //相对于基点的偏移位置
		    draggable:false
		});
	marker1.vehicle=obj;
	AMap.event.addListener(marker1,"click",function(e){   
		  var obj = this.vehicle;
		  	var txt = "<b style='color:#3399FF'>"+obj.vehino+"</b><br/><b>[所属公司]</b>："+obj.compname+"<br/><b>[SIM卡号]</b>："+obj.vehisim+"<br/><b>[联系人]</b>："+obj.ownname
					  +"<br/><b>[联系电话]</b>："+obj.owntel;
		  inforWindow.setContent(txt);
		  inforWindow.open(zdcljkmap,new AMap.LngLat(obj.longi,obj.lati));
		});
//    AMap.event.addListener(zdcljkmap,"click",function(e){
//	  inforWindow.close();
//	});
	zdcljkmap.setZoomAndCenter(15, [obj.longi, obj.lati]);
}
var st1,arrline1=[],marker1=null,polyline1=null;
function clgz(info){
	if(st1){
		zdcljkmap.clearMap();
		clearInterval(st1);
		arrline1=[];
		marker1=null;
		polyline1=null;
	}
	cxdata1(info);
	st1 = setInterval(function() {
		cxdata1(info);
	}, 30000);
}
function cxdata1(obj){
	$.ajax({
		url : basePath + 'clqk/clgz',
		type : 'post',
		data:{
			"vhic" : obj
		},
		dataType: 'json',
		timeout : 180000,
		success:function(json){
			console.log(json);
			if(json.length==0){
				alert("该车暂无轨迹");
				zdcljkmap.clearMap();
				clearInterval(st1);
				arrline1=[];
				marker1=null;
				polyline1=null;
				return;
			}
			var data = json[0];
        	var amapxy = new AMap.LngLat(data.PX,data.PY);
        	arrline1.push(amapxy);
        	if(marker1!=null){
        		var xiaomarker1= new AMap.Marker({
            	    map:zdcljkmap,
            	    position:marker1.getPosition(),
            	    zIndex:102,  
            	    offset:new AMap.Pixel(-6,-6), //相对于基点的偏移位置
            	    draggable:false,
            	    angle:marker1.getAngle(),
            	    icon:"resources/images/fx4.png"
            	});
        		marker1.setAngle(data.heading-90);
        		marker1.setPosition(amapxy);
        		polyline1.setPath(arrline1);
        	}else{
        		marker1 = new AMap.Marker({
            	    map:zdcljkmap,
            	    position:amapxy,
            	    zIndex:102,  
            	    offset:new AMap.Pixel(-12,-12), //相对于基点的偏移位置
            	    draggable:false,
            	    angle:data.HEADING-90,
            	    icon:"resources/images/fpcar.png"
            	});
        		var txt = "<b style='color:#3399FF'>"+data.VEHI_NO+"</b><br/><br/><b>[GPS时间]</b>："+data.SPEED_TIME
        		+"<br/><b>[GPS速度]</b>："+data.SPEED
        		+"<br/><b>[行驶方向]</b>："+data.HEADING;
        		var info = [];                 
            	info.push(txt);                 
            	var inforWindow = new AMap.InfoWindow({                 
            	  offset:new AMap.Pixel(0,0),                 
            	  content:info.join("<br>")                 
            	});                 
            	  AMap.event.addListener(marker1,"click",function(e){
            		  getaddress(new AMap.LngLat(data.PX,data.PY),1);
            		  inforWindow.open(zdcljkmap,marker1.getPosition());              
            		});
        		polyline1 = new AMap.Polyline({
                    map:zdcljkmap,
                    path:arrline1,
                    strokeColor:"#000",//线颜色
                    strokeOpacity:1,//线透明度
                    strokeWeight:3,//线宽
                    strokeStyle:"solid"//线样式
                });
        	}
        	
        	zdcljkmap.setZoomAndCenter(15,amapxy);
        },
		error:function(){
		}		
	});
}