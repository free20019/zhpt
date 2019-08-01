<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- http://127.0.0.1:8080/taximonitor/jsp/hkvideo.action?yh=1 -->
<!DOCTYPE HTML >
<html lang="zh-cn" style="height:100%">
<head>
<link rel="stylesheet" type="text/css"
	href="../css/zTreeStyle/zTreeStyle.css" />
<script src="../js/libs/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../js/jquery.resizableColumns.js"></script>
</head>
<body style="height: 99%;">
	<div style="height: 99%;">

		<table width="100%" height="99%" class="table listext"
			data-resizable-columns-id="demo-table">
			<thead>
				<tr>
					<th style="white-space: nowrap;" data-resizable-column-id="#">车辆列表</th>
					<th style="white-space: nowrap;"
						data-resizable-column-id="first_name">视频监控</th>
					<th style="white-space: nowrap;"
						data-resizable-column-id="last_name">操作列表</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td
						style="white-space: nowrap;width: 200px;height: 100%;vertical-align:top">
						<div style="height: 550px;overflow-y: auto;overflow-x: hidden;"
							title="车辆列表">
							<div class="queryVehicle">
								<input type="text" placeholder="" id="vediokeyword">
								<button onclick="getteam();" id="cxbtn">查询</button>

							</div>


							<ul id="databanzu" class="ztree">
							</ul>

						</div>
					</td>
					<td style="white-space: nowrap;"><div style='height: 100%;'
							id="hksp">
							<table cellpadding='0' cellspacing='0' border='1'
								bordercolor='#ffffff' width='100%' height="100%" align='center'>
								<tr style="height: 50%">
									<td><object id='v1'
											classid='clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778'
											width='100%' height='100%' style='margin: 0;'></object></td>
									<td><object id='v2'
											classid='clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778'
											width='100%' height='100%' style='margin: 0;'></object></td>
								</tr>
								<tr style="height: 50%">
									<td><object id='v3'
											classid='clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778'
											width='100%' height='100%' style='margin: 0;'></object></td>
									<td><object id='v4'
											classid='clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778'
											width='100%' height='100%' style='margin: 0;'></object></td>
								</tr>
							</table>
						</div></td>
					<td style="white-space: nowrap;width: 250px;vertical-align:top">
						<div style='width: 100%;' id="caozuo">
							<table class="cztab">
								<tr>
									<td>车牌</td>
									<td><input type="text" id="cxcp"
										style="width: 127px;height: 20px;"><input
										type="hidden" id="cxsbid"></td>
								</tr>
								<tr>
									<td>通道</td>
									<td><select style="width: 130px;height: 24px;" id="cxtd">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
									</select></td>
								</tr>
								<tr>
									<td>开始时间</td>
									<td><input type="text" id="stime"
										style="width: 127px;height: 20px;" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
								</tr>
								<tr>
									<td>结束时间</td>
									<td><input type="text" id="etime"
										style="width: 127px;height: 20px;" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: center;">
										<button class="czbtn" onclick="cxsp()">查询视频</button>
									</td>
								</tr>

								<tr>
									<td>查询结果</td>
									<td><select style="width: 130px;height: 24px;" id="cxjg">
									</select></td>
								</tr>
								<tr>
									<td colspan="2" style="text-align: center;">
										<button class="czbtn" onclick="DownloadByTime()">下载视频</button>
										<button class="czbtn" onclick="PlayBack()" id="hfbtn">回放</button>
									</td>
								</tr>
								<tr>
									<td>下载进度</td>
									<td><input type="text" id="xzjd"
										style="width: 87px;height: 20px;" value="0/100" readonly="readonly">
										<button class="czbtn" onclick="StopDownloadByTime()" id="xztz">停止下载</button>
										</td>
								</tr>
<!-- 								<tr> -->
<!-- 									<td>文件存放</td> -->
<!-- 									<td><input type="file" id="cflj" -->
<!-- 										style="width: 130px;height: 20px;" value="D:\\test.mp4"> -->
<!-- 										</td> -->
<!-- 								</tr> -->
							</table>
							<div id="aaa"></div>
						</div>
					</td>

				</tr>
			</tbody>
		</table>

	</div>
	<input type="hidden" id="RegIp" value="192.168.0.151">
	<!-- 注册服务器ip 端口port -->
	<input type="hidden" id="RegPort" value="7660">
	<input type="hidden" id="StreamIp" value="192.168.0.151">
	<!-- 流媒体服务器ip 端口port -->
	<input type="hidden" id="StreamPort" value="7554">
	<input type="hidden" id="AlarmIp" value="192.168.0.151">
	<!-- 报警服务器ip 端口port -->
	<input type="hidden" id="AlarmPort" value="7332">
	<input type="hidden" id="RegisterNo" value="2">
	<!-- 设备注册线路编号 -->
	<input type="hidden" id="StreamType" value="1">
	<!-- 流码类型 -->
	<object id='ppvs' classid='clsid:EE4EA829-B8DA-4229-AC72-585AF45AD778'
		width='100%' height='100%' style='margin: 0;display: none'></object>
	<script type="text/javascript">
	function setetime() {
		var edate = new Date();
		var y = (edate.getFullYear()).toString();
		var m = (edate.getMonth() + 1).toString();
		if (m.length == 1) {
			m = "0" + m;
		}
		var d = edate.getDate().toString();
		if (d.length == 1) {
			d = "0" + d;
		}
		var h = edate.getHours().toString();
		if (h.length == 1) {
			h = "0" + h;
		}
		var mi = edate.getMinutes().toString();
		if (mi.length == 1) {
			mi = "0" + mi;
		}
		var s = edate.getSeconds().toString();
		if (s.length == 1) {
			s = "0" + s;
		}
		var time = y + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s;
		return time;
	}
	function setstime() {
		var sdate = new Date();
		var y = (sdate.getFullYear()).toString();
		var m = (sdate.getMonth() + 1).toString();
		if (m.length == 1) {
			m = "0" + m;
		}
		var d = sdate.getDate().toString();
		if (d.length == 1) {
			d = "0" + d;
		}
		var time = y + "-" + m + "-" + d + " 00:00:00";
		return time;
	}
		window.onload = function() {
			$("table").resizableColumns({});
			getteam();
			try {
				//根据控件的PorgID来判断是否注册，未注册则抛出异常
				obj = new ActiveXObject("PPVSGUARD.PpvsguardCtrl.1");

				//设置窗口模式
				var PlayOCX = document.getElementById("v1");

				if (typeof PlayOCX == "undefined" || PlayOCX == null) {
					alert("海康视频控件已注册但页面对象不存在！");
					return;
				}
				PlayOCX.SetActiveXShowMode(0, 0);//实时预览模式，需要修改模式或者窗口UI可参考SetActiveXShowMode接口说明
			} catch (e) {
				alert("海康视频控件未注册或未安装！");
			}
			
		}
		
		
		function do_login() {
			document.getElementById("TLMDvrAx").LoginStyle = 0;
			var szServerIP = "192.168.0.82";
			var nSeverPort = "10000";
			var szUserName = "admin";
			var szPassWord = "njtynjty";
			var nRet = document.getElementById("TLMDvrAx").LoginServer(
					szServerIP, nSeverPort, szUserName, szPassWord);
			if (nRet) {
				document.getElementById("TLMDvrAx").ShowWndStyle = 4;
			} else {
				alert("登陆失败！");
			}
		}

		var treeNodes;
		var zTree;
		function getteam() {
			$("#stime").val(setstime());
			$("#etime").val(setetime());
			$.ajax({
				async : false,
				cache : false,
				url : 'queryvediovhic.action',
				type : 'post',
				data : {
					"keyword" : $("#vediokeyword").val()
				},
				dataType : 'json',
				timeout : 120000,
				success : function(json) {
					treeNodes = eval('(' + json.cars + ')');
					zTree = $.fn.zTree
							.init($("#databanzu"), setting, treeNodes);
				},
				error : function() {
					alert("提交失败!");
				}
			});
		}
		var playid1 = "0";
		var playid2 = "0";
		var playid3 = "0";
		var playid4 = "0";

		var oc = 0;

		var setting = {
			async : {
				enable : true,
				url : "queryvediovhic.action",
				dataType : "text",
				type : "post"
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : 0
				}
			},
			callback : {
				onClick : function(event, treeId, treeNode) {
					if (treeNode.lx == "hksp") {
						$("#hksp").css('display', '');
						$("#tysp").css('display', 'none');

						if (treeNode.id == "td1") {
							if (playid1 == "0") {
								treeNode.icon = "../img/video/mdton.png";
								zTree.updateNode(treeNode);
								playid1 = "1";
								Play1(treeNode.camera);
							} else {
								treeNode.icon = "../img/video/mdt.png";
								zTree.updateNode(treeNode);
								var obj = document.getElementById("v1");
								obj.StreamPlayStopByTCP();
								playid1 = "0";
							}
						} else if (treeNode.id == "td2") {
							if (playid2 == "0") {
								treeNode.icon = "../img/video/mdton.png";
								zTree.updateNode(treeNode);
								playid2 = "1";
								Play2(treeNode.camera);
							} else {
								treeNode.icon = "../img/video/mdt.png";
								zTree.updateNode(treeNode);
								var obj = document.getElementById("v2");
								obj.StreamPlayStopByTCP();
								playid2 = "0";
							}
						} else if (treeNode.id == "td3") {
							if (playid3 == "0") {
								treeNode.icon = "../img/video/mdton.png";
								zTree.updateNode(treeNode);
								playid3 = "1";
								Play3(treeNode.camera);
							} else {
								treeNode.icon = "../img/video/mdt.png";
								zTree.updateNode(treeNode);
								var obj = document.getElementById("v3");
								obj.StreamPlayStopByTCP();
								playid3 = "0";
							}
						} else if (treeNode.id == "td4") {
							if (playid4 == "0") {
								treeNode.icon = "../img/video/mdton.png";
								zTree.updateNode(treeNode);
								playid4 = "1";
								Play4(treeNode.camera);
							} else {
								treeNode.icon = "../img/video/mdt.png";
								zTree.updateNode(treeNode);
								var obj = document.getElementById("v4");
								obj.StreamPlayStopByTCP();
								playid4 = "0";
							}
						} else if (treeNode.pId == "hksp") {
							$("#cxcp").val(treeNode.name);
							$("#cxsbid").val(treeNode.camera);
						}
					} else if (treeNode.lx == "tysp") {
						$("#tysp").css('display', '');
						$("#hksp").css('display', 'none');
						do_login();
						if (oc == 0) {
							start_chn(treeNode.vehinum.substr(1),
									parseInt(treeNode.td));
							treeNode.icon = "../img/video/mdton.png";
							zTree.updateNode(treeNode);
							oc = 1;
						} else if (oc == 1) {
							stop_chn();
							treeNode.icon = "../img/video/mdt.png";
							zTree.updateNode(treeNode);
							oc = 0;
						}
					}
					//alert("dns:"+treeNode.camera);
				}
			}
		}
		var iPlayHandle1;
		var iPlayHandle2;
		var iPlayHandle3;
		var iPlayHandle4;
		function Play1(obj) {

			var szAccount = obj;
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("v1");

			var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount, szRegIP,
					iRegPort, "admin", "12345");

			if (iConnHandle < 0) {
				alert("连接设备失败！");
				return;
			}
			var iChannel = "1";
			var iRegisterNo = document.getElementById("RegisterNo").value;
			//流媒体服务器信息
			var szStreamServerIP = document.getElementById("StreamIp").value;
			var iStreamServerPort = document.getElementById("StreamPort").value;

			//有视频播放，则先停止
			if (iPlayHandle1 > 0) {
				PlayOCX.StreamPlayStopByTCP();
			}
			//设置设备注册线路编号，以iVMS-7200添加设备选择注册线路匹配
			PlayOCX.SetDeviceNetLine(iRegisterNo);
			//取流类型
			var iStreamType = document.getElementById("StreamType").value;

			//客户端到流媒体采用TCP，流媒体到设备采用TCP取流
			iPlayHandle1 = PlayOCX.StreamPlayStartByTCP(szStreamServerIP,
					iStreamServerPort, szAccount, iChannel, iStreamType, 1);

			//设置窗口的设备信息显示
			var szMode = "TCP";
			PlayOCX.SetDeviceInfoForShow(">>> 转发取流(" + szMode + ") <<<");
		}

		function Play2(obj) {

			var szAccount = obj;
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("v2");

			var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount, szRegIP,
					iRegPort, "admin", "12345");

			if (iConnHandle < 0) {
				alert("连接设备失败！");
				return;
			}
			var iChannel = "2";
			var iRegisterNo = document.getElementById("RegisterNo").value;
			//流媒体服务器信息
			var szStreamServerIP = document.getElementById("StreamIp").value;
			var iStreamServerPort = document.getElementById("StreamPort").value;

			//有视频播放，则先停止
			if (iPlayHandle2 > 0) {
				PlayOCX.StreamPlayStopByTCP();
			}
			//设置设备注册线路编号，以iVMS-7200添加设备选择注册线路匹配
			PlayOCX.SetDeviceNetLine(iRegisterNo);
			//取流类型
			var iStreamType = document.getElementById("StreamType").value;

			//客户端到流媒体采用TCP，流媒体到设备采用TCP取流
			iPlayHandle2 = PlayOCX.StreamPlayStartByTCP(szStreamServerIP,
					iStreamServerPort, szAccount, iChannel, iStreamType, 1);

			//设置窗口的设备信息显示
			var szMode = "TCP";
			PlayOCX.SetDeviceInfoForShow(">>> 转发取流(" + szMode + ") <<<");
		}
		function Play3(obj) {

			var szAccount = obj;
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("v3");

			var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount, szRegIP,
					iRegPort, "admin", "12345");

			if (iConnHandle < 0) {
				alert("连接设备失败！");
				return;
			}
			var iChannel = "3";
			var iRegisterNo = document.getElementById("RegisterNo").value;
			//流媒体服务器信息
			var szStreamServerIP = document.getElementById("StreamIp").value;
			var iStreamServerPort = document.getElementById("StreamPort").value;

			//有视频播放，则先停止
			if (iPlayHandle3 > 0) {
				PlayOCX.StreamPlayStopByTCP();
			}
			//设置设备注册线路编号，以iVMS-7200添加设备选择注册线路匹配
			PlayOCX.SetDeviceNetLine(iRegisterNo);
			//取流类型
			var iStreamType = document.getElementById("StreamType").value;

			//客户端到流媒体采用TCP，流媒体到设备采用TCP取流
			iPlayHandle3 = PlayOCX.StreamPlayStartByTCP(szStreamServerIP,
					iStreamServerPort, szAccount, iChannel, iStreamType, 1);

			//设置窗口的设备信息显示
			var szMode = "TCP";
			PlayOCX.SetDeviceInfoForShow(">>> 转发取流(" + szMode + ") <<<");
		}
		function Play4(obj) {

			var szAccount = obj;
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("v4");

			var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount, szRegIP,
					iRegPort, "admin", "12345");

			if (iConnHandle < 0) {
				alert("连接设备失败！");
				return;
			}
			var iChannel = "4";
			var iRegisterNo = document.getElementById("RegisterNo").value;
			//流媒体服务器信息
			var szStreamServerIP = document.getElementById("StreamIp").value;
			var iStreamServerPort = document.getElementById("StreamPort").value;

			//有视频播放，则先停止
			if (iPlayHandle4 > 0) {
				PlayOCX.StreamPlayStopByTCP();
			}
			//设置设备注册线路编号，以iVMS-7200添加设备选择注册线路匹配
			PlayOCX.SetDeviceNetLine(iRegisterNo);
			//取流类型
			var iStreamType = document.getElementById("StreamType").value;

			//客户端到流媒体采用TCP，流媒体到设备采用TCP取流
			iPlayHandle4 = PlayOCX.StreamPlayStartByTCP(szStreamServerIP,
					iStreamServerPort, szAccount, iChannel, iStreamType, 1);

			//设置窗口的设备信息显示
			var szMode = "TCP";
			PlayOCX.SetDeviceInfoForShow(">>> 转发取流(" + szMode + ") <<<");
		}

		function cxsp() {
			if ($("#cxcp").val() == "") {
				alert("请先在左边车辆列表点击车辆进行查询！");
				return;
			}
			var szAccount = $("#cxsbid").val();
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("ppvs");
			var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount, szRegIP,
					iRegPort, "admin", "12345");
			var iChannel = $("#cxtd").val();
			var stime = $("#stime").val();
			var etime = $("#etime").val();
			var szInfo = PlayOCX.PlaybackSearchFile(iChannel, 0, stime, etime);
			var a = $.parseXML(szInfo);
			$(a).find('File').each(
					function() {
						var name = $(this).children('FileName').text();
						var st = $(this).children('StartTime').text();
						var et = $(this).children('StopTime').text();
						var dx = $(this).children('FileSize').text();
						var opt = "<option value='"+name+"|"+dx+"|"+st+"|"+et+"'>" + name + "(" + st + "," + et
								+ ")</option>"
						$("#cxjg").append(opt);
					});
		}

		function PlayBack() {
			var hfxx = $("#cxjg").val();
			if(hfxx==null||hfxx==""){
				alert("没有远程录像，请先搜索录像！");
				return;
			}
			var szAccount = $("#cxsbid").val();;
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("v1");
			if ($("#hfbtn").html() == "回放") {
				var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount,
						szRegIP, iRegPort, "admin", "12345");
				var hf = hfxx.split("|");
				PlayOCX.StartPlaybackByFile("1", hf[0], hf[1]);//录像文件信息需要与实际查询QueryDevRecordFiles返回一致，此处仅为样例
				$("#hfbtn").html("停止");
			} else if ($("#hfbtn").html() == "停止") {
				PlayOCX.StopPlaybackByFile();
				$("#hfbtn").html("回放");
			}
		}
		var DownloadHandle = -1; 
		function DownloadByTime()
		{
			var hfxx = $("#cxjg").val();
			if(hfxx==null||hfxx==""){
				alert("没有远程录像，请先搜索录像！");
				return;
			}
			var szAccount = $("#cxsbid").val();
			//注册服务IP、端口
			var szRegIP = document.getElementById("RegIp").value;
			var iRegPort = document.getElementById("RegPort").value;
			var PlayOCX = document.getElementById("ppvs");
			var iConnHandle = PlayOCX.ConnectDeviceByACS(szAccount, szRegIP,
					iRegPort, "admin", "12345");
			var iChannel = $("#cxtd").val();
			var hf = hfxx.split("|");
			//录像文件信息需要与实际查询QueryDevRecordFiles返回一致，此处仅为样例
			var wjdx = parseInt(hf[1]);
			DownloadHandle = PlayOCX.StartDownloadFileByTime(iChannel,hf[0],wjdx, "D:\\test\\"+hf[0]+".mp4", hf[2],
					hf[3], hf[2], hf[3]);
			if ( DownloadHandle > 0 )
			{
				var DownloadPos = PlayOCX.GetDownloadPos(DownloadHandle);
				$("#xzjd").val(DownloadPos+"/100");
			}
			else
			{
				alert("下载失败");
			}  
		}
		
		function StopDownloadByTime()
		{
			var PlayOCX = document.getElementById("ppvs");
			PlayOCX.StopDownloadFile(DownloadHandle);
			DownloadHandle = -1;
			$("#xzjd").val("0/100");
			alert("已停止下载");
		}
	</script>

	<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

a,img {
	border: 0;
}

table {
	empty-cells: show;
	border-collapse: collapse;
	border-spacing: 0;
}

body {
	font: 12px/180% Arial, Helvetica, sans-serif, "新宋体";
}

.demo {
	width: 760px;
	margin: 40px auto;
}

.demo h2 {
	font-size: 18px;
	height: 52px;
	color: #3366cc;
	text-align: center;
}

.listext th {
	background: #eee;
	color: #3366cc;
}

.listext th,.listext td {
	border: solid 1px #ddd;
	text-align: left;
	padding: 10px;
	font-size: 14px;
}

.rc-handle-container {
	position: relative;
}

.rc-handle {
	position: absolute;
	width: 7px;
	cursor: ew-resize;
	*cursor: pointer;
	margin-left: -3px;
}

/* #vediokeyword{ */
/*     width: 90px; */
/*     border: none; */
/*     height: 23px; */
/*     background: #fff; */
/*     overflow: hidden; */
/*     vertical-align: middle; */
/* } */
/* #cxbtn{ */
/*     width: 50px; */
/*     height: 25px; */
/*     text-align: center; */
/*     font-size: 13px; */
/*     color: #fff; */
/*     border: none; */
/*     overflow: hidden; */
/*     vertical-align: middle; */
/*     background: #008cd6; */
/*     outline: none; */
/*     cursor: pointer; */
/*     position: relative; */
/*     left: -4px; */
/*     top: -2px; */
/* } */
.queryVehicle {
	width: 120px;
	height: 25px;
	position: relative;
	border: 1px solid #888;
	/*     box-shadow: -1px -1px 1px #888, 2px 2px 2px #888; */
}

.queryVehicle input {
	width: 80px;
	height: 23px;
	line-height: 24px;
	border: none;
	font-size: 15px;
	text-indent: 5px;
	outline: none;
}

.queryVehicle button {
	width: 40px;
	height: 27px;
	position: relative;
	top: -1px;
	right: 0;
	border: none;
	font-size: 13px;
	color: #ffffff;
	background: #3384fe;
}

.czbtn {
	width: 60px;
	height: 27px;
	position: relative;
	top: -1px;
	right: 0;
	border: none;
	font-size: 13px;
	color: #ffffff;
	background: #3384fe;
}

.cztab {
	width: 100%
}

.cztab td {
	border: solid 1px #ddd;
	text-align: left;
	padding: 5px;
	font-size: 14px;
}
</style>
</body>
</html>