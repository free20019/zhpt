//单车平均载客等候时间分析
var dcpjzkdhsjfxGrid = null;
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
    var dcpjzkdhsjfx_time= new TextBox({id:"dcpjzkdhsjfx_time",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('dcpjzkdhsjfxdiv');
    var queryCondition = {"time":dcpjzkdhsjfx_time};
    var dcpjzkdhsjfxbutton=new Button({
        label: "查询",
        onClick: function(){
            this.showWait();
            var postData = {"page":1,"pageSize":pageSize};
            for(var o in queryCondition){
                if(o == "time"){
                    postData[o] = dojo.byId("dcpjzkdhsjfx_time").value;
                }else{
                    postData[o] = queryCondition[o].value;
                }
            }
            dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
        }
    }).placeAt('dcpjzkdhsjfxdiv');
    new Button({
        label : "导  出",
        onClick : function() {
            var postData = {
                "page" : 1,
                "pageSize" : 10000
            };
            for(var o in queryCondition){
                if(o == "time"){
                    postData[o] = dojo.byId("dcpjzkdhsjfx_time").value;
                }else{
                    postData[o] = queryCondition[o].value;
                }
            }
            url = "hygl/finddcfxexcle?postData="
                + dojo.toJson(postData), window.open(url)
        }
    }).placeAt('dcpjzkdhsjfxdiv');
    //添加表格
    var columns = [
        {field:'message',
            label : "等候时间(时/分)"
        },{field:'y0',
            label : "0:00"
        },{field:'y1',
            label : "1:00"
        },{field:'y2',
            label : "2:00"
        },{field:'y3',
            label : "3:00"
        },{field:'y4',
            label : "4:00"
        },{field:'y5',
            label : "5:00"
        },{field:'y6',
            label : "6:00"
        },{field:'y7',
            label : "7:00"
        },{field:'y8',
            label : "8:00"
        },{field:'y9',
            label : "9:00"
        },{field:'y10',
            label : "10:00"
        },{field:'y11',
            label : "11:00"
        },{field:'y12',
            label : "12:00"
        },{field:'y13',
            label : "13:00"
        },{field:'y14',
            label : "14:00"
        },{field:'y15',
            label : "15:00"
        },{field:'y16',
            label : "16:00"
        },{field:'y17',
            label : "17:00"
        },{field:'y18',
            label : "18:00"
        },{field:'y19',
            label : "19:00"
        },{field:'y20',
            label : "20:00"
        },{field:'y21',
            label : "21:00"
        },{field:'y22',
            label : "22:00"
        },{field:'y23',
            label : "23:00"
        }
    ];



    //查询按钮
    var xhrArgs = {
        url : basePath + "hygl/finddcfx",
        postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
        handleAs : "json",
        load : function(data) {
            dcpjzkdhsjfxbutton.hideWait();
            var jint = [];
            var zuot = [];
            var qiant=[];
            var shangz=[];
            var pingj=[];
            var shangy=[];
            var shangm=[];
            var max=[];
            var min=[];
            for (var i = 0; i < data.DATA.length; i++) {
                if(i==0){
                    jint=tjnr(data.DATA[0]);
                }else if(i==1){
                    zuot=tjnr(data.DATA[1]);
                }else if(i==2){
                    qiant=tjnr(data.DATA[2]);
                }else if(i==3){
                    shangz=tjnr(data.DATA[3]);
                }else if(i==4){
                    pingj=tjnr(data.DATA[4]);
                }else if(i==5){
                    max=tjnr(data.DATA[5]);
                }else if(i==6){
                    min=tjnr(data.DATA[6]);
                }else if(i==7){
                    shangy=tjnr(data.DATA[7]);
                }else if(i==8){
                    shangn=tjnr(data.DATA[8]);
                }
                data.DATA[i] = dojo.mixin({
                    id : i + 1
                }, data.DATA[i]);
            }
            dcpjzkdhsjfxtb(jint,zuot,qiant,shangz,pingj,shangy,shangm,max,min);
            store = createSyncStore({
                data : data.DATA
            });
            dcpjzkdhsjfxGrid.set('collection', store);
        },
    };


    var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
        Selection, ColumnResizer, Editor ]);
    dcpjzkdhsjfxGrid = new CustomGrid({
        columns : columns
    }, "dcpjzkdhsjfxtable");


//	dojo.xhrPost(xhrArgs);
});
//时间
function setformat(obj){
    var y=(obj.getFullYear()).toString();
    var m=(obj.getMonth()+1).toString();
    if(m.length==1){
        m="0"+m;
    }
    var d=obj.getDate().toString();
    if(d.length==1){
        d="0"+d;
    }
    var h = obj.getHours().toString();
    if(h.length==1){
        h="0"+h;
    }
    var mi = obj.getMinutes().toString();
    if(mi.length==1){
        mi="0"+mi;
    }
    var s = obj.getSeconds().toString();
    if(s.length==1){
        s="0"+s;
    }
    var time=y+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
    return time;
}
//时间
function setformat(obj){
    var y=(obj.getFullYear()).toString();
    var m=(obj.getMonth()+1).toString();
    if(m.length==1){
        m="0"+m;
    }
    var d=obj.getDate().toString();
    if(d.length==1){
        d="0"+d;
    }
    var h = obj.getHours().toString();
    if(h.length==1){
        h="0"+h;
    }
    var mi = obj.getMinutes().toString();
    if(mi.length==1){
        mi="0"+mi;
    }
    var s = obj.getSeconds().toString();
    if(s.length==1){
        s="0"+s;
    }
    var time=y+"-"+m+"-"+d;
    return time;
}
function dcpjzkdhsjfxtb(jint,zuot,qiant,shangz,pingj,shangy,shangm,max,min){
    var myChart = echarts.init(document.getElementById('dcpjzkdhsjfxtb'));
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
            data:['今天','昨天','前天','上周同比','上周平均','上月同比','上年同比','前半月最大','前半月最小']
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
                data : ['00:00', '01:00', '02:00',
                    '03:00', '04:00', '05:00', '06:00',
                    '07:00', '08:00', '09:00', '10:00',
                    '11:00', '12:00', '13:00', '14:00',
                    '15:00', '16:00', '17:00', '18:00',
                    '19:00', '20:00', '21:00', '22:00',
                    '23:00']
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
                name:'今天',
                type:'line',
                data:jint
            },{
                name:'昨天',
                type:'line',
                data:zuot
            },{
                name:'前天',
                type:'line',
                data:qiant
            },{
                name:'上周同比',
                type:'line',
                data:shangz
            },{
                name:'上周平均',
                type:'line',
                data:pingj
            },{
                name:'前半月最大',
                type:'line',
                data:max
            },{
                name:'前半月最小',
                type:'line',
                data:min
            },{
                name:'上月同比',
                type:'line',
                data:shangy
            },{
                name:'上年同比',
                type:'line',
                data:shangm
            }
        ]
    };

    echarts.init(document.getElementById('dcpjzkdhsjfxtb')).setOption(option);
}
function tjnr(list){
    var zhsz=[];
    for(var i=0;i<24;i++){
        if(list['y'+i]!=null){
            zhsz.push(list['y'+i]);
        }
    }
    return zhsz;
}