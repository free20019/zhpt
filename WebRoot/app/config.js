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
var leftYjzhMenu=[];
//leftYjzhMenu = [{'name':'接入','item':['值班','接入']}
//,{'name':'响应','item':['报送','启动']}
//,{'name':'事件处理','item':['指挥调度','处置流程']}
//,{'name':'信息发布','item':['信息生成','信息发布']}
//,{'name':'评估分析','item':['分析与评估','查询与统计']}
//,{'name':'资源库','item':['评估标准','应急预案',{'name':'应急知识库','item':['法律法规','应急知识','案例库']}]}
//];
basePath = 'http://115.236.61.148:9085/zhpt/'
pageSize = 30 ;	
menuHandlerLevel2 = [
      {name:"值班",title:"值班",href:'app/html/yjzhZhiban.html',closable:true}
    ,{name:"接入",title:"接入",href:'app/html/yjzhJr.html',closable:true}
	,{name:"报送",title:"报送",href:'app/html/yjzhBs.html',closable:true}
	,{name:"启动",title:"启动",href:'app/html/yjzhQd.html',closable:true}
	,{name:"指挥调度",title:"指挥调度",href:'app/html/yjzhZhdd.html',closable:true}
	,{name:"处置流程",title:"处置流程",href:'app/html/yjzhCzlc.html',closable:true}
	,{name:"信息生成",title:"信息生成",href:'app/html/yjzhXxsc.html',closable:true}
	,{name:"信息发布",title:"信息发布",href:'app/html/yjzhXxfb.html',closable:true}
	,{name:"分析与评估",title:"分析与评估",href:'app/html/yjzhFxpg.html',closable:true}
	,{name:"查询与统计",title:"查询与统计",href:'app/html/yjzhCxtj.html',closable:true}
	,{name:"应急演练",title:"应急演练",href:'app/html/yjzhYjyl.html',closable:true}
	,{name:"维护管理",title:"维护管理",href:'app/html/yjzhWhgl.html',closable:true}
	,{name:"评估标准",title:"评估标准", href:'app/html/yjzhPgbz.html',closable:true}
	,{name:"应急预案",title:"应急预案",href:'app/html/yjzhYjya.html',closable:true}
];
menuHandlerLevel3 = [
     {name:"法律法规",title:"法律法规",href:'app/html/yjzhFlfg.html',closable:true}
	,{name:"应急知识",title:"应急知识",href:'app/html/yjzhYjzs.html',closable:true}
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
]