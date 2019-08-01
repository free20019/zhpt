$(document).ready(function() {
	globalConditionQuery = new ConditionQuery().init();
	initTree1();
	enter('管理');
	$($('#mainSplitterLeft_ul .li2:contains("司机信息")')[0]).click();
});

function initTree1() {
	var myTemplate1 = Handlebars.compile($("#li1_1_text_template").html());
	var myTemplate2 = Handlebars.compile($("#li1_2_text_template").html());
	var myTemplate3 = Handlebars.compile($("#li1_3_text_template").html());
	var myTemplate4 = Handlebars.compile($("#li1_4_text_template").html());
	
	$('#mainSplitterLeft_ul')
	.append(myTemplate1(xxwh_li_list))
	.append(myTemplate2(cssz_li_list))
	.append(myTemplate3(xxcx_li_list))
	.append(myTemplate4(ryby_li_list));


	$('#mainSplitterLeft_ul .li1').each(function(index,item){
		$(item).bind('click',function(){
			$('#mainSplitterLeft_ul li').css('background','#E5E7E9').css('color','#000')
			$(item).css('background','#2ABBA3').css('color','#fff')
			
			var div = $('#mainSplitterLeft_ul div[id^="li1_'+(index+1)+'"]');
			globalSsjg = index+1;
			if(div && div.length >0 ){
				div = $(div[0])
			}else{
				return;
			}
			
			if(div.css('display')=='block'){
				$('#mainSplitterLeft_ul div[id^="li1_"]').hide();
			}else{
				$('#mainSplitterLeft_ul div[id^="li1_"]').hide();
				$('#mainSplitterLeft_ul div[id^="li1_'+(index+1)+'"]').fadeIn(200);
			}
		})
	})
	
	$('#mainSplitterLeft_ul .li2').each(function(index,item){
		$(item).bind('click',function(){
			if($(item.parentElement).css('display')=='none'){
				$('#mainSplitterLeft_ul div[id^="li1_"]').hide();
				$(item.parentElement).show()
			}
			globalQueryCondition = '';
			if(excel && excel.container){
				cacheExcelData = excel.getData();
			}
			$('#mainSplitterLeft_ul li').css('background','#E5E7E9').css('color','#000')
			$(item).css('background','#2ABBA3').css('color','#fff')
			var config = null;
			if($.trim($(item).text()) == '司机信息'){
				showCqPanel('cp_driver');
				config = configDriver;
			}else if($.trim($(item).text()) == '业户信息'){
				showCqPanel('cp_users');
				config = configUsers ;
			}else if($.trim($(item).text()) == '车辆信息'){
				showCqPanel('cp_vehicle');
				config = configVehicle ;
			}else if($.trim($(item).text()) == '油价设置'){
				showCqPanel('cp_yj');
				config = configYj ;
			}else if($.trim($(item).text()) == '油耗设置'){
				showCqPanel('cp_yh');
				config = configYh ;
			}else if($.trim($(item).text()) == '基准价设置'){
				showCqPanel('cp_btjzj');
				config = configBtjzj ;
			}else if($.trim($(item).text()) == '司机异动'){
				showCqPanel('cp_driveryd');
				config = configDriveryd ;
			}else if($.trim($(item).text()) == '业户异动'){
				showCqPanel('cp_usersyd');
				config = configUsersyd ;
			}else if($.trim($(item).text()) == '车辆异动'){
				showCqPanel('cp_vehicleyd');
				config = configVehicleyd ;
			}else if($.trim($(item).text()) == '营运班次录入'){
				showCqPanel('cp_bclr');
				genExcel(getOffsetMon(new Date(),-1));
			}else if($.trim($(item).text()) == '营运班次'){
				showCqPanel('cp_bcb');
				config = configBcb;
			}else if($.trim($(item).text()) == '燃油补贴'){
				showCqPanel('cp_bt');
				config = configBt ;
			}else if($.trim($(item).text()) == '每日详细补贴'){
				showCqPanel('cp_btjs');
				config = configBtjs ;
			}else if($.trim($(item).text()) == '每月详细补贴'){
				showCqPanel('cp_btjsmon');
				config = configBtjsmon ;
			}
			if(config != null){
				//config.temp_parameter='"ssjg":'+globalSsjg;
				currentPagination = new Pagination(page,pageSize,config);
				currentPagination.first()
			}
		})
	})
	
};





function PanelXq(title,json,callback){
	this.title = title;
	this.json = json;
	this.initDom = function(){
		
	}
	this.render =function(){
		callback(this);
	}
}


//查询条件面板展现  案源
function showCqPanel(panel){
	$([cp_driver,cp_users,cp_vehicle,cp_yj,cp_yh,cp_btjzj  ,cp_usersyd,cp_vehicleyd,cp_driveryd,cp_bcb,cp_bt,cp_bclr,cp_btjs,cp_btjsmon ]).each(function(index,item){
		$(item).hide();
	})
	$('#'+panel).show();
}


function ShowDiv(){
	HiddenDiv();
	var waitDiv = '<div id="wait"><span class=wait_gif/><span style="margin-left: 28px;">请等待...</span></div>'
	$(window.top.document.body).append($(waitDiv)); 
}

function HiddenDiv(){
	$(window.top.document.body).find('#wait').remove();
}

function quriqi(year,month){
	   var sys=""
	   for(var i=0;i<12;i++){
	   
		   if(month==0){
		   month=12
		   year--
		   }
		   var ss=month+"月"
		   sys=sys+(i==0?"":",")+ss
		   month--
	   }
	      return sys.split(",")
}
