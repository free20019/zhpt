
////////callback
function cbGhajxx(po){
	
	$('#panelXq').remove();
	var ghajxxDom = $('<table>'
			+'<tr>'
			+'<td class="bgcolor">ID</td><td class="tdclass">'+po.json['ID']+'</td>'
			+'<td class="bgcolor">立案日期</td><td class="tdclass">'+new Date(po.json['LARQ']).format("yyyy-MM-dd")+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">名称</td><td class="tdclass">'+po.json['GMXM']+'</td>'
			+'<td class="bgcolor">单位</td><td class="tdclass">'+po.json['DWMC']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">案由</td><td class="tdclass">'+po.json['AY']+'</td>'
			+'<td class="bgcolor">案件类别</td><td class="tdclass">'+po.json['AJLB']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">发案地点</td><td class="tdclass">'+po.json['FADD']+'</td>'
			+'<td class="bgcolor">执法编号</td><td class="tdclass">'+po.json['ZFBH']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">承办人</td><td class="tdclass">'+po.json['CBR']+'</td>'
			+'<td class="bgcolor">承办人代码</td><td class="tdclass">'+po.json['CBRDWDM']+'</td>'
			+'</tr><tr>'
			+'</table>');
	
	var lcjdDom = $('<div>lcjdDom</div>');
	var gdDom = $('<div>......</div>');
	
	var panelXq = $('<div id="panelXq" >'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>').appendTo('body');
	var titles = ['案件信息','流程信息','归档信息'];
	var contents   = [ghajxxDom[0],lcjdDom[0],gdDom[0]];
	var urls = ['','ghlcjd/get?postData={"id":"'+po.json.ID+'","page":1,"pageSize":100}',''];
	var callbacks = [null,cbLcjdGhAjxx,null];
	var tabNav = new TabNav(titles,contents,urls,callbacks);	
	panelXq.append(tabNav.dom).appendTo('body');
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	});
	
}

function cbLzcf(po){
	$('#panelXq').remove();
	var lzcfAjxxDom = $('<table>'
			+'<tr>'
			+'<td class="bgcolor">ID</td><td class="tdclass">'+po.json['fdObjectid']+'</td>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass">'+po.json['partydept']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">姓名</td><td class="tdclass">'+po.json['partyname']+'</td>'
			+'<td class="bgcolor">性别</td><td class="tdclass">'+po.json['partysex']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">单位法定代表人</td><td class="tdclass">'+po.json['partywork']+'</td>'
			+'<td class="bgcolor">车号</td><td class="tdclass">'+po.json['plates']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">案由</td><td class="tdclass">'+(po.json['casereason']==4?'超限运输':po.json['casereason'])+'</td>'
			+'<td class="bgcolor">处罚金额</td><td class="tdclass">'+po.json['nbFinaldueend']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">车辆类型</td><td class="tdclass">'+po.json['cartype']+'</td>'
			+'<td class="bgcolor">车籍地</td><td class="tdclass">'+po.json['vehicle']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">检测人员1</td><td class="tdclass">'+po.json['checkpersonone']+'</td>'
			+'<td class="bgcolor">检测人员2</td><td class="tdclass">'+po.json['checkpersontwo']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">询问人员1</td><td class="tdclass">'+po.json['askpersonone']+'</td>'
			+'<td class="bgcolor">询问人员2</td><td class="tdclass">'+po.json['askpersontwo']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">停车场</td><td class="tdclass">'+po.json['parking']+'</td>'
			+'<td class="bgcolor">营运证号</td><td class="tdclass">'+po.json['operationno']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">治超站点</td><td class="tdclass">'+po.json['overloadside']+'</td>'
			+'<td class="bgcolor">司机</td><td class="tdclass">'+po.json['driver']+'</td>'
			+'</tr><tr>'
			+'</table>');
			
	var lcjdDom = $('<table class=lcjdtable></table>')
	lcjdDom.append('<tr  style="font-weight: bold;"><td style="width:20%">处理人</td><td style="width:20%">处理时间</td><td style="width:60%">当前节点</td></tr>');
	if(po.json.handleperson ==''){
		lcjdDom = $('<div style="margin: 5px 5px;">还没有流程信息</div>');
	}else{
		lcjdDom.append('<tr><td>'+po.json.handleperson+'</td><td>'+new Date(po.json.decisiontime).format("yyyy-MM-dd")+'</td><td>'+gllcjdmb[po.json.status]+'</td></tr>');
	}
	
	var gdDom = $('<div>......</div>');
	var panelXq = $('<div id="panelXq" >'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>').appendTo('body');
	var titles = ['案件信息','流程信息','归档信息'];
	var contents   = [lzcfAjxxDom[0],lcjdDom[0],gdDom[0]];
	var urls = ['','',''];
	var callbacks = [null,null,null];
	var tabNav = new TabNav(titles,contents,urls,callbacks);	
	panelXq.append(tabNav.dom).appendTo('body');
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	});
}

//function cbZfAjxx(po){
//	$('#panelXq').remove();
//	$('<div id="panelXq" >'
//			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
//			+'<div style="height: 91%;overflow: auto;"><table>'
//			+'<tr>'
//			+'<td class="bgcolor">案件案号</td><td class="tdclass">'+po.json['ajah']+'</td>'
//			+'<td class="bgcolor">执法机构</td><td class="tdclass">'+po.json['zfjg']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">稽察日期</td><td class="tdclass">'+po.json['jcrq']+'</td>'
//			+'<td class="bgcolor">稽查地点</td><td class="tdclass">'+po.json['jcdd']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">处罚程序</td><td class="tdclass">'+po.json['cfcx']+'</td>'
//			+'<td class="bgcolor">案件来源</td><td class="tdclass">'+po.json['ajly']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">车牌号</td><td class="tdclass">'+po.json['cph']+'</td>'
//			+'<td class="bgcolor">车辆类型</td><td class="tdclass">'+po.json['cllx']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">车籍所在地</td><td class="tdclass">'+po.json['cjszd']+'</td>'
//			+'<td class="bgcolor">吨位</td><td class="tdclass">'+po.json['cldw']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">座位数</td><td class="tdclass">'+po.json['clzws']+'</td>'
//			+'<td class="bgcolor">运输证号</td><td class="tdclass">'+po.json['clyszh']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">业户许可证号</td><td class="tdclass">'+po.json['yhxkzh']+'</td>'
//			+'<td class="bgcolor">业户名称</td><td class="tdclass">'+po.json['yhmc']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">当事人姓名</td><td class="tdclass">'+po.json['dsrxm']+'</td>'
//			+'<td class="bgcolor">性别</td><td class="tdclass">'+po.json['dsrxb']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">年龄</td><td class="tdclass">'+po.json['dsrnl']+'</td>'
//			+'<td class="bgcolor">联系电话</td><td class="tdclass">'+po.json['dsrlxdh']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">证件类型</td><td class="tdclass">'+po.json['dsrzjlx']+'</td>'
//			+'<td class="bgcolor">证件号</td><td class="tdclass">'+po.json['dsrzjh']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">执法证号1</td><td class="tdclass">'+po.json['zfzh1']+'</td>'
//			+'<td class="bgcolor">执法人员1</td><td class="tdclass">'+po.json['zfry1']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">执法证号2</td><td class="tdclass">'+po.json['zfzh2']+'</td>'
//			+'<td class="bgcolor">执法人员2</td><td class="tdclass">'+po.json['zfry2']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">违法事实</td><td class="tdclass">'+po.json['wfss']+'</td>'
//			+'<td class="bgcolor">罚款总额</td><td class="tdclass">'+po.json['fkze']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">案件状态</td><td class="tdclass">'+po.json['ajzt']+'</td>'
//			+'<td class="bgcolor">结案意见</td><td class="tdclass">'+po.json['jayj']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">创建人</td><td class="tdclass">'+po.json['cjr']+'</td>'
//			+'<td class="bgcolor">创建日期</td><td class="tdclass">'+po.json['cjrq']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">修改人</td><td class="tdclass">'+po.json['xgr']+'</td>'
//			+'<td class="bgcolor">修改日期</td><td class="tdclass">'+po.json['xgrq']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">备注</td><td class="tdclass">'+po.json['bz']+'</td>'
//			+'<td class="bgcolor">所属机构标识</td><td class="tdclass">'+po.json['jgbs']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">业户或个人</td><td class="tdclass">'+po.json['yhsx']+'</td>'
//			+'<td class="bgcolor">公民姓名</td><td class="tdclass">'+po.json['gmxm']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">公民证件类型</td><td class="tdclass">'+po.json['gmzjlx']+'</td>'
//			+'<td class="bgcolor">公民证件号</td><td class="tdclass">'+po.json['gmzjh']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">公民性别</td><td class="tdclass">'+po.json['gmxb']+'</td>'
//			+'<td class="bgcolor">公民出生日期</td><td class="tdclass">'+po.json['gmcsrq']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">公民联系电话</td><td class="tdclass">'+po.json['gmlxdh']+'</td>'
//			+'<td class="bgcolor">公民联系地址</td><td class="tdclass">'+po.json['gmjtdz']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">罚字名称</td><td class="tdclass">'+po.json['fzmc']+'</td>'
//			+'<td class="bgcolor">案例库</td><td class="tdclass">'+po.json['alk']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">结案案号</td><td class="tdclass">'+po.json['jaah']+'</td>'
//			+'<td class="bgcolor">结案日期</td><td class="tdclass">'+po.json['jarq']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">有效期</td><td class="tdclass">'+po.json['yxq']+'</td>'
//			+'<td class="bgcolor">暂扣备注</td><td class="tdclass">'+po.json['zkbz']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">违章代码</td><td class="tdclass">'+po.json['wzdm']+'</td>'
//			+'<td class="bgcolor">案由</td><td class="tdclass">'+po.json['ay']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">案别</td><td class="tdclass">'+po.json['ab']+'</td>'
//			+'<td class="bgcolor">违反条例</td><td class="tdclass">'+po.json['wftl']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">处罚依据</td><td class="tdclass">'+po.json['cfyj']+'</td>'
//			+'<td class="bgcolor">暂扣起始日期</td><td class="tdclass">'+po.json['zkrq']+'</td>'
//			+'</tr><tr>'
//			+'<td class="bgcolor">驾驶证号</td><td class="tdclass">'+po.json['jszh']+'</td>'
//			+'<td class="bgcolor">从业资格证号</td><td class="tdclass">'+po.json['cyzgzh']+'</td>'
//			+'<tr>'
//			+'</table>'
//			+'</div>').appendTo('body')
//			
//	$('#panelXqClose').click(function(){
//		$('#panelXq').remove();
//	})
//}

//修改 调整
function cbZfAjxx2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" >'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+'<div style="height: 91%;overflow: auto;"><table>'
			+'<tr>'
			+'<td class="bgcolor">执法车辆</td><td class="tdclass">'+po.json['zfcl']+'</td>'
			+'<td class="bgcolor">案由明细</td><td class="tdclass">'+po.json['aymx']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">预期处理时间</td><td class="tdclass">'+po.json['yqclsj']+'</td>'
			+'<td class="bgcolor">备用1(处罚种)</td><td class="tdclass">'+po.json['by1']+'</td>'
			+'</tr>'
			+'</table>'
			+'</div>').appendTo('body')
			
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

function cbZfAjxx(po){
	$('#panelXq').remove();
	var ajxxDom = $('<table>'
			+'<tr>'
			+'<td class="bgcolor">案件案号</td><td class="tdclass">'+po.json['AJAH']+'</td>'
			+'<td class="bgcolor">执法机构</td><td class="tdclass">'+po.json['ZFJG']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">稽察日期</td><td class="tdclass">'+po.json['JCRQ']+'</td>'
			+'<td class="bgcolor">稽查地点</td><td class="tdclass">'+po.json['JCDD']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">车牌号</td><td class="tdclass">'+po.json['CPH']+'</td>'
			+'<td class="bgcolor">运输证号</td><td class="tdclass">'+po.json['CLYSZH']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">车籍所在地</td><td class="tdclass">'+po.json['CJSZD']+'</td>'
			+'<td class="bgcolor">吨位</td><td class="tdclass">'+po.json['CLDW']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">业户许可证号</td><td class="tdclass">'+po.json['YHXKZH']+'</td>'
			+'<td class="bgcolor">业户名称</td><td class="tdclass">'+po.json['YHMC']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">当事人姓名</td><td class="tdclass">'+po.json['DSRXM']+'</td>'
			+'<td class="bgcolor">性别</td><td class="tdclass">'+po.json['DSRXB']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">年龄</td><td class="tdclass">'+po.json['DSRNL']+'</td>'
			+'<td class="bgcolor">联系电话</td><td class="tdclass">'+po.json['DSRLXDH']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">证件类型</td><td class="tdclass">'+po.json['DSRZJLX']+'</td>'
			+'<td class="bgcolor">证件号</td><td class="tdclass">'+po.json['DSRZJH']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">执法证号1</td><td class="tdclass">'+po.json['ZFZH1']+'</td>'
			+'<td class="bgcolor">执法人员1</td><td class="tdclass">'+po.json['ZFRY1']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">执法证号2</td><td class="tdclass">'+po.json['ZFZH2']+'</td>'
			+'<td class="bgcolor">执法人员2</td><td class="tdclass">'+po.json['ZFRY2']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">违法事实</td><td class="tdclass">'+po.json['WFSS']+'</td>'
			+'<td class="bgcolor">罚款总额</td><td class="tdclass">'+po.json['FKZE']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">案件状态</td><td class="tdclass">'+po.json['AJZT']+'</td>'
			+'<td class="bgcolor">结案意见</td><td class="tdclass">'+po.json['JAYJ']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">创建人</td><td class="tdclass">'+po.json['CJR']+'</td>'
			+'<td class="bgcolor">创建日期</td><td class="tdclass">'+po.json['CJRQ']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">修改人</td><td class="tdclass">'+po.json['XGR']+'</td>'
			+'<td class="bgcolor">修改日期</td><td class="tdclass">'+po.json['XGRQ']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">备注</td><td class="tdclass">'+po.json['BZ']+'</td>'
			+'<td class="bgcolor">所属机构标识</td><td class="tdclass">'+po.json['JGBS']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">业户或个人</td><td class="tdclass">'+po.json['YHSX']+'</td>'
			+'<td class="bgcolor">公民姓名</td><td class="tdclass">'+po.json['GMXM']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">公民证件类型</td><td class="tdclass">'+po.json['GMZJLX']+'</td>'
			+'<td class="bgcolor">公民证件号</td><td class="tdclass">'+po.json['GMZJH']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">公民性别</td><td class="tdclass">'+po.json['GMXB']+'</td>'
			+'<td class="bgcolor">公民出生日期</td><td class="tdclass">'+po.json['GMCSRQ']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">公民联系电话</td><td class="tdclass">'+po.json['GMLXDH']+'</td>'
			+'<td class="bgcolor">公民联系地址</td><td class="tdclass">'+po.json['GMJTDZ']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">罚字名称</td><td class="tdclass">'+po.json['FZMC']+'</td>'
			+'<td class="bgcolor">案例库</td><td class="tdclass">'+po.json['ALK']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">结案案号</td><td class="tdclass">'+po.json['JAAH']+'</td>'
			+'<td class="bgcolor">结案日期</td><td class="tdclass">'+po.json['JARQ']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">有效期</td><td class="tdclass">'+po.json['YXQ']+'</td>'
			+'<td class="bgcolor">暂扣备注</td><td class="tdclass">'+po.json['ZKBZ']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">案别</td><td class="tdclass">'+po.json['AB']+'</td>'
			+'<td class="bgcolor">违反条例</td><td class="tdclass">'+po.json['WFTL']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">处罚依据</td><td class="tdclass">'+po.json['CFYJ']+'</td>'
			+'<td class="bgcolor">暂扣起始日期</td><td class="tdclass">'+po.json['ZKRQ']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">驾驶证号</td><td class="tdclass">'+po.json['JSZH']+'</td>'
			+'<td class="bgcolor">从业资格证号</td><td class="tdclass">'+po.json['CYZGZH']+'</td>'
			+'<tr>'
			+'</table>');
	
//	var subLcjdUrl ='cllc/get?postData={"ajxxh":"'+po.guid+'","page":1,"pageSize":100}';
//	$.get(subLcjdUrl,function(data){
//		var result =  eval('('+data+')');
//		var datas = result.datas;
//		var lcjd = $('<table class=lcjdtable></table>')
//		lcjd.append('<tr  style="font-weight: bold;"><td style="width:20%">处理人</td><td style="width:20%">处理时间</td><td style="width:60%">当前节点</td></tr>');
//		if(datas.length ==0){
//			lcjd = $('<div style="margin: 5px 5px;">还没有流程信息</div>');
//		}
//		for(var i=0;i<datas.length;i++){
//			lcjd.append('<tr><td>'+datas[i]["clr"]+'</td><td>'+datas[i]["cjrq"]+'</td><td>'+yglcjdmb[datas[i]["ajdqzt"]]+'</td></tr>');
//		}
//	});
	
	var lcjdDom = $('<div>lcjdDom</div>');
	var gdDom = $('<div>......</div>');
	
	var panelXq = $('<div id="panelXq" >'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>').appendTo('body');
	var titles = ['案件信息','流程信息','归档信息'];
	var contents   = [ajxxDom[0],lcjdDom[0],gdDom[0]];
	var urls = ['','cllc/get?postData={"ajxxh":"'+po.json['GUID']+'","page":1,"pageSize":100}',''];
	var callbacks = [null,cbLcjdAjxx,null];
	var tabNav = new TabNav(titles,contents,urls,callbacks);	
	panelXq.append(tabNav.dom).appendTo('body');
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	});
}


function cbZfCfjds(po){
	$('#panelXq').remove();
	$('<div id="panelXq" >'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+'<div style="height: 91%;overflow: auto;"><table>'
			+'<tr>'
			+'<td class="bgcolor">当事人</td><td style="width: 35%;" class="tdclass">'+po.json['dsr']+'</td>'
			+'<td class="bgcolor">处罚决定书号</td><td  style="width:15% "> '+po.json['cfjdsh']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">证件号码</td><td class="tdclass">'+po.json['zjhm']+'</td>'
			+'<td class="bgcolor">创建日期</td><td class="tdclass">'+po.json['cjrq']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">稽查地点</td><td class="tdclass">'+po.json['jcdd']+'</td>'
			+'<td class="bgcolor"></td><td>'+''+'</td>'
			+'</table>'
			+'</div>').appendTo('body')
			
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}


function cbZfCllc(po){
	$('#panelXq').remove();
	$('<div id="panelXq" >'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+'<div style="height: 91%;overflow: auto;"><table>'
			+'<tr>'
			+'<td class="bgcolor">当前状态</td><td style="width: 35%;" class="tdclass">'+po.json['ajdqzt']+'</td>'
			+'<td class="bgcolor">处理状态</td><td style="width: 35%;" class="tdclass">'+po.json['ajclzt']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">处理人</td><td class="tdclass">'+po.json['clr']+'</td>'
			+'<td class="bgcolor">创建日期</td><td class="tdclass">'+po.json['cjrq']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">处理意见</td><td class="tdclass">'+po.json['clyj']+'</td>'
			+'<td class="bgcolor"></td><td>'+''+'</td>'
			+'</table>'
			+'</div>').appendTo('body')
			
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}


function cbJcFlfg(po){
	$('#panelXq').remove();
	$('<div id="panelXq" >'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+'<div style="height: 91%;overflow: auto;"><table>'
			+'<tr>'
			+'<td class="bgcolor">违章代码</td><td style="width: 35%;" class="tdclass">'+po.json['wzdm']+'</td>'
			+'<td class="bgcolor">PID</td><td style="width: 35%;" class="tdclass">'+po.json['pid']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">违规名称</td><td class="tdclass">'+po.json['wgmc']+'</td>'
			+'<td class="bgcolor">违规内容</td><td class="tdclass">'+po.json['wgnr']+'</td>'
			+'</tr>'
			+'</table>'
			+'</div>').appendTo('body')
			
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

function cbZfLadjb(po){
	$('#panelXq').remove();
	$('<div id="panelXq" >'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 91%;overflow: auto;"><table>'
			+'<tr>'
			+'<td class="bgcolor">案件案号</td><td class="tdclass">'+po.json['ajah']+'</td>'
			+'<td class="bgcolor">发案时间</td><td class="tdclass">'+po.json['fasj']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">发案地点</td><td class="tdclass">'+po.json['fadd']+'</td>'
			+'<td class="bgcolor">事实及理由</td><td class="tdclass">'+po.json['ssjly']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">承办人意见</td><td class="tdclass">'+po.json['cbryj']+'</td>'
			+'<td class="bgcolor">领导批示</td><td class="tdclass">'+po.json['ldps']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">创建人</td><td class="tdclass">'+po.json['cjr']+'</td>'
			+'<td class="bgcolor">创建日期</td><td class="tdclass">'+po.json['cjrq']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">修改人</td><td class="tdclass">'+po.json['xgr']+'</td>'
			+'<td class="bgcolor">修改日期</td><td class="tdclass">'+po.json['xgrq']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">备注</td><td class="tdclass">'+po.json['bz']+'</td>'
			+'<td class="bgcolor">公民姓名</td><td class="tdclass">'+po.json['gm']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">性别</td><td class="tdclass">'+po.json['xb']+'</td>'
			+'<td class="bgcolor">年龄</td><td class="tdclass">'+po.json['nl']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">证件号</td><td class="tdclass">'+po.json['zjh']+'</td>'
			+'<td class="bgcolor">地址</td><td class="tdclass">'+po.json['dz']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">电话</td><td class="tdclass">'+po.json['dh']+'</td>'
			+'<td class="bgcolor">车辆类型</td><td class="tdclass">'+po.json['cllx']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">车籍所在地</td><td class="tdclass">'+po.json['cjszd']+'</td>'
			+'<td class="bgcolor">车牌号</td><td class="tdclass">'+po.json['cph']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">违章联系人</td><td class="tdclass">'+po.json['wzlxr']+'</td>'
			+'<td class="bgcolor">违章联系人性别</td><td class="tdclass">'+po.json['wzlxrxb']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">违章联系人年龄</td><td class="tdclass">'+po.json['wzlxrnl']+'</td>'
			+'<td class="bgcolor">违章联系人证件号</td><td class="tdclass">'+po.json['wzlxrzjh']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">违章联系人地址</td><td class="tdclass">'+po.json['wzlxrdz']+'</td>'
			+'<td class="bgcolor">违章联系人电话</td><td class="tdclass">'+po.json['wzlxrdh']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">领导姓名（执法人员1）</td><td class="tdclass">'+po.json['ldxm']+'</td>'
			+'<td class="bgcolor">领导签名日期</td><td class="tdclass">'+po.json['ldqmrq']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">承办人签名</td><td class="tdclass">'+po.json['cbrqm']+'</td>'
			+'<td class="bgcolor">承办人签名日期</td><td class="tdclass">'+po.json['cbrqmrq']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass">'+po.json['dwmc']+'</td>'
			+'<td class="bgcolor">单位地址</td><td class="tdclass">'+po.json['dwdz']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">法定代表人</td><td class="tdclass">'+po.json['fddbr']+'</td>'
			+'<td class="bgcolor">单位电话</td><td class="tdclass">'+po.json['dwdh']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">具体执法部门</td><td class="tdclass">'+po.json['jtzfbm']+'</td>'
			+'<td class="bgcolor">备用1</td><td class="tdclass">'+po.json['by1']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">备用2</td><td class="tdclass">'+po.json['by2']+'</td>'
			+'<td class="bgcolor">备用3</td><td class="tdclass">'+po.json['by3']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">备用4</td><td class="tdclass">'+po.json['by4']+'</td>'
			+'<td class="bgcolor">备用5</td><td class="tdclass">'+po.json['by5']+'</td>'
			+'</tr>'
			+	'</table>'
			+'</div>').appendTo('body')
			
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

function cbZfLadjb2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" >'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 91%;overflow: auto;"><table>'
			+'<tr>'
			+'<td class="bgcolor">主键</td><td class="tdclass">'+po.json['guid']+'</td>'
			+'<td class="bgcolor">案件信息编号</td><td class="tdclass">'+po.json['ajxxbh']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">案件案号</td><td class="tdclass">'+po.json['ajah']+'</td>'
			+'<td class="bgcolor">发案时间</td><td class="tdclass">'+po.json['fasj']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">发案地点</td><td class="tdclass">'+po.json['fadd']+'</td>'
			+'<td class="bgcolor">事实及理由</td><td class="tdclass">'+po.json['ssjly']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">承办人意见</td><td class="tdclass">'+po.json['cbryj']+'</td>'
			+'<td class="bgcolor">领导批示</td><td class="tdclass">'+po.json['ldps']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">创建人</td><td class="tdclass">'+po.json['cjr']+'</td>'
			+'<td class="bgcolor">创建日期</td><td class="tdclass">'+po.json['cjrq']+'</td>'
			+'</tr><tr>'
			+'</tr>'
			+	'</table>'
			+'</div>').appendTo('body')
			
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}



function cbJhAjjbxx(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height: 250px;width: 600px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 220px;overflow: auto;"><table>'
			+'<tr>'
			+'<td class="bgcolor">流水号</td><td class="tdclass" class="tdclass">'+po.json['msgId']+'</td>'
			+'<td class="bgcolor">开始日期</td><td class="tdclass" class="tdclass">'+po.json['insertDatetime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">更新日期</td><td class="tdclass" class="tdclass">'+po.json['updateDatatime']+'</td>'
			+'<td class="bgcolor">是否本系统</td><td class="tdclass" class="tdclass">'+(po.json['islocalsystem']=='false'?'不是':'是')+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">方案ID</td><td class="tdclass" class="tdclass">'+po.json['activityschemeguid']+'</td>'
			+'<td class="bgcolor">案件实例编号</td><td class="tdclass" class="tdclass">'+po.json['assemblynum']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">环节编号</td><td class="tdclass" class="tdclass">'+po.json['activitymodelstepid']+'</td>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass" class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">案件标题</td><td class="tdclass" class="tdclass">'+po.json['subject']+'</td>'
			+'<td class="bgcolor">单位编号</td><td class="tdclass" class="tdclass">'+po.json['companyid']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass" class="tdclass">'+po.json['companyname']+'</td>'
			+'<td class="bgcolor">部门编号</td><td class="tdclass" class="tdclass">'+po.json['deptid']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">部门名称</td><td class="tdclass" class="tdclass">'+po.json['deptname']+'</td>'
			+'<td class="bgcolor">处罚当事人</td><td class="tdclass" class="tdclass">'+po.json['punishTarget']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">当事人类型</td><td class="tdclass" class="tdclass">'+po.json['litiganttype']+'</td>'
			+'<td class="bgcolor">表格内容</td><td class="tdclass" class="tdclass">'+po.json['form']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">办理人编号</td><td class="tdclass" class="tdclass">'+po.json['userId']+'</td>'
			+'<td class="bgcolor">办理人姓名</td><td class="tdclass" class="tdclass">'+po.json['userName']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">当前案由</td><td class="tdclass" class="tdclass">'+po.json['enforcecase']+'</td>'
			+'<td class="bgcolor">立案时间</td><td class="tdclass" class="tdclass">'+po.json['punishRegtime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">立案系统时间</td><td class="tdclass" class="tdclass">'+po.json['punishRegsystime']+'</td>'
			+'<td class="bgcolor">业务类型编码</td><td class="tdclass" class="tdclass">'+po.json['businesstype']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">岗位状态</td><td class="tdclass" class="tdclass">'+po.json['status']+'</td>'
			+'<td class="bgcolor">案件编号</td><td class="tdclass" class="tdclass">'+po.json['caseno']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">全局预警</td><td class="tdclass" class="tdclass">'+po.json['globalwarningtime']+'</td>'
			+'<td class="bgcolor">全局超时</td><td class="tdclass" class="tdclass">'+po.json['promise']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">告知日期</td><td class="tdclass" class="tdclass">'+po.json['apprizetime']+'</td>'
			+'<td class="bgcolor">告知系统日期</td><td class="tdclass" class="tdclass">'+po.json['apprizesystime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">听证日期</td><td class="tdclass" class="tdclass">'+po.json['provetime']+'</td>'
			+'<td class="bgcolor">听证系统日期</td><td class="tdclass" class="tdclass">'+po.json['provesystime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">处罚日期</td><td class="tdclass" class="tdclass">'+po.json['punishtime']+'</td>'
			+'<td class="bgcolor">处罚系统日期</td><td class="tdclass" class="tdclass">'+po.json['punishsystime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">申请强制日期</td><td class="tdclass" class="tdclass">'+po.json['applyforcetime']+'</td>'
			+'<td class="bgcolor">结案日期</td><td class="tdclass" class="tdclass">'+po.json['finishtime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">结案系统日期</td><td class="tdclass" class="tdclass">'+po.json['finishsystime']+'</td>'
			+'<td class="bgcolor">归档日期</td><td class="tdclass" class="tdclass">'+po.json['torecordtime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">事项编码</td><td class="tdclass" class="tdclass">'+po.json['businessid']+'</td>'
			+'<td class="bgcolor">重大案件标识</td><td class="tdclass" class="tdclass">'+po.json['greatcase']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">案源ID</td><td class="tdclass" class="tdclass">'+po.json['sourceid']+'</td>'
			+'<td class="bgcolor">处罚金额</td><td class="tdclass" class="tdclass">'+po.json['enforceMoney']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">地区名称</td><td class="tdclass" class="tdclass">'+po.json['regionname']+'</td>'
			+'<td class="bgcolor">地区编码</td><td class="tdclass" class="tdclass">'+po.json['regioncode']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">是否垂管</td><td class="tdclass" class="tdclass">'+(po.json['ismanage']==1?'是':'不是')+'</td>'
			+'<td class="bgcolor">是否被删除</td><td class="tdclass" class="tdclass">'+po.json['isdelete']+'</td>'
			+'</tr>'
			+	'</table>'
			+'</div>').appendTo('body')
			
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}


//调整
function cbJhAjjbxx2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">ID</td><td class="tdclass" class="tdclass">'+po.json['msgId']+'</td>'
			+'<td class="bgcolor">超时时间</td><td><input style="width:96%" type=text value="'+po.json['promise']+'"></td>'
			+'</tr><tr>'
			+'<td class="bgcolor">处罚当事人</td><td class="tdclass" class="tdclass">'+po.json['punishTarget']+'</td>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass" class="tdclass">'+po.json['companyname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">案件标题</td><td class="tdclass" class="tdclass">'+po.json['subject']+'</td>'
			+'<td class="bgcolor">立案时间</td><td class="tdclass" class="tdclass">'+po.json['punishRegtime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">节点</td><td class="tdclass" class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">地区编码</td><td class="tdclass" class="tdclass">'+po.json['regioncode']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">是否垂管</td><td class="tdclass" class="tdclass">'+(po.json['ismanage']==1?'是':'不是')+'</td>'
			+'<td class="bgcolor">更新日期</td><td class="tdclass" class="tdclass">'+po.json['updateDatatime']+'</td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		
		var td  = document.getElementById("theTable").getElementsByTagName("td")
	
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_ajjbxx/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[3].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['promise']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

function cbJhXzcfxx2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">岗位状态</td><td class="tdclass">'+po.json['status']+'</td>'
			+'<td class="bgcolor">部门名称</td><td class="tdclass">'+po.json['deptname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass">'+po.json['companyname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">处理意见</td><td class="tdclass">'+po.json['note']+'</td>'
			+'<td class="bgcolor">环节超时时间</td><td><input style="width:96%" type=text value="'+po.json['promise']+'"></td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		//alert(po.json['promise']=="")
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_xzcfgcxx/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[11].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['promise']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}


function cbJhXzcfcsxx2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass">'+po.json['companyname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">环节开始时间</td><td class="tdclass">'+po.json['startdate']+'</td>'
			+'<td class="bgcolor">环节结束时间</td><td class="tdclass">'+po.json['enddate']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">超时时间</td><td><input style="width:96%" type=text value="'+po.json['timeoutdate']+'"></td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_xzcfcsxx/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[9].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['timeoutdate']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

function cbJhAyxx2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr><tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">更新日期</td><td><input style="width:96%" type=text value="'+po.json['updateDatatime']+'"></td>'
			+'</tr><tr>'
			+'<td class="bgcolor">案源标题</td><td class="tdclass">'+po.json['sourcetitle']+'</td>'
			+'<td class="bgcolor">案源登记时间</td><td class="tdclass">'+po.json['sourcetime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">部门名称</td><td class="tdclass">'+po.json['deptname']+'</td>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass">'+po.json['companyname']+'</td>'
			+'</tr>'
			+	'</table>'
			
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_ayxx/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[3].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['updateDatatime']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

function cbJhAjbgxx2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">地区名称</td><td class="tdclass">'+po.json['regionname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">办理人姓名</td><td class="tdclass">'+po.json['userName']+'</td>'
			+'<td class="bgcolor">变更日期</td><td><input style="width:96%" type=text value="'+po.json['changetime']+'"></td>'
			+'</tr><tr>'
			+'<td class="bgcolor">变更理由</td><td class="tdclass">'+po.json['changereason']+'</td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_ajbgxx/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[7].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['changetime']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhTsczxx2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">办理人姓名</td><td class="tdclass">'+po.json['userName']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">地区名称</td><td class="tdclass">'+po.json['regionname']+'</td>'
			+'<td class="bgcolor">操作类型编码</td><td class="tdclass">'+po.json['casetype']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">操作日期</td><td><input style="width:96%" type=text value="'+po.json['changetime']+'"></td>'
			+'<td class="bgcolor">操作理由</td><td class="tdclass">'+po.json['changereason']+'</td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_tsczxx/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[9].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['changetime']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhCfwg2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">办理人姓名</td><td class="tdclass">'+po.json['userName']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">地区名称</td><td class="tdclass">'+po.json['regionname']+'</td>'
			+'<td class="bgcolor">处罚幅度名称</td><td class="tdclass">'+po.json['punishrangename']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">处罚日期</td><td><input style="width:96%" type=text value="'+po.json['createtime']+'"></td>'
			+'<td class="bgcolor">处罚金额</td><td class="tdclass">'+po.json['punishmoney']+'</td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_cfwg/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[9].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['createtime']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhBjbzfryzg2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">环节编号</td><td class="tdclass">'+po.json['activitymodelstepid']+'</td>'
			+'<td class="bgcolor">办理人编号</td><td class="tdclass">'+po.json['userId']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">地区名称</td><td class="tdclass">'+po.json['regionname']+'</td>'
			+'<td class="bgcolor">写入时间</td><td class="tdclass">'+po.json['createtime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">环节结束时间</td><td class="tdclass">'+po.json['enddate']+'</td>'
			+'<td class="bgcolor">超时时间</td><td><input style="width:96%" type=text value="'+po.json['timeoutdate']+'"></td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_bjbzg/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[11].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['timeoutdate']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhZdaj2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">事项名称</td><td class="tdclass">'+po.json['businessname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">用户姓名</td><td class="tdclass">'+po.json['userName']+'</td>'
			+'<td class="bgcolor">原因</td><td class="tdclass">'+po.json['greatcasereason']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">写入时间</td><td><input style="width:96%" type=text value="'+po.json['createdate']+'"></td>'
			+'<td class="bgcolor">案由</td><td class="tdclass">'+po.json['enforcecase']+'</td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_zdaj/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[9].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['createdate']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhFkyj2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">信息类型</td><td class="tdclass">'+po.json['messagetype']+'</td>'
			+'<td class="bgcolor">信息标题</td><td class="tdclass">'+po.json['messageTitle']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">信息内容</td><td class="tdclass">'+po.json['messagecontent']+'</td>'
			+'<td class="bgcolor">发布时间</td><td><input style="width:96%" type=text value="'+po.json['messagetime']+'"></td>'
			+'</tr><tr>'
			+'<td class="bgcolor">发布人姓名</td><td class="tdclass">'+po.json['sendusername']+'</td>'
			+'<td class="bgcolor">接收人姓名</td><td class="tdclass">'+po.json['receiveusername']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">监察人姓名</td><td class="tdclass">'+po.json['monitorusername']+'</td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_fkyj/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[7].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['messagetime']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}


	function cbJhFkyjgc2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">是否本系统</td><td class="tdclass">'+(po.json['islocalsystem']=='false'?'不是':'是')+'</td>'
			+'<td class="bgcolor">信息类型</td><td class="tdclass">'+po.json['messagetype']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">信息标题</td><td class="tdclass">'+po.json['messageTitle']+'</td>'
			+'<td class="bgcolor">信息内容</td><td class="tdclass">'+po.json['messagecontent']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">发布时间</td><td><input style="width:96%" type=text value="'+po.json['messagetime']+'"></td>'
			+'<td class="bgcolor">发布人姓名</td><td class="tdclass">'+po.json['sendusername']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">接收人姓名</td><td class="tdclass">'+po.json['receiveusername']+'</td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_fkyjgc/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[9].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['messagetime']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhJczb2(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table id="theTable">'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">案件标题</td><td class="tdclass">'+po.json['title']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass">'+po.json['companyname']+'</td>'
			+'<td class="bgcolor">部门名称</td><td class="tdclass">'+po.json['deptname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">办理人姓名</td><td class="tdclass">'+po.json['userName']+'</td>'
			+'<td class="bgcolor">写入时间</td><td><input style="width:96%" type=text value="'+po.json['insertDatetime']+'"></td>'
			+'</tr>'
			+	'</table>'
			+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		var td  = document.getElementById("theTable").getElementsByTagName("td")
		//alert(td[3].getElementsByTagName("input")[0].value)
		$.get("jh_jczb/updateTime?"+
				"msgId="+po.json['msgId']+
				"&promise="+td[11].getElementsByTagName("input")[0].value+
				"&oldPromise="+po.json['insertDatetime']+
				"&ssjg="+po.json['ssjg']
				,function(data){
			currentPagination.getData();
		});
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

////////////////////////////////////////////
function cbJhXzcfxx(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:600px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">岗位状态</td><td class="tdclass">'+po.json['status']+'</td>'
			+'<td class="bgcolor">部门名称</td><td class="tdclass">'+po.json['deptname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass">'+po.json['companyname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">处理意见</td><td class="tdclass">'+po.json['note']+'</td>'
			+'<td class="bgcolor">环节超时时间</td><td class="tdclass">'+po.json['promise']+'</td>'
			+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}


function cbJhXzcfcsxx(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass">'+po.json['companyname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">环节开始时间</td><td class="tdclass">'+po.json['startdate']+'</td>'
			+'<td class="bgcolor">环节结束时间</td><td class="tdclass">'+po.json['enddate']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">超时时间</td><td class="tdclass">'+po.json['timeoutdate']+'</td>'
		//	+'<td class="bgcolor">超时时间</td><td class="tdclass">'+po.json['timeoutdate']+'</td>'
			+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

function cbJhAyxx(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">案源标题</td><td class="tdclass">'+po.json['sourcetitle']+'</td>'
			+'<td class="bgcolor">案源登记时间</td><td class="tdclass">'+po.json['sourcetime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">部门名称</td><td class="tdclass">'+po.json['deptname']+'</td>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass">'+po.json['companyname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			//+'<td class="bgcolor">超时时间</td><td class="tdclass">'+po.json['timeoutdate']+'</td>'
			+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

function cbJhAjbgxx(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">地区名称</td><td class="tdclass">'+po.json['regionname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">办理人姓名</td><td class="tdclass">'+po.json['userName']+'</td>'
			+'<td class="bgcolor">变更日期</td><td class="tdclass">'+po.json['changetime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">变更理由</td><td class="tdclass">'+po.json['changereason']+'</td>'
			//+'<td class="bgcolor">超时时间</td><td class="tdclass">'+po.json['timeoutdate']+'</td>'
			+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhTsczxx(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">办理人姓名</td><td class="tdclass">'+po.json['userName']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">地区名称</td><td class="tdclass">'+po.json['regionname']+'</td>'
			+'<td class="bgcolor">操作类型编码</td><td class="tdclass">'+po.json['casetype']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">操作日期</td><td class="tdclass">'+po.json['changetime']+'</td>'
			+'<td class="bgcolor">操作理由</td><td class="tdclass">'+po.json['changereason']+'</td>'
			+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhCfwg(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">办理人姓名</td><td class="tdclass">'+po.json['userName']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">地区名称</td><td class="tdclass">'+po.json['regionname']+'</td>'
			+'<td class="bgcolor">处罚幅度名称</td><td class="tdclass">'+po.json['punishrangename']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">处罚日期</td><td class="tdclass">'+po.json['createtime']+'</td>'
			+'<td class="bgcolor">处罚金额</td><td class="tdclass">'+po.json['punishmoney']+'</td>'
			+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhBjbzfryzg(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">环节编号</td><td class="tdclass">'+po.json['activitymodelstepid']+'</td>'
			+'<td class="bgcolor">办理人编号</td><td class="tdclass">'+po.json['userId']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">地区名称</td><td class="tdclass">'+po.json['regionname']+'</td>'
			+'<td class="bgcolor">写入时间</td><td class="tdclass">'+po.json['createtime']+'</td>'
			+'</tr>'
			//+'<td class="bgcolor">环节结束时间</td><td class="tdclass">'+po.json['enddate']+'</td>'
			//+'<td class="bgcolor">超时时间</td><td class="tdclass">'+po.json['timeoutdate']+'</td>'
		//	+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhZdaj(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">事项名称</td><td class="tdclass">'+po.json['businessname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">用户姓名</td><td class="tdclass">'+po.json['userName']+'</td>'
			+'<td class="bgcolor">原因</td><td class="tdclass">'+po.json['greatcasereason']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">写入时间</td><td class="tdclass">'+po.json['createdate']+'</td>'
			+'<td class="bgcolor">案由</td><td class="tdclass">'+po.json['enforcecase']+'</td>'
			+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhFkyj(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">信息类型</td><td class="tdclass">'+po.json['messagetype']+'</td>'
			+'<td class="bgcolor">信息标题</td><td class="tdclass">'+po.json['messageTitle']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">信息内容</td><td class="tdclass">'+po.json['messagecontent']+'</td>'
			+'<td class="bgcolor">发布时间</td><td class="tdclass">'+po.json['messagetime']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">发布人姓名</td><td class="tdclass">'+po.json['sendusername']+'</td>'
			+'<td class="bgcolor">接收人姓名</td><td class="tdclass">'+po.json['receiveusername']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">监察人姓名</td><td class="tdclass">'+po.json['monitorusername']+'</td>'
			+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}


	function cbJhFkyjgc(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">是否本系统</td><td class="tdclass">'+(po.json['islocalsystem']=='false'?'不是':'是')+'</td>'
			+'<td class="bgcolor">信息类型</td><td class="tdclass">'+po.json['messagetype']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">信息标题</td><td class="tdclass">'+po.json['messageTitle']+'</td>'
			+'<td class="bgcolor">信息内容</td><td class="tdclass">'+po.json['messagecontent']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">发布时间</td><td class="tdclass">'+po.json['messagetime']+'</td>'
			+'<td class="bgcolor">发布人姓名</td><td class="tdclass">'+po.json['sendusername']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">接收人姓名</td><td class="tdclass">'+po.json['receiveusername']+'</td>'
			+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

	function cbJhJczb(po){
	$('#panelXq').remove();
	$('<div id="panelXq" style="background: #FFF;border: #6B83A1 1px solid;height:200px;width:650px;position: absolute;top: 20%;left: 30%;">'
			+'<div class=panelXqTitle><span>'+po.title+'</span><a id="panelXqClose" ></a></div>'
			+ '<div style="height: 155px;overflow: auto;padding: 5px 5px;"><table>'
			+'<tr>'
			+'<td class="bgcolor">环节名称</td><td class="tdclass">'+po.json['activitymodelstepname']+'</td>'
			+'<td class="bgcolor">案件标题</td><td class="tdclass">'+po.json['title']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">单位名称</td><td class="tdclass">'+po.json['companyname']+'</td>'
			+'<td class="bgcolor">部门名称</td><td class="tdclass">'+po.json['deptname']+'</td>'
			+'</tr><tr>'
			+'<td class="bgcolor">办理人姓名</td><td class="tdclass">'+po.json['userName']+'</td>'
			+'<td class="bgcolor">指标名称</td><td class="tdclass">'+po.json['targetname']+'</td>'
			+'</tr>'
			+	'</table>').appendTo('body')
			//+'<div style="text-align: center;margin: 15 0 0;"><button id="panelXqSubmit">提交</button></div>'
			//+'</div>').appendTo('body')
	$('#panelXqSubmit').click(function(){
		//TODO 提交
		$('#panelXq').remove();
	})		
	$('#panelXqClose').click(function(){
		$('#panelXq').remove();
	})
}

function TabNav(titles,tabs,urls,callbacks){
	this.dom = $('<div style="height: 91%;overflow: auto;"></div>')
	this.titles = titles;
	this.tabs = tabs;
	this.urls = urls;
	this.callbacks = callbacks;
	this.show =function(index){
		var _tabs = $(this.dom).find('#tabNavHeader span');
		_tabs.hide();
		_tabs[index].show();
	}
	this.init =function(){
		//TODO is arrays ?
		if(titles.length != tabs.length){
			return
		}
		var tabNavHeader = $('<div id=tabNavHeader></div>');
		for(var i=0;i<titles.length;i++){
			var span = $('<span class=tabNavHeader_item>'+titles[i]+'</span>')[0]
			if(i==0){
				$(span).css('font-weight','bold');
			}
			tabNavHeader.append(span);
			$(span).bind('click',{'index':i,'url':this.urls[i],'callback':this.callbacks[i]},function(event){
				$('#tabNavHeader .tabNavHeader_item').css('font-weight','normal');
				$(this).css('font-weight','bold');
				var _tabs = $('#tabNavContent .tabNavContent_item');
				_tabs.hide();
				if(event.data.url != ''){
					$.get(event.data.url,function(data){
//						var result =  eval('('+data+')');
//						var datas = result.datas;
//						var lcjd = $('<table class=lcjdtable></table>')
//						lcjd.append('<tr  style="font-weight: bold;"><td style="width:20%">处理人</td><td style="width:20%">处理时间</td><td style="width:60%">当前节点</td></tr>');
//						if(datas.length ==0){
//							lcjd = $('<div style="margin: 5px 5px;">还没有流程信息</div>');
//						}
//						for(var i=0;i<datas.length;i++){
//							lcjd.append('<tr><td>'+datas[i]["clr"]+'</td><td>'+datas[i]["cjrq"]+'</td><td>'+yglcjdmb[datas[i]["ajdqzt"]]+'</td></tr>');
//						}
						var lcjd = event.data.callback(data);
						_tabs[event.data.index].innerHTML = lcjd[0].outerHTML
						$(_tabs[event.data.index]).show();
					});
				}else{
					$(_tabs[event.data.index]).show();
				}
			});
		}
		var tabNavContent = $('<div id=tabNavContent ></div>');
		for(var i=0;i<tabs.length;i++){
			var item = $('<div class=tabNavContent_item>'+tabs[i].outerHTML+'</div>')
			if( i != 0 ){
				item.css('display','none');
			}
			tabNavContent.append(item)
		}
		$(this.dom).append(tabNavHeader[0]).append(tabNavContent[0]);
		return this;
	}
	return this.init();
}

function cbLcjdAjxx(data){
	var result =  eval('('+data+')');
	var datas = result.datas;
	var lcjd = $('<table class=lcjdtable></table>')
	lcjd.append('<tr  style="font-weight: bold;"><td style="width:20%">处理人</td><td style="width:20%">处理时间</td><td style="width:60%">当前节点</td></tr>');
	if(datas.length ==0){
		lcjd = $('<div style="margin: 5px 5px;">还没有流程信息</div>');
	}
	for(var i=0;i<datas.length;i++){
		lcjd.append('<tr><td>'+datas[i]["clr"]+'</td><td>'+datas[i]["cjrq"]+'</td><td>'+yglcjdmb[datas[i]["ajdqzt"]]+'</td></tr>');
	}
	return lcjd;
}

function cbLcjdGhAjxx(data){
	var result =  eval('('+data+')');
	var datas = result.datas;
	var lcjd = $('<table class=lcjdtable></table>')
	lcjd.append('<tr  style="font-weight: bold;"><td style="width:20%">处理人</td><td style="width:20%">处理时间</td><td style="width:60%">当前节点</td></tr>');
	if(datas.length ==0){
		lcjd = $('<div style="margin: 5px 5px;">还没有流程信息</div>');
	}
	for(var i=0;i<datas.length;i++){
		lcjd.append('<tr><td>'+datas[i]["realname"]+'</td><td>'+datas[i]["nodedate"]+'</td><td>'+datas[i]["working"]+'</td></tr>');
	}
	return lcjd;
}