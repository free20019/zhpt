var areaclmxGrid = null;
var areaclmxinfoGrid=null
var speedGrid = null;
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
	dc.place(dc.create("span",{innerHTML:"区域",style:{"margin-right":"5px"}}),'areaclmxdiv');
	var areaclmx_area= new FilteringSelect({id:"areaclmx_area",options: [],style:{"width":"8em","margin-right":"15px"}}).placeAt('areaclmxdiv');
	dc.place(dc.create("span",{innerHTML:"日期",style:{"margin-right":"5px"}}),'areaclmxdiv');
	var areaclmx_time= new TextBox({id:"areaclmx_time",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('areaclmxdiv');
	
	dc.place(dc.create("span",{innerHTML:"超速值",style:{"margin-right":"5px"}}),'areaclmxdiv');
	var speed_value= new TextBox({id:"speed_value",style:{"width":"6em","margin-right":"15px"}}).placeAt('areaclmxdiv');
	var queryCondition = {"quyu":areaclmx_area,"time":areaclmx_time,"speed":speed_value};
	var areaclmxbutton = new Button({
	        label: "查询",
	        onClick: function(){
	        	this.showWait();
	        	var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        		if(o == "areaclmx_time"){
	        			postData[o] = dojo.byId(queryCondition[o].id).value;
	        		}else{
	        			postData[o] = queryCondition[o].value;
	        		}
	        		
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('areaclmxdiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				if(o == "areaclmx_time"){
					postData[o] = dojo.byId(queryCondition[o].id).value;
				}else{
					postData[o] = queryCondition[o].value;
				}

			}
			url = "hygl/clmxexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('areaclmxdiv');

	queryArea().then(function(data){
//		console.dir("#####"+data.datas)
		areaclmx_area.set('store',new dojo.store.Memory({ data: data.datas}));
	})
		areaclmx_area.set('queryExpr', "*${0}*"  )
	//添加表格
	var columns = [
		       		{field:'y0',
		    			label : "0:00" 
		    		},{field:'y1', 
		    			label : "0:30" 
		    		},{field:'y2', 
		    			label : "1:00" 
		    		},{field:'y3', 
		    			label : "1:30" 
		    		},{field:'y4',
		    			label : "2:00"
			    	},{field:'y5', 
		    			label : "2:30" 
		    		},{field:'y6',
		    			label : "3:00"
				    },{field:'y7', 
		    			label : "3:30" 
		    		},{field:'y8',
		    			label : "4:00"
				    },{field:'y9', 
		    			label : "4:30" 
		    		},{field:'y20',
		    			label : "5:00"
				    },{field:'y11', 
		    			label : "5:30" 
		    		},{field:'y12',
		    			label : "6:00"
				    },{field:'y13', 
		    			label : "6:30" 
		    		},{field:'y14',
		    			label : "7:00"
				    },{field:'y15', 
		    			label : "7:30" 
		    		},{field:'y16',
		    			label : "8:00"
				    },{field:'y17', 
		    			label : "8:30" 
		    		},{field:'y18',
		    			label : "9:00"
				    },{field:'y19', 
		    			label : "9:30" 
		    		},{field:'y20', 
		    			label : "10:00" 
		    		},{field:'y21', 
		    			label : "10:30" 
		    		},{field:'y22', 
		    			label : "11:00" 
		    		},{field:'y23', 
		    			label : "11:30" 
		    		},{field:'y24',
		    			label : "12:00"
			    	},{field:'y25', 
		    			label : "12:30" 
		    		},{field:'y26',
		    			label : "13:00"
				    },{field:'y27', 
		    			label : "13:30" 
		    		},{field:'y28',
		    			label : "14:00"
				    },{field:'y29', 
		    			label : "14:30" 
		    		},{field:'y30',
		    			label : "15:00"
				    },{field:'y31', 
		    			label : "15:30" 
		    		},{field:'y32',
		    			label : "16:00"
				    },{field:'y33', 
		    			label : "16:30" 
		    		},{field:'y34',
		    			label : "17:00"
				    },{field:'y35', 
		    			label : "17:30" 
		    		},{field:'y36',
		    			label : "18:00"
				    },{field:'y37', 
		    			label : "18:30" 
		    		},{field:'y38',
		    			label : "19:00"
				    },{field:'y39', 
		    			label : "19:30" 
		    		},{field:'y40', 
		    			label : "20:00" 
		    		},{field:'y41', 
		    			label : "20:30" 
		    		},{field:'y42', 
		    			label : "21:00" 
		    		},{field:'y43', 
		    			label : "21:30" 
		    		},{field:'y44',
		    			label : "22:00"
			    	},{field:'y45', 
		    			label : "22:30" 
		    		},{field:'y46',
		    			label : "23:00"
				    },{field:'y47', 
		    			label : "23:30" 
		    		}
	];
	
	

	//查询按钮
	var xhrArgs = {
			url : basePath + "hygl/clmx",
			postData : 'postData={"page":1,"pageSize":pageSize}',
			handleAs : "json",
			load : function(data) {
				areaclmxbutton.hideWait();
		console.log(data)
				for (var i = 0; i < data.datas.length; i++) {
					
					data.datas[i] = dojo.mixin({
						id : i + 1
					}, data.datas[i]);
				}
				store = createSyncStore({
					data : data.datas
				});
				areaclmxGrid.set('collection', store);
				
				var aa = dojo.query('#areaclmxtable .dgrid-cell');
				for(var i=48;i<aa.length; i++){
					aa[i].index = i-48;
					aa[i].onclick = function(){
						console.log(this.index)
						findareamxinfo(this.index);
					}
					
				}
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	areaclmxGrid = new CustomGrid({
	   					columns : columns
	   				}, "areaclmxtable");
	
	
//	areaclmxGrid.on('.dgrid-cell:click', function(event) {
//		console.log(this.index)
//			//var row = areaclmxGrid.row(event);
//			//console.log('Row clicked:', row.id + '\t' + event);
//		});

// dojo.xhrPost(xhrArgs);
	
	
	//具体明细数据查询
	function findareamxinfo(i){
		console.log(i)
		//添加表格
		var columns1 = [
			       		{field:'id',label : "序号"}, // give column a custom name
		    		{field:'COMP_NAME', 
		    			label : "公司" 
		    		},{field:'AREA_NAME', 
		    			label : "所在区域"
		    		},{field:'VEHI_NO', 
		    			label : "车号"
		    		},{field:'SPEED', 
		    			label : "速度(Km/h)"
		    		},{field:'VEHI_SIM', 
		    			label : "SIM卡号"
		    		},{field:'OWN_NAME', 
		    			label : "联系人"
		    		},{field:'OWN_TEL', 
		    			label : "联系电话"
		    		}
		];
		
		

		//查询按钮
		var xhrArgs1 = {
				url : basePath + "hygl/clmxinfo",
				postData : 'postData={"page":1,"pageSize":'+pageSize+',"quyu":"'+dijit.byId("areaclmx_area").value+'","time":"'+dijit.byId("areaclmx_time").value+'","i":'+i+',"speed":"'+dijit.byId("speed_value").value+'"}',
				handleAs : "json",
				load : function(data) {
					areaclmxbutton.hideWait();

					var speed=[];
			console.log(data.datas)
					for (var i = 0; i < data.datas.length; i++) {
						
						//speed.push(data[i].SPEED);
						
						data.datas[i] = dojo.mixin({
							id : i + 1
						}, data.datas[i]);
					}
					store = createSyncStore({
						data : data.datas
					});
					areaclmxinfoGrid.set('collection', store);
					speedGrid.set('collection', store);
					
				},
				error : function(error) {
//					targetNode.innerHTML = "An unexpected error occurred: "
//							+ error;
				}
			};
		
		
		var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
		   						Selection, ColumnResizer, Editor ]);
		
		areaclmxinfoGrid = new CustomGrid({
		   					columns : columns1
		   				}, "areaclmxtb");
		dojo.xhrPost(xhrArgs1);
		speedGrid = new CustomGrid({
			columns : columns1
		}, "speedtable");
	}
});
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
	var time=y+"-"+m+"-"+d; 
	return time;
}
/**
 * 区域
 * @returns
 */
function queryArea(){
	var xhrArgs = {
			url : basePath + "common/findarea",
			postData : 'postData={"page":1,"pageSize":9999}',
			handleAs : "json"
	};
	return dojo.xhrPost(xhrArgs);
}
