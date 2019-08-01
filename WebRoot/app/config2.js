dojoConfig = {
		 packages : [ {
			name : "app",
			location : "/zhpt/app"
		} 
//		 ,{
//				name : "cbtree",
//				location : "../../cbtree-v0.9.4-0"
//			} 
		 ]
};
leftYjzhMenu = [{'name':'应急指挥','item':['流程监控','车辆监控','营运态势','区域管理','速度曲线报表','里程统计','分公司里程统计','车辆在线统计','多日未上线车辆统计','司机资质信息','车辆信息','车载设备信息','司机上班信息','司机下班信息','替班信息','执法稽查信息','考核信息投诉','违章处罚信息',,'服务质量综合评定','出租企业营收信息统计分析']}
];
basePath = 'http://115.236.61.148:9085/zhpt/'
menuHandlerLevel2 = [
      {name:"流程监控",title:"流程监控",href:'app/html/yjzh/lcjk.html',closable:true}
    ,{name:"车辆监控",title:"车辆监控", href:'app/html/yjzh/cljk.html',closable:true}
    ,{name:"视频监控",title:"视频监控", href:'app/html/yjzh/cljksp.html',closable:true}
    ,{name:"营运态势",title:"营运态势", href:'app/html/monitor/taxiyyts.html',closable:true}
    ,{name:"区域管理",title:"区域管理", href:'app/html/monitor/area_management.html',closable:true}
    ,{name:"速度曲线报表",title:"速度曲线报表", href:'app/html/monitor/sdqxbj.html',closable:true}
    ,{name:"里程统计",title:"里程统计", href:'app/html/monitor/lctj.html',closable:true}
    ,{name:"分公司里程统计",title:"分公司里程统计", href:'app/html/monitor/fgslctj.html',closable:true}
    ,{name:"车辆在线统计",title:"车辆在线统计", href:'app/html/monitor/wsxcl.html',closable:true}
    ,{name:"多日未上线车辆统计",title:"多日未上线车辆统计", href:'app/html/monitor/drwsxcl.html',closable:true}
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
    ,{name:"出租企业营收信息统计分析",title:"出租企业营收信息统计分析", href:'app/html/monitor/qyycxxtj.html',closable:true}
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
]