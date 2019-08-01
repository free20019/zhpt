
define([
"dijit/Dialog", "dgrid/Editor", "dijit/form/Button",
"dijit/form/DateTextBox", "dijit/form/TimeTextBox",
"dijit/form/SimpleTextarea","dijit/form/FilteringSelect", "dgrid/OnDemandGrid",
"dijit/form/TextBox", "dgrid/extensions/Pagination",
"dgrid/Selection", "dgrid/Keyboard",
"dgrid/extensions/ColumnResizer", "app/createAsyncStore",
"dstore/Memory", "dgrid/extensions/DijitRegistry",
"dijit/registry", "dojo/dom-style", "dojo/_base/declare",
"dojo/dom-construct", "dojo/on", "app/util","dojo/store/Memory", "dijit/form/ComboBox"
,"dijit/form/RadioButton","dojo/DeferredList","dijit/form/CheckBox","dojo/dom-class","dojo/aspect"
    ], function(Dialog, Editor, Button, DateTextBox, TimeTextBox,
			SimpleTextarea,FilteringSelect, Grid, TextBox, Pagination, Selection, Keyboard,
			ColumnResizer, createAsyncStore, Memory, DijitRegistry,
			registry, domStyle, declare, dc, on, util,DojoMemory,ComboBox,RadioButton,DeferredList,CheckBox,domClass,aspect){
	console.log(333)
	var CustomGrid = declare([ DijitRegistry, Grid, Keyboard,Selection, ColumnResizer ]);
	var columns = {
			dojoId: {label:"序号"},
			HTH: {label: "任务号"},
			YCBM:{label: "用车部门"},
			XM: {label: "姓名"},
			LDHM: {label: "来电号码"},
			SFZHM: {label: "证件号"},
			YCDX: {label: "用车对象"},
			CLLX: {label: "车辆类型"},
			YCQK: {label: "用车区块"},
			YCDZ: {label: "地址"},
			/*YCSL: {label: "用车数量"},*/
			PFLX: {label: "排放类型"},
			TCC: {label: "停车场"},
			ZCSJ: {label: "租车时间",formatter : util.formatYYYYMMDD},
			HCSJ: {label: "还车时间",formatter : util.formatYYYYMMDD},
			CPHM: {label: "车牌号码"},
			ZLFS: {label: "租赁方式"},
			SJXM: {label: "司机姓名"},
			SJDH: {label: "司机电话"},
			JZLX: {label: "驾照类型"},
			JL: {label: "驾龄"},
			ZJ: {label: "租金"},
			SJHCSJ: {label: "实际还车时间",formatter : util.formatYYYYMMDDHHMISS},
			SSZJ: {label: "实收租金"},
			YE: {label: "余额"},
			ZT: {label: "状态"},
			SCDD: {label: "上车地点"},
			HCDD: {label: "还车地点"},
			BZ: {label: "备注"}
		};

	var grid=null,
	toolWrapper='ddfwToolBar',
	gridWrapper='ddfwGridDiv',
	store=null,
	dialog=null,
	resultData=null,
	queryCondition={},
	delArgs={},
	updateArgs={},
	addArgs={},
	gridCurrentSelect=null,
	pdxq = {},//派单条件
	callbackGet=function(jsonResult){
		if(jsonResult && jsonResult.data){
			for (var i = 0; i < jsonResult.data.length; i++) {
				jsonResult.data[i] = dojo.mixin({
					dojoId : i + 1
				}, jsonResult.data[i]);
			}
			store = new Memory({
				data : {
					identifier : 'dojoId',
					label : 'dojoId',
					items : jsonResult.data
				}
			});
		}
		

		if (grid) {
			grid = null;
			dojo.empty(gridWrapper)
		}
		grid = new CustomGrid({
			collection : store,
			columns : columns
		}, gridWrapper);
		grid.on('.dgrid-row:click', function(event) {
			var row = grid.row(event);
			gridCurrentSelect = row;
		});
	},
	getArgs={
		//url : "testData.txt",
		url : "common/getZcgl",
		postData : 'postData={"page":1,"pageSize":100,"keyword":""}',
		handleAs : "json",
		preventCache:true,
		withCredentials :  true,
		load : callbackGet,
		error:callbackError
	};	
	
	function verifyformvalues(btn,formvalues) {
		return true ;//TODO
		if(btn.id == 'ddfw_zbBtn'){//中巴车
			if (null == formvalues.pflx_zb || '' == formvalues.pflx_zb) {
				alert('请选着排放类型');
				return false;
			}
			if('' == formvalues.pflx_zb_sl) {
				alert('请输入车辆数量');
				return false;
			}
		}else if(btn.id == 'ddfw_dbBtn') {//大巴车
			if (null == formvalues.pflx_db || '' == formvalues.pflx_sw) {
				alert('请选着排放类型');
				return false;
			}
			if('' == formvalues.pflx_db_sl) {
				alert('请输入车辆数量');
				return false;
			}
		}else if(btn.id == 'ddfw_swBtn') {//商务车
			if (null == formvalues.pflx_sw || '' == formvalues.pflx_sw) {
				alert('请选着排放类型');
				return false;
			}
			if('' == formvalues.pflx_sw_sl) {
				alert('请输入车辆数量');
				return false;
			}
		}else if(btn.id == 'ddfw_xjcBtn') {//小轿车
			if (null == formvalues.pflx_xjc || '' == formvalues.pflx_xjc) {
				alert('请选着排放类型');
				return false;
			}
			if('' == formvalues.pflx_xjc_sl) {
				alert('请输入车辆数量');
				return false;
			}
		}else if(btn.id == 'ddfw_suvBtn') {//行李车
			if (null == formvalues.pflx_suv || '' == formvalues.pflx_suv) {
				alert('请选着排放类型');
				return false;
			}
			if('' == formvalues.pflx_suv_sl) {
				alert('请输入车辆数量');
				return false;
			}
		}else if(btn.id == 'ddfw_xsBtn') {//厢式
			if (null == formvalues.pflx_xs || '' == formvalues.pflx_xs) {
				alert('请选着排放类型');
				return false;
			}
			if('' == formvalues.pflx_xs_sl) {
				alert('请输入车辆数量');
				return false;
			}
		}
		/*
		else if(btn.id == 'ddfw_gncBtn') {//
			if (null == formvalues.pflx_gnc || '' == formvalues.pflx_gnc) {
				alert('请选着排放类型');
				return false;
			}
			if('' == formvalues.pflx_gnc_sl) {
				alert('请输入车辆数量');
				return false;
			}
		}*/
		return true;
	}
	function vehicleinfojoin(btn,formvalues) {
		var result = {"id":btn.id}
		if(btn.id == 'ddfw_zbBtn'){//中巴车
			result.cllx = '中巴车',result.sl=formvalues.pflx_zb_sl,result.pflx=(formvalues.pflx_zb=='1'?'燃油':'新能源');
		}else if(btn.id == 'ddfw_dbBtn') {//大巴车
			result.cllx = '大巴车',result.sl=formvalues.pflx_db_sl,result.pflx=(formvalues.pflx_db=='1'?'燃油':'新能源');
		}else if(btn.id == 'ddfw_swBtn') {//商务车
			result.cllx = '商务车',result.sl=formvalues.pflx_sw_sl,result.pflx=(formvalues.pflx_sw=='1'?'燃油':'新能源');
		}else if(btn.id == 'ddfw_xjcBtn') {//小轿车
			result.cllx = '小轿车',result.sl=formvalues.pflx_xjc_sl,result.pflx=(formvalues.pflx_xjc=='1'?'燃油':'新能源');
		}else if(btn.id == 'ddfw_suvBtn') {//行李车
			result.cllx = '行李车',result.sl=formvalues.pflx_suv_sl,result.pflx=(formvalues.pflx_suv=='1'?'燃油':'新能源');
		}else if(btn.id == 'ddfw_xsBtn') {//行李车
			result.cllx = '货车',result.sl=formvalues.pflx_xs_sl,result.pflx=(formvalues.ddfw_xs=='1'?'燃油':'新能源');
		}
		/*else if(btn.id == 'ddfw_gncBtn') {//行李车
			result.cllx = '功能车',result.sl=formvalues.pflx_gnc_sl,result.pflx=(formvalues.ddfw_gnc=='1'?'燃油':'新能源');
		}*/
		result.getString = function(){
			return this.cllx + "---" + this.pflx +"---" +this.sl +'辆'
		}
		return result ;
	}
	function getCllx(id) {
		var tempString;
		if(id == 'ddfw_zbBtn'){//中巴车
			tempString = '中巴车'
		}else if(id == 'ddfw_dbBtn') {//大巴车
			tempString = '大巴车'
		}else if(id == 'ddfw_swBtn') {//商务车
			tempString = '商务车'
		}else if(id == 'ddfw_xjcBtn') {//小轿车
			tempString = '小轿车'
		}else if(id == 'ddfw_suvBtn') {//行李车
			tempString = '行李车'
		}
		return tempString;
	}
	function initBtn(){
		var btnList = ['ddfw_zbBtn','ddfw_dbBtn','ddfw_swBtn','ddfw_xjcBtn','ddfw_suvBtn','ddfw_xsBtn','ddfw_gncBtn'];
		for(var i=0;i<btnList.length;i++){
			new Button({
		        onClick: function(){
		        	
		        	var btn = this,ht=['hth','ldhm','xm','sfzhm','ycdx','ycbm','ycdz'];//,'ycsl'
		        	for(var j=0;j<ht.length;j++){
		        		if(ddfwForm.getValues()[ht[j]] === undefined || dojo.trim(ddfwForm.getValues()[ht[j]])==''){
		        			alert('任务信息必须填写完整')
		        			return;
		        		}
		        	}
		        	var pd_dialog_table_warper = dc.create("div",{class:'pd_dialog_table_warper'});
		        	var pd_dialog_table = dc.create("table",{id:'pd_dialog_table',class:'ab'});
		        	var pd_dialog_tool = dc.create("div",{id:'pd_dialog_tool'});
		        	var pd_dialog_action = dc.create("div",{id:'pd_dialog_action',class:'dijitDialogPaneActionBar'});

		        	dc.place(pd_dialog_table,pd_dialog_table_warper);
		        	console.info('############',btn);
					var formvalues = ddfwForm.getValues();
					if (!verifyformvalues(btn,formvalues)) {
						return false;
					}
					pdxq = vehicleinfojoin(btn,formvalues);
					if(pdxq.sl > maxCllxYcsl){
						alert('用车数量不能大于' + maxCllxYcsl);
						return;
					}
						//vehicleCount =  parseInt(vehicleinfojoin(btn,formvalues)[1]);

//					var hisstime= new TextBox({id:"his_stime",value: '123',style:{'width':'80px','margin-right':'15px','margin-bottom':'10px','padding':'3px 0px'}}).placeAt('pd_dialog_tool');
					//dc.place(dc.create("span",{innerHTML:"至",style:{"margin-right":"5px"}}),'histjdiv');
					//var hisetime= new TextBox({id:"his_etime",value:setformat(new Date()),onClick:function(){WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})},style:{"width":"185px","margin-right":"15px"}}).placeAt('histjdiv');
					//dc.place(dc.create("span",{innerHTML:"公司",style:{"margin-right":"5px"}}),'histjdiv');
	        		pd_dialog_tool.innerHTML  = pdxq.getString()
	        		
		        	var pd_dialog = new Dialog({title:'派单',
		        		style:'width:1200px;',
		        		id:'pd_dialog',
			        	onHide:function(){
		        			this.destroyRecursive()
		        		}
		        	});
	        		pd_dialog.containerNode.appendChild(pd_dialog_tool);
		        	pd_dialog.containerNode.appendChild(pd_dialog_table_warper);
					pd_dialog.containerNode.appendChild(pd_dialog_action);
//		        	pd_dialog.show()
					var heads = ['区块','停车场','座位数','租车时间','还车时间','上车地点','还车地点','车牌号码','配驾驶员','司机','司机电话','驾照类型','驾龄','单价'];
		        	var tr = pd_dialog_table.insertRow();
		        	for(var k=0;k<heads.length;k++){
		        		var th = tr.insertCell(k);
		        		th.innerHTML = heads[k];
		        	}
	        		var pd_dialogDef  = pd_dialog.show();
	        		var tccDef = filterTcc();
	        		var clDef = filterCl(pdxq.cllx);
	        		var jsyDef = filterJsy(pdxq.cllx);
	        		var defs = new DeferredList([pd_dialogDef,tccDef, clDef,jsyDef]);  
	        		
	        		defs.then(function(results){
	    				new Button({
							id:"pd_dialog_buttonConfirm",
							label: "确认",
							onClick: function(){
								var mixJson = dojo.mixin(dojo.mixin(pd_dialog.getValues()
										,{"sl":parseInt(ddfwForm.getValues().ycsl)}),ddfwForm.getValues());
								
								for(var jk=0;jk<pdxq.sl;jk++){
//									for(var kl=0;kl<heads.length;kl++){
										mixJson['zcsj'+jk] = new Date(mixJson['zcsj'+jk]).getTime();
										mixJson['hcsj'+jk] = new Date(mixJson['hcsj'+jk]).getTime();
										mixJson['sjxm'+jk] = mixJson['jsy'+jk];
										mixJson['zlfs'+jk] = mixJson['zdjsy'+jk];
										mixJson['ycdx'] = ddfwForm.getValues().ycdx;
										mixJson['ycbm'] = ddfwForm.getValues().ycbm;
										mixJson['cllx'] = vehicleinfojoin(btn,formvalues).cllx;
										mixJson['pflx'] = vehicleinfojoin(btn,formvalues).pflx;
										mixJson['lxsl'] = vehicleinfojoin(btn,formvalues).sl;
										mixJson['fwlx'] = 1;
//									}
								}
								addZcgl(dojo.toJson(mixJson)).then(function(data){
									btn.setDisabled()
									btn.setLabel('已派单');
									pd_dialog.hide();
									var postData = {
											"page" : 1,
											"pageSize" : 100,
											"fwlx":1
										};
						        	for(var o in queryCondition){
							        	postData[o] = queryCondition[o].value;
							    	}
						        	dojo.xhrPost(dojo.mixin(getArgs,{"postData":'postData='+dojo.toJson(postData)}));
								});
							}
						}).placeAt('pd_dialog_action');
	    				new Button({
							id:"pd_dialog_buttonCancel",
							label: "取消",
							onClick: function(){
								pd_dialog.hide();
								var cphms = dojo.query('input[name^=cphm]')
								for(var i=0;i<cphms.length;i++){
									if(cphms[i].value != ''){
										qxyxCl(cphms[i].value);
									}
								}
							}
						}).placeAt('pd_dialog_action');
						pd_dialog.on('cancel',function(){
							var cphms = dojo.query('input[name^=cphm]')
							for(var i=0;i<cphms.length;i++){
								if(cphms[i].value != ''){
									qxyxCl(cphms[i].value);
								}
							}
						})
		        		for(var k=0;k<pdxq.sl;k++){
		        			var tr = pd_dialog_table.insertRow();
		        			var tds = [];
		        			for(var j=0;j<heads.length;j++){
		        				var td = tr.insertCell(j);
		        				if(j==8){
		        					td.innerHTML = '<div id=pd_dialog_table'+k+'_'+j+'_1'+'></div>是'
		        					td.innerHTML +='<div id=pd_dialog_table'+k+'_'+j+'_2'+'></div>否'
		        				}else{
		        					td.innerHTML = '<div id=pd_dialog_table'+k+'_'+j+'></div>'
		        				}
		        				tds.push(td);
		        			}
		        			
		        			//td0
		        			var blockStore = new DojoMemory({
		        				data: qkData
		        			});
		        			var blockComboBox = new ComboBox({
		        				name: "ycqk"+k,
		        				store: blockStore,
		        				searchAttr: "name",
		        				value:'重点区',
		        				style:"width:85px"
		        			}, 'pd_dialog_table'+k+'_'+'0').startup();
		        			
		        			//td1var 停车场
							var parkStore = new DojoMemory({
								data: results[1][1]
							});
		        			var parkComboBox = new ComboBox({
		        				name: "tcc"+k,
		        				store: parkStore,
		        				searchAttr: "name",
		        				value:'火车东站',
		        				style:"width:95px"
		        			}, 'pd_dialog_table'+k+'_'+'1').startup();
		        			
		        			//td2 座位数
		        			new dijit.form.ComboBox({
		        				name:'zws'+k,
		        				style:"width:60px",
		        				store: new DojoMemory({
			        				data: zwsData[pdxq.cllx]
			        			}),
			        			value:zwsData[pdxq.cllx][0]['name'],
		        				onClick:function(){
		        					
		        				}	
		        			},'pd_dialog_table'+k+'_'+'2').startup();
		        			
		        			//td2 租车时间
		        			new dijit.form.ValidationTextBox({
		        				name:'zcsj'+k,
		        				style:"width:90px",
		        				value:testZcData.ZCSJ[k],
		        				onClick:function(){WdatePicker({dateFmt:'yyyy-MM-dd'})}	
		        			},'pd_dialog_table'+k+'_'+'3').startup();
		        			
		        			//td3 还车时间
							new dijit.form.ValidationTextBox({
								name:'hcsj'+k,
								style:"width:90px",
								value:testZcData.HCSJ[k],
								onClick:function(){WdatePicker({dateFmt:'yyyy-MM-dd'})}
							},'pd_dialog_table'+k+'_'+'4').startup();

							// 上车地点
							new dijit.form.ValidationTextBox({
								name:'scdd'+k,
								style:"width:90px" ,
								value:testZcData.SCDD[k]
							},'pd_dialog_table'+k+'_'+'5').startup();
							
							// 还车地点
							new dijit.form.ValidationTextBox({
								name:'hcdd'+k,
								style:"width:90px" ,
								value:testZcData.HCDD[k]
							},'pd_dialog_table'+k+'_'+'6').startup();
							
		        			//td4 车牌号码
							var plateNumbersStore = new DojoMemory({
								data: results[2][1]
							});
							var cphmComboBox = new ComboBox({
								queryExpr:"*${0}*",
								autoComplete:false,
								name: "cphm"+k,
								//store: plateNumbersStore,
								searchAttr: "name",
								style:"width:90px",
								value:testZcData.CPHM[k],
								oldValue:"",
								onChange:function(){
									console.log('车牌号码 change')
									yxCl(this.displayedValue,this.oldValue);
									var iid = this.id.substr(0,this.id.lastIndexOf('_'));
									dojo.byId(iid+'_7').innerHTML = this.item.sjxm
									dojo.byId(iid+'_8').innerHTML = this.item.sjdh
									dojo.byId(iid+'_9').innerHTML = this.item.jzlx
//									dojo.byId(iid+'_12').innerHTML = this.item.jl
								},
								onClick:function(){
									this.oldValue = this.displayedValue;
								},
								onFocus:function(){
									var box = this;
									this.oldValue = this.displayedValue;
									console.log('focus..');
									filterCl(getCllx(btn.id)).then(function(data){
										console.log('cphmComboBox',cphmComboBox)
										box.set('store',new DojoMemory({
											data: data
										}))
									})
								}
							}, 'pd_dialog_table'+k+'_'+'7')
							cphmComboBox.startup();

		        			//td5 自带驾驶员
		        			var radioOne = new RadioButton({
		        		        value: "自带",
		        		        name: "zdjsy" + k,
		        		    }, 'pd_dialog_table'+k+'_'+'8_1').startup();
		        			var radioOne = new RadioButton({
		        		        value: "派遣",
		        		        name: "zdjsy" + k,
		        		        checked:true
		        		    }, 'pd_dialog_table'+k+'_'+'8_2').startup();

							//td6 驾驶员
							var plateNumbersStore = new DojoMemory({
								data: results[3][1]
								//,idProperty:'ID'
							});
							var jsyComboBox = new ComboBox({
								queryExpr:"*${0}*",
								autoComplete:false,
								name: "jsy"+k,
//								store: plateNumbersStore,
								searchAttr: "SJXM",
								style:"width:95px",
								onChange:function(){
									yxJsy(this.displayedValue,ddfwForm.getValues().hth+"_"+this.name+"_"+pdxq.cllx);
									
									var iid = this.id.substr(0,this.id.lastIndexOf('_'));
									dojo.byId(iid+'_10').innerHTML = this.item.SJDH
									dojo.byId(iid+'_11').innerHTML = this.item.JZLX
									dojo.byId(iid+'_12').innerHTML = this.item.JL
								},
								onFocus:function(){
									var box = this;
									console.log('focus..');
									filterJsy(getCllx(btn.id)).then(function(data){
										box.set('store',new DojoMemory({
											data: data
										}))
									})
								}
							}, 'pd_dialog_table'+k+'_'+'9');
							jsyComboBox.startup();

							//td7
//							new dijit.form.ValidationTextBox({
//								style:"width:90px"
//							},'pd_dialog_table'+k+'_'+'7').startup();

							
							//td8
//							new dijit.form.ValidationTextBox({
//								style:"width:40px"
//							},'pd_dialog_table'+k+'_'+'8').startup();

							//td9
//							new dijit.form.ValidationTextBox({
//								style:"width:30px"
//							},'pd_dialog_table'+k+'_'+'9').startup();
//							document.getElementById('pd_dialog_table'+k+'_'+'8').style.width='90px'
							//td10 租金
							new dijit.form.ValidationTextBox({
		        				name:'zj'+k,
		        				style:"width:40px",
		        				value:testZcData.ZJ[pdxq.cllx]
		        				
		        			},'pd_dialog_table'+k+'_'+'13').startup();
							//dc.place(dc.create("span",{innerHTML:"500",style:{"margin-right":"5px"}}),'pd_dialog_table'+k+'_'+'10');
							
							new dijit.form.TextBox({
		        				name:'bz'+k,
		        				style:"width:40px",
		        			},'pd_dialog_table'+k+'_'+'8').startup();
		        		}
		        	});

		        }
		    }, btnList[i]).startup();
		}
	}
	
	return declare( null,{
		constructor:function(){
			this.initToolBar();
			this.get();
			initBtn();
//			this.init();
		},
//		init:function(){
//			dojo.hitch(this.callbackGet);
//		},
		
		del:function(){
			
		},
		add:function(json){
			var formTemp = {};
			for ( var key in json) {
				formTemp[key] = json[key]
			}
			
			formTemp.CILENT_ID=dijit.byId('CILENT_ID').get('value');
			formTemp.LICENSE_PLATE=$("#LICENSE_PLATE").val();
			formTemp.DISPATCH_TIME=new Date(formTemp.DISPATCH_TIME).getTime();
			formTemp.RETURN_TIME=new Date(formTemp.RETURN_TIME).getTime();
			var jsonString = dojo.toJson(formTemp)
			var xhrArgs2 = {
				url : "rent/addrent",
				postData : 'postData={"page":1,"pageSize":100}',
				handleAs : "json",
				preventCache:true,
				withCredentials :  true,
				load : function(data) {
					dijit.byId('qd_dialog').show().then(function() {
						$("#czjg").html(data.info);
						on.once(dojo.byId("qd_dialog_submit"),'click',function () {
							dojo.xhrPost(xhrArgs);
						})
					})
				}
			}
			dojo.xhrPost(dojo.mixin(xhrArgs2, {
				"postData" : 'postData=' + jsonString
			}));
		},
		update:function(json){
			var formTemp = {};
			for ( var key in json) {
				formTemp[key] = json[key]
			}
			formTemp.CILENT_ID=dijit.byId('CILENT_ID').get('value');
			formTemp.LICENSE_PLATE=$("#LICENSE_PLATE").val();
			var jsonString = dojo.toJson(formTemp)
			var xhrArgs2 = {
				url : "rent/editrent",
				postData : 'postData={"page":1,"pageSize":100}',
				handleAs : "json",
				preventCache:true,
				withCredentials :  true,
				load : function(data) {
					dijit.byId('qd_dialog').show().then(function() {
						$("#czjg").html(data.info);
						on.once(dojo.byId("qd_dialog_submit"),'click',function () {
							dojo.xhrPost(xhrArgs);
						})
					})
				}
			}
			dojo.xhrPost(dojo.mixin(xhrArgs2, {
				"postData" : 'postData=' + jsonString
			}));
		},
		get:function(extArgs){
			var postData = {
				"page" : 1,
				"pageSize" : 100,
				"fwlx":1
			};
        	for(var o in queryCondition){
	        	postData[o] = queryCondition[o].get('value');
	    	}
        	dojo.xhrPost(dojo.mixin(getArgs,{"postData":'postData='+dojo.toJson(postData)}));
		},
		initToolBar:function(){
//			var dbtFilter;
//			if(!dijit.byId('dbt')){
//				console.log('no dbt')
//				dbtFilter = new FilteringSelect({
//					name:'dbt',
//					queryExpr:"*${0}*",
//					autoComplete:false,
//					style:"width:110px",
//			        //store: new dojo.store.Memory({ data: storedata }),
//			        searchAttr: "name",
//			        onChange: function(state){
//			        }
//			    }, "dbt");
//				dbtFilter.startup();
//			}
//			filterDbt().then(function(data){
//				dbtFilter.set('store',new dojo.store.Memory({ data: data }))
//			})
			
			
			var keyword = new TextBox({
				placeHolder:'注册编号,车牌,电话号码,代表团',
				style : {
					"width" : "18em",
					"margin-right" : "15px"
				}
			}).placeAt(toolWrapper);
//			var cphm = new TextBox({
//				placeHolder:'车牌',
//				style : {
//					"width" : "8em",
//					"margin-right" : "15px"
//				}
//			}).placeAt(toolWrapper);
//			var ldhm = new TextBox({
//				placeHolder:'电话号码',
//				style : {
//					"width" : "8em",
//					"margin-right" : "15px"
//				}
//			}).placeAt(toolWrapper);
//			var dbt = new TextBox({
//				placeHolder:'代表团',
//				style : {
//					"width" : "8em",
//					"margin-right" : "15px"
//				}
//			}).placeAt(toolWrapper);
			
			new Button({
				label : "查询",
				moudel:this,
				onClick : function(evt) {
					console.log('##',this,evt);
					this.moudel.get()
//					this.showWait();
//					var postData = {
//						"page" : 1,
//						"pageSize" : 100
//					};
//		        	for(var o in queryCondition){
//			        	postData[o] = queryCondition[o].value;
//		        	}
//		        	dojo.xhrPost(dojo.mixin(xhrArgs,{"postData":'postData='+dojo.toJson(postData)}));
				}
			}).placeAt(toolWrapper);
			new Button({
				label : "还车",
				moudel:this,
				onClick : function(evt) {
//					ddfw_dialog_hc = new Dialog({title:'还车',
//		        		style:'width:600px;height: 260px;',
//		        		href:'app/html/zcgl/ddfw_ddfw_dialog_hc.html',
//			        	onHide:function(){
//		        			this.destroyRecursive()
//		        		}
//		        	});
					ddfw_dialog_hc.set('title','还车');
					var moudel = this.moudel;
					ddfw_dialog_hc.execute = function(){
						var postData = 'postData='+dojo.toJson(ddfw_dialog_hc.getValues());
						dojo.xhrPost({
							postData: postData,
							url: encodeURI(basePath + 'common/hcZcgl'),
							load:function (data) {
								console.log('aaaa')
									moudel.get();
							}

						});
					}
					if(gridCurrentSelect&&gridCurrentSelect.data&&gridCurrentSelect.data.ZT=='未还'){
						
						ddfw_dialog_hc.show().then(function(){
//						dojo.byId('dialog_clgl_hc_qd').onclick = function(){
//							var postData = 'postData='+dojo.toJson(ddfw_dialog_hc.getValues());
//							dojo.xhrPost({
//								postData: postData,
//								url: encodeURI(basePath + 'common/hcZcgl'),
//								load:function (data) {
//									console.log('aaaa')
//										moudel.get();
//								}
//
//							});
//						}
//						dojo.byId('dialog_clgl_hc_qx').onclick = function(){
//							ddfw_dialog_hc.hide();
//						}
//						dojo.forEach(grid.collection.data, function (item, index) {
//							console.log('addadd..')
//							if (grid.isSelected(item)) {
//								console.log(item)
//								item.ZCSJ = util.formatYYYYMMDD(item.ZCSJ)
//								item.HCSJ = util.formatYYYYMMDD(item.HCSJ)
//								item.SJHCSJ = util.formatYYYYMMDD(item.SJHCSJ)
//								ddfw_dialog_hc.setValues(item)
//							}
//						});
							var item = dojo.clone(gridCurrentSelect.data);
							item.ZCSJ = util.formatYYYYMMDD(item.ZCSJ)
							item.HCSJ = util.formatYYYYMMDD(item.HCSJ)
							item.SJHCSJ = item.SJHCSJ !='' ? util.formatYYYYMMDD(item.SJHCSJ) : ''
							ddfw_dialog_hc.setValues(item);
							
						});
					}else{
						alert('当前车辆已还车')
					}
				}
			}).placeAt(toolWrapper);
			new Button({
				label : "修改",
				moudel:this,
				onClick : function(evt) {
					var moudel = this.moudel;
					ddfw_dialog_xg.set('title','修改车辆');
					ddfw_dialog_xg.setValues({"SJXM":"","CPHM":"","BZ":""})
					
					ddfw_dialog_xg.execute = function(){
						var postData = 'postData='+dojo.toJson(dojo.mixin(ddfw_dialog_xg.getValues(),
								{"HTH":gridCurrentSelect.data.HTH
								,"OLDSJXM":gridCurrentSelect.data.SJXM
								,"OLDSCPHM":gridCurrentSelect.data.CPHM
								}));
						dojo.xhrPost({
							postData: postData,
							url: encodeURI(basePath + 'common/xgZcgl'),
							load:function (data) {
									moudel.get();
							}

						});
					}
					
					//取消修改
					ddfw_dialog_xg.qx_sj_cp = function(){
						qxyxXgCl(gridCurrentSelect.data.HTH,ddfw_dialog_xg.getValues().CPHM,gridCurrentSelect.data.CPHM);
						qxyxXgJsy(gridCurrentSelect.data.HTH,ddfw_dialog_xg.getValues().SJXM,gridCurrentSelect.data.SJXM);
					}
					if(gridCurrentSelect&&gridCurrentSelect.data&&gridCurrentSelect.data.ZT=='未还'){
						ddfw_dialog_xg.show().then(function(){
							var item = dojo.clone(gridCurrentSelect.data);
							item.ZCSJ = util.formatYYYYMMDD(item.ZCSJ)
							item.HCSJ = util.formatYYYYMMDD(item.HCSJ)
							item.SJHCSJ = item.SJHCSJ !='' ? util.formatYYYYMMDD(item.SJHCSJ) : ''
							ddfw_dialog_xg.setValues(item);
							
							var arg = {	queryExpr:"*${0}*",
									autoComplete:false,
									name: "SJXM",
									searchAttr: "SJXM"}
							dojo.mixin(ddfw_xg_xsj,arg);
							ddfw_xg_xsj.onChange = function(){
								yxJsy(this.displayedValue,gridCurrentSelect.data.HTH+"_"+this.name+"_"+gridCurrentSelect.data.CLLX);
							} 
							ddfw_xg_xsj.onFocus = function(){
								filterJsy(pdxq.pflx).then(function(data){
									ddfw_xg_xsj.set('store',new DojoMemory({
										data: data
									}))
								})
							}
							var arg2 = {	queryExpr:"*${0}*",
									autoComplete:false,
									name: "CPHM",
									searchAttr: "name"}
							dojo.mixin(ddfw_xg_xcp,arg2);
							ddfw_xg_xcp.onChange = function(){
								yxCl(this.displayedValue,gridCurrentSelect.data.HTH+"_"+this.name+"_"+pdxq.cllx);
							} 
							ddfw_xg_xcp.onFocus = function(){
								filterCl(pdxq.cllx).then(function(data){
									ddfw_xg_xcp.set('store',new DojoMemory({
										data: data
									}))
								})
							}
							ddfw_xg_xsj.onFocus();
							ddfw_xg_xcp.onFocus();
							
						});
					}else{
						alert('当前车辆已还车，不能修改')
					}
				}
			}).placeAt(toolWrapper);
			new Button({
				label : "删除",
				moudel:this,
				onClick : function(evt) {
					if(!confirm("确认删除")){
						return;
					}
					var moudel = this.moudel;
					dojo.forEach(grid.collection.data, function (item, index) {
						if (grid.isSelected(item)) {
							var pd = {"ID":item.ID}
							dojo.xhrPost({
								postData: 'postData='+dojo.toJson(pd),
								url: encodeURI(basePath + 'common/deleteZcgl'),
								load:function (data) {
									moudel.get();
								}
							});
						}
					});
				}
			}).placeAt(toolWrapper);
			
			
			var checkBox1 = new CheckBox({
				moudel:this,
				checked:true,
				style:{"margin-left":"10px"},
				onClick : function(evt) {
//					console.log('##',this,evt);
					this.moudel.get()
				}
			}).placeAt(toolWrapper);
			dc.place(dc.create("span",{innerHTML:"未还车",style:{"margin-left":"1px","margin-right":"5px"}}),toolWrapper);
			var checkBox2 = new CheckBox({
				moudel:this,
				checked:true,
				onClick : function(evt) {
//					console.log('##',this,evt);
					this.moudel.get()
				}
			}).placeAt(toolWrapper);
			dc.place(dc.create("span",{innerHTML:"已还车",style:{"margin-left":"1px"}}),toolWrapper);
			new Button({
				label : "生成合同",
				moudel:this,
				onClick : function(evt) {
					dialog_ddfw_scht.show();
				}
			}).placeAt(toolWrapper);
			queryCondition = {"keyword":keyword,"checkBox1":checkBox1,"checkBox2":checkBox2};
			
			new Button({
				label : "导  出",
				onClick : function() {
					var postData = {};
					for(var o in queryCondition){
		        			postData[o] = queryCondition[o].value;
		        	}
					url = "common/zcglExport?postData="
							+ dojo.toJson(postData), window.open(url)
				}
			}).placeAt(toolWrapper);
			/*new Button({
				label : "统 计",
				onClick : function() {
					dialog_zcgl_tj.show();
					url = basePath+"common/zcglCount"
				}
			}).placeAt(toolWrapper);*/
		}
	})
})


