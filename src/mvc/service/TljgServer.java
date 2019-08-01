package mvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helper.JacksonUtil;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TljgServer {
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

	// 司机资质信息
	public String sjzz(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String cerserialNumber = String.valueOf(paramMap.get("zz_no"));
		String yname = String.valueOf(paramMap.get("zz_yehu"));
		String zz_name = String.valueOf(paramMap.get("zz_name"));
		String tj = "";
		if (!StringUtils.isEmpty(cerserialNumber)
				&& !cerserialNumber.toLowerCase().equals("null")) {
			tj += " and CERTIFICATE_NUMBER like '%" + cerserialNumber + "%'";
		}
		if (!StringUtils.isEmpty(yname) && !yname.toLowerCase().equals("null")) {
			tj += " and company_name like '%" + yname + "%'";
		}
		if (!StringUtils.isEmpty(zz_name) && !zz_name.toLowerCase().equals("null")) {
			tj += " and name like '%" + zz_name + "%'";
		}
		String sql = "select (select count(*) from (select c.company_name,c.plate_number,t.name,t.id_number,c.certificate_number,valid_period_end,check_end_date,t.mobile_phone from V_PERSON_CERTIFICATE@TAXILINK_SJZX C,V_TW_TAXI_PERSON@TAXILINK_SJZX T where category_code ='09000' and plate_number like '%浙A%' and  T.ID=C.PERSON_ID  ";
		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from (select c.company_name,c.plate_number,t.name,t.id_number,c.certificate_number,valid_period_end,check_end_date,t.mobile_phone from V_PERSON_CERTIFICATE@TAXILINK_SJZX C,V_TW_TAXI_PERSON@TAXILINK_SJZX T where category_code ='09000' and plate_number like '%浙A%' and  T.ID=C.PERSON_ID ";
		sql += tj;
		sql += ") t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 车辆信息
	public String clxx(String postData) {
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String plateNumber = String.valueOf(paramMap.get("clxx_vhic"));
		String plateColor = String.valueOf(paramMap.get("clxx_color"));
		String comName = String.valueOf(paramMap.get("clxx_yehu"));
		String tj = "";
		if (!StringUtils.isEmpty(plateNumber)
				&& !plateNumber.toLowerCase().equals("null")) {
			tj += " and vehi_no like '%" + plateNumber + "%'";
		}
		if (!StringUtils.isEmpty(plateColor)
				&& !plateColor.toLowerCase().equals("null")) {
			tj += " and plate_color like '%" + plateColor + "%'";
		}
		if (!StringUtils.isEmpty(comName)
				&& !comName.toLowerCase().equals("null")) {
			tj += " and comp_name like '%" + comName + "%'";
		}
		String sql = "select (select count(*) from (select * from"
				+ " zhpt.tb_clxx@taxilink114 where 1=1";
		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from (select"
				+ " * from  zhpt.tb_clxx@taxilink114 where 1=1 ";
		sql += tj;
		sql += " ) t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 车载设备信息
	public String czsbxx(String postData) {

		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});

		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String czsb_vhic = String.valueOf(paramMap.get("czsb_vhic"));
		String czsb_mdttype = String.valueOf(paramMap.get("czsb_mdttype"));
		String tj = "";
		if (!StringUtils.isEmpty(czsb_vhic) && !czsb_vhic.toLowerCase().equals("null")) {
			tj += " and vehi_no like '%" + czsb_vhic + "%'";
		}
		if (!StringUtils.isEmpty(czsb_mdttype) && !czsb_mdttype.toLowerCase().equals("null")) {
			tj += " and mdt_sub_type like '%" + czsb_mdttype + "%'";
		}
		String info="select (select count(*) from (select * from" +
				" HZGPS_TAXI.VW_VEHICLE@TAXILINK t where mdt_sub_type is not null ";
			info+=tj;
		info+=")) as count, tt.* from (select t.*, rownum as rn from (select *" +
				" from HZGPS_TAXI.VW_VEHICLE@TAXILINK t where mdt_sub_type is not null ";
			info+=tj;
		info += ") t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(info);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(info);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 司机上班信息
	public String sjsb(String postData) {

		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});

		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String jsycyzgzh = String.valueOf(paramMap.get("sb_cyzgz"));
		String qyjyxkzh = String.valueOf(paramMap.get("sb_jyxkz"));
		String clzt = String.valueOf(paramMap.get("sb_sxbzt"));
		String tj = "";
		if (!StringUtils.isEmpty(jsycyzgzh)
				&& !jsycyzgzh.toLowerCase().equals("null")) {
			tj += " and jsycyzgzh like '%" + jsycyzgzh + "%'";
		}
		if (!StringUtils.isEmpty(qyjyxkzh)
				&& !qyjyxkzh.toLowerCase().equals("null")) {
			tj += " and qyjyxkzh like '%" + qyjyxkzh + "%'";
		}
		if (!StringUtils.isEmpty(clzt) && !clzt.toLowerCase().equals("null")) {
			tj += " and clzt like '%" + clzt + "%'";
		}
		String sql = "select (select count(*) from (select * from"
				+ " hz_systems.SIGNON V where 1=1 ";
		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from (select * "
				+ " from hz_systems.SIGNON v where 1=1 ";
		sql += tj;
		sql += ") t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 司机下班信息
	public String sjxb(String postData) {

		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});

		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String jsycyzgzh = String.valueOf(paramMap.get("xb_cyzgz"));
		String qyjyxkzh = String.valueOf(paramMap.get("xb_jyxkz"));
		String clzt = String.valueOf(paramMap.get("xb_sxbzt"));
		String tj = "";
		if (!StringUtils.isEmpty(jsycyzgzh)
				&& !jsycyzgzh.toLowerCase().equals("null")) {
			tj += " and jsycyzgzh like '%" + jsycyzgzh + "%'";
		}
		if (!StringUtils.isEmpty(qyjyxkzh)
				&& !qyjyxkzh.toLowerCase().equals("null")) {
			tj += " and qyjyxkzh like '%" + qyjyxkzh + "%'";
		}
		if (!StringUtils.isEmpty(clzt) && !clzt.toLowerCase().equals("null")) {
			tj += " and clzt like '%" + clzt + "%'";
		}
		String sql = "select (select count(*) from (select * from"
				+ " hz_systems.SIGNOUT V where 1=1 ";

		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from (select * "
				+ " from hz_systems.SIGNOUT v where 1=1 ";

		sql += tj;
		sql += ") t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 司机替班信息
	public String sjtb(String postData) {

		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});

		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String tbsjbh = String.valueOf(paramMap.get("tb_htbh"));
		String cphm = String.valueOf(paramMap.get("tb_vhic"));
		String cpys = String.valueOf(paramMap.get("tb_color"));
		String tj = "";
		if (!StringUtils.isEmpty(tbsjbh)
				&& !tbsjbh.toLowerCase().equals("null")) {
			tj += " and tbsjbh like '%" + tbsjbh + "%'";
		}
		if (!StringUtils.isEmpty(cphm) && !cphm.toLowerCase().equals("null")) {
			tj += " and cphm like '%" + cphm + "%'";
		}
		if (!StringUtils.isEmpty(cpys) && !cpys.toLowerCase().equals("null")) {
			tj += " and cpys like '%" + cpys + "%'";
		}
		String sql = "select (select count(*) from (select * from"
				+ " hz_systems.SUBSTITUTE V where 1=1 ";

		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from (select * "
				+ " from hz_systems.SUBSTITUTE v where 1=1 ";

		sql += tj;
		sql += ") t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 克隆信息
	public String klxx(String postData) {

		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});

		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String zfjcxx_name = String.valueOf(paramMap.get("zfjcxx_name"));
		String cphm = String.valueOf(paramMap.get("zfjcxx_vhic"));
		String zfjcxx_stime = String.valueOf(paramMap.get("zfjcxx_stime"));
		String zfjcxx_etime = String.valueOf(paramMap.get("zfjcxx_etime"));
		String tj = "";
		if (!StringUtils.isEmpty(zfjcxx_name) && !zfjcxx_name.toLowerCase().equals("null")) {
			tj += " and ILLEGAL_FACT like '%" + zfjcxx_name + "%'";
		}
		if (!StringUtils.isEmpty(cphm) && !cphm.toLowerCase().equals("null")) {
			tj += " and VEHICLE_PLATE_NUMBER like '%" + cphm + "%'";
		}
		if (!StringUtils.isEmpty(zfjcxx_stime) && !zfjcxx_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(zfjcxx_etime)
				&& !zfjcxx_etime.toLowerCase().equals("null")) {
			tj += " and  illegal_time >to_date('" + zfjcxx_stime
					+ "','yyyy-MM-dd hh24:mi:ss')"
					+ " and  illegal_time<to_date('" + zfjcxx_etime
					+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		String sql = "select (select count(*) from (select * from"
				+ "  V_TW_TAXI_CASE@TAXILINK_SJZX V where audit_type ='12' and (vehicle_plate_number like '%浙ALT%' or vehicle_plate_number like '%浙APT%' or vehicle_plate_number like '%浙AT%' or vehicle_plate_number like '%浙AQT%' or vehicle_plate_number like '%浙AUT%' or vehicle_plate_number like '%浙ACT%')";

		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from (select * "
				+ " from  V_TW_TAXI_CASE@TAXILINK_SJZX v where audit_type ='12' and (vehicle_plate_number like '%浙ALT%' or vehicle_plate_number like '%浙APT%' or vehicle_plate_number like '%浙AT%' or vehicle_plate_number like '%浙AQT%' or vehicle_plate_number like '%浙AUT%' or vehicle_plate_number like '%浙ACT%')";

		sql += tj;
		sql += " order by illegal_time desc) t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 服务质量信息考核
	public String fwzlxx(String postData) {

		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});

		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String yhmc = String.valueOf(paramMap.get("pd_qymc"));
		String pd_nd = String.valueOf(paramMap.get("pd_nd"));
		String pd_xydj = String.valueOf(paramMap.get("pd_xydj"));

		String tj = "";
		if (!StringUtils.isEmpty(yhmc) && !yhmc.toLowerCase().equals("null")) {
			tj += " and yhmc like '%" + yhmc + "%'";
		}
		if (!StringUtils.isEmpty(pd_nd) && !pd_nd.toLowerCase().equals("null")) {
			tj += " and sj like '%" + pd_nd + "%'";
		}
		if (!StringUtils.isEmpty(pd_xydj) && !pd_xydj.toLowerCase().equals("null")) {
			tj += " and xydj = '" + pd_xydj + "'";
		}
		String sql = "select (select count(*) from (select * from"
				+ " hz_systems.SERVICEEVALUATION V where 1=1 ";

		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from (select * "
				+ " from hz_systems.SERVICEEVALUATION v where 1=1 ";

		sql += tj;
		sql += ") t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 执法案件表违章---处罚
	public String zfwz(String postData) {

		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});

		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String company_name = String.valueOf(paramMap.get("wz_yhmc"));
		String vehicle = String.valueOf(paramMap
				.get("wz_cheh"));
		String wz_stime = String.valueOf(paramMap.get("wz_stime"));
		String wz_etime = String.valueOf(paramMap.get("wz_etime"));
		String tj = "";
		if (!StringUtils.isEmpty(company_name)
				&& !company_name.toLowerCase().equals("null")) {
			tj += " and COMPANY_NAME like '%" + company_name + "%'";
		}
		if (!StringUtils.isEmpty(vehicle)
				&& !vehicle.toLowerCase().equals("null")) {
			tj += " and VEHICLE_PLATE_NUMBER like '%" + vehicle
					+ "%'";
		}
		if (!StringUtils.isEmpty(wz_stime) && !wz_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(wz_etime)
				&& !wz_etime.toLowerCase().equals("null")) {
			tj += " and  illegal_time >to_date('" + wz_stime
					+ "','yyyy-MM-dd hh24:mi:ss')"
					+ " and  illegal_time<to_date('" + wz_etime
					+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		String sql = "select (select count(*) from (select * from"
				+ " hz_systems.V_CASE V where audit_type ='12' and (vehicle_plate_number like '%浙ALT%' or vehicle_plate_number like '%浙APT%' or vehicle_plate_number like '%浙AT%' or vehicle_plate_number like '%浙AQT%' or vehicle_plate_number like '%浙AUT%' or vehicle_plate_number like '%浙ACT%')";

		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from (select * "
				+ " from hz_systems.V_CASE v where audit_type ='12' and (vehicle_plate_number like '%浙ALT%' or vehicle_plate_number like '%浙APT%' or vehicle_plate_number like '%浙AT%' or vehicle_plate_number like '%浙AQT%' or vehicle_plate_number like '%浙AUT%' or vehicle_plate_number like '%浙ACT%')";

		sql += tj;
		sql += ") t where rownum <= " + (page * pageSize)
				+ " order by illegal_time desc) tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}

	// 执法案件表投诉
	public String zfts(String postData) {

		Map<String, Object> paramMap = jacksonUtil.toObject(postData,
				new TypeReference<Map<String, Object>>() {
				});

		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String id = String.valueOf(paramMap.get("ts_yehu"));
		String status = String.valueOf(paramMap.get("ts_vehi"));
		String ts_stime = String.valueOf(paramMap.get("ts_stime"));
		String ts_etime = String.valueOf(paramMap.get("ts_etime"));
		String tj = "";
		if (!StringUtils.isEmpty(id) && !id.toLowerCase().equals("null")) {
			tj += " and COMPANY_NAME like '%" + id + "%'";
		}
		if (!StringUtils.isEmpty(status)
				&& !status.toLowerCase().equals("null")) {
			tj += " and VEHICLE_PLATE_NUMBER like '%" + status + "%'";
		}
		if (!StringUtils.isEmpty(ts_stime) && !ts_stime.toLowerCase().equals("null")
				&& !StringUtils.isEmpty(ts_etime)
				&& !ts_etime.toLowerCase().equals("null")) {
			tj += " and  illegal_time >to_date('" + ts_stime
					+ "','yyyy-MM-dd hh24:mi:ss')"
					+ " and  illegal_time<to_date('" + ts_etime
					+ "','yyyy-MM-dd hh24:mi:ss')";
		}
		String sql = "select (select count(*) from (select * from"
				+ "  V_TW_TAXI_CASE@TAXILINK_SJZX V where source='3' and (vehicle_plate_number like '%浙ALT%' or vehicle_plate_number like '%浙APT%' or vehicle_plate_number like '%浙AT%' or vehicle_plate_number like '%浙AQT%' or vehicle_plate_number like '%浙AUT%' or vehicle_plate_number like '%浙ACT%')";

		sql += tj;
		sql += ")) as count, tt.* from (select t.*, rownum as rn from (select * "
				+ " from  V_TW_TAXI_CASE@TAXILINK_SJZX v where source='3' and (vehicle_plate_number like '%浙ALT%' or vehicle_plate_number like '%浙APT%' or vehicle_plate_number like '%浙AT%' or vehicle_plate_number like '%浙AQT%' or vehicle_plate_number like '%浙AUT%' or vehicle_plate_number like '%浙ACT%')";

		sql += tj;
		sql += " order by illegal_time desc) t where rownum <= " + (page * pageSize)
				+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list = null;
		list = jdbcTemplate2.queryForList(sql);
		int count = 0;
		if (null != list && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map resultMap = new HashMap();
		resultMap.put("count", count);
		resultMap.put("datas", list);
		return jacksonUtil.toJson(resultMap);
	}
	/**
	 * 司机账号信息增删改查
	 */
	public String findAccounts(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer
				.valueOf(String.valueOf(paramMap.get("pageSize")));
		String comp = String.valueOf(paramMap.get("comp"));
		String vehi_no = String.valueOf(paramMap.get("vehi_no"));
		String tj="";
		if(comp!=null&&comp.length()>0){
			tj+=" and pname like '%"+comp+"%'";
		}
		if(vehi_no!=null&&vehi_no.length()>0){
			tj+=" and car_no like '%"+vehi_no+"%'";
		}
		String sql="select (select count(*) from (select * from zhifubao.TB_ACCOUNTS t" +
				" where  biz_no = '0'";
			sql+=tj;
		sql+=")) as count, tt.* from (select t.*, rownum as rn from (select * from" +
				" zhifubao.TB_ACCOUNTS t where biz_no = '0'";
			sql+=tj;
		sql += " order by system_time desc) t where rownum <= " + (page * pageSize)
		+ ") tt where tt.rn > " + ((page - 1) * pageSize);
		System.out.println(sql);
		List<Map<String, Object>> list=jdbcTemplate2.queryForList(sql);
		int count=0;
		if(null!=list&&list.size()>0){
			count=Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("count", count);
		map.put("datas", list);
		return jacksonUtil.toJson(map);
	}
	
	public int addAccounts(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String DRIVER_NAME = String.valueOf(paramMap.get("driver_name"));
		String DRIVER_MOBILE = String.valueOf(paramMap.get("driver_mobile"));
		String LICENCE_NO = String.valueOf(paramMap.get("licence_no"));
		String SUBSIDIARY_ID = String.valueOf(paramMap.get("subsidiary_id"));
		String CAR_NO = String.valueOf(paramMap.get("car_no"));
		String CITY_NO = String.valueOf(paramMap.get("city_no"));
		String ALIPAY_ACCOUNT = String.valueOf(paramMap.get("alipay_account"));
		String BIZ_NO = String.valueOf(paramMap.get("state"));
		String PNAME = String.valueOf(paramMap.get("pname"));
		String PID = String.valueOf(paramMap.get("pid"));
		String SYSTEM_TIME = String.valueOf(paramMap.get("system_time"));
		String COMPANY_ID = String.valueOf(paramMap.get("company_id"));
		String sql="insert into zhifubao.TB_ACCOUNTS (DRIVER_NAME," +
				"DRIVER_MOBILE,LICENCE_NO,SUBSIDIARY_ID,CAR_NO," +
				"CITY_NO,ALIPAY_ACCOUNT,BIZ_NO,PNAME,PID," +
				"SYSTEM_TIME,COMPANY_ID) values ('"+DRIVER_NAME+"','"+DRIVER_MOBILE+"'," +
				"'"+LICENCE_NO+"','"+SUBSIDIARY_ID+"','"+CAR_NO+"','"+CITY_NO+"'," +
				"'"+ALIPAY_ACCOUNT+"','"+BIZ_NO+"','"+PNAME+"','"+PID+"'," +
				"to_date('"+SYSTEM_TIME+"','yyyy-MM-dd hh24:mi:ss'),'"+COMPANY_ID+"')" ;
		System.out.println(sql);
		int count=jdbcTemplate2.update(sql);
		return count;
	}
	public String findAccountsid(String id){
		String sql="select * from zhifubao.TB_ACCOUNTS where id='"+id+"'";
		System.out.println(sql);
		List<Map<String , Object>>list=jdbcTemplate2.queryForList(sql);
		return jacksonUtil.toJson(list);
	}
	public int editAccount(String postData){
		Map<String, Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String, Object>>() {});
		String DRIVER_NAME = String.valueOf(paramMap.get("driver_name"));
		String DRIVER_MOBILE = String.valueOf(paramMap.get("driver_mobile"));
		String LICENCE_NO = String.valueOf(paramMap.get("licence_no"));
		String SUBSIDIARY_ID = String.valueOf(paramMap.get("subsidiary_id"));
		String CAR_NO = String.valueOf(paramMap.get("car_no"));
		String CITY_NO = String.valueOf(paramMap.get("city_no"));
		String ALIPAY_ACCOUNT = String.valueOf(paramMap.get("alipay_account"));
		String BIZ_NO = String.valueOf(paramMap.get("state"));
		String PNAME = String.valueOf(paramMap.get("pname"));
		String PID = String.valueOf(paramMap.get("pid"));
		String SYSTEM_TIME = String.valueOf(paramMap.get("system_time"));
		String COMPANY_ID = String.valueOf(paramMap.get("company_id"));
		String ID = String.valueOf(paramMap.get("id"));
		String sql="update zhifubao.TB_ACCOUNTS set " +
				"DRIVER_NAME='"+DRIVER_NAME+"'," +
				"DRIVER_MOBILE='"+DRIVER_MOBILE+"'," +
				"LICENCE_NO='"+LICENCE_NO+"'," +
				"SUBSIDIARY_ID='"+SUBSIDIARY_ID+"'," +
				"CAR_NO='"+CAR_NO+"'," +
				"CITY_NO='"+CITY_NO+"'," +
				"ALIPAY_ACCOUNT='"+ALIPAY_ACCOUNT+"'," +
				"BIZ_NO='"+BIZ_NO+"'," +
				"PNAME='"+PNAME+"'," +
				"PID='"+PID+"'," +
				"SYSTEM_TIME=to_date('"+SYSTEM_TIME+"','yyyy-mm-dd hh24:mi:ss')," +
				"COMPANY_ID='"+COMPANY_ID+"' where id = '"+ID+"'";
		System.out.println(sql);
		int count=jdbcTemplate2.update(sql);
		return count;
	}
	public int delAccounts(String id){
		String sql="delete from zhifubao.TB_ACCOUNTS where id='"+id+"'";
		int count=jdbcTemplate2.update(sql);
		return count;
	}
}
