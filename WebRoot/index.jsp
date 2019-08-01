<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html ng-app="demoApp">
  <head>
    <base href="<%=basePath%>" />
    <script>webRoot ="<%=basePath%>" </script>
   	<title>杭州市出租汽车燃油补贴管理信息系统</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="libs/angular.min.js"></script>
		<script type="text/javascript" src="libs/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxcore.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxsplitter.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxbuttons.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxswitchbutton.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxscrollbar.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxpanel.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxwindow.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxdata.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxtree.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxinput.js"></script>
		<script type="text/javascript" src="libs/jqwidgets-ver3.7.1/jqwidgets/jqxangular.js"></script>
		<script type="text/javascript" src="libs/json2.js"></script>


		<script type="text/javascript" src="resources/js/portal.js"></script>
		<script type="text/javascript" src="resources/js/main.js"></script>
		<script type="text/javascript" src="resources/js/config.js"></script>
		<script type="text/javascript" src="resources/js/callback.js"></script>
		<script type="text/javascript" src="resources/js/js2table.js"></script>
		<script type="text/javascript" src="resources/js/condition_query.js"></script>

		<script src="libs/datepicker/WdatePicker.js"></script>

		<script src="libs/hightcharts/highcharts.js"></script>
		<script src="libs/hightcharts/exporting.js"></script>
		
		<script src="libs/handlebars-v2.0.0.js"></script>

		<script data-jsfiddle="common" src="libs/handsontable-0.11.1/dist/handsontable141.full.js"></script>
  		<link data-jsfiddle="common" rel="stylesheet" media="screen" href="libs/handsontable-0.11.1/dist/handsontable141.full.css">


		<link rel="stylesheet" href="resources/css/reset.css" type="text/css" />
		<link rel="stylesheet" href="libs/jqwidgets-ver3.7.1/jqwidgets/styles/jqx.base.css" type="text/css" />
		<link rel="stylesheet" href="resources/css/main.css" type="text/css" />
	</head>
	<body>
		<!--导航 header-->
		<div id="head_DIV_1">
			<div id="head_DIV_3">杭州市出租汽车燃油补贴管理信息系统</div>
<%--			<div id="head_DIV_4">--%>
<%--				<div id="head_DIV_5">--%>
<%--					<div id="head_DIV_30_icona">--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<div id="head_DIV_31">--%>
<%--					<div id="head_DIV_30_iconb">--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<div id="head_DIV_33">--%>
<%--					<div id="head_DIV_30_iconc">--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<div id="head_DIV_35">--%>
<%--					<div id="head_DIV_36">--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<div id="head_DIV_37">--%>
<%--					<div id="head_DIV_38">--%>
<%--						管理员--%>
<%--					</div>--%>
<%--					<div id="head_DIV_39">--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--		</div>--%>


		<div id="mainSplitter">
			<div id="mainSplitterLeft" class=mainSplitterLeft>
				 <ul id=mainSplitterLeft_ul >
				 
				<script id="li1_1_text_template" type="text/x-handlebars-template">
				<li class=li1 style="color:#fff;background:#2ABBA3"><span></span>信息维护</li>
				<div id=li1_1_text >
 					{{#each list}}
					<li class=li2><span></span>{{name}}</li>
 					{{/each}}
				</div>
				</script>
				<script id="li1_2_text_template" type="text/x-handlebars-template">
				<li class=li1><span></span>参数设置</li>
				<div id=li1_2_text style="display:none">
				 
 					{{#each list}}
					<li class=li2><span></span>{{name}}</li>
 					{{/each}}
				</div>
				</script>
				<script id="li1_3_text_template" type="text/x-handlebars-template">
				<li class=li1><span></span>异动信息查询</li>
				<div id=li1_3_text style="display:none">
				 
 					{{#each list}}
					<li class=li2><span></span>{{name}}</li>
 					{{/each}}
				</div>
				</script>
				<script id="li1_4_text_template" type="text/x-handlebars-template">
				<li class=li1><span></span>燃油补贴管理</li>
				<div id=li1_4_text style="display:none">
				 
 					{{#each list}}
					<li class=li2><span></span>{{name}}</li>
 					{{/each}}
				</div>
				</script>
				</ul>
			</div>
			<div id="mainSplitterRight">
				<div
					style="height: 40px; border-bottom: 1px dashed #000; padding-top: 10px; padding-left: 10px;"
					id="mainSplitterRightTop"></div>
				<div id="mainSplitterRightBottom">
					<div id="mainSplitterRightBottomTable"></div>
					<div id="mainSplitterRightBottomPagination"></div>
				</div>
			</div>
		</div>
		
		<form method="post" action="ExportServlet">
			<table>
			<tr>
				<td><input type="hidden" name="svg"/>
				<input type="hidden" name="width"/>
				<input type="hidden" name="type"/>
				<input type="hidden" name="method"/></td>
			</tr>
			</table>
		</form>
	</body>
</html>
