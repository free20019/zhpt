var findyyinfoGrid;
require(
		[ "app/util", "dgrid/Editor", "dijit/form/Button",
				"dijit/form/DateTextBox", "dijit/form/Select",
				"dijit/form/FilteringSelect", "dgrid/OnDemandGrid",
				"dijit/form/TextBox", "app/Pagination", "dgrid/Selection",
				"dgrid/Keyboard", "dgrid/extensions/ColumnResizer",
				"app/createAsyncStore", "dstore/Memory",
				"dgrid/extensions/DijitRegistry", "dijit/registry",
				"dojo/dom-style", "dojo/_base/declare", "dojo/dom-construct",
				"dojo/domReady!" ],
		function(util, Editor, Button, DateTextBox, Select, FilteringSelect,
				Grid, TextBox, Pagination, Selection, Keyboard, ColumnResizer,
				createAsyncStore, Memory, DijitRegistry, registry, domStyle,
				declare, dc) {
			var store = null, count = 0;
			dc.place(dc.create("span", {
				innerHTML : "起止时间",
				style : {
					"margin-right" : "5px"
				}
			}), 'findyyinfodiv');
			var yyxx_stime = new TextBox({
				id : "yyxx_stime",
				value : setformat(new Date((new Date()).getTime() - 1000 * 60
						* 60 * 2)),
				onClick : function() {
					WdatePicker({
						startDate : '%y-%M-01 00:00:00',
						dateFmt : 'yyyy-MM-dd HH:mm:ss',
						alwaysUseStartDate : true
					})
				},
				style : {
					"width" : "184px",
					"margin-right" : "15px"
				}
			}).placeAt('findyyinfodiv');
			dc.place(dc.create("span", {
				innerHTML : "至",
				style : {
					"margin-right" : "5px"
				}
			}), 'findyyinfodiv');
			var yyxx_etime = new TextBox({
				id : "yyxx_etime",
				value : setformat(new Date()),
				onClick : function() {
					WdatePicker({
						startDate : '%y-%M-01 00:00:00',
						dateFmt : 'yyyy-MM-dd HH:mm:ss',
						alwaysUseStartDate : true
					})
				},
				style : {
					"width" : "184px",
					"margin-right" : "15px"
				}
			}).placeAt('findyyinfodiv');
//			dc.place(dc.create("span", {
//				innerHTML : "公司",
//				style : {
//					"margin-right" : "5px"
//				}
//			}), 'findyyinfodiv');
//			var yyinfogs_id = new TextBox({ 	id : "yyinfogs_id" }).placeAt('findyyinfodiv');
			dc.place(dc.create("span", {
				innerHTML : "车号",
				style : {
					"margin-right" : "5px"
				}
			}), 'findyyinfodiv');
			var yyinfovehi_comp = new TextBox({
				id : "yyinfovehi_comp "}).placeAt('findyyinfodiv');
			var queryCondition = {
				"yyxx_stime" : yyxx_stime,
				"yyxx_etime" : yyxx_etime,
//				"gs_id" : yyinfogs_id,
				"vehi_no" : yyinfovehi_comp
			};
			var findyyinfobutton=new Button({
				label : "查询",
				onClick : function() {
					var stime = new Date(dojo.byId('yyxx_stime').value), etime = new Date(dojo.byId('yyxx_etime').value);
					if(stime.getFullYear()!=etime.getFullYear()) {
						alert('拒绝跨年查询,请查询当月的信息');
						return false;
					}
					if (stime.getMonth()!=etime.getMonth()) {
						alert('拒绝跨月查询,请查询当月的信息');
						return false;
					}
                    this.showWait();
                    var postData = {
						"page" : 1,
						"pageSize" : 10
					};

					for(var o in queryCondition){
						if(o == "yyxx_stime" || o == "yyxx_etime"){
							postData[o] = dojo.byId(o).value;
						}else{
							postData[o] = queryCondition[o].value;
						}
					}
					dojo.xhrPost(dojo.mixin(xhrArgs, {
						"postData" : 'postData=' + dojo.toJson(postData)
					}));
				}
			}).placeAt('findyyinfodiv');
			new Button({
				label : "导  出",
				onClick : function() {
					var postData = {
						"page" : 1,
						"pageSize" : 10000
					};
					for(var o in queryCondition){
						if(o == "yyxx_stime" || o == "yyxx_etime"){
							postData[o] = dojo.byId(o).value;
						}else{
							postData[o] = queryCondition[o].value;
						}
					}
					url = "hygl/findyyinfoexcle?postData="
						+ dojo.toJson(postData), window.open(url)
				}
			}).placeAt('findyyinfodiv');
			var columns = [ 
				{field:'dojoId',label : "序号"},
				{field : 'VEHI_NO',
				label : "车牌号"
			}, {
				field : 'YINGYUN',
				label : "资格证号"
			}, {
				field : 'SHANGCHE',
				label : "上车时间"
					,formatter : util.formatYYYYMMDDHHMISS
			}, {
				field : 'XIACHE',
				label : "下车时间"
					,formatter : util.formatYYYYMMDDHHMISS
			}, {
				field : 'JINE',
				label : "营运金额"
			}, {
				field : 'JICHENG',
				label : "计程（公里）"
			}, {
				field : 'KONGSHI',
				label : "空驶（公里）"
			}, {
				field : 'DENGHOU',
				label : "等候（秒）"
			}, {
				field : 'JIAOYITYPE',
				label : "交易类型"
			}

			];

			var xhrArgs = {
				url : basePath + "hygl/findyyinfo",
				postData : 'postData={"page":1,"pageSize":'+pageSize+'}',
				handleAs : "json",
				load : function(data) {
					console.log(data)
                    findyyinfobutton.hideWait();
					findyyinfoGrid.totalCount = data.count;
					for (var i = 0; i < data.datas.datas.length; i++) {
						data.datas.datas[i] = dojo.mixin({
							dojoId : i + 1
						}, data.datas.datas[i]);
					}
					store = new Memory({
						data : {
							identifier : 'dojoId',
							label : 'dojoId',
							items : data.datas.datas
						}
					});
					findyyinfoGrid.set('collection', store);
					findyyinfoGrid.pagination.refresh(data.datas.datas.length);
				},
			};
			var CustomGrid = declare([ Grid, Keyboard, ColumnResizer, Selection ]);
			findyyinfoGrid = new CustomGrid({
				totalCount : 0,
				columns : columns,
				pagination : null
			}, "findyyinfotable");
			findyyinfoGrid.refresh2 = function() {
				dojo.xhrPost(xhrArgs);
			}
			// dojo.xhrPost(xhrArgs);

			var pageii = new Pagination(findyyinfoGrid, xhrArgs, queryCondition);

			dc.place(pageii.first(), 'findyyinfotable', 'after');
		});
// 时间
function setformat(obj) {
	var y = (obj.getFullYear()).toString();
	var m = (obj.getMonth() + 1).toString();
	if (m.length == 1) {
		m = "0" + m;
	}
	var d = obj.getDate().toString();
	if (d.length == 1) {
		d = "0" + d;
	}
	var h = obj.getHours().toString();
	if (h.length == 1) {
		h = "0" + h;
	}
	var mi = obj.getMinutes().toString();
	if (mi.length == 1) {
		mi = "0" + mi;
	}
	var s = obj.getSeconds().toString();
	if (s.length == 1) {
		s = "0" + s;
	}
	var time = y + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s;
	return time;
}
/**
 * 公司
 * 
 * @returns
 */
function queryGs() {
	var xhrArgs = {
		url : basePath + "common/findcomp",
		postData : 'postData={"page":1,"pageSize":9999}',
		handleAs : "json"
	};
	return dojo.xhrPost(xhrArgs);
}
/**
 * 车辆 下拉框
 * 
 * @returns
 */
function queryVhicById(compName) {
	if (compName == "") {
		return
	}
	var xhrArgs = {
		url : basePath + "common/findvhic",
		postData : 'postData={"ownid":"' + compName + '"}',
		handleAs : "json"
	};
	return dojo.xhrPost(xhrArgs);
}
