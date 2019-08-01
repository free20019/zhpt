package mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mvc.dao.TbYjzhSj;
import mvc.dao.TbYjzhAlk;
import mvc.dao.TbYjzhAlkDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhAlkService {

	private TbYjzhAlkDAO tbYjzhAlkDAO;

	public TbYjzhAlkDAO getTbYjzhAlkDAO() {
		return tbYjzhAlkDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhAlkDAO(TbYjzhAlkDAO tbYjzhAlkDAO) {
		this.tbYjzhAlkDAO = tbYjzhAlkDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhAlk>> resultMap = tbYjzhAlkDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getById(String id){
		if(null == id || id.isEmpty()){
			return "{}";
		}
		TbYjzhAlk Alk = tbYjzhAlkDAO.get(id);
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", Alk.getId());
		map.put("name", Alk.getName());
		map.put("content", new String(Alk.getContent()));
		return jacksonUtil.toJson(map);
	}
	
	public String save(HttpServletRequest request){
		try {
			TbYjzhAlk yjzhAlk = new TbYjzhAlk();
			yjzhAlk.setName(request.getParameter("name"));
			System.out.println(request.getParameter("content"));
			yjzhAlk.setContent(request.getParameter("content").getBytes());
			tbYjzhAlkDAO.save(yjzhAlk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
	
	public String update(HttpServletRequest request){
		try {
			TbYjzhAlk yjzhAlk = tbYjzhAlkDAO.get(request.getParameter("id"));
//			yjzhAlk.setName(request.getParameter("name"));
			yjzhAlk.setContent(request.getParameter("content").getBytes());
			tbYjzhAlkDAO.update(yjzhAlk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
	
	public String getAllNames(){
		List list = tbYjzhAlkDAO.getAllNames();
		System.out.println(list);
		return jacksonUtil.toJson(list);
	}
	
	public String getContent(HttpServletRequest request){
		TbYjzhAlk Alk =  tbYjzhAlkDAO.get(request.getParameter("id"));
		String content = new String(Alk.getContent());//tbYjzhAlkDAO.getContent(request.getParameter("id"));
		return content;
	}
	public String del(HttpServletRequest request){
		TbYjzhAlk entity = tbYjzhAlkDAO.get(request.getParameter("id"));
		try {
			tbYjzhAlkDAO.delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}
}

