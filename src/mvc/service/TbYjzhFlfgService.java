package mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mvc.dao.TbYjzhSj;
import mvc.dao.TbYjzhFlfg;
import mvc.dao.TbYjzhFlfgDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhFlfgService {

	private TbYjzhFlfgDAO tbYjzhFlfgDAO;

	public TbYjzhFlfgDAO getTbYjzhFlfgDAO() {
		return tbYjzhFlfgDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhFlfgDAO(TbYjzhFlfgDAO tbYjzhFlfgDAO) {
		this.tbYjzhFlfgDAO = tbYjzhFlfgDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhFlfg>> resultMap = tbYjzhFlfgDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getById(String id){
		if(null == id || id.isEmpty()){
			return "{}";
		}
		TbYjzhFlfg Flfg = tbYjzhFlfgDAO.get(id);
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", Flfg.getId());
		map.put("name", Flfg.getName());
		map.put("content", new String(Flfg.getContent()));
		return jacksonUtil.toJson(map);
	}
	
	public String save(HttpServletRequest request){
		try {
			TbYjzhFlfg yjzhFlfg = new TbYjzhFlfg();
			yjzhFlfg.setName(request.getParameter("name"));
			System.out.println(request.getParameter("content"));
			yjzhFlfg.setContent(request.getParameter("content").getBytes());
			tbYjzhFlfgDAO.save(yjzhFlfg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
	
	public String update(HttpServletRequest request){
		try {
			TbYjzhFlfg yjzhFlfg = tbYjzhFlfgDAO.get(request.getParameter("id"));
//			yjzhFlfg.setName(request.getParameter("name"));
			yjzhFlfg.setContent(request.getParameter("content").getBytes());
			tbYjzhFlfgDAO.update(yjzhFlfg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
	
	public String getAllNames(){
		List list = tbYjzhFlfgDAO.getAllNames();
		System.out.println(list);
		return jacksonUtil.toJson(list);
	}
	
	public String getContent(HttpServletRequest request){
		TbYjzhFlfg Flfg =  tbYjzhFlfgDAO.get(request.getParameter("id"));
		String content = new String(Flfg.getContent());//tbYjzhFlfgDAO.getContent(request.getParameter("id"));
		return content;
	}
	public String del(HttpServletRequest request){
		TbYjzhFlfg entity = tbYjzhFlfgDAO.get(request.getParameter("id"));
		try {
			tbYjzhFlfgDAO.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
}

