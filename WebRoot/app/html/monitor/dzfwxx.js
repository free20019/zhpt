require([ "app/util","dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select"
	          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
	          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
	          ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
	          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
	          "dojo/dom-construct", "dojo/domReady!"], function(util,
	        		  Editor, Button,DateTextBox,Select,Grid,TextBox
	        		  , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
	        		  ,registry, domStyle,declare, dc ) {
	yywl();
	cgl();
	yclx();
});

function yywl(){
	var myChart = echarts.init(document.getElementById('yywl'));
    // 使用
	option = {
		    title : {
		        text: '月业务量',
		        subtext: ''
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['月业务量']
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
		            data : ['1号', '2号', '3号','4号', '5号', '6号', '7号' ]
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
		            name:'月业务量',
		            type:'line',
		            data:[400,324,454,565,234,645,565]
		        }
		    ] 
		};
	
	echarts.init(document.getElementById('yywl')).setOption(option);	
}

function cgl(){
	var myChart = echarts.init(document.getElementById('cgl'));
    // 使用
	option = {
		    title : {
		        text: '成功率',
		        subtext: ''
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['成功率']
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
		            data : ['1号', '2号', '3号','4号', '5号', '6号', '7号' ]
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
		            name:'成功率',
		            type:'line',
		            data:['100','99','99','100','99','100','99']
		        }
		    ] 
		};
	
	echarts.init(document.getElementById('cgl')).setOption(option);	
}

function yclx(){		
	var myChart = echarts.init(document.getElementById('yclx'));
    // 使用
   
            
	option = {
			title : {
		        text: '约车类型',
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['电话约车','短信约车','QQ约车','微信约车','预约约车','扬招点约车','WEB约车','第三方约车']
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
		            data : ['1日','2日','3日','4日','5日','6日','7日']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'电话约车',
		            type:'bar',
		            data:[55, 42, 67, 34, 55, 67, 45],
		        },{
		            name:'短信约车',
		            type:'bar',
		            data:[24, 34, 55, 21, 24, 34, 26],
		        },{
		            name:'QQ约车',
		            type:'bar',
		            data:[26, 59, 30, 26, 28, 27, 56],
		        },{
		            name:'微信约车',
		            type:'bar',
		            data:[27, 29, 19, 26, 28, 37, 56],
		        },{
		            name:'预约约车',
		            type:'bar',
		            data:[20, 19, 19, 26, 28, 27, 30],
		        },{
		            name:'扬招点约车',
		            type:'bar',
		            data:[8, 10, 20, 30, 28, 15, 60],
		        },{
		            name:'WEB约车',
		            type:'bar',
		            data:[30, 59, 20, 30, 28, 25, 30],
		        },{
		            name:'第三方约车',
		            type:'bar',
		            data:[30, 24, 40, 44, 23, 20, 19],
		        }
		    ]
		};

	
	echarts.init(document.getElementById('yclx')).setOption(option);				
}	