/**
 * Created by ly on 2016/5/23.
 */
var zjsjtxxzxGrid;
require([ "app/util","dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/Select"
    ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
    ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
    ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
    ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
    "dojo/dom-construct", "dojo/domReady!"], function(util,
                                                      Editor, Button,DateTextBox,Select,Grid,TextBox
    , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
    ,registry, domStyle,declare, dc ) {
    var  store = null ,count = 0;
    var columns = [
        {field:'dojoId',label : "序号"}, // give column a custom name
        {field:'SUBJECT',
            label : "对象"
            ,formatter : function(){return "浙江省交通厅信息中心"}
        },{field:'DB_TIME',
            label : "日期"
        },{field:'COUNT',
            label : "日均(数据量)"
        },{field:'GMT_PAYMENT',
            label : "数据类型"
            ,formatter : function(){return "出租车卫星定位"}
        },{field:'SELLER_EMAIL',
            label : "车辆数"
            ,formatter : function(){return "9612"}
        },{field:'BUYER_EMAIL',
            label : "方式"
            ,formatter : function(){return "共享"}
        }
    ];


    var xhrArgs = {
        url : basePath + "jyxx/findxxbs",
        postData : 'postData={"page":1,"pageSize":30}',
        handleAs : "json",
        load : function(data) {
//					zjsjtxxzxGrid.totalCount = data.count;
            for(var i=0; i<data.datas.length;  i++){
                data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
            }
            store = new Memory({ data: {
                identifier: 'dojoId',
                label: 'dojoId',
                items: data.datas
            } });
            zjsjtxxzxGrid.set('collection',store);
//					zjsjtxxzxGrid.pagination.refresh(data.datas.length);
            var li = "<li>今日上报数据量:"+data.datas[0].COUNT+"条</li>";
            dojo.query('#sjgxtp ul')[0].innerHTML+=li;
        },
    };
    var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
    zjsjtxxzxGrid= new CustomGrid({
        totalCount : 0,
        columns: columns,
        pagination:null
    },"zjsjtxxzxtable");
//		zjsjtxxzxGrid.refresh2 = function(){
//			dojo.xhrPost(xhrArgs);
//		}
    dojo.xhrPost(xhrArgs);

//		var pageii = new Pagination(zjsjtxxzxGrid,xhrArgs,queryCondition,30);
//		
//		dc.place(pageii.first(),'zjsjtxxzxtable','after');
});
