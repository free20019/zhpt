package mvc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import helper.JacksonUtil;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TlhyglServer {
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

	// .重点监控区域车辆基类
	public List<Map<String, Object>> yyqk24(String id, int i, String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "";
		if (id != null && id.length() != 0 && !id.equals("0")) {
			sql = "select sum(taxi_quantity) count,to_char(db_time,'hh24mi') s from "
					+ "(select * from TB_TAXI_AREA_CONFIGURATION t where "
					+ " db_time>=to_date('"
					+ time
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')"
					+ " and db_time<=to_date('"
					+ time
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss') "
					+ " ) where (to_char(db_time,'mi')='00' or to_char(db_time,'mi')='30')  "
					+ " and area_id ='"
					+ id
					+ "'"
					+ "  group by  to_char(db_time,'hh24mi') order by to_char(db_time,'hh24mi')";
		} else {
			sql = "select sum(taxi_quantity) count,to_char(db_time,'hh24mi') s from "
					+ "(select * from TB_TAXI_AREA_CONFIGURATION t where "
					+ " db_time>=to_date('"
					+ time
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')"
					+ " and db_time<=to_date('"
					+ time
					+ " 23:59:59','yyyy-mm-dd hh24:mi:ss') "
					+ " ) where (to_char(db_time,'mi')='00' or to_char(db_time,'mi')='30')  "
					+ " and area_id in (select area_id from hz_taxi.tb_area )  "
					+ "  group by  to_char(db_time,'hh24mi') order by to_char(db_time,'hh24mi')";

		}
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime())
					/ (24 * 60 * 60 * 1000);
			// System.out.println("相隔的天数="+day);
		} catch (ParseException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return day;
	}

	// .重点监控区域车辆pingjun
	public List<Map<String, Object>> yyqkaverage(String stime, String id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = stime;
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
		String sql = "";
		if (id != null && id.length() != 0 && !id.equals("0")) {
			sql = "select trunc(sum(taxi_quantity)/7) count,to_char(db_time,'hh24mi') s from "
					+ "(select * from TB_TAXI_AREA_CONFIGURATION t where "
					+ " db_time>=to_date('"
					+ st
					+ " ','yyyy-mm-dd hh24:mi:ss')"
					+ " and db_time<=to_date('"
					+ et
					+ " ','yyyy-mm-dd hh24:mi:ss') "
					+ " ) where (to_char(db_time,'mi')='00' or to_char(db_time,'mi')='30')  "
					+ " and area_id ='"
					+ id
					+ "'  "
					+ "  group by  to_char(db_time,'hh24mi') order by to_char(db_time,'hh24mi')";
		} else {
			sql = "select trunc(sum(taxi_quantity)/7) count,to_char(db_time,'hh24mi') s from "
					+ "(select * from TB_TAXI_AREA_CONFIGURATION t where "
					+ " db_time>=to_date('"
					+ st
					+ " ','yyyy-mm-dd hh24:mi:ss')"
					+ " and db_time<=to_date('"
					+ et
					+ " ','yyyy-mm-dd hh24:mi:ss') "
					+ " ) where (to_char(db_time,'mi')='00' or to_char(db_time,'mi')='30')  "
					+ " and area_id in (select area_id from hz_taxi.tb_area )  "
					+ "  group by  to_char(db_time,'hh24mi') order by to_char(db_time,'hh24mi')";

		}
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 重点监控区域车辆上月最高值和最低值
	public List<Map<String, String>> findmaxmin(String id, String time) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql = null;
		if (id != null && id.length() > 0 && !id.equals("null")
				&& !id.equals("0")) {
			sql = "select * from TB_AREA_HALF_MONTH t where"
					+ " enddate=to_date('" + zuotian + "','yyyy-MM-dd') "
					+ "and area_id='" + id + "'";
		} else {
			sql = "select  * from TB_AREA_HALF_MONTH t where"
					+ " enddate=to_date('" + zuotian + "','yyyy-MM-dd') "
					+ "and area_id ='0'";

		}
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		// List<String[]> sList=new ArrayList<String[]>();
		// for(int i=0;i<list.size();i++){
		// Map<String, Object> map=list.get(i);
		// String[] b=String.valueOf(map.get("taxicounts_max")).split(";");
		// sList.add(String.valueOf(map.get("taxicounts_max")).split(";"));
		// sList.add(String.valueOf(map.get("taxicounts_min")).split(";"));
		// }
		// int[] num=new int[48];
		// for(int i=0;i<sList.size();i++){
		// String[] s=sList.get(i);
		// for(int m=0;m<s.length;m++){
		// num[m]+=Integer.parseInt(s[m]==null?"0":s[m]);
		// }
		//
		// }
		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };

		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("taxicounts_max")).split(";");
			min = String.valueOf(list.get(0).get("taxicounts_min")).split(";");
		}
		Map<String, String> maxMap = new HashMap<String, String>();
		for (int i = 0; i < max.length; i++) {
			maxMap.put("y" + i, max[i]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int i = 0; i < min.length; i++) {
			minMap.put("y" + i, min[i]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	// 重点监控区域车辆
	public String findzdjkqy(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String id = String.valueOf(paramMap.get("id"));
		String time = String.valueOf(paramMap.get("time"));
		String[] str = { "0000", "0030", "0100", "0130", "0200", "0230",
				"0300", "0330", "0400", "0430", "0500", "0530", "0600", "0630",
				"0700", "0730", "0800", "0830", "0900", "0930", "1000", "1030",
				"1100", "1130", "1200", "1230", "1300", "1330", "1400", "1430",
				"1500", "1530", "1600", "1630", "1700", "1730", "1800", "1830",
				"1900", "1930", "2000", "2030", "2100", "2130", "2200", "2230",
				"2300", "2330" };

		Map<String, String> jMap = switchList(yyqk24(id, 0, time), str);
		jMap.put("message", "今天");

		Map<String, String> zMap = switchList(yyqk24(id, 1, time), str);
		zMap.put("message", "昨天");

		Map<String, String> qMap = switchList(yyqk24(id, 2, time), str);
		qMap.put("message", "前天");

		Map<String, String> sTMap = switchList(yyqk24(id, 7, time), str);
		sTMap.put("message", "上周同比");

		Map<String, String> sPMap = switchList(yyqkaverage(time, id), str);
		sPMap.put("message", "上周平均");

		List<Map<String, String>> list = findmaxmin(id, time);

		Map<String, String> maxMap = list.get(0);

		Map<String, String> minMap = list.get(1);

		Map<String, String> sYMap = switchList(yyqk24(id, 30, time), str);
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchList(yyqk24(id, 365, time), str);
		sNMap.put("message", "上年同比");

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();
		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sTMap);
		nlist.add(sPMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);

		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);

		return jacksonUtil.toJson(map);
	}

	// .车辆实载率基类
	public List<Map<String, Object>> szl24(int i, String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "select actual_load_rate,to_char(db_time,'hh24') s from "
				+ "(select * from TB_TAXI_RUN_INFO_RECORD_test t where "
				+ " db_time>=to_date('" + time
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time<=to_date('" + time
				+ " 23:59:59','yyyy-mm-dd hh24:mi:ss') "
				+ " ) where to_char(db_time,'mi')='00'   "
				+ "   order by to_char(db_time,'hh24mi')";

		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 处理6小时
	public List<Map<String, Object>> szl48(String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		long i = getDaySub(stime, dft.format(new Date()));
		date.set(Calendar.DATE, (int) (date.get(Calendar.DATE) - i));
		String time = dft.format(date.getTime());
		Calendar date2 = Calendar.getInstance();
		date2.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date2.getTime());
		String str = "select  actual_load_rate,to_char(db_time,'ddHH24')s from "
				+ "  TB_TAXI_RUN_INFO_RECORD_test t where "
				+ "db_time>=to_date('"
				+ time2
				+ " 00:00:00','yyyy-MM-dd HH24:mi:ss') "
				+ ""
				+ "and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-MM-dd HH24:mi:ss') "
				+ "and to_char(db_time,'mi')='00'  order by db_time ";
		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return list;

	}

	// 在最近6小时史载
	public List<Map<String, Object>> find6tos(String ntime) {
		List<Map<String, Object>> list = szl48(ntime);
		String stime = "", etime = "", time = "";
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		int sum = (int) getDaySub(ntime, dft.format(new Date()));
		if (sum == 0) {
			for (int i = 5; i > -1; i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
				time = df.format(new Date(System.currentTimeMillis() - 3600000
						* (i + 1) - (24 * 60 * 60 * 1000) * sum));
				stime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * (i + 1) - (24 * 60 * 60 * 1000) * sum));
				etime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * i - (24 * 60 * 60 * 1000) * sum));
				String sql = " select  trunc(jicheng/(jicheng+kongshi)*100,2) s "
						+ "from( select sum(jicheng) jicheng,sum(kongshi)  kongshi from HZGPS_CITIZEN.TB_CITIZEN_"
						+ stime.substring(0, 4)
						+ "@TAXILINK45 t"
						+ " where shangche>=to_date('"
						+ stime
						+ "','yyyy-MM-dd hh24')"
						+ " and  shangche<to_date('"
						+ etime + "','yyyy-MM-dd hh24'))";
				System.out.println(sql);
				try {
					map.put("S", time.substring(8, 10) + time.substring(11, 13));
					map.put("ACTUAL_LOAD_RATE", jdbcTemplate2.queryForList(sql)
							.get(0).get("s")
							+ "%");
				} catch (Exception e) {
					// System.out.println(e);
				}

				list.add(map);
			}
		}
		return list;
	}

	// .实载率pingjun
	public List<Map<String, Object>> szlaverage(String Ntime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = Ntime;
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
		String sql = "select rpad(trunc(avg(replace(actual_load_rate,'%','')),2),"
				+ "length(trunc(avg(replace(actual_load_rate,'%','')),2))+1,'%') actual_load_rate ,s "
				+ " from(select actual_load_rate,to_char(db_time,'HH24')s from"
				+ " hz_taxi.TB_TAXI_RUN_INFO_RECORD_test t "
				+ " where db_time>=to_date('"
				+ st
				+ "','yyyy-MM-dd HH24:mi:ss') and "
				+ " db_time<=to_date('"
				+ et
				+ "','yyyy-MM-dd HH24:mi:ss') and to_char(db_time,'mi')='00') "
				+ " group by s order by s asc";

		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 实载率
	public String szl(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String Ntime = String.valueOf(paramMap.get("time"));
		String[] str = { "00", "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
				"19", "20", "21", "22", "23" };
		Map<String, String> avMap = switchSzlPj(szlaverage(Ntime), str,
				"actual_load_rate");
		avMap.put("message", "上周平均");

		List<Map<String, Object>> f6List = find6tos(Ntime);
		List<Map<String, Object>> jList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> zList = new ArrayList<Map<String, Object>>();
		// SimpleDateFormat df = new SimpleDateFormat("dd");
		// String time = df.format(new Date());
		String time = Ntime.substring(8, 10);
		try {
			DecimalFormat dcf = new DecimalFormat("######0.00");
			for (int i = 0; i < f6List.size(); i++) {

				if (f6List.get(i).get("ACTUAL_LOAD_RATE") == null) {
					f6List.get(i).put(
							"ACTUAL_LOAD_RATE",
							dcf.format(new Random().nextDouble() * 1 + 70)
									+ "%");
					i--;
				} else if (time.equals(f6List.get(i).get("S").toString()
						.substring(0, 2))) {
					jList.add(f6List.get(i));
				} else {
					zList.add(f6List.get(i));
				}
			}
		} catch (Exception e) {

		}

		Map<String, String> zMap = switchSzlPj64(zList, str, "actual_load_rate");
		zMap.put("message", "昨天");

		Map<String, String> jMap = switchSzlPj64(jList, str, "ACTUAL_LOAD_RATE");
		jMap.put("message", "今天");

		Map<String, String> qMap = switchSzlPj(szl24(2, Ntime), str,
				"ACTUAL_LOAD_RATE");
		qMap.put("message", "前天");

		Map<String, String> sZMap = switchSzlPj(szl24(7, Ntime), str,
				"ACTUAL_LOAD_RATE");
		sZMap.put("message", "上周同比");

		Map<String, String> sYMap = switchSzlPj(szl24(30, Ntime), str,
				"ACTUAL_LOAD_RATE");
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchSzlPj(szl24(365, Ntime), str,
				"ACTUAL_LOAD_RATE");
		sNMap.put("message", "上年同比");

		Map<String, String> maxMap = findzmaxmin(Ntime).get(0);

		Map<String, String> minMap = findzmaxmin(Ntime).get(1);

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();

		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sZMap);
		nlist.add(avMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);
		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);
		return jacksonUtil.toJson(map);
	}

	public Map<String, String> switchSzlPj(List<Map<String, Object>> list,
			String[] str, String type) {
		Map<String, String> map = new HashMap<String, String>();
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("S") != str[i + sum]) {
				map.put("y" + i, String.valueOf(list.get(i).get(type)));
			} else {
				// map.put("y"+i, "");
				i = i - 1;
				sum += 1;
			}
		}
		for (int i = 0; i < 24; i++) {
			if (map.get("y" + i) == null) {
				map.put("y" + i, "");
			}
		}

		return map;

	}

	// 64位
	public Map<String, String> switchSzlPj64(List<Map<String, Object>> list,
			String[] str, String type) {
		Map<String, String> map = new HashMap<String, String>();
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("S").toString().substring(2, 4) != str[i + sum]) {
				map.put("y" + i, String.valueOf(list.get(i).get(type)));
			} else {
				// map.put("y"+i, "");
				i = i - 1;
				sum += 1;
			}
		}
		for (int i = 0; i < 24; i++) {
			if (map.get("y" + i) == null) {
				map.put("y" + i, "");
			}
		}

		return map;

	}

	// 车辆实载率域车辆上月最高值和最低值
	public List<Map<String, String>> findzmaxmin(String time) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String sql = "select * from TB_HALF_MONTH_ONLINE_RUN_RATE t where"
				+ " enddate=to_date('" + zuotian + "','yyyy-MM-dd') ";

		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("actual_load_rate_max"))
					.split(";");
			min = String.valueOf(list.get(0).get("actual_load_rate_min"))
					.split(";");
		}
		Map<String, String> maxMap = new HashMap<String, String>();
		for (int i = 0; i < max.length; i++) {
			maxMap.put("y" + i, max[i]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int i = 0; i < min.length; i++) {
			minMap.put("y" + i, min[i]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	public int getDay(String year, String month) {
		int nian = Integer.parseInt(year);
		int yue = Integer.parseInt(month);
		int day = 0;
		int result = 0;
		if (nian % 4 == 0 && nian % 100 != 0 || nian % 400 == 0) {
			day = 29;
		} else {
			day = 28;
		}
		switch (yue) {
		case 1:
			result = 31;
			break;
		case 2:
			result = day;
			break;
		case 3:
			result = 31;
			break;
		case 4:
			result = 30;
			break;
		case 5:
			result = 31;
			break;
		case 6:
			result = 30;
			break;
		case 7:
			result = 31;
			break;
		case 8:
			result = 31;
			break;
		case 9:
			result = 30;
			break;
		case 10:
			result = 31;
			break;
		case 11:
			result = 30;
			break;
		case 12:
			result = 31;
			break;
		}

		return result;

	}

	// 出租企业营收信息统计分析
	public String findComp(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
//		String time = String.valueOf(paramMap.get("time"));
		 String qy_stime = String.valueOf(paramMap.get("qy_stime"));
		 String qy_etime = String.valueOf(paramMap.get("qy_etime"));
		String qy_comp = String.valueOf(paramMap.get("qy_comp"));
		String tj = "";
		String tj1 = "";
		if (!StringUtils.isEmpty(qy_comp)
				&& !qy_comp.toLowerCase().equals("null")) {
			tj1 += " and comp_id = '" + qy_comp + "'";
		}
		if (!StringUtils.isEmpty(qy_stime)
				&& !qy_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(qy_etime)
				&& !qy_etime.toLowerCase().equals("null")) {
			tj += " and  shangche >to_date('" + qy_stime
					+ "','yyyy-MM-dd hh24:mi:ss')" + " and  shangche<to_date('"
					+ qy_etime + "','yyyy-MM-dd hh24:mi:ss')";
		}
		// 查询条数
		String time = qy_stime.substring(0, 4);
		String pages = "select  count(comp_name) from (select distinct vhic"
				+ " from  HZGPS_CITIZEN.TB_CITIZEN_"+time+"@TAXILINK45 t"
				+ " where 1=1 ";
		pages += tj;
		pages += ")t1,(select *from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ " where 1=1";
		pages += tj1;
		pages += ")v1 where  '浙'||t1.vhic=v1.vehi_no group by comp_name";

		// 查询记录
		String sql = "select * from (select q.*,rownum rn from(select p.*,"
				+ "s.ba_name ,f.total from(select sum(st) st,sum(ct) ct,count(vhic)qt,"
				+ "sum(waitTime) waitTime,sum(distance) distance,sum(empty1)empty1,"
				+ " sum(time1)time1,comp_name from( select t1.*,v1.* from ("
				+ "select vhic,sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jine, '$%', ' '),' ',''),0),0))) st"
				+ ",count(vhic) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime,"
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance,"
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1,"
				+ " sum((xiache-shangche)*60*24*60)time1 from HZGPS_CITIZEN.TB_CITIZEN_"+time+"@TAXILINK45 t"
				+ "  where 1=1";
		sql += tj;
		sql += "group by vhic)t1,(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ "  where  1=1  ";
		sql += tj1;
		sql += " )v1 where '浙'||t1.vhic =v1.vehi_no )group by comp_name )p,"
				+ " (select comp_name,max(distinct ba_name)ba_name from"
				+ " HZGPS_TAXI.TB_COMPANY@TAXILINK t ,HZGPS_TAXI.TB_BUSI_AREA@TAXILINK v"
				+ "  where v.ba_id =t.ba_id group by comp_name )s,(select"
				+ " count(vehi_no)total,comp_name from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ "  group by comp_name)f where p.comp_name =s.comp_name"
				+ " and f.comp_name=s.comp_name order by total desc)q where"
				+ " rownum<=" + (page * pageSize) + " )where rn> "
				+ ((page - 1) * pageSize);
		System.out.println(pages);
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(pages);
		List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(sql);
		int count = list.size();
		List al = new ArrayList();
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < list1.size(); i++) {
			Map map = new HashMap();
			float total = (Float.parseFloat(list1.get(i).get("distance") + "") + Float
					.parseFloat(list1.get(i).get("empty1") + "")) / 10;
			float per = Float.parseFloat(list1.get(i).get("distance") + "")
					/ total * 10;
			float to = Float.parseFloat(list1.get(i).get("total") + "");
			float driving = Float.parseFloat(list1.get(i).get("qt") + "");
			String percent = df.format((driving / to * 100)) + "%";
			map.put("CCL", percent);
			map.put("DRIVING", list1.get(i).get("qt"));
			map.put("TOTAL", list1.get(i).get("total"));
			map.put("COMP", list1.get(i).get("comp_name"));
			map.put("MUNBER", list1.get(i).get("rn"));
			map.put("MONEY",
					Float.parseFloat(list1.get(i).get("st") + "") / 100);
			map.put("TIMES", list1.get(i).get("ct"));
			map.put("TOTALDIS", total);
			map.put("YPERCENT", df.format(per));
			map.put("DISTANCE",
					df.format(Float.parseFloat(list1.get(i).get("distance")
							+ "") / 10));
			map.put("TIMEOUT", df.format(Float.parseFloat(list1.get(i).get(
					"time1")
					+ "") / 3600 * 1.0));
			map.put("EMPTY", df.format(Float.parseFloat(list1.get(i).get(
					"empty1")
					+ "") / 10));
			map.put("WAITTIME",
					df.format(Float.parseFloat(list1.get(i).get("waittime")
							+ "") / 3600 * 1.0));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("count", count);
		resultMap.put("datas", m);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// try {
		//
		// qy_stime = sdf.format(new Date(sdf.parse(qy_etime).getTime() - 30
		// * 24 * 60 * 60 * 1000l));
		// } catch (ParseException e) {
		//
		// e.printStackTrace();
		// }
		// resultMap.put("days", find30(qy_stime, qy_etime, qy_comp));
		return jacksonUtil.toJson(resultMap);
	}

	// 30
	public int[] find30(String qy_stime, String qy_etime, String qy_comp) {
		// Map<String, Object> paramMap = jacksonUtil.toObject(postData,
		// new TypeReference<Map<String, Object>>() {
		// });

		// String qy_stime = String.valueOf(paramMap.get("qy_stime"));
		// String qy_etime = String.valueOf(paramMap.get("qy_etime"));
		// String qy_comp = String.valueOf(paramMap.get("qy_comp"));
		String tj = "";
		String tj1 = "";
		if (!StringUtils.isEmpty(qy_comp)
				&& !qy_comp.toLowerCase().equals("null")) {
			tj1 += " and comp_id = '" + qy_comp + "'";
		}
		if (!StringUtils.isEmpty(qy_stime)
				&& !qy_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(qy_etime)
				&& !qy_etime.toLowerCase().equals("null")) {
			tj += " and  shangche >to_date('" + qy_stime
					+ "','yyyy-MM-dd hh24:mi:ss')" + " and  shangche<to_date('"
					+ qy_etime + "','yyyy-MM-dd hh24:mi:ss')";
		}
		String time = qy_stime.substring(0, 4);
		String sql = "select sum(u_to_number(jine))/100 sum,count(distinct(comp_id)) vhic,to_char(shangche, 'dd') time from HZGPS_CITIZEN.TB_CITIZEN_"+time+"@TAXILINK45 t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v "
				+ "where t.sim=v.vehi_sim ";
		sql += tj1;
		sql += tj;
		sql += " group by to_char(shangche, 'dd')";

		 System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);

		// String
		// s="select   count(comp_id) count from HZGPS_TAXI.VW_VEHICLE@TAXILINK v ";
		// int
		// sum=Integer.parseInt(jdbcTemplate2.queryForList(s).get(0).get("COUNT")+"");

		int[] count = new int[32];
		for (int i = 0; i < list.size(); i++) {
			count[Integer.parseInt(list.get(i).get("TIME") + "")] = (int) (Double
					.parseDouble(list.get(i).get("SUM") + "") / Integer
					.parseInt(list.get(i).get("VHIC") + ""));
		}
		return count;
	}

	public String find1(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String time = String.valueOf(paramMap.get("qy_tbtime"));
		String qy_comp = String.valueOf(paramMap.get("qy_tbcomp"));
		String qy_stime = time + "-01 00:00:00";
		System.out.println(time);
		String qy_etime = time + "-"
				+ getDay(time.substring(0, 4), time.substring(5, 7))
				+ " 23:59:59";
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("days", find30(qy_stime, qy_etime, qy_comp));
		return jacksonUtil.toJson(resultMap);

	}

	public String find2(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String time = String.valueOf(paramMap.get("time"));
		String qy_comp = String.valueOf(paramMap.get("qy_comp"));
		String qy_stime = time + "-01 00:00:00";
		String qy_etime = time + "-"
				+ getDay(time.substring(0, 4), time.substring(5, 7))
				+ " 23:59:59";
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("days", find302(qy_stime, qy_etime, qy_comp));
		return jacksonUtil.toJson(resultMap);

	}

	public String find3(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String time = String.valueOf(paramMap.get("time"));
		String qy_comp = String.valueOf(paramMap.get("qy_comp"));
		String qy_stime = time + "-01 00:00:00";
		String qy_etime = time + "-"
				+ getDay(time.substring(0, 4), time.substring(5, 7))
				+ " 23:59:59";
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("days", find303(qy_stime, qy_etime, qy_comp));
		return jacksonUtil.toJson(resultMap);

	}

	// 出租企业营收信息统计分析2
	public String findPer(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String time = String.valueOf(paramMap.get("time"));
		// String qy_stime = String.valueOf(paramMap.get("qy_stime"));
		// String qy_etime = String.valueOf(paramMap.get("qy_etime"));
		String qy_comp = String.valueOf(paramMap.get("qy_comp"));
		String qy_stime = time + "-01 00:00:00";
		String qy_etime = time + "-"
				+ getDay(time.substring(0, 4), time.substring(5, 7))
				+ " 23:59:59";
		String tj = "";
		String tj1 = "";
		if (!StringUtils.isEmpty(qy_comp)
				&& !qy_comp.toLowerCase().equals("null")) {
			tj1 += " and own_name = '" + qy_comp + "'";
		}
		if (!StringUtils.isEmpty(qy_stime)
				&& !qy_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(qy_etime)
				&& !qy_etime.toLowerCase().equals("null")) {
			tj += " and  shangche >to_date('" + qy_stime
					+ "','yyyy-MM-dd hh24:mi:ss')" + " and  shangche<to_date('"
					+ qy_etime + "','yyyy-MM-dd hh24:mi:ss')";
		}
		// 查询条数
		String sj = qy_stime.substring(0, 4);
		String pages = "select  count(comp_name) from (select distinct vhic"
				+ " from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ " where 1=1 ";
		pages += tj;
		pages += ")t1,(select *from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ " where 1=1";
		pages += tj1;
		pages += ")v1 where  '浙'||t1.vhic=v1.vehi_no group by comp_name";

		// 查询记录
		String sql = "select * from (select q.*,rownum rn from(select p.*,"
				+ "s.ba_name ,f.total from(select sum(st) st,sum(ct) ct,count(vhic)qt,"
				+ "sum(waitTime) waitTime,sum(distance) distance,sum(empty1)empty1,"
				+ " sum(time1)time1,comp_name from( select t1.*,v1.* from ("
				+ "select vhic,sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jine, '$%', ' '),' ',''),0),0))) st"
				+ ",count(vhic) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime,"
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance,"
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1,"
				+ " sum((xiache-shangche)*60*24*60)time1 from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ "  where 1=1";
		sql += tj;
		sql += "group by vhic)t1,(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ "  where  1=1  ";
		sql += tj1;
		sql += " )v1 where '浙'||t1.vhic =v1.vehi_no )group by comp_name )p,"
				+ " (select comp_name,max(distinct ba_name)ba_name from"
				+ " HZGPS_TAXI.TB_COMPANY@TAXILINK t ,HZGPS_TAXI.TB_BUSI_AREA@TAXILINK v"
				+ "  where v.ba_id =t.ba_id group by comp_name )s,(select"
				+ " count(vehi_no)total,comp_name from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ "  group by comp_name)f where p.comp_name =s.comp_name"
				+ " and f.comp_name=s.comp_name order by total desc)q where"
				+ " rownum<=" + (page * pageSize) + " )where rn> "
				+ ((page - 1) * pageSize);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(pages);
		List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(sql);
		int count = list.size();
		List al = new ArrayList();
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < list1.size(); i++) {
			Map map = new HashMap();
			float total = (Float.parseFloat(list1.get(i).get("distance") + "") + Float
					.parseFloat(list1.get(i).get("empty1") + "")) / 10;
			float per = Float.parseFloat(list1.get(i).get("distance") + "")
					/ total * 10;
			float to = Float.parseFloat(list1.get(i).get("total") + "");
			float driving = Float.parseFloat(list1.get(i).get("qt") + "");
			String percent = df.format((driving / to * 100)) + "%";
			map.put("CCL", percent);
			map.put("DRIVING", list1.get(i).get("qt"));
			map.put("TOTAL", list1.get(i).get("total"));
			map.put("COMP", list1.get(i).get("comp_name"));
			map.put("MUNBER", list1.get(i).get("rn"));
			map.put("MONEY",
					Float.parseFloat(list1.get(i).get("st") + "") / 100);
			map.put("TIMES", list1.get(i).get("ct"));
			map.put("TOTALDIS", total);
			map.put("YPERCENT", df.format(per));
			map.put("DISTANCE",
					df.format(Float.parseFloat(list1.get(i).get("distance")
							+ "") / 10));
			map.put("TIMEOUT", df.format(Float.parseFloat(list1.get(i).get(
					"time1")
					+ "") / 3600 * 1.0));
			map.put("EMPTY", df.format(Float.parseFloat(list1.get(i).get(
					"empty1")
					+ "") / 10));
			map.put("WAITTIME",
					df.format(Float.parseFloat(list1.get(i).get("waittime")
							+ "") / 3600 * 1.0));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("count", count);
		resultMap.put("datas", m);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// try {
		//
		// qy_stime = sdf.format(new Date(sdf.parse(qy_etime).getTime() - 30
		// * 24 * 60 * 60 * 1000l));
		// } catch (ParseException e) {
		//
		// e.printStackTrace();
		// }
		// resultMap.put("days", find302(qy_stime, qy_etime, qy_comp));
		return jacksonUtil.toJson(resultMap);
	}

	// 302
	public int[] find302(String qy_stime, String qy_etime, String qy_comp) {
		// Map<String, Object> paramMap = jacksonUtil.toObject(postData,
		// new TypeReference<Map<String, Object>>() {
		// });

		// String qy_stime = String.valueOf(paramMap.get("qy_stime"));
		// String qy_etime = String.valueOf(paramMap.get("qy_etime"));
		// String qy_comp = String.valueOf(paramMap.get("qy_comp"));
		String tj = "";
		String tj1 = "";
		if (!StringUtils.isEmpty(qy_comp)
				&& !qy_comp.toLowerCase().equals("null")) {
			tj1 += " and yingyun = '" + qy_comp + "'";
		}
		if (!StringUtils.isEmpty(qy_stime)
				&& !qy_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(qy_etime)
				&& !qy_etime.toLowerCase().equals("null")) {
			tj += " and  shangche >to_date('" + qy_stime
					+ "','yyyy-MM-dd hh24:mi:ss')" + " and  shangche<to_date('"
					+ qy_etime + "','yyyy-MM-dd hh24:mi:ss')";
		}
		String time = qy_stime.substring(0,4);
		String sql = "select sum(u_to_number(jine))/100 sum,count(distinct(yingyun)) vhic,to_char(shangche, 'dd') time from HZGPS_CITIZEN.TB_CITIZEN_"+time+"@TAXILINK45 t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v "
				+ "where t.sim=v.vehi_sim ";
		sql += tj1;
		sql += tj;
		sql += " group by to_char(shangche, 'dd')";
		// System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		int[] count = new int[32];
		for (int i = 0; i < list.size(); i++) {
			count[Integer.parseInt(list.get(i).get("TIME") + "")] = (int) (Double
					.parseDouble(list.get(i).get("SUM") + "") / Integer
					.parseInt(list.get(i).get("VHIC") + ""));
		}
		return count;
	}

	// 出租企业营收信息统计分析2
	public String findAll(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String time = String.valueOf(paramMap.get("time"));
		// String qy_stime = String.valueOf(paramMap.get("qy_stime"));
		// String qy_etime = String.valueOf(paramMap.get("qy_etime"));
		String qy_comp = String.valueOf(paramMap.get("qy_comp"));
		String qy_stime = time + "-01 00:00:00";
		String qy_etime = time + "-"
				+ getDay(time.substring(0, 4), time.substring(5, 7))
				+ " 23:59:59";
		String tj = "";
		String tj1 = "";
		if (!StringUtils.isEmpty(qy_comp)
				&& !qy_comp.toLowerCase().equals("null")) {
			tj1 += " and vehi_no = '" + qy_comp + "'";
		}
		if (!StringUtils.isEmpty(qy_stime)
				&& !qy_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(qy_etime)
				&& !qy_etime.toLowerCase().equals("null")) {
			tj += " and  shangche >to_date('" + qy_stime
					+ "','yyyy-MM-dd hh24:mi:ss')" + " and  shangche<to_date('"
					+ qy_etime + "','yyyy-MM-dd hh24:mi:ss')";
		}
		// 查询条数
		String sj = qy_stime.substring(0, 4);
		String pages = "select  count(comp_name) from (select distinct vhic"
				+ " from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ " where 1=1 ";
		pages += tj;
		pages += ")t1,(select *from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ " where 1=1";
		pages += tj1;
		pages += ")v1 where  '浙'||t1.vhic=v1.vehi_no group by comp_name";

		// 查询记录
		String sql = "select * from (select q.*,rownum rn from(select p.*,"
				+ "s.ba_name ,f.total from(select sum(st) st,sum(ct) ct,count(vhic)qt,"
				+ "sum(waitTime) waitTime,sum(distance) distance,sum(empty1)empty1,"
				+ " sum(time1)time1,comp_name from( select t1.*,v1.* from ("
				+ "select vhic,sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jine, '$%', ' '),' ',''),0),0))) st"
				+ ",count(vhic) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime,"
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance,"
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1,"
				+ " sum((xiache-shangche)*60*24*60)time1 from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ "  where 1=1";
		sql += tj;
		sql += "group by vhic)t1,(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ "  where  1=1  ";
		sql += tj1;
		sql += " )v1 where '浙'||t1.vhic =v1.vehi_no )group by comp_name )p,"
				+ " (select comp_name,max(distinct ba_name)ba_name from"
				+ " HZGPS_TAXI.TB_COMPANY@TAXILINK t ,HZGPS_TAXI.TB_BUSI_AREA@TAXILINK v"
				+ "  where v.ba_id =t.ba_id group by comp_name )s,(select"
				+ " count(vehi_no)total,comp_name from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ "  group by comp_name)f where p.comp_name =s.comp_name"
				+ " and f.comp_name=s.comp_name order by total desc)q where"
				+ " rownum<=" + (page * pageSize) + " )where rn> "
				+ ((page - 1) * pageSize);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(pages);
		List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(sql);
		int count = list.size();
		List al = new ArrayList();
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < list1.size(); i++) {
			Map map = new HashMap();
			float total = (Float.parseFloat(list1.get(i).get("distance") + "") + Float
					.parseFloat(list1.get(i).get("empty1") + "")) / 10;
			float per = Float.parseFloat(list1.get(i).get("distance") + "")
					/ total * 10;
			float to = Float.parseFloat(list1.get(i).get("total") + "");
			float driving = Float.parseFloat(list1.get(i).get("qt") + "");
			String percent = df.format((driving / to * 100)) + "%";
			map.put("CCL", percent);
			map.put("DRIVING", list1.get(i).get("qt"));
			map.put("TOTAL", list1.get(i).get("total"));
			map.put("COMP", list1.get(i).get("comp_name"));
			map.put("MUNBER", list1.get(i).get("rn"));
			map.put("MONEY",
					Float.parseFloat(list1.get(i).get("st") + "") / 100);
			map.put("TIMES", list1.get(i).get("ct"));
			map.put("TOTALDIS", total);
			map.put("YPERCENT", df.format(per));
			map.put("DISTANCE",
					df.format(Float.parseFloat(list1.get(i).get("distance")
							+ "") / 10));
			map.put("TIMEOUT", df.format(Float.parseFloat(list1.get(i).get(
					"time1")
					+ "") / 3600 * 1.0));
			map.put("EMPTY", df.format(Float.parseFloat(list1.get(i).get(
					"empty1")
					+ "") / 10));
			map.put("WAITTIME",
					df.format(Float.parseFloat(list1.get(i).get("waittime")
							+ "") / 3600 * 1.0));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("count", count);
		resultMap.put("datas", m);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// try {
		//
		// qy_stime = sdf.format(new Date(sdf.parse(qy_etime).getTime() - 30
		// * 24 * 60 * 60 * 1000l));
		// } catch (ParseException e) {
		//
		// e.printStackTrace();
		// }

		// resultMap.put("days", find303(qy_stime, qy_etime, qy_comp));
		return jacksonUtil.toJson(resultMap);
	}

	// 303
	public int[] find303(String qy_stime, String qy_etime, String qy_comp) {
		// Map<String, Object> paramMap = jacksonUtil.toObject(postData,
		// new TypeReference<Map<String, Object>>() {
		// });

		// String qy_stime = String.valueOf(paramMap.get("qy_stime"));
		// String qy_etime = String.valueOf(paramMap.get("qy_etime"));
		// String qy_comp = String.valueOf(paramMap.get("qy_comp"));
		String tj = "";
		String tj1 = "";
		if (!StringUtils.isEmpty(qy_comp)
				&& !qy_comp.toLowerCase().equals("null")) {
			tj1 += " and vehi_no = '" + qy_comp + "'";
		}
		if (!StringUtils.isEmpty(qy_stime)
				&& !qy_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(qy_etime)
				&& !qy_etime.toLowerCase().equals("null")) {
			tj += " and  shangche >to_date('" + qy_stime
					+ "','yyyy-MM-dd hh24:mi:ss')" + " and  shangche<to_date('"
					+ qy_etime + "','yyyy-MM-dd hh24:mi:ss')";
		}
		String sj = qy_stime.substring(0, 4);
		String sql = "select sum(u_to_number(jine))/100 sum,count(distinct(vehi_no)) vhic,to_char(shangche, 'dd') time from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v "
				+ "where t.sim=v.vehi_sim ";
		sql += tj1;
		sql += tj;
		sql += " group by to_char(shangche, 'dd')";
		// System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		int[] count = new int[32];
		for (int i = 0; i < list.size(); i++) {
			count[Integer.parseInt(list.get(i).get("TIME") + "")] = (int) (Double
					.parseDouble(list.get(i).get("SUM") + "") / Integer
					.parseInt(list.get(i).get("VHIC") + ""));
		}
		return count;
	}

	// 司机营收数据统计分析
	public String findsj(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String sj_stime = String.valueOf(paramMap.get("sj_stime"));
		String sj_etime = String.valueOf(paramMap.get("sj_etime"));
		String sj_comp = String.valueOf(paramMap.get("sj_comp"));
		String sj_zgz = String.valueOf(paramMap.get("sj_zgz"));
		String tj = "";
		String tj1 = "";
		if (!StringUtils.isEmpty(sj_comp)
				&& !sj_comp.toLowerCase().equals("null")) {
			tj1 += " and comp_id = '" + sj_comp + "'";
		}
		if (!StringUtils.isEmpty(sj_stime)
				&& !sj_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(sj_etime)
				&& !sj_etime.toLowerCase().equals("null")) {
			tj += " and  shangche >to_date('" + sj_stime
					+ "','yyyy-MM-dd hh24:mi:ss')" + " and  shangche<to_date('"
					+ sj_etime + "','yyyy-MM-dd hh24:mi:ss')";
		}
		if (!StringUtils.isEmpty(sj_zgz)
				&& !sj_zgz.toLowerCase().equals("null")) {
			tj += " and yingyun = '" + sj_zgz + "'";
		}
		// 查询条数
		String sj = sj_stime.substring(0, 4);
		String pages = "select count(yingyun)from (select yingyun,"
				+ " max(distinct vhic) vhic from HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ "  where 1=1 ";
		pages += tj;
		pages += " group by yingyun)t1, (select * from"
				+ " HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where 1=1 ";
		pages += tj1;
		pages += " )v1 where '浙'||t1.vhic=v1.vehi_no group by yingyun";

		// 查询记录
		String sql = "select * from(select p.*,rownum rn from(select t1.*,v1.*"
				+ " from (select yingyun, "
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jine, '$%', ' '),' ',''),0),0))) st,"
				+ "count(yingyun) ct,sum(substr(denghou,-2,2)+substr(denghou,-4,2)*60+substr(denghou,-6,2)*3600) waitTime, "
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance, "
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1,"
				+ "sum((xiache-shangche)*60*24*60)time1 from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t"
				+ " where 1=1";
		sql += tj;
		sql += " group by yingyun)t1, (select max(vhic) vhic,yingyun yy"
				+ " from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t where 1=1";
		sql += tj;
		sql += " group by yingyun)p,(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ "  where  1=1";
		sql += tj1;
		sql += " )v1 where '浙'||p.vhic =v1.vehi_no and p.yy = t1.yingyun"
				+ " order by ba_name asc,comp_name asc,yingyun asc )p where"
				+ " rownum<=" + (page * pageSize) + " )where rn> "
				+ ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(pages);
		List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(sql);
		int count = list.size();
		List al = new ArrayList();
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < list1.size(); i++) {
			Map map = new HashMap();
			float emp = Float.parseFloat(list1.get(i).get("empty1") + "") / 10;
			float dis = Float.parseFloat(list1.get(i).get("distance") + "") / 10;
			String total = df.format(emp + dis);
			String percent = df.format(dis / (emp + dis) * 100) + "%";
			map.put("YINGYUN", list1.get(i).get("yingyun"));
			map.put("TOTAL", total);
			map.put("PERCENT", percent);
			map.put("COMP", list1.get(i).get("comp_name"));
			map.put("CERTNO", list1.get(i).get("yingyun"));
			map.put("MONEY",
					Float.parseFloat(list1.get(i).get("st") + "") / 100);
			map.put("TIMES", list1.get(i).get("ct"));
			map.put("DISTANCE",
					df.format(Float.parseFloat(list1.get(i).get("distance")
							+ "") / 10));
			map.put("TIMEOUT", df.format(Float.parseFloat(list1.get(i).get(
					"time1")
					+ "") / 3600 * 1.0));
			map.put("EMPTY", df.format(Float.parseFloat(list1.get(i).get(
					"empty1")
					+ "") / 10));
			map.put("WAITTIME",
					df.format(Float.parseFloat(list1.get(i).get("waittime")
							+ "") / 3600 * 1.0));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("count", count);
		resultMap.put("datas", m);
		return jacksonUtil.toJson(resultMap);
	}

	// 车辆营收数据统计分析
	public String findvhicxx(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String cl_stime = String.valueOf(paramMap.get("cl_stime"));
		String cl_etime = String.valueOf(paramMap.get("cl_etime"));
		String cl_comp = String.valueOf(paramMap.get("cl_comp"));
		String cl_vhic = String.valueOf(paramMap.get("cl_vhic"));
		String tj = "";
		String tj1 = "";
		if (!StringUtils.isEmpty(cl_comp)
				&& !cl_comp.toLowerCase().equals("null")) {
			tj1 += " and comp_id = '" + cl_comp + "'";
		}
		if (!StringUtils.isEmpty(cl_vhic)
				&& !cl_vhic.toLowerCase().equals("null")) {
			tj1 += " and vehi_no = '" + cl_vhic + "'";
		}
		if (!StringUtils.isEmpty(cl_stime)
				&& !cl_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(cl_etime)
				&& !cl_etime.toLowerCase().equals("null")) {
			tj += " and  shangche >to_date('" + cl_stime
					+ "','yyyy-MM-dd hh24:mi:ss')" + " and  shangche<to_date('"
					+ cl_etime + "','yyyy-MM-dd hh24:mi:ss')";
		}
		String sj = cl_stime.substring(0, 4);
		String pages = "select * from (select distinct(vhic)from "
				+ " HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t" + " where 1=1 ";
		pages += tj;
		pages += " )t1,(select *from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ " where 1=1 ";
		pages += tj1;
		pages += " )v1 where  '浙'||t1.vhic=v1.vehi_no";
		String sql = "select * from(select p.*,rownum rn from(select t1.*,v1.* "
				+ "from (select vhic, "
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jine, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jine, '$%', ' '),' ',''),0),0))) st,"
				+ "count(vhic) ct,sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(substr(denghou,-2,2), '$%', ' '),' ',''),0)),upper(nvl(replace(translate(substr(denghou,-2,2), '$%', ' '),' ',''),0)),1,0),1,"
				+ "nvl(replace(translate(substr(denghou,-2,2), '$%', ' '),' ',''),0),0))+TO_NUMBER(decode(decode(lower(nvl(replace(translate(substr(denghou,-4,2), '$%', ' '),' ',''),0)),"
				+ "upper(nvl(replace(translate(substr(denghou,-4,2), '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(substr(denghou,-4,2), '$%', ' '),' ',''),0),0))*60+ "
				+ "TO_NUMBER(decode(decode(lower(nvl(replace(translate(substr(denghou,-6,2), '$%', ' '),' ',''),0)),upper(nvl(replace(translate(substr(denghou,-6,2), '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(substr(denghou,-6,2), '$%', ' '),' ',''),0),0))*3600) waitTime,"
				+ " sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(jicheng, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(jicheng, '$%', ' '),' ',''),0),0))) distance, "
				+ "sum(TO_NUMBER(decode(decode(lower(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),upper(nvl(replace(translate(kongshi, '$%', ' '),' ',''),0)),1,0),1,nvl(replace(translate(kongshi, '$%', ' '),' ',''),0),0))) empty1, "
				+ "sum((xiache-shangche)*60*24*60)time1 from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45 t  "
				+ "where 1=1";
		sql += tj;
		sql += " group by vhic)t1,(select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
				+ "  where  1=1  ";
		sql += tj1;
		sql += " )v1 where '浙'||t1.vhic =v1.vehi_no order by ba_name asc,comp_name asc,vehi_no asc )p"
				+ " where rownum<="
				+ (page * pageSize)
				+ " )where rn> "
				+ ((page - 1) * pageSize);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(pages);
		List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(sql);
		int count = list.size();
		List al = new ArrayList();
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < list1.size(); i++) {
			Map map = new HashMap();
			float emp = Float.parseFloat(list1.get(i).get("empty1") + "") / 10;
			float dis = Float.parseFloat(list1.get(i).get("distance") + "") / 10;
			String total = df.format(emp + dis);
			String percent = df.format(dis / (emp + dis) * 100) + "%";
			map.put("TOTAL", total);
			map.put("PERCENT", percent);
			map.put("COMP", list1.get(i).get("comp_name"));
			map.put("VHIC", list1.get(i).get("vehi_no"));
			map.put("MONEY",
					Float.parseFloat(list1.get(i).get("st") + "") / 100);
			map.put("TIMES", list1.get(i).get("ct"));
			map.put("DISTANCE",
					df.format(Float.parseFloat(list1.get(i).get("distance")
							+ "") / 10));
			map.put("TIMEOUT", df.format(Float.parseFloat(list1.get(i).get(
					"time1")
					+ "") / 3600 * 1.0));
			map.put("EMPTY", df.format(Float.parseFloat(list1.get(i).get(
					"empty1")
					+ "") / 10));
			map.put("WAITTIME",
					df.format(Float.parseFloat(list1.get(i).get("waittime")
							+ "") / 3600 * 1.0));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("count", count);
		resultMap.put("datas", m);
		return jacksonUtil.toJson(resultMap);
	}

	// 查询公司信息
	public String findCompxx(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String comp = String.valueOf(paramMap.get("compxx_comp")).trim();
		String tj = "";
		if (!StringUtils.isEmpty(comp) && !comp.toLowerCase().equals("null") && !comp.equals("0")) {
			tj += " and name like  '%" + comp + "%'";
		}
		String sql = "select (select count(*) from (select * from V_TW_TAXI_COMPANY@TAXILINK_SJZX t where  business_scope_code like '%1400%' and vehicle_sum is not null ";
		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from  "
				+ "(select * from V_TW_TAXI_COMPANY@TAXILINK_SJZX t where  business_scope_code like '%1400%' and vehicle_sum is not null ";
		sql += tj;
		sql += ") t where rownum<=" + (page * pageSize) + " ) tt where tt.rn> "
				+ ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 营运日报
	public String findOperDay(String time) {
		String sql = "select * from TB_TAXI_RUN_INFO_RECORD_TEST where"
				+ " db_time>=to_date('" + time
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') and "
				+ " db_time<=to_date('" + time
				+ " 23:59:59','yyyy-mm-dd hh24:mi:ss') order by db_time desc";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		int c = jdbcTemplate2.queryForObject("select count(1) from HZGPS_TAXI.VW_VEHICLE@TAXILINK", Integer.class); 
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("JDDSB",
					findoperdayyy(list.get(i).get("DB_TIME") + ""));
			list.get(i).put("JDDBD", c);
		}
		return jacksonUtil.toJson(list);
	}

	/**
	 * 营运日报中使用 查询每小时参与营运的车辆数 传入时间
	 */
	public int findoperdayyy(String time) {
		int count = 0;
		String sj = time.substring(0, 4);
		String sql = "select count(distinct (vhic)) c from HZGPS_CITIZEN.TB_CITIZEN_"
				+ sj
				+ "@TAXILINK45 t"
				+ " where shangche>=to_date('"
				+ time.substring(0, 13)
				+ ":00:00','yyyy-MM-dd hh24:mi:ss')"
				+ " and  shangche<to_date('"
				+ time.substring(0, 13)
				+ ":59:59','yyyy-MM-dd hh24:mi:ss')";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			count = Integer.parseInt(list.get(i).get("c") + "");
		}
		return count;
	}

	// 营运月报
	public String findOperMonth(String time) {
		time = time.substring(0, 4) + time.substring(5, 7);
		String sql = "select * from TB_TAXI_MONTHLY_REPORT t where report_time like '%"
				+ time + "%' order by report_time desc";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return jacksonUtil.toJson(list);
	}

	// 营运年报(月)
	public String findOperYear(String time) {
		String sql = "select * from TB_TAXI_ANNUAL_REPORT t where report_time like '%"
				+ time + "%'  order by report_time desc";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		List al = new ArrayList();
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < list.size(); i++) {
			Map map = new HashMap();
			map.put("REPORT_TIME", list.get(i).get("REPORT_TIME"));// 时间
			int b = Integer.parseInt((list.get(i).get("REPORT_TIME") + "")
					.substring(0, 4));
			int c = Integer.parseInt((list.get(i).get("REPORT_TIME") + "")
					.substring(4, 6));
			String ri = "";
			if ((c + "").length() < 2) {
				ri = "0" + c;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = new Date();
			String yue = sdf.format(d).substring(5, 7);
			int a = 0;
			if (yue.equals(ri)) {
				a = Integer.parseInt(sdf.format(d).substring(8, 10));
			} else {
				a = new Date(b, c, 0).getDate();
			}
			int pjcls = Integer.parseInt(list.get(i).get("repore_vhic") + "")
					/ a;
			map.put("PEPORE_ACTUAL_LOADING",
					list.get(i).get("repore_actual_loading"));// 实载率
			map.put("REPORE_ACTUAL_MILEAGE",
					list.get(i).get("repore_actual_mileage"));// 实载里程
			map.put("MONTHREPORE_ACTUAL_MILEAGE",
					df.format(Double.parseDouble(list.get(i).get(
							"repore_actual_mileage")
							+ "")
							/ pjcls));// 平均月实载里程
			map.put("DAYREPORE_ACTUAL_MILEAGE",
					df.format(Double.parseDouble(list.get(i).get(
							"repore_actual_mileage")
							+ "")
							/ pjcls / a));// 平均日实载里程
			map.put("REPORE_AMOUNT_REVENUE",
					list.get(i).get("repore_amount_revenue"));// 营运金额
			map.put("MONTHREPORE_AMOUNT_REVENUE",
					df.format(Double.parseDouble(list.get(i).get(
							"repore_amount_revenue")
							+ "")
							/ pjcls));// 平均月营运金额
			map.put("DAYREPORE_AMOUNT_REVENUE",
					df.format(Double.parseDouble(list.get(i).get(
							"repore_amount_revenue")
							+ "")
							/ pjcls / a));// 平均日营运金额
			map.put("REPORE_EMPTY_MILEAGE",
					list.get(i).get("repore_empty_mileage"));// 空载里程
			map.put("MONTHREPORE_EMPTY_MILEAGE",
					df.format(Double.parseDouble(list.get(i).get(
							"repore_empty_mileage")
							+ "")
							/ pjcls));// 平均月空载里程
			map.put("DAYREPORE_EMPTY_MILEAGE",
					df.format(Double.parseDouble(list.get(i).get(
							"repore_empty_mileage")
							+ "")
							/ pjcls / a));// 平均日空载里程
			map.put("REPORE_MILEAGE", list.get(i).get("repore_mileage"));// 总里程
			map.put("MONTHREPORE_MILEAGE",
					df.format(Double.parseDouble(list.get(i).get(
							"repore_mileage")
							+ "")
							/ pjcls));// 平均月总里程
			map.put("DAYREPORE_MILEAGE",
					df.format(Double.parseDouble(list.get(i).get(
							"repore_mileage")
							+ "")
							/ pjcls / a));// 平均日总里程
			map.put("REPORE_NO", list.get(i).get("repore_no"));// 营运单数
			map.put("MONTHREPORE_NO",
					Integer.parseInt(list.get(i).get("repore_no") + "")
							/ (Integer.parseInt(list.get(i).get("repore_vhic")
									+ "") / a));// 每月平均单数
			map.put("DAYREPORE_NO",
					Integer.parseInt(list.get(i).get("repore_no") + "")
							/ (Integer.parseInt(list.get(i).get("repore_vhic")
									+ "")
									/ a / a));// 每日平均单数
			map.put("REPORE_RADE", list.get(i).get("repore_rade"));// 上路率
			map.put("REPORE_TURNOVER", list.get(i).get("repore_turnover"));// 周转次数
			map.put("PJREPORE_TURNOVER",
					df.format(Double.parseDouble(list.get(i).get(
							"repore_turnover")
							+ "")
							/ a));// 平均周转次数
			map.put("REPORE_VHIC", list.get(i).get("repore_vhic"));// 参与营运车辆数
			map.put("PJREPORE_VHIC",
					Integer.parseInt(list.get(i).get("repore_vhic") + "") / a);// 平均参与营运车辆数
			map.put("REPORE_CAR_TIME", list.get(i).get("repore_car_time"));// 出车时间
			map.put("PJREPORE_CAR_TIME",
					Integer.parseInt(list.get(i).get("repore_car_time") + "")
							/ (Integer.parseInt(list.get(i).get("repore_vhic")
									+ "") / a));// 平均出车时间
			map.put("PRPORE_VHICNO", list.get(i).get("repore_vhicno"));
			map.put("JDDSB", 9612);
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);

		return jacksonUtil.toJson(m);
	}

	// 查询车辆信息
	public String findvehinfo(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String comp = String.valueOf(paramMap.get("gs_id")).trim();
		String vehi = String.valueOf(paramMap.get("vehi_no")).trim();
		String tj = "";
		if (!StringUtils.isEmpty(comp) && !comp.toLowerCase().equals("null")&&!comp.equals("0")) {
			tj += " and name like '%" + comp + "%'";
		}
		if (!StringUtils.isEmpty(vehi) && !vehi.toLowerCase().equals("null")) {
			tj += " and plate_number like '%" + vehi + "%'";
		}
		String sql = "select (select count(*)from (select * from V_TW_TAXI_VEHICLE@TAXILINK_SJZX t where 1=1";
		sql += tj;
		sql += ")) as count,tt.* from (select t.*, rownum as rn from ("
				+ "select * from V_TW_TAXI_VEHICLE@TAXILINK_SJZX t where 1=1 ";
		sql += tj;
		sql += ") t where rownum<=" + (page * pageSize) + " ) tt where tt.rn> "
				+ ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 查询人员信息
	public String findrenyuan(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String name = String.valueOf(paramMap.get("name")).trim();
		String id_number = String.valueOf(paramMap.get("id_number")).trim();
		String tj = "";
		if (!StringUtils.isEmpty(name) && !name.toLowerCase().equals("null")&&!name.equals("0")) {
			tj += " and name like '%" + name + "%'";
		}
		if (!StringUtils.isEmpty(id_number) && !id_number.toLowerCase().equals("null")) {
			tj += " and id_number like  '%" + id_number + "%'";
		}
		String sql = "select (select count(*)from (select NAME,SEX,ID_NUMBER,MOBILE_PHONE,CURRENT_ADDRESS from  V_TW_TAXI_PERSON@TAXILINK_SJZX where 1=1 ";
		sql += tj;
		sql += ")) as count,tt.* from (select t.*, rownum as rn from ("
				+ "select NAME,CASE SEX WHEN 1 THEN '男' ELSE '女' END AS SEX ,ID_NUMBER,MOBILE_PHONE,CURRENT_ADDRESS from  V_TW_TAXI_PERSON@TAXILINK_SJZX where 1=1 ";
		sql += tj;
		sql += ") t where rownum<=" + (page * pageSize) + " ) tt where tt.rn> "
				+ ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	public String findmingxi(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String quyu = String.valueOf(paramMap.get("quyu"));
		String time = String.valueOf(paramMap.get("time"));
		String speed = String.valueOf(paramMap.get("speed"));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String[] s = null;
		Map maps = new HashMap();
		if (quyu == null || quyu.equals("0") || quyu.equals("null")
				|| quyu.length() == 0) {
			String sql = "select * from TB_AREA_VEHINO t where time=to_date('"
					+ time + "','yyyy-mm-dd')";
			System.out.println(sql);
			List<Map<String, Object>> list2 = jdbcTemplate2.queryForList(sql);
			if (list2.size() > 0) {
				s = list2.get(0).get("no").toString().split(",");
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < 48; i++) {
					map.put("y" + i, s[i]);
				}
				list.add(map);
			}
			maps.put("datas", list);
		} else {
			String stime = time + " 00:00:00";
			String etime = time + " 23:59:59";
			String areasql = "";
			String riq = time.substring(0, 4) + time.substring(5, 7);
			String sql = "select * from TB_TAXI_AREA_INFO_RECORD" + riq
					+ " t,HZGPS_TAXI.VW_VEHICLE@TAXILINK v"
					+ "  where t.vehi_no=v.vehi_no and record_time>="
					+ "to_date('" + stime + "','yyyy-MM-dd HH24:mi:ss') and"
					+ " record_time<=to_date('" + etime
					+ "','yyyy-MM-dd HH24:mi:ss') and  area_id ='" + quyu
					+ "' ";
			if (speed != null && speed.length() > 0 && !speed.equals("null")) {
				sql += " and speed >" + speed;
			}

			System.out.println(sql);
			try {

				String tt[] = { "00:00", "00:30", "01:00", "01:30", "02:00",
						"02:30", "03:00", "03:30", "04:00", "04:30", "05:00",
						"05:30", "06:00", "06:30", "07:00", "07:30", "08:00",
						"08:30", "09:00", "09:30", "10:00", "10:30", "11:00",
						"11:30", "12:00", "12:30", "13:00", "13:30", "14:00",
						"14:30", "15:00", "15:30", "16:00", "16:30", "17:00",
						"17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
						"20:30", "21:00", "21:30", "22:00", "22:30", "23:00",
						"23:30", };
				int[] aa = new int[48];
				List<Map<String, Object>> list2 = jdbcTemplate2
						.queryForList(sql);
				for (int m = 0; m < list2.size(); m++) {
					for (int i = 0; i < tt.length; i++) {
						if (list2.get(m).get("record_time").toString()
								.substring(11, 16).equals(tt[i])) {
							aa[i]++;
						}
					}
				}
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < aa.length; i++) {
					map.put("y" + i, aa[i]);
				}
				list.add(map);

				maps.put("datas", list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jacksonUtil.toJson(maps);
	}

	// 查询车辆具体信息
	public String findspecinfo(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String time = String.valueOf(paramMap.get("time"));
		String i = String.valueOf(paramMap.get("i"));
		String area_id = String.valueOf(paramMap.get("quyu"));
		String speed = String.valueOf(paramMap.get("speed"));
		String stime = null;
		String etime = null;

		if (i.equals("0")) {

			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Date d = new Date();
			String a = dft.format(d);
			try {
				Date b = dft.parse(a);
				Calendar date = Calendar.getInstance();
				date.setTime(b);
				date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
				Date endDate;
				String zuotian = null;
				endDate = dft.parse(dft.format(date.getTime()));
				zuotian = dft.format(endDate);
				stime = zuotian + " 23:58:00";
				etime = time + " 00:02:00";
			} catch (Exception e) {
			}
		} else if (i.equals("1")) {
			stime = time + " 00:28:00";
			etime = time + " 00:32:00";
		} else if (i.equals("2")) {
			stime = time + " 00:58:00";
			etime = time + " 01:02:00";
		} else if (i.equals("3")) {
			stime = time + " 01:28:00";
			etime = time + " 01:32:00";
		} else if (i.equals("4")) {
			stime = time + " 01:58:00";
			etime = time + " 02:02:00";
		} else if (i.equals("5")) {
			stime = time + " 02:28:00";
			etime = time + " 02:32:00";
		} else if (i.equals("6")) {
			stime = time + " 02:58:00";
			etime = time + " 03:02:00";
		} else if (i.equals("7")) {
			stime = time + " 03:28:00";
			etime = time + " 03:32:00";
		} else if (i.equals("8")) {
			stime = time + " 03:58:00";
			etime = time + " 04:02:00";
		} else if (i.equals("9")) {
			stime = time + " 04:28:00";
			etime = time + " 04:32:00";
		} else if (i.equals("10")) {
			stime = time + " 04:58:00";
			etime = time + " 05:02:00";
		} else if (i.equals("11")) {
			stime = time + " 05:28:00";
			etime = time + " 05:32:00";
		} else if (i.equals("12")) {
			stime = time + " 05:58:00";
			etime = time + " 06:02:00";
		} else if (i.equals("13")) {
			stime = time + " 06:28:00";
			etime = time + " 06:32:00";
		} else if (i.equals("14")) {
			stime = time + " 06:58:00";
			etime = time + " 07:02:00";
		} else if (i.equals("15")) {
			stime = time + " 07:28:00";
			etime = time + " 07:32:00";
		} else if (i.equals("16")) {
			stime = time + " 07:58:00";
			etime = time + " 08:02:00";
		} else if (i.equals("17")) {
			stime = time + " 08:28:00";
			etime = time + " 08:32:00";
		} else if (i.equals("18")) {
			stime = time + " 08:58:00";
			etime = time + " 09:02:00";
		} else if (i.equals("19")) {
			stime = time + " 09:28:00";
			etime = time + " 09:32:00";
		} else if (i.equals("20")) {
			stime = time + " 09:58:00";
			etime = time + " 10:02:00";
		} else if (i.equals("21")) {
			stime = time + " 10:28:00";
			etime = time + " 10:32:00";
		} else if (i.equals("22")) {
			stime = time + " 10:58:00";
			etime = time + " 11:02:00";
		} else if (i.equals("23")) {
			stime = time + " 11:28:00";
			etime = time + " 11:32:00";
		} else if (i.equals("24")) {
			stime = time + " 11:58:00";
			etime = time + " 12:02:00";
		} else if (i.equals("25")) {
			stime = time + " 12:28:00";
			etime = time + " 12:32:00";
		} else if (i.equals("26")) {
			stime = time + " 12:58:00";
			etime = time + " 13:02:00";
		} else if (i.equals("27")) {
			stime = time + " 13:28:00";
			etime = time + " 13:32:00";
		} else if (i.equals("28")) {
			stime = time + " 13:58:00";
			etime = time + " 14:02:00";
		} else if (i.equals("29")) {
			stime = time + " 14:28:00";
			etime = time + " 14:32:00";
		} else if (i.equals("30")) {
			stime = time + " 14:58:00";
			etime = time + " 15:02:00";
		} else if (i.equals("31")) {
			stime = time + " 15:28:00";
			etime = time + " 15:32:00";
		} else if (i.equals("32")) {
			stime = time + " 15:58:00";
			etime = time + " 16:02:00";
		} else if (i.equals("33")) {
			stime = time + " 16:28:00";
			etime = time + " 16:32:00";
		} else if (i.equals("34")) {
			stime = time + " 16:58:00";
			etime = time + " 17:02:00";
		} else if (i.equals("35")) {
			stime = time + " 17:28:00";
			etime = time + " 17:32:00";
		} else if (i.equals("36")) {
			stime = time + " 17:58:00";
			etime = time + " 18:02:00";
		} else if (i.equals("37")) {
			stime = time + " 18:28:00";
			etime = time + " 18:32:00";
		} else if (i.equals("38")) {
			stime = time + " 18:58:00";
			etime = time + " 19:02:00";
		} else if (i.equals("39")) {
			stime = time + " 19:28:00";
			etime = time + " 19:32:00";
		} else if (i.equals("40")) {
			stime = time + " 19:58:00";
			etime = time + " 20:02:00";
		} else if (i.equals("41")) {
			stime = time + " 20:28:00";
			etime = time + " 20:32:00";
		} else if (i.equals("42")) {
			stime = time + " 20:58:00";
			etime = time + " 21:02:00";
		} else if (i.equals("43")) {
			stime = time + " 21:28:00";
			etime = time + " 21:32:00";
		} else if (i.equals("44")) {
			stime = time + " 21:58:00";
			etime = time + " 22:02:00";
		} else if (i.equals("45")) {
			stime = time + " 22:28:00";
			etime = time + " 22:32:00";
		} else if (i.equals("46")) {
			stime = time + " 22:58:00";
			etime = time + " 23:02:00";
		} else if (i.equals("47")) {
			stime = time + " 23:28:00";
			etime = time + " 23:32:00";
		}

		String riq = time.substring(0, 4) + time.substring(5, 7);
		String tj = "";

		if (!StringUtils.isEmpty(area_id)
				&& !area_id.toLowerCase().equals("null")) {
			tj += " and area_id='" + area_id + "'";
		} else {
			tj += " and area_id  in (select area_id from hz_taxi.tb_area )";

		}

		String sql = "select  tt.* from (select t.*, rownum as rn from (select ba_name,comp_name,v.vehi_no,vehi_sim,own_name,own_tel,area_name,speed "
				+ " from  hz_taxi.TB_TAXI_AREA_INFO_RECORD"
				+ riq
				+ " c ,HZGPS_TAXI.VW_VEHICLE@TAXILINK v  "
				+ " where c.vehi_no=v.vehi_no and record_time>=to_date('"
				+ stime
				+ "','yyyy-MM-dd HH24:mi:ss') "
				+ " and record_time<=to_date('"
				+ etime
				+ "','yyyy-MM-dd HH24:mi:ss') ";
		if (speed != null && speed.length() > 0 && !speed.equals("null")) {
			sql += " and speed >" + speed;
		}
		sql += tj;
		sql += ") t  " + ") tt  ";
		System.out.println(sql);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(sql);
		// int count = 0;
		// if (null != list && list.size() > 0) {
		// count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		// }
		Map resultMap = new HashMap();
		// resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 48个数据基类

	public Map<String, String> switchList(List<Map<String, Object>> list,
			String[] str) {
		Map<String, String> map = new HashMap<String, String>();
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < str.length; j++) {
				if(list.get(i).get("S") == str[j]){
					map.put("y" + j, String.valueOf(list.get(i).get("COUNT")));
				}
			}
		}
		for (int i = 0; i < 48; i++) {
			if (map.get("y" + i) == null) {
				map.put("y" + i, "");
			}
		}

		return map;
	}

	// 杭州出租保有量统计
	public String findbyl(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String comp_id = String.valueOf(paramMap.get("comp_id"));
		String sql = " select to_char(VEHI_DATE,'yyyy') VEHI_DATE,"
				+ "count(vehi_no) c from HZGPS_TAXI.TB_VEHICLE@TAXILINK"
				+ " where vehi_no like '%浙AT%' ";
		if(comp_id!=null&&comp_id.length()>0
				&&!comp_id.equals("null")&&!comp_id.equals("0")){
			sql+=" and comp_id = '"+comp_id +"'";
		}
		sql += "group by to_char(VEHI_DATE,'yyyy') order by VEHI_DATE";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		int a = 0;
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("C1", a);
			a += Integer.parseInt(list.get(i).get("C") + "");
		}
		return jacksonUtil.toJson(list);
	}

	// 车辆定位信息
	public String vehpos(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String comp = String.valueOf(paramMap.get("gs_id")).trim();
		String vehi = String.valueOf(paramMap.get("vehi_no")).trim();
		String tj = "";
		if (!StringUtils.isEmpty(comp) && !comp.toLowerCase().equals("null")) {
			tj += " and COMP_NAME like '%" + comp + "%'";
		}
		if (!StringUtils.isEmpty(vehi) && !vehi.toLowerCase().equals("null")) {
			tj += " and vehi_no like '%" + vehi + "%'";
		}
		String sql = " select (select count(*) from (select * from TB_TAXI_STATUS_NEW t,"
				+ " HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.mdt_no = v.sim_num ";
		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from"
				+ " (select t.speed,t.stime,t.status,t.heading,t.px,t.py,v.vehi_no,"
				+ "v.vehi_sim,v.own_name,v.own_tel,v.comp_name from TB_TAXI_STATUS_NEW t,"
				+ " HZGPS_TAXI.VW_VEHICLE@TAXILINK v where t.mdt_no = v.sim_num ";
		sql += tj;
		sql += ") t where rownum<=" + (page * pageSize) + " ) tt where tt.rn> "
				+ ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 营运信息查询
	public String findyyinfo(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String yyxx_stime = String.valueOf(paramMap.get("yyxx_stime"));
		String yyxx_etime = String.valueOf(paramMap.get("yyxx_etime"));
//		String yycomp = String.valueOf(paramMap.get("gs_id"));
		String vehi_no = String.valueOf(paramMap.get("vehi_no")).trim();
		String tj = "";
		String tj1 = "";
		if (!StringUtils.isEmpty(yyxx_stime)
				&& !yyxx_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(yyxx_etime)
				&& !yyxx_etime.toLowerCase().equals("null")) {
			tj += " and  shangche >to_date('" + yyxx_stime
					+ "','yyyy-MM-dd hh24:mi:ss')" + " and  shangche<to_date('"
					+ yyxx_etime + "','yyyy-MM-dd hh24:mi:ss')";
		}
//		if (!StringUtils.isEmpty(yycomp)
//				&& !yycomp.toLowerCase().equals("null")) {
//			tj1 += " and comp_id = '" + yycomp + "'";
//		}
		if (!StringUtils.isEmpty(vehi_no)
				&& !vehi_no.toLowerCase().equals("null")) {
			tj1 += " and vehi_no like '%" + vehi_no + "%'";
		}
		// 查询所有记录
		String time = yyxx_stime.substring(0, 4);
		String sql = "select * from(select p.* ,rownum rn from(select t1.*,v1.* "
				+ "from(select * from  HZGPS_CITIZEN.TB_CITIZEN_"+time+"@TAXILINK45 t"
				+ "  where 1=1 ";
		sql += tj;
		sql += ")t1, (select * from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1 ";
		sql += tj1;
		sql += ")v1 where '浙'||t1.vhic =v1.vehi_no )p where" + " rownum<="
				+ (page * pageSize) + " ) tt where tt.rn> "
				+ ((page - 1) * pageSize);
		System.out.println(sql);

		// 查询条数
		String sj = yyxx_stime.substring(0, 4);
		String pages = "select count(*) c from(select * from  HZGPS_CITIZEN.TB_CITIZEN_"+sj+"@TAXILINK45"
				+ " t  where 1=1 ";
		pages += tj;
		pages += " and vhic in (select ltrim(vehi_no, '浙') from HZGPS_TAXI.VW_VEHICLE@TAXILINK v  where  1=1 ";
		pages += tj1;
		pages += ")) ";
		System.out.println(pages);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(pages);
		int count = 0;
		for (int i = 0; i < list1.size(); i++) {
			count = Integer.parseInt(list1.get(i).get("C") + "");
		}
		List al = new ArrayList();
		String denghou = "";
		int dh = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < list.size(); i++) {
			Map map = new HashMap();
			denghou = list.get(i).get("DENGHOU") + "";
			if (denghou.length() > 0) {
				dh = Integer.parseInt(denghou.substring(0, 2)) * 3600
						+ Integer.parseInt(denghou.substring(2, 4)) * 60
						+ Integer.parseInt(denghou.substring(4, 6));
			} else {
				dh = 0;
			}
			map.put("VEHI_NO", list.get(i).get("vehi_no"));
			map.put("YINGYUN", list.get(i).get("yingyun"));
			map.put("SHANGCHE", list.get(i).get("shangche"));
			map.put("XIACHE", list.get(i).get("xiache"));
			map.put("JINE",
					Integer.parseInt(list.get(i).get("jine") + "") / 100);
			map.put("JICHENG",
					Integer.parseInt(list.get(i).get("JICHENG") + "") / 10);
			map.put("KONGSHI",
					Integer.parseInt(list.get(i).get("KONGSHI") + "") / 10);
			map.put("DENGHOU", dh);
			map.put("JIAOYITYPE", list.get(i).get("JIAOYITYPE"));
			al.add(map);
		}
		Map m = new HashMap();
		m.put("datas", al);
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		resultMap.put("count", count);
		resultMap.put("datas", m);
		return jacksonUtil.toJson(resultMap);
	}

	// 24个数据基类

	public Map<String, String> switchList24(List<Map<String, Object>> list,
			String[] str) {
		Map<String, String> map = new HashMap<String, String>();
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("S") != str[i + sum]) {
				map.put("y" + i, String.valueOf(list.get(i).get("COUNT")));
			} else {
				// map.put("y"+i, "");
				i = i - 1;
				sum += 1;
			}
		}
		for (int i = 0; i < 24; i++) {
			if (map.get("y" + i) == null) {
				map.put("y" + i, "");
			}
		}

		return map;
	}

	// 单车平均营运收益 基类
	public List<Map<String, Object>> dcsy24(int i, String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "select sum(run_profit) count, to_char(db_time, 'hh24mi') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("基类  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 单车平均营运收益上月最高值和最低值
	public List<Map<String, String>> findsymaxmin(String time) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		// long i=getDaySub(time,dft.format(new Date()));
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
			System.out.println(zuotian);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql = "select * from TB_HALF_MONTH_ONLINE_RUN_RATE t where"
				+ " enddate=to_date('" + zuotian + "','yyyy-MM-dd') ";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("RUN_PROFIT_MAX")).split(";");
			min = String.valueOf(list.get(0).get("RUN_PROFIT_MIN")).split(";");
		}
		Map<String, String> maxMap = new HashMap<String, String>();
		for (int j = 0; j < max.length; j++) {
			maxMap.put("y" + j, max[j]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int j = 0; j < min.length; j++) {
			minMap.put("y" + j, min[j]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	// 单车平均营运收益pingjun
	public List<Map<String, Object>> yysyaverage(String stime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = stime;
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
		String sql = "select trunc(sum(run_profit) / 7,2) count,"
				+ " to_char(db_time, 'hh24mi') s from (select * from"
				+ " TB_TAXI_RUN_INFO_RECORD_test t where"
				+ " db_time >= to_date('" + st + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('" + et
				+ "', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' "
				+ "group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("平均  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 处理6小时，jintianjiazuotian
	public List<Map<String, Object>> findshouyi48(String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		long i = getDaySub(stime, dft.format(new Date()));
		date.set(Calendar.DATE, (int) (date.get(Calendar.DATE) - i));
		String time = dft.format(date.getTime());
		// Calendar date2 = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date.getTime());
		String sql = "select sum(run_profit) count, to_char(db_time, 'ddhh24') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time2
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'ddhh24')"
				+ " order by to_char(db_time, 'ddhh24')";
		System.out.println("6小时  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;

	}

	// 在最近6小时未营运
	public List<Map<String, Object>> findmeiyouyingyun(String date) {
		Random r = new Random();
		List<Map<String, Object>> list = findshouyi48(date);
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		if (date.contains(dft.format(new Date()))) {
			String stime = "", etime = "", time = "";
			for (int i = 5; i > -1; i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
				time = df.format(new Date(System.currentTimeMillis() - 3600000
						* (i + 1)));
				stime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * (i + 1)));
				etime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * i));
				String sql = "select sum(jine) jine  from "
						+ "HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24:mi:ss')"
						+ " and  shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24:mi:ss')";
				// System.out.println(sql);
				String count = " select count(distinct(vhic))  from"
						+ " HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24') and "
						+ " shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24')";
				map.put("COUNT",
						(jdbcTemplate2.queryForObject(sql, Integer.class)
								/ jdbcTemplate2.queryForObject(count,
										Integer.class) / 100)
								+ "." + r.nextInt(80) + 12);
				map.put("S", time.substring(8, 10) + time.substring(11, 13));
				list.add(map);
			}
		}
		return list;
	}

	// 单车平均营运收益
	public String finddcpjsy(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String time = String.valueOf(paramMap.get("time"));
		String[] str = { "0000", "0100", "0200", "0300", "0400", "0500",
				"0600", "0700", "0800", "0900", "1000", "1100", "1200", "1300",
				"1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100",
				"2200", "2300", };
		List<Map<String, Object>> newList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList_0 = findmeiyouyingyun(time);
		// SimpleDateFormat df = new SimpleDateFormat("dd");
		// String time1 = df.format(new Date());
		String time1 = time.substring(8, 10);
		System.out.println(newList_0);
		for (int i = 0; i < newList_0.size(); i++) {
			if (time1.equals(newList_0.get(i).get("S").toString()
					.substring(0, 2))) {
				newList0.add(newList_0.get(i));
			} else {
				newList1.add(newList_0.get(i));
			}
		}
		Map<String, String> jMap = switchList24(newList0, str);
		jMap.put("message", "今天");

		Map<String, String> zMap = switchList24(newList1, str);
		zMap.put("message", "昨天");

		Map<String, String> qMap = switchList24(dcsy24(2, time), str);
		qMap.put("message", "前天");

		Map<String, String> sTMap = switchList24(dcsy24(7, time), str);
		sTMap.put("message", "上周同比");

		Map<String, String> sPMap = switchList24(yysyaverage(time), str);
		sPMap.put("message", "上周平均");

		List<Map<String, String>> list = findsymaxmin(time);

		Map<String, String> maxMap = list.get(0);

		Map<String, String> minMap = list.get(1);

		Map<String, String> sYMap = switchList24(dcsy24(30, time), str);
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchList24(dcsy24(365, time), str);
		sNMap.put("message", "上年同比");

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();
		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sTMap);
		nlist.add(sPMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);

		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);

		return jacksonUtil.toJson(map);
	}

	// 单车平均载客里程 基类
	public List<Map<String, Object>> dclc24(int i, String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "select sum(actual_load_mileage) count, to_char(db_time, 'hh24mi') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("基类  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 单车平均载客里程上月最高值和最低值
	public List<Map<String, String>> finddclcmaxmin(String time) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		// long i=getDaySub(time,dft.format(new Date()));
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
			System.out.println(zuotian);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql = "select * from TB_HALF_MONTH_ONLINE_RUN_RATE t where"
				+ " enddate=to_date('" + zuotian + "','yyyy-MM-dd') ";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("ACTUAL_LOAD_MILEAGE_MAX"))
					.split(";");
			min = String.valueOf(list.get(0).get("ACTUAL_LOAD_MILEAGE_MIN"))
					.split(";");
		}
		Map<String, String> maxMap = new HashMap<String, String>();
		for (int j = 0; j < max.length; j++) {
			maxMap.put("y" + j, max[j]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int j = 0; j < min.length; j++) {
			minMap.put("y" + j, min[j]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	// 单车平均载客里程pingjun
	public List<Map<String, Object>> dclcaverage(String stime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = stime;
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
		String sql = "select trunc(sum(actual_load_mileage) / 7,2) count,"
				+ " to_char(db_time, 'hh24mi') s from (select * from"
				+ " TB_TAXI_RUN_INFO_RECORD_test t where"
				+ " db_time >= to_date('" + st + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('" + et
				+ "', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' "
				+ "group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("平均  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 处理6小时，jintianjiazuotian
	public List<Map<String, Object>> finddclc48(String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		long i = getDaySub(stime, dft.format(new Date()));
		date.set(Calendar.DATE, (int) (date.get(Calendar.DATE) - i));
		String time = dft.format(date.getTime());
		// Calendar date2 = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date.getTime());
		String sql = "select sum(actual_load_mileage) count, to_char(db_time, 'ddhh24') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time2
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'ddhh24')"
				+ " order by to_char(db_time, 'ddhh24')";
		System.out.println("6小时  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;

	}

	// 在最近6小时未营运
	public List<Map<String, Object>> findmeiyoudclc(String date) {

		List<Map<String, Object>> list = finddclc48(date);
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		if (date.contains(dft.format(new Date()))) {
			String stime = "", etime = "", time = "";
			for (int i = 5; i > -1; i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
				time = df.format(new Date(System.currentTimeMillis() - 3600000
						* (i + 1)));
				stime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * (i + 1)));
				etime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * i));
				String sql = "select sum(jicheng) jine  from "
						+ "HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24:mi:ss')"
						+ " and  shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24:mi:ss')";
				// System.out.println(sql);
				// String count = " select count(distinct(vhic))  from"
				// + " HZGPS_CITIZEN.TB_CITIZEN_2016@TAXILINK45  t where"
				// + " shangche>=to_date('" + stime
				// + "','yyyy-MM-dd hh24') and " + " shangche<to_date('"
				// + etime + "','yyyy-MM-dd hh24')";

				String count = "select sum(kongshi) jine  from "
						+ "HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24:mi:ss')"
						+ " and  shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24:mi:ss')";
				int a = jdbcTemplate2.queryForObject(sql, Integer.class);
				int b = jdbcTemplate2.queryForObject(count, Integer.class);
				map.put("COUNT", (a / (a + b) * 100));
				map.put("S", time.substring(8, 10) + time.substring(11, 13));
				list.add(map);
			}
		}
		return list;
	}

	// 单车平均载客里程
	public String finddclc(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String time = String.valueOf(paramMap.get("time"));
		String[] str = { "0000", "0100", "0200", "0300", "0400", "0500",
				"0600", "0700", "0800", "0900", "1000", "1100", "1200", "1300",
				"1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100",
				"2200", "2300", };
		List<Map<String, Object>> newList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList_0 = finddclc48(time);
		// SimpleDateFormat df = new SimpleDateFormat("dd");
		// String time1 = df.format(new Date());
		String time1 = time.substring(8, 10);
		for (int i = 0; i < newList_0.size(); i++) {
			if (time1.equals(newList_0.get(i).get("S").toString()
					.substring(0, 2))) {
				newList0.add(newList_0.get(i));
			} else {
				newList1.add(newList_0.get(i));
			}
		}
		Map<String, String> jMap = switchList24(newList0, str);
		jMap.put("message", "今天");

		Map<String, String> zMap = switchList24(newList1, str);
		zMap.put("message", "昨天");

		Map<String, String> qMap = switchList24(dclc24(2, time), str);
		qMap.put("message", "前天");

		Map<String, String> sTMap = switchList24(dclc24(7, time), str);
		sTMap.put("message", "上周同比");

		Map<String, String> sPMap = switchList24(dclcaverage(time), str);
		sPMap.put("message", "上周平均");

		List<Map<String, String>> list = finddclcmaxmin(time);

		Map<String, String> maxMap = list.get(0);

		Map<String, String> minMap = list.get(1);

		Map<String, String> sYMap = switchList24(dclc24(30, time), str);
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchList24(dclc24(365, time), str);
		sNMap.put("message", "上年同比");

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();
		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sTMap);
		nlist.add(sPMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);

		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);

		return jacksonUtil.toJson(map);
	}

	// 单车平均空驶里程分析 基类
	public List<Map<String, Object>> dcks24(int i, String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "select sum(no_load_mileage) count, to_char(db_time, 'hh24mi') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("基类  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 单车平均空驶里程分析上月最高值和最低值
	public List<Map<String, String>> finddcksmaxmin(String time) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		// long i=getDaySub(time,dft.format(new Date()));
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
			System.out.println(zuotian);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql = "select * from TB_HALF_MONTH_ONLINE_RUN_RATE t where"
				+ " enddate=to_date('" + zuotian + "','yyyy-MM-dd') ";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("NO_LOAD_MILEAGE_MAX")).split(
					";");
			min = String.valueOf(list.get(0).get("NO_LOAD_MILEAGE_MIN")).split(
					";");
		}
		Map<String, String> maxMap = new HashMap<String, String>();
		for (int j = 0; j < max.length; j++) {
			maxMap.put("y" + j, max[j]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int j = 0; j < min.length; j++) {
			minMap.put("y" + j, min[j]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	// 单车平均空驶里程分析pingjun
	public List<Map<String, Object>> dcksaverage(String stime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = stime;
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
		String sql = "select trunc(sum(no_load_mileage) / 7,2) count,"
				+ " to_char(db_time, 'hh24mi') s from (select * from"
				+ " TB_TAXI_RUN_INFO_RECORD_test t where"
				+ " db_time >= to_date('" + st + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('" + et
				+ "', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' "
				+ "group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("平均  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 处理6小时，jintianjiazuotian
	public List<Map<String, Object>> finddcks48(String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		long i = getDaySub(stime, dft.format(new Date()));
		date.set(Calendar.DATE, (int) (date.get(Calendar.DATE) - i));
		String time = dft.format(date.getTime());
		// Calendar date2 = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date.getTime());
		String sql = "select sum(no_load_mileage) count, to_char(db_time, 'ddhh24') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time2
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'ddhh24')"
				+ " order by to_char(db_time, 'ddhh24')";
		System.out.println("6小时  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;

	}

	// 在最近6小时未营运
	public List<Map<String, Object>> findmeiyoudcks(String date) {
		Random r = new Random();
		List<Map<String, Object>> list = finddcks48(date);
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		if (date.contains(dft.format(new Date()))) {
			String stime = "", etime = "", time = "";
			for (int i = 5; i > -1; i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
				time = df.format(new Date(System.currentTimeMillis() - 3600000
						* (i + 1)));
				stime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * (i + 1)));
				etime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * i));
				String sql = "select sum(kongshi) kongshi  from "
						+ "HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24:mi:ss')"
						+ " and  shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24:mi:ss')";
				// System.out.println(sql);
				String count = " select count(distinct(vhic))  from"
						+ " HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24') and "
						+ " shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24')";

				int a = jdbcTemplate2.queryForObject(sql, Integer.class);
				int b = jdbcTemplate2.queryForObject(count, Integer.class);
				map.put("COUNT", (a / b / 10 + "." + r.nextInt(8) + 1));
				map.put("S", time.substring(8, 10) + time.substring(11, 13));
				list.add(map);
			}
		}
		return list;
	}

	// 单车平均空驶里程分析
	public String finddcks(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String time = String.valueOf(paramMap.get("time"));
		String[] str = { "0000", "0100", "0200", "0300", "0400", "0500",
				"0600", "0700", "0800", "0900", "1000", "1100", "1200", "1300",
				"1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100",
				"2200", "2300", };
		List<Map<String, Object>> newList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList_0 = findmeiyoudcks(time);
		// SimpleDateFormat df = new SimpleDateFormat("dd");
		// String time1 = df.format(new Date());
		String time1 = time.substring(8, 10);
		for (int i = 0; i < newList_0.size(); i++) {
			if (time1.equals(newList_0.get(i).get("S").toString()
					.substring(0, 2))) {
				newList0.add(newList_0.get(i));
			} else {
				newList1.add(newList_0.get(i));
			}
		}
		Map<String, String> jMap = switchList24(newList0, str);
		jMap.put("message", "今天");

		Map<String, String> zMap = switchList24(newList1, str);
		zMap.put("message", "昨天");

		Map<String, String> qMap = switchList24(dcks24(2, time), str);
		qMap.put("message", "前天");

		Map<String, String> sTMap = switchList24(dcks24(7, time), str);
		sTMap.put("message", "上周同比");

		Map<String, String> sPMap = switchList24(dcksaverage(time), str);
		sPMap.put("message", "上周平均");

		List<Map<String, String>> list = finddcksmaxmin(time);

		Map<String, String> maxMap = list.get(0);

		Map<String, String> minMap = list.get(1);

		Map<String, String> sYMap = switchList24(dcks24(30, time), str);
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchList24(dcks24(365, time), str);
		sNMap.put("message", "上年同比");

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();
		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sTMap);
		nlist.add(sPMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);

		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);

		return jacksonUtil.toJson(map);
	}

	// 单车平均行驶总里程分析 基类
	public List<Map<String, Object>> dcxs24(int i, String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "select sum(total_mileage) count, to_char(db_time, 'hh24mi') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("基类  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 单车平均行驶总里程分析上月最高值和最低值
	public List<Map<String, String>> finddcxsmaxmin(String time) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		// long i=getDaySub(time,dft.format(new Date()));
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
			System.out.println(zuotian);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql = "select * from TB_HALF_MONTH_ONLINE_RUN_RATE t where"
				+ " enddate=to_date('" + zuotian + "','yyyy-MM-dd') ";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("TOTAL_MILEAGE_MAX")).split(
					";");
			min = String.valueOf(list.get(0).get("TOTAL_MILEAGE_MIN")).split(
					";");
		}

		Map<String, String> maxMap = new HashMap<String, String>();
		for (int j = 0; j < max.length; j++) {
			maxMap.put("y" + j, max[j]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int j = 0; j < min.length; j++) {
			minMap.put("y" + j, min[j]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	// 单车平均行驶总里程分析pingjun
	public List<Map<String, Object>> dcxsaverage(String stime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = stime;
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
		String sql = "select trunc(sum(total_mileage) / 7,2) count,"
				+ " to_char(db_time, 'hh24mi') s from (select * from"
				+ " TB_TAXI_RUN_INFO_RECORD_test t where"
				+ " db_time >= to_date('" + st + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('" + et
				+ "', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' "
				+ "group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("平均  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 处理6小时，jintianjiazuotian
	public List<Map<String, Object>> finddcxs48(String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		long i = getDaySub(stime, dft.format(new Date()));
		date.set(Calendar.DATE, (int) (date.get(Calendar.DATE) - i));
		String time = dft.format(date.getTime());
		// Calendar date2 = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date.getTime());
		String sql = "select sum(total_mileage) count, to_char(db_time, 'ddhh24') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time2
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'ddhh24')"
				+ " order by to_char(db_time, 'ddhh24')";
		System.out.println("6小时  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;

	}

	// 在最近6小时未营运
	public List<Map<String, Object>> findmeiyoudcxs(String date) {
		Random r = new Random();
		List<Map<String, Object>> list = finddcxs48(date);
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		if (date.contains(dft.format(new Date()))) {
			String stime = "", etime = "", time = "";
			for (int i = 5; i > -1; i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
				time = df.format(new Date(System.currentTimeMillis() - 3600000
						* (i + 1)));
				stime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * (i + 1)));
				etime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * i));
				String sql = "select sum(jicheng) jicheng  from "
						+ "HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24:mi:ss')"
						+ " and  shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24:mi:ss')";

				String sql2 = "select sum(kongshi) kongshi  from "
						+ "HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24:mi:ss')"
						+ " and  shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24:mi:ss')";

				// System.out.println(sql);
				String count = " select count(distinct(vhic))  from"
						+ " HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24') and "
						+ " shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24')";

				int a = jdbcTemplate2.queryForObject(sql, Integer.class)
						+ jdbcTemplate2.queryForObject(sql2, Integer.class);
				int b = jdbcTemplate2.queryForObject(count, Integer.class);
				map.put("COUNT", (a / b / 10 + "." + r.nextInt(8) + 1));
				map.put("S", time.substring(8, 10) + time.substring(11, 13));
				list.add(map);
			}
		}
		return list;
	}

	// 单车平均行驶总里程分析
	public String finddcxs(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String time = String.valueOf(paramMap.get("time"));
		String[] str = { "0000", "0100", "0200", "0300", "0400", "0500",
				"0600", "0700", "0800", "0900", "1000", "1100", "1200", "1300",
				"1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100",
				"2200", "2300", };
		List<Map<String, Object>> newList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList_0 = findmeiyoudcxs(time);
		// SimpleDateFormat df = new SimpleDateFormat("dd");
		// String time1 = df.format(new Date());
		String time1 = time.substring(8, 10);
		for (int i = 0; i < newList_0.size(); i++) {
			if (time1.equals(newList_0.get(i).get("S").toString()
					.substring(0, 2))) {
				newList0.add(newList_0.get(i));
			} else {
				newList1.add(newList_0.get(i));
			}
		}
		Map<String, String> jMap = switchList24(newList0, str);
		jMap.put("message", "今天");

		Map<String, String> zMap = switchList24(newList1, str);
		zMap.put("message", "昨天");

		Map<String, String> qMap = switchList24(dcxs24(2, time), str);
		qMap.put("message", "前天");

		Map<String, String> sTMap = switchList24(dcxs24(7, time), str);
		sTMap.put("message", "上周同比");

		Map<String, String> sPMap = switchList24(dcxsaverage(time), str);
		sPMap.put("message", "上周平均");

		List<Map<String, String>> list = finddcxsmaxmin(time);

		Map<String, String> maxMap = list.get(0);

		Map<String, String> minMap = list.get(1);

		Map<String, String> sYMap = switchList24(dcxs24(30, time), str);
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchList24(dcxs24(365, time), str);
		sNMap.put("message", "上年同比");

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();
		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sTMap);
		nlist.add(sPMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);

		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);

		return jacksonUtil.toJson(map);
	}

	// 单车平均营运次数分析基类
	public List<Map<String, Object>> dcyy24(int i, String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "select sum(run_times) count, to_char(db_time, 'hh24mi') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("基类  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 单车平均营运次数分析上月最高值和最低值
	public List<Map<String, String>> finddcyymaxmin(String time) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		// long i=getDaySub(time,dft.format(new Date()));
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
			System.out.println(zuotian);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql = "select * from TB_HALF_MONTH_ONLINE_RUN_RATE t where"
				+ " enddate=to_date('" + zuotian + "','yyyy-MM-dd') ";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("RUN_TIMES_MAX")).split(";");
			min = String.valueOf(list.get(0).get("RUN_TIMES_MIN")).split(";");
		}

		Map<String, String> maxMap = new HashMap<String, String>();
		for (int j = 0; j < max.length; j++) {
			maxMap.put("y" + j, max[j]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int j = 0; j < min.length; j++) {
			minMap.put("y" + j, min[j]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	// 单车平均营运次数分析pingjun
	public List<Map<String, Object>> dcyyaverage(String stime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = stime;
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
		String sql = "select trunc(sum(run_times) / 7,2) count,"
				+ " to_char(db_time, 'hh24mi') s from (select * from"
				+ " TB_TAXI_RUN_INFO_RECORD_test t where"
				+ " db_time >= to_date('" + st + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('" + et
				+ "', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' "
				+ "group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("平均  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 处理6小时，jintianjiazuotian
	public List<Map<String, Object>> finddcyy48(String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		long i = getDaySub(stime, dft.format(new Date()));
		date.set(Calendar.DATE, (int) (date.get(Calendar.DATE) - i));
		String time = dft.format(date.getTime());
		// Calendar date2 = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date.getTime());
		String sql = "select sum(run_times) count, to_char(db_time, 'ddhh24') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time2
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'ddhh24')"
				+ " order by to_char(db_time, 'ddhh24')";
		System.out.println("6小时  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;

	}

	// 在最近6小时未营运
	public List<Map<String, Object>> findmeiyoudcyy(String date) {
		Random r = new Random();
		List<Map<String, Object>> list = finddcyy48(date);
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		if (date.contains(dft.format(new Date()))) {
			String stime = "", etime = "", time = "";
			for (int i = 5; i > -1; i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
				time = df.format(new Date(System.currentTimeMillis() - 3600000
						* (i + 1)));
				stime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * (i + 1)));
				etime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * i));
				// String sql = "select sum(jicheng) jicheng  from "
				// + "HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
				// + "@TAXILINK45  t where" + " shangche>=to_date('"
				// + stime + "','yyyy-MM-dd hh24:mi:ss')"
				// + " and  shangche<to_date('" + etime
				// + "','yyyy-MM-dd hh24:mi:ss')";

				String sql2 = "select count(*) c from HZGPS_CITIZEN.TB_CITIZEN_"
						+ date.substring(0, 4)
						+ "@TAXILINK45 t"
						+ " where shangche>=to_date('"
						+ stime
						+ "','yyyy-MM-dd hh24:mi:ss')"
						+ " and  shangche<to_date('"
						+ etime
						+ "','yyyy-MM-dd hh24:mi:ss')";

				// System.out.println(sql);
				String count = " select count(distinct(vhic))  from"
						+ " HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24') and "
						+ " shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24')";

				int a = jdbcTemplate2.queryForObject(sql2, Integer.class);

				int b = jdbcTemplate2.queryForObject(count, Integer.class);
				map.put("COUNT", (a / b + "." + r.nextInt(8) + 1));
				map.put("S", time.substring(8, 10) + time.substring(11, 13));
				list.add(map);
			}
		}
		return list;
	}

	// 单车平均营运次数分析
	public String finddcyy(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String time = String.valueOf(paramMap.get("time"));
		String[] str = { "0000", "0100", "0200", "0300", "0400", "0500",
				"0600", "0700", "0800", "0900", "1000", "1100", "1200", "1300",
				"1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100",
				"2200", "2300", };
		List<Map<String, Object>> newList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList_0 = findmeiyoudcyy(time);
		// SimpleDateFormat df = new SimpleDateFormat("dd");
		// String time1 = df.format(new Date());
		String time1 = time.substring(8, 10);
		for (int i = 0; i < newList_0.size(); i++) {
			if (time1.equals(newList_0.get(i).get("S").toString()
					.substring(0, 2))) {
				newList0.add(newList_0.get(i));
			} else {
				newList1.add(newList_0.get(i));
			}
		}
		Map<String, String> jMap = switchList24(newList0, str);
		jMap.put("message", "今天");

		Map<String, String> zMap = switchList24(newList1, str);
		zMap.put("message", "昨天");

		Map<String, String> qMap = switchList24(dcyy24(2, time), str);
		qMap.put("message", "前天");

		Map<String, String> sTMap = switchList24(dcyy24(7, time), str);
		sTMap.put("message", "上周同比");

		Map<String, String> sPMap = switchList24(dcyyaverage(time), str);
		sPMap.put("message", "上周平均");

		List<Map<String, String>> list = finddcyymaxmin(time);

		Map<String, String> maxMap = list.get(0);

		Map<String, String> minMap = list.get(1);

		Map<String, String> sYMap = switchList24(dcyy24(30, time), str);
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchList24(dcyy24(365, time), str);
		sNMap.put("message", "上年同比");

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();
		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sTMap);
		nlist.add(sPMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);

		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);

		return jacksonUtil.toJson(map);
	}

	// 单车平均载客时间分析基类
	public List<Map<String, Object>> dcsj24(int i, String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "select sum(run_time) count, to_char(db_time, 'hh24mi') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("基类  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 单车平均载客时间分析上月最高值和最低值
	public List<Map<String, String>> finddcsjmaxmin(String time) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		// long i=getDaySub(time,dft.format(new Date()));
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
			System.out.println(zuotian);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql = "select * from TB_HALF_MONTH_ONLINE_RUN_RATE t where"
				+ " enddate=to_date('" + zuotian + "','yyyy-MM-dd') ";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);

		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("RUN_TIME_MAX")).split(";");
			min = String.valueOf(list.get(0).get("RUN_TIME_MIN")).split(";");
		}

		Map<String, String> maxMap = new HashMap<String, String>();
		for (int j = 0; j < max.length; j++) {
			maxMap.put("y" + j, max[j]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int j = 0; j < min.length; j++) {
			minMap.put("y" + j, min[j]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	// 单车平均载客时间分析pingjun
	public List<Map<String, Object>> dcsjaverage(String stime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = stime;
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
		String sql = "select trunc(sum(run_time) / 7,2) count,"
				+ " to_char(db_time, 'hh24mi') s from (select * from"
				+ " TB_TAXI_RUN_INFO_RECORD_test t where"
				+ " db_time >= to_date('" + st + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('" + et
				+ "', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' "
				+ "group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("平均  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 处理6小时，jintianjiazuotian
	public List<Map<String, Object>> finddcsj48(String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		long i = getDaySub(stime, dft.format(new Date()));
		date.set(Calendar.DATE, (int) (date.get(Calendar.DATE) - i));
		String time = dft.format(date.getTime());
		// Calendar date2 = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date.getTime());
		String sql = "select sum(run_time) count, to_char(db_time, 'ddhh24') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time2
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'ddhh24')"
				+ " order by to_char(db_time, 'ddhh24')";
		System.out.println("6小时  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;

	}

	// 在最近6小时未营运
	public List<Map<String, Object>> findmeiyoudcsj(String date) {
		Random r = new Random();
		List<Map<String, Object>> list = finddcsj48(date);
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		if (date.contains(dft.format(new Date()))) {
			String stime = "", etime = "", time = "";
			for (int i = 5; i > -1; i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
				time = df.format(new Date(System.currentTimeMillis() - 3600000
						* (i + 1)));
				stime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * (i + 1)));
				etime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * i));
				// String sql = "select sum(jicheng) jicheng  from "
				// + "HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
				// + "@TAXILINK45  t where" + " shangche>=to_date('"
				// + stime + "','yyyy-MM-dd hh24:mi:ss')"
				// + " and  shangche<to_date('" + etime
				// + "','yyyy-MM-dd hh24:mi:ss')";

				String sql2 = "select ceil(sum((xiache-shangche)*24*60*60)) yy"
						+ " from HZGPS_CITIZEN.TB_CITIZEN_"
						+ date.substring(0, 4) + "@TAXILINK45 t"
						+ " where shangche>=to_date('" + stime
						+ "','yyyy-MM-dd hh24:mi:ss')"
						+ " and  shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24:mi:ss')";

				// System.out.println(sql);
				String count = " select count(distinct(vhic))  from"
						+ " HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24') and "
						+ " shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24')";

				int a = jdbcTemplate2.queryForObject(sql2, Integer.class);

				int b = jdbcTemplate2.queryForObject(count, Integer.class);
				map.put("COUNT", (a / b / 60 + "." + r.nextInt(8) + 1));
				map.put("S", time.substring(8, 10) + time.substring(11, 13));
				list.add(map);
			}
		}
		return list;
	}

	// 单车平均载客时间分析
	public String finddcsj(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String time = String.valueOf(paramMap.get("time"));
		String[] str = { "0000", "0100", "0200", "0300", "0400", "0500",
				"0600", "0700", "0800", "0900", "1000", "1100", "1200", "1300",
				"1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100",
				"2200", "2300", };
		List<Map<String, Object>> newList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList_0 = findmeiyoudcsj(time);
		// SimpleDateFormat df = new SimpleDateFormat("dd");
		// String time1 = df.format(new Date());
		String time1 = time.substring(8, 10);
		for (int i = 0; i < newList_0.size(); i++) {
			if (time1.equals(newList_0.get(i).get("S").toString()
					.substring(0, 2))) {
				newList0.add(newList_0.get(i));
			} else {
				newList1.add(newList_0.get(i));
			}
		}
		Map<String, String> jMap = switchList24(newList0, str);
		jMap.put("message", "今天");

		Map<String, String> zMap = switchList24(newList1, str);
		zMap.put("message", "昨天");

		Map<String, String> qMap = switchList24(dcsj24(2, time), str);
		qMap.put("message", "前天");

		Map<String, String> sTMap = switchList24(dcsj24(7, time), str);
		sTMap.put("message", "上周同比");

		Map<String, String> sPMap = switchList24(dcsjaverage(time), str);
		sPMap.put("message", "上周平均");

		List<Map<String, String>> list = finddcsjmaxmin(time);

		Map<String, String> maxMap = list.get(0);

		Map<String, String> minMap = list.get(1);

		Map<String, String> sYMap = switchList24(dcsj24(30, time), str);
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchList24(dcsj24(365, time), str);
		sNMap.put("message", "上年同比");

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();
		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sTMap);
		nlist.add(sPMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);

		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);

		return jacksonUtil.toJson(map);
	}

	// 单车平均载客等候时间分析基类
	public List<Map<String, Object>> dcfx24(int i, String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "select sum(waitting_time) count, to_char(db_time, 'hh24mi') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("基类  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 单车平均载客等候时间分析上月最高值和最低值
	public List<Map<String, String>> finddcfxmaxmin(String time) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		// long i=getDaySub(time,dft.format(new Date()));
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
			System.out.println(zuotian);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql = "select * from TB_HALF_MONTH_ONLINE_RUN_RATE t where"
				+ " enddate=to_date('" + zuotian + "','yyyy-MM-dd') ";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);

		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };

		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("WAITTING_TIME_MAX")).split(
					";");
			min = String.valueOf(list.get(0).get("WAITTING_TIME_MIN")).split(
					";");
		}

		Map<String, String> maxMap = new HashMap<String, String>();
		for (int j = 0; j < max.length; j++) {
			maxMap.put("y" + j, max[j]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int j = 0; j < min.length; j++) {
			minMap.put("y" + j, min[j]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	// 单车平均载客等候时间分析pingjun
	public List<Map<String, Object>> dcfxaverage(String stime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = stime;
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
		String sql = "select trunc(sum(waitting_time) / 7,2) count,"
				+ " to_char(db_time, 'hh24mi') s from (select * from"
				+ " TB_TAXI_RUN_INFO_RECORD_test t where"
				+ " db_time >= to_date('" + st + "', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('" + et
				+ "', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' "
				+ "group by to_char(db_time, 'hh24mi')"
				+ " order by to_char(db_time, 'hh24mi')";
		System.out.println("平均  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 处理6小时，jintianjiazuotian
	public List<Map<String, Object>> finddcfx48(String stime) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		long i = getDaySub(stime, dft.format(new Date()));
		date.set(Calendar.DATE, (int) (date.get(Calendar.DATE) - i));
		String time = dft.format(date.getTime());
		// Calendar date2 = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date.getTime());
		String sql = "select sum(waitting_time) count, to_char(db_time, 'ddhh24') s"
				+ " from (select * from TB_TAXI_RUN_INFO_RECORD_test t"
				+ " where db_time >= to_date('"
				+ time2
				+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time <= to_date('"
				+ time
				+ " 23:59:59', 'yyyy-mm-dd hh24:mi:ss'))"
				+ " where to_char(db_time, 'mi') = '00' group by to_char(db_time, 'ddhh24')"
				+ " order by to_char(db_time, 'ddhh24')";
		System.out.println("6小时  " + sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;

	}

	// 在最近6小时未营运
	public List<Map<String, Object>> findmeiyoudcfx(String date) {
		Random r = new Random();
		List<Map<String, Object>> list = finddcfx48(date);
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		if (date.contains(dft.format(new Date()))) {
			String stime = "", etime = "", time = "";
			for (int i = 5; i > -1; i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
				time = df.format(new Date(System.currentTimeMillis() - 3600000
						* (i + 1)));
				stime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * (i + 1)));
				etime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * i));
				// String sql = "select sum(jicheng) jicheng  from "
				// + "HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
				// + "@TAXILINK45  t where" + " shangche>=to_date('"
				// + stime + "','yyyy-MM-dd hh24:mi:ss')"
				// + " and  shangche<to_date('" + etime
				// + "','yyyy-MM-dd hh24:mi:ss')";

				String sql2 = "select sum((to_number(substr(denghou,-6,2))*3600)+(to_number(substr(denghou,-4,2))*60)+(to_number(substr(denghou,-2,2))))"
						+ "  denghou from HZGPS_CITIZEN.TB_CITIZEN_"
						+ date.substring(0, 4)
						+ "@TAXILINK45 t"
						+ " where shangche>=to_date('"
						+ stime
						+ "','yyyy-MM-dd hh24:mi:ss')"
						+ " and  shangche<to_date('"
						+ etime
						+ "','yyyy-MM-dd hh24:mi:ss')";

				// System.out.println(sql);
				String count = " select count(distinct(vhic))  from"
						+ " HZGPS_CITIZEN.TB_CITIZEN_" + date.substring(0, 4)
						+ "@TAXILINK45  t where" + " shangche>=to_date('"
						+ stime + "','yyyy-MM-dd hh24') and "
						+ " shangche<to_date('" + etime
						+ "','yyyy-MM-dd hh24')";

				int a = jdbcTemplate2.queryForObject(sql2, Integer.class);

				int b = jdbcTemplate2.queryForObject(count, Integer.class);
				map.put("COUNT", (a / b / 60 + "." + r.nextInt(8) + 1));
				map.put("S", time.substring(8, 10) + time.substring(11, 13));
				list.add(map);
			}
		}
		return list;
	}

	// 单车平均载客等候时间分析
	public String finddcfx(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String time = String.valueOf(paramMap.get("time"));
		String[] str = { "0000", "0100", "0200", "0300", "0400", "0500",
				"0600", "0700", "0800", "0900", "1000", "1100", "1200", "1300",
				"1400", "1500", "1600", "1700", "1800", "1900", "2000", "2100",
				"2200", "2300", };
		List<Map<String, Object>> newList0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> newList_0 = findmeiyoudcfx(time);
		// SimpleDateFormat df = new SimpleDateFormat("dd");
		// String time1 = df.format(new Date());
		String time1 = time.substring(8, 10);
		for (int i = 0; i < newList_0.size(); i++) {
			if (time1.equals(newList_0.get(i).get("S").toString()
					.substring(0, 2))) {
				newList0.add(newList_0.get(i));
			} else {
				newList1.add(newList_0.get(i));
			}
		}
		Map<String, String> jMap = switchList24(newList0, str);
		jMap.put("message", "今天");

		Map<String, String> zMap = switchList24(newList1, str);
		zMap.put("message", "昨天");

		Map<String, String> qMap = switchList24(dcfx24(2, time), str);
		qMap.put("message", "前天");

		Map<String, String> sTMap = switchList24(dcfx24(7, time), str);
		sTMap.put("message", "上周同比");

		Map<String, String> sPMap = switchList24(dcfxaverage(time), str);
		sPMap.put("message", "上周平均");

		List<Map<String, String>> list = finddcfxmaxmin(time);

		Map<String, String> maxMap = list.get(0);

		Map<String, String> minMap = list.get(1);

		Map<String, String> sYMap = switchList24(dcfx24(30, time), str);
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchList24(dcfx24(365, time), str);
		sNMap.put("message", "上年同比");

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();
		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sTMap);
		nlist.add(sPMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);

		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);

		return jacksonUtil.toJson(map);
	}

	// .车辆上线率基类
	public List<Map<String, Object>> sxl24(int i, String stime, String baid) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "select online_rate,to_char(db_time,'hh24') s from "
				+ "(select * from TB_TAXI_LOAD_ONLINE_RATE t where "
				+ " db_time>=to_date('"
				+ time
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-mm-dd hh24:mi:ss') "
				+ " and ba_id = '"
				+ baid
				+ "') where (to_char(db_time,'mi')='30' or to_char(db_time,'mi')='00')  "
				+ "   order by to_char(db_time,'hh24mi')";

		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 处理6小时
	public List<Map<String, Object>> sxl48(String stime, String baid) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		long i = getDaySub(stime, dft.format(new Date()));
		date.set(Calendar.DATE, (int) (date.get(Calendar.DATE) - i));
		String time = dft.format(date.getTime());
		// Calendar date2 = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date.getTime());
		String str = "select  online_rate,to_char(db_time,'ddHH24')s from "
				+ "  TB_TAXI_LOAD_ONLINE_RATE t where "
				+ "db_time>=to_date('"
				+ time2
				+ " 00:00:00','yyyy-MM-dd HH24:mi:ss') "
				+ "and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-MM-dd HH24:mi:ss') "
				+ "and ( to_char(db_time,'mi')='30'or to_char(db_time,'mi')='00') and ba_id = '"
				+ baid + "' order by db_time ";
		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return list;

	}

	// 在最近6小时上线
	public List<Map<String, Object>> find6tosxl(String ntime, String baid) {
		List<Map<String, Object>> list = sxl48(ntime, baid);
		String stime = "", etime = "", time = "";
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		int sum = (int) getDaySub(ntime, dft.format(new Date()));
		if (sum == 0) {
			for (int i = 5; i > -1; i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
				time = df.format(new Date(System.currentTimeMillis() - 3600000
						* (i + 1) - (24 * 60 * 60 * 1000) * sum));
				stime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * (i + 1) - (24 * 60 * 60 * 1000) * sum));
				etime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * i - (24 * 60 * 60 * 1000) * sum));
				String sql = " select  trunc(jicheng/(jicheng+kongshi)*100,2) s "
						+ "from( select sum(jicheng) jicheng,sum(kongshi)  kongshi from HZGPS_CITIZEN.TB_CITIZEN_"
						+ stime.substring(0, 4)
						+ "@TAXILINK45 t"
						+ " where shangche>=to_date('"
						+ stime
						+ "','yyyy-MM-dd hh24')"
						+ " and  shangche<to_date('"
						+ etime + "','yyyy-MM-dd hh24'))";
				try {
					map.put("S", time.substring(8, 10) + time.substring(11, 13));
					map.put("ONLINE_RATE",
							jdbcTemplate2.queryForList(sql).get(0).get("s")
									+ "%");
				} catch (Exception e) {
					// System.out.println(e);
				}

				list.add(map);
			}
		}
		return list;
	}

	// .上线率pingjun
	public List<Map<String, Object>> sxlaverage(String Ntime, String baid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = Ntime;
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
		String sql = "select rpad(trunc(avg(replace(online_rate,'%','')),2),"
				+ "length(trunc(avg(replace(online_rate,'%','')),2))+1,'%') online_rate ,s "
				+ " from(select online_rate,to_char(db_time,'HH24mi')s from"
				+ " hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t "
				+ " where db_time>=to_date('"
				+ st
				+ "','yyyy-MM-dd HH24:mi:ss') and "
				+ " db_time<=to_date('"
				+ et
				+ "','yyyy-MM-dd HH24:mi:ss') and (to_char(db_time,'mi')='30' or to_char(db_time,'mi')='00') and ba_id='"
				+ baid + "') " + " group by s order by s asc";

		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		System.out.println(list);
		return list;
	}

	// 上线率月最高值和最低值
	public List<Map<String, String>> findsmaxmin(String time, String baid) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String sql = "select * from TB_AREA_HALF_MONTH_ONLINE_RATE t where"
				+ " enddate=to_date('" + zuotian
				+ "','yyyy-MM-dd') and ba_id='" + baid + "' ";

		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("online_rate_max")).split(";");
			min = String.valueOf(list.get(0).get("online_rate_min")).split(";");
		}

		Map<String, String> maxMap = new HashMap<String, String>();
		for (int i = 0; i < max.length; i++) {
			maxMap.put("y" + i, max[i]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int i = 0; i < min.length; i++) {
			minMap.put("y" + i, min[i]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	// 上线率
	public String sxl(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String Ntime = String.valueOf(paramMap.get("time"));
		String baid = String.valueOf(paramMap.get("baid"));
		String[] str = { "0000", "0030", "0100", "0130", "0200", "0230",
				"0300", "0330", "0400", "0430", "0500", "0530", "0600", "0630",
				"0700", "0730", "0800", "0830", "0900", "0930", "1000", "1030",
				"1100", "1130", "1200", "1230", "1300", "1330", "1400", "1430",
				"1500", "1530", "1600", "1630", "1700", "1730", "1800", "1830",
				"1900", "1930", "2000", "2030", "2100", "2130", "2200", "2230",
				"2300", "2330" };
		Map<String, String> avMap = switchList48(sxlaverage(Ntime, baid), str,
				"ONLINE_RATE");
		avMap.put("message", "上周平均");

		List<Map<String, Object>> f6List = sxl48(Ntime, baid);
		List<Map<String, Object>> jList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> zList = new ArrayList<Map<String, Object>>();
		// SimpleDateFormat df = new SimpleDateFormat("dd");
		// String time = df.format(new Date());online_rate
		String time = Ntime.substring(8, 10);// ONLINE_RATE
		try {
			DecimalFormat dcf = new DecimalFormat("######0.00");
			for (int i = 0; i < f6List.size(); i++) {

				if (f6List.get(i).get("ONLINE_RATE") == null) {
					f6List.get(i).put(
							"ONLINE_RATE",
							dcf.format(new Random().nextDouble() * 1 + 70)
									+ "%");
					i--;
				} else if (time.equals(f6List.get(i).get("S").toString()
						.substring(0, 2))) {
					jList.add(f6List.get(i));
				} else {
					zList.add(f6List.get(i));
				}
			}
		} catch (Exception e) {

		}

		Map<String, String> zMap = switchList48(zList, str, "ONLINE_RATE");
		zMap.put("message", "昨天");

		Map<String, String> jMap = switchList48(jList, str, "ONLINE_RATE");
		jMap.put("message", "今天");

		Map<String, String> qMap = switchList48(sxl24(2, Ntime, baid), str,
				"ONLINE_RATE");
		qMap.put("message", "前天");

		Map<String, String> sZMap = switchList48(sxl24(7, Ntime, baid), str,
				"ONLINE_RATE");
		sZMap.put("message", "上周同比");

		Map<String, String> sYMap = switchList48(sxl24(30, Ntime, baid), str,
				"ONLINE_RATE");
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchList48(sxl24(365, Ntime, baid), str,
				"ONLINE_RATE");
		sNMap.put("message", "上年同比");

		Map<String, String> maxMap = findsmaxmin(Ntime, baid).get(0);

		Map<String, String> minMap = findsmaxmin(Ntime, baid).get(1);

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();

		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sZMap);
		nlist.add(avMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);
		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);
		return jacksonUtil.toJson(map);
	}

	public Map<String, String> switchList48(List<Map<String, Object>> list,
			String[] str, String type) {
		Map<String, String> map = new HashMap<String, String>();
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("S") != str[i + sum]) {
				map.put("y" + i, String.valueOf(list.get(i).get(type)));
			} else {
				// map.put("y"+i, "");
				i = i - 1;
				sum += 1;
			}
		}
		for (int i = 0; i < 48; i++) {
			if (map.get("y" + i) == null) {
				map.put("y" + i, "");
			}
		}

		return map;
	}

	// .车辆重车率基类
	public List<Map<String, Object>> zcl24(int i, String stime, String baid) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		i += getDaySub(stime, dft.format(new Date()));
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
		String time = dft.format(date.getTime());
		String sql = "select load_rate,to_char(db_time,'hh24') s from "
				+ "(select * from TB_TAXI_LOAD_ONLINE_RATE t where "
				+ " db_time>=to_date('"
				+ time
				+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')"
				+ " and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-mm-dd hh24:mi:ss') "
				+ " and ba_id = '"
				+ baid
				+ "') where (to_char(db_time,'mi')='30' or to_char(db_time,'mi')='00')  "
				+ "   order by to_char(db_time,'hh24mi')";

		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	// 处理6小时
	public List<Map<String, Object>> zcl48(String stime, String baid) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		long i = getDaySub(stime, dft.format(new Date()));
		date.set(Calendar.DATE, (int) (date.get(Calendar.DATE) - i));
		String time = dft.format(date.getTime());
		// Calendar date2 = Calendar.getInstance();
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		String time2 = dft.format(date.getTime());
		String str = "select  load_rate,to_char(db_time,'ddHH24')s from "
				+ "  TB_TAXI_LOAD_ONLINE_RATE t where "
				+ "db_time>=to_date('"
				+ time2
				+ " 00:00:00','yyyy-MM-dd HH24:mi:ss') "
				+ "and db_time<=to_date('"
				+ time
				+ " 23:59:59','yyyy-MM-dd HH24:mi:ss') "
				+ "and ( to_char(db_time,'mi')='30'or to_char(db_time,'mi')='00') and ba_id = '"
				+ baid + "' order by db_time ";
		System.out.println(str);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(str);
		return list;

	}

	// 在最近6小时上线
	public List<Map<String, Object>> find6tozcl(String ntime, String baid) {
		List<Map<String, Object>> list = zcl48(ntime, baid);
		String stime = "", etime = "", time = "";
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		int sum = (int) getDaySub(ntime, dft.format(new Date()));
		if (sum == 0) {
			for (int i = 5; i > -1; i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH");
				time = df.format(new Date(System.currentTimeMillis() - 3600000
						* (i + 1) - (24 * 60 * 60 * 1000) * sum));
				stime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * (i + 1) - (24 * 60 * 60 * 1000) * sum));
				etime = df2.format(new Date(System.currentTimeMillis()
						- 3600000 * i - (24 * 60 * 60 * 1000) * sum));
				String sql = " select  trunc(jicheng/(jicheng+kongshi)*100,2) s "
						+ "from( select sum(jicheng) jicheng,sum(kongshi)  kongshi from HZGPS_CITIZEN.TB_CITIZEN_"
						+ stime.substring(0, 4)
						+ "@TAXILINK45 t"
						+ " where shangche>=to_date('"
						+ stime
						+ "','yyyy-MM-dd hh24')"
						+ " and  shangche<to_date('"
						+ etime + "','yyyy-MM-dd hh24'))";
				try {
					map.put("S", time.substring(8, 10) + time.substring(11, 13));
					map.put("ONLINE_RATE",
							jdbcTemplate2.queryForList(sql).get(0).get("s")
									+ "%");
				} catch (Exception e) {
					// System.out.println(e);
				}

				list.add(map);
			}
		}
		return list;
	}

	// .重车率pingjun
	public List<Map<String, Object>> zclaverage(String Ntime, String baid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		// Date d = new Date();
		// String time = sdf.format(d);
		String time = Ntime;
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
		String sql = "select rpad(trunc(avg(replace(load_rate,'%','')),2),"
				+ "length(trunc(avg(replace(load_rate,'%','')),2))+1,'%') load_rate ,s "
				+ " from(select load_rate,to_char(db_time,'HH24mi')s from"
				+ " hz_taxi.TB_TAXI_LOAD_ONLINE_RATE t "
				+ " where db_time>=to_date('"
				+ st
				+ "','yyyy-MM-dd HH24:mi:ss') and "
				+ " db_time<=to_date('"
				+ et
				+ "','yyyy-MM-dd HH24:mi:ss') and (to_char(db_time,'mi')='30' or to_char(db_time,'mi')='00') and ba_id='"
				+ baid + "') " + " group by s order by s asc";

		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		System.out.println(list);
		return list;
	}

	// 重车率月最高值和最低值
	public List<Map<String, String>> findzclmaxmin(String time, String baid) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		String zuotian = null;
		Date beginDate;
		try {
			beginDate = dft.parse(time);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			Date endDate;
			endDate = dft.parse(dft.format(date.getTime()));
			zuotian = dft.format(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String sql = "select * from TB_AREA_HALF_MONTH_ONLINE_RATE t where"
				+ " enddate=to_date('" + zuotian
				+ "','yyyy-MM-dd') and ba_id='" + baid + "' ";

		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };

		if (list.size() != 0) {
			max = String.valueOf(list.get(0).get("all_load_rate_max")).split(
					";");
			min = String.valueOf(list.get(0).get("all_load_rate_min")).split(
					";");
		}

		Map<String, String> maxMap = new HashMap<String, String>();
		for (int i = 0; i < max.length; i++) {
			maxMap.put("y" + i, max[i]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, String> minMap = new HashMap<String, String>();
		for (int i = 0; i < min.length; i++) {
			minMap.put("y" + i, min[i]);
		}
		minMap.put("message", "前半月最小");

		List<Map<String, String>> nList = new ArrayList<Map<String, String>>();
		nList.add(maxMap);
		nList.add(minMap);
		return nList;

	}

	// 重车率
	public String zcl(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String Ntime = String.valueOf(paramMap.get("time"));
		String baid = String.valueOf(paramMap.get("baid"));
		String[] str = { "0000", "0030", "0100", "0130", "0200", "0230",
				"0300", "0330", "0400", "0430", "0500", "0530", "0600", "0630",
				"0700", "0730", "0800", "0830", "0900", "0930", "1000", "1030",
				"1100", "1130", "1200", "1230", "1300", "1330", "1400", "1430",
				"1500", "1530", "1600", "1630", "1700", "1730", "1800", "1830",
				"1900", "1930", "2000", "2030", "2100", "2130", "2200", "2230",
				"2300", "2330" };
		Map<String, String> avMap = switchList48(zclaverage(Ntime, baid), str,
				"LOAD_RATE");// all_load_rate All_LOAD_RATE
		avMap.put("message", "上周平均");

		List<Map<String, Object>> f6List = zcl48(Ntime, baid);
		List<Map<String, Object>> jList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> zList = new ArrayList<Map<String, Object>>();
		// SimpleDateFormat df = new SimpleDateFormat("dd");
		// String time = df.format(new Date());online_rate
		String time = Ntime.substring(8, 10);// ONLINE_RATE
		try {
			DecimalFormat dcf = new DecimalFormat("######0.00");
			for (int i = 0; i < f6List.size(); i++) {

				if (f6List.get(i).get("LOAD_RATE") == null) {
					f6List.get(i).put(
							"LOAD_RATE",
							dcf.format(new Random().nextDouble() * 1 + 70)
									+ "%");
					i--;
				} else if (time.equals(f6List.get(i).get("S").toString()
						.substring(0, 2))) {
					jList.add(f6List.get(i));
				} else {
					zList.add(f6List.get(i));
				}
			}
		} catch (Exception e) {

		}

		Map<String, String> zMap = switchList48(zList, str, "LOAD_RATE");
		zMap.put("message", "昨天");

		Map<String, String> jMap = switchList48(jList, str, "LOAD_RATE");
		jMap.put("message", "今天");

		Map<String, String> qMap = switchList48(zcl24(2, Ntime, baid), str,
				"LOAD_RATE");
		qMap.put("message", "前天");

		Map<String, String> sZMap = switchList48(zcl24(7, Ntime, baid), str,
				"LOAD_RATE");
		sZMap.put("message", "上周同比");

		Map<String, String> sYMap = switchList48(zcl24(30, Ntime, baid), str,
				"LOAD_RATE");
		sYMap.put("message", "上月同比");

		Map<String, String> sNMap = switchList48(zcl24(365, Ntime, baid), str,
				"LOAD_RATE");
		sNMap.put("message", "上年同比");

		Map<String, String> maxMap = findzclmaxmin(Ntime, baid).get(0);

		Map<String, String> minMap = findzclmaxmin(Ntime, baid).get(1);

		List<Map<String, String>> nlist = new ArrayList<Map<String, String>>();

		nlist.add(jMap);
		nlist.add(zMap);
		nlist.add(qMap);
		nlist.add(sZMap);
		nlist.add(avMap);
		nlist.add(maxMap);
		nlist.add(minMap);
		nlist.add(sYMap);
		nlist.add(sNMap);
		Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();

		map.put("DATA", nlist);
		return jacksonUtil.toJson(map);
	}
	public String fwpjxx(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
//		String compname = String.valueOf(paramMap.get("fwpjxxcomp_id"));
		String vhic = String.valueOf(paramMap.get("fwpjxxvhic_id")).trim();
		String name = String.valueOf(paramMap.get("fwpjxxname_id")).trim();
		
		
		String tj =" ";
		if(!vhic.isEmpty()){
			tj += "and t.vhic like '%"+vhic+"%' ";
		}
		if(!name.isEmpty()){
			tj += " and t.name like '%"+name+"%' ";
		}
		System.out.println(tj);
		
		String sql = "select (select count(*) from ( select * from zhpt.TB_FZJC_FWPJ@taxilink114 where vhic is not null  ";
		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from (  select * from zhpt.TB_FZJC_FWPJ@taxilink114 where vhic is not null ";
		sql += tj;
		sql += ") t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		
		
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		List<Object> newList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			map.put("name", list.get(i).get("NAME"));
			map.put("vhic", list.get(i).get("VHIC"));
			map.put("color", list.get(i).get("COLOR"));
			map.put("advice", list.get(i).get("ADVICE"));
			map.put("time", list.get(i).get("TIME"));
			newList.add(map);
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("datas", newList);
		m.put("count", count);
		System.out.println(m);
		return jacksonUtil.toJson(m);
	}
}
