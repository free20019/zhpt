package mvc.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import helper.DownloadAct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.service.TlhyglServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/hygl")
public class TlhyglAction {
	private TlhyglServer tlhyglServer;
	private DownloadAct downloadAct = new DownloadAct();
	public TlhyglServer getTlhyglServer() {
		return tlhyglServer;
	}

	@Autowired
	public void setTlhyglServer(TlhyglServer tlhyglServer) {
		this.tlhyglServer = tlhyglServer;
	}

	/**
	 * 重点监控区域车辆分析
	 * 
	 * @return
	 */
	@RequestMapping(value = "/zdjkqy")
	@ResponseBody
	public String sjzz(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		msg = tlhyglServer.findzdjkqy(postData);
		System.out.println(msg);
		return msg;
	}
	@RequestMapping("zdjkqyexcle")
	@ResponseBody
	public String zdjkqyexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"时间","0:00","0:30","1:00","1:30","2:00","2:30","3:00","3:30","4:00","4:30","5:00","5:30","6:00","6:30","7:00","7:30","8:00","8:30","9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23","y24","y25","y26","y27","y28","y29","y30","y31","y32","y33","y34","y35","y36","y37","y38","y39","y40","y41","y42","y43","y44","y45","y46","y47"};//导出map中的key
		String gzb = "重点区域车辆数量分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findzdjkqy(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 重点监控区域车辆明细查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/clmxinfo")
	@ResponseBody
	public String findspecinfo(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tlhyglServer.findspecinfo(postData);
		return msg;
	}

	/**
	 * 重点监控区域车辆明细查询 ---------------上面的
	 * 
	 * @return
	 */
	@RequestMapping(value = "/clmx")
	@ResponseBody
	public String findmingxi(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		msg = tlhyglServer.findmingxi(postData);
		return msg;
	}

	/**
	 * .车辆实载率
	 * 
	 * @return
	 */
	@RequestMapping(value = "/szl")
	@ResponseBody
	public String szl(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tlhyglServer.szl(postData);
		return msg;
	}
	@RequestMapping("szlexcle")
	@ResponseBody
	public String szlexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"时间","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "实载率分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.szl(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 出租汽车营收信息统计分析
	 */
	@RequestMapping("/czysxxtj")
	@ResponseBody
	public String findComp(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.findComp(postData);
		System.out.println(msg);
		return msg;
	}
	@RequestMapping("czysxxtjexcle")
	@ResponseBody
	public String czysxxtjexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"所属公司","车辆数","营运数","出车率","营运金额(元)","次数(次)","计程(公里)","空驶(公里)","总里程(公里)","实载率","载客时间(小时)","载客等候时间(小时)"};//导出列明
		String b[] = {"COMP","TOTAL","DRIVING","CCL","MONEY","TIMES","DISTANCE","EMPTY","TOTALDIS","YPERCENT","TIMEOUT","WAITTIME"};//导出map中的key
		String gzb = "出租企业营收信息统计";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findComp(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List3(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 出租汽车营收信息统计分析2
	 */
	@RequestMapping("/findPer")
	@ResponseBody
	public String findPer(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.findPer(postData);
		return msg;
	}
	
	/**
	 * 出租汽车营收信息统计分析3
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public String findAll(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.findAll(postData);
		return msg;
	}

	@RequestMapping("/find1")
	@ResponseBody
	public String find1(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.find1(postData);
		return msg;
	}

	@RequestMapping("/find2")
	@ResponseBody
	public String find2(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.find2(postData);
		return msg;
	}

	@RequestMapping("/find3")
	@ResponseBody
	public String find3(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.find3(postData);
		return msg;
	}

	/**
	 * 司机营收信息统计分析
	 */
	@RequestMapping("/sjysxxtj")
	@ResponseBody
	public String findsj(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.findsj(postData);
		return msg;
	}
	@RequestMapping("sjysxxtjexcle")
	@ResponseBody
	public String sjysxxtjexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"所属公司","资格证号","营运金额(元)","次数(次)","计程(公里)","空驶(公里)","总里程(公里)","实载率","载客时间(小时)","载客等候时间(小时)"};//导出列明
		String b[] = {"COMP","YINGYUN","MONEY","TIMES","DISTANCE","EMPTY","TOTAL","PERCENT","TIMEOUT","WAITTIME"};//导出map中的key
		String gzb = "出租司机营收信息统计";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findsj(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List3(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 车辆营收信息统计分析
	 */
	@RequestMapping("/clysxxtj")
	@ResponseBody
	public String findvhicxx(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.findvhicxx(postData);
		return msg;
	}
	@RequestMapping("clysxxtjexcle")
	@ResponseBody
	public String clysxxtjexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"所属公司","车号","营运金额(元)","次数(次)","计程(公里)","空驶(公里)","总里程(公里)","实载率","载客时间(小时)","载客等候时间(小时)"};//导出列明
		String b[] = {"COMP","VHIC","MONEY","TIMES","DISTANCE","EMPTY","TOTAL","PERCENT","TIMEOUT","WAITTIME"};//导出map中的key
		String gzb = "出租车辆营收信息统计";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findvhicxx(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List3(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 查询公司信息
	 */
	@RequestMapping("/findcompall")
	@ResponseBody
	public String findcompall(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.findCompxx(postData);
		return msg;
	}
	@RequestMapping("findcompallexcle")
	@ResponseBody
	public String findcompallexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"公司名","所在区域","地址"};//导出列明
		String b[] = {"COMP_NAME","AREA","NOTE"};//导出map中的key
		String gzb = "业户查询";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findCompxx(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 日报表
	 */
	@RequestMapping("/findoperday")
	@ResponseBody
	public String findoperday(HttpServletRequest request, String time) {
		String msg = tlhyglServer.findOperDay(time);
		return msg;
	}
	@RequestMapping("findoperdayexcle")
	@ResponseBody
	public String findoperdayexcle(HttpServletRequest request,
			HttpServletResponse response,
			String time) throws IOException{
		String a[] = {"日期","总车辆数","平均营运车辆数","平均周转次数","平均营收金额","平均实载率","平均重车时间(分)","平均等候时间(分)","平均实载里程(分)","平均空驶里程(公里)"};//导出列明
		String b[] = {"DB_TIME","JDDBD","JDDSB","RUN_TIMES","RUN_PROFIT","ACTUAL_LOAD_RATE","RUN_TIME","WAITTING_TIME","ACTUAL_LOAD_MILEAGE","NO_LOAD_MILEAGE"};//导出map中的key
		String gzb = "营运日报";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findOperDay(time);
		List<Map<String, Object>>list = downloadAct.parseJSON2List2(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 月报表
	 */
	@RequestMapping("/findopermonth")
	@ResponseBody
	public String findopermonth(HttpServletRequest request, String time) {
		String msg = tlhyglServer.findOperMonth(time);
		return msg;
	}
	@RequestMapping("findopermonthexcle")
	@ResponseBody
	public String findopermonthexcle(HttpServletRequest request,
			HttpServletResponse response,
			String time) throws IOException{
		String a[] = {"日期","总车辆数","参与营运车辆数","周转总次数","平均周转次数","平均营运率","平均营收金额","平均实载率","平均出车时间(时)","平均重车时间(时)","平均等候时间(时)","平均总里程(公里)","平均实载里程(公里)","平均空驶里程(公里)"};//导出列明
		String b[] = {"REPORT_TIME","REPORE_VHICNO","REPORE_VHIC","REPORE_NO","REPORE_TURNOVER","REPORE_RADE","REPORE_AMOUNT_REVENUE","REPORE_ACTUAL_LOADING","REPORE_CAR_TIME","REPROE_REVENUE_TIME","REPORE_WAIT_TIME","REPORE_MILEAGE","REPORE_ACTUAL_MILEAGE","REPORE_EMPTY_MILEAGE"};//导出map中的key
		String gzb = "营运月报";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findOperMonth(time);
		List<Map<String, Object>>list = downloadAct.parseJSON2List2(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 年报表
	 */
	@RequestMapping("/findoperyear")
	@ResponseBody
	public String findoperyear(HttpServletRequest request, String time) {
		String msg = tlhyglServer.findOperYear(time);
		return msg;
	}
	@RequestMapping("findoperyearexcle")
	@ResponseBody
	public String findoperyearexcle(HttpServletRequest request,
			HttpServletResponse response,
			String time) throws IOException{
		String a[] = {"月份","总车辆数","日参与营运平均车辆数","周转总次数","月平均单车周转次数","日平均单车周转次数","平均营运率","总出车时间(天)","平均出车时间(时间)","营收金额","月平均单车营收金额","日平均单车营收金额","总里程(公里)","月平均单车总里程(公里)","日平均单车总里程(公里)","实载总里程(公里)","月平均单车实载里程(公里)","日平均单车实载里程(公里)","空驶总里程(公里)","月平均单车空驶里程(公里)","日平均单车空驶里程(公里)","平均实载率"};//导出列明
		String b[] = {"REPORT_TIME","JDDSB","PJREPORE_VHIC","REPORE_NO","MONTHREPORE_NO","DAYREPORE_NO","REPORE_RADE","REPORE_CAR_TIME","PJREPORE_CAR_TIME","REPORE_AMOUNT_REVENUE","MONTHREPORE_AMOUNT_REVENUE","DAYREPORE_AMOUNT_REVENUE","REPORE_MILEAGE","MONTHREPORE_MILEAGE","DAYREPORE_MILEAGE","REPORE_ACTUAL_MILEAGE","MONTHREPORE_ACTUAL_MILEAGE","DAYREPORE_ACTUAL_MILEAGE","REPORE_EMPTY_MILEAGE","MONTHREPORE_EMPTY_MILEAGE","DAYREPORE_EMPTY_MILEAGE","PEPORE_ACTUAL_LOADING"};//导出map中的key
		String gzb = "营运年报";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findOperYear(time);
		List<Map<String, Object>>list = downloadAct.parseJSON2List1(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 查询车辆信息
	 */
	@RequestMapping("/findvehinfo")
	@ResponseBody
	public String findvehinfo(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.findvehinfo(postData);
		return msg;
	}
	@RequestMapping("findvehinfoexcle")
	@ResponseBody
	public String findvehinfoexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"公司名","车号","SIM卡号","终端编号","车辆类型","终端类型","联系人","联系电话","车辆颜色"};//导出列明
		String b[] = {"COMP_NAME","VEHI_NO","VEHI_SIM","MDT_NO","VT_NAME","MT_NAME","OWN_NAME","OWN_TEL","VC_NAME"};//导出map中的key
		String gzb = "车辆查询";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findvehinfo(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 查询人员信息
	 */
	@RequestMapping("/findrenyuan")
	@ResponseBody
	public String findrenyuan(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.findrenyuan(postData);
		return msg;
	}
	@RequestMapping("findrenyuanexcle")
	@ResponseBody
	public String findrenyuanexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"姓名","性别","身份证号","手机号码","现居住地"};//导出列明
		String b[] = {"NAME","SEX","ID_NUMBER","MOBILE_PHONE","CURRENT_ADDRESS"};//导出map中的key
		String gzb = "人员查询";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findrenyuan(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 杭州出租保有量统计
	 */
	@RequestMapping("/findbyl")
	@ResponseBody
	public String findbyl(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.findbyl(postData);
		return msg;
	}

	/**
	 * 车辆定位信息
	 */
	@RequestMapping("/vehpos")
	@ResponseBody
	public String vehpos(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.vehpos(postData);
		return msg;
	}
	@RequestMapping("vehposexcle")
	@ResponseBody
	public String vehposexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"速度","时间","状态","方向","经度","纬度","车号","SIM卡号","公司名称","司机姓名","司机电话"};//导出列明
		String b[] = {"SPEED","STIME","STATUS","HEADING","PX","PY","VEHI_NO","VEHI_SIM","COMP_NAME","OWN_NAME","OWN_TEL"};//导出map中的key
		String gzb = "车辆定位信息";//导出sheet名和导出的文件名
		String msg = tlhyglServer.vehpos(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 营运信息查询
	 */
	@RequestMapping("/findyyinfo")
	@ResponseBody
	public String findyyinfo(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.findyyinfo(postData);
		System.out.println(msg);
		return msg;
	}
	@RequestMapping("findyyinfoexcle")
	@ResponseBody
	public String findyyinfoexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"车号","资格证号","上车时间","下车时间","营运金额","计程(公里)","空驶(公里)","等候(秒)","交易类型"};//导出列明
		String b[] = {"VEHI_NO","YINGYUN","SHANGCHETIME","XIACHE","JINE","JICHENG","KONGSHI","DENGHOU","JIAOYITYPE"};//导出map中的key
		String gzb = "营运信息查询";//导出sheet名和导出的文件名
		String msg = tlhyglServer.findyyinfo(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List3(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 车辆平均营运收益分析
	 */
	@RequestMapping("/findpjyysy")
	@ResponseBody
	public String findpjyysy(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.finddcpjsy(postData);
		return msg;
	}
	@RequestMapping("findpjyysyexcle")
	@ResponseBody
	public String findpjyysyexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"营运收益(元/时)","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "单车平均营运收益分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.finddcpjsy(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 单车平均载客里程
	 */
	@RequestMapping("/finddclc")
	@ResponseBody
	public String finddclc(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.finddclc(postData);
		return msg;
	}
	@RequestMapping("finddclcexcle")
	@ResponseBody
	public String finddclcexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"载客里程(公里/时)","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "单车平均载客里程分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.finddclc(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 单车平均空驶里程分析
	 */
	@RequestMapping("/finddcks")
	@ResponseBody
	public String finddcks(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.finddcks(postData);
		return msg;
	}
	@RequestMapping("finddcksexcle")
	@ResponseBody
	public String finddcksexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"空驶里程(公里/时)","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "单车平均空驶里程分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.finddcks(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 单车平均行驶总里程分析
	 */
	@RequestMapping("/finddcxs")
	@ResponseBody
	public String finddcxs(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.finddcxs(postData);
		return msg;
	}
	@RequestMapping("finddcxsexcle")
	@ResponseBody
	public String finddcxsexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"总里程(公里/时)","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "单车平均总里程分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.finddcxs(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 单车平均营运次数分析
	 */
	@RequestMapping("/finddcyy")
	@ResponseBody
	public String finddcyy(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.finddcyy(postData);
		return msg;
	}
	@RequestMapping("finddcyyexcle")
	@ResponseBody
	public String finddcyyexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"次数","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "单车平均营运次数分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.finddcyy(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 单车平均载客时间分析
	 */
	@RequestMapping("/finddcsj")
	@ResponseBody
	public String finddcsj(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.finddcsj(postData);
		return msg;
	}
	@RequestMapping("finddcsjexcle")
	@ResponseBody
	public String finddcsjexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"载客时间","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "单车平均载客时间分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.finddcsj(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 单车平均载客等候时间分析
	 */
	@RequestMapping("/finddcfx")
	@ResponseBody
	public String finddcfx(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = tlhyglServer.finddcfx(postData);
		return msg;
	}
	@RequestMapping("finddcfxexcle")
	@ResponseBody
	public String finddcfxexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"载客等候时间","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "单车平均载客等候时间分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.finddcfx(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * .上线率
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sxl")
	@ResponseBody
	public String sxl(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tlhyglServer.sxl(postData);
		return msg;
	}
	@RequestMapping("sxlexcle")
	@ResponseBody
	public String sxlexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"时间","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "重点区域上线率分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.sxl(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * .重车率
	 * 
	 * @return
	 */
	@RequestMapping(value = "/zcl")
	@ResponseBody
	public String zcl(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tlhyglServer.zcl(postData);
		return msg;
	}
	@RequestMapping("zclexcle")
	@ResponseBody
	public String zclexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"时间","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "重车率分析";//导出sheet名和导出的文件名
		String msg = tlhyglServer.zcl(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List4(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 服务评价信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/fwpjxx")
	@ResponseBody
	public String fwpjxx(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
		String msg = "ok";
		msg = tlhyglServer.fwpjxx(postData);
		System.out.println(msg);
		return msg;
	}
	
	@RequestMapping("fwpjxxexcle")
	@ResponseBody
	public String fwpjxxexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"车号","资格证号","上车时间","下车时间","营运金额","计程(公里)","空驶(公里)","等候(秒)","交易类型"};//导出列明
		String b[] = {"VEHI_NO","YINGYUN","SHANGCHETIME","XIACHE","JINE","JICHENG","KONGSHI","DENGHOU","JIAOYITYPE"};//导出map中的key
		String gzb = "营运信息查询";//导出sheet名和导出的文件名
		String msg = tlhyglServer.fwpjxx(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List3(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
}
