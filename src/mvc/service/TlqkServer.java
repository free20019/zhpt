package mvc.service;

import helper.JWDSwitch;
import helper.JacksonUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mvc.cache.DataItem;
import mvc.cache.GisData;
import net.sf.json.JSONArray;














import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TlqkServer {
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

	public String test() {
		System.out.println(jdbcTemplate);
		System.out.println(jdbcTemplate2);

		return "ok";
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

		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return jacksonUtil.toJson(list);
	}

	public String lcjkXg(String id, String address) {
		String str = "update hz_taxi.TB_YJZHZX_PROCESS_MONITORING  set address='"
				+ address + "' where lc_id='" + id + "'";
		int i = jdbcTemplate2.update(str);

		return String.valueOf(i);
	}

	// 所有车辆
	public String findvehiall() {
		String str = "select vehi_no,vehi_sim,comp_name,own_name,own_tel,"
				+ "speed,stime,status,px,py,db_time,heading from HZGPS_TAXI.VW_VEHICLE@TAXILINK t left join "
				+ "hz_taxi.TB_TAXI_STATUS s  on t.sim_num=s.mdt_no where t.vehi_no like '%浙AT%'";
//		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		int lxs = 0;// 下线
		int zxs = 0;// 上线
		int yds = 0;// 空车
		int tcs = 0;// 重车
		List<Object> newList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("vehi_no", list.get(i).get("vehi_no"));
			map.put("vehi_sim", list.get(i).get("vehi_sim"));
			map.put("comp_name", list.get(i).get("comp_name"));
			map.put("own_name", list.get(i).get("own_name"));
			map.put("own_tel", list.get(i).get("own_tel"));
			map.put("speed", list.get(i).get("speed"));
			map.put("stime", list.get(i).get("stime"));
			map.put("status", list.get(i).get("status"));
			map.put("px", list.get(i).get("px"));
			map.put("py", list.get(i).get("py"));
			map.put("heading", list.get(i).get("heading"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				if (list.get(i).get("DB_TIME") != null) {
					Date otime = sdf.parse(list.get(i).get("DB_TIME")
							.toString());
					Date ntime = new Date();
					long diff = ntime.getTime() - otime.getTime();
					long mins = diff / (1000 * 60);
					if (mins > 10) {
						lxs++;
						map.put("speed", "0.00");
					} else {
						zxs++;
						map.put("speed", (new BigDecimal(list.get(i).get(
								"SPEED").toString()).setScale(2,
								BigDecimal.ROUND_HALF_UP))
								+ "");
						if (Integer.parseInt(list.get(i).get("status")
								.toString()) == 0) {
							yds++;
						} else {
							tcs++;
						}
					}
				} else {
					lxs++;
					map.put("speed", "0.00");
				}
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}

			newList.add(map);

		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("datas", newList);
		m.put("total", list.size());
		m.put("lxs", lxs);
		m.put("zxs", zxs);
		m.put("yds", yds);
		m.put("tcs", tcs);
		return jacksonUtil.toJson(m);
	}

	// 查询一辆车
	public String findvehi(String vehino) {
		String str = "select vehi_no,vehi_sim,comp_name,own_name,own_tel,"
				+ "speed,stime,status,px,py,db_time,heading from HZGPS_TAXI.VW_VEHICLE@TAXILINK t , "
				+ "hz_taxi.TB_TAXI_STATUS s  where t.sim_num=s.mdt_no and t.vehi_no like '"
				+ vehino + "' ";
//		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);

		List<Object> newList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("vehi_no", list.get(i).get("vehi_no"));
			map.put("vehi_sim", list.get(i).get("vehi_sim"));
			map.put("comp_name", list.get(i).get("comp_name"));
			map.put("own_name", list.get(i).get("own_name"));
			map.put("own_tel", list.get(i).get("own_tel"));
			map.put("speed", list.get(i).get("speed"));
			map.put("stime", list.get(i).get("stime"));
			map.put("status", list.get(i).get("status"));
			map.put("px", list.get(i).get("px"));
			map.put("py", list.get(i).get("py"));
			map.put("heading", list.get(i).get("heading"));
			map.put("address", JWDSwitch.getplace(list.get(i).get("px"), list
					.get(i).get("py")));
			newList.add(map);

		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("datas", newList);
		return jacksonUtil.toJson(m);
	}

	// 上线率基类
	public List<Map<String, Object>> findyingyun24(int i) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String str = "select ONLINE_RATE,to_char(db_time,'HH24')s from hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t where "
				+ "db_time>=to_date('"
				+ time
				+ " 00:00:00','yyyy-MM-dd HH24:mi:ss') "
				+ "and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-MM-dd HH24:mi:ss') "
				+ "and to_char(db_time,'mi')='00' and ba_id ='0' order by db_time ";
//		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return list;

	}

	// 查询上线率上周平均
	public List<Map<String, Object>> findaverageyingyun() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Date d = new Date();
		String time = sdf.format(d);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY);

		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date sTime = calendar.getTime();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date eTime = calendar.getTime();
		String st = sdf.format(sTime) + " 00:00:00";
		String et = sdf.format(eTime) + " 23:59:59";
		// String sql =
		// "select ONLINE_RATE from hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t where db_time>=to_date('"
		// + st
		// + "','yyyy-MM-dd HH24:mi:ss')"
		// + " and db_time<=to_date('"
		// + et
		// +
		// "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00' and ba_id ='0'";

		String sql = "select rpad(trunc(avg(replace(ONLINE_RATE,'%',''))),length(trunc(avg(replace(ONLINE_RATE,'%',''))))+1,'%') ONLINE_RATE ,s "
				+ " from(select ONLINE_RATE,to_char(db_time,'HH24')s from hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t "
				+ " where db_time>=to_date('"
				+ st
				+ "','yyyy-MM-dd HH24:mi:ss') and "
				+ " db_time<=to_date('"
				+ et
				+ "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00' and ba_id ='0') "
				+ " group by s order by s asc";

//		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);

		return list;
	}

	// 上线率
	public String sxl() {
		Map<String, Object> m = new HashMap<String, Object>();
		List<Map<String, Object>> newList0 = findyingyun24(0);
		List<Map<String, Object>> newList1 = findyingyun24(1);
		List<Map<String, Object>> newList2 = findyingyun24(2);
		List<Map<String, Object>> newList7 = findyingyun24(7);
		List<Map<String, Object>> newList = findaverageyingyun();
		m.put("Today", getsObject(newList0, "ONLINE_RATE"));
		m.put("Yesterday", getsObject(newList1, "ONLINE_RATE"));
		m.put("TheDayBeforeYesterday", getsObject(newList2, "ONLINE_RATE"));
		m.put("LastWeek", getsObject(newList7, "ONLINE_RATE"));
		m.put("LastWeekAverage", getsObject(newList, "ONLINE_RATE"));
		List<Object> al = ZhuanHuan(getsObject(newList0, "ONLINE_RATE"),
				getsObject(newList1, "ONLINE_RATE"), 
				getsObject(newList2, "ONLINE_RATE"), 
				getsObject(newList7, "ONLINE_RATE"),
						getsObject(newList, "ONLINE_RATE"));
		Map<Object, Object> jg = new HashMap<Object, Object>();
		jg.put("datas", al);
		return jacksonUtil.toJson(jg);
	}

	// 一小时以上未营运车辆数基类
	public List<Map<String, Object>> findweiyingyun24(int i) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String str = "select (9612-run_taxis) run_taxis,to_char(db_time,'ddHH24')s from hz_taxi.TB_TAXI_RUN_INFO_RECORD_TEST t where "
				+ "db_time>=to_date('"
				+ time
				+ " 00:00:00','yyyy-MM-dd HH24:mi:ss') "
				+ "and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-MM-dd HH24:mi:ss') "
				+ "and to_char(db_time,'mi')='00'  order by db_time ";
//		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return list;

	}

	// 处理6小时，jintianjiazuotian
	public List<Map<String, Object>> findweiyingyun48() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE));
		String time = dft.format(date.getTime());
		Calendar date2 = Calendar.getInstance();
		date2.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date2.getTime());
		String str = "select  (9612-run_taxis) run_taxis,to_char(db_time,'ddHH24')s from hz_taxi.TB_TAXI_RUN_INFO_RECORD_TEST t where "
				+ "db_time>=to_date('"
				+ time2
				+ " 00:00:00','yyyy-MM-dd HH24:mi:ss') "
				+ "and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-MM-dd HH24:mi:ss') "
				+ "and to_char(db_time,'mi')='00'  order by db_time ";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return list;

	}

	// 在最近6小时未营运
	public List<Map<String, Object>> findmeiyouyingyun() {
		List<Map<String, Object>> list = findweiyingyun48();
		String stime = "", etime = "", time = "";
		for (int i = 5; i > -1; i--) {
			Map<String, Object> map = new HashMap<String, Object>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
			time = df.format(new Date(System.currentTimeMillis() - 3600000
					* (i + 1)));
			stime = df2.format(new Date(System.currentTimeMillis() - 3600000
					* (i + 1)));
			etime = df2.format(new Date(System.currentTimeMillis() - 3600000
					* i));
			String sql = "select count(distinct (vhic)) c from HZGPS_CITIZEN.TB_CITIZEN_"
					+ stime.substring(0, 4)
					+ "@TAXILINK45 t"
					+ " where shangche>=to_date('"
					+ stime
					+ "','yyyy-MM-dd hh24')"
					+ " and  shangche<to_date('"
					+ etime + "','yyyy-MM-dd hh24')";
//			System.out.println(sql);
			map.put("RUN_TAXIS", 9612 - jdbcTemplate2.queryForObject(sql,Integer.class));
			map.put("S", time.substring(8, 10) + time.substring(11, 13));
			list.add(map);
		}
		return list;
	}

	// 查询一小时以上未营运车辆数上周平均
	public List<Map<String, Object>> findaveragezaixian() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Date d = new Date();
		String time = sdf.format(d);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY);

		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date sTime = calendar.getTime();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date eTime = calendar.getTime();
		String st = sdf.format(sTime) + " 00:00:00";
		String et = sdf.format(eTime) + " 23:59:59";
		// String sql =
		// "select ONLINE_RATE from hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t where db_time>=to_date('"
		// + st
		// + "','yyyy-MM-dd HH24:mi:ss')"
		// + " and db_time<=to_date('"
		// + et
		// +
		// "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00' and ba_id ='0'";

		String sql = "select (9612-trunc(avg(replace(run_taxis,'%','')))) run_taxis ,s "
				+ " from(select run_taxis,to_char(db_time,'HH24')s from hz_taxi.TB_TAXI_RUN_INFO_RECORD_TEST t "
				+ " where db_time>=to_date('"
				+ st
				+ "','yyyy-MM-dd HH24:mi:ss') and "
				+ " db_time<=to_date('"
				+ et
				+ "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00') "
				+ " group by s order by s asc";

//		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);

		return list;
	}

	// 一小时以上未营运车辆数
	public String yxswyy() {
		//Map<String, Object> m = new HashMap<String, Object>();
		List<Map<String, Object>> newList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList2 = findweiyingyun24(2);
		List<Map<String, Object>> newList7 = findweiyingyun24(7);
		List<Map<String, Object>> newList = findaveragezaixian();
		List<Map<String, Object>> newList_0 = findmeiyouyingyun();

		SimpleDateFormat df = new SimpleDateFormat("dd");
		String time = df.format(new Date());
		for (int i = 0; i < newList_0.size(); i++) {
			if (time.equals(newList_0.get(i).get("S").toString()
					.substring(0, 2))) {
				newList0.add(newList_0.get(i));
			} else {
				newList1.add(newList_0.get(i));
			}
		}
		List<Object>al=ZhuanHuan(getObject(newList0, "run_taxis"), 
				getObject(newList1, "run_taxis"), 
				getObject(newList2, "run_taxis"), 
				getObject(newList7, "run_taxis"), 
				getsObject(newList, "run_taxis"));
		Map<Object, Object>jg= new HashMap<Object, Object>();
		jg.put("datas", al);
		return jacksonUtil.toJson(jg);
	}

	// 重点监控区域车辆数基类
	public List<Map<String, Object>> findarea(int i) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String str = "select sum(taxi_quantity) rate,s from(select taxi_quantity,to_char(db_time,'HH24')s from hz_taxi.TB_TAXI_AREA_CONFIGURATION t where "
				+ "db_time>=to_date('"
				+ time
				+ " 00:00:00','yyyy-MM-dd HH24:mi:ss') "
				+ "and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-MM-dd HH24:mi:ss') "
				+ "and to_char(db_time,'mi')='00'   and area_id in (select area_id from hz_taxi.tb_area)) group by s order by s";
//		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return list;

	}

	// 重点监控区域上周平均
	public List<Map<String, Object>> findshangzhouaverg() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Date d = new Date();
		String time = sdf.format(d);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY);

		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date sTime = calendar.getTime();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date eTime = calendar.getTime();
		String st = sdf.format(sTime) + " 00:00:00";
		String et = sdf.format(eTime) + " 23:59:59";
		// String sql =
		// "select ONLINE_RATE from hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t where db_time>=to_date('"
		// + st
		// + "','yyyy-MM-dd HH24:mi:ss')"
		// + " and db_time<=to_date('"
		// + et
		// +
		// "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00' and ba_id ='0'";

		String sql = "select trunc(sum(taxi_quantity)/7) rate ,s "
				+ " from(select taxi_quantity,to_char(db_time,'HH24')s from hz_taxi.TB_TAXI_AREA_CONFIGURATION t "
				+ " where db_time>=to_date('" + st
				+ "','yyyy-MM-dd HH24:mi:ss') and " + " db_time<=to_date('"
				+ et
				+ "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00' "
				+ "and area_id in (select area_id from hz_taxi.tb_area)) "
				+ " group by s order by s asc";

//		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);

		return list;
	}

	// 重点监控区域车辆数
	public String findzdqu() {
		//Map<String, Object> m = new HashMap<String, Object>();
		List<Map<String, Object>> newList0 = findarea(0);
		List<Map<String, Object>> newList1 = findarea(1);
		List<Map<String, Object>> newList2 = findarea(2);
		List<Map<String, Object>> newList7 = findarea(7);
		List<Map<String, Object>> newList = findshangzhouaverg();
		List<Object>al=ZhuanHuan(getsObject(newList0, "rate"),
				getsObject(newList1, "rate"),
				getsObject(newList2, "rate"),
				getsObject(newList7, "rate"),
				getsObject(newList, "rate"));
		Map<Object, Object>jg= new HashMap<Object, Object>();
		jg.put("datas", al);
		return jacksonUtil.toJson(jg);
	}
	// 根据id查询区域
	public String editAreaID(String id) {
		String str = " select * from hz_taxi.tb_area where area_id = '"+id+"'";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		List<Object> newList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("AREA_ID", list.get(i).get("area_id"));
			map.put("AREA_NAME", list.get(i).get("area_name"));
			map.put("AREA_SIZE", list.get(i).get("AREA_SIZE"));
			map.put("AREA_DESCRIBE", list.get(i).get("AREA_DESCRIBE"));
			map.put("AREA_COORDINATES", list.get(i).get("AREA_COORDINATES"));
			String nums = list.get(i).get("ALARMNUM").toString();
			for(int i1=0;i1<nums.split(";").length;i1++){
				map.put("ALARMNUM", nums.split(";")[getybjnum()]);
			}
			newList.add(map);
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("datas", newList);
		return jacksonUtil.toJson(m);
	}
	// 查询区域
	public String findArea() {
		String str = " select * from hz_taxi.tb_area order by to_number(area_id) desc";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		List<Object> newList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("AREA_ID", list.get(i).get("area_id"));
			map.put("AREA_NAME", list.get(i).get("area_name"));
			map.put("AREA_SIZE", list.get(i).get("AREA_SIZE"));
			map.put("AREA_DESCRIBE", list.get(i).get("AREA_DESCRIBE"));
			map.put("AREA_COORDINATES", list.get(i).get("AREA_COORDINATES"));
			String nums = list.get(i).get("ALARMNUM").toString();
			for(int i1=0;i1<nums.split(";").length;i1++){
				map.put("ALARMNUM", nums.split(";")[getybjnum()]);
			}
			newList.add(map);
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("datas", newList);
		return jacksonUtil.toJson(m);
	}
	//根据时间添加区域预警数
	public int getybjnum(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String t = sdf.format(new Date());
		int a = timenum(t);
		if(a==timenum("23:58")||a==timenum("23:59")||a==timenum("00:02")||a==timenum("00:01")){
			return 0;
		}else if(a>=timenum("00:03")&&a<=timenum("00:57")){
			return 1;
		}else if(a>=timenum("00:58")&&a<=timenum("01:02")){
			return 2;
		}else if(a>=timenum("01:03")&&a<=timenum("01:57")){
			return 3;
		}else if(a>=timenum("01:58")&&a<=timenum("02:02")){
			return 4;
		}else if(a>=timenum("02:03")&&a<=timenum("02:57")){
			return 5;
		}else if(a>=timenum("02:58")&&a<=timenum("03:02")){
			return 6;
		}else if(a>=timenum("03:03")&&a<=timenum("03:57")){
			return 7;
		}else if(a>=timenum("03:58")&&a<=timenum("04:02")){
			return 8;
		}else if(a>=timenum("04:03")&&a<=timenum("04:57")){
			return 9;
		}else if(a>=timenum("04:58")&&a<=timenum("05:02")){
			return 10;
		}else if(a>=timenum("05:03")&&a<=timenum("05:57")){
			return 11;
		}else if(a>=timenum("05:58")&&a<=timenum("06:02")){
			return 12;
		}else if(a>=timenum("06:03")&&a<=timenum("06:57")){
			return 13;
		}else if(a>=timenum("06:58")&&a<=timenum("07:02")){
			return 14;
		}else if(a>=timenum("07:03")&&a<=timenum("07:57")){
			return 15;
		}else if(a>=timenum("07:58")&&a<=timenum("08:02")){
			return 16;
		}else if(a>=timenum("08:03")&&a<=timenum("08:57")){
			return 17;
		}else if(a>=timenum("08:58")&&a<=timenum("09:02")){
			return 18;
		}else if(a>=timenum("09:03")&&a<=timenum("09:57")){
			return 19;
		}else if(a>=timenum("09:58")&&a<=timenum("10:02")){
			return 20;
		}else if(a>=timenum("10:03")&&a<=timenum("10:57")){
			return 21;
		}else if(a>=timenum("10:58")&&a<=timenum("11:02")){
			return 22;
		}else if(a>=timenum("11:03")&&a<=timenum("11:57")){
			return 23;
		}else if(a>=timenum("11:58")&&a<=timenum("12:02")){
			return 24;
		}else if(a>=timenum("12:03")&&a<=timenum("12:57")){
			return 25;
		}else if(a>=timenum("12:58")&&a<=timenum("13:02")){
			return 26;
		}else if(a>=timenum("13:03")&&a<=timenum("13:57")){
			return 27;
		}else if(a>=timenum("13:58")&&a<=timenum("14:02")){
			return 28;
		}else if(a>=timenum("14:03")&&a<=timenum("14:57")){
			return 29;
		}else if(a>=timenum("14:58")&&a<=timenum("15:02")){
			return 30;
		}else if(a>=timenum("15:03")&&a<=timenum("15:57")){
			return 31;
		}else if(a>=timenum("15:58")&&a<=timenum("16:02")){
			return 32;
		}else if(a>=timenum("16:03")&&a<=timenum("16:57")){
			return 33;
		}else if(a>=timenum("16:58")&&a<=timenum("17:02")){
			return 34;
		}else if(a>=timenum("17:03")&&a<=timenum("17:57")){
			return 35;
		}else if(a>=timenum("17:58")&&a<=timenum("18:02")){
			return 36;
		}else if(a>=timenum("18:03")&&a<=timenum("18:57")){
			return 37;
		}else if(a>=timenum("18:58")&&a<=timenum("19:02")){
			return 38;
		}else if(a>=timenum("19:03")&&a<=timenum("19:57")){
			return 39;
		}else if(a>=timenum("19:58")&&a<=timenum("20:02")){
			return 40;
		}else if(a>=timenum("20:03")&&a<=timenum("20:57")){
			return 41;
		}else if(a>=timenum("20:58")&&a<=timenum("21:02")){
			return 42;
		}else if(a>=timenum("21:03")&&a<=timenum("21:57")){
			return 43;
		}else if(a>=timenum("21:58")&&a<=timenum("22:02")){
			return 44;
		}else if(a>=timenum("22:03")&&a<=timenum("22:57")){
			return 45;
		}else if(a>=timenum("22:58")&&a<=timenum("23:02")){
			return 46;
		}else if(a>=timenum("23:03")&&a<=timenum("23:57")){
			return 47;
		}else{
			return 0;
		}
	}
	public int timenum(String arg){
		return Integer.parseInt(arg.split(":")[0])*60+Integer.parseInt(arg.split(":")[1]);
	}
	// 增加区域
	public int addArea(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String area_name = String.valueOf(paramMap.get("AREA_NAME"));
		String AREA_DESCRIBE = String.valueOf(paramMap.get("AREA_DESCRIBE"));
		String AREA_COORDINATES = String.valueOf(paramMap
				.get("AREA_COORDINATES"));
		String ALARMNUM = String.valueOf(paramMap.get("ALARMNUM"));
		String AREA_SIZE = String.valueOf(paramMap.get("AREA_SIZE"));

		String str = " insert into hz_taxi.tb_area (area_name,AREA_DESCRIBE,AREA_COORDINATES,ALARMNUM,AREA_SIZE)"
				+ " values ('"
				+ area_name
				+ "','"
				+ AREA_DESCRIBE
				+ "','"
				+ AREA_COORDINATES + "','" + ALARMNUM + "','" + AREA_SIZE + "')";
		int count = jdbcTemplate2.update(str);
		return count;
	}

	// 修改区域
	public int editArea(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String area_name = String.valueOf(paramMap.get("AREA_NAME"));
		String AREA_DESCRIBE = String.valueOf(paramMap.get("AREA_DESCRIBE"));
		String id = String.valueOf(paramMap.get("AREA_ID"));

		String str = " update  hz_taxi.tb_area set area_name='" + area_name
				+ "',AREA_DESCRIBE='" + AREA_DESCRIBE + "' "
				+ "where area_id='" + id + "'";
		int count = jdbcTemplate2.update(str);
		return count;
	}

	// 删除区域
	public int delArea(String id) {

		String str = "delete from hz_taxi.tb_area where area_id='" + id + "'";
		int count = jdbcTemplate2.update(str);
		return count;
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

	// 查询公司
	public String findcomp() {
		List<Map<String, Object>> list = jdbcTemplate2
				.queryForList("select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK ");
		List<Object> a = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("name", list.get(i).get("comp_name"));
			map.put("id", list.get(i).get("comp_id"));
			a.add(map);
		}
		Map<Object, Object> m = new HashMap<Object, Object>();
		m.put("datas", a);

		return jacksonUtil.toJson(m);
	}

	// 根据公司id查询车号
	public String findvhic(String id) {
		String sql = "select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where 1=1";
		if (id != null && id.length() > 0) {
			sql += " and comp_id = '" + id + "'";
		}
//		System.out.println("查询车号" + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		List<Object> a = new ArrayList<Object>();

		for (int i = 0; i < list.size(); i++) {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("name", list.get(i).get("vehi_no"));
			map.put("id", list.get(i).get("vehi_no"));
			a.add(map);
		}
		Map<Object, Object> m = new HashMap<Object, Object>();
		m.put("datas", a);

		return jacksonUtil.toJson(m);
	}

	// 轨迹
	public String findGj(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String sTime = String.valueOf(paramMap.get("sTime"));
		String eTime = String.valueOf(paramMap.get("eTime"));
		String vehino = String.valueOf(paramMap.get("vehino"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		if(sTime==null&&sTime.length()==0&&sTime.equals("null")
				&&eTime==null&&eTime.length()==0&&eTime.equals("null")){
			return "{}";
		}else if(!sTime.substring(0, 4).equals(eTime.substring(0, 4))&&!sTime.substring(0, 4).equals(eTime.substring(0, 4))){
				return "{}";
		}else if(vehino==null&&vehino.length()==0){
					return "{}";
				}else{
					// String bname = String.valueOf(paramMap.get("bname"));
					String str = "select * from(select a.vehicle_num,a.speed_time,a.px,a.py,a.speed"
						+ ",a.direction,a.state, b.vehi_sim,b.comp_name,b.own_name,b.own_tel"
						+ " from hzgps_taxi.tb_gps_"
						+ getTable(sTime)
						+ "@taxilink a, "
						+ "hzgps_taxi.vw_vehicle@taxilink  b where a.vehicle_num = b.vehi_no"
						+ " and a.vehicle_num = '"
						+ vehino
						+ "' and "
						+ "speed_time >= to_date('"
						+ sTime
						+ "', 'yyyy-mm-dd hh24:mi:ss')"
						+ " and speed_time <= to_date('"
						+ eTime
						+ "', 'yyyy-mm-dd hh24:mi:ss')) " + "order by speed_time";
//					System.out.println("轨迹 "+ str);
					List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
					return jacksonUtil.toJson(list);
				}
		
	}

	// 在线营运率基类
	public List<Map<String, Object>> findzxyy24(int i) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String str = "select run_rate,to_char(db_time,'ddHH24')s from hz_taxi.TB_TAXI_RUN_RATE t where "
				+ "db_time>=to_date('"
				+ time
				+ " 00:00:00','yyyy-MM-dd HH24:mi:ss') "
				+ "and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-MM-dd HH24:mi:ss') "
				+ "and to_char(db_time,'mi')='00'  order by db_time ";
//		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return list;

	}

	// 处理6小时，jintianjiazuotian
	public List<Map<String, Object>> findzxyy48() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE));
		String time = dft.format(date.getTime());
		Calendar date2 = Calendar.getInstance();
		date2.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date2.getTime());
		String str = "select  run_rate,to_char(db_time,'ddHH24')s from hz_taxi.TB_TAXI_RUN_RATE t where "
				+ "db_time>=to_date('"
				+ time2
				+ " 00:00:00','yyyy-MM-dd HH24:mi:ss') "
				+ "and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-MM-dd HH24:mi:ss') "
				+ "and to_char(db_time,'mi')='00'  order by db_time ";
//		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return list;

	}

	// 在最近6小时在线营运
	public List<Map<String, Object>> findwozaixian() {
		List<Map<String, Object>> list = findzxyy48();
		String stime = "", etime = "", time = "";
		for (int i = 5; i > -1; i--) {
			Map<String, Object> map = new HashMap<String, Object>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
			time = df.format(new Date(System.currentTimeMillis() - 3600000
					* (i + 1)));
			stime = df2.format(new Date(System.currentTimeMillis() - 3600000
					* (i + 1)));
			etime = df2.format(new Date(System.currentTimeMillis() - 3600000
					* i));
			String sql = "select trunc(count(distinct (vhic))*100/9612) c from HZGPS_CITIZEN.TB_CITIZEN_"
					+ stime.substring(0, 4)
					+ "@TAXILINK45 t"
					+ " where shangche>=to_date('"
					+ stime
					+ "','yyyy-MM-dd hh24')"
					+ " and  shangche<to_date('"
					+ etime + "','yyyy-MM-dd hh24')";
//			System.out.println(sql);
			map.put("RUN_RATE", jdbcTemplate2.queryForObject(sql,Integer.class) + "%");
			map.put("S", time.substring(8, 10) + time.substring(11, 13));
			list.add(map);
		}
		return list;
	}

	// 查询在线营运率上周平均
	public List<Map<String, Object>> findzzyyaverage() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Date d = new Date();
		String time = sdf.format(d);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY);

		calendar.add(Calendar.DATE, -7);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date sTime = calendar.getTime();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date eTime = calendar.getTime();
		String st = sdf.format(sTime) + " 00:00:00";
		String et = sdf.format(eTime) + " 23:59:59";
		// String sql =
		// "select ONLINE_RATE from hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t where db_time>=to_date('"
		// + st
		// + "','yyyy-MM-dd HH24:mi:ss')"
		// + " and db_time<=to_date('"
		// + et
		// +
		// "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00' and ba_id ='0'";

		String sql = "select rpad(trunc(avg(replace(run_rate,'%',''))),length(trunc(avg(replace(run_rate,'%',''))))+1,'%') run_rate ,s "
				+ " from(select run_rate,to_char(db_time,'HH24')s from hz_taxi.TB_TAXI_RUN_RATE t "
				+ " where db_time>=to_date('"
				+ st
				+ "','yyyy-MM-dd HH24:mi:ss') and "
				+ " db_time<=to_date('"
				+ et
				+ "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00') "
				+ " group by s order by s asc";

//		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);

		return list;
	}

	// 在线营运率
	public String zxyy() {
		//Map<String, Object> m = new HashMap<String, Object>();
		List<Map<String, Object>> newList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList2 = findzxyy24(2);
		List<Map<String, Object>> newList7 = findzxyy24(7);
		List<Map<String, Object>> newList = findzzyyaverage();
		List<Map<String, Object>> newList_0 = findwozaixian();

		SimpleDateFormat df = new SimpleDateFormat("dd");
		String time = df.format(new Date());
		for (int i = 0; i < newList_0.size(); i++) {
			if (time.equals(newList_0.get(i).get("S").toString()
					.substring(0, 2))) {
				newList0.add(newList_0.get(i));
			} else {
				newList1.add(newList_0.get(i));
			}
		}
		List<Object> al=ZhuanHuan(getObject(newList0, "run_rate"),
				getObject(newList1, "run_rate"),
				getObject(newList2, "run_rate"),
				getObject(newList7, "run_rate"),
				getsObject(newList, "run_rate"));
		Map<Object, Object> jg = new HashMap<Object, Object>();
		jg.put("datas", al);
		return jacksonUtil.toJson(jg);
	}

	// 处理6小时，疑似停运车辆
	public List<Map<String, Object>> findysty48() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE));
		String time = dft.format(date.getTime());
		Calendar date2 = Calendar.getInstance();
		date2.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date2.getTime());
		String str = "select  (9612-run_count) run_count,to_char(db_time,'ddHH24')s from hz_taxi.TB_TAXI_RUN_COUNT t where "
				+ "db_time>=to_date('"
				+ time2
				+ " 00:00:00','yyyy-MM-dd HH24:mi:ss') "
				+ "and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-MM-dd HH24:mi:ss') "
				+ "and to_char(db_time,'mi')='00'  order by db_time ";
//		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return list;

	}

	// 在最近6小时在线停运
	public List<Map<String, Object>> findwotingyun() {
		List<Map<String, Object>> list = findysty48();
		String stime = "", etime = "", time = "";
		for (int i = 5; i > -1; i--) {
			Map<String, Object> map = new HashMap<String, Object>();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
			SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
			time = df.format(new Date(System.currentTimeMillis() - 3600000
					* (i + 1)));
			// stime = df2.format(new Date(System.currentTimeMillis() - 3600000
			// * (i + 1)));
			stime = df3.format(new Date()) + " 040000";
			etime = df2.format(new Date(System.currentTimeMillis() - 3600000
					* i));
			String sql = "select count(distinct (vhic)) c from HZGPS_CITIZEN.TB_CITIZEN_"
					+ stime.substring(0, 4)
					+ "@TAXILINK45 t"
					+ " where shangche>=to_date('"
					+ stime
					+ "','yyyy-MM-dd hh24miss')"
					+ " and  shangche<to_date('"
					+ etime + "','yyyy-MM-dd hh24')";
//			System.out.println(sql);
			map.put("RUN_COUNT", 9612 - jdbcTemplate2.queryForObject(sql,Integer.class));
			map.put("S", time.substring(8, 10) + time.substring(11, 13));
			list.add(map);
		}
		return list;
	}

	// 查询疑似停运车辆平均
	public List<Map<String, Object>> findystyaverage() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Date d = new Date();
		String time = sdf.format(d);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setFirstDayOfWeek(Calendar.MONDAY);

		calendar.add(Calendar.DATE, -3);
		// calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date sTime = calendar.getTime();
		// calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.add(Calendar.DATE, +2);

		Date eTime = calendar.getTime();
		String st = sdf.format(sTime) + " 00:00:00";
		String et = sdf.format(eTime) + " 23:59:59";
		// String sql =
		// "select ONLINE_RATE from hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t where db_time>=to_date('"
		// + st
		// + "','yyyy-MM-dd HH24:mi:ss')"
		// + " and db_time<=to_date('"
		// + et
		// +
		// "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00' and ba_id ='0'";

		String sql = "select (9612-trunc(avg(replace(run_count,'%','')))) run_count ,s "
				+ " from(select run_count,to_char(db_time,'HH24')s from hz_taxi.TB_TAXI_RUN_COUNT t "
				+ " where db_time>=to_date('"
				+ st
				+ "','yyyy-MM-dd HH24:mi:ss') and "
				+ " db_time<=to_date('"
				+ et
				+ "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00') "
				+ " group by s order by s asc";

//		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);

		return list;
	}

	// 疑似停运
	public String ysty() {
		//Map<String, Object> m = new HashMap<String, Object>();
		List<Map<String, Object>> newList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList = findystyaverage();
		List<Map<String, Object>> newList_0 = findwotingyun();

		SimpleDateFormat df = new SimpleDateFormat("dd");
		String time = df.format(new Date());
		for (int i = 0; i < newList_0.size(); i++) {
			if (time.equals(newList_0.get(i).get("S").toString()
					.substring(0, 2))) {
				newList0.add(newList_0.get(i));
			} else {
				newList1.add(newList_0.get(i));
			}
		}

		// List<Object> list0 = new ArrayList<Object>();
		int[] object = new int[24];
		for (int i = 0; i < newList0.size(); i++) {
			int count = Integer.parseInt(newList0.get(i).get("S").toString()
					.substring(2, 3).endsWith("0") ? newList0.get(i).get("S")
					.toString().substring(3, 4) : newList0.get(i).get("S")
					.toString().substring(2, 4));
			object[count] = Integer.parseInt(newList0.get(i).get("RUN_COUNT")
					.toString());

		}
		Object[] newObject = new Object[19];
		for (int i = 0; i < 19; i++) {
			newObject[i] = object[i + 5];
		}

		int[] object2 = new int[24];
		for (int i = 0; i < newList.size(); i++) {

			int count = Integer.parseInt(newList.get(i).get("S").toString()
					.substring(0, 1).endsWith("0") ? newList.get(i).get("S")
					.toString().substring(1, 2) : newList.get(i).get("S")
					.toString().substring(0, 2));
			object2[count] = Integer.parseInt(newList.get(i).get("RUN_COUNT")
					.toString());

		}
		Object[] newObject2 = new Object[19];
		for (int i = 0; i < 19; i++) {
			newObject2[i] = object2[i + 5];
		}

		int[] object3 = new int[19];
		for (int i = 0; i < newObject.length; i++) {
			object3[i] = Integer.parseInt(newObject[i].toString())
					- Integer.parseInt(newObject2[i].toString());
			if (object3[i] < 0) {
				object3[i] = 0;
			}

		}
		List<Object> al = new ArrayList<Object>();
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (int i = 0; i < newObject.length; i++) {
			if(newObject[i].toString().equals("0")){
				newObject[i]="";
			}
			map.put("a" + i, newObject[i]);
		}
		map.put("DATE", "今天");
		al.add(map);
		Map<Object, Object> map1 = new HashMap<Object, Object>();
		for (int i = 0; i < newObject2.length; i++) {
			if(newObject2[i].toString().equals("0")){
				newObject[i]="";
			}
			map1.put("a" + i, newObject2[i]);
		}
		map1.put("DATE", "前三日平均");
		al.add(map1);
		Map<Object, Object> map2 = new HashMap<Object, Object>();
		String zh="";
		for (int i = 0; i < object3.length; i++) {
			if(object3[i]==0){
				map2.put("a" + i, zh);
			}else{
				map2.put("a" + i, object3[i]);
			}
		}
		map2.put("DATE", "疑似停运数");
		al.add(map2);
		Map<Object, Object>jg=new HashMap<Object, Object>();
		jg.put("datas", al);
		return jacksonUtil.toJson(jg);
	}

	public Object[] getObject(List<Map<String, Object>> list, String str) {

		Object[] object = new Object[24];
		for (int i = 0; i < list.size(); i++) {

			int count = Integer.parseInt(list.get(i).get("S").toString()
					.substring(2, 3).endsWith("0") ? list.get(i).get("S")
					.toString().substring(3, 4) : list.get(i).get("S")
					.toString().substring(2, 4));
			object[count] = list.get(i).get(str.toUpperCase()).toString();

		}
		// System.out.println(list.toString());
		// System.out.println(Arrays.toString(object));
		return object;
	}

	public Object[] getsObject(List<Map<String, Object>> list, String str) {

		Object[] object = new Object[24];
		for (int i = 0; i < list.size(); i++) {

			int count = Integer.parseInt(list.get(i).get("S").toString()
					.substring(0, 1).endsWith("0") ? list.get(i).get("S")
					.toString().substring(1, 2) : list.get(i).get("S")
					.toString().substring(0, 2));
			object[count] = list.get(i).get(str.toUpperCase()).toString();

		}
		return object;
	}
	//将查询出的数据转换成页面显示的
	public List<Object> ZhuanHuan(Object[] newList0,
			Object[] newList1,
			Object[] newList2,
			Object[] newList3,
			Object[] newList4) {
		List<Object> al = new ArrayList<Object>();
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (int i = 0; i < newList0.length; i++) {
			map.put("a" + i, newList0[i]);
		}
		map.put("DATE", "今天");
		al.add(map);
		Map<Object, Object> map1 = new HashMap<Object, Object>();
		for (int i = 0; i < newList1.length; i++) {
			map1.put("a" + i, newList1[i]);
		}
		map1.put("DATE", "昨天");
		al.add(map1);
		Map<Object, Object> map2 = new HashMap<Object, Object>();
		for (int i = 0; i < newList2.length; i++) {
			map2.put("a" + i, newList2[i]);
		}
		map2.put("DATE", "前天");
		al.add(map2);
		Map<Object, Object> map3 = new HashMap<Object, Object>();
		for (int i = 0; i < newList3.length; i++) {
			map3.put("a" + i, newList3[i]);
		}
		map3.put("DATE", "上周");
		al.add(map3);
		Map<Object, Object> map4 = new HashMap<Object, Object>();
		for (int i = 0; i < newList4.length; i++) {
			map4.put("a" + i, newList4[i]);
		}
		map4.put("DATE", "上周平均");
		al.add(map4);
		return al;
	}

	public String qyjk() {
		DataItem data = (DataItem) GisData.map.get("data");
		if(null != data){
			Map map = new HashMap();
			map.put("vehilist", data.getVehilist());
			map.put("arealist",  data.getArealist());
			map.put("num",  data.getNum());
			map.put("l2",  data.getL2());
			map.put("l3",  data.getL3());
			map.put("l4",  data.getL4());
			map.put("l5",  data.getL5());
			map.put("l6",  data.getL6());
			map.put("l7",  data.getL7());
			//System.out.println(vehilist.size());
			return jacksonUtil.toJson(map);
		}
		
		return "{}";
	}

	//根据前台车牌查询车号
	public String findonevhic(String vhic){
//		String sql="select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK where (vehi_no like '%"+vhic+"%' or comp_name like '%"+vhic+"%') and vehi_no like '%浙AT%'";
		String sql1 = "select * from (select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK where"
				+ " (vehi_no like '%"+vhic+"%' or comp_name like '%"+vhic+"%') and vehi_no like '%浙AT%')"
						+ " a left join hz_taxi.TB_TAXI_STATUS s on a.sim_num = s.mdt_no";
		System.out.println(sql1);
		List <Map<String, Object>> list=jdbcTemplate2.queryForList(sql1);
		List al = new ArrayList();
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("title", list.get(i).get("VEHI_NO"));
			map.put("category", list.get(i).get("BA_NAME").toString().replace("有限公司", "公司"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("results", al);
		
		return  jacksonUtil.toJson(m);
	}

	public String qyadd(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String qy_fwdmc = String.valueOf(paramMap.get("qy_fwdmc"));
		String qy_jwd = String.valueOf(paramMap.get("qy_jwd"));
String sql= "insert into zhpt.TB_AREA_CZCBZ@taxilink114 t (t.area_name,t.coordinates) values ('"+qy_fwdmc+"','"+qy_jwd+"')";
int count=jdbcTemplate2.update(sql);
		return "1";
	}

	public String qycx() {
		String sql1 = "select * from TB_AREA_CZCBZ";
		System.out.println(sql1);
		List <Map<String, Object>> list=jdbcTemplate2.queryForList(sql1);
		List al = new ArrayList();
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			Map map = new HashMap();
			map.put("AREA_NAME", list.get(i).get("AREA_NAME").toString());
			map.put("AREA_COORDINATES", list.get(i).get("AREA_COORDINATES").toString());
			al.add(map);
		}
		Map m = new HashMap();
		m.put("results", al);
		return  jacksonUtil.toJson(m);
	}

	public int client(HttpServletRequest request,String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String mdt_no = String.valueOf(paramMap.get("mdt_no"));
		String note = String.valueOf(paramMap.get("note"));
		int a = (int)(1+Math.random()*(100000-1+1));
//		Date now=new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String time = dateFormat.format(now);
//		String sql="insert into TB_G20WW_DDRZ t (t.DDNR,t.DDSJ) values ('"+note+"',to_date('"+time+"','yyyy-mm-dd hh24:mi:ss'))";
//		int count=jdbcTemplate.update(sql);
		String[] arr=mdt_no.split(",");
		try {
				Socket socket = new Socket("192.168.0.105", 7706);
				for (int j = 0; j < arr.length; j++) {
					OutputStream out = socket.getOutputStream();
					String nr = arr[j]+"|"+a+"|"+note+"|4";
//					String nr = "100100888888|4|司机你好,测试|4";
					byte[] bt = nr.getBytes("GB2312");
					out.write(bt);
				}
				socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public String raodao(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00"); 
		String raodao_stime = String.valueOf(paramMap.get("raodao_stime"));
		String raodao_etime = String.valueOf(paramMap.get("raodao_etime"));
		String raodao_vhic = String.valueOf(paramMap.get("raodao_vhic"));
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String str1 ="select * from (select t.*,rownum r from TB_TAXI_PRIVIDE t where t.vhic like '%"+raodao_vhic+"%' and t.s_time >= to_date('"+raodao_stime+"', 'yyyy-mm-dd hh24:mi:ss') and t.e_time <= to_date('"+raodao_etime+"', 'yyyy-mm-dd hh24:mi:ss'))tt where tt.r<=" + (page * pageSize) + " and tt.r>= "+(page - 1) * pageSize;
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str1);
		
		List<Object> newList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			//计价器里程
//			double jjq = Double.parseDouble(list.get(i).get("J_MILE").toString());
//			//程序计算里程(GPS里程)
			double gps = Double.parseDouble(list.get(i).get("S_MILE").toString());
			//速度最优里程(最短时间里程)
			double zy = Double.parseDouble(list.get(i).get("R_MILE").toString());
			//距离最短里程
			double zd = Double.parseDouble(list.get(i).get("R_MILE_2").toString());
			map.put("RDONE", df.format(gps/zd));
			map.put("RDTWO", df.format(gps/zy));
//			if( (jjq-zy)/zy >1.2){
//				map.put("RD", "1");
//			}else{
//				map.put("RD", "0");
//			}
			map.put("id", i+1);
			newList.add(map);
		}
		String sql1 ="select * from TB_TAXI_PRIVIDE t where t.vhic like '%"+raodao_vhic+"%' and t.s_time >= to_date('"+raodao_stime+"', 'yyyy-mm-dd hh24:mi:ss') and t.e_time <= to_date('"+raodao_etime+"', 'yyyy-mm-dd hh24:mi:ss')";
		List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(sql1);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("datas", newList);
		m.put("count", list1.size());
		System.out.println(m);
		return jacksonUtil.toJson(m);
	}

	public String jjqyc(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00"); 
		String jjqyc_stime = String.valueOf(paramMap.get("jjqyc_stime"));
		String jjqyc_etime = String.valueOf(paramMap.get("jjqyc_etime"));
		String jjqyc_vhic = String.valueOf(paramMap.get("jjqyc_vhic"));
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String str1 ="select * from (select t.*,rownum r from TB_TAXI_PRIVIDE t where t.vhic like '%"+jjqyc_vhic+"%' and t.s_time >= to_date('"+jjqyc_stime+"', 'yyyy-mm-dd hh24:mi:ss') and t.e_time <= to_date('"+jjqyc_etime+"', 'yyyy-mm-dd hh24:mi:ss'))tt where tt.r<=" + (page * pageSize) + " and tt.r>= "+(page - 1) * pageSize;
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str1);
		
		List<Object> newList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			//计价器里程
			double jjq = Double.parseDouble(list.get(i).get("J_MILE").toString());
//			//程序计算里程(GPS里程)
			double gps = Double.parseDouble(list.get(i).get("S_MILE").toString());
			//速度最优里程(最短时间里程)
			map.put("RDONE", df.format(jjq/gps));
//			if( (jjq-zy)/zy >1.2){
//				map.put("RD", "1");
//			}else{
//				map.put("RD", "0");
//			}
			map.put("id", i+1);
			newList.add(map);
		}
		String sql1 ="select * from TB_TAXI_PRIVIDE t where t.vhic like '%"+jjqyc_vhic+"%' and t.s_time >= to_date('"+jjqyc_stime+"', 'yyyy-mm-dd hh24:mi:ss') and t.e_time <= to_date('"+jjqyc_etime+"', 'yyyy-mm-dd hh24:mi:ss')";
		List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(sql1);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("datas", newList);
		m.put("count", list1.size());
		System.out.println(m);
		return jacksonUtil.toJson(m);
	}
	public String clgz(String vhic){
		String sql="select *  from TB_TAXI_STATUS_NEW t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.mdt_no=v.sim_num"
				+ " and vehi_no = '"+vhic+"'";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return jacksonUtil.toJson(list);
	}

}
