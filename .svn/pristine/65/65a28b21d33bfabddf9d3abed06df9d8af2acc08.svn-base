package mvc.service;

import helper.JWDSwitch;
import helper.JacksonUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import mvc.cache.DataItem;
import mvc.cache.GisData;
import net.sf.json.JSONArray;

















import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class gxdcServer {
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
	public String gxdc(String gs,String bh) {
		String sql = "select comp_name,vehi_bh from gxdc.tb_yba where comp_name =? and vehi_bh =?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,gs,bh);
		if(list.size()==0){
			int c = jdbcTemplate.queryForObject("select count(1) from gxdc.tb_wba where  comp_name ='"+gs+"' and vehi_bh ='"+bh+"'", Integer.class);
			if(c==0){
				jdbcTemplate.update("insert into gxdc.tb_wba(comp_name,vehi_bh,sj) values(?,?,sysdate)", gs,bh);
			}
		}else{
			jdbcTemplate.update("update gxdc.tb_yba set sj=sysdate where comp_name =? and vehi_bh =?", gs,bh);
		}
		return jacksonUtil.toJson(list);
	}
	public String bal(){
		DecimalFormat    df   = new DecimalFormat("######0.00");
		String sql = "select c1.*,c2.c2 from(select a.comp_name,a.c,b.c1 from (select count(1) c,"
				+ " comp_name from gxdc.tb_yba group by comp_name) a left join (select count(1) c1,"
				+ " comp_name from gxdc.tb_wba group by comp_name) b on a.comp_name = b.comp_name"
				+ " order by c desc) c1 left join (select count(1) c2,comp_name from gxdc.tb_yba"
				+ " where sj is not null group by comp_name) c2 on c1.comp_name = c2.comp_name";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			double a = Double.parseDouble(String.valueOf(list.get(i).get("C")).equals("null")?"0":String.valueOf(list.get(i).get("C")));
			double b = Double.parseDouble(String.valueOf(list.get(i).get("C1")).equals("null")?"0":String.valueOf(list.get(i).get("C1")));
			double c = Double.parseDouble(String.valueOf(list.get(i).get("C2")).equals("null")?"0":String.valueOf(list.get(i).get("C2")));
			list.get(i).put("LV", df.format(b/(a+b)));
			list.get(i).put("C1", b);
			list.get(i).put("C2", b+c);
		}
		return jacksonUtil.toJson(list);
	}
	public String lchajd(String sjhm,String nr){
		sjhm = sjhm.replace(" ", "");
//		String[] sz = sjhm.split(";");
		Pattern pattern = Pattern.compile("[； ;]+");
		String[] sz = pattern.split(sjhm);
		String id = java.util.UUID.randomUUID().toString();
		int c = 0;
		for (int i = 0; i < sz.length; i++) {
			if(sz[i].length()>1){
				c = jdbcTemplate2.update("insert into hz_other.TB_LCHAJD(id,sjhm,nr) values(?, ?, ?)",id,sz[i],nr);
			}
		}
		if(c>0){
			return "{\"info\":\"发送成功\"}";
		}else{
			return "{\"info\":\"发送失败\"}";
		}
	}
	public String lchajd2(){
		String sql = "select * from hz_other.TB_LCHAJD";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			if(String.valueOf(list.get(i).get("BJ")).equals("2")){
				list.get(i).put("TYPE", "发送成功");
			}else{
				list.get(i).put("TYPE", "发送失败");
			}
		}
		jdbcTemplate2.update("delete from hz_other.TB_LCHAJD");
		return jacksonUtil.toJson(list);
	}
}
