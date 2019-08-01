package mvc.service;

import java.util.List;
import java.util.Map;

import mvc.dao.TbYjzhUser;
import mvc.dao.TbYjzhUserDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhUserService {

	private TbYjzhUserDAO tbYjzhUserDAO;

	public TbYjzhUserDAO getTbYjzhUserDAO() {
		return tbYjzhUserDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhUserDAO(TbYjzhUserDAO tbYjzhUserDAO) {
		this.tbYjzhUserDAO = tbYjzhUserDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhUser>> resultMap = tbYjzhUserDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getAll(String parames){
		return "";
	}

	public String logout(String postData) {
		// TODO Auto-generated method stub
		return null;
	}

	public String login(String postData) {
		boolean result = tbYjzhUserDAO.login();
		return null;
	}
	
}

