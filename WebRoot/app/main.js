/**
 * 
 */

var yjyaEditDialog=null,mapTabContainer,fstree=null;
require(["dijit/form/FilteringSelect","dijit/layout/TabContainer", "dijit/layout/ContentPane", "dojo/parser","dojo/on",'dojo/dom-class',"dojo/dom-style","dojo/domReady!"]
, function(FilteringSelect,TabContainer, ContentPane,parser,on,domClass,domStyle){
	parser.parse()
	
//	on(dojo.byId('zhpt_dtjg'),'click',function(){
//		dijit.byNode(dojo.byId('cpMainCenter')).set('href','app/html/dtjg.html')
//	});
//	on(dojo.byId('zhpt_yjzh'),'click',function(){
//		dijit.byNode(dojo.byId('cpMainCenter')).set('href','app/html/yjzh.html')
//	});
//	on(dojo.byId('zhpt_qyyygl'),'click',function(){
//		dijit.byNode(dojo.byId('cpMainCenter')).set('href','app/html/qyyygl.html')
//	});
	
	on(dojo.byId('btn_zhuye'),'click',function(){
		window.location.href="http://"+self.location.host+"/zhpt/demo2.html"
	});
	on(dojo.byId('btn_lb'),'click',function(){
		toggleSidebarWidth(dijit.byId('borderContainer'),dijit.byId('cpLeft'))
	});
	
	if(dojo.byId('btn_quit')){
		on(dojo.byId('btn_quit'),'click',function(){
			window.location.href="http://"+self.location.host+"/zhpt/demo.html"
		});
	}
	dojo.forEach(['btn_lb','btn_zhuye','btn_quit'],function(item,index){
		domStyle.set(item, { 'opacity': 0.9 });
		on(dojo.byId(item),'mouseover',function(){
			domStyle.set(this, { 'opacity': 1 });
		});
		on(dojo.byId(item),'mouseout',function(){
			domStyle.set(this, { 'opacity': 0.9 });
		});
		
		/*
		on(dojo.byId(item),'mouseover,mouseout',function(){
			domClass.toggle(this, 'divHover');
		});
		*/
	})
	
	function toggleSidebarWidth(appLayout, contentAccordion) {
	    var size = dojo.marginBox(contentAccordion.domNode);
	    if(size.w > 2) {
	        dojo.marginBox(contentAccordion.domNode, {w: 0});
	    } else {
	        dojo.marginBox(contentAccordion.domNode, {w: 200});
	    }
	    appLayout.resize();
	}
	
//	tc = new TabContainer({
//        style: "height: 100%; width: 100%;"
//    }, "tc1-prog");
//
//    var cp1 = new ContentPane({
//         title: "主页",
//         content: "主页面板，重要信息，通知，资讯"
//    });
//    tc.addChild(cp1);
//    tc.startup();
	dijit.form.FilteringSelect.prototype.autoComplete = false;
});

function getTabIndex(title){
	var aaa = tc.getChildren();
	var index = -1;
	for(var i=0;i<aaa.length;i++){
		if(aaa[i].params.title == title){
			index  = i;
		}
	}
	return index;
	
}

function getCurrentYear() {
    var date = new Date();
    return {"start":date.getFullYear() + "-01-01", "end": date.getFullYear() + "-12-31" }; 
}

function getCurrentMonth() {
    var date = new Date();
    return {"start":date.getFullYear()  + "-" + (date.getMonth() + 1) + "-01",
            "end": date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + getLastDay(date.getFullYear(), (date.getMonth() + 1)) 
}};

function getLastDay(year, month) {
    var new_year = year;     // 取当前的年份
    var new_month = month++; // 取下一个月的第一天，方便计算（最后一天不固定）
    if(month>12)             // 如果当前大于12月，则年份转到下一年
    {
        new_month -=12;      // 月份减
        new_year++;          // 年份增
    }
    var new_date = new Date(new_year,new_month,1);      // 取当年当月中的第一天
    return (new Date(new_date.getTime() - 1000 * 60 * 60 * 24)).getDate(); // 获取当月最后一天日期
}

/*
 * 
 * 
getOffsetMon(new Date('2015/1/3'),-1)
12
getOffsetMon(new Date('2015/1/3'),-2)
11
getOffsetMon(new Date('2015/1/3'),1)
2
 */
function getOffsetMon(date, offset) {
	var month = date.getMonth()+1;
    var new_month = month+offset;  
    if(new_month>12)             
    {
        new_month -=12;      
    }else if(new_month < 0)             
    {
        new_month +=12;      
    }else if(new_month ==0 ){
    	new_month = 12;
    }
    return new_month;
   
}

/**
 * 公司
 * @returns
 */
function queryGs(){
	var xhrArgs = {
			url : basePath + "common/findcomp",
			postData : 'postData={"page":1,"pageSize":9999}',
			handleAs : "json"
	};
	return dojo.xhrPost(xhrArgs);
}
/**
 * 查询所有车辆
 * @returns
 */
function queryVhic(){
	var xhrArgs = {
			url : basePath + "common/findVhicAll",
			handleAs : "json"
		};
	return dojo.xhrPost(xhrArgs);
}
/**
 * 车辆 下拉框
 * @returns
 */
function queryVhicById(compName){
	if(compName ==""){return}
	var xhrArgs = {
			url : basePath + "common/findvhic",
			postData : 'postData={"ownid":"'+compName+'"}',
			handleAs : "json"
		};
	return dojo.xhrPost(xhrArgs);
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
	var time=y+"-"+m+"-"+d+" "+h+":"+mi+":"+s; 
	return time;
}
function setformat1(obj){
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
function setformatday(obj){
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
//时间知道月份
function setformatmonth(obj){
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
	var time=y+"-"+m; 
	return time;
}
/**
 * 司机营业证 下拉框
 * @returns
 */
function queryCardnoById(compName){
	if(compName ==""){return}
	var card_stime=dijit.byId("sj_stime").value;
	var card_etime=dijit.byId("sj_etime").value;
	var xhrArgs = {
			url : basePath + "common/findcardno",
			postData : 'postData={"comp":"'+compName+'","stime":"'+card_stime+'","etime":"'+card_etime+'"}',
			handleAs : "json"
		};
	return dojo.xhrPost(xhrArgs);
}

function query_vhictj(compName,vhic){
	if(vhic.length<2){return};
	console.log(compName,vhic)
	var xhrArgs = {
			url : basePath + "common/hzclcx",
			postData : 'postData={"ownid":"'+compName+'","vehi_no":"'+vhic+'"}',
			handleAs : "json"
		};
	return dojo.xhrPost(xhrArgs);
}


/**
 * 数据中心
 */
/**
 * 公司
 * @returns
 */
function query_sjzx_Gs(){
	var xhrArgs = {
			url : basePath + "common/find_sjzx_comp",
			postData : 'postData={"page":1,"pageSize":9999}',
			handleAs : "json"
	};
	return dojo.xhrPost(xhrArgs);
}
/**
 * 查询所有车辆
 * @returns
 */
function query_sjzx_Vhic(){
	var xhrArgs = {
			url : basePath + "common/find_sjzx_VhicAll",
			handleAs : "json"
		};
	return dojo.xhrPost(xhrArgs);
}

/**
 * 查询所有车辆,模糊查询 
 * @returns
 */
function query_sjzx_Vhics(cphm){
	var xhrArgs = {
			url : basePath + "common/find_sjzx_Vhics?cphm="+cphm,
			handleAs : "json"
		};
	return dojo.xhrPost(xhrArgs);
}
/**
 * 车辆 下拉框
 * @returns
 */
function query_sjzx_VhicById(compName){
	if(compName ==""){return};
	var xhrArgs = {
			url : basePath + "common/find_sjzx_vhic",
			postData : 'postData={"ownid":"'+compName+'"}',
			handleAs : "json"
		};
	return dojo.xhrPost(xhrArgs);
}
/**
* 根据公司和车辆查询符合条件的车号
*/
function query_sjzx_vhictj(compName,vhic){
	if(vhic.length<2){return};
	var xhrArgs = {
			url : basePath + "common/sjzxclcx",
			postData : 'postData={"ownid":"'+compName+'","vehi_no":"'+vhic+'"}',
			handleAs : "json"
		};
	return dojo.xhrPost(xhrArgs);
}