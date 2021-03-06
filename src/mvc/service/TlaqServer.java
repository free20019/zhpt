package mvc.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import helper.JacksonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;




import com.service.GpsServicesDelegate;

import mvc.dao.Vehicle;

@Service
public class TlaqServer {
	protected JdbcTemplate jdbcTemplate = null;
	protected JdbcTemplate jdbcTemplate2 = null;

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
	private static  String WS_URL = "http://192.168.0.102:9086/EWS/GpsServicesPort?WSDL";

	// 车辆速度曲线统计分析
	public String clsdqx(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {});
		String stime = String.valueOf(paramMap.get("speed_stime"));
		String etime = String.valueOf(paramMap.get("speed_etime"));
		String vehinum = String.valueOf(paramMap.get("speed_vhic"));
		String speed = String.valueOf(paramMap.get("speed_value"));
		String riqi = stime.substring(2, 4) + stime.substring(5, 7);
		if (vehinum == null && vehinum.length() == 0 &&vehinum.equals("null")) {
			return "[{}]";
		}else{
			String sql = "select  * from HZGPS_TAXI.TB_GPS_"
				+ riqi
				+ "@TAXILINK t,"
				+ "HZGPS_TAXI.VW_VEHICLE@TAXILINK v where  t.vehicle_num=v.vehi_no "
				+ " and speed_time >=to_date('" + stime
				+ "','yyyy-mm-dd hh24:mi:ss') and" + " speed_time <=to_date('"
				+ etime + "','yyyy-mm-dd hh24:mi:ss') ";
			sql += " and vehi_no='" + vehinum + "'";
			if (speed != null && speed.length() > 0 && !speed.equals("0")
					&& !speed.equals("null")) {
				sql += " and speed>'" + speed + "'";
			}
			sql +=" order by speed_time";
			System.out.println(sql);
			List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				int num = Integer.parseInt(String.valueOf(map.get("DIRECTION")));
				String heading = "";
				if (num == 0 || num == 360) {
					heading = "正北";
				} else if (num == 90) {
					heading = "正东";
				} else if (num == 180) {
					heading = "正南";
				} else if (num == 270) {
					heading = "正西";
				} else if (num > 0 && num < 90) {
					heading = "东北";
				} else if (num > 90 && num < 180) {
					heading = "东南";
				} else if (num > 180 && num < 270) {
					heading = "西南";
				} else if (num > 270 && num < 360) {
					heading = "西北";
				}
				map.put("heading", heading);
			}
			return jacksonUtil.toJson(list);
		}
	}

	// 里程
	public String zlctj(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String stime = String.valueOf(paramMap.get("zspeed_stime"));
		String etime = String.valueOf(paramMap.get("zspeed_etime"));
		String comp_name = String.valueOf(paramMap.get("zspeed_comp"));
		String riqi = stime.substring(0, 4);
		if (comp_name == null && comp_name.length()== 0
				&& comp_name.equals("null")) {
			return "[{}]";
		}else{
			String sql = "select ('浙'||vhic) vhic,(sum(jicheng)+sum(kongshi))/10 licheng from (select distinct  * from HZGPS_CITIZEN.TB_CITIZEN_"
				+ riqi
				+ "@TAXILINK45 t,"
				+ "HZGPS_TAXI.VW_VEHICLE@TAXILINK v where '浙'||t.vhic=v.vehi_no "
				+ " and shangche >=to_date('"
				+ stime
				+ "','yyyy-mm-dd hh24:mi:ss')"
				+ " and shangche <=to_date('"
				+ etime + "','yyyy-mm-dd hh24:mi:ss')";
			sql += " and comp_id='" + comp_name + "'";
			sql += ") group by vhic";
			System.out.println(sql);
			List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
			return jacksonUtil.toJson(list);
		}
	}

	// 假的
	public String lctj(String vehinum, String stime, String etime,
			String ba_name, String comp_name) {
		String riqi = stime.substring(0, 4);
		String sql = "select distinct  * from HZGPS_CITIZEN.TB_CITIZEN_"
				+ riqi
				+ "@TAXILINK45 t,"
				+ "HZGPS_TAXI.VW_VEHICLE@TAXILINK v where '浙'||t.vhic=v.vehi_no "
				+ " and shangche >=to_date('" + stime
				+ "','yyyy-mm-dd hh24:mi:ss')" + " and shangche <=to_date('"
				+ etime + "','yyyy-mm-dd hh24:mi:ss')";
		if (ba_name != null && ba_name.length() > 0 && !ba_name.equals("选择公司")) {
			sql += " and ba_id='" + ba_name + "'";
		}
		if (comp_name != null && comp_name.length() > 0
				&& !comp_name.equals("选择分公司")) {
			sql += " and comp_id='" + comp_name + "'";
		}
		if (vehinum != null && vehinum.length() > 0 && !vehinum.equals("选择车辆")) {
			sql += " and vehi_no='" + vehinum + "'";
		}
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			count += Integer.parseInt(String.valueOf(map.get("jicheng")))
					+ Integer.parseInt(String.valueOf(map.get("kongshi")));
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("count", count / 10);
			map2.put("vehinum", map.get("vhic"));
			list2.add(map2);
		}
		return jacksonUtil.toJson(list2);
	}

	// 在线车辆查询统计
	public String findbyno(String postData) {
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		String ba_name = String.valueOf(paramMap.get("off_comp"));
		String vehino=(String) paramMap.get("off_vhic");
		String sql;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (ba_name == null && ba_name.length() == 0 && ba_name.equals("null")) {
			return "[{}]";
		}else{
			if (vehino != null && vehino.length() > 0 && !vehino.equals("null")) {
				sql = "select * from (select * from (select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where t.VEHI_NO='"
					+ vehino
					+ "') tt left join TB_COMPANY_PHONE c on tt.COMP_NAME=c.COMPNAME)a left join TB_TAXI_STATUS_new s on a.sim_num=s.mdt_no";
			} else {
				if(ba_name.equals("0")){
					sql = "select * from (select * from (select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK t "
							+ ") tt left join TB_COMPANY_PHONE c on tt.COMP_NAME=c.COMPNAME)a left join TB_TAXI_STATUS_new s on a.sim_num=s.mdt_no";
				}else{
					sql = "select * from (select * from (select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where  t.comp_id='"
							+ ba_name
							+ "') tt left join TB_COMPANY_PHONE c on tt.COMP_NAME=c.COMPNAME)a left join TB_TAXI_STATUS_new s on a.sim_num=s.mdt_no";
				}
			}
			System.out.println(sql);
			List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				try {
					
					if ((System.currentTimeMillis() - sdf
							.parse(String.valueOf(map.get("stime") == null ? "1970-01-01 00:00:00"
									: map.get("stime"))).getTime()) < 10 * 60 * 1000) {
						map.put("z_state", "在线");
						
					} else {
						map.put("z_state", "不在线");
					}
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
			}
			
			// map.put("palce",
			// getplace(String.valueOf(map.get("py")),
			// String.valueOf(map.get("px"))));
			
			return jacksonUtil.toJson(list);
		}
	}
	//多日未上线车辆查询
	public String offline(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		String comp = String.valueOf(paramMap.get("droff_comp"));
		int time=Integer.valueOf((String) paramMap.get("droff_mins"));
		SimpleDateFormat forma=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 long hms=System.currentTimeMillis()-(time*24*60*60*1000);
		 Date riqi=new Date(hms);
		 String date=forma.format(riqi);
		String  sql = "select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK t "
					+ "   left join  TB_TAXI_STATUS_new s on t.sim_num=s.mdt_no where 1=1";
			if (comp!=null&&comp.length()>0&&!comp.equals("null")&&!comp.equals("0")) {
				sql+=" and comp_id='"+comp+"'";
			}
			if(date!=null&&date.length()>0){
				sql+=" and stime<to_date('"+date+"','yyyy-mm-dd hh24:mi:ss')";
			}
			System.out.println("未上线车辆"+sql);
		List <Map<String, Object>> list=jdbcTemplate2.queryForList(sql);
		return jacksonUtil.toJson(list);
	}
	// public static boolean jisuan(String date1){
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// double result=0;
	// try {
	// Date start = sdf.parse(date1);
	// Date end = new Date();
	// long cha = end.getTime() - start.getTime();
	// result = cha * 1.0 / (1000 * 60);
	//
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// if(result<=30){
	// return true;
	// }else{
	// return false;
	// }
	// }
	public String getplace(String lati, String longti) {
		if (lati == null || longti == null || lati.equals("0")
				|| longti.equals("0") || lati.equals("-.000474")
				|| longti.equals(".00356") || lati.equals("")
				|| longti.equals("")) {
			return "未知位置";
		}
		String path = "http://restapi.amap.com/v3/geocode/regeo?location="
				+ longti
				+ ","
				+ lati
				+ "&key=0a54a59bdc431189d9405b3f2937921a&radius=100&extensions=all";
		// System.out.println(path);
		String place = "";
		try {
			URL url = new URL(path);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			StringBuffer buffer = new StringBuffer();
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(is,
						"utf-8");
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);

				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
				bufferedReader.close();
				inputStreamReader.close();
				is.close();
				is = null;
				conn.disconnect();
				JSONObject jsonObject = JSONObject
						.fromObject(buffer.toString());
				String addrList = jsonObject.getString("regeocode");
				JSONObject jsonObject1 = JSONObject.fromObject(addrList);
				String addrList1 = jsonObject1.getString("formatted_address");
				String roads = jsonObject1.getString("roads");
				JSONArray jsonarry = JSONArray.fromObject(roads);
				String roadName = "";
				for (int i = 0; i < jsonarry.size(); i++) {
					JSONObject jsonObject2 = jsonarry.getJSONObject(i);
					roadName = jsonObject2.getString("name");
				}
				place = addrList1 + " " + roadName;
				if (place.equals("")) {
					place = "未知地点";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return place;
	}
	/**
	 * 运价异常车辆查询
	 */
	public String yjycclcx(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		String yjyc_stime = String.valueOf(paramMap.get("yjyc_stime"));
		String yjyc_etime = String.valueOf(paramMap.get("yjyc_etime"));
		String yjyc_vhic = String.valueOf(paramMap.get("yjyc_vhic"));
		String sql = "select t.*, t.jine / 100 JINE1,v.comp_name,s.stime,s.px,s.py,own_name,own_tel"
				+ " from TB_TAXI_KYORDER t,TB_TAXI_STATUS_new s,HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ " where s.mdt_no=v.sim_num and v.vehi_sim=t.sim and"
				+ " shangche >= to_date('"+yjyc_stime+"', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and shangche <= to_date('"+yjyc_etime+"', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and vhic like '%"+yjyc_vhic+"%'";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return jacksonUtil.toJson(list);
	}

	
	public String findswcz(String qd_stime, String qd_etime, String zd_stime,
			String zd_etime, String qd_jwd, String zd_jwd) {
		List<List<Vehicle>> zzjg = new ArrayList<List<Vehicle>>();
		List<String> list = new ArrayList<String>();
		String s1 = "",s2 = "",s3 = "";
		List<Vehicle> listqd = new ArrayList<Vehicle>();
		List<Vehicle> listzd = new ArrayList<Vehicle>();
		if(qd_jwd!=null&&qd_jwd.length()>0){
			s1=swczff(qd_stime,qd_etime,qd_jwd,"1");
			JSONObject json = JSONObject.fromObject(s1);
	        list.add(json.getString("result0"));
	        JSONObject json1 =  JSONObject.fromObject(json.getString("result0"));
	        Set setqd = json1.keySet();
	        Iterator it = setqd.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 v.setStime(json1.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json1.get(a).toString().split("\\[")[1]);
	    		 listqd.add(v);
	    	 }  
		}
		
		System.out.println(1);
		for(int i=0;i<listqd.size();i++){
			System.out.println(listqd.get(i).getVehi_no()+" ####");
		}
		if(zd_jwd!=null&&zd_jwd.length()>0){
			s2=swczff(zd_stime,zd_etime,zd_jwd,"2");
			JSONObject json = JSONObject.fromObject(s2);
	        list.add(json.getString("result0"));
	        JSONObject json1 =  JSONObject.fromObject(json.getString("result0"));
	        Set setzd = json1.keySet();
	        Iterator it = setzd.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {  
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 v.setStime(json1.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json1.get(a).toString().split("\\[")[1]);
	    		 listzd.add(v);
	    	 }  
		}
		System.out.println(list.size()+"####%%%%");
		List<Vehicle> listjg = new ArrayList<Vehicle>();
        	JSONObject json11=JSONObject.fromObject(list.get(0));
        	JSONObject json2;
        	 json2=JSONObject.fromObject(list.get(1));
        	 Set arrayList1=json11.keySet();
        	 Set arrayList2=json2.keySet();
        	 List s11 = new ArrayList(arrayList1);
        	 List s22 = new ArrayList(arrayList2);
        	 s11.retainAll(s22);
        	 Iterator it = s11.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {  
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 System.out.println(a+"  ####");
	    		 v.setStime(json11.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json11.get(a).toString().split("\\[")[1]);
	    		 listjg.add(v);
	    	 }
		zzjg.add(listqd);
		zzjg.add(listzd);
		zzjg.add(listjg);
		System.out.println(zzjg.get(0).size()+"  @@@");
		return jacksonUtil.toJson(zzjg);
	}
	
	public String findswcz1(String qd_stime, String qd_etime, String zd_stime,
			String zd_etime, String qd_jwd, String zd_jwd) {
		List<List<Vehicle>> zzjg = new ArrayList<List<Vehicle>>();
		List<String> list = new ArrayList<String>();
		String s1 = "",s2 = "",s3 = "";
		List<Vehicle> listqd = new ArrayList<Vehicle>();
		List<Vehicle> listzd = new ArrayList<Vehicle>();
		if(qd_jwd!=null&&qd_jwd.length()>0){
			s1=swczff(qd_stime,qd_etime,qd_jwd,"1");
			JSONObject json = JSONObject.fromObject(s1);
	        list.add(json.getString("result0"));
	        JSONObject json1 =  JSONObject.fromObject(json.getString("result0"));
	        Set setqd = json1.keySet();
	        Iterator it = setqd.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 v.setStime(json1.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json1.get(a).toString().split("\\[")[1]);
	    		 listqd.add(v);
	    	 }  
		}
		
		System.out.println(1);
		for(int i=0;i<listqd.size();i++){
			System.out.println(listqd.get(i).getVehi_no()+" ####");
		}
		if(zd_jwd!=null&&zd_jwd.length()>0){
			s2=swczff(zd_stime,zd_etime,zd_jwd,"2");
			JSONObject json = JSONObject.fromObject(s2);
	        list.add(json.getString("result0"));
	        JSONObject json1 =  JSONObject.fromObject(json.getString("result0"));
	        Set setzd = json1.keySet();
	        Iterator it = setzd.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {  
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 v.setStime(json1.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json1.get(a).toString().split("\\[")[1]);
	    		 listzd.add(v);
	    	 }  
		}
		System.out.println(list.size()+"####%%%%");
		List<Vehicle> listjg = new ArrayList<Vehicle>();
        	JSONObject json11=JSONObject.fromObject(list.get(0));
        	JSONObject json2;
        	 json2=JSONObject.fromObject(list.get(1));
        	 Set arrayList1=json11.keySet();
        	 Set arrayList2=json2.keySet();
        	 List s11 = new ArrayList(arrayList1);
        	 List s22 = new ArrayList(arrayList2);
        	 s11.retainAll(s22);
        	 Iterator it = s11.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {  
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 System.out.println(a+"  ####");
	    		 v.setStime(json11.get(a).toString().split("\\[")[0].split(",")[0]);
	    		 v.setPx(json11.get(a).toString().split("\\[")[1].split("\\]")[0]);
	    		 listjg.add(v);
	    	 }
		return jacksonUtil.toJson(listjg);
	}
	
	public String swczff(String stime,String etime,String jwd,String obj){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long st = 0,et = 0;
		try {
			st = sdf.parse(stime).getTime();
			et = sdf.parse(etime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		URL url = null;
		try {
			url = new URL(WS_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        QName qname = new QName("http://service.com/", "GpsServicesService");
        javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
        GpsServicesDelegate gpsService = service.getPort(GpsServicesDelegate.class);
        Map<String, Object> req_ctx = ((BindingProvider)gpsService).getRequestContext();
        List qy = new ArrayList();
        Calendar calendar = Calendar.getInstance() ,calendar2 = Calendar.getInstance();
        calendar.set(2016, 4, 31, 18, 30, 22);
		calendar2.set(2016, 4,31, 18, 50, 22);
        qy.add(jwd);
        String  result = gpsService.swcz3Day(qy, st, et);
        System.out.println(result);
        return result;
	}
}
