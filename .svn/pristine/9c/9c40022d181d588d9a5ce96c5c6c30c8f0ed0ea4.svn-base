/**
 * Created by ly on 2016/5/23.
 */
var jtysbGrid;
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
        {field:'',
            label : "对象"
        },{field:'',
            label : "日期"
        },{field:'',
            label : "日均(数据量)"
        },{field:'',
            label : "数据类型"
        },{field:'',
            label : "车辆数"
        },{field:'',
            label : "方式"
        }
    ];


    var xhrArgs = {
        url : basePath + "jyxx/findxxbs",
        postData : 'postData={"page":1,"pageSize":30}',
        handleAs : "json",
        load : function(data) {
//					jtysbGrid.totalCount = data.count;
//            for(var i=0; i<data.datas.length;  i++){
//                data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
//            }
            store = new Memory({ data: {
                identifier: 'dojoId',
                label: 'dojoId',
//                items: data.datas
            } });
            jtysbGrid.set('collection',store);
//					jtysbGrid.pagination.refresh(data.datas.length);
        },
    };
    var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
    jtysbGrid= new CustomGrid({
        totalCount : 0,
        columns: columns,
        pagination:null
    },"jtysbtable");
//		jtysbGrid.refresh2 = function(){
//			dojo.xhrPost(xhrArgs);
//		}
    dojo.xhrPost(xhrArgs);

//		var pageii = new Pagination(jtysbGrid,xhrArgs,queryCondition,30);
//		
//		dc.place(pageii.first(),'jtysbtable','after');
});
