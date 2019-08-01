package mvc.controllers;

import helper.DownloadAct;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.service.TljgServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/scjg")
public class TljgAction {
	private TljgServer tljgServer;
	private DownloadAct downloadAct = new DownloadAct();
	public TljgServer getTljgServer() {
		return tljgServer;
	}

	@Autowired
	public void setTljgServer(TljgServer tljgServer) {
		this.tljgServer = tljgServer;
	}

	/**
	 * 司机资质信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sjzz")
	@ResponseBody
	public String sjzz(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tljgServer.sjzz(postData);
		return msg;
	}
	@RequestMapping("sjzzexcle")
	@ResponseBody
	public String sjzzexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[]={"司机姓名","身份证号","业户","从业资格证","业户资格证有效期","年审有效期","手机号","服务资格证","驾驶证"};//导出列明
		String b[]={"NAME","ID_NUMBER","YHNAME","LICENSE_NUMBER","LICENSE_VALID_PERIOD_END","ANNUAL_REVIEW_VALID_PERIOD_END","MOBILE_PHONE","CER_SERIAL_NUMBER","DRIVING_LICENSE"};//导出map中的key
		String gzb="司机资质信息";//导出sheet名和导出的文件名
		String msg=tljgServer.sjzz(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 车辆信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/clxx")
	@ResponseBody
	public String clxx(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tljgServer.clxx(postData);
		return msg;
	}
	@RequestMapping("clxxexcle")
	@ResponseBody
	public String clxxexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[]={"车号","道路运输证","有效日期","业户名称","经营许可证","经营范围"};//导出列明
		String b[]={"PLATE_NUMBER","LICENSE_NUMBER","ANNUAL_REVIEW_VALID_PERIOD_END","COM_NAME","COM_LICENSE_NUMBER","BUSINESS_SCOPE_NAME"};//导出map中的key
		String gzb="车辆信息";//导出sheet名和导出的文件名
		String msg=tljgServer.clxx(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 车载设备信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/czsbxx")
	@ResponseBody
	public String czsbxx(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tljgServer.czsbxx(postData);
		return msg;
	}
	@RequestMapping("czsbxxexcle")
	@ResponseBody
	public String czsbxxexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[]={"车号","SIM卡号","终端类型","终端编号"};//导出列明
		String b[]={"VEHI_NO","VEHI_SIM","MDT_SUB_TYPE","MDT_NO"};//导出map中的key
		String gzb="车载设备信息";//导出sheet名和导出的文件名
		String msg=tljgServer.czsbxx(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 司机上班信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sjsb")
	@ResponseBody
	public String sjsb(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tljgServer.sjsb(postData); 
		return msg;
	}
	@RequestMapping("sjsbexcle")
	@ResponseBody
	public String sjsbexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[]={"报警标志","车辆状态","经度","纬度","速度","方向","GPS时间","企业经营许可证","从业资格证","车号","开机时间"};//导出列明
		String b[]={"BJBZ","CLZT","JD","WD","SD","FX","SJ","QYJYXKZH","JSYCYZGZH","CPHM","KJSJ"};//导出map中的key
		String gzb="司机上班信息";//导出sheet名和导出的文件名
		String msg=tljgServer.sjsb(postData); 
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 司机下班信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sjxb")
	@ResponseBody
	public String sjxb(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tljgServer.sjxb(postData);
		return msg;
	}
	@RequestMapping("sjxbexcle")
	@ResponseBody
	public String sjxbexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[]={"报警标志","车辆状态","经度","纬度","速度","方向","GPS时间","企业经营许可证","从业资格证","车号","计价器K值","当班开机时间","当班关机时间","当班里程","当班营运里程","车次","计时时间","总计金额","卡收金额","卡次","当班里程","总计里程","总营运里程","单价","总营运次数","签退方式"};//导出列明
		String b[]={"BJBZ","CLZT","JD","WD","SD","FX","SJ","QYJYXKZH","JSYCYZGZH","CPHM","JJQKZ","DBKJSJ","DBGJSJ","DBLC","DBYYLC","CC","JSSJ","ZJJE","KSJE","KC","BJLC","ZJLC","ZYYLC","DJ","ZYYCS","QTFS"};//导出map中的key
		String gzb="司机下班信息";//导出sheet名和导出的文件名
		String msg=tljgServer.sjxb(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 司机替班信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sjtb")
	@ResponseBody
	public String sjtb(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		msg = tljgServer.sjtb(postData);
		return msg;
	}
	@RequestMapping("sjtbexcle")
	@ResponseBody
	public String sjtbexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[]={"替班时间起","替班时间止","当班司机编号","当班司机名称","替班司机编号","替班司机姓名","车号","公司审核人员","企业名称"};//导出列明
		String b[]={"TBSJQ","TBSJZ","DBSJBH","DBSJXM","TBSJBH","TBSJXM","CPHM","SHRY","QYMC"};//导出map中的key
		String gzb="替班信息";//导出sheet名和导出的文件名
		String msg=tljgServer.sjtb(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 克隆信息  执法稽查信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/klxx")
	@ResponseBody
	public String klxx(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tljgServer.klxx(postData);
		return msg;
	}
	@RequestMapping("klxxexcle")
	@ResponseBody
	public String klxxexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[]={"车号","事件名称","稽查时间","当事人姓名","稽查地点"};//导出列明
		String b[]={"VEHICLE_PLATE_NUMBER","ILLEGAL_FACT","ILLEGAL_TIME","PARTY_NAME","ILLEGAL_LOCATION"};//导出map中的key
		String gzb="执法稽查信息";//导出sheet名和导出的文件名
		String msg=tljgServer.klxx(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 服务质量信息考核
	 * 
	 * @return
	 */
	@RequestMapping(value = "/fwzlxx")
	@ResponseBody
	public String fwzlxx(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tljgServer.fwzlxx(postData);
		return msg;
	}
	@RequestMapping("fwzlxxexcle")
	@ResponseBody
	public String fwzlxxexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[]={"企业名称","年度","信誉等级"};//导出列明
		String b[]={"YHMC","SJ","XYDJ"};//导出map中的key
		String gzb="服务质量综合评定";//导出sheet名和导出的文件名
		String msg=tljgServer.fwzlxx(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 执法案件表违章---处罚
	 * 
	 * @return
	 */
	@RequestMapping(value = "/zfwz")
	@ResponseBody
	public String zfwz(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tljgServer.zfwz(postData);
		return msg;
	}
	@RequestMapping("zfwzexcle")
	@ResponseBody
	public String zfwzexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"时间名称","违章时间","车号","企业名称","当事人姓名","经营许可证","罚款","扣分","执法单位","执法地点"};//导出列明
		String b[] = {"ILLEGAL_FACT","ILLEGAL_TIME","VEHICLE_PLATE_NUMBER","COMPANY_NAME","PARTY_NAME","COMPANY_LICENSE_NUMBER","FINE","DEDUCTION_SCORE","PUNISH_UNIT","ILLEGAL_LOCATION"};//导出map中的key
		String gzb = "违章处罚信息";//导出sheet名和导出的文件名
		String msg = tljgServer.zfwz(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 执法案件表投诉
	 * 
	 * @return
	 */
	@RequestMapping(value = "/zfts")
	@ResponseBody
	public String zfts(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tljgServer.zfts(postData);
		return msg;
	}
	@RequestMapping("zftsexcle")
	@ResponseBody
	public String zftsexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"投诉内容","受理时间","发生地点","车号","当事人姓名","企业名称","经营许可证"};//导出列明
		String b[] = {"ILLEGAL_FACT","ILLEGAL_TIME","ILLEGAL_LOCATION","VEHICLE_PLATE_NUMBER","PARTY_NAME","COMPANY_NAME","COMPANY_LICENSE_NUMBER"};//导出map中的key
		String gzb = "考核信息投诉";//导出sheet名和导出的文件名
		String msg = tljgServer.zfts(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 司机账号信息增删改查
	 */
	@RequestMapping("/findAccounts")
	@ResponseBody
	public String findAccounts(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg=tljgServer.findAccounts(postData);
		return msg;
	}
	@RequestMapping("findAccountsexcle")
	@ResponseBody
	public String findAccountsexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[]={"司机姓名","营运证号码","手机号","车牌号码","公司名称","支付宝账号","系统时间"};//导出列明
		String b[]={"DRIVER_NAME","LICENCE_NO","DRIVER_MOBILE","CAR_NO","PNAME","ALIPAY_ACCOUNT","SYSTEM_TIME"};//导出map中的key
		String gzb="司机账号信息";//导出sheet名和导出的文件名
		String msg=tljgServer.findAccounts(postData);
		List<Map<String, Object>>list = downloadAct.parseJSON2List(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	@RequestMapping("/addAccounts")
	@ResponseBody
	public String addAccounts(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String info="";
		int a=tljgServer.addAccounts(postData);
		if(a!=0){
			info="添加成功";
		}else{
			info="添加失败";
		}
		return "{'info':'"+info+"'}";
	}
	@RequestMapping("/findAccountID")
	@ResponseBody
	public String findAccountID(HttpServletRequest request,
			@RequestParam("id") String id) {
		String msg=tljgServer.findAccountsid(id);
		return msg;
	}
	@RequestMapping("/editAccounts")
	@ResponseBody
	public String editAccounts(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String info="";
		int a=tljgServer.editAccount(postData);
		if(a!=0){
			info="修改成功";
		}else{
			info="修改失败";
		}
		return "{'info':'"+info+"'}";
	}
	@RequestMapping("/delAccounts")
	@ResponseBody
	public String delAccounts(HttpServletRequest request,
			@RequestParam("id") String id) {
		String info="";
		int a=tljgServer.delAccounts(id);
		if(a!=0){
			info="删除成功";
		}else{
			info="删除失败";
		}
		return "{'info':'"+info+"'}";
	}
}
