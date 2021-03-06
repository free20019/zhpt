var yjycclcxGrid = null;
var zLC=0;
require([ "app/util", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select","dijit/form/Textarea","dijit/form/ComboBox"
          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
          ,"app/createSyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
          "dojo/dom-construct", "dojo/domReady!"], function(util,
        		  Editor, Button,DateTextBox,Select,Textarea,FilteringSelect,Grid,TextBox
        		  , Pagination,Selection,Keyboard,ColumnResizer,createSyncStore,Memory,DijitRegistry
        		  ,registry, domStyle,declare, dc ) {
	//加载查询条件按钮
	dc.place(dc.create("span",{innerHTML:"起止时间",style:{"margin-right":"5px"}}),'yjycclcxdiv');
	var yjycclcx_stime= new TextBox({id:"yjycclcx_stime",value:setformat(new Date((new Date()).getTime() - 1000 * 60 * 60*2)),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('yjycclcxdiv');
	dc.place(dc.create("span",{innerHTML:"至",style:{"margin-right":"5px"}}),'yjycclcxdiv');
	var yjycclcx_etime= new TextBox({id:"yjycclcx_etime",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('yjycclcxdiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'yjycclcxdiv');
	var yjycclcx_vhic= new TextBox({id:"yjycclcx_vhic",style:{"width":"8em","margin-right":"15px"}}).placeAt('yjycclcxdiv');
	
	var queryCondition = {"yjyc_stime":yjycclcx_stime,"yjyc_etime":yjycclcx_etime,"yjyc_vhic":yjycclcx_vhic};
	var yjycclcxbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        		if(o == "yjyc_stime" || o == "yjyc_etime" ){
	        			console.log(dojo.byId(queryCondition[o].id).value,queryCondition[o].id,o)
	        			postData[o] = dojo.byId(queryCondition[o].id).value;
	        		}else{
	        			console.log(dojo.byId(queryCondition[o].id).value,queryCondition[o].id,o,'2')
	        			postData[o] = queryCondition[o].value;
	        		}
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('yjycclcxdiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				if(o == "yjycclcx_stime" || o == "yjycclcx_etime" ){
					postData[o] = dojo.byId(queryCondition[o].id).value;
				}else{
					postData[o] = queryCondition[o].value;
				}
			}
			url = "claq/yjycclcxexcle?postData="
				+ dojo.toJson(postData), window.open(url);
		}
	}).placeAt('yjycclcxdiv');
	//添加表格
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'VHIC', 
		    			label : "车号" 
		    		},{field:'SHANGCHE',
		    			label : "上车时间"
	    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'XIACHE',
		    			label : "下车时间"
	    				,formatter : util.formatYYYYMMDDHHMISS
		    		},{field:'SIM',
		    			label : "SIM卡号"
		    		},{field:'JINE1',
		    			label : "交易金额(元)"
		    		},{field:'AVG_SPEED',
		    			label : "平均速度（公里/小时）"
		    		},{field:'RUNTIME',
		    			label : "营运时间（秒）"
		    		},{field:'SPEED',
		    			label : "营运速度"
		    		},{
						field : 'CAOZUO',
						label : "操作",
						renderCell: function(item){
							var taskdiv = dc.create("div",{})
							dc.place(dc.create("button",{
									innerHTML: "作业审核",
									onclick : function() {
										work_map_dialog.show().then(function(){
											console.log(item)
											ssdwmap = new AMap.Map('work_map', {
										        animateEnable:false,
										        jogEnable:false,
												view: new AMap.View2D({
													center: new AMap.LngLat(120.153576,30.287459),
													zoom: 15
												})
											});	
											addMarker(item);
										})
								}
							}),taskdiv);
							return taskdiv;
						}
					}
	];
	
	

	//查询按钮
	var xhrArgs = {
			url : basePath + "claq/yjycclcx",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
			if(data.length==0){
				yjycclcxbutton.hideWait();
				alert("该车在该时段内没有GPS数据");
			}else{
				yjycclcxbutton.hideWait();
				var speed=[];
				var speed_time=[];
				zLC=0;
				for (var i = 0; i < data.length; i++) {
					data[i] = dojo.mixin({
						id : i + 1
					}, data[i]);
				}
				store = createSyncStore({
					data : data
				});
				yjycclcxGrid.set('collection', store);
			}
			},
		};
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	yjycclcxGrid = new CustomGrid({
	   					columns : columns
	   				}, "yjycclcxtable");
	
	
	
});
//单击表内容,在地图添加改位置的点
function addMarker(row){
	ssdwmap.clearMap();
	var markerContent = document.createElement("div");
    markerContent.className = "txtstyle";
    //点标记中的图标
    var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.src="resources/images/c.png";
	markerContent.appendChild(markerImg);
	marker = new AMap.Marker({
	    map:ssdwmap,
	    position:new AMap.LngLat(row.PX,row.PY),     
	    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
	    draggable:false,  //是否可拖动
	    content:markerContent   //自定义点标记覆盖物内容
	});
	ssdwmap.setCenter(new AMap.LngLat(row.PX,row.PY));
	var txt = "<b style='color:#3399FF'>"+row.VHIC+"</b><br/><b>[所属公司]</b>："+row.COMP_NAME+"<br/><b>[当前速度]</b>："+row.SPEED+"<br/><b>[车辆所属人]</b>："+row.OWN_NAME
					  +"<br/><b>[联系电话]</b>："+row.OWN_TEL+"<br/><b>[经度]</b>："+row.PX+"<br/><b>[纬度]</b>："+row.PY;
	var info = [];                 
	info.push(txt);                 
	               
	var inforWindowone = new AMap.InfoWindow({                 
	  offset:new AMap.Pixel(8,8),                 
	  content:info.join("<br>")                 
	});           
	  inforWindowone.open(ssdwmap,marker.getPosition());                 
	  AMap.event.addListener(marker,"click",function(e){                 
		  inforWindowone.open(ssdwmap,marker.getPosition());                 
		});
	               
}	
