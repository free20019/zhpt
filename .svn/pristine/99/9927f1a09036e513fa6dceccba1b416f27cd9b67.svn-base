package mvc.service;

import java.util.List;
import java.util.Map;

import mvc.dao.TbYjzhZbry;
import mvc.dao.TbYjzhZbryDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhZbryService {

	private TbYjzhZbryDAO tbYjzhZbryDAO;

	public TbYjzhZbryDAO getTbYjzhZbryDAO() {
		return tbYjzhZbryDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhZbryDAO(TbYjzhZbryDAO tbYjzhZbryDAO) {
		this.tbYjzhZbryDAO = tbYjzhZbryDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhZbry>> resultMap = tbYjzhZbryDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getAll(String parames){
		return "";
	}

	public String save(String postData) {
		// TODO Auto-generated method stub
		return null;
	}

	public String delete(String postData) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

