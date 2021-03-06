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
	dc.place(dc.create("span",{innerHTML:"起止时间",style:{"margin-right":"5px"}}),'speedtjdiv');
	var speed_stime= new TextBox({id:"speed_stime",value:setformat(new Date((new Date()).getTime() - 1000 * 60 * 60*2)),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('speedtjdiv');
	dc.place(dc.create("span",{innerHTML:"至",style:{"margin-right":"5px"}}),'speedtjdiv');
	var speed_etime= new TextBox({id:"speed_etime",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('speedtjdiv');
	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'speedtjdiv');
	var speed_comp= new FilteringSelect({id:"speed_comp",
		onChange:function(){
		queryVhicById(this.value).then(function(data){
			dijit.byId('speed_vhic').set('value',"");
			speed_vhic.set('store',new dojo.store.Memory({ data: data.datas}));
				});
		},
		options: [],
	                                       style:{"width":"8em","margin-right":"15px"}}).placeAt('speedtjdiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'speedtjdiv');
	var speed_vhic= new FilteringSelect({id:"speed_vhic",options: [],
	                                       style:{"width":"8em","margin-right":"15px"}}).placeAt('speedtjdiv');
	dc.place(dc.create("span",{innerHTML:"超速值",style:{"margin-right":"5px"}}),'speedtjdiv');
	var speed_value= new TextBox({id:"speed_value",style:{"width":"6em","margin-right":"15px"}}).placeAt('speedtjdiv');
	var queryCondition = {"speed_stime":speed_stime,"speed_etime":speed_etime,"speed_value":speed_value,"speed_vhic":speed_vhic};
	var sdqxbjbutton=new Button({
	        label: "查询",
	        onClick: function(){
				var stime = new Date(dojo.byId('speed_stime').value), etime = new Date(dojo.byId('speed_etime').value);
				var vhic = dojo.byId("speed_vhic").value;
				if(stime.getFullYear()!=etime.getFullYear()) {
					alert('拒绝跨年查询,请查询当月的信息');
					return false;
				}
				if (stime.getMonth()!=etime.getMonth()) {
					alert('拒绝跨月查询,请查询当月的信息');
					return false;
				}
				if ('' == vhic || ' ' == vhic) {
					alert('请输入车号,\n在输入车号前先选择公司');
					return false;
				}
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        		if(o == "speed_stime" || o == "speed_etime" ){
	        			postData[o] = dojo.byId(o).value;
	        		}else{
	        			postData[o] = queryCondition[o].value;
	        		}
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('speedtjdiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var stime = new Date(dojo.byId('speed_stime').value), etime = new Date(dojo.byId('speed_etime').value);
			if(stime.getFullYear()!=etime.getFullYear()) {
				alert('拒绝跨年导出,请导出当月的信息');
				return false;
			}
			if (stime.getMonth()!=etime.getMonth()) {
				alert('拒绝跨月导出,请导出当月的信息');
				return false;
			}
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				if(o == "speed_stime" || o == "speed_etime" ){
					postData[o] = dojo.byId(o).value;
				}else{
					postData[o] = queryCondition[o].value;
				}
			}
			url = "claq/clsdqxexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('speedtjdiv');
	var speedtj_zlc=dc.place(dc.create("span",{id:"zlc1",style:{"margin-right":"5px"}}),'speedtjdiv');
	
	queryGs().then(function(data){
		speed_comp.set('store',new dojo.store.Memory({ data: data.datas}));
	})
	queryVhic().then(function(data){
		speed_vhic.set('store',new dojo.store.Memory({ data: data.datas}));
		})
	speed_comp.set('queryExpr', "*${0}*"  );
	speed_vhic.set('queryExpr', "*${0}*"  );
	//添加表格
	var columns = [
		       		{field:'id',label : "序号"}, // give column a custom name
		       		{field:'VEHICLE_NUM', 
		    			label : "车号" 
		    		},{field:'PX',
		    			label : "经度"
		    		},{field:'PY',
		    			label : "纬度"
		    		},{field:'SPEED',
		    			label : "速度(KM/H)"
		    		},{field:'DIRECTION',
		    			label : "方向"
		    			,formatter : util.fxzh
		    		},{field:'SPEED_TIME',
		    			label : "时间"
		    			,formatter : util.formatYYYYMMDDHHMISS
		    		}
		    		,{field:'',
		    			label : "位置信息"
		    		}
		    							    		
	];
	
	
	
	//查询按钮
	var xhrArgs = {
			url : basePath + "claq/clsdqx",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				sdqxbjbutton.hideWait();
				var zlc1=0;
				var speed=[];
			var speed_time=[];
			if(data.length<=0){
				alert("该车没有GPS数据");
			}else{
				zlc1=0;
				for (var i = 0; i < data.length; i++) {
					if(i!=0){
						var l1=new AMap.LngLat(data[i].PX,data[i].PY);
						var l2=new AMap.LngLat(data[i-1].PX,data[i-1].PY);
						zlc1 += l1.distance(l2);
					}
					speed.push(data[i].SPEED);
					speed_time.push(util.formatYYYYMMDDHHMISS(data[i].SPEED_TIME));
					data[i] = dojo.mixin({
						id : i + 1
					}, data[i]);
				}
				dojo.byId("zlc1").innerHTML="总里程:"+parseFloat((zlc1/1000).toFixed(2))+"公里";
				store = createSyncStore({
					data : data
				});
				speedGrid.set('collection', store);
				speedform(speed,speed_time);
			}
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	speedGrid = new CustomGrid({
	   					columns : columns
	   				}, "speedtable");
					
	function speedform(speed,speed_time){		
		var myChart = echarts.init(document.getElementById('speedform'));
	    // 使用
	   
	            
		option = {
			    title : {
			        text: '',
			        subtext: ''
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['速度']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : speed_time
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series : [
			        {
			            name:'速度',
			            type:'line',
			            data:speed,
			            markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
			        }
			    ]
			};
		
		echarts.init(document.getElementById('speedform')).setOption(option);				
	}		
});
