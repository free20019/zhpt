var sjzhxxGrid;
require([ "app/util","dgrid/Editor","dijit/form/Button" ,"dijit/form/DateTextBox","dijit/form/FilteringSelect","dojo/store/Memory","dijit/form/Select"
	,"dijit/Dialog", "dgrid/OnDemandGrid","dijit/form/TextBox","app/Pagination"
	,"dgrid/Selection", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"
	,"app/createAsyncStore","dstore/Memory","dgrid/extensions/DijitRegistry"
	,"dijit/registry", "dojo/dom-style","dojo/_base/declare",
	"dojo/dom-construct", "dojo/domReady!"], function(util,Editor, Button,DateTextBox,FilteringSelect,_Memory,Select,Dialog,Grid,TextBox
	, Pagination,Selection,Keyboard,ColumnResizer,createAsyncStore,Memory,DijitRegistry
	,registry, domStyle,declare, dc ) {
	var  store = null ,count = 0;
	dc.place(dc.create("span",{innerHTML:"公司名",style:{"margin-right":"5px"}}),'sjzhxxdiv');
	var zh_name= new TextBox({id:"zh_name",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjzhxxdiv');
	dc.place(dc.create("span",{innerHTML:"车号",style:{"margin-right":"5px"}}),'sjzhxxdiv');
	var zh_no= new TextBox({id:"zh_no",style:{"width":"8em","margin-right":"15px"}}).placeAt('sjzhxxdiv');
	var queryCondition = {"comp":zh_name,"vehi_no":zh_no};

	var sjzhxxFrameDialog = new Dialog({id:'sjzhxxFrameDialog', title:'添加司机', style:'height:390px;width:300px;'},'sjzhxxFrameDialogDiv');
	var sjzhxxbutton=new Button({
		label: "查询",
		onClick: function(){
			this.showWait();
			var postData = {"page":1,"pageSize":pageSize};
			for(var o in queryCondition){
				postData[o] = queryCondition[o].value;
			}
			dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
		}
	}).placeAt('sjzhxxdiv');

	new Button({
		label: "删除",
		onClick: function(){
			if(window.confirm("确定删除该司机吗？")){
				dojo.forEach(sjzhxxGrid.collection.data, function(item,index) {
					if (sjzhxxGrid.isSelected(item)) {
						dojo.xhrPost({
							postData : "id="+item.ID,
							url : basePath + "scjg/delAccounts",
							handleAs : "json",
							load : function(data) {
								console.log(data);
								alert(data.info);
								deferred = dojo.xhrPost(xhrArgs);
							}
						});
					}
				});
			}
		}
	}).placeAt('sjzhxxdiv');


	var btnFlag = 0;
	//添加、修改弹框
	dc.place(dc.create("span",{innerHTML:"司机姓名:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display': 'inline-block','position':'absolute','top':'40px'}}),'sjzhxxFrameDialog');
	var driver_name= new TextBox({id:"driver_name",name:"driver_name",trim:"true",style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	dc.place(dc.create("span",{innerHTML:"手机号:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display': 'inline-block','position':'absolute','top':'65px'}}),'sjzhxxFrameDialog');
	var driver_mobile= new TextBox({id:"driver_mobile",name:"driver_mobile",trim:"true",style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	dc.place(dc.create("span",{innerHTML:"营运证号码:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display': 'inline-block','position':'absolute','top':'90px'}}),'sjzhxxFrameDialog');
	var licence_no= new TextBox({id:"licence_no",name:"licence_no",trim:"true",style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	dc.place(dc.create("span",{innerHTML:"子公司:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display':'inline-block','position':'absolute','top':'117px'}}),'sjzhxxFrameDialog');
	var subsidiary_id= new TextBox({id:"subsidiary_id",name:"subsidiary_id",trim:"true",style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	dc.place(dc.create("span",{innerHTML:"车牌号码:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display':'inline-block','position':'absolute','top':'143px'}}),'sjzhxxFrameDialog');
	var car_no= new TextBox({id:"car_no",name:"car_no",trim:"true",style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	dc.place(dc.create("span",{innerHTML:"邮政编码:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display':'inline-block','position':'absolute','top':'170px'}}),'sjzhxxFrameDialog');
	var city_no= new TextBox({id:"city_no",name:"city_no",trim:"true",style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	dc.place(dc.create("span",{innerHTML:"支付宝账号:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right','display': 'inline-block','position':'absolute','top':'195px'}}),'sjzhxxFrameDialog');
	var alipay_account= new TextBox({id:"alipay_account",name:"alipay_account",trim:"true",style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	var bizNoStore = new _Memory({data: [{name:"不需更新", id:"0"}, {name:"更新不上传", id:"1"}, {name:"更新后上传", id:"2"}]});
	dc.place(dc.create("span",{innerHTML:"状态:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display':'inline-block','position':'absolute','top':'220px'}}),'sjzhxxFrameDialog');
	var biz_no= new FilteringSelect({id:"biz_no",trim:"true",name: 'state',value: '更新不上传', store: bizNoStore,searchAttr: 'name',style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	dc.place(dc.create("span",{innerHTML:"公司名称:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display':'inline-block','position':'absolute','top':'248px'}}),'sjzhxxFrameDialog');
	var pname= new TextBox({id:"pname",name:"pname",trim:"true",style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	dc.place(dc.create("span",{innerHTML:"pid:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display':'inline-block','position':'absolute','top':'273px'}}),'sjzhxxFrameDialog');
	var pid= new TextBox({id:"pid",name:"pid",trim:"true",style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	dc.place(dc.create("span",{innerHTML:"系统时间:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display':'inline-block','position':'absolute','top':'300px'}}),'sjzhxxFrameDialog');
	var system_time= new TextBox({id:"system_time",name:"system_time:",onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	dc.place(dc.create("span",{innerHTML:"公司编号:",style:{'width': '80px', "margin-bottom":"10px", "margin-right":"10px", 'text-align': 'right', 'display':'inline-block','position':'absolute','top':'328px'}}),'sjzhxxFrameDialog');
	var company_id= new TextBox({id:"company_id",name:"company_id:",trim:"true",style:{"width":"15em","margin-left":"80px","margin-bottom":"5px"}}).placeAt('sjzhxxFrameDialog');
	dc.place(dc.create("br"),'sjzhxxFrameDialog');
	var updateId = new TextBox({id:"sjzhxxId",name:"id",style:{"display": "none"}}).placeAt('sjzhxxFrameDialog');
	function yanzheng() {
		var drivername = driver_name.value;
		var drivermobile = driver_mobile.value;
		var drivermobilereg = /^1[345789][\d]{9}$/;
		var licenceno = licence_no.value;
		var subsidiaryid = subsidiary_id.value;
		var carno = car_no.value;
		var cityno = city_no.value;
		var citynoreg = /^[1-9][\d]{5}$/;
		var alipayaccount = alipay_account.value;
		var bizno = biz_no.value;
		var _pname = pname.value;
		var _pid = pid.value;
		var systemtime = $('#system_time').val();
		var companyid = company_id.value;
		if ('' == drivername || ' ' == drivername) {
			alert('请填写"司机名称"');
			return false;
		} else if ('' == drivermobile || ' ' == drivermobile) {
			alert('请填写"手机号码"');
			return false;
		} else if (!drivermobilereg.test(drivermobile)) {
			alert('输入的手机格式不正确');
			return false;
		} else if ('' == licenceno || ' ' == licenceno) {
			alert('请填写"营运证号码"');
			return false;
		} else if ('' == subsidiaryid || ' ' == subsidiaryid) {
			alert('请填写"子公司"');
			return false;
		} else if ('' == carno || ' ' == carno) {
			alert('请填写"车牌号码"');
			return false;
		} else if ('' != cityno && ' ' != cityno && !citynoreg.test(cityno)) {
			alert('输入的邮政编码格式不正确');
			return false;
		} else if ('' == alipayaccount || ' ' == alipayaccount) {
			alert('请填写"支付宝账号"');
			return false;
		} else if ('' == bizno || ' ' == bizno) {
			alert('请填写"状态"');
			return false;
		} else if ('' == _pname || ' ' == _pname) {
			alert('请填写"公司名称"');
			return false;
		} else if ('' == _pid || ' ' == _pid) {
			alert('请填写"pid"');
			return false;
		} else if ('' == systemtime || ' ' == systemtime) {
			alert('请填写"系统时间"');
			return false;
		} else if ('' == companyid || ' ' == companyid) {
			alert('请填写"公司编号"');
			return false;
		}
		return true;
	}
	function closeinput() {
		dijit.byId('driver_name').set('value', '');
		dijit.byId('driver_mobile').set('value', '');
		dijit.byId('licence_no').set('value', '');
		dijit.byId('subsidiary_id').set('value', '');
		dijit.byId('car_no').set('value', '');
		dijit.byId('city_no').set('value', '');
		dijit.byId('alipay_account').set('value', '');
		dijit.byId('biz_no').set('value', '更新不上传');
		dijit.byId('pname').set('value', '');
		dijit.byId('pid').set('value', '');
		dijit.byId('system_time').set('value', '');
		dijit.byId('company_id').set('value', '');
	}
	var sjzhxxButton = new Button({
		label: "提交",onClick: function(){
			if (btnFlag == 1) {
				if (yanzheng()) {
					var oo = sjzhxxFrameDialog.getValues();
					oo.state = (oo.state == '不需更新'?0:oo.state == '更新不上传'?1:2);
					dojo.xhrPost({
						//url : basePath + 'scjg/addAccounts',
						url:"http://localhost:8080/zhpt/scjg/addAccounts",
						postData : 'postData=' +  dojo.toJson(oo),
						handleAs : "json",
						load : function(data) {
							alert(data);
							sjzhxxFrameDialog.hide();
							closeinput();
							deferred = dojo.xhrPost(xhrArgs);
						},
						error : function(error) {
							targetNode.innerHTML = "An unexpected error occurred: " + error;
						}
					});
				}
			} else {
				if (yanzheng()) {
					var oo = sjzhxxFrameDialog.getValues();
					oo.state = (oo.state == '不需更新'?0:oo.state == '更新不上传'?1:2);
					dojo.xhrPost({
						url :'scjg/editAccounts',
						postData : 'postData=' + dojo.toJson(oo),
						handleAs : "json",
						load : function(data) {
							alert(data.info);
							if ('修改失败' == data.info) return;
							sjzhxxFrameDialog.hide();
							closeinput();
							deferred = dojo.xhrPost(xhrArgs);
						},
					});
				}
			}
		}
	}).placeAt('sjzhxxFrameDialog');
	new Button({
		label: "取消",onClick: function(){
			sjzhxxFrameDialog.hide();
		}
	}).placeAt('sjzhxxFrameDialog');

	new Button({
		label: "添加",
		onClick: function(){
			closeinput();
			document.getElementById('sjzhxxFrameDialog_title').innerText = '添加';
			document.getElementById('dijit_form_Button_2_label').innerText = '添加';
			dijit.byId("system_time").setValue(new Date().format('yyyy-MM-dd hh:mm:ss'));
			sjzhxxFrameDialog.show();
			btnFlag = 1;
		}
	}).placeAt('sjzhxxdiv');

	new Button({
		label: "修改",
		onClick: function(){
			var hs=0;
			dojo.forEach(sjzhxxGrid.collection.data, function(item,index) { if (sjzhxxGrid.isSelected(item)) hs++; });
			if(hs==0){
				alert("请选择一行进行修改");
			}else if(hs>1){
				alert("只能选择一行进行修改");
			}else{
				sjzhxxFrameDialog.show().then(function() {
					dojo.forEach(sjzhxxGrid.collection.data, function(item, index) {
						if (sjzhxxGrid.isSelected(item)) {
							dojo.xhrPost({
								url : basePath + 'scjg/findAccountID',
								postData : 'id=' + item.ID,
								handleAs : "json",
								load : function(data) {
									document.getElementById('sjzhxxFrameDialog_title').innerText = '修改';
									document.getElementById('dijit_form_Button_2_label').innerText = '修改';
									dijit.byId("sjzhxxId").setValue(data[0].ID);
									dijit.byId("driver_name").setValue(data[0].DRIVER_NAME);
									dijit.byId("driver_mobile").setValue(data[0].DRIVER_MOBILE);
									dijit.byId("licence_no").setValue(data[0].LICENCE_NO);
									dijit.byId("subsidiary_id").setValue(data[0].SUBSIDIARY_ID);
									dijit.byId("car_no").setValue(data[0].CAR_NO);
									dijit.byId("city_no").setValue(data[0].CITY_NO);
									dijit.byId("alipay_account").setValue(data[0].ALIPAY_ACCOUNT);
									var bizno = data[0].BIZ_NO;
									dijit.byId("biz_no").setValue(bizno == 0 ? '不需更新' : bizno == 1?'更新不上传':'更新后上传');
									dijit.byId("pname").setValue(data[0].PNAME);
									dijit.byId("pid").setValue(data[0].PID);
									dijit.byId("system_time").setValue(new Date(data[0].SYSTEM_TIME).format('yyyy-MM-dd hh:mm:ss'));
									dijit.byId("company_id").setValue(data[0].COMPANY_ID);
									btnFlag = 2;
								},
							});
						}
					})
				});
			}

		}
	}).placeAt('sjzhxxdiv');
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
			url = "scjg/findAccountsexcle?postData="
				+ dojo.toJson(postData), window.open(url)
		}
	}).placeAt('sjzhxxdiv');

	var columns = [
		       		{field:'dojoId',label : "序号"}, // give column a custom name
		    		{field:'DRIVER_NAME',
		    			label : "司机姓名"
					},{field:'LICENCE_NO',
						label : "营运证号码"
		    		},{field:'DRIVER_MOBILE',
		    			label : "手机号"
					},{field:'CAR_NO',
						label : "车牌号码"
					},{field:'PNAME',
						label : "公司名称"
		    		},{field:'ALIPAY_ACCOUNT',
		    			label : "支付宝账号"
		    		},{field:'SYSTEM_TIME',
		    			label : "系统时间"
		    				,formatter : util.formatYYYYMMDDHHMISS
		    		}
		];

	

		var xhrArgs = {
			url : basePath + "scjg/findAccounts",
			postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
			handleAs : "json",
			load : function(data) {
				sjzhxxbutton.hideWait();
				console.log(data.count)
					sjzhxxGrid.totalCount = data.count;
					for(var i=0; i<data.datas.length;  i++){
				    	data.datas[i] = dojo.mixin({ dojoId: i+1 }, data.datas[i]);
				    }
					store = new Memory({ data: {
						identifier: 'dojoId',
						label: 'dojoId',
						items: data.datas
					} });
					sjzhxxGrid.set('collection',store);
					sjzhxxGrid.pagination.refresh(data.datas.length);
			},
		};
		var CustomGrid = declare([Grid, Keyboard ,ColumnResizer,Selection]);
		sjzhxxGrid= new CustomGrid({
			totalCount : 0,
			columns: columns,
			pagination:null
		},"sjzhxxtable");
		sjzhxxGrid.refresh2 = function(){
			dojo.xhrPost(xhrArgs);
		}
//			dojo.xhrPost(xhrArgs);
		
		var pageii = new Pagination(sjzhxxGrid,xhrArgs,queryCondition,pageSize);
		
		dc.place(pageii.first(),'sjzhxxtable','after');
});
