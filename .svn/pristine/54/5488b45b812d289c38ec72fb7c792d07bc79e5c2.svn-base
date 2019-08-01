var qyycxxtjGrid;
require([ "app/util","dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select"
	          ,"dijit/form/FilteringSelect","dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
	          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
	          ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
	          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
	          "dojo/dom-construct", "dojo/domReady!"], function(util,
	        		  Editor, Button,DateTextBox,Select,FilteringSelect,Grid,TextBox
	        		  , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
	        		  ,registry, domStyle,declare, dc ) {
	var  store = null ,count = 0;
	dc.place(dc.create("span",{innerHTML:"起止时间",style:{"margin-right":"5px"}}),'qyycxxtjdiv');
	var qy_stime= new TextBox({id:"qy_stime",value:setformat1(new Date((new Date()).getTime() - 1000 * 60 * 60*2)),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('qyycxxtjdiv');
	dc.place(dc.create("span",{innerHTML:"至",style:{"margin-right":"5px"}}),'qyycxxtjdiv');
	var qy_etime= new TextBox({id:"qy_etime",value:setformat1(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('qyycxxtjdiv');
	dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'qyycxxtjdiv');
	var qy_comp= new FilteringSelect({id:"qy_comp",onChange:function(){},options: [],
	    style:{"width":"8em","margin-right":"15px"}}).placeAt('qyycxxtjdiv');
	var queryCondition = {"qy_stime":qy_stime,"qy_etime":qy_etime,"qy_comp":qy_comp};
	var qyycxxtjbutton=new Button({
	        label: "查询",
	        onClick: function(){
				var stime = new Date(dojo.byId('qy_stime').value), etime = new Date(dojo.byId('qy_etime').value);
				if(stime.getFullYear()!=etime.getFullYear()) {
					alert('拒绝跨年查询,请查询当月的信息');
					return false;
				}
				if (stime.getMonth()!=etime.getMonth()) {
					alert('拒绝跨月查询,请查询当月的信息');
					return false;
				}
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        		if(o == "qy_stime" ||o == "qy_etime"){
	        			postData[o] = dojo.byId(o).value;
	        		}else{
	        			postData[o] = queryCondition[o].value;
	        		}
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('qyycxxtjdiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var stime = new Date(dojo.byId('qy_stime').value), etime = new Date(dojo.byId('qy_etime').value);
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
				if(o == "qy_stime" ||o == "qy_etime"){
					postData[o] = dojo.byId(o).value;
				}else{
					postData[o] = queryCondition[o].value;
				}
			}
			url = "hygl/czysxxtjexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('qyycxxtjdiv');
	queryGs().then(function(data){
		qy_comp.set('store',new dojo.store.Memory({ data: data.datas}));
	})
	qy_comp.set('queryExpr', "*${0}*"  );
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'COMP', 
		    			label : "所属公司" 
		    		},{field:'TOTAL', 
		    			label : "车辆数"
		    		},{field:'DRIVING',
		    			label : "营运数"
		    		},{field:'CCL', 
		    			label : "出车率"
		    		},{field:'MONEY',
		    			label : "营运金额(元)"
		    		},{field:'TIMES',
		    			label : "次数(次)"
		    		},{field:'DISTANCE',
		    			label : "计程(公里)"
		    		},{field:'EMPTY',
		    			label : "空驶(公里)"
		    		},{field:'TOTALDIS',
		    			label : "总里程(公里)"
		    		},{field:'YPERCENT',
		    			label : "实载率"
		    		},{field:'TIMEOUT',
		    			label : "载客时间(小时)"
		    		},{field:'WAITTIME',
		    			label : "载客等候时间(小时)"
		    		}
		];

		var xhrArgs = {
			url : basePath + "hygl/czysxxtj",
			postData : 'postData={"page":1,"pageSize":10}',
			handleAs : "json",
			load : function(data) {
				qyycxxtjbutton.hideWait();
				qyycxxtjGrid.totalCount = data.count;
					for(var i=0; i<data.datas.datas.length;  i++){
				    	data.datas.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas.datas
					} });
					qyycxxtjGrid.set('collection',store);
					qyycxxtjGrid.pagination.refresh(data.datas.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		qyycxxtjGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"qyycxxtjtable");
		qyycxxtjGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(qyycxxtjGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'qyycxxtjtable','after');
		
		
		//图表显示
//		dc.place(dc.create("span",{innerHTML:"杭州出租企业每日平均营收金额图表",style:{"width" : "240px","margin-right":"5px"}}),'qyycxxtjtbdiv');
//		dc.place(dc.create("span",{innerHTML:"时间",style:{"margin-right":"5px"}}),'qyycxxtjtbdiv');
//		var qy_tbtime= new TextBox({id:"qy_tbtime",value:setformatmonth(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M',dateFmt:'yyyy-MM',alwaysUseStartDate:true})},style:{"width":"8em","margin-right":"15px"}}).placeAt('qyycxxtjtbdiv');
//		var queryCondition1 = {"qy_tbtime":qy_tbtime,"qy_tbcomp":""};
//		var qyycxxtjtbbutton = new Button({
//	        label: "查询",
//	        onClick: function(){
//	        	this.showWait();
//	        	var postData = {"page":1,"pageSize":pageSize};
//			for(var o in queryCondition1){
//        		if(o == "qy_tbtime" ){
//        			postData[o] = dojo.byId(o).value;
//        		}else{
//        			postData[o] = queryCondition1[o].value;
//        		}
//        	}
//	        	dojo.xhrPost(dojo.mixin(xhrArgsqytb,{"postData":'postData='+dojo.toJson(postData)}));
//	        }
//		}).placeAt('qyycxxtjtbdiv');
//		var xhrArgsqytb = {
//				url : basePath + "hygl/find1",
//				postData : 'postData={"page":1,"pageSize":10}',
//				handleAs : "json",
//				load : function(data) {
//					qyycxxtjtbbutton.hideWait();
//					var time=[];
//					var je=[];
//					console.log(data)
//					for(var i=0;i<data.days.length;i++){
//						if(data.days[i]!=0){
//							time.push(i);
//							je.push(data.days[i]);
//						}
//					}
//					yyycxxtjtb(time,je);
//				},
//			};
});
//function yyycxxtjtb(time,je){
//		  // 使用
//	console.log(time)
//	console.log(je)
//                // 基于准备好的dom，初始化echarts图表
//                var myChart = echarts.init(document.getElementById('qyycxxtjtb'));
//	option = {
//		    title : {
//		        text: '',
//		        subtext: ''
//		    },
//		    tooltip : {
//		        trigger: 'axis'
//		    },
//		    legend: {
//		        data:['金额']
//		    },
//		    toolbox: {
//		        show : true,
//		        feature : {
//		            mark : {show: true},
//		            dataView : {show: true, readOnly: false},
//		            magicType : {show: true, type: ['line', 'bar']},
//		            restore : {show: true},
//		            saveAsImage : {show: true}
//		        }
//		    },
//		    calculable : true,
//		    xAxis : [
//		        {
//		            type : 'category',
//		            boundaryGap : false,
//		            data : time
//		        }
//		    ],
//		    yAxis : [
//		        {
//		            type : 'value',
//		            axisLabel : {
//		                formatter: '{value}'
//		            }
//		        }
//		    ],
//		    series : [
//		        {
//		            name:'金额',
//		            type:'line',
//		            data:je,
//		            markPoint : {
//		                data : [
//		                    {type : 'max', name: '最大值'},
//		                    {type : 'min', name: '最小值'}
//		                ]
//		            },
//		            markLine : {
//		                data : [
//		                    {type : 'average', name: '平均值'}
//		                ]
//		            }
//		        }
//		    ]
//		};
//		                    
//
//		                    
//
//      
//                // 为echarts对象加载数据 
//              echarts.init(document.getElementById('qyycxxtjtb')).setOption(option); 
//	}
