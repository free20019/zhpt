var map;
var polygonarea=null;
var markerarea=null;
var area_name=[];
var area_jb = [];
var level;
var index_xiabiao;
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
   
   mapTabContainer.addChild(cp1);
   cp1.onShow = function(){
		tc.resize();
   };
   mapTabContainer.startup();

    dojo.xhrGet({
        url : basePath + "clqk/qyjk",//clqk/qyjk
        //url:'http://localhost:8080/zhpt/clqk/qyjk',
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
            dadian();
            dojo.query('#cp_qyjk tr').forEach(function(item,index) {
                var sjjbcl = parseInt(dojo.query('#cp_qyjk tr')[index].children[1].innerText) -
                    parseInt(dojo.query('#cp_qyjk tr')[index].children[2].innerText);
                if (sjjbcl>=-3) {
                    if(sjjbcl >= -3){
                        dojo.query('#cp_qyjk tr')[index].style.color = '#FF0907';
                        dojo.query('#cp_qyjk tr')[index].style.fontWeight = 'bold';
                        area_name.push(dojo.query('#cp_qyjk tr')[index].children[0].innerText)
                        if(sjjbcl<200){
                            level='一级';
                            area_jb.push(level);
                        }
                        if(sjjbcl>200 && sjjbcl<500){
                            level='二级';
                            area_jb.push(level);
                        }
                        if(sjjbcl>500){
                            level='三级';
                            area_jb.push(level);
                        }
                    }
                }
            });
        if(area_name.length>0){
            dojo.byId('right-top1').style.display = 'block';
            on(dojo.byId('right-top1'), 'click',function () {
                dojo.query('#yjbjxx li').remove();
                var li = "";
                var date=new Date().Format("yyyy-MM-dd hh:mm");
                for (var i = 0; i < area_name.length; i++) {
                    li += "<li>" +
                        "<a><span>时间：" + date + "</span></a>" +
                        "<a><span>地点：" + area_name[i] + "</span></a>" +
                        "<a><span>事件级别：" + area_jb[i] + "</span></a>" +
                        "</li>";
                }
                dojo.query('#yjbjxx ul')[0].innerHTML = li;
                dojo.byId('yjbjxx').style.display = 'block';
                dojo.query('#yjbjxx li').forEach(function(item,index){
                    dojo.connect(item, "dblclick", function() {
                        fireNavItem('接入');
                        dojo.byId('yjbjxx').style.display = 'none';
                        index_xiabiao=index;
                        console.log(index_xiabiao)
                    });
                });
            });
        }
        }
    })
   
});
function redenerQyjk(flag){
    var table = json2table(qyjk.arealist);
    dijit.byId('cp_qyjk').set('content',table);
    noscroll(table);
// 	var div = dojo.create("div", {style:" overflow: auto; padding-right: 15px;width:115%;height: 100%;" });
// 	dojo.place(json2table(qyjk.arealist),div,'first')
// 	dojo.place(div,'cp_qyjk','first')
    dojo.query('#cp_qyjk tr').forEach(function(item,index){
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


var inforWindow = new AMap.InfoWindow({
    offset:new AMap.Pixel(0,0)
});
inforWindow.on('close',function(){
    if(inforWindow.getIsOpen()){

        console.log('close...');
    }
});
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
});
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
            AMap.event.addListener(marker1,"click",function(e){
                //  console.log(this,e)
                var obj = this.vehicle;
                var txt = "<b style='color:#3399FF'>"+obj.vehino+"</b><br/><b>[所属公司]</b>："+obj.compname+"<br/><b>[车辆类型]</b>："+obj.cartype+"<br/><b>[车辆颜色]</b>："+obj.color+"<br/><b>[GPS时间]</b>："+obj.dateTime.substr(0,19)+"<br/><b>[SIM卡]</b>："+obj.vehisim+"<br/><b>[联系人]</b>："+obj.ownname
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
function noscroll(node){
    console.log('###noscroll....')
    if (typeof node == "string") {
        node =    dojo.byId(node);
    }
    var parentNode = node.parentNode;
    var newWidth = (parseInt(dojo.getComputedStyle(node).width) + 15) +'px';
    console.log('###'+newWidth);
    dojo.setStyle(node,{'overflow':'hidden'});
//	node.style.width = newWidth
    var warperParent = dojo.create("div",{style:"overflow: hidden;  position: relative;height:100%"})
    var warperSub = dojo.create("div",{style:" overflow: auto; padding-right: 15px; height: 100%;width:"+newWidth})
    dojo.place(warperParent,parentNode,'first')
    dojo.place(warperSub,warperParent,'first')
    dojo.place(node,warperSub,'first')
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
function fireNavItem(title){
    dojo.query('#li1_1_text .li2').forEach(function(item,index){
        if(item.innerText == title){
            item.click();
            setTimeout('yanchi()',500);
            }
    });
}
function yanchi(){
    AddyjsjjrDialog.show();
    dijit.byId('yjsj_fsdz').set('value', dojo.query('#yjbjxx li')[index_xiabiao].children[1].innerText.substr(3));
    dijit.byId('yjsj_sjjb').set('value', level);
    changeaddress(dojo.query('#yjbjxx li')[index_xiabiao].children[1].innerText.substr(3));
}
function changeaddress(e){
    var wljd='';
    var wlwd='';
    var wldz='';
    for(var i=0;i<qyjk.arealist.length;i++){
        if(qyjk.arealist[i].areaname == e){
            dijit.byId('yjsj_jwdxx').set('value',qyjk.arealist[i].areazbs.split(';')[0].split(',')[0]+ ", " + qyjk.arealist[i].areazbs.split(';')[0].split(',')[1]);
            wljd=qyjk.arealist[i].areazbs.split(';')[0].split(',')[0];
            wlwd=qyjk.arealist[i].areazbs.split(';')[0].split(',')[1];
            wldz=qyjk.arealist[i].areaname;
            console.log(wljd,wlwd,wldz)
        }
    }
    var map = new AMap.Map('sjlrMap', {
        resizeEnable: true,
        zoom:11
//        	        center: [116.397428, 39.90923]


    }),lnglatXY = [wljd, wlwd]; //已知点坐标
    regeocoder();
    function regeocoder() {  //逆地理编码
        var geocoder = new AMap.Geocoder({
            radius: 1000,
            extensions: "all"
        });
        geocoder.getAddress(lnglatXY, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                geocoder_CallBack(result);
            }
        });
        var marker = new AMap.Marker({  //加点
            map: map,
            position: lnglatXY
        });
        var infoWindow = new AMap.InfoWindow({
            content: wldz,
            offset: {x: 0, y: -30}
        });
        infoWindow.open(map, marker.getPosition());
        function geocoder_CallBack(data) {
            map.setFitView();
        }
    }
}


Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}