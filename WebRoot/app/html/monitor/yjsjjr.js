var yjzhJrGrid = null,xhrArgsyjsjjr=null;
require([ "app/util", "dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select","dijit/form/Textarea","dijit/form/FilteringSelect"
          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
          ,"app/createSyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
          "dojo/dom-construct", "dojo/domReady!"], function(util,
        		  Editor, Button,DateTextBox,Select,Textarea,FilteringSelect,Grid,TextBox
        		  , Pagination,Selection,Keyboard,ColumnResizer,createSyncStore,Memory,DijitRegistry
        		  ,registry, domStyle,declare, dc ) {
	//加载查询条件按钮
	var marker=null,xg_id=null;
	dc.place(dc.create("span",{innerHTML:"主题:",style:{"margin-right":"5px"}}),'yhToolBar');
	var yjzhJr_sjzt= new TextBox({id:"yjzhJr_sjzt",style:{"width":"104px","margin-right":"15px"}}).placeAt('yhToolBar');
	var queryCondition = {"sjzt":yjzhJr_sjzt};
	var jrbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};

				for(var o in queryCondition){
					postData[o] = queryCondition[o].value;
				}
	        	dojo.xhrPost(dojo.mixin(xhrArgsyjsjjr,{"postData":'sjzt='+dijit.byId("yjzhJr_sjzt").value}));
	        }
	}).placeAt('yhToolBar');
	new Button({
        label: "添加",
        onClick: function(){
        	AddyjsjjrDialog.show().then(function(){
//        		dijit.byId("yjsj_sjbh").setValue("");
//        		dijit.byId("yjsj_sjzt").setValue("");
//        		dijit.byId("yjsj_fsdz").setValue("");
//        		dijit.byId("yjsj_jwdxx").setValue("");
//        		dijit.byId("yjsj_bjr").setValue("");
//        		dijit.byId("yjsj_bjdh").setValue("");
//        		dijit.byId("yjsj_jjr").setValue("");
//        		dijit.byId("yjsj_sjjl").setValue("");
//        		dijit.byId("yjsj_bjnr").setValue("");
        		AddyjsjjrDialog.reset()
        		var map = new AMap.Map('sjlrMap', {
        	        resizeEnable: true,
        	        zoom:11,
//        	        center: [116.397428, 39.90923]
        	        
        	    });
        		
        		map.on('click', function(e) {
        			map.clearMap();
        			dijit.byId("yjsj_jwdxx").setValue(e.lnglat.getLng()+','+e.lnglat.getLat());
        			lnglatXY = [e.lnglat.getLng(), e.lnglat.getLat()]; 
        			var geocoder = new AMap.Geocoder({
        	            radius: 1000,
        	            extensions: "all"
        	        });        
        	        geocoder.getAddress(lnglatXY, function(status, result) {
        	            if (status === 'complete' && result.info === 'OK') {
        	              var address = result.regeocode.formattedAddress; //返回地址描述
        	              dijit.byId("yjsj_fsdz").setValue(address);
        	            }
        	        });  
        	        marker = new AMap.Marker({
        	            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
        	            position: [e.lnglat.getLng(), e.lnglat.getLat()]
        	        });
        	        marker.setMap(map);
        	        
        		});
        	});
        	
        	AddyjsjjrDialog.add = function(){
//        		var sjbh=dijit.byId("yjsj_sjbh").value;
//        		var sjzt=dijit.byId("yjsj_sjzt").value;
//        		var fsdz=dijit.byId("yjsj_fsdz").value;
//        		var jwdxx=dijit.byId("yjsj_jwdxx").value;
//        		var bjr=dijit.byId("yjsj_bjr").value;
//        		var bjdh=dijit.byId("yjsj_bjdh").value;
//        		var jjr=dijit.byId("yjsj_jjr").value;
//        		var sjjl=dijit.byId("yjsj_sjjl").value;
//        		var bjnr=dijit.byId("yjsj_bjnr").value;
//        		var bjfs=dijit.byId("yjsj_bjfs").value;
        		
    			var xhrArgs = {
    					url : basePath + "jyxx/jrsave",
    					postData : 'postData='+dojo.toJson(AddyjsjjrDialog.getValues()),
    					handleAs : "json",
    					preventCache : true,
    					withCredentials :  true,
    					load : function(data) {
    						AddyjsjjrDialog.hide();
    						dojo.xhrPost(xhrArgsyjsjjr);
    					},
    					error : function(error) {
    					}
    			};
        			
        			dojo.xhrPost(xhrArgs);
        	}
        }
	}).placeAt('yhToolBar');
	new Button({
		label : "修改",
		moudel:this,
		onClick : function(evt) {
			var hs=0;
			dojo.forEach(yjzhJrGrid.collection.data, function(item,index) { if (yjzhJrGrid.isSelected(item)) hs++; });
			if(hs==0){
				alert("请选择一行进行修改");
			}else if(hs>1){
				alert("只能选择一行进行修改");
			}else{
				AddyjsjjrDialog.show().then(function() {
					dojo.forEach(yjzhJrGrid.collection.data, function(item, index) {
						if (yjzhJrGrid.isSelected(item)) {
							console.log(item)
							dijit.byId("yjsj_sjbh").setValue(item.SJBH);
			        		dijit.byId("yjsj_sjzt").setValue(item.SJZT);
			        		dijit.byId("yjsj_fsdz").setValue(item.ADDRESS);
			        		dijit.byId("yjsj_jwdxx").setValue(item.PX+","+item.PY);
			        		dijit.byId("yjsj_bjr").setValue(item.BJR);
			        		dijit.byId("yjsj_bjdh").setValue(item.DJDH);
			        		dijit.byId("yjsj_jjr").setValue(item.JJR);
			        		dijit.byId("yjsj_sjjl").setValue(item.SJJL);
			        		dijit.byId("yjsj_bjnr").setValue(item.SJNR);
			        		dijit.byId("yjsj_id").setValue(item.ID);
							
							var map = new AMap.Map('sjlrMap', {
			        	        resizeEnable: true,
			        	        zoom:11,
//			        	        center: [116.397428, 39.90923]
			        	        
			        	    });
							
							marker = new AMap.Marker({
		        	            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
		        	            position: [item.PX, item.PY]
		        	        });
		        	        marker.setMap(map);
		        	        
			        		map.on('click', function(e) {
			        			map.clearMap();
			        			dijit.byId("yjsj_jwdxx").setValue(e.lnglat.getLng()+','+e.lnglat.getLat());
			        			lnglatXY = [e.lnglat.getLng(), e.lnglat.getLat()]; 
			        			var geocoder = new AMap.Geocoder({
			        	            radius: 1000,
			        	            extensions: "all"
			        	        });        
			        	        geocoder.getAddress(lnglatXY, function(status, result) {
			        	            if (status === 'complete' && result.info === 'OK') {
			        	              var address = result.regeocode.formattedAddress; //返回地址描述
			        	              dijit.byId("yjsj_fsdz").setValue(address);
			        	            }
			        	        });  
			        	        marker = new AMap.Marker({
			        	            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
			        	            position: [e.lnglat.getLng(), e.lnglat.getLat()]
			        	        });
			        	        marker.setMap(map);
			        	        
			        		});
						}
					})
				});
			}
			
			
			AddyjsjjrDialog.add = function(){
//				var sjbh=dijit.byId("yjsj_sjbh").value;
//        		var sjzt=dijit.byId("yjsj_sjzt").value;
//        		var fsdz=dijit.byId("yjsj_fsdz").value;
//        		var jwdxx=dijit.byId("yjsj_jwdxx").value;
//        		var bjr=dijit.byId("yjsj_bjr").value;
//        		var bjdh=dijit.byId("yjsj_bjdh").value;
//        		var jjr=dijit.byId("yjsj_jjr").value;
//        		var sjjl=dijit.byId("yjsj_sjjl").value;
//        		var bjnr=dijit.byId("yjsj_bjnr").value;
//        		var bjfs=dijit.byId("yjsj_bjfs").value;
				var editArgs = {
						url : basePath + "jyxx/jredit",
//    					postData : 'postData={"sjbh":'+"'"+sjbh+
//    					"'"+',"sjzt":'+"'"+sjzt+
//    					"'"+',"fsdz":'+"'"+fsdz+"'"+
//    					',"jwdxx":'+"'"+jwdxx+"'"+
//    					',"bjr":'+"'"+bjr+"'"+
//    					',"bjdh":'+"'"+bjdh+"'"+
//    					',"jjr":'+"'"+jjr+"'"+
//    					',"sjjl":'+"'"+sjjl+"'"+
//    					',"bjnr":'+"'"+bjnr+"'"+
//    					',"id":'+"'"+xg_id+"'"+
//    					',"bjfs":'+"'"+bjfs+"'"+'}',
						postData : 'postData='+dojo.toJson(AddyjsjjrDialog.getValues()),
    					handleAs : "json",
    					preventCache : true,
    					withCredentials :  true,
    					load : function(data) {
    						AddyjsjjrDialog.hide();
    						dojo.xhrPost(xhrArgsyjsjjr);
    					},
    					error : function(error) {
    					}
					};
				dojo.xhrPost(editArgs);
			}
		}
	}).placeAt('yhToolBar');
	new Button({
		label : "核  实",
		onClick : function() {
			var hs=0;
			dojo.forEach(yjzhJrGrid.collection.data, function(item,index) {
				if (yjzhJrGrid.isSelected(item)) {
					hs++;
				}
			});
			if(hs==0){
				alert("请选择一行进行核实");
			}else if(hs>1){
				alert("只能选择一行进行核实");
			}else{
				if(window.confirm("确定核实该事件吗？")){
					dojo.forEach(yjzhJrGrid.collection.data, function(item,index) {
						if (yjzhJrGrid.isSelected(item)) {
							dojo.xhrPost({
								postData : "id="+item.ID,
								url : basePath + "jyxx/jrhs",
								handleAs : "json",
								load : function(data) {
									dojo.xhrPost(xhrArgsyjsjjr);
								}
							});
						}
					});
				}
			}
		}
	}).placeAt('yhToolBar');
	new Button({
		label : "删除",
		moudel:this,
		onClick : function(evt) {
			//this.moudel.del()
			if(window.confirm("确定删除该事件吗？")){
				dojo.forEach(yjzhJrGrid.collection.data, function(item,index) {
					if (yjzhJrGrid.isSelected(item)) {
						dojo.xhrPost({
							postData : "id="+item.ID,
							url : basePath + "jyxx/jrdel",
							handleAs : "json",
							load : function(data) {
								dojo.xhrPost(xhrArgsyjsjjr);
							}
						});
					}
				});
			}
		}
	}).placeAt('yhToolBar');
	//添加表格
	var columns = {
			id : "序号",
			TIME : {
				label : "时间",
				formatter : util.formatYYYYMMDD
			},
			SJZT : {
				label : "主题"
			},
			BJR : {
				label : "报警人"
			},
			DJDH : {
				label : "报警人电话"
			},
			JJR : {
				label : "接警人"
			},
			SJJB : {
				label : "事件级别"
			},
			SH : {
				label : "状态"
					,formatter : util.formatSH_STATUS
			}
		};
	
	
	var sjzt=dijit.byId("yjzhJr_sjzt").value;
	//查询按钮
	xhrArgsyjsjjr = {
			url : basePath + "jyxx/jr",
//			url:"http://localhost:8080/zhpt/jyxx/jr",
			postData : "sjzt="+sjzt,
			handleAs : "json",
			load : function(data) {
				jrbutton.hideWait();
				console.log(data)
				for (var i = 0; i < data.length; i++) {
					data[i] = dojo.mixin({
						id : i + 1
					}, data[i]);
				}
				store = createSyncStore({
					data : data
				});
				yjzhJrGrid.set('collection', store);
				var length=data.length-1;
				dojo.byId("sjmiaoshu").innerHTML=data[length].SJJL;
				 marker = new AMap.Marker({
			            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
			            position: [data[length].PX, data[length].PY]
			        });
			        marker.setMap(yjsjMapShow);
			        yjsjMapShow.setZoomAndCenter(14, [data[length].PX, data[length].PY]);
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	yjzhJrGrid = new CustomGrid({
	   					columns : columns
	   				}, "yjzhJrGridDiv");
	
	yjzhJrGrid.on('.dgrid-row:click', function(event) {
		var row = yjzhJrGrid.row(event);
		console.log(row.data.SJNR)
		dojo.byId("sjmiaoshu").innerHTML=row.data.SJJL;
		yjsjMapShow.clearMap();
	        marker = new AMap.Marker({
	            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
	            position: [row.data.PX, row.data.PY]
	        });
	        marker.setMap(yjsjMapShow);
	        yjsjMapShow.setZoomAndCenter(14, [row.data.PX, row.data.PY]);
	});	
	dojo.xhrPost(xhrArgsyjsjjr);
	
	
	function addyjsjjr(){
		
	};
});


