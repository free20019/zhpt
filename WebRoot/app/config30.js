dojoConfig = {
		 packages : [ {
			name : "app",
			location : "/zhpt/app",
			isDebug:true
		} 
//		 ,{
//				name : "cbtree",
//				location : "../../cbtree-v0.9.4-0"
//			} 
		 ]
};




leftYjzhMenu=[];
leftMap=[];
leftScjgMenu=[];
leftFzjcMenu=[];
leftZfbMenu=[];
leftXxbsMenu=[];
leftZcyyMenu=[];
leftDzddMenu=[];
leftQxglMenu=[];

var xhrArgsqx123 = {
//  		url:"http://localhost:8080/zhpt/qxgl/qxglcheckbm",
//      url : basePath + "qxgl/qxglcheckbm",
  		url:  "qxgl/qxglcheckbm",
	    handleAs : "json",
  	        load : function(data){
  	        	console.log(data);	
  	        	var a1 = data.xt1;
  	        	var a2 = data.xt2;
  	        	var a3 = data.xt3;
  	        	var a4 = data.xt4;
  	        	var a5 = data.xt5;
  	        	var a6 = data.xt6;
  	        	var a7 = data.xt7;
  	        	var a8 = data.xt8;
  	        	var a9 = data.xt9;
  	        	for(var i=0;i<a1.length;i++){
  	        		leftYjzhMenu.push(eval("(" + a1[i] + ")"));
  	        	}
  	        	for(var i=0;i<a2.length;i++){
  	        		leftMap.push(eval("(" + a2[i] + ")"));
  	        	}
  	        	for(var i=0;i<a3.length;i++){
  	        		leftScjgMenu.push(eval("(" + a3[i] + ")"));
  	        	}
  	        	for(var i=0;i<a4.length;i++){
  	        		leftFzjcMenu.push(eval("(" + a4[i] + ")"));
  	        	}
  	        	for(var i=0;i<a5.length;i++){
  	        		leftZfbMenu.push(eval("(" + a5[i] + ")"));
  	        	}
  	        	for(var i=0;i<a6.length;i++){
  	        		leftXxbsMenu.push(eval("(" + a6[i] + ")"));
  	        	}
  	        	for(var i=0;i<a7.length;i++){
  	        		leftZcyyMenu.push(eval("(" + a7[i] + ")"));
  	        	}
  	        	for(var i=0;i<a8.length;i++){
  	        		leftDzddMenu.push(eval("(" + a8[i] + ")"));
  	        	}
  	        	for(var i=0;i<a9.length;i++){
  	        		leftQxglMenu.push(eval("(" + a9[i] + ")"));
  	        	}
  	        }
}
basePath = ''//'http://115.236.61.148:9085/zhpt/'


//leftYjzhMenu = [{'name':'应急接入','item':['值班','应急预警','接入'],'id':'menu_yjzh_jr'}
//,{'name':'应急响应','item':['启动报送'],'id':'menu_yjzh_xy'}
//,{'name':'事件处理','item':['指挥调度','处置流程'],'id':'menu_yjzh_sjcl'}
//,{'name':'信息发布','item':['信息生成','信息发布'],'id':'menu_yjzh_xxfb'}
//,{'name':'评估分析','item':['查询与统计'],'id':'menu_yjzh_pgfx'}
//,{'name':'资源库','item':[{'name':'应急知识库','item':['法律法规','案例库']},'评估标准','应急预案'],'id':'menu_yjzh_zyk'}
//];

pageSize = 30 ;	
menuHandlerLevel2 = [
     {name:"值班",title:"值班",href:'app/html/yjzhZhiban.html',closable:true}
     ,{name:"应急预警",title:"应急预警",href:'app/html/yjzhyjyj.html',closable:true}
    ,{name:"接入",title:"接入",href:'app/html/monitor/yjsjjr.html',closable:true}
	,{name:"启动报送",title:"启动报送",href:'app/html/yjzhBs.html',closable:true}
	//,{name:"启动",title:"启动",href:'app/html/yjzhQd.html',closable:true}
	,{name:"指挥调度",title:"指挥调度",href:'app/html/yjzhZhdd.html',closable:true}
	,{name:"处置流程",title:"处置流程",href:'app/html/yjzhCzlc.html',closable:true}
	,{name:"信息生成",title:"信息生成",href:'app/html/yjzhXxsc.html',closable:true}
	,{name:"信息发布",title:"信息发布",href:'app/html/yjzhXxfb.html',closable:true}
//	,{name:"分析与评估",title:"分析与评估",href:'app/html/yjzhFxpg.html',closable:true}
	,{name:"应急演练",title:"应急演练",href:'app/html/yjzhYjyl.html',closable:true}
	,{name:"维护管理",title:"维护管理",href:'app/html/yjzhWhgl.html',closable:true}
	,{name:"评估标准",title:"评估标准", href:'app/html/yjzhPgbz.html',closable:true}
	,{name:"应急预案",title:"应急预案",href:'app/html/yjzhYjya.html',closable:true}
	,{name:"查询与统计",title:"查询与统计",href:'app/html/monitor/cxytj.html',closable:true}
];
menuHandlerLevel3 = [
     {name:"法律法规",title:"法律法规",href:'app/html/yjzhFlfg.html',closable:true}
	//,{name:"应急知识",title:"应急知识",href:'app/html/yjzhYjzs.html',closable:true}
	,{name:"案例库",title:"案例库",href:'app/html/yjzhAlk.html',closable:true}
	,{name:"组织机构",title:"组织机构",href:'app/html/yjzhZzjg.html',closable:true}
	,{name:"车辆",title:"车辆",href:'app/html/yjzhCl.html',closable:true}
	,{name:"物资",title:"物资",href:'app/html/yjzhWz.html',closable:true}
];

imports_1 = [ "dijit/layout/TabContainer", "dijit/layout/ContentPane",
	"dijit/Dialog", "dgrid/Editor", "dijit/form/Button",
	"dijit/form/DateTextBox", "dijit/form/CheckBox",
	"dijit/form/TimeTextBox", "dijit/form/SimpleTextarea",
	"dgrid/OnDemandGrid", "dijit/form/TextBox",
	"dgrid/extensions/Pagination", "dgrid/Selection", "dgrid/Keyboard",
	"dgrid/extensions/ColumnResizer", "app/createAsyncStore",
	"dstore/Memory", "dgrid/extensions/DijitRegistry",
	"dijit/registry", "dojo/dom-style", "dojo/_base/declare",
	"cbtree/Tree", "cbtree/models/ForestStoreModel",
	"dojo/data/ItemFileWriteStore", "dojo/dom-construct", "dojo/on",
	"app/util", "dojo/domReady!" 
];

leftMap = [{'name':'运行状态监控','item':['实时监控','营运态势','行业总览','车辆跟踪','重点关注车辆'],'id':'menu_rcjkyyj_yxztjk'}
	,{'name':'安全监控与报警','item':['失物查找','绕道','计价器异常','速度曲线报表','里程统计',
                               '分公司里程统计','车辆在线统计','多日未上线车辆统计'],'id':'menu_rcjkyyj_aqjkybj'}];
leftMapView = [
       {name:"实时监控",title:"实时监控",href:'index20_2.html',closable:true}
      ,{name:"车辆跟踪",title:"车辆跟踪",href:'app/html/monitor/clgz.html',closable:true}
		,{name:"行业总览",title:"行业总览",href:'app/html/rcyy/hyzl.html',closable:true}
       ,{name:"信息下发",title:"信息下发",href:'index20_2_2.html',closable:true}
      ,{name:"营运态势",title:"营运态势", href:'app/html/monitor/taxiyyts.html',closable:true}
      ,{name:"速度曲线报表",title:"速度曲线报表", href:'app/html/monitor/sdqxbj.html',closable:true}
      ,{name:"里程统计",title:"里程统计", href:'app/html/monitor/lctj.html',closable:true}
      ,{name:"分公司里程统计",title:"分公司里程统计", href:'app/html/monitor/fgslctj.html',closable:true}
      ,{name:"车辆在线统计",title:"车辆在线统计", href:'app/html/monitor/wsxcl.html',closable:true}
      ,{name:"多日未上线车辆统计",title:"多日未上线车辆统计", href:'app/html/monitor/drwsxcl.html',closable:true}
      ,{name:"车载视频",title:"车载视频", href:'app/html/monitor/shipin.html',closable:true}
      ,{name:"运价异常车辆查询",title:"运价异常车辆查询", href:'app/html/monitor/yjycclcx.html',closable:true}
      ,{name:"失物查找",title:"失物查找", href:'app/html/monitor/shiwucz.html',closable:true}
      ,{name:"绕道",title:"绕道", href:'app/html/monitor/raodao.html',closable:true}
      ,{name:"计价器异常",title:"计价器异常", href:'app/html/monitor/jjqyc.html',closable:true}
      ,{name:"重点关注车辆",title:"重点关注车辆", href:'app/html/monitor/zdgzcl.html',closable:true}
];

//市场监管
//leftScjgMenu = [{'name':'基础信息管理','item':['司机资质信息','司机账号信息','车辆信息','车载设备信息'],'id':'menu_scjg_jcxxgl'}
//	,{'name':'司机运营监管','item':['司机上班信息','司机下班信息',  '替班信息'],'id':'menu_scjg_sjyyjg'}
//	,{'name':'执法稽查信息','item':['执法稽查信息' ],'id':'menu_scjg_zfjcxx'}
//,{'name':'服务质量监督','item':['考核信息投诉','违章处罚信息','服务质量综合评定',
////                              '区域管理',
//                              ],'id':'menu_scjg_fwzljd'}
//];
leftScjgView = [{name:"视频监控",title:"视频监控", href:'app/html/yjzh/cljksp.html',closable:true}
    ,{name:"区域管理",title:"区域管理", href:'app/html/monitor/area_management.html',closable:true}
    ,{name:"司机资质信息",title:"司机资质信息", href:'app/html/monitor/sjzzxx.html',closable:true}
    ,{name:"车辆信息",title:"车辆信息", href:'app/html/monitor/clxx.html',closable:true}
    ,{name:"执法稽查信息",title:"执法稽查信息", href:'app/html/monitor/zfjcxx.html',closable:true}
    ,{name:"车载设备信息",title:"车载设备信息", href:'app/html/monitor/czsbxx.html',closable:true}
    ,{name:"司机上班信息",title:"司机上班信息", href:'app/html/monitor/sjsbxx.html',closable:true}
    ,{name:"司机下班信息",title:"司机下班信息", href:'app/html/monitor/sjxbxx.html',closable:true}
    ,{name:"替班信息",title:"替班信息", href:'app/html/monitor/tbxx.html',closable:true}
    ,{name:"考核信息投诉",title:"考核信息投诉", href:'app/html/monitor/khxxts.html',closable:true}
    ,{name:"违章处罚信息",title:"违章处罚信息", href:'app/html/monitor/khxxwz.html',closable:true}
    ,{name:"服务质量综合评定",title:"服务质量综合评定", href:'app/html/monitor/fwzlzhpd.html',closable:true}
	,{name:"司机账号信息",title:"司机账号信息", href:'app/html/monitor/sjzhxx.html',closable:true}
];

//辅助决策
leftFzjcMenu = [
{'name':'综合查询','item':['人员查询','车辆查询','业户查询','车辆定位信息','营运信息查询','营运日报','营运月报','营运年报'],'id':'menu_fzjc_zhcx'}
,{'name':'运营情况统计分析','item':['重点区域车辆数量分析','重点区域车辆数量明细','实载率分析','重点区域上线率分析','重车率分析','出租聚运情况分析','营运车辆围栏进出分析'],'id':'menu_fzjc_yyqktjfx'}
,{'name':'运营效益统计分析','item':['出租企业营收信息统计分析','出租司机营收信息统计分析','出租车辆营收信息统计分析','单车平均营运收益分析','单车平均载客里程分析','单车平均空驶里程分析','单车平均行驶总里程分析','单车平均营运次数分析','单车平均载客时间分析','单车平均载客等候时间分析'],'id':'menu_fzjc_yyxytjfx'}
,{'name':'运力调整','item':['杭州出租保有量统计'],'id':'menu_fzjc_yltz'}
];
leftFzjcView = [
	{name:"出租企业营收信息统计分析",title:"出租企业营收信息统计分析", href:'app/html/monitor/qyycxxtj.html',closable:true}
	,{name:"出租司机营收信息统计分析",title:"出租司机营收信息统计分析", href:'app/html/monitor/sjycxxtj.html',closable:true}
	,{name:"出租车辆营收信息统计分析",title:"出租车辆营收信息统计分析", href:'app/html/monitor/clycxxtj.html',closable:true}
	,{name:"单车平均营运收益分析",title:"单车平均营运收益分析", href:'app/html/monitor/dcpjyysyfx.html',closable:true}
	,{name:"单车平均载客里程分析",title:"单车平均载客里程分析", href:'app/html/monitor/dcpjzklc.html',closable:true}
	,{name:"单车平均空驶里程分析",title:"单车平均空驶里程分析", href:'app/html/monitor/dcpjkslcfx.html',closable:true}
	,{name:"单车平均行驶总里程分析",title:"单车平均行驶总里程分析", href:'app/html/monitor/dcpjxszlcfx.html',closable:true}
	,{name:"单车平均营运次数分析",title:"单车平均营运次数分析", href:'app/html/monitor/dcpjyycsfx.html',closable:true}
	,{name:"单车平均载客时间分析",title:"单车平均载客时间分析", href:'app/html/monitor/dcpjzksjfx.html',closable:true}
	,{name:"单车平均载客等候时间分析",title:"单车平均载客等候时间分析", href:'app/html/monitor/dcpjzkdhsjfx.html',closable:true}
	,{name:"业户查询",title:"业户查询", href:'app/html/monitor/findcompxx.html',closable:true}
	,{name:"车辆查询",title:"车辆查询", href:'app/html/monitor/findvehinfo.html',closable:true}
	,{name:"人员查询",title:"人员查询", href:'app/html/monitor/findrenyuan.html',closable:true}
	,{name:"车辆定位信息",title:"车辆定位信息", href:'app/html/monitor/vehpos.html',closable:true}
	,{name:"营运信息查询",title:"营运信息查询", href:'app/html/monitor/findyyinfo.html',closable:true}
	,{name:"营运日报",title:"营运日报", href:'app/html/monitor/findoperday.html',closable:true}
	,{name:"营运月报",title:"营运月报", href:'app/html/monitor/findopermonth.html',closable:true}
	,{name:"营运年报",title:"营运年报", href:'app/html/monitor/findoperyear.html',closable:true}
	,{name:"杭州出租保有量统计",title:"杭州出租保有量统计", href:'app/html/monitor/findbyl.html',closable:true}
	,{name:"重点区域车辆数量分析",title:"重点区域车辆数量分析", href:'app/html/monitor/areaclfx.html',closable:true}
	,{name:"重点区域车辆数量明细",title:"重点区域车辆数量明细", href:'app/html/monitor/areaclmx.html',closable:true}
	,{name:"实载率分析",title:"实载率分析", href:'app/html/monitor/shizailv.html',closable:true}
	,{name:"重点区域上线率分析",title:"重点区域上线率分析", href:'app/html/monitor/zdqusxlfx.html',closable:true}
	,{name:"重车率分析",title:"重车率分析", href:'app/html/monitor/zclfx.html',closable:true}
	,{name:"服务评价信息",title:"服务评价信息", href:'app/html/monitor/fwpjxx.html',closable:true}
	,{name:"出租聚运情况分析",title:"出租聚运情况分析", href:'app/html/monitor/czjyqkfx.html',closable:true}
	,{name:"营运车辆围栏进出分析",title:"营运车辆围栏进出分析", href:'app/html/monitor/yyclwljcfx.html',closable:true}
];

//sendMessageConfig = [ 
//  {"name" : "运管局","type" : "parent","children" : [ { "_reference" : "应急办" } , { "_reference" : "应急办2" }]}
//, {"name" : "应急办","type" : "child","checked" : false,"children" : [ {"_reference" : "杨杨"}, {"_reference" : "朱同涛"} ]}
//, {"name" : "杨杨","type" : "child","checked" : false,"tel":15968127425}
//, {"name" : "朱同涛","type" : "child","checked" : false,"tel":15267455556}
//, {"name" : "办公室","type" : "parent","checked" : false,"children" : [ {"_reference" : "刘羽"}, {"_reference" : "卢春晖"} ]}
//, {"name" : "刘羽","type" : "child","checked" : false,"tel":18768189665}
//, {"name" : "卢春晖","type" : "child","checked" : false,"tel":18368833376} 
//, {"name" : "应急办2","type" : "child","checked" : false,"tel":18368833376}
//]

//sendMessageConfig = [ 
//                     {id:"id1","name" : "运管局",parent:""}
//                     , {id:"id2","name" : "应急办","checked" : false,parent:"id1"}
//                     , {id:"id3","name" : "杨杨","checked" : false,"tel":"15968127425",parent:"id2"}
//                     , {id:"id4","name" : "朱同涛","checked" : false,"tel":"15267455556",parent:"id2"}
//                     ,{id:"id5","name" : "办公室","checked" : false,parent:"id1"}
//                     , {id:"id6","name" : "刘羽","checked" : false,"tel":"18768189665","mail":"446926066@qq.com",parent:"id5"}
//  	                 , {id:"id7","name" : "卢春晖","checked" : false,"tel":"18368833376",parent:"id5"} 
//                     ];

sendMessageConfig = [ 
                     {id:"id1","name" : "运管局",parent:""}
                    , {id:"id2","name" : "指挥中心","checked" : false,parent:"id1"}
  , {id:"id6","name" : "金俊","checked" : false,"tel":"13634108668","mail":"342378212@qq.com",parent:"id2"}
                    // , {id:"id3","name" : "杨杨","checked" : false,"tel":"15968127425",parent:"id2"}
                    // , {id:"id4","name" : "朱同涛","checked" : false,"tel":"15267455556",parent:"id2"}
                   //  ,{id:"id5","name" : "办公室","checked" : false,parent:"id1"}
                   //  , {id:"id6","name" : "刘羽","checked" : false,"tel":"18768189665","mail":"446926066@qq.com",parent:"id5"}
               //     , {id:"id7","name" : "卢春晖","checked" : false,"tel":"18368833376",parent:"id5"} 
                     ]; 




//leftZfbMenu = [{'name':'交易信息总览','item':['交易信息总览','支付宝交易','微信交易','银联交易'],'id':''}];
leftZfbMenuView = [
  {name:"交易信息总览",title:"交易信息总览",href:'app/html/monitor/jyxx.html',closable:true}
  ,{name:"支付宝交易",title:"支付宝交易",href:'app/html/monitor/zfbjy.html',closable:true}
  ,{name:"微信交易",title:"微信交易",href:'app/html/monitor/wxjy.html',closable:true}
  ,{name:"银联交易",title:"银联交易",href:'app/html/monitor/yljy.html',closable:true}
];


//leftXxbsMenu = [{'name':'信息共享与报送','item':['信息共享与报送','杭州市交通运输局','浙江省交通运输厅','交通运输部'],'id':''}];
leftXxbsMenuView = [
  {name:"信息共享与报送",title:"信息共享与报送",href:'app/html/monitor/sjgxcx.html',closable:true}
  ,{name:"杭州市交通运输局",title:"杭州市交通运输局",href:'app/html/monitor/hzsjtxxj.html',closable:true}
  ,{name:"浙江省交通运输厅",title:"浙江省交通运输厅",href:'app/html/monitor/zjsjtxxzx.html',closable:true}
  ,{name:"交通运输部",title:"交通运输部",href:'app/html/monitor/jtysb.html',closable:true}
];


//leftZcyyMenu = [{'name':'专车数据总览','item':['专车数据总览','滴滴','优步','曹操','首汽','神州','易到'],'id':''}];
leftZcyyMenuView = [
  {name:"专车数据总览",title:"专车数据总览",href:'app/html/monitor/zcyy.html',closable:true}
  ,{name:"滴滴",title:"滴滴",href:'app/html/monitor/dddc.html',closable:true}
  ,{name:"优步",title:"优步",href:'app/html/monitor/ybdc.html',closable:true}
  ,{name:"曹操",title:"曹操",href:'app/html/monitor/ccdc.html',closable:true}
  ,{name:"首汽",title:"首汽",href:'app/html/monitor/sqdc.html',closable:true}
  ,{name:"神州",title:"神州",href:'app/html/monitor/szdc.html',closable:true}
  ,{name:"易到",title:"易到",href:'app/html/monitor/yddc.html',closable:true}
];


//leftDzddMenu = [{'name':'电召总览','item':['电召总览'],'id':'menu_scjg_sjyyjg'},
//	{'name':'车辆调度服务','item':['车辆调度','消息下发'],'id':'menu_rcjkyyj_yxztjk'}
//,{'name':'智能扬招服务','item':['手机扬招','平板扬招'],'id':'menu_fzjc_yyqktjfx'}
//,{'name':'企业在线管理','item':['车辆监控','消息发布','车辆营运统计','业户数据统计','营运日报','营运月报','营运年报'],'id':'menu_fzjc_yltz'}
//,{'name':'公众信息服务','item':['乘客出行服务','实时路况','司机路况','信息服务','安全服务','提醒注意'],'id':'menu_scjg_fwzljd'}
//,{'name':'电召调度服务','item':['电召服务','预约用车','失物查找','路线规划','路况查找','外语翻译'],'id':'menu_scjg_sjyyjg'}
//];
leftDzddMenuView = [
    {name:"车辆调度",title:"车辆调度",href:'app/html/monitor/cldd.html',closable:true}
    ,{name:"消息下发",title:"消息下发",href:'app/html/monitor/xxxf.html',closable:true}
    ,{name:"手机扬招",title:"手机扬招",href:'app/html/monitor/sjyz.html',closable:true}
    ,{name:"平板扬招",title:"平板扬招",href:'app/html/monitor/pbyz.html',closable:true}
    ,{name:"车辆监控",title:"车辆监控",href:'app/html/monitor/cljk.html',closable:true}
    ,{name:"消息发布",title:"消息发布",href:'app/html/monitor/xxfb.html',closable:true}
    ,{name:"车辆营运统计",title:"车辆营运统计",href:'app/html/monitor/clyytj.html',closable:true}
    ,{name:"业户数据统计",title:"业户数据统计",href:'app/html/monitor/yhyytj.html',closable:true}
    ,{name:"营运日报",title:"营运日报",href:'app/html/monitor/yyrb.html',closable:true}
    ,{name:"营运月报",title:"营运月报",href:'app/html/monitor/yyyb.html',closable:true}
    ,{name:"营运年报",title:"营运年报",href:'app/html/monitor/yynb.html',closable:true},
	{name:"电召服务",title:"电召服务",href:'app/html/monitor/dzfw.html',closable:true},
	{name:"预约用车",title:"预约用车",href:'app/html/monitor/yyyc.html',closable:true},
	{name:"失物查找",title:"失物查找",href:'app/html/monitor/swcz.html',closable:true},
	{name:"路线规划",title:"路线规划",href:'app/html/monitor/lxgh.html',closable:true},
	{name:"路况查找",title:"路况查找",href:'app/html/monitor/lkcz.html',closable:true},
	{name:"外语翻译",title:"外语翻译",href:'app/html/monitor/wyfy.html',closable:true},
	{name:"提醒注意",title:"提醒注意",href:'app/html/monitor/txzy.html',closable:true},
	{name:"安全服务",title:"安全服务",href:'app/html/monitor/aqfw.html',closable:true},
	{name:"信息服务",title:"信息服务",href:'app/html/monitor/xxfw.html',closable:true},
	{name:"司机路况",title:"司机路况",href:'app/html/monitor/sjlk.html',closable:true},
	{name:"实时路况",title:"实时路况",href:'app/html/monitor/sslk.html',closable:true},
	{name:"乘客出行服务",title:"乘客出行服务",href:'app/html/monitor/ckcxfw.html',closable:true},
	{name:"电召总览",title:"电召总览",href:'app/html/monitor/dzddzym.html',closable:true}
];
//leftQxglMenu = [{'name':'权限管理','item':['用户管理','岗位管理'],'id':'menu_scjg_qxgl'}];
eftQxglMenuView = [
	{name:"用户管理",title:"用户管理",href:'app/html/monitor/yhqxgl.html',closable:true},
	{name:"岗位管理",title:"岗位管理",href:'app/html/monitor/gwqxgl.html',closable:true}
];

