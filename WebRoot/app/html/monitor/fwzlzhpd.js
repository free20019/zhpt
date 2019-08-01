var fwzlzhpdGrid;
var fwzldata="";
var option="";
require([ "app/util","dgrid/Editor","dijit/form/Button" ,"dijit/Dialog","dijit/form/DateTextBox","dijit/form/Select"
	          ,"dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
	          ,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
	          ,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
	          ,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
	          "dojo/dom-construct", "dojo/on","dojo/domReady!"], function(util,
	        		  Editor, Button,Dialog,DateTextBox,Select,Grid,TextBox
	        		  , Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
	        		  ,registry, domStyle,declare, dc,on ) {
	var  store = null ,count = 0;
	dc.place(dc.create("span",{innerHTML:"企业名称",style:{"margin-right":"5px"}}),'fwzlzhpddiv');
	var pd_qymc= new TextBox({id:"pd_qymc",style:{"width":"8em","margin-right":"15px"}}).placeAt('fwzlzhpddiv');
	dc.place(dc.create("span",{innerHTML:"年度",style:{"margin-right":"5px"}}),'fwzlzhpddiv');
	var pd_nd= new TextBox({id:"pd_nd",style:{"width":"8em","margin-right":"15px"}}).placeAt('fwzlzhpddiv');
	dc.place(dc.create("span",{innerHTML:"信誉等级",style:{"margin-right":"5px"}}),'fwzlzhpddiv');
	var pd_xydj= new TextBox({id:"pd_xydj",style:{"width":"8em","margin-right":"15px"}}).placeAt('fwzlzhpddiv');
	var queryCondition = {"pd_qymc":pd_qymc,"pd_nd":pd_nd,"pd_xydj":pd_xydj};

	var fwzlzhpdbutton=new Button({
	        label: "查询",
	        onClick: function(){
				this.showWait();
				var postData = {"page":1,"pageSize":pageSize};
	        	for(var o in queryCondition){
	        			postData[o] = queryCondition[o].value;
	        	}
	        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
	        }
	}).placeAt('fwzlzhpddiv');
	new Button({
		label : "导  出",
		onClick : function() {
			var postData = {
				"page" : 1,
				"pageSize" : 10000
			};
			for(var o in queryCondition){
				postData[o] = queryCondition[o].value;
			}
			url = "scjg/fwzlxxexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('fwzlzhpddiv');
	
	new Button({
		label : "导  入",
		onClick : function() {
			fwzlzhpdDialog.show().then(function () {
				dojo.query('#sel-add option').remove();
				dojo.query('#sel-add1 option').remove();
//				for(var i=0; i<fwzldata.datas.length;  i++) {
//					option += "<option>" + fwzldata.datas[i].YHMC + "  " + fwzldata.datas[i].SJ + "  " + fwzldata.datas[i].XYDJ + "</option>";
//				}
//				dojo.query('#sel-add')[0].innerHTML = option;
				tiaozheng();
			});

		}
	}).placeAt('fwzlzhpddiv');
	
	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'YHMC', 
		    			label : "企业名称" 
		    		},{field:'SJ', 
		    			label : "年度"
		    		},{field:'XYDJ', 
		    			label : "信誉等级"
		    		}
		];

		var xhrArgs = {
			url : basePath + "scjg/fwzlxx",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				fwzldata = data;
				fwzlzhpdbutton.hideWait();
				console.log(data.count)
					fwzlzhpdGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					fwzlzhpdGrid.set('collection',store);
					fwzlzhpdGrid.pagination.refresh(data.datas.length);
			}
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		fwzlzhpdGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"fwzlzhpdtable");
		fwzlzhpdGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(fwzlzhpdGrid,xhrArgs,queryCondition);
		
		dc.place(pageii.first(),'fwzlzhpdtable','after');
});

function tiaozheng(){
	//添加所有车
	$('#addalls').unbind('click').click(function(){
		var vehicle = $("#sel-add option");
		for(var i =0;i< vehicle.length;i++){
			$("#sel-add1").append("<option value='"+vehicle[i].text+"'>"+vehicle[i].text+"</option>");
		}
		$("#sel-add").empty();

	});
//添加一辆车
	$('#addones').unbind('click').click(function(){
		var vehicle = $("#sel-add option:selected").text();
		var i = $("#sel-add").get(0).selectedIndex;
		if(vehicle !=""){
			$("#sel-add1").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
			$("#sel-add").get(0).remove(i);
		}else{
			alert("请选择要添加的车号");
		}
	});
//删除一辆车
	$('#delones').unbind('click').click(function(){
		var i = $("#sel-add1").get(0).selectedIndex;
		var vehicle = $("#sel-add1 option:selected").text();
		if(i != -1){
			$("#sel-add1").get(0).remove(i);
			$("#sel-add").append("<option value='"+vehicle+"'>"+vehicle+"</option>");
		}else{
			alert("请选择要移除的车号");
		}
	});
//删除所有车
	$('#delalls').unbind('click').click(function(){
		$("#sel-add1").empty();
	});
//	取消
	$("#quit").unbind('click').click(function(){
		fwzlzhpdDialog.hide();
	});
	
	$("#insert").unbind('click').click(function(){
		var value="";
		var values=$("#sel-add option");
		for(var i = 0, l = values.length; i < l; i++) {
			value+=values[i].text+";";
		}
		$.ajax({
			secureuri:false,//一般设置为false
			url: 'excle/insert',//请求的action路径
			data: 'value='+value,
			dataType: 'text',//返回值类型 一般设置为json
			error: function (data, status, e){
			},
			success: function (data,status){  //服务器成功响应处理函数
				
			}
		});
		alert('添加成功')
		fwzlzhpdDialog.hide();
	});
}

function FileUpLoad(){
	$.ajaxFileUpload({
		secureuri:false,//一般设置为false
		fileElementId:'uploadfile',//文件上传空间的id属性  <input type="file" id="file" name="file" />
		url: 'excle/upload',//请求的action路径
		dataType: 'text',//返回值类型 一般设置为json
		error: function (data, status, e){
		},
		success: function (data,status){  //服务器成功响应处理函数
			 showlb();
		}
	});
}
function showlb(){
	$.ajaxFileUpload({
		secureuri:false,//一般设置为false
		url: 'excle/show',//请求的action路径
		dataType: 'json',//返回值类型 一般设置为json
		error: function (data, status, e){
		},
		success: function (data,status){  //服务器成功响应处理函数
			var li;
			for(var i=0;i<data.count;i++){
				li+="<option>'"+data.datas[i].COMPNAME+","+data.datas[i].TIME+","+ data.datas[i].LEVEL1+"'</option>";
			}
			$("#sel-add1").html(li);		
		}
	});
}

