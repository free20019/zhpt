
function ConditionQuery(){
	this.domDriver = null;
	this.driver = function(){
		//				<td>超时<input type="checkbox" id=cp_driver_cs_flag value="cs" name=cp_driver_cs_flag></td>\
		var table = '<table id=cp_driver style="display:none">\
			<tr>\
				<td>姓名</td>\
				<td><span class="cds_textbox" ><input id=cp_driver_name type="text" class="cds_textbox-text" ></span></td>\
				<td>身份证</td>\
				<td><span class="cds_textbox" ><input id=cp_driver_sfz type="text" class="cds_textbox-text" ></span></td>\
				<td>总公司</td>\
				<td><span class="cds_textbox" ><input id=cp_driver_zgs type="text" class="cds_textbox-text" ></span></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			this.domDriver =  $(table)[0]
			$(this.domDriver).find('a').bind('click',function(){
				ff_driver();
			})
	}
	function ff_driver(){
		globalQueryCondition = '';
		if($(cp_driver_name).val() != ''){
			globalQueryCondition += '"name":"'+$(cp_driver_name).val()+'",'
		}
		if($(cp_driver_sfz).val() != ''){
			globalQueryCondition += '"sfz":"'+$(cp_driver_sfz).val()+'",'
		}
		if($(cp_driver_zgs).val() != ''){
			globalQueryCondition += '"baId":"'+$(cp_driver_zgs).val()+'",'
		}
		new Pagination(page,pageSize,configDriver).first();
	}
	this.domUsers = null;
	this.users = function(){
		//				<td>超时<input type="checkbox" id=cp_driver_cs_flag value="cs" name=cp_driver_cs_flag></td>\
		var table = '<table id=cp_users style="display:none">\
			<tr>\
			<td>姓名</td>\
			<td><span class="cds_textbox" ><input id=cp_users_name type="text" class="cds_textbox-text" ></span></td>\
			<td>总公司</td>\
			<td><span class="cds_textbox" ><input id=cp_users_zgs type="text" class="cds_textbox-text" ></span></td>\
			<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			this.domUsers =  $(table)[0]
			$(this.domUsers).find('a').bind('click',function(){
				ff_users();
			})
	}
	function ff_users(){
		globalQueryCondition = '';
		if($(cp_users_name).val() != ''){
			globalQueryCondition += '"name":"'+$(cp_users_name).val()+'",'
		}
		if($(cp_users_zgs).val() != ''){
			globalQueryCondition += '"baId":"'+$(cp_users_zgs).val()+'",'
		}
		new Pagination(page,pageSize,configUsers).first();
	}
	
	this.domVehicle = null;
	this.vehicle = function(){
		//				<td>超时<input type="checkbox" id=cp_driver_cs_flag value="cs" name=cp_driver_cs_flag></td>\
		var table = '<table id=cp_vehicle style="display:none">\
			<tr>\
			<td>车号</td>\
			<td><span class="cds_textbox" ><input id=cp_vehicle_no type="text" class="cds_textbox-text" ></span></td>\
			<td>总公司</td>\
			<td><span class="cds_textbox" ><input id=cp_vehicle_zgs type="text" class="cds_textbox-text" ></span></td>\
			<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			this.domVehicle =  $(table)[0]
			$(this.domVehicle).find('a').bind('click',function(){
				ff_vehicle();
			})
	};
	function ff_vehicle(){
		globalQueryCondition = '';
		if($(cp_vehicle_no).val() != ''){
			globalQueryCondition += '"no":"'+$(cp_vehicle_no).val()+'",'
		}
		if($(cp_vehicle_zgs).val() != ''){
			globalQueryCondition += '"baId":"'+$(cp_vehicle_zgs).val()+'",'
		}
		new Pagination(page,pageSize,configUsers).first();
	}
	
	this.domYj = null;
	this.yj = function(){
		//				<td>超时<input type="checkbox" id=cp_lzcf_cs_flag value="cs" name=cp_lzcf_cs_flag></td>\
		var table = '<table id=cp_yj style="display:none">\
			<tr>\
				<td>开始时间</td>\
				<td><span class="cds_textbox" ><input value = $1 id=cp_lzcf_lrsj_stime type="text"  class="cds_textbox-text" onclick="WdatePicker();"></span>\
				至 <span class="cds_textbox" ><input value = $2 id=cp_lzcf_lrsj_etime   type="text" class="cds_textbox-text" onclick="WdatePicker();"></span></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			table = table.replace('$1',new Date().getFullYear()+'-01-01').replace('$2',new Date().getFullYear()+'-12-31');
			this.domYj =  $(table)[0]
			$(this.domYj).find('a').bind('click',function(){
				ff_lzcfajxx();
			})
	}
	this.domBtjzj = null;
	this.btjzj = function(){
		//				<td>超时<input type="checkbox" id=cp_lzcf_cs_flag value="cs" name=cp_lzcf_cs_flag></td>\
		var table = '<table id=cp_btjzj style="display:none">\
			<tr>\
				<td>开始时间</td>\
				<td><span class="cds_textbox" ><input value = $1 id=cp_lzcf_lrsj_stime type="text"  class="cds_textbox-text" onclick="WdatePicker();"></span>\
				至 <span class="cds_textbox" ><input value = $2 id=cp_lzcf_lrsj_etime   type="text" class="cds_textbox-text" onclick="WdatePicker();"></span></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			table = table.replace('$1',new Date().getFullYear()+'-01-01').replace('$2',new Date().getFullYear()+'-12-31');
			this.domBtjzj =  $(table)[0]
			$(this.domBtjzj).find('a').bind('click',function(){
				ff_lzcfajxx();
			})
	}
	this.domYh = null;
	this.yh = function(){
		//				<td>超时<input type="checkbox" id=cp_lzcf_cs_flag value="cs" name=cp_lzcf_cs_flag></td>\
		var table = '<table id=cp_yh style="display:none">\
			<tr>\
			<td>车型</td>\
			<td><span class="cds_textbox" ><input id=cp_driver_name type="text" class="cds_textbox-text" ></span></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			table = table.replace('$1',new Date().getFullYear()+'-01-01').replace('$2',new Date().getFullYear()+'-12-31');
			this.domYh =  $(table)[0]
			$(this.domYh).find('a').bind('click',function(){
				ff_lzcfajxx();
			})
	}
	//
	this.domDriveryd = null;
	this.driveryd = function(){
		//				<td>超时<input type="checkbox" id=cp_lzcf_cs_flag value="cs" name=cp_lzcf_cs_flag></td>\
		var table = '<table id=cp_driveryd style="display:none">\
			<tr>\
			<td>异动时间</td>\
			<td><span class="cds_textbox" ><input value = $1 id=cp_lzcf_lrsj_stime type="text"  class="cds_textbox-text" onclick="WdatePicker();"></span>\
			至 <span class="cds_textbox" ><input value = $2 id=cp_lzcf_lrsj_etime   type="text" class="cds_textbox-text" onclick="WdatePicker();"></span></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			table = table.replace('$1',new Date().getFullYear()+'-01-01').replace('$2',new Date().getFullYear()+'-12-31');
			this.domDriveryd =  $(table)[0]
			$(this.domDriveryd).find('a').bind('click',function(){
				ff_lzcfajxx();
			})
	}
	this.domVehicleyd = null;
	this.vehicleyd = function(){
		var table = '<table id=cp_vehicleyd  style="display:none">\
			<tr>\
			<td>异动时间</td>\
			<td><span class="cds_textbox" ><input value = $1 id=cp_lzcf_lrsj_stime type="text"  class="cds_textbox-text" onclick="WdatePicker();"></span>\
			至 <span class="cds_textbox" ><input value = $2 id=cp_lzcf_lrsj_etime   type="text" class="cds_textbox-text" onclick="WdatePicker();"></span></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			table = table.replace('$1',new Date().getFullYear()+'-01-01').replace('$2',new Date().getFullYear()+'-12-31');
			this.domVehicleyd =  $(table)[0]
			$(this.domVehicleyd).find('a').bind('click',function(){
				ff_lzcfajxx();
			})
	}
	this.domUsersyd = null;
	this.usersyd = function(){
		//				<td>超时<input type="checkbox" id=cp_lzcf_cs_flag value="cs" name=cp_lzcf_cs_flag></td>\
		var table = '<table id=cp_usersyd style="display:none">\
			<tr>\
			<td>异动时间</td>\
			<td><span class="cds_textbox" ><input value = $1 id=cp_lzcf_lrsj_stime type="text"  class="cds_textbox-text" onclick="WdatePicker();"></span>\
			至 <span class="cds_textbox" ><input value = $2 id=cp_lzcf_lrsj_etime   type="text" class="cds_textbox-text" onclick="WdatePicker();"></span></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			table = table.replace('$1',new Date().getFullYear()+'-01-01').replace('$2',new Date().getFullYear()+'-12-31');
			this.domUsersyd =  $(table)[0]
			$(this.domUsersyd).find('a').bind('click',function(){
				ff_lzcfajxx();
			})
	}
	this.domBcb = null;
	this.bcb = function(){
		//				<td>超时<input type="checkbox" id=cp_lzcf_cs_flag value="cs" name=cp_lzcf_cs_flag></td>\
		var table = '<table id=cp_bcb style="display:none">\
			<tr>\
			<td>车号</td>\
			<td><span class="cds_textbox" ><input id=cp_bcb_no type="text" class="cds_textbox-text" ></span></td>\
			<td>司机</td>\
			<td><span class="cds_textbox" ><input id=cp_bcb_driver_id type="text" class="cds_textbox-text" ></span></td>\
			<td>日期</td>\
			<td><span class="cds_textbox" ><input id=cp_bcb_datetime type="text"  class="cds_textbox-text" onclick="WdatePicker();"></span>\
			</td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			this.domBcb =  $(table)[0]
			$(this.domBcb).find('a').bind('click',function(){
				ff_bcb();
			})
	}
	function ff_bcb(){
		globalQueryCondition = '';
		console.log('query.........1')
		globalQueryCondition = '';
		if($(cp_bcb_no).val() != ''){
			globalQueryCondition += '"no":"'+$(cp_bcb_no).val()+'",'
		}
		if($(cp_bcb_driver_id).val() != ''){
			globalQueryCondition += '"driverId":"'+$(cp_bcb_driver_id).val()+'",'
		}
		if($(cp_bcb_datetime).val() != ''){
			globalQueryCondition += '"datetime":"'+$(cp_bcb_datetime).val()+'",'
		}
		new Pagination(page,pageSize,configBcb).first();
	}
	
	
	this.domBtjs = null;
	this.btjs = function(){
		//				<td>超时<input type="checkbox" id=cp_lzcf_cs_flag value="cs" name=cp_lzcf_cs_flag></td>\
		var table = '<table id=cp_btjs style="display:none">\
			<tr>\
			<td>车号</td>\
			<td><span class="cds_textbox" ><input id=cp_btjs_no type="text" class="cds_textbox-text" ></span></td>\
			<td>司机</td>\
			<td><span class="cds_textbox" ><input id=cp_btjs_driver_id type="text" class="cds_textbox-text" ></span></td>\
			<td>日期</td>\
			<td><span class="cds_textbox" ><input id=cp_btjs_datetime type="text"  class="cds_textbox-text" onclick="WdatePicker();"></span>\
			</td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			this.domBtjs =  $(table)[0]
			$(this.domBtjs).find('a').bind('click',function(){
				ff_btjs();
			})
	}
	function ff_btjs(){
		globalQueryCondition = '';
		console.log('query.........1')
		globalQueryCondition = '';
		if($(cp_btjs_no).val() != ''){
			globalQueryCondition += '"no":"'+$(cp_btjs_no).val()+'",'
		}
		if($(cp_btjs_driver_id).val() != ''){
			globalQueryCondition += '"driverId":"'+$(cp_btjs_driver_id).val()+'",'
		}
		if($(cp_btjs_datetime).val() != ''){
			globalQueryCondition += '"datetime":"'+$(cp_btjs_datetime).val()+'",'
		}
		new Pagination(page,pageSize,configBtjs).first();
	}
	
	this.domBtjsmon = null;
	this.btjsmon = function(){
		var table = '<table id=cp_btjsmon style="display:none">\
			<tr>\
			<td>司机</td>\
			<td><span class="cds_textbox" ><input id=cp_btjsmon_driver_id type="text" class="cds_textbox-text" ></span></td>\
			<td>日期</td>\
			<td><span class="cds_textbox" ><input id=cp_btjsmon_datetime type="text"  class="cds_textbox-text" onclick="WdatePicker();"></span>\
			</td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			this.domBtjsmon =  $(table)[0]
			$(this.domBtjsmon).find('a').bind('click',function(){
				ff_btjsmon();
			})
	}
	function ff_btjsmon(){
		globalQueryCondition = '';
		console.log('query.........1')
		globalQueryCondition = '';
		if($(cp_btjsmon_driver_id).val() != ''){
			globalQueryCondition += '"driverId":"'+$(cp_btjsmon_driver_id).val()+'",'
		}
		if($(cp_btjsmon_datetime).val() != ''){
			globalQueryCondition += '"datetime":"'+$(cp_btjsmon_datetime).val()+'",'
		}
		new Pagination(page,pageSize,configBtjsmon).first();
	}
	
	
	
	this.domBt = null;
	this.bt = function(){
		//				<td>超时<input type="checkbox" id=cp_lzcf_cs_flag value="cs" name=cp_lzcf_cs_flag></td>\
		var table = '<table id=cp_bt style="display:none">\
			<tr>\
			<td>姓名</td>\
			<td><span class="cds_textbox" ><input id=cp_driver_name type="text" class="cds_textbox-text" ></span></td>\
			<td>日期</td>\
			<td>异动时间</td>\
			<td><span class="cds_textbox" ><input value = $1 id=cp_lzcf_lrsj_stime type="text"  class="cds_textbox-text" onclick="WdatePicker();"></span>\
			至 <span class="cds_textbox" ><input value = $2 id=cp_lzcf_lrsj_etime   type="text" class="cds_textbox-text" onclick="WdatePicker();"></span></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
			<tr>\
			</table>';
			table = table.replace('$1',new Date().getFullYear()+'-01-01').replace('$2',new Date().getFullYear()+'-12-31');
			this.domBt =  $(table)[0]
			$(this.domBt).find('a').bind('click',function(){
				ff_lzcfajxx();
			})
	}
	this.domBclr = null;
	this.bclr = function(){
		var table = '<table id=cp_bclr style="display:none">\
			<tr>\
			<td>\
			<div class="select_border">\
			<div class="select_container">\
			<select class="select" id=cp_bclr_year ><option value="2014" >2014年</option><option value="2015"  >2015年</option><option value="2016">2016年</option></select>\
			</div>\
			</div></td>\
			<td>\
			<div class="select_border">\
			<div class="select_container">\
			<select class="select" id=cp_bclr_mon ><option value="1" >1月</option><option value="2">2月</option><option value="3">3月</option><option value="4" >4月</option>\
			<option value="5">5月</option><option value="6">6月</option><option value="7" >7月</option><option value="8">8月</option><option value="9">9月\
			<option value="10" >10月</option><option value="11">11月</option><option value="12">12月</option>\
			</option></select>\
			</div>\
			</div></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">生成模板</span></a></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">查  询</span></a></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">保  存</span></a></td>\
				<td><a href="javascript:void(0)" class="cds_btn"><span class="cds_btn_text">删  除</span></a></td>\
			<tr>\
			</table>';
			this.domBclr =  $(table)[0]
			var aas = $(this.domBclr).find('a');
			$(aas[0]).bind('click',function(){
				cacheExcelData = null;
				genExcel($('#cp_bclr_mon').val());
			})
			$(aas[1]).bind('click',function(){
				excelLoad();
			});
			$(aas[2]).bind('click',function(){
				excelSave();
			});
			$(aas[3]).bind('click',function(){
				delExcel();
			});
			$(this.domBclr).find('#cp_bclr_mon').val(getOffsetMon(new Date(),-1));
			$(this.domBclr).find('#cp_bclr_year').val(new Date().getFullYear());
	}
	this.init = function(){
		var o = this;
		var arrays = [];
		for(var p in o){
		    if(typeof(o[p])=='function'){
		    	if(p != 'init'){
		    		o[p]();
		    	}
		    }
		}
		for(var p in o){
		    if(typeof(o[p])!='function'){
		    	arrays.push(o[p]);
		    }
		}
		$(arrays).each(function(index,item){
			$(mainSplitterRightTop).append(item);
		});
		return this;
	}
	
}

