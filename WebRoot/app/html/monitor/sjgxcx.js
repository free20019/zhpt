var sjgxcxGrid;
require([ "app/util","dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select"
	          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
	          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
	          ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
	          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
	          "dojo/dom-construct", "dojo/domReady!"], function(util,
	        		  Editor, Button,DateTextBox,Select,Grid,TextBox
	        		  , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
	        		  ,registry, domStyle,declare, dc ) {
//	var  store = null ,count = 0;
//	var columns = [
//		       		{field:'dojoId',label : "序号"}, // give column a custom name
//		    		{field:'SUBJECT',
//		    			label : "对象"
//		    				,formatter : function(){return "浙江省交通厅信息中心"}
//		    		},{field:'DB_TIME',
//		    			label : "日期"
//		    		},{field:'COUNT',
//		    			label : "日均(数据量)"
//		    		},{field:'GMT_PAYMENT',
//		    			label : "数据类型"
//		    				,formatter : function(){return "出租车卫星定位"}
//		    		},{field:'SELLER_EMAIL',
//		    			label : "车辆数"
//		    				,formatter : function(){return "9612"}
//		    		},{field:'BUYER_EMAIL',
//		    			label : "方式"
//		    				,formatter : function(){return "共享"}
//		    		}
//		];
//
//
		var xhrArgs = {
			url : basePath + "jyxx/findxxbs",
			postData : 'postData={"page":1,"pageSize":30}',
			handleAs : "json",
			load : function(data) {
				console.log(data.datas[0].COUNT)
//					sjgxcxGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
				tbtbtbtb(data.now[0].AVERAGE_COUNT,data.datas[0].COUNT);
					//store = new Memory({ data: {
					//	identifier: 'dojoId',
					//	label: 'dojoId',
					//	items: data.datas
					//} });
					//sjgxcxGrid.set('collection',store);
//					sjgxcxGrid.pagination.refresh(data.datas.length);
//					var li = "今日:"+data.datas[0].COUNT+"条";
//					dojo.query('#yst2 a')[0].innerHTML+=li;
			}
		};
//		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
//		sjgxcxGrid= new CustomGrid({
//			totalCount : 0,
//			columns: columns,
//			pagination:null
//		},"sjgxcxtable");
////		sjgxcxGrid.refresh2 = function(){
////			dojo.xhrPost(xhrArgs);
////		}
			dojo.xhrPost(xhrArgs);
      dojo.connect(dojo.byId('yst_1'),'dblclick', function () {
          fireNavItem('浙江省交通运输厅')
      });
    dojo.connect(dojo.byId('ysj_1'),'dblclick', function () {
        fireNavItem('杭州市交通运输局')
    });
    dojo.connect(dojo.byId('ysb_1'),'dblclick', function () {
        fireNavItem('交通运输部')
    });
////		var pageii = new Pagination(sjgxcxGrid,xhrArgs,queryCondition,30);
////
////		dc.place(pageii.first(),'sjgxcxtable','after');
});
function fireNavItem(title){
    dojo.query('#mainSplitterLeft_xxbs .li2').forEach(function(item,index){
    if(item.innerText == title){item.click()}
})}


function tbtbtbtb(obj,obj1){
	var myChart = echarts.init(document.getElementById('yst_2'));
	option = {
		title: {
			text: '    当前流量       今日交换量',
			x: '0',
			y: '-3',
			itemGap: 8,
			textStyle : {
				color : 'rgba(30,144,255,0.8)',
				fontFamily : '微软雅黑',
				fontSize : 16,
				fontWeight : 'bolder'
			}
		},
		series : [
			{
				name:'今日交换量',
				type:'gauge',
				center : ['65%', '55%'],
				radius : '90%',
				z: 3,
				min:0,
				max:4000,
				splitNumber:5,
				axisLine: {            // 坐标轴线
					lineStyle: {       // 属性lineStyle控制线条样式
						width: 10
					}
				},
				axisTick: {            // 坐标轴小标记
					length :15,        // 属性length控制线长
					lineStyle: {       // 属性lineStyle控制线条样式
						color: 'auto'
					}
				},
				splitLine: {           // 分隔线
					length :20,         // 属性length控制线长
					lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
						color: 'auto'
					}
				},
				title : {
					offsetCenter: ['80%', '80%'],
					textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
						fontWeight: 'bolder',
						fontSize: 16,
						fontStyle: 'italic'
					}
				},
				detail : {
					textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
						fontWeight: 'bolder',
						fontSize: 18
					}
				},
				data:[{value: parseFloat(obj1/10000).toFixed(1), name: '万/日'}]
			},
			{
				name:'当前流量',
				type:'gauge',
				center : ['25%', '55%'],    // 默认全局居中
				radius : '70%',
				min:0,
				max:4,
				endAngle:45,
				splitNumber:4,
				axisLine: {            // 坐标轴线
					lineStyle: {       // 属性lineStyle控制线条样式
						width: 8
					}
				},
				axisTick: {            // 坐标轴小标记
					length :12,        // 属性length控制线长
					lineStyle: {       // 属性lineStyle控制线条样式
						color: 'auto'
					}
				},
				splitLine: {           // 分隔线
					length :20,         // 属性length控制线长
					lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
						color: 'auto'
					}
				},
				pointer: {
					width:5
				},

				title : {
					offsetCenter: ['60%', '100%'],
					textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
						fontWeight: 'bolder',
						fontSize: 16,
						fontStyle: 'italic'
					}
				},
				detail : {
					textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
						fontWeight: 'bolder',
						fontSize: 18
					}
				},
				data:[{value: parseFloat(obj/10000).toFixed(1), name: '万/分'}]
			}
		]
	};
	echarts.init(document.getElementById('yst_2')).setOption(option);
}