	var fsContent	= null;
var sjvehilist = null;
var qbcl = null;
var qyjk=null;
var circle=null;
var inforWindowone1=null;
var marker1=null;
var wzclslxx=null;
var lsgjDialog1=null;
var lsgjmap1=null;

		var zhddRyGrid =zhddClGrid= storeRy = storeCl = myDialog = null;
		function gridStartUp(){
			zhddClGrid.startup();
		}
		var grid2StartUp,grid3StartUp;

		require(["dijit/Dialog", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/FilteringSelect"
			         ,"dijit/form/CheckBox"
			         ,"dijit/form/TimeTextBox","dijit/form/SimpleTextarea"
			          ,"dgrid/OnDemandGrid","dijit/form/TextBox","dgrid/extensions/Pagination"
			          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
			          ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
			          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare"
			          ,"cbtree/Tree","cbtree/models/ForestStoreModel","dojo/data/ItemFileWriteStore"
			          ,"dojo/dom-construct","dojo/on","app/util","dojo/domReady!"], function(
			        		  Dialog,Editor, Button,DateTextBox,FilteringSelect,CheckBox,TimeTextBox,SimpleTextarea,Grid,TextBox
			        		  , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
			        		  ,registry, domStyle,declare
			        		  ,Tree, ForestStoreModel,ItemFileWriteStore
			        		  , dc,on,util ) {


			var marker="";
			//地图
			var map = new AMap.Map('ssjk_map', {
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



			var xhrArgsqb = {
				url : basePath+"yjzh/findyjsj",
				handleAs : "json",
				load : function(data) {
					if(data.info==1) {
						dojo.byId('message-center').style.display='none';
					}else{
						gg=data.datas;
						//新事件详情
					}
				}
			};
			dojo.xhrPost(xhrArgsqb);

			dojo.connect(dojo.byId('message-center'),'click',this,function(){
				dojo.query('#ditial2')[0].innerHTML='';
				dojo.query('#check_new_event ul li').remove();
				dojo.query('#ditial_car ul li').remove();
				dojo.query("#check_new_car ul li").remove();
				dojo.query('#ditial2 span').remove();
				dojo.query('#ditial2 a').remove();
				dojo.byId('ditial2').innerHTML='';
				dojo.query('#back span')[0].innerHTML='共计'+gg.length+'条记录';
				if(dojo.byId('ditial_car').style.display == 'block'){
					toggleElement('searchbox',toggleName);
					dojo.byId('searchbox').style.display = 'block';
					map.clearMap();
				}else {
					toggleElement('check_new_event',toggleName);
					dojo.byId('back').style.display = 'block';
					dojo.byId('ditial_car').style.display = 'block';
					dojo.byId('searchbox').style.display = 'block';
					num=7;

					var xhrArgssjkcl = {
						url : basePath+"yjzh/findyjsj",
						handleAs : "json",
						load : function(data) {
							if(data.info==1) {
								dojo.byId('message-center').style.display='none';
							}else{
								wzclslxx=data.datas;
								//新事件详情
							}
						}
					};
					dojo.xhrPost(xhrArgssjkcl).then(function(){
						var li1="<li>事件名称 : <span>"+wzclslxx[0].SJZT+"</span></li>"+
							"<li>事件时间 : <span>"+util.formatYYYYMMDDHHMISS(wzclslxx[0].TIME)+"</span></li>"+
							"<li>时间地点 : <span>"+wzclslxx[0].ADDRESS+"</span></li>"+
							"<li>事件内容 : <span>"+wzclslxx[0].SJNR+"</span></li>";
						dojo.query('#check_new_event ul')[0].innerHTML+=li1;

						//相关车辆
						var li3="<span></span><i>有关车辆:</i>" +
							//"<input id='sousuocl' placeholder='搜索车辆'>" +
							"<a id='bcxx'>保存</a>";
						dojo.query('#ditial2')[0].innerHTML+=li3;

						markervhic(wzclslxx[0],4);

						circle = new AMap.Circle({
							center: new AMap.LngLat("120.153576","30.287459"),// 圆心位置
							radius: 500, //半径
							strokeColor: "rgba(244, 35, 0, 0.54)", //线颜色
							strokeOpacity: 0.54, //线透明度
							strokeWeight: 1, //线粗细度
							fillColor: "rgba(244, 35, 0, 0.34)", //填充颜色
							fillOpacity: 0.35//填充透明度
						});
						circle.setMap(map);


						var sjkbccl = wzclslxx[0].VEHI_NO
						console.log(sjkbccl)
						var bwkclsz = [];
						if(sjkbccl!=null){
							var bcclsz=sjkbccl.split(',');
							for(var i=0;i<bcclsz.length;i++){
								if(bcclsz[i]!=null&&bcclsz[i].length>0){
									bwkclsz.push(bcclsz[i]);
									var li4="<li><span></span><i>"+bcclsz[i]+"</i><a class='selected'></a></li>";
									dojo.query('#ditial_car ul')[0].innerHTML+=li4;
								}
							}
						}

						for(var i = 0; i<qbcl.length; i++){
							if(circle.contains([qbcl[i].longi, qbcl[i].lati])){
								bwkclsz.push(qbcl[i])
								markercar(qbcl[i]);
								var li4="<li><span></span><i>"+qbcl[i].vehino+"</i><a class=''></a></li>";
								dojo.query('#ditial_car ul')[0].innerHTML+=li4;
							}


						}
						//搜索框
						dojo.connect(dojo.byId('sousuocl'),'keyup',this,function(){
							if(dojo.byId('sousuocl').value != ''){
								dojo.byId('clss').style.display= 'block';
								var clssinput=dojo.byId('sousuocl').value;
								var xhrArgsclss = {
									url : basePath+"yjzh/findclsj?info="+clssinput,
									handleAs : "json",
									load : function(data) {
										clxqclss = data;
										for(var i=0;i<clxqclss.length;i++) {
											var li = "<li><span></span><i>" + clxqclss[i].VEHI_NO + "</i></li>";
											dojo.query('#clss ul')[0].innerHTML += li;
										}
										dojo.query('#clss li').forEach(function(item,index){
											dojo.connect(item, "click", function(){
												markervhic(clxqclss[index],1);
											});
										});
										var clssli=dojo.query('#ditial_car ul')[0].children;
										console.log(clssli.length)
										dojo.query('#clss li').forEach(function(item,index){
											dojo.connect(item, "dblclick", function(){
												dojo.byId('clss').style.display='none';
												var li="<li><span></span><i>"+clxqclss[index].VEHI_NO+"</i><a class='selected'></a></li>";
												dojo.query('#ditial_car ul')[0].innerHTML+=li;
												//map.clearMap();
												//console.log(clssli.length)
												//for(var i=0;i<=clssli.length;i++){
												//	for(var j=0;j<qyjk.length;j++){
												//		if(clssli[i].children[1].innerHTML== qyjk[j].vehino){
												//			markercar(qyjk[j]);
												//		}
												//	}
												//}
											});
										});
									}
								}
								dojo.xhrPost(xhrArgsclss);
							}else{
								dojo.byId('clss').style.display= 'none';
								dojo.query('#clss ul')[0].innerHTML='';
							}
						});

						//选择车辆列表
						dojo.query('#ditial_car li').forEach(function(item,index){
							dojo.connect(item, "click", function(){
								dojo.byId('check_new_car').style.display = 'block';
								dojo.byId('check_new_event').style.display = 'none';
								dojo.byId('searchbox_tool').style.display = 'none';
								dojo.byId('sou_car').style.display = 'none';
								dojo.query("#check_new_car ul")[0].innerHTML='';
								num=8;

								for(var i = 0; i<qbcl.length; i++){
									if(qbcl[i].vehino==item.children[1].innerHTML){
										var li="<li>车牌号 : <span>"+qbcl[i].vehino+"</span></li>"+
											"<li>所属公司 : <span>"+qbcl[i].compname+"</span></li>"+
											"<li>司机 : <span>"+qbcl[i].ownname+"</span></li>"+
											"<li>电话 : <span>"+qbcl[i].owntel+"</span></li>";
										dojo.query("#check_new_car ul")[0].innerHTML+=li;
										markervhic(qbcl[i],2);
									}
								}
							});
						});

						//相关车辆勾选
						on(dojo.query('#ditial_car li'), "dblclick", function(){
							var dex= dojo.query(this)[0];
							if(dex.children[2].className ==''){
								dex.children[2].className = 'selected';
							}else {
								dex.children[2].className = '';
							}
						});

						//保存信息
						on(dojo.byId('bcxx'),'click',function(){
							dojo.byId('check_new_event').style.display = 'block';
							dojo.byId('check_new_car').style.display = 'none';
							var cphm="";
							var car=dojo.query('#ditial_car li');
							var count=dojo.query('[class^=selected]').length;
							//console.log(dojo.query('[class^=selected]'))
							for(var i=0;i<count;i++){
								if(dojo.query('[class^=selected]')[i].parentNode.children[2].className == 'selected') {
									var zxcl = dojo.query('[class^=selected]')[i].parentNode.children[1].innerHTML;
									cphm += zxcl + ",";
								}
							}
							console.log(cphm)
							var xhrArgsclsave = {
								url : basePath+"yjzh/yjsjclsave?postData={'sjbh':'"+wzclslxx[0].SJBH+"','cphm':'"+cphm+"'}",
								handleAs : "json",
								load : function(data) {
								}
							};
							dojo.xhrPost(xhrArgsclsave);
							num=5;
						});
					});


				}
			});


			var xhrArgsqbcl = {
				url : basePath+"clqk/qyjk",
				handleAs : "json",
				load : function(data) {
					qbcl=data.vehilist;
				}
			};
			dojo.xhrPost(xhrArgsqbcl);


			//显示与隐藏
			//控件
			var toggleName=['tool-more','searchbox_tool','check_new_event','sou_event','sou_movie','sou_car','sou_rect','back','check_new_car','clss','check_new_event','ditial_car','check_new_movie','sou_fanwei'];

			//视频详情
			dojo.connect(dojo.byId('check_movie'),'click',this,function(){
				toggleElement('check_new_movie',toggleName);
				dojo.byId('back').style.display = 'block';
				num=6;
			});



			//搜索框

			dojo.connect(dojo.byId('searchbox_container'),'click',this,function(){
				toggleElement('searchbox_tool',toggleName);

			});


			//返回
			var num=null;
			dojo.connect(dojo.byId('back'),'click',this,function(){
				if(num==1){
					toggleElement('searchbox_tool',toggleName);
					dojo.byId('sou_event').style.top = '50px';
					dojo.byId('sou_car').style.top = '50px';
					dojo.query('#sou_car ul')[0].innerHTML='';
					dojo.query('#sou_event ul')[0].innerHTML='';
				}
				if(num==2){
					toggleElement('sou_event',toggleName);
					dojo.byId('sou_event').style.top = '50px';
					dojo.byId('back').style.display = 'block';
					num=1;
					dojo.query('#check_new_event ul')[0].innerHTML='';
					dojo.query('#ditial_car ul')[0].innerHTML='';
					dojo.query('#ditial2')[0].innerHTML='';
					map.clearMap();
				}
				if(num==4){
					toggleElement('sou_car',toggleName);
					dojo.byId('sou_car').style.top = '50px';
					dojo.byId('back').style.display = 'block';
					num=1;
				}
				if(num==3){
					toggleElement('check_new_event',toggleName);
					dojo.byId('back').style.display = 'block';
					dojo.byId('sou_car').style.top = '50px';
					dojo.byId('sou_event').style.top = '50px';
					dojo.byId('ditial_car').style.display = 'block';
					num=2;
				}
				if(num==5){
					toggleElement('sou_event',toggleName);
					dojo.byId('sou_event').style.top = '50px';
					dojo.byId('back').style.display = 'block';
					num=1;
				}
				if(num==6){
					toggleElement('sou_movie',toggleName);
					dojo.byId('sou_movie').style.top = '50px';
					dojo.byId('back').style.display = 'block';
					num=1;
				}
				if(num==7){
					toggleElement('searchbox_tool',toggleName);
					dojo.byId('sou_event').style.top = '50px';
					dojo.byId('sou_car').style.top = '50px';
					dojo.query('#sou_car ul')[0].innerHTML='';
					dojo.query('#sou_event ul')[0].innerHTML='';
					dojo.query('#check_new_event ul li').remove();
					dojo.query('#ditial_car ul li').remove();
					dojo.query("#check_new_car ul li").remove();
					map.clearMap();
				}
				if(num==8){
					toggleElement('check_new_event',toggleName);
					dojo.byId('back').style.display = 'block';
					dojo.byId('sou_car').style.top = '50px';
					dojo.byId('sou_event').style.top = '50px';
					dojo.byId('ditial_car').style.display = 'block';
					map.clearMap();
					num=1;
				}
			});

			//3大搜索
			dojo.connect(dojo.byId('search_event'),'click',this,function(){
				toggleElement('sou_event',toggleName);
				dojo.byId('back').style.display = 'block';
				dojo.byId('sou_event').style.top = '50px';
				num=1;
				findevent();
			});
			dojo.connect(dojo.byId('search_car'),'click',this,function(){
				toggleElement('sou_car',toggleName);
				dojo.byId('back').style.display = 'block';
				dojo.byId('sou_car').style.top = '50px';
				num=1;
				findcar();
			});
			dojo.connect(dojo.byId('search_movie'),'click',this,function(){
				toggleElement('sou_movie',toggleName);
				dojo.byId('back').style.display = 'block';
				num=1;
				findmovie();
			});


			//地图
			dojo.connect(dojo.byId('luxian'),'click',this,function(){
				dojo.query(toggleName)[0].forEach(function (item,index) {
					dojo.byId(item).style.display='none';
				});
			});

			//输入值开始搜索
			dojo.connect(dojo.byId('content_input'),'keyup',this,function(){
				toggleKey('input-clear');
				dojo.byId('searchbox_tool').style.display = 'none';
				if(dojo.byId('content_input'=='')){
					dojo.byId('sou_rect').style.display = 'none';
					dojo.byId('input-clear').style.display = 'none';
				}
			});
			//清除
			dojo.connect(dojo.byId('input-clear'),'click',this,function(){
				dojo.byId('content_input').value = "";
				dojo.byId('sou_rect').style.display = 'none';
				dojo.byId('input-clear').style.display = 'none';
			});

			//全部车辆列表
			dojo.query('#sou_car li').forEach(function(item,index){
				dojo.connect(item, "click", function(){
					//toggleElement('check_new_car',toggleName);
					//dojo.byId('back').style.display = 'block';
					num=1;
				});
			});


			//显示与隐藏方法
			function toggleElement(id,toggleName){
				toggleHidden(toggleName);
				var element = dojo.byId(id);
				element.style.display != 'none'?element.style.display = 'none':element.style.display = 'block';
			}
			function toggleHidden(toggleName){
				for(var i=0;i<toggleName.length;i++){
					var element = dojo.byId(toggleName[i]);
					var isVisible = element.style.display != 'none';
					if(isVisible) element.style.display = 'none';
				}
			}
			//清除方法
			var ccc;
			function toggleKey(id){
				var element = dojo.byId(id);
				var isVisible = dojo.byId('content_input').value != "";
				dojo.query('.amap-sug-result').style.display='none';
				var bb=dojo.byId('content_input').value;
				if(isVisible){
					element.style.display = 'block';
					if(bb.length>2){
						dojo.byId('sou_rect').style.display = 'block';
						var xhrArgs = {
							url : basePath+"yjzh/findclsj?info="+bb,
							handleAs : "json",
							load : function(data) {
								ccc=data;
								var li;
								for(var i=0;i<ccc.length;i++){
									li="<li><span></span><i>"+ccc[i].COMP_NAME +"</i><a>"+ccc[i].VEHI_NO +"</a></li>";
									dojo.query('#sou_rect ul')[0].innerHTML+=li;
								}

								//搜索框下拉点击
								dojo.query('#sou_rect li').forEach(function(item,index){
									dojo.connect(item, "click", function(){
										markervhic(ccc[index],1);
									});
								});
								//if(data.length<=0){
								//	//输入提示
								//	dojo.query('.amap-sug-result').style.display='block';
								//	dojo.byId('sou_rect').style.display = 'none';
								//	var autoOptions = {
								//		input: "content_input"
								//	};
								//	var auto = new AMap.Autocomplete(autoOptions);
								//	var placeSearch = new AMap.PlaceSearch({
								//		map: map
								//	});  //构造地点查询类
								//	AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
								//	function select(e) {
								//		placeSearch.setCity(e.poi.adcode);
								//		placeSearch.search(e.poi.name);  //关键字查询查询
								//	}
								//}
							}
						};
						dojo.xhrPost(xhrArgs);
					}else{
						dojo.query('#sou_rect ul li').remove();
					}
				}else{
					element.style.display = 'none';
					dojo.byId('sou_rect').style.display = 'none';
				}
			}


			//导航栏图标
			//轨迹
			dojo.connect(dojo.byId('gj'),'click',this,function(){
				toggleBackground1('gj1');
				findvhiclsgj();
			});

			//路况
			var lkpd = false;
			AMap.event.addDomListener(document.getElementById('lk'), 'click', function() {
				toggleBackground1('lk1');
				if (lkpd) {
					trafficLayer.hide();
					lkpd = false;
				} else {
					trafficLayer.show();
					lkpd = true;
				}
			}, false);
			var trafficLayer;
			trafficLayer = new AMap.TileLayer.Traffic({
				zIndex: 10
			});
			trafficLayer.setMap(map);
			trafficLayer.hide();

			//卫星图
			var wxtpd = false;
			AMap.event.addDomListener(document.getElementById('wxt'), 'click', function() {
				toggleBackground1('wxt1');
				if (wxtpd) {
					layers.hide();
					wxtpd = false;
				} else {
					layers.show();
					wxtpd = true;
				}
			}, false);
			var layers=new AMap.TileLayer.Satellite();
			layers.setMap(map);
			layers.hide();

			//列表
			dojo.connect(dojo.byId('lb'),'click',this,function(){
				toggleBackground1('lb1');
				if(dojo.byId('searchbox').style.display=='none'){
					dojo.byId('searchbox').style.display='block';
				} else {
					dojo.query(toggleName)[0].forEach(function (item,index) {
						dojo.byId(item).style.display='none';
					});
					dojo.byId('searchbox').style.display='none';
				}
			});

			//更多
			dojo.connect(dojo.byId('gd'),'click',this,function(){
				toggleBackground1('gd1');
				map.clearMap();
				if(dojo.byId('tool-more').style.display!='none'){
					dojo.byId('tool-more').style.display='none';
				}else{
					toggleElement('tool-more',toggleName);
				}
			});
			//测距
			dojo.connect(dojo.byId('cj'),'click',this,function(){
				toggleBackground1('cj1');
				startRuler1();
			});
			function startRuler1() {
				ruler2.turnOff();
				ruler1.turnOn();
			}
			//地图

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


			//范围
			var fangkuang=true;
			var fxudmouseTool=null;
			var polygonfk;
			var udpolygonOption = {
				strokeColor:"",
				strokeOpacity:0,
				strokeWeight:1
			};
			dojo.connect(dojo.byId('fw'),'click',this,function(){
				toggleBackground1('fw1');
				if(fangkuang){
					fangkuang=false;
					if(fxudmouseTool!=null){
						alert("鼠标在地图上点击绘制多边形，单击右键或者双击左键结束绘制");
					}else{
						if(polygonfk!=null){
							polygonfk.setMap();
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
								dojo.byId('sou_fanwei').style.display='block';
								dojo.byId('back').style.display='block';
								var vhiccount = "";
								for(var j=0; j<qyjk.length; j++) {
									if (polygonfk.contains([qyjk[j].longi, qyjk[j].lati])) {
										vhiccount++;
										var li="<li><div class='col2-l'><span></span><i>"+qyjk[j].compname.substr(0,14)+"</i><a>"+qyjk[j].vehino+"</a></div></a></li>";
										dojo.query('#sou_fanwei ul')[0].innerHTML+=li;
									}
								}
								num=1;
								fxudmouseTool.close(true);
								fxudmouseTool=null;

								for(var i=0;i<qyjk.length;i++){
									if(polygonfk.contains([qyjk[i].longi,qyjk[i].lati])){
										markercar(qyjk[i])
									}
								}
								dojo.query('#sou_fanwei ul li').forEach(function (item,index) {
									dojo.connect(item,'click', function () {
										for(var i=0;i<qyjk.length;i++){
											if(item.children[0].children[2].innerHTML == qyjk[i].vehino){
												markercar(qyjk[i])
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
				}
			});


			//导航栏图标方法
			var aa=1;
			function toggleBackground1(id){
				var element = dojo.byId(id);
				if(aa==1){
					element.style.backgroundImage= "url('resources/images/index/"+id+".png')";
					element.nextSibling.style.color="rgb(12,136,232)";
					aa=0;
				}else{
					element.style.backgroundImage= "url('resources/images/index/"+id+"2.png')";
					aa=1;
					element.nextSibling.style.color="rgb(0,0,0)";
				}
			}


			//marker 1

			function markervhic(vehicle,xxx){
				if(marker!=""){
					marker.setMap(null);
				}
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
				if(xxx==1){
					marker = new AMap.Marker({
						map:map,
						position:new AMap.LngLat(vehicle.PX,vehicle.PY),
						offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
						draggable:false,  //是否可拖动
						content:markerContent   //自定义点标记覆盖物内容
					});
					map.setCenter(new AMap.LngLat(vehicle.PX,vehicle.PY));
					var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.VEHI_NO+"</b></td></tr><tr><td><b>[所属公司]</b>："+vehicle.COMP_NAME+"</tr></td><tr><td><b>[SIM卡]</b>："+vehicle.VEHI_SIM+"</tr></td><tr><td><b>[车辆所属人]</b>："+vehicle.OWN_NAME
						+"</tr></td><tr><td><b>[联系电话]</b>："+vehicle.OWN_TEL+"</tr></td><tr><td><b>[经度]</b>："+vehicle.PX+"</tr></td><tr><td><b>[纬度]</b>："+vehicle.PY;
				}
				if(xxx==2){
					marker = new AMap.Marker({
						map:map,
						position:new AMap.LngLat(vehicle.longi,vehicle.lati),
						offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
						draggable:false,  //是否可拖动
						content:markerContent   //自定义点标记覆盖物内容
					});
					map.setCenter(new AMap.LngLat(vehicle.longi,vehicle.lati));
					var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.vehino+"</b></td></tr><tr><td><b>[所属公司]</b>："+vehicle.compname+"</tr></td><tr><td><b>[SIM卡]</b>："+vehicle.vehisim+"</tr></td><tr><td><b>[车辆所属人]</b>："+vehicle.ownname
						+"</tr></td><tr><td><b>[联系电话]</b>："+vehicle.owntel+"</tr></td><tr><td><b>[经度]</b>："+vehicle.longi+"</tr></td><tr><td><b>[纬度]</b>："+vehicle.lati;
				}
				if(xxx==3){
					marker = new AMap.Marker({
						map:map,
						position:new AMap.LngLat(vehicle.PX,vehicle.PY),
						offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
						draggable:false,  //是否可拖动
						content:markerContent   //自定义点标记覆盖物内容
					});
					map.setCenter(new AMap.LngLat(vehicle.PX,vehicle.PY));
					var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.SJZT+"</b></td></tr><tr><td><b>[地点]</b>："+vehicle.ADDRESS;
				}
				if(xxx==4){
					marker = new AMap.Marker({
						map:map,
						position:new AMap.LngLat(120.153576,30.287459),
						offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
						draggable:false,  //是否可拖动
						content:markerContent   //自定义点标记覆盖物内容
					});
					map.setCenter(new AMap.LngLat(120.153576,30.287459));
					var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.SJZT+"</b></td></tr><tr><td><b>[地点]</b>："+vehicle.ADDRESS;
				}
				var info = [];
				info.push(txt);
				var inforWindowone = new AMap.InfoWindow({
					offset:new AMap.Pixel(5,10),
					content:info.join("</tr></td></table>")
				});
				inforWindowone.open(map,marker.getPosition());

				AMap.event.addListener(marker,"click",function(e){
					inforWindowone.open(map,this.getPosition());
				});
			}

			function markercar(vehicle){
				if(marker!=""){
					marker.setMap(null);
				}
				if(vehicle.PX =='' || vehicle.PY==''){
					vehicle.PX=vehicle.PY=0;
				}
				var markerContent = document.createElement("div");
				markerContent.className = "txtstyle";
				//点标记中的图标
				var markerImg= document.createElement("img");
				markerImg.className="markerlnglat";
				if(vehicle.gpsStatus=="0"){
					markerImg.src="resources/images/h.png";
				}else if(vehicle.gpsStatus=="1"){
					markerImg.src="resources/images/c.png";
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
					var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.vehino+"</b></td></tr><tr><td><b>[所属公司]</b>："+vehicle.compname+"</tr></td><tr><td><b>[SIM卡]</b>："+vehicle.vehisim+"</tr></td><tr><td><b>[车辆所属人]</b>："+vehicle.ownname
						+"</tr></td><tr><td><b>[联系电话]</b>："+vehicle.owntel+"</tr></td><tr><td><b>[经度]</b>："+vehicle.longi+"</tr></td><tr><td><b>[纬度]</b>："+vehicle.lati;
					var info = [];
					info.push(txt);
					inforWindowone1 = new AMap.InfoWindow({
						offset:new AMap.Pixel(5,10),
						content:info.join("</tr></td></table>")
					});
				});
			}



			//事件查询
			var strArray=null;
			function findevent(){
				var xhrArgs = {
					url : basePath+"yjzh/findjtsj ",
					handleAs : "json",
					load : function(data) {
						sjclqyjk=data;
						dojo.query("#back span")[0].innerHTML='共计'+sjclqyjk.length+'条记录';
						for(var i=0;i<sjclqyjk.length;i++){
							var li="<li>"+
								"<div class='col1'>"+
								"<div class='col1-l'><a></a></div>"+
								"<div class='col1-r'>"+
								"<div class='col1-r-t'><a></a></div>"+
								"<div class='col1-img'><a></a></div>"+
								"</div>"+
								"<div class='col-center'>"+
								"<div class='row1'>事件名称 : <span>"+sjclqyjk[i].SJZT+"</span></div></br>"+
								"<div class='row'>事件地点 : <span>"+sjclqyjk[i].ADDRESS+"</span></div>"+
								"<div class='row'>事件时间 : <span></span>"+util.formatYYYYMMDDHHMISS(sjclqyjk[i].TIME)+"</div>"+
								"<div id='check_event"+i+"'>查看详情</div>"+
								"</div>"+
								"</div>"+
								"</li>";
							dojo.query('#sou_event ul')[0].innerHTML+=li;
						}
						dojo.query('#sou_event li').forEach(function(item,index){
							dojo.connect(item, "click", function(){
								markervhic(sjclqyjk[index],3);
							});
						});
						//事件详情
						on(dojo.query('[id^=check_event]'),'click', function () {
							var strr=this.id.substr(-1,1);
							toggleElement('check_new_event',toggleName);
							dojo.byId('back').style.display = 'block';
							dojo.byId('ditial_car').style.display = 'block';
							dojo.query('#bcxx a').remove();
							dojo.query('#ditial_car li').remove();
							dojo.query('#ditial2 span').remove();
							dojo.query('#ditial2 a').remove();
							dojo.byId('ditial2').innerHTML='';
							dojo.query('#check_new_event li').remove();
							num=2;
							//console.log(sjclqyjk[strr])
							var li1="<li>事件名称 : <span>"+sjclqyjk[strr].SJZT+"</span></li>"+
								"<li>事件时间 : <span>"+util.formatYYYYMMDDHHMISS(sjclqyjk[strr].TIME)+"</span></li>"+
								"<li>时间地点 : <span>"+sjclqyjk[strr].ADDRESS+"</span></li>"+
								"<li>事件内容 : <span>"+sjclqyjk[strr].SJNR+"</span></li>"+
								"<li>事件相关车辆数量 : <span>"+(sjclqyjk[strr].VEHI_NO.split(",")).length+"</span></li>";
							dojo.query('#check_new_event ul')[0].innerHTML+=li1;

							//相关车辆
							strArray=sjclqyjk[strr].VEHI_NO.split(",");
							var li3="<span></span>有关车辆:";
							dojo.query('#ditial2')[0].innerHTML+=li3;
							for(var i=0;i<strArray.length;i++){
								var li2="<li><span></span><i>"+strArray[i]+"</i></li>";
								dojo.query('#ditial_car ul')[0].innerHTML+=li2;
							}


							//选择车辆列表
							dojo.query('#ditial_car li').forEach(function(item,index){
								dojo.connect(item, "click", function(){
									dojo.byId('check_new_car').style.display = 'block';
									dojo.byId('check_new_event').style.display = 'none';
									dojo.byId('searchbox_tool').style.display = 'none';
									dojo.byId('sou_car').style.display = 'none';
									num=3;
									//console.log((sjclqyjk[0].VEHI_NO.split(","))[index]);
									if(dojo.query("#check_new_car ul")[0].innerHTML!=''){
										dojo.query("#check_new_car ul")[0].innerHTML='';
										var li="<li>车牌号 : <span>"+(sjclqyjk[0].VEHI_NO.split(","))[index]+"</span></li>"+
											"<li>所属公司 : <span>"+(sjclqyjk[0].COMP_NAME.split(","))[index]+"</span></li>"+
											"<li>司机 : <span>"+(sjclqyjk[0].OWN_NAME.split(","))[index]+"</span></li>"+
											"<li>电话 : <span>"+(sjclqyjk[0].OWN_TEL.split(","))[index]+"</span></li>";
										dojo.query("#check_new_car ul")[0].innerHTML+=li;
									}
								});
							});
						})
					}
				};
				dojo.xhrPost(xhrArgs);
			}
			var xhrArgs = {
				url : basePath+"clqk/qyjk",
				handleAs : "json",
				load : function(data) {
					qyjk=data.vehilist;
				}
			};
			dojo.xhrPost(xhrArgs);

			//车辆查询
			function findcar(){

				dojo.query('#back span')[0].innerHTML='共计'+qyjk.length+'条记录';
				var li;
				for(var i=0;i<100;i++){
					var c1=qyjk[i].compname.substr(0,14);
					li="<li><div class='col2-l'><span></span><i>"+c1+"</i><a>"+qyjk[i].vehino+"</a></div></a></li>";
					dojo.query('#sou_car ul')[0].innerHTML+=li;
				}

				//搜索框下拉点击
				dojo.query('#sou_car li').forEach(function(item,index){
					dojo.connect(item, "click", function(){
						markervhic(qyjk[index],2);
					});
				});
			}

			function findmovie(){
				dojo.query("#back span")[0].innerHTML='共计'+0+'条记录';
			}

		});


function findvhiclsgj(){
	require(["dijit/Dialog", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/FilteringSelect"
		,"dijit/form/CheckBox"
		,"dijit/form/TimeTextBox","dijit/form/SimpleTextarea"
		,"dgrid/OnDemandGrid","dijit/form/TextBox","dgrid/extensions/Pagination"
		,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
		,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
		,"dijit/registry", "dojo/dom-style","dojo/_base/declare"
		,"cbtree/Tree","cbtree/models/ForestStoreModel","dojo/data/ItemFileWriteStore"
		,"dojo/dom-construct","dojo/on","app/util","dojo/domReady!"], function(
		Dialog,Editor, Button,DateTextBox,FilteringSelect,CheckBox,TimeTextBox,SimpleTextarea,Grid,TextBox
		, Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
		,registry, domStyle,declare
		,Tree, ForestStoreModel,ItemFileWriteStore
		, dc,on,util ) {
			lsgjDialog1.show().then(function(){
				dijit.byId("lsgj_stime1").setValue(setformat(new Date((new Date()).getTime() - 1000 * 60 * 60*1)));
				dijit.byId("lsgj_etime1").setValue(setformat(new Date()));
				lsgjmap1 = new AMap.Map('lsgjmapdiv1', {
					animateEnable:false,
					jogEnable:false,
					view: new AMap.View2D({
						center: new AMap.LngLat(120.153576,30.287459),
						zoom: 15
					})
				});
//				queryVhic().then(function(data){
////				console.dir("#####"+data.datas)
//					dijit.byId("lsgj_vhic1").set('store',new dojo.store.Memory({ data: data.datas}));
//				})
//				dijit.byId("lsgj_vhic1").set('queryExpr', "*${0}*"  )

				dojo.connect(dojo.byId('lsgj_vhic1'),'keyup', function () {
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
								gjcl = data;
								var li;
								for (var i = 0; i < gjcl.length; i++) {
									li = "<li><span></span><i>" + gjcl[i].COMP_NAME + "</i><a>" + gjcl[i].VEHI_NO + "</a></li>";
									dojo.query('#sou_rect1 ul')[0].innerHTML += li;
								}
								dojo.query('#sou_rect1 li').forEach(function(item,index){
									dojo.connect(item, "click", function(){
										dojo.byId('lsgj_vhic1').value=gjcl[index].VEHI_NO;
										console.log(gjcl[index].VEHI_NO)
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
}


function findlsgj1(){
	require([ "app/createSyncStore"], function(createSyncStore) {
		dijit.byId("findlsgj1").showWait();
		dojo.query('#sou_rect1 li').remove();
		var xhrArgs = {
			url : basePath + "clqk/findGj",
			postData : 'postData={"sTime":"'+dijit.byId("lsgj_stime1").value+'","eTime":"'+dijit.byId("lsgj_etime1").value+'","vehino":"'+dojo.byId("lsgj_vhic1").value+'"}',
			handleAs : "json",
			load : function(data) {
				dijit.byId("findlsgj1").hideWait();
				if(data.length==0){
					alert("当前时段没有历史轨迹");
					dojo.byId('sou_rect1').style.display='none';
				}else{
					for (var i = 0; i < data.length; i++) {
						if(i==0){
							addmkshisstart1(data[i]);
						}else if(i==data.length-1){
							addmkshisend1(data[i]);
						}else{
							if(data[i-1].PX!=data[i].PX){
								addmkshis1(data[i]);
							}
						}
//						markerMovingControl = new MarkerMovingControl(lsgjmap1, markerhistory, lineArr);
						completeEventHandler1(data);
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
function addmkshisstart1(obj){
	lsgjmap1.clearMap();
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

	var txt = "<b style='color:#3399FF'>"+obj.VEHICLE_NUM+"(起点)</b><br/><br/><b>[GPS时间]</b>："+obj.SPEED_TIME
		+"<br/><b>[车辆状态]</b>："+state
		+"<br/><b>[GPS速度]</b>："+obj.SPEED
		+"<br/><b>[行驶方向]</b>："+dlwz(obj.DIRECTION)
		+"<br/><b>[经纬度]</b>："+obj.PX+","+obj.PY
//				  +"<br/><b>[位置描述]</b>："+obj.ADDRESS;
	var marker1 = new AMap.Marker({
		map:lsgjmap1,
		position:new AMap.LngLat(obj.PX,obj.PY),
		zIndex:102,
		offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
		draggable:false,  //是否可拖动
		content:markerContent   //自定义点标记覆盖物内容
	});
	//$("#img"+obj.messageid).rotate(obj.direction-90);
	marker1.setMap(lsgjmap1);  //在地图上添加点

	//添加文本标记
	var info = [];
	info.push(txt);

	var inforWindow = new AMap.InfoWindow({
		offset:new AMap.Pixel(0,0),
		content:info.join("<br>")
	});
	AMap.event.addListener(marker1,"click",function(e){
		inforWindow.open(lsgjmap1,marker1.getPosition());
	});
}
//添加终点
function addmkshisend1(obj){
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
	var txt = "<b style='color:#3399FF'>"+obj.VEHICLE_NUM+"(起点)</b><br/><br/><b>[GPS时间]</b>："+obj.SPEED_TIME
		+"<br/><b>[车辆状态]</b>："+state
		+"<br/><b>[GPS速度]</b>："+obj.SPEED
		+"<br/><b>[行驶方向]</b>："+dlwz(obj.DIRECTION)
		+"<br/><b>[经纬度]</b>："+obj.PX+","+obj.PY;
//				  +"<br/><b>[位置描述]</b>："+obj.address;
	var marker1 = new AMap.Marker({
		map:lsgjmap1,
		position:new AMap.LngLat(obj.PX,obj.PY),
		zIndex:102,
		offset:new AMap.Pixel(-24,-28), //相对于基点的偏移位置
		draggable:false,  //是否可拖动
		content:markerContent   //自定义点标记覆盖物内容
	});
	//$("#img"+obj.messageid).rotate(obj.direction-90);
	marker1.setMap(lsgjmap1);  //在地图上添加点

	//添加文本标记
	var info = [];
	info.push(txt);

	var inforWindow = new AMap.InfoWindow({
		offset:new AMap.Pixel(0,0),
		content:info.join("<br>")
	});
	AMap.event.addListener(marker1,"click",function(e){
		inforWindow.open(lsgjmap1,marker1.getPosition());
	});

	//添加角度
	//markerlist.push(marker1);
}


//添加所有点
function addmkshis1(obj){
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
	var txt = "<b style='color:#3399FF'>"+obj.VEHICLE_NUM+"</b><br/><br/><b>[GPS时间]</b>："+obj.SPEED_TIME
		+"<br/><b>[车辆状态]</b>："+state
		+"<br/><b>[GPS速度]</b>："+obj.SPEED
		+"<br/><b>[行驶方向]</b>："+dlwz(obj.DIRECTION)
		+"<br/><b>[经纬度]</b>："+obj.PX+","+obj.PY
//				  +"<br/><b>[位置描述]</b>："+obj.address;

	var marker1 =null;
	if(obj.STATE==0){
		console.log(obj.DIRECTION)
		var marker1 = new AMap.Marker({
			//map:lsgjmap1,
			position:new AMap.LngLat(obj.PX,obj.PY),
			offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
			draggable:false,  //是否可拖动
			icon:"resources/images/fx.jpg",
			//  content:markerContent,   //自定义点标记覆盖物内容
			angle:obj.DIRECTION-90
		});
	}else if(obj.STATE==1){
		var marker1 = new AMap.Marker({
			//map:lsgjmap1,
			position:new AMap.LngLat(obj.PX,obj.PY),
			offset:new AMap.Pixel(-14,-7), //相对于基点的偏移位置
			draggable:false,  //是否可拖动
			icon:"resources/images/fx2.png",
			//  content:markerContent,   //自定义点标记覆盖物内容
			angle:obj.DIRECTION-90
		});
	}
	//$("#img"+obj.messageid).rotate(obj.direction-90);
	marker1.setMap(lsgjmap1);  //在地图上添加点

	//添加文本标记
	var info = [];
	info.push(txt);

	var inforWindow = new AMap.InfoWindow({
		offset:new AMap.Pixel(0,0),
		content:info.join("<br>")
	});
	AMap.event.addListener(marker1,"click",function(e){
		inforWindow.open(lsgjmap1,marker1.getPosition());
	});

	//添加角度
	//markerlist.push(marker1);
}

//添加轨迹
function completeEventHandler1(vehigps) {
	if (vehigps[0].STATE == 0) {
		markerhistory = new AMap.Marker({
			map: lsgjmap1,
			//draggable:true, //是否可拖动
			position: new AMap.LngLat(vehigps[0].PX, vehigps[0].PY),//基点位置
			icon: "resources/images/car2.png", //marker图标，直接传递地址url
			zIndex: 101,
			offset: new AMap.Pixel(-26, -14), //相对于基点的位置
			angle: vehigps[0].DIRECTION - 90,
			autoRotation: true
		});
	} else if (vehigps[0].STATE == 1) {
		markerhistory = new AMap.Marker({
			map: lsgjmap1,
			//draggable:true, //是否可拖动
			position: new AMap.LngLat(vehigps[0].PX, vehigps[0].PY),//基点位置
			//icon: "resources/images/car.png", //marker图标，直接传递地址url
			zIndex: 101,
			offset: new AMap.Pixel(-26, -14), //相对于基点的位置
			angle: vehigps[0].DIRECTION - 90,
			autoRotation: true
		});
	}
	lineArr = new Array();
	for (var i = 0; i < vehigps.length; i++) {
		var lngX = vehigps[i].PX;
		var lngY = vehigps[i].PY;
		if (i > 0) {
			var longi0 = vehigps[i - 1].PX;
			var lati0 = vehigps[i - 1].PY;
			if (lngX != longi0 || lngY != lati0) {
				lineArr.push(new AMap.LngLat(lngX, lngY));
			} else {
				//  alert(lngY+"   "+(parseFloat(vehigps[i].lati)+0.00001));
				lngY = parseFloat(vehigps[i].PY) + 0.00001 * i;
				lineArr.push(new AMap.LngLat(lngX, lngY));
			}
		} else {
			lineArr.push(new AMap.LngLat(lngX, lngY));
		}
	}
	//绘制轨迹
	polyline = new AMap.Polyline({
		map: lsgjmap1,
		path: lineArr,
		strokeColor: "#00A",//线颜色
		strokeOpacity: 1,//线透明度
		strokeWeight: 3,//线宽
		strokeStyle: "dashed"//线样式
	});
	lsgjmap1.setFitView();
	//alert(lineArr.length+"   "+vehigps.length);
	// var linea=0;
	AMap.event.addListener(markerhistory, 'moving', function (e) {
		for (var i = 0; i < lineArr.length; i++) {
			var l = lineArr[i];
			if (markerhistory.getPosition().distance(l) <= 2) {

				$('#vehigpsdata tr').each(function () {
					$(this).css('color', 'black');
				});
				$("#his" + vehigps[i].MESSAGE_ID).css('color', 'red');
				if (vehigps[i].STATE == 0) {
					markerhistory.setIcon("resources/images/car2.png");
				} else if (vehigps[i].STATE == 1) {
					markerhistory.setIcon("resources/images/car.png");
				}
				// linea = i+1;
				break;
			} else {

			}
		}
	});
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

function hqch(){
	console.log(111)
	queryVhic().then(function(data){
		dijit.byId("lsgj_vhic1").set('store',new dojo.store.Memory({ data: data.datas}));
	})
}