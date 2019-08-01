package mvc.service;

import helper.JWDSwitch;
import helper.JacksonUtil;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mvc.cache.DataItem;
import mvc.cache.GisData;
import net.sf.json.JSONArray;














import net.sf.json.JSONObject;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JyxxServer extends MvcService{
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
	public String findnowmonthall(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String time = sdf.format(d);
		String stime = time.substring(0, 10)+" 00:00:00";
		String time1 = "2015-01-01 00:00:00";
		String time2 = time.substring(0, 7)+"-01 00:00:00";
		String now = findjyxx1(stime,time);
		String all = findjyxx1(time1,time);
		String month = findjyxx1(time2, time);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("NOW", now);
		map.put("ALL", all);
		map.put("MONTH", month);
		return jacksonUtil.toJson(map);
	}
	public String findjyxx1(String jyxx_stime,String jyxx_etime){
		String a = "0,0";
		String sql="select sum(total_fee) count,count(*) c from ZHIFUBAO.TB_ORDER_RECODER where"
				+ " gmt_payment>to_Date('"+jyxx_stime+"','yyyy-mm-dd hh24:mi:ss')"
				+ " and gmt_payment<to_Date('"+jyxx_etime+"','yyyy-mm-dd hh24:mi:ss')";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		if(list!=null&&list.size()>0){
			a=list.get(0).get("COUNT")+","+list.get(0).get("C");
		}
		return a;
	}
	public String findjyxx(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String jyxx_stime = String.valueOf(paramMap.get("jyxx_stime"));
		String jyxx_etime = String.valueOf(paramMap.get("jyxx_etime"));
		String jyxx_type  = String.valueOf(paramMap.get("jyxx_type"));
		if(jyxx_stime==null||jyxx_stime.equals("null")||jyxx_etime==null||jyxx_etime.equals("null")){
			return "";
		}else{
			String sql="select * from (select t.*,rownum rn from ZHIFUBAO.TB_ORDER_RECODER t where"
					+ " gmt_payment>to_Date('"+jyxx_stime+"','yyyy-mm-dd hh24:mi:ss')"
					+ " and gmt_payment<to_Date('"+jyxx_etime+"','yyyy-mm-dd hh24:mi:ss')";
			if(jyxx_type!=null&&jyxx_type.length()>0&&!jyxx_type.equals("null")){
				sql += " and subject like '%"+jyxx_type+"%' ";
			}
			sql+="and rownum <= "+(page * pageSize)+") where rn>"+((page - 1) * pageSize);
			
			String sql1="select * from (select t.*,rownum rn from ZHIFUBAO.TB_ORDER_RECODER t where"
					+ " gmt_payment>to_Date('"+jyxx_stime+"','yyyy-mm-dd hh24:mi:ss')"
					+ " and gmt_payment<to_Date('"+jyxx_etime+"','yyyy-mm-dd hh24:mi:ss')";
			if(jyxx_type!=null&&jyxx_type.length()>0&&!jyxx_type.equals("null")){
				sql1 += " and subject like '%"+jyxx_type+"%' ";
			}
			sql1+=")";
			String sql2="select sum(total_fee) sum from ZHIFUBAO.TB_ORDER_RECODER t where"
					+ " gmt_payment > to_Date('"+jyxx_stime+"', 'yyyy-mm-dd hh24:mi:ss')"
					+ " and gmt_payment < to_Date('"+jyxx_etime+"', 'yyyy-mm-dd hh24:mi:ss')";
			System.out.println(sql);
			List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
			List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(sql1);
			List<Map<String, Object>> list2 =jdbcTemplate2.queryForList(sql2);
			int count= list1.size();
			System.out.println(count);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("datas", list);
			map.put("count", count);
			map.put("jine", list2);
			return jacksonUtil.toJson(map);
		}
	}
	
	
	public String findxxbs(){
		String sql = "select sum(average_count*60) count,to_char(db_time,'yyyy-mm-dd')"
				+ " db_time from ELECTRIC_TRICK_CAR.TB_ST_BB t group by"
				+ " to_char(db_time,'yyyy-mm-dd') order by db_time desc";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		long a = d.getTime();
		long c = a-1000*60*60;
		Date b = new Date(c);
		String stime = sdf.format(b);
		String etime = sdf.format(d);
		String sql1 = "select * from ELECTRIC_TRICK_CAR.TB_ST_BB t where"
				+ " db_time>to_date('"+stime+"','yyyy-MM-dd HH24:mi:ss')"
				+ " and db_time<to_date('"+etime+"','yyyy-MM-dd HH24:mi:ss')";
		List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(sql1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datas", list);
		map.put("now", list1);
		return jacksonUtil.toJson(map);
	}
	public String fingyjsjjr(String sjzt){
		String sql = "select * from zhpt.TB_YJZH_YJSJ@taxilink114 where 1=1 ";
		if(sjzt!=null&&sjzt.length()>0&&!sjzt.equals("null")){
			sql+=" and sjzt like '%"+sjzt+"%'";
		}
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return jacksonUtil.toJson(list);
	}
	public String addyjsjjr(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String sjbh = String.valueOf(paramMap.get("sjbh"));
		String sjzt = String.valueOf(paramMap.get("sjzt"));
		String fsdz  = String.valueOf(paramMap.get("fsdz"));
		String jwdxx = String.valueOf(paramMap.get("jwdxx"));
		String bjr = String.valueOf(paramMap.get("bjr"));
		String bjdh  = String.valueOf(paramMap.get("bjdh"));
		String jjr = String.valueOf(paramMap.get("jjr"));
		String sjjl = String.valueOf(paramMap.get("sjjl"));
		String bjnr  = String.valueOf(paramMap.get("bjnr"));
		String bjfs = String.valueOf(paramMap.get("bjfs"));
		String sjjb =String.valueOf(paramMap.get("sjjb"));
		String[] xx=null;
		if(jwdxx!=null&&jwdxx.length()>0&&!jwdxx.equals("null")){
			xx=jwdxx.split(",");
		}
		String sql = "insert into zhpt.TB_YJZH_YJSJ@taxilink114 (SJBH,SJZT,TIME,ADDRESS,SJNR,"
				+ "BJR,DJDH,JJR,BJFS,SJJL,PX,PY,SJJB) values ("
				+ "'"+sjbh+"','"+sjzt+"',sysdate,'"+fsdz+"','"+bjnr.trim()+"','"+bjr+
				"','"+bjdh+"','"+jjr+"','"+bjfs+"','"+sjjl+"','"+xx[0]+"','"+xx[1]+"','"+sjjb+"')";
		System.out.println(sql);
		jdbcTemplate2.update(sql);
		return "{}";
	}
	public String delyjsjjr(String id){
		String sql = "delete from zhpt.TB_YJZH_YJSJ@taxilink114 where id = '"+id+"'";
		System.out.println(sql);
		jdbcTemplate2.update(sql);
		return "{}";
	}
	public String edityjsjjr(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		String sjbh = String.valueOf(paramMap.get("sjbh"));
		String sjzt = String.valueOf(paramMap.get("sjzt"));
		String fsdz  = String.valueOf(paramMap.get("fsdz"));
		String jwdxx = String.valueOf(paramMap.get("jwdxx"));
		String bjr = String.valueOf(paramMap.get("bjr"));
		String bjdh  = String.valueOf(paramMap.get("bjdh"));
		String jjr = String.valueOf(paramMap.get("jjr"));
		String sjjl = String.valueOf(paramMap.get("sjjl"));
		String bjnr  = String.valueOf(paramMap.get("bjnr"));
		String bjfs = String.valueOf(paramMap.get("bjfs"));
		String sjjb =String.valueOf(paramMap.get("sjjb"));
		String id = String.valueOf(paramMap.get("id"));
		String[] xx=null;
		if(jwdxx!=null&&jwdxx.length()>0&&!jwdxx.equals("null")){
			xx=jwdxx.split(",");
		}
		String sql = "update zhpt.TB_YJZH_YJSJ@taxilink114 set SJBH = '"+sjbh+"',"
				+ "SJZT='"+sjzt+"',TIME=sysdate,ADDRESS='"+fsdz+"'"
				+ ",SJNR='"+bjnr+"',"
				+ "BJR='"+bjr+"',DJDH='"+bjdh+"',JJR='"+jjr+"',"
				+ "BJFS='"+bjfs+"',SJJL='"+sjjl+"',PX='"+xx[0]+"',PY='"+xx[1]+"',SJJB='"+sjjb+"' "
				+ " where id = '"+id+"'";
		System.out.println(sql);
		jdbcTemplate2.update(sql);
		return "{}";
	}
	public String hsyjsjjr(String id){
		String sql = "update zhpt.TB_YJZH_YJSJ@taxilink114 set sh='1' where id = '"+id+"'";
		System.out.println(sql);
		jdbcTemplate2.update(sql);
		return "{}";
	}
	public String findzbb(){
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
		Date d = new Date();
		String now = sdf.format(d);
		String sql = "select * from zhpt.TB_YJZH_ZBB@taxilink114 t where zbsj = '"+now+"'";
		System.out.println(sql);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		//得到当前一周的日期
		Calendar cal =Calendar.getInstance();
	    SimpleDateFormat df = new SimpleDateFormat("M,d");
	    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
	    System.out.println("********得到本周一的日期*******"+df.format(cal.getTime()));
	    String[] now1 = df.format(cal.getTime()).split(",");
		  //这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		  //增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		System.out.println("********得到本周天的日期*******"+df.format(cal.getTime()));
		String[] week = df.format(cal.getTime()).split(",");
		  
		System.out.println(now1[0]+"   "+week[0]);
		 Calendar c_begin = new GregorianCalendar();
	     Calendar c_end = new GregorianCalendar();
	     DateFormatSymbols dfs = new DateFormatSymbols();
	     String[] weeks = dfs.getWeekdays();
	     c_begin.set(2016, Integer.parseInt(now1[0])-1, Integer.parseInt(now1[1])); //Calendar的月从0-11，所以4月是3.
	     c_end.set(2016, Integer.parseInt(week[0])-1, Integer.parseInt(week[1])); //Calendar的月从0-11，所以5月是4.

	     int count = 1;
	     c_end.add(Calendar.DAY_OF_YEAR, 1);  //结束日期下滚一天是为了包含最后一天
	     String tj = "";
	     while(c_begin.before(c_end)){
//	       System.out.println("第"+count+"周  日期："+sdf.format(new java.sql.Date(c_begin.getTime().getTime()))+","+weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
	    	 System.out.println(sdf.format(new java.sql.Date(c_begin.getTime().getTime())));
	    	 tj += "'"+sdf.format(new java.sql.Date(c_begin.getTime().getTime()))+"',";

	      if(c_begin.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
	          count++;
	      }
	      c_begin.add(Calendar.DAY_OF_YEAR, 1);
	     }
	     tj=tj.substring(0, tj.length()-1);
	     String sql1 = "select * from zhpt.TB_YJZH_ZBB@taxilink114 t where zbsj in ("+tj+")";
	     System.out.println(sql1);
	     List<Map<String, Object>> list1 = jdbcTemplate2.queryForList(sql1);
	     Map<String, Object> map = new HashMap<String, Object>();
	     map.put("NOW", list);
	     map.put("WEEK", list1);
		return jacksonUtil.toJson(map);
	}
	public String findchgs(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.parseInt(paramMap.get("page")+"");
		int pageSize = Integer.parseInt(paramMap.get("pageSize")+"");
		String type = String.valueOf(paramMap.get("type"));
		String vhic = String.valueOf(paramMap.get("vhic"));
		
		String info="select (select count(*) from (select * from" +
				" zhpt.tb_vehicle@taxilink114 t where compname like '%"+type+"%' ";
		if(vhic!=null&&vhic.length()>0&&!vhic.equals("null")){
			info +=" and PLATENUMBER like '%"+vhic+"%'";
		}
		info+=")) as count, tt.* from (select t.*, rownum as rn from (select *" +
				" from zhpt.tb_vehicle@taxilink114  where compname like '%"+type+"%' ";
		if(vhic!=null&&vhic.length()>0&&!vhic.equals("null")){
			info +=" and PLATENUMBER like '%"+vhic+"%'";
		}
		info += ") t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(info);
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(info);
		int count = 0;
		if(list!=null&&list.size()>0){
			count=Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datas", list);
		map.put("count", count);
		return jacksonUtil.toJson(map);
	}
	public String findcomp(){
		String sql="select count(c.name) count from HZYZ.TB_VEHICLE_ADD@taxilink114 t,zhpt.TB_COMPANY@taxilink114 c where t.companyid1 = c.fid group by c.name";
	     System.out.println(sql);
	     List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return jacksonUtil.toJson(list);
	}
	public String findname(){
		String sql="select count(owner1) count from HZYZ.TB_VEHICLE_ADD@taxilink114 t,zhpt.TB_COMPANY@taxilink114 c where t.companyid1 = c.fid group by  c.name";
	     System.out.println(sql);
	     List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return jacksonUtil.toJson(list);
	}
	public String findvideo(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String vhic = String.valueOf(paramMap.get("vhic"));
		String sql = "select * from HZGPS_TAXI.VW_VEHI_MDT@taxilink v where v.vehi_no like '%"+vhic+"%' and vehi_no like '%浙AT%' and rownum < 500 order by vehi_no";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		JSONArray jsonArray = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int count = 0;
		if(null!=list){
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
					Date otime = null;
					try {
						otime = sdf.parse(list.get(i).get("DB_TIME").toString());
					} catch (ParseException e) {
						System.out.println("时间转换失败");
						e.printStackTrace();
					}
					Date ntime = new Date();
					long diff = ntime.getTime() - otime.getTime();
					long mins = diff / (1000 * 60);
					if(mins>10){
						one.put("icon", "../img/video/off.png");
					}else{
						one.put("icon", "../img/video/on.png");
						JSONObject td1 = new JSONObject(); 
						td1.put("id", "td1");
						td1.put("pId", count+"");
						td1.put("name", "通道1");
						td1.put("icon", "../img/video/mdt.png");
						td1.put("camera", list.get(i).get("MDT_NO"));
						td1.put("lx", "hksp");
						td1.put("td", "1");
						td1.put("vehinum",list.get(i).get("VEHI_NO"));
						JSONObject td2 = new JSONObject(); 
						td2.put("id", "td2");
						td2.put("pId", count+"");
						td2.put("name", "通道2");
						td2.put("icon", "../img/video/mdt.png");
						td2.put("camera", list.get(i).get("MDT_NO"));
						td2.put("lx", "hksp");
						td2.put("td", "2");
						td2.put("vehinum",list.get(i).get("VEHI_NO"));
						JSONObject td3 = new JSONObject(); 
						td3.put("id", "td3");
						td3.put("pId", count+"");
						td3.put("name", "通道3");
						td3.put("icon", "../img/video/mdt.png");
						td3.put("camera", list.get(i).get("MDT_NO"));
						td3.put("lx", "hksp");
						td3.put("td", "3");
						td3.put("vehinum",list.get(i).get("VEHI_NO"));
						JSONObject td4 = new JSONObject(); 
						td4.put("id", "td4");
						td4.put("pId", count+"");
						td4.put("name", "通道4");
						td4.put("icon", "../img/video/mdt.png");
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
					one.put("icon", "../img/video/off.png");
				}
				jsonArray.add(one);
			}
		}
		JSONObject hksp = new JSONObject(); 
		hksp.put("id", "hksp");
		hksp.put("pId", "");
		hksp.put("name", "海康视频");
		hksp.put("isParent",true);
		hksp.put("open",true);
		jsonArray.add(hksp);
		return jacksonUtil.toJson(jsonArray);
	}
}
