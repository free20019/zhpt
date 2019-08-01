package mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import helper.JacksonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;




@Service
public class fileServer {
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
	

	
	public int upload(List<List<Object>> list) {
		int count = 0;
		String sql1="delete from TB_SCJG_DR t";
		count=jdbcTemplate.update(sql1);
		for (int i = 1; i < list.size(); i++) {
			String id= list.get(i).get(0).toString();
			String compname=list.get(i).get(1).toString();
			String level=list.get(i).get(2).toString();
			String time=list.get(i).get(3).toString();
			String sql= "insert into TB_SCJG_DR t (t.no,t.compname,t.time,t.level1) values ('"+id+"','"+compname+"','"+time+"','"+level+"')";
			count=jdbcTemplate.update(sql);
		}
		return count;
	}

	public String show() {
		String str ="select t.* from TB_SCJG_DR t";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(str);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("datas", list);
		m.put("count", list.size());
		return jacksonUtil.toJson(m);
	}

	public int insert(HttpServletRequest request) {
		int count=0;
		String[] value =request.getParameter("value").split(";");
		for (int i = 0; i < value.length; i++) {
			String[] key=value[i].split(",");
			String compname =key[0].substring(1, key[0].length());
			String time =key[1];
			String level = key[2].substring(0, key[2].length()-1);
			String sql= "insert into hz_systems.SERVICEEVALUATION t (t.yhmc,t.sj,t.xydj) values ('"+compname+"','"+time+"','"+level+"')";
			System.out.println(sql);
			count=jdbcTemplate2.update(sql);
		}
		return count;
	}
}
