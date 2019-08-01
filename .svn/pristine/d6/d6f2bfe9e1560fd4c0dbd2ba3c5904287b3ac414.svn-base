package mvc.service;

import java.util.List;
import java.util.Map;

import mvc.dao.TbYjzhYjzs;
import mvc.dao.TbYjzhYjzsDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhYjzsService {

	private TbYjzhYjzsDAO tbYjzhYjzsDAO;

	public TbYjzhYjzsDAO getTbYjzhYjzsDAO() {
		return tbYjzhYjzsDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhYjzsDAO(TbYjzhYjzsDAO tbYjzhYjzsDAO) {
		this.tbYjzhYjzsDAO = tbYjzhYjzsDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhYjzs>> resultMap = tbYjzhYjzsDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getAll(String parames){
		return "";
	}
	
}

