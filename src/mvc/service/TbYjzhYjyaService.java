package mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mvc.dao.TbYjzhSj;
import mvc.dao.TbYjzhYjya;
import mvc.dao.TbYjzhYjyaDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhYjyaService {

	private TbYjzhYjyaDAO tbYjzhYjyaDAO;

	public TbYjzhYjyaDAO getTbYjzhYjyaDAO() {
		return tbYjzhYjyaDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhYjyaDAO(TbYjzhYjyaDAO tbYjzhYjyaDAO) {
		this.tbYjzhYjyaDAO = tbYjzhYjyaDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhYjya>> resultMap = tbYjzhYjyaDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getById(String id){
		if(null == id || id.isEmpty()){
			return "{}";
		}
		TbYjzhYjya yjya = tbYjzhYjyaDAO.get(id);
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", yjya.getId());
		map.put("name", yjya.getName());
		map.put("content", new String(yjya.getContent()));
		return jacksonUtil.toJson(map);
	}
	
	public String save(HttpServletRequest request){
		try {
			TbYjzhYjya yjzhYjya = new TbYjzhYjya();
			yjzhYjya.setName(request.getParameter("name"));
			System.out.println(request.getParameter("content"));
			yjzhYjya.setContent(request.getParameter("content").getBytes());
			tbYjzhYjyaDAO.save(yjzhYjya);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
	
	public String update(HttpServletRequest request){
		try {
			TbYjzhYjya yjzhYjya = tbYjzhYjyaDAO.get(request.getParameter("id"));
//			yjzhYjya.setName(request.getParameter("name"));
			yjzhYjya.setContent(request.getParameter("content").getBytes());
			tbYjzhYjyaDAO.update(yjzhYjya);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
	
	public String getAllNames(){
		List list = tbYjzhYjyaDAO.getAllNames();
		System.out.println(list);
		return jacksonUtil.toJson(list);
	}
	
	public String getContent(HttpServletRequest request){
		TbYjzhYjya yjya =  tbYjzhYjyaDAO.get(request.getParameter("id"));
		String content = new String(yjya.getContent());//tbYjzhYjyaDAO.getContent(request.getParameter("id"));
		return content;
	}
	public String del(HttpServletRequest request){
		TbYjzhYjya entity = tbYjzhYjyaDAO.get(request.getParameter("id"));
		try {
			tbYjzhYjyaDAO.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
}

