table_yjfx_temp__ = null;
window.console = window.console || (function(){ 
var c = {}; c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function(){}; 
return c; 
})(); 

var globalQueryCondition = '';	//好多查询条件
var globalSsjg = 1;//运管 ，2，公路，3：港航
var page = 1 ;
var pageSize=10;
var currentPagination = null;
var currentExcelOptYearMon ;
$.ajaxSetup({cache:false,contentType: "application/x-www-form-urlencoded; charset=utf-8",async: false});
var xxwh_li_list = {
        "list": [{"name":"司机信息"},{"name":"业户信息"},{"name":"车辆信息"}]
};
var cssz_li_list = {
        "list": [{"name":"油价设置"},{"name":"油耗设置"},{"name":"基准价设置"}]
};
var xxcx_li_list = {
        "list": [{"name":"司机异动"},{"name":"车辆异动"},{"name":"业户异动"}]
};
var ryby_li_list = {
        "list": [{"name":"营运班次录入"},{"name":"营运班次"},{"name":"每日详细补贴"},{"name":"每月详细补贴"},{"name":"燃油补贴"}]
};

//<<
var excel;
var vehicles,drivers = [];
var cacheExcelData = null;
driver = ["赵同辉","陈志明","李林锋","傅小龙","宋  军","郑财良","鲁卫东","吕忠孝","王忠民","陈伟根","冯  胜","周樟龙","戴国利","时红民","唐启庆","郑仁根","张和平","韩文超","卢财振","臧淦余","陈  超","王国泉","孙中源","张亚飞","赵世军","张卫彪","麦征","贺敏强","陈迪庆","徐立万","张更雷","周晓斌","陈明堂","朱  航","李  铮","庄建平","杨世坤","秦  广","赵  波","周洪海","屠世潮","亓鸣飞","徐武兴","叶来元","吴林云","徐伟胜","许民权","张泰来","方流胜","杨建锐","贾振华","张连军","徐  杭","朱洪才","陈俊勇","袁亚东","傅振波","刘学斌","娄国强","徐  峰","徐卫兴","陈信","刘世雄","张守坤","马瑞飚","金勇华","赵  懿","冯红雨","应志宏","昝延东","于  侠","胡  波","倪锦松","程立新","刘小勇","翟哲明","安  辉","黎华绍","祝连富","田宝林","金连升","冯  磊","巫伟平","蒋铁山","陈  伟","沈海峰","叶  华","巫建新","何福禄","李春祥","胡建春","程晓红","杨小珍","黄政如","陈喜云","高玉民","沈继杭","吴跃富","鲁小明","孙惠林","张汉定","吴志寿","林  龙","张耀华","柴  丞","邹晓红","吴时鸿","王海民","邵建明","章  正","陈国祥","石俊辉","郭金宝","张清华","凌建新","陈宝根","韩志强","柯善珊","杨长盛","方洪强","庄伟根","陈正强","李  强","朱红艳","肖  峰","沈  琪","杨红俊","杨建平","徐伟民","陈惠荣","陈春根","宋  琦","王顺德","方丽军","陈潇吉","寿亚平","张荣生","朱国林","茅丹伏","魏建庄","盛  任","滕承武","章  伟","庞建卫","任  松","杜金林","戚新春","周志国","计建平","白井涛","张  烈","陈来法","徐  航","朱劲松","沈国凌","杨佐江","蔡兰付","项樟华","朱金荣","徐廷建","潘永平","李继新","周  卫","单冬寿","张建明","姚洪民","徐玉祥","周建军","袁德法","廖伟明","徐远建","曾长青","梁文伟","朱树屏","池泳涛","姚建良","黄永祥","孟小军","张国伟","夏  炎","何国华","王晓波","周勇坚","甘  强","毕朝阳","杨细军","郑卫红","蒋  兵","沈  坚","傅红辉","曹幸福","宋炳良","朱建国","边国强","汪金聚","蒋惠红","宁善法","王海力","黄国良","金黎明","桑会军","宋  伟","陈立忠","张定法","陈  克","汪珍娟","陈越胜","张广运","倪  晶","白国贤","郑红萍","汪其林","肖春全","邵伟彬","宋海民","王国志","吴国强","孟凡炬","陈  宏","汪国华","俞肖华","姜甘民","李三星","施申余","盛大浩","孟  伟","吴泽敏","刘  军","范娟琴","付梅龙","邵建荣","王  民","黄  亮","丁  洪","胡旭明","詹文祥","周轩更","陈锡培","吁财高","周  明","赵青气","郑仁跃","丁韶勇","沈月祥","陈建平","何明达","王  杰","张惠菊","苏水旺","陈  华","安德兵","邱民华","龚丽英","盛立军","王  勇","潘  胜","席洪超","柴树成","金纪良","庄  伟","王卫东","钱大园","潘柏军","陈冬琴","张  涛","陈智强","郑友党","王建明","张建华","蔡其丰","马永财","杨顺法","邹国忠","张宝根","周杭德","周兴妹","洪再华","谭金平","刘小强","汪文军","吴海斌","程旺通","曹永祥","秦心灵","钱水理","周松军","徐丽娜","夏炳辉","孙建成","张成波","鲍秋平","董顺华","钱红峰","寿建妹","孙文静","汤世荣","李作能","李国彬","陈冠权","徐述明","宋文伟","方旺平","赵长龙","张  杰","蒋伟荣","李军军","汪水林","姚舜华","蔡  晓","严国良","王勇明","周  亮","董伟成","孙亦超","吴永坚","龚方伟","顾洪高","张永春","徐永明","姜向平","沈天军","蒋  勇","王  澎","俞波尔","钱海豹","曾金龙","来勇明","何伟强","罗  忠","陈维","张宁","蒋海平","熊冬洪","张心和","邵宏伟","沈忠华","徐淑楷","王  强","陈志勇","岳  峰","赵春生","沈  健","陈  明","屠方永","戴银娟","密妙生","俞军伟","周金华","胡永建","周杭州","严建平","徐名金","陈小生","战寿斌","余  钧","洪潘海","章建峰","张海军","周学军","朱张林","许建军","金宝发","张冬涵","夏宇峰","张建平","程中桃","姚永炳","鲁华锋","陈大伟","翁建伟","赖寿松","孙家勤","舒水均","沈田园","吴宇峰","何立华","叶友明","田俊锋","唐伟国","潘明军","蒋惠祥","杨  磊","穆红光","凌跃辉","冯关永","关  渊","陈  杰","彭顺喜","陈铮雄","吴志亮","陈荣华","宋秀龙","葛云龙","贾绍斌","张立春","孔华军","李玉书","林立新","李长江","盛建伟","王震球","陈以国","杜以刚","那宏宇","刘  锋","陈玉祥","王海涛","蒋保华","方华安","方晖北","李炳华","吴有顺","徐  江","谢文华","陆  奎","吕卫忠","胡塬威","束  平","赵子鸿","郑金强","孙继春","夏建卫","蒋洪国","王伟","沈伟锋","陈炳有","葛宝源","隽建华","孙丽红","陆国焕","姬荣君","胡水明","郎振国","童晓春","张建生","沈国明","汪祖明","曾步雨","翟卫青","王学武","罗冯勇","俞智奇","冯超杰","杨大明","吴明春","楼宝国","沈利兴","朱向涛","邬  涛","陈  一","瞿志达","汪  毅","杨裘梁","陈琴娥","叶红斌","张加寒","陈柏青","梁明亮","鲍国钧","王小首","王锡林","吴小杰","俞江华","姚春兴","程俊敏","吕伟华","王  鹰","王儒昌","马德法","袁国良","任元林","施伟华","程  高","王建国","史晓琳","李成胜","马勤汉","张  旭","袁法其","蔚普涛","化红伟","章朝晖","闻勇军","王建国","林国良","张瑞平","来跃荣","朱应龙","周祝刚","汪际良","刘广亮","吴  青","应建强","潘建平","彭健","都云德","陈  平","王宝根","郝正伟","李  萍","陈广顺","赵宏杰","白玉坤","周志红","余晓锋","郑玉荣","郭志勇","沈洁峰","周建平","周  强","章宏根","陆有水","肖  军","钱  斌","方  勇","谢啸俊","刘新营","陈  斌","盛东渭","樊晓峰","王  跃","殷文龙","蒋宝清","冯子华","杨建生","杨刚","寿  麟","孔松伟","史建康","何利军","竺  明","陈  亚","金煌","王国荣","唐传飞","金云晓","郑玉良","刘智伟","李学军","邢王晨","柏争清","徐雁洲","陈  玮","周永成","解成龙","徐华坤","冯建明","徐  明","徐赵华","徐连福","莫丽娟","叶关富","沈鹤鸣","张如民","白征宇","冯连兴","洪永祥","胡  冈","高尔林","朱勇祥","朱玉超","陈献平","陈文","赵立春","陈建平","宋慧勇","李现振","冯为荣","张敬丰","宋文强","吴  军","徐  进","张根法","郑志祥","高金燕","沈苏杭","王兴华","刘元兔","董雄壮","程红鸣","贺培林","余钟平","方建平","鞠建国","刘  彤","韩家麟","李志根","王锋林","胡建红","于宏波","程建国","周卫国","黄佩华","马金民","赵永华","王学敏","李伟民","王卫东","倪德根","郑有林","方建军","钱富山","吴锡林","周柏荣","陆勇毅","张忠伟","姚四青","林英良","朱  强","陈芳","叶李琦","陈出新","陈智民","毛  坚","张孝文","袁文贵","周伯建","陈三九","章  兵","林建业","范诗君","王  华","余扬金","徐  虹","戴国林","邬杭玲","楼浩云","郭  云","郑济名","赵国华","王国良","廖桂根","徐志嵩","许贵彬","张广杰","傅桂明","傅  军","祝华巍","李鹤旦"];
vehicles = ['浙A00001','浙A00002','浙A00003','浙A00004'];
function mergeCellRow(instance, td, row, col, prop, value, cellProperties) {
         Handsontable.TextCell.renderer.apply(this, arguments);
         if(cellProperties.flag == 2){
         	//td.style.display = 'none';
         }else{
	        // td.setAttribute("rowspan", 2);
//	         var value = vehicles[Math.round(row/2)-1];
//	         if(value){
//	        	 td.innerHTML = value;
//	         }
         }
}

function mergeCellCol(instance, td, row, col, prop, value, cellProperties) {
         Handsontable.TextCell.renderer.apply(this, arguments);
         td.style.height = '50px';
         if(cellProperties.flag == 2){
//         	td.style.display = 'none';
         }else{
//         	 td.setAttribute("colspan", 2);
//	         td.innerHTML = Math.round(col/2)+'日 <br>司机、比例';
         }
}

function genExcel(mon){
	$('#mainSplitterRightBottomTable').empty();
	var excelWrap = $('<div id=excelWrap></div>');
	var excelContainer = $('<div id=excelContainer style="width:99%;overflow:auto"></div>')
	excelContainer.append(excelWrap);
	$('#mainSplitterRightBottomTable').append(excelContainer);
	
	var year = $('#cp_bclr_year').val();
	currentExcelOptYearMon = ''+year+","+mon+'';
	$('#mainSplitterRightBottomPagination').empty();
	if(excel && excel.container){
		excel.destroy();
	}
	
	var mergeCellsCols = [];
	var mergeCellsRows = [];
	var mergeCells = [];
	var startCols = 61;
	switch (parseInt(mon)) {
	case 1:
	case 3:	
	case 5:	
	case 7:	
	case 8:	
	case 10:	
	case 12:	
		startCols = 63
		break;
	case 4:
	case 6:	
	case 9:	
	case 11:	
		startCols = 61
		break;
	case 2:	
		startCols = 57
		break;
	default:
		break;
	}
	
	for(var i =1; i< startCols ; i++){
		mergeCellsCols.push({row: 0, col: i++, rowspan: 1, colspan: 2})
	}
	for(var i =1; i<= vehicles.length ; i++){
		mergeCellsRows.push({row: (i*2)-1, col: 0, rowspan: 2, colspan: 1})
	}
	mergeCells = mergeCellsCols.concat(mergeCellsRows);
	
	var data = [];
	for(var i =0; i<= vehicles.length*2 ; i++){
		var colDatas = [];
		for(var j =0; j< startCols ; j++){
			if(j==0){
				if(i>0){
					if(i%2==0){
						colDatas.push(null);
					}else{
						colDatas.push(vehicles[Math.round(i/2)-1]);
					}
				}else{
					colDatas.push(null);
				}
			}else if(j>0){
				if(i==0){
					colDatas.push(Math.round(j/2)+'日\n司机、比例');
				}else{
					colDatas.push(null);
				}
			}
		}
		data.push(colDatas);
	}
	
	//列
//	var columns = [];
//	for(var i =1; i< startCols ; i++){
//		if(i%2==1){
//			columns.push({"type":"autocomplete",source:driver})
//		}
//	}
	$(excelWrap).handsontable({
	  startCols:startCols
	 // ,startRows:51 指定data时无效
	  ,minSpareRows : 4
	  ,data:data
	  //,maxCols:11 只有11列可编辑，超过不可录入
	  //,fillHandle: false 填充
	  ,startRows : vehicles.length*2+1
	 // minSpareRows: 1,
	  ,colHeaders: true//加abcde..列头
	 // ,rowHeaders: true 123456..
	  ,contextMenu: true
	  //,rowHeaders:[1,2]
	  //,mergeCells:true //为true时右击才会有合并的菜单
	 // fixedRowsTop:1,
	 // fixedColumnsLeft :1
	  // readOnly: true,
	 // colHeaders :[1,2],
	 ,className :'htCenter htMiddle'
	 ,readOnly : false
	 ,colWidths : 90
	 //,width:1700
	 ,mergeCells:mergeCells
//	 ,columns:columns
	 ,cells: cellsCallback
	});
	excel = $(excelWrap).handsontable('getInstance');
	if(cacheExcelData){
		excel.loadData(cacheExcelData);
	}
}
function cellsCallback (row, col, prop) {
    var cellProperties = { };
    cellProperties.readOnly = false;                   
//    if (col == 0 && row % 2 === 1) {
//   	 	  cellProperties.readOnly = false;
//          cellProperties.renderer = mergeCellRow; 
//    }else if (col == 0 && row % 2 === 0 && row > 1) {
//    	  console.log(col+','+row+','+prop);
//          cellProperties.flag = 2;//偶数
//   	 	  cellProperties.readOnly = false;
//          cellProperties.renderer = mergeCellRow; 
//    }else  if (col > 0 && row   === 0) {
//    	cellProperties.readOnly = false;
//    	if(col%2==0){
//    		cellProperties.flag = 2;//偶数
//    	}
//        cellProperties.renderer = mergeCellCol; 
//    }else 
    	if(col >=1 && col%2==1 && row > 0){
    	  cellProperties.type= 'autocomplete';	
	      cellProperties.source= driver;
	      cellProperties.strict= true;
    }else if(col >=1 && col%2==0 && row > 0){
  	  		cellProperties.type= 'numeric';	
    }
    return cellProperties;
}
function excelLoad(){
	cacheExcelData = null;
	$('#mainSplitterRightBottomPagination').empty();
	var year = $('#cp_bclr_year').val();
	var mon = $('#cp_bclr_mon').val();
	currentExcelOptYearMon = ''+year+","+mon+'';
	if(year==undefined || mon ==undefined){
		console.log('时间为空');
		return;
	}
	var postData= 'postData={"year":"'+year+'","mon":"'+mon+'"}';
	$.ajax({
		type:"POST",
		dom : this.dom,
		data:postData,
		pagination:this,
		async : true,
		config:this.config,
		beforeSend: function () {
           // ShowDiv();
           // alert(1);
        },
        complete: function () {
          // HiddenDiv();
            //alert(2);
        },
        url:encodeURI('bcb/getExcel'),
        success:function(data){ 
//           console.log(data);
           var result =  eval('('+data+')');
           console.log(result);
          // JSON.parse(result.vehicles);
           //JSON.parse(result.data);
           genExcel(mon);
           if(excel && excel.container){
        	   
        	   if(result.data){
        		   var datas = JSON.parse(result.data);
        		   if(datas.length > 0){
        			   excel.loadData(datas);
        		   }else{
        			   excel.clear();
        		   }
        	   }
       	}
        }
    });
}

function delExcel(){
	var year =$(cp_bclr_year).val();
	var mon = $(cp_bclr_mon).val();
	var yearmon =  ''+year+","+mon+'';
	if(window.confirm( '确认删除 ' +year+'年'+mon+'月 班次数据')){
		var postData= 'postData={"year":"'+year+'","mon":"'+mon+'"}';
		$.ajax({
			type:"POST",
			data:postData,
			async : true,
			beforeSend: function () {
	            ShowDiv();
	        },
	        complete: function () {
	           HiddenDiv();
	        },
	        url:encodeURI('bcb/delExcel'),
	        success:function(data){ 
	           var result =  eval('('+data+')');
	           if(currentExcelOptYearMon == yearmon && excel){
	        	   excel.destroy();
	        	   cacheExcelData = null;
	        	   genExcel(mon);
	           }
	           alert(result.msg);
	        }
	    });
	}
}
function excelSave(){
	$('#mainSplitterRightBottomPagination').empty();
	var year = $('#cp_bclr_year').val();
	var mon = $('#cp_bclr_mon').val();
	currentExcelOptYearMon = ''+year+","+mon+'';
	if(year==undefined || mon ==undefined){
		console.log('时间为空');
		return;
	}
	var postData= 'postData={"excel":'+JSON.stringify(excel.getData())+',"vehicles":'+JSON.stringify(vehicles)+',"year":"'+year+'","mon":"'+mon+'"}';
	
	$.ajax({
		type:"POST",
		dom : this.dom,
		data:postData,
		pagination:this,
		async : true,
		config:this.config,
		beforeSend: function () {
           // ShowDiv();
           // alert(1);
        },
        complete: function () {
          // HiddenDiv();
            //alert(2);
        },
        url:encodeURI('bcb/saveExcel'),
        success:function(data){ 
//           console.log(data);
           var result =  eval('('+data+')');
           excelSaveBtjs();
           alert(result.msg);
        }
    });
}

function btjs(){
	$.ajax({
		type:"GET",
		dom : this.dom,
		async : true,
        url:encodeURI('common/btjs'),
        success:function(data){ 
           var result =  eval('('+data+')');
         //  alert(result.msg);
        }
    });
}
//>>

function saveYj(stime,etime,price){
var postData= 'postData={"startTime":"'+stime+'","endTime":"'+etime+'","price":"'+price+'"}';
	
	$.ajax({
		type:"POST",
		data:postData,
		async : true,
        url:encodeURI('yj/save'),
        success:function(data){ 
           var result =  eval('('+data+')');
           console.log(result);
           $('#editYj').jqxWindow('destroy');
           $($('#mainSplitterLeft_ul .li2:contains("油价设置")')[0]).click();
        }
    });
}

function delYj(id){
	var postData= 'postData={"id":"'+id+'"}';
		
		$.ajax({
			type:"POST",
			data:postData,
			async : true,
	        url:encodeURI('yj/del'),
	        success:function(data){ 
	           var result =  eval('('+data+')');
	           console.log(result);
	           $($('#mainSplitterLeft_ul .li2:contains("油价设置")')[0]).click();
	        }
	    });
}

function saveBtjzj(stime,etime,price){
	var postData= 'postData={"startTime":"'+stime+'","endTime":"'+etime+'","price":"'+price+'"}';
		
		$.ajax({
			type:"POST",
			data:postData,
			async : true,
	        url:encodeURI('btjzj/save'),
	        success:function(data){ 
	           var result =  eval('('+data+')');
	           console.log(result);
	           $('#editBtjzj').jqxWindow('destroy');
	           $($('#mainSplitterLeft_ul .li2:contains("基准价设置")')[0]).click();
	        }
	    });
}
function delBtjzj(id){
		var postData= 'postData={"id":"'+id+'"}';
			$.ajax({
				type:"POST",
				data:postData,
				async : true,
		        url:encodeURI('btjzj/del'),
		        success:function(data){ 
		           var result =  eval('('+data+')');
		           console.log(result);
		           $($('#mainSplitterLeft_ul .li2:contains("基准价设置")')[0]).click();
		        }
		    });
}
	
function getClsj(){
	
	$.ajax({
		type:"POST",
		pagination:this,
		async : true,
		config:this.config,
		beforeSend: function () {
           // ShowDiv();
           // alert(1);
        },
        complete: function () {
          // HiddenDiv();
            //alert(2);
        },
        url:encodeURI('common/getclsj'),
        success:function(data){ 
//           console.log(data);
           var result =  eval('('+data+')');
           alert(result);
        }
    });
}

function enter(menu){
	console.log(menu);
	var panel = '';
	//location.href = 'index.jsp'
	if(menu=='管理'){
		panel = 'mainSplitter';
	}
	showPanel(panel)
	$('#'+panel).jqxSplitter({
		width : '100%',
		height : '94%',
		panels : [ {
			size : '15%'
		}, {
			min : '83%'
		}]
	});
}

function showPanel(panel){
	$([/*portalWarper,*/mainSplitter]).each(function(index,item){
		$(item).hide();
	})
	hidePanel_yjfx();//打开1级，关闭所有2级
	$('#'+panel).show();
}

//level 2
function showPanel_yjfx(panel){
	$([yjfx_index_div,yjfx_zfzl_div,yjfx_yg_div,yjfx_gl_div,yjfx_gh_div]).each(function(index,item){
		$(item).hide();
	})
	$('#'+panel).show();
}

//level 2
function hidePanel_yjfx(){
//	$([yjfx_index_div,yjfx_zfzl_div,yjfx_yg_div,yjfx_gl_div,yjfx_gh_div]).each(function(index,item){
//		$(item).hide();
//	})
}

/*
function intoDetail(flag,flag2){
	//
	var currentYear = getCurrentYear();
	var currentMonth = getCurrentMonth();
	if(flag2 == 11 || flag2 == 21){
		$(cp_jhAjxxb_stime).val('');
		$(cp_jhAjxxb_etime).val('');
	}else if(flag2 == 12 || flag2 == 22){
		$(cp_jhAjxxb_stime).val(currentYear.start);
		$(cp_jhAjxxb_etime).val(currentYear.end);
	}else if(flag2 == 13 || flag2 == 23){
		$(cp_jhAjxxb_stime).val(currentMonth.start);
		$(cp_jhAjxxb_etime).val(currentMonth.end);
	}
	if(flag2 == 21 || flag2 == 22 || flag2 == 23 ){
		$(cp_jhAjxxb_jcd).val('cs90');
	}else{
		$(cp_jhAjxxb_jcd).val('');
	}
	
	globalSsjg = flag;
	
	//
	configJhEnforcecases.temp_parameter='"ssjg":'+flag;
	
	//显示交换调整
	panel = 'mainSplitter2';
	showCqPanel2('cp_jhAjxxb');
	showPanel(panel)
	$('#'+panel).jqxSplitter({
		width : '100%',
		height : '94%',
		panels : [ {
			size : '18%'
		}, {
			min : '80%'
		}]
	});
	
	//定位item 
	var item = $($('#li1_'+flag+'_text li')[0])[0]
	if($(item.parentElement).css('display')=='none'){
		$('#mainSplitterLeft2_ul div[id^="li1_"]').hide();
		$(item.parentElement).show()
	}
	$('#mainSplitterLeft2_ul li').css('background','#E5E7E9').css('color','#000');
	$(item).css('background','#2ABBA3').css('color','#fff');
	
	$(cx_1000).click();
}
*/
function intoDetail(flag,flag2){
	//
	initTree1_();
	var currentYear = getCurrentYear();
	var currentMonth = getCurrentMonth();
	if(flag2 == 11 || flag2 == 21){
		if(flag ==1 ){
			$(cp_ajxx_cjrq_stime).val('2012-01-01');$(cp_ajxx_cjrq_etime).val(currentMonth.end);
			$(cp_ajxx_kg_cjrq_stime).val('');$(cp_ajxx_kg_cjrq_etime).val(currentMonth.end);
		}else if(flag ==2){
			$(cp_lzcf_lrsj_stime).val('2012-01-01');
			$(cp_lzcf_lrsj_etime).val(currentMonth.end);
		}else if(flag ==3){
			$(cp_ghajxx_larq_stime).val('2012-01-01');$(cp_ghajxx_larq_etime).val(currentMonth.end);
		}
	}else if(flag2 == 12 || flag2 == 22){
			if(flag ==1 ){
				$(cp_ajxx_cjrq_stime).val(currentYear.start);
				$(cp_ajxx_cjrq_etime).val(currentYear.end);
				
				$(cp_ajxx_kg_cjrq_stime).val(currentYear.start);
				$(cp_ajxx_kg_cjrq_etime).val(currentYear.end);
			}else if(flag ==2){
				$(cp_lzcf_lrsj_stime).val(currentYear.start);
				$(cp_lzcf_lrsj_etime).val(currentYear.end);
			}else if(flag ==3){
				$(cp_ghajxx_larq_stime).val(currentYear.start);
				$(cp_ghajxx_larq_etime).val(currentYear.end);
			}
		
	}else if(flag2 == 13 || flag2 == 23){
		if(flag ==1 ){
			$(cp_ajxx_cjrq_stime).val(currentMonth.start);
			$(cp_ajxx_cjrq_etime).val(currentMonth.end);
			$(cp_ajxx_kg_cjrq_stime).val(currentMonth.start);
			$(cp_ajxx_kg_cjrq_etime).val(currentMonth.end);
		}else if(flag ==2){
			$(cp_lzcf_lrsj_stime).val(currentMonth.start);
			$(cp_lzcf_lrsj_etime).val(currentMonth.end);
		}else if(flag ==3){
			$(cp_ghajxx_larq_stime).val(currentMonth.start);
			$(cp_ghajxx_larq_etime).val(currentMonth.end);
		}
	}
	
	
	globalSsjg = flag;
	
	//
	configJhEnforcecases.temp_parameter='"ssjg":'+flag;


	
	panel = 'mainSplitter';
	$('#'+panel).jqxSplitter({
		width : '100%',
		height : '94%',
		panels : [ {
			size : '8%'
		}, {
			min : '90%'
		}]
	});
//	$(cx_1000).click();
	var items = $('#mainSplitterLeft1_ul li')
	if(items.length == 4){
		if(flag==1){
			showCqPanel2('cp_ajxx');
			$(items[0]).click();
		}else if(flag ==2 ){
			showCqPanel2('cp_lzcf');
			$(items[2]).click();
		}else if(flag ==3 ){
			showCqPanel2('cp_ghajxx');
			$(items[3]).click();
		}
	}
	//显示合并后的数据
	showPanel(panel)
}


function cx(flag){
	globalQueryCondition = '';
	if(flag == 1){
		if($(yjfx_yg_div_startTime).val() != ''){
			globalQueryCondition += '"startTime":"'+$(yjfx_yg_div_startTime).val()+'",'
		}
		if($(yjfx_yg_div_endTime).val() != ''){
			globalQueryCondition += '"endTime":"'+$(yjfx_yg_div_endTime).val()+'",'
		}
		console.log('#####'+globalQueryCondition);
		initYjfx2(32)
	}else if(flag == 2){
		if($(yjfx_gl_div_startTime).val() != ''){
			globalQueryCondition += '"startTime":"'+$(yjfx_gl_div_startTime).val()+'",'
		}
		if($(yjfx_gl_div_endTime).val() != ''){
			globalQueryCondition += '"endTime":"'+$(yjfx_gl_div_endTime).val()+'",'
		}
		console.log('#####'+globalQueryCondition);
		initYjfx2(38)
	}else if(flag == 3){
		if($(yjfx_gh_div_startTime).val() != ''){
			globalQueryCondition += '"startTime":"'+$(yjfx_gh_div_startTime).val()+'",'
		}
		if($(yjfx_gh_div_endTime).val() != ''){
			globalQueryCondition += '"endTime":"'+$(yjfx_gh_div_endTime).val()+'",'
		}
		initYjfx2(4)
		console.log('#####'+globalQueryCondition);
	}
}

function cxzfzl(){
	
	console.log('#####cxzfzl');
	initYjfxZfzl();
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


/*
 *
var time1 = new Date().format("yyyy-MM-dd HH:mm:ss");     
var time2 = new Date().format("yyyy-MM-dd");   
 */
Date.prototype.format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}