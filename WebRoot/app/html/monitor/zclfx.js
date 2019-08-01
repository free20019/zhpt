var zclfxGrid = null;
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
    dc.place(dc.create("span",{innerHTML:"日期",style:{"margin-right":"5px"}}),'zclfxdiv');
    var zclfx_time= new TextBox({id:"zclfx_time",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})},style:{"width":"184px","margin-right":"15px"}}).placeAt('zclfxdiv');
    var queryCondition = {"time":zclfx_time};
    var zclfxbutton=new Button({
        label: "查询",
        onClick: function(){
            this.showWait();
            var postData = {"page":1,"pageSize":pageSize,"baid":0};
            for(var o in queryCondition){
                if(o == "zclfx_time"){
                    postData[o] = dojo.byId(queryCondition[o].id).value;
                }else{
                    postData[o] = queryCondition[o].value;
                }
            }
            console.log(postData)
            dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
        }
    }).placeAt('zclfxdiv');
    new Button({
        label : "导  出",
        onClick : function() {
            var postData = {
                "page" : 1,
                "pageSize" : 10000,
                "baid":0
            };
            for(var o in queryCondition){
                if(o == "zclfx_time"){
                    postData[o] = dojo.byId(queryCondition[o].id).value;
                }else{
                    postData[o] = queryCondition[o].value;
                }
            }
            url = "hygl/zclexcle?postData="
                + dojo.toJson(postData), window.open(url)
        }
    }).placeAt('zclfxdiv');
    //添加表格
    var columns = [
        {field:'message',
            label : "重车率"
        },{field:'y0',
            label : "0:00"
        },{field:'y1',
            label : "0:30"
        },{field:'y2',
            label : "1:00"
        },{field:'y3',
            label : "1:30"
        },{field:'y4',
            label : "2:00"
        },{field:'y5',
            label : "2:30"
        },{field:'y6',
            label : "3:00"
        },{field:'y7',
            label : "3:30"
        },{field:'y8',
            label : "4:00"
        },{field:'y9',
            label : "4:30"
        },{field:'y10',
            label : "5:00"
        },{field:'y11',
            label : "5:30"
        },{field:'y12',
            label : "6:00"
        },{field:'y13',
            label : "6:30"
        },{field:'y14',
            label : "7:00"
        },{field:'y15',
            label : "7:30"
        },{field:'y16',
            label : "8:00"
        },{field:'y17',
            label : "8:30"
        },{field:'y18',
            label : "9:00"
        },{field:'y19',
            label : "9:30"
        },{field:'y20',
            label : "10:00"
        },{field:'y21',
            label : "10:30"
        },{field:'y22',
            label : "11:00"
        },{field:'y23',
            label : "11:30"
        },{field:'y24',
            label : "12:00"
        },{field:'y25',
            label : "12:30"
        },{field:'y26',
            label : "13:00"
        },{field:'y27',
            label : "13:30"
        },{field:'y28',
            label : "14:00"
        },{field:'y29',
            label : "14:30"
        },{field:'y30',
            label : "15:00"
        },{field:'y31',
            label : "15:30"
        },{field:'y32',
            label : "16:00"
        },{field:'y33',
            label : "16:30"
        },{field:'y34',
            label : "17:00"
        },{field:'y35',
            label : "17:30"
        },{field:'y36',
            label : "18:00"
        },{field:'y37',
            label : "18:30"
        },{field:'y38',
            label : "19:00"
        },{field:'y39',
            label : "19:30"
        },{field:'y40',
            label : "20:00"
        },{field:'y41',
            label : "20:30"
        },{field:'y42',
            label : "21:00"
        },{field:'y43',
            label : "21:30"
        },{field:'y44',
            label : "22:00"
        },{field:'y45',
            label : "22:30"
        },{field:'y46',
            label : "23:00"
        },{field:'y47',
            label : "23:30"
        }
    ];



    //查询按钮
    var xhrArgs = {
        url : basePath + "hygl/zcl",
        postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
        handleAs : "json",
        load : function(data) {
            zclfxbutton.hideWait();
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
            console.log(jint)
            console.log(pingj)
            zclfxtb(jint,zuot,qiant,shangz,pingj,shangy,shangm,max,min);
            store = createSyncStore({
                data : data.DATA
            });
            zclfxGrid.set('collection', store);
        },
    };


    var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,
        Selection, ColumnResizer, Editor ]);
    zclfxGrid = new CustomGrid({
        columns : columns
    }, "zclfxtable");


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
    var time=y+"-"+m+"-"+d;
    return time;
}
function zclfxtb(jint,zuot,qiant,shangz,pingj,shangy,shangm,max,min){
    var myChart = echarts.init(document.getElementById('zclfxtb'));
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
                data : ['00:00', '00:30', '01:00', '01:30','02:00','02:30','03:00', '03:30','04:00','04:30', '05:00','05:30', '06:00','06:30',
                    '07:00', '07:30','08:00', '08:30','09:00','09:30', '10:00','10:30','11:00', '11:30','12:00','12:30', '13:00','13:30', '14:00','14:30',
                    '15:00','15:30', '16:00','16:30', '17:00','17:30', '18:00','18:30','19:00','19:30', '20:00','20:30', '21:00', '21:30','22:00','22:30',
                    '23:00' ,'23:30']
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

    echarts.init(document.getElementById('zclfxtb')).setOption(option);
}
function tjnr(list){
    var zhsz=[];
    for(var i=0;i<48;i++){
        if(list['y'+i]!=null){
            zhsz.push(list['y'+i].substring(0,list['y'+i].indexOf("%")));
        }
    }
    return zhsz;
}