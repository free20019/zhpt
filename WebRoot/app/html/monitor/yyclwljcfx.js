var yyclwljcfxGrid = null;
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
	//添加表格
	var columns = [
       {field:'name',
           label : "围栏名称"
       },{field:'vhic',
           label : "车牌号码"
       },{field:'stime',
           label : "进围栏时间"
       },{field:'etime',
           label : "出围栏时间"
       },{field:'address',
           label : "地址"
       }
   ];
	//查询按钮
	var xhrArgs = {
			url : basePath + "hygl/findoperyear",
			postData : "time=2018",
			handleAs : "json",
			load : function(data) {
				 var datas = [{"name":"杭州东","vhic":"浙AT2550","stime":"2018-05-25 12:26:36","etime":"2018-05-25 16:16:32","address":"杭州东(高速口)"},
                          {"name":"杭州南","vhic":"浙ALT815","stime":"2018-05-25 08:37:59","etime":"2018-05-25 14:59:53","address":"杭州南(绕城南口)"}]
				store = createSyncStore({
					data : datas
				});
				yyclwljcfxGrid.set('collection', store);
			},
		};
	
	
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
	   						Selection, ColumnResizer, Editor ]);
	yyclwljcfxGrid = new CustomGrid({
	   					columns : columns
	   				}, "yyclwljcfxtable");
	dojo.xhrPost(xhrArgs);
	yyclwljcfxtb();
});
function yyclwljcfxtb(){		
	var myChart = echarts.init(document.getElementById('yyclwljcfxtb'));
    // 使用
   
            
	option = {
		    title : {
		        text: '燃油类型',
		        subtext: '',
		        x:'left'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['插电式新能源车','汽油车','柴油车','油电混合车']
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:1563, name:'插电式新能源车'},
		                {value:7236, name:'汽油车'},
		                {value:2963, name:'柴油车'},
		                {value:1492, name:'油电混合车'}
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	
	echarts.init(document.getElementById('yyclwljcfxtb')).setOption(option);				
}	