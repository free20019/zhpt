package mvc.service;

import helper.JacksonUtil;
import helper.MessageClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import mvc.controllers.MyAuth;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.tool.Extension.Param;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class CommonService {

	protected JdbcTemplate jdbcTemplate = null;
	protected JdbcTemplate jdbcTemplate2 = null;

	private MessageClient messageClient = null;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate2() {
		return jdbcTemplate2;
	}

	@Autowired
	public void setJdbcTemplate2(JdbcTemplate jdbcTemplate2) {
		this.jdbcTemplate2 = jdbcTemplate2;
	}

	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();

	public String test() {
		System.out.println(jdbcTemplate);
		System.out.println(jdbcTemplate2);

		return "ok";
	}
	//查询公司
	public String findcomp(){
		List <Map<String, Object>> list=jdbcTemplate2.queryForList("select * from HZGPS_TAXI.TB_COMPANY@TAXILINK t");
		List al = new ArrayList();
		Map map1 = new HashMap();
		map1.put("name", "所有公司");
		map1.put("id", "0");
		al.add(map1);
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("name", list.get(i).get("comp_name"));
			map.put("id", list.get(i).get("comp_id"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		return  jacksonUtil.toJson(m);
	}
	
	//查询所有车辆
	public String findVhicAll(){
		String sql="select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK";
		System.out.println(sql);
		List <Map<String, Object>> list=jdbcTemplate2.queryForList(sql);
		List al = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("name", list.get(i).get("vehi_no"));
			map.put("id", list.get(i).get("vehi_no"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		
		return  jacksonUtil.toJson(m);
	}
	
	
	//根据公司id查询车号
	public String findvhic(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		String comp = String.valueOf(paramMap.get("ownid"));
		String sql="select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK where 1=1";
		if (comp!=null&&comp.length()>0) {
			sql+=" and comp_id = '"+comp+"'";
		}
		System.out.println("查询车号"+sql);
		List <Map<String, Object>> list=jdbcTemplate2.queryForList(sql);
		List al = new ArrayList();
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("name", list.get(i).get("vehi_no"));
			map.put("id", list.get(i).get("vehi_no"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		
		return  jacksonUtil.toJson(m);
	}
	//根据公司查询资格证号
	public String findcardno(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData, new TypeReference<Map<String,Object>>(){});
		String comp=String.valueOf(paramMap.get("comp"));
		String stime=String.valueOf(paramMap.get("stime"));
		String etime=String.valueOf(paramMap.get("etime"));
		String sql="select distinct yingyun from HZGPS_CITIZEN.TB_CITIZEN_2016@TAXILINK45 t" +
				" ,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where" +
				" shangche>=to_date('"+stime+"','yyyy-MM-dd hh24:mi:ss')" +
				" and shangche<=to_date('"+etime+"','yyyy-MM-dd hh24:mi:ss')" +
				"  and v.comp_id = '"+comp+"' and '浙'||t.vhic = v.vehi_no order by yingyun";
		List <Map<String, Object>> list=jdbcTemplate2.queryForList(sql);
		List al = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("name", list.get(i).get("yingyun"));
			map.put("id", list.get(i).get("yingyun"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		
		return  jacksonUtil.toJson(m);
	}
	public String cljkLsgj(String vehicleNo) {
		String stime = "";
		String etime = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		etime = sdf.format(date);
		Date date2 = new Date(System.currentTimeMillis() - 3600000);
		stime = sdf.format(date2);
		String str = "select * from(select a.vehicle_num,a.speed_time,a.px,a.py,a.speed"
				+ ",a.direction,a.state, b.vehi_sim,b.comp_name,b.own_name,b.own_tel"
				+ " from hzgps_taxi.tb_gps_"
				+ getTable(stime)
				+ "@taxilink a, "
				+ "hzgps_taxi.vw_vehicle@taxilink  b where a.vehicle_num = b.vehi_no"
				+ " and a.vehicle_num = '"
				+ vehicleNo
				+ "' and "
				+ "speed_time >= to_date('"
				+ stime
				+ "', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and speed_time <= to_date('"
				+ etime
				+ "', 'yyyy-mm-dd hh24:mi:ss')) " + "order by speed_time";

//		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return jacksonUtil.toJson(list);
	}

	public String cljkCx() {
		List<Map<String, Object>> list = jdbcTemplate2
				.queryForList("select vehi_no  from hz_taxi.TB_YJZHZX_VEHICLE_MONITORING t where type=1");
		return jacksonUtil.toJson(list);
	}

	public String lcjkXg(String id, String address) {
		String str = "update hz_taxi.TB_YJZHZX_PROCESS_MONITORING  set address='"
				+ address + "' where lc_id='" + id + "'";
		int i = jdbcTemplate2.update(str);

		return String.valueOf(i);
	}

	public String lcjkCx() {
		String str = "select * from hz_taxi.TB_YJZHZX_PROCESS_MONITORING";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return jacksonUtil.toJson(list);
	}

	public String cljkDel(String vehicleNo) {
		String str = "update hz_taxi.TB_YJZHZX_VEHICLE_MONITORING  set type=2 where vehi_no='"
				+ vehicleNo + "'";
		System.out.println(str);
		int i = jdbcTemplate2.update(str);

		return String.valueOf(i);
	}

	public String cljkAdd(String vehicleNo) {
		String str = "select * from hz_taxi.TB_YJZHZX_VEHICLE_MONITORING   where vehi_no='"
				+ vehicleNo + "'";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		int i = 0;
		if (list.size() == 0) {
			i = jdbcTemplate2
					.update(" insert into  hz_taxi.TB_YJZHZX_VEHICLE_MONITORING   (vehi_no,type)  values ('"
							+ vehicleNo + "',1)");
		} else {
			i = jdbcTemplate2
					.update("update hz_taxi.TB_YJZHZX_VEHICLE_MONITORING  set type=1 where vehi_no='"
							+ vehicleNo + "'");
		}
		return String.valueOf(i);

	}

	public String getTable(String srctime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyMM");
		Date date = null;
		try {
			date = sdf.parse(srctime);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return sdf2.format(date);

	}

	public String lcjkCxmx(String id) {
		String str = "select * from hz_taxi.TB_YJZHZX_MONITORING_VEHICLE where lc_id='"
				+ id + "'";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return jacksonUtil.toJson(list);
	}
	
	public String findarea(){
		String sql="select * from TB_AREA t";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		List al = new ArrayList();
		Map map1 = new HashMap();
		map1.put("name","所有区域");
		map1.put("id",0);
		al.add(map1);
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("name", list.get(i).get("area_name"));
			map.put("id", list.get(i).get("area_id"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		
		return  jacksonUtil.toJson(m);
	}

	public String sendInfo(String tels,String message) {
		if(messageClient == null){
			messageClient = new MessageClient();
		}
		String telArray [] = tels.split(",");
		if(telArray.length > 20 ){
			System.out.println("电话号码超过最大数量20");
			return "";
		}
		for(int i=0; i< telArray.length; i++){
			if(telArray[i].trim().isEmpty()){
				continue;
			}
			System.out.println("###tel:"+telArray[i]);
			messageClient.sendMessage(telArray[i], message);
		}
		
		return "{}";
	}
	public String login(HttpServletRequest request,String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		String username = String.valueOf(paramMap.get("username"));
		String password = String.valueOf(paramMap.get("password"));
		if(username==null||username.equals("null")||password==null||password.equals("null")){
			return "{}";
		}else{
			String sql="select * from zhpt.TB_QXGL_YHB@taxilink114 t,zhpt.TB_QXGL_GWB@taxilink114 g where t.id = g.qxid and  t.username='"+username+"'" +
					" and t.password = '"+password+"'";
			System.out.println(sql);
			List<Map<String, Object>>list=jdbcTemplate2.queryForList(sql);
			System.out.println(list.get(0).get("COMPETENCE"));
			if(list.size() > 0 ){
				request.getSession().setAttribute("user", username);
				request.getSession().setAttribute("name", list.get(0).get("NAME"));
				request.getSession().setAttribute("qxid", list.get(0).get("COMPETENCE"));
			}
			return jacksonUtil.toJson(list);
		}
	}
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		return "1";
		
	}
	public String findclsj(String info) {
		String str = "select t.vehi_no, t.comp_name, t.vehi_sim, t.own_name,"
				+ " t.own_tel, s.speed, s.stime, s.status, s.heading, s.gps_status,"
				+ " s.px, s.py from HZGPS_TAXI.VW_VEHICLE@TAXILINK t left join"
				+ " hz_taxi.TB_TAXI_STATUS s on t.sim_num = s.mdt_no where"
				+ "  t.vehi_no like '%"+info+"%' AND ROWNUM <500";
		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return jacksonUtil.toJson(list);
	}
	public String findjtsj() {
		String str = "(select * from zhpt.TB_YJZH_YJSJ@TAXILINK114 t left join (select sjbh,WM_CONCAT(y.vehi_no) vehi_no,WM_CONCAT(y.comp_name) comp_name, WM_CONCAT(y.own_name) own_name,WM_CONCAT(y.own_tel) own_tel from zhpt.TB_YJZH_SJCL@TAXILINK114 y, HZGPS_TAXI.VW_VEHICLE@TAXILINK v where y.vehi_no = v.vehi_no group by sjbh) b on t.sjbh = b.sjbh)";
		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return jacksonUtil.toJson(list);
	}
	
	public String findyjsj(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();   //当前时间
		long date = d.getTime();
		
		String str = "SELECT * FROM (SELECT T.*, ROWNUM RN FROM (select * from"
				+ " zhpt.TB_YJZH_YJSJ@TAXILINK114 t left join (select sjbh"
				+ ",WM_CONCAT(y.vehi_no) vehi_no,WM_CONCAT(y.comp_name) comp_name,"
				+ " WM_CONCAT(y.own_name) own_name,WM_CONCAT(y.own_tel) own_tel"
				+ " from zhpt.TB_YJZH_SJCL@TAXILINK114 y, "
				+ "HZGPS_TAXI.VW_VEHICLE@TAXILINK v where y.vehi_no = v.vehi_no"
				+ " group by sjbh) b  on t.sjbh = b.sjbh) T) WHERE RN = (SELECT"
				+ " COUNT(1) FROM (select * from zhpt.TB_YJZH_YJSJ@TAXILINK114 t"
				+ " left join (select sjbh,WM_CONCAT(y.vehi_no) vehi_no,"
				+ "WM_CONCAT(y.comp_name) comp_name, WM_CONCAT(y.own_name) own_name,"
				+ "WM_CONCAT(y.own_tel) own_tel from zhpt.TB_YJZH_SJCL@TAXILINK114 y"
				+ ", HZGPS_TAXI.VW_VEHICLE@TAXILINK v where y.vehi_no = v.vehi_no"
				+ " group by sjbh) b  on t.sjbh = b.sjbh) T)";
		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		String listtime = list.get(0).get("TIME")+"";
		if(listtime!=null&&listtime.length()<12){
			listtime += " 00:00:00";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			long weektime = sdf.parse(listtime).getTime();
			long weektime1 = weektime+1000*60*60*24*7l;
			if(weektime1>date){
				map.put("info", "0");
			}else {
				map.put("info", "1");
			}
			map.put("datas", list);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jacksonUtil.toJson(map);
	}
	public String yjsjclsave(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String sjbh = String.valueOf(paramMap.get("sjbh"));
		String[] cphm = String.valueOf(paramMap.get("cphm")).split(",");
		System.out.println(String.valueOf(paramMap.get("cphm"))+"  @@");
		String del = "delete from zhpt.TB_YJZH_SJCL@taxilink114 where sjbh = '"+sjbh+"'";
		int count =jdbcTemplate2.update(del);
		for(int i=0; i<cphm.length; i++){
			if(cphm!=null&&cphm.length>0){
				String sql = "insert into zhpt.TB_YJZH_SJCL@taxilink114 (sjbh,vehi_no) values('"+sjbh+"','"+cphm[i]+"')";
				jdbcTemplate2.update(sql);
				System.out.println(sql);
			}
		}
		return "{}";
	}

	public String sendMail(String mail, String message) {
		//第一步，创建与服务器交互的session
		String info ="";
		Properties props=new Properties();
		
		props.put("mail.smtp.host", "smtp.sina.com");
		props.put("mail.smtp.auth", "true");
		
		Session session =Session.getDefaultInstance(props,new MyAuth());
		
		//第二步，创建消息对象
		Message msg=new MimeMessage(session);
		
		try {
			
			InternetAddress ia=new InternetAddress(mail);
			msg.setRecipient(Message.RecipientType.TO, ia);
			msg.setSubject("你好，测试");
			msg.setFrom(new InternetAddress("crime_ly@sina.com"));
			msg.setText(message);
			
			//第三步，发送
			Transport.send(msg);
			info = "1";
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	public JSONArray findcamera() {
		String sql = "select * from HZGPS_TAXI.VW_VEHI_MDT@taxilink"
				+ " where vehi_no in ('浙AT0032','浙AT0095','浙AT0418','浙AT0452','浙AT0534','浙AT0537','浙AT0548','浙AT0695','浙AT0701','浙AT0792'"
				+ ",'浙AT1113','浙AT1169','浙AT1225','浙AT1312','浙AT1891','浙AT2249','浙AT2458','浙AT2480','浙AT2920','浙AT3021','浙AT3033','浙AT3083'"
				+ ",'浙AT3307','浙AT3317','浙AT3329','浙AT3601','浙AT3605','浙AT3630','浙AT3635','浙AT3682','浙AT3777','浙AT3868','浙AT3907','浙AT3958'"
				+ ",'浙AT4752','浙AT4771','浙AT5393','浙AT6461','浙AT8458','浙AT8459','浙AT8797','浙AT8799','浙AT8807','浙AT8812','浙AT8816','浙AT8828'"
				+ ",'浙AT8833','浙AT8839','浙AT0179','浙AT0247')";
		System.out.println(sql);
		JSONArray jsonArray = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
			int count=0;
			for (int i = 0; i < list.size(); i++) {
				count++;
				JSONObject one = new JSONObject(); 
				one.put("id", count+""); 
				one.put("open", "close");
				one.put("name", list.get(i).get("VEHI_NO"));
				one.put("pId", "hksp");
				one.put("lx", "hksp");
				one.put("camera", list.get(i).get("MDT_NO"));
				if(list.get(i).get("DB_TIME")!=null){
					Date otime=new Date();
					try {
						otime = sdf.parse(list.get(i).get("DB_TIME").toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Date ntime = new Date();
					long diff = ntime.getTime() - otime.getTime();
					long mins = diff / (1000 * 60);
					if(mins>10){
						one.put("icon", "resources/images/video/off.png");
					}else{
						one.put("icon", "resources/images/video/on.png");
						JSONObject td1 = new JSONObject(); 
						td1.put("id", "td1");
						td1.put("pId", count+"");
						td1.put("name", "通道1");
						td1.put("icon", "resources/images/video/mdt.png");
						td1.put("camera", list.get(i).get("MDT_NO"));
						td1.put("lx", "hksp");
						td1.put("td", "1");
						td1.put("vehinum",list.get(i).get("VEHI_NO"));
						JSONObject td2 = new JSONObject(); 
						td2.put("id", "td2");
						td2.put("pId", count+"");
						td2.put("name", "通道2");
						td2.put("icon", "resources/images/video/mdt.png");
						td2.put("camera", list.get(i).get("MDT_NO"));
						td2.put("lx", "hksp");
						td2.put("td", "2");
						td2.put("vehinum",list.get(i).get("VEHI_NO"));
						JSONObject td3 = new JSONObject(); 
						td3.put("id", "td3");
						td3.put("pId", count+"");
						td3.put("name", "通道3");
						td3.put("icon", "resources/images/video/mdt.png");
						td3.put("camera", list.get(i).get("MDT_NO"));
						td3.put("lx", "hksp");
						td3.put("td", "3");
						td3.put("vehinum",list.get(i).get("VEHI_NO"));
						JSONObject td4 = new JSONObject(); 
						td4.put("id", "td4");
						td4.put("pId", count+"");
						td4.put("name", "通道4");
						td4.put("icon", "resources/images/video/mdt.png");
						td4.put("camera", list.get(i).get("MDT_NO"));
						td4.put("lx", "hksp");
						td4.put("td", "4");
						td4.put("vehinum",list.get(i).get("VEHI_NO"));
						jsonArray.add(td1);
						jsonArray.add(td2);
						jsonArray.add(td3);
						jsonArray.add(td4);
					}
				}else{
					one.put("icon", "resources/images/video/off.png");
				}
				jsonArray.add(one);
			}
		return jsonArray;
	}
	
	//查询数据中心车户信息
	public String find_sjzx_comp(){
		String sql = "select * from V_TW_TAXI_COMPANY@TAXILINK_SJZX t where  business_scope_code = '1400' and vehicle_sum is not null";
		List <Map<String, Object>> list=jdbcTemplate2.queryForList(sql);
		List al = new ArrayList();
		Map map1 = new HashMap();
		map1.put("name", "所有公司");
		map1.put("id", "0");
		al.add(map1);
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("name", list.get(i).get("name"));
			map.put("id", list.get(i).get("id"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		return  jacksonUtil.toJson(m);
	}
	
	//查询所有车辆
	public String find_sjzx_VhicAll(){
		String sql="select t.id,t.plate_number from V_TW_TAXI_VEHICLE@TAXILINK_SJZX t,V_TW_TAXI_COMPANY @TAXILINK_SJZX c where t.company_id = c.id ";
		System.out.println(sql);
		List <Map<String, Object>> list=jdbcTemplate2.queryForList(sql);
		List al = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("name", list.get(i).get("plate_number"));
			map.put("id", list.get(i).get("plate_number"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		
		return  jacksonUtil.toJson(m);
	}
	public String find_sjzx_Vhics(String cphm){
		String sql="select t.id,t.plate_number from V_TW_TAXI_VEHICLE@TAXILINK_SJZX t,V_TW_TAXI_COMPANY @TAXILINK_SJZX c"
				+ " where t.company_id = c.id and t.plate_number like '%"+cphm+"%'";
		System.out.println(sql);
		List <Map<String, Object>> list=jdbcTemplate2.queryForList(sql);
		List al = new ArrayList();
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("name", list.get(i).get("plate_number"));
			map.put("id", list.get(i).get("plate_number"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		
		return  jacksonUtil.toJson(m);
	}
	
	//根据公司id查询车号
	public String find_sjzx_vhic(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		String comp = String.valueOf(paramMap.get("ownid"));
		String sql="select t.id,t.plate_number,t.company_id from V_TW_TAXI_VEHICLE@TAXILINK_SJZX t,V_TW_TAXI_COMPANY@TAXILINK_SJZX c where t.company_id = c.id ";
		if (comp!=null&&comp.length()>0) {
			sql+=" and company_id = '"+comp+"'";
		}
		System.out.println("查询车号"+sql);
		List <Map<String, Object>> list=jdbcTemplate2.queryForList(sql);
		List al = new ArrayList();
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("name", list.get(i).get("plate_number"));
			map.put("id", list.get(i).get("plate_number"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		
		return  jacksonUtil.toJson(m);
	}
	/**
	 * 车辆查询，当车辆下拉框中值发生改变执行
	 * 杭州数据库
	 */
	public String hzclcx(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData, new TypeReference<Map<String, Object>>() {});
		String comp = String.valueOf(paramMap.get("ownid"));
		String vhic = String.valueOf(paramMap.get("vehi_no"));
		String sql = "select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK where 1=1 ";
		if(comp!=null&&!comp.isEmpty()&&comp.length()>0){
			sql += "and comp_name like '%"+comp+"%'";
		}
		if(vhic!=null&&vhic.isEmpty()&&vhic.length()>0){
			sql += "and vehi_no like '%"+vhic+"%'";
		}
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		List<Map<String, Object>> al = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).get("vehi_no"));
			map.put("name", list.get(i).get("vehi_no"));
			al.add(map);
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("datas", al);
		return jacksonUtil.toJson(m);
	}
	/**
	 * 车辆查询，当车辆下拉框中值发生改变执行
	 * 数据中心数据库
	 */
	public String sjzxclcx(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData, new TypeReference<Map<String, Object>>() {});
		String comp = String.valueOf(paramMap.get("ownid"));
		String vhic = String.valueOf(paramMap.get("vehi_no"));
		String sql="select t.id,t.plate_number,t.company_id from V_TW_TAXI_VEHICLE@TAXILINK_SJZX t,"
				+ "V_TW_TAXI_COMPANY@TAXILINK_SJZX c where t.company_id = c.id ";
		if (comp!=null&&comp.length()>0) {
			sql+=" and company_id = '"+comp+"'";
		}
		if(vhic!=null&&vhic.isEmpty()&&vhic.length()>0){
			sql += "and plate_number like '%"+vhic+"%'";
		}
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		List<Map<String, Object>> al = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", list.get(i).get("plate_number"));
			map.put("name", list.get(i).get("plate_number"));
			al.add(map);
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("datas", al);
		return jacksonUtil.toJson(m);
	}
}
