var zdgzclmap;
var zdgzclvehilist;
var vhicsz=new Array();
dojo.ready(function(){
	zdgzclmap = new AMap.Map('zdgzclmap', {
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
			zdgzclvehilist = data.vehilist;
		}
	})
});

require(["dijit/layout/TabContainer", "dijit/Dialog","dijit/layout/ContentPane", "dijit/form/FilteringSelect", "dijit/form/Button", "dojo/on","dojo/domReady!"]
, function(TabContainer,Dialog, ContentPane,FilteringSelect,Button,on){
	
	var mapTabContainer4 = new TabContainer({
			doLayout:false,
			style: "opacity: 0.9;" //height: 50%; width: 300px;
   }, "panel_zdgzcl");

   var cp4 = new ContentPane({
	   	id:'cp_zdgzcl',
        title: "车辆跟踪",
        style:"height:600px;overflow: hidden",
        content: "<div id='live_query_box_zdgzcl'></div><div id='live_query_box_deatil_zdgzcl'></div></div>"
   });
   mapTabContainer4.addChild(cp4);
   cp4.onShow = function(){
		tc.resize();
   };
   mapTabContainer4.startup();
   liveQuery = new LiveQuery(basePath+'clqk/onevhic?vehino=','live_query_box_zdgzcl');//clqk/onevhic?vehino=12
   liveQuery.defaultMessage 	= "请输入车牌号/公司名";
   liveQuery.liveQueryBoxDeatil = 'live_query_box_deatil_zdgzcl';
   liveQuery.setDetail = function(){
   			var qyjkvehi=zdgzclvehilist;
   			console.log(this)
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
			var tab="<table class='fontsize zdgzcltable'>";
   			for(var i=0; i<vhicsz.length; i++){
   				tab += "<tr class='types'><td style='width:50px'>"
   					+"<input type='checkbox' class='selectRow' name='selectRow' value = '"+vhicsz[i]+"'/>"
					+"</td><td onclick='zdgzcl_mar(\""+vhicsz[i]+"\");'>"+vhicsz[i]+"</td></tr>";
   			}
   			dojo.byId('live_query_box_deatil_zdgzcl').innerHTML= tab+"</table>";
   			$('.selectRow').on('click', function(){
   				zdgzclmap.clearMap();
   				var fruit = [];
   				$("input:checkbox[name='selectRow']:checked").each(function() {
   					fruit.push($(this).val());
   				});
   				console.log(fruit)
   				if(fruit.length>0){
   					for(var i=0; i<zdgzclvehilist.length; i++){
   						for(var j=0; j<fruit.length; j++){
   							if(zdgzclvehilist[i].vehino == fruit[j]){
   								zdcldd(zdgzclvehilist[i]);
   							}
   						}
   					}
   				}
   		   })
   };
}); 
//打点
function zdcldd(obj){
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
		   map:zdgzclmap,
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
		  inforWindow.open(zdgzclmap,new AMap.LngLat(obj.longi,obj.lati));
		});
//    AMap.event.addListener(zdgzclmap,"click",function(e){
//	  inforWindow.close();
//	});
	zdgzclmap.setZoomAndCenter(15, [obj.longi, obj.lati]);
}
function zdgzcl_mar(obj){
	var type = 0;
	var px,py;
	for(var i=0; i<zdgzclvehilist.length; i++){
		if(zdgzclvehilist[i].vehino == obj){
			py=zdgzclvehilist[i].lati;
			px=zdgzclvehilist[i].longi;
		}
	}
	var mar = zdgzclmap.getAllOverlays();
	for(var i=0; i<mar.length;i++){
		if(mar[i].hasOwnProperty('vehicle') && mar[i].vehicle.vehino==obj){
			type = 1;
			AMap.event.trigger(mar[i],'click');
		}
	}
	if(type == 1){
		zdgzclmap.setZoomAndCenter(15, [px, py]);
	}
}
