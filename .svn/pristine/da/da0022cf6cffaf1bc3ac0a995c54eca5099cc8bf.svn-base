package mvc.service;

import java.util.List;
import java.util.Map;

import mvc.dao.TbYjzhUserRole;
import mvc.dao.TbYjzhUserRoleDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhUserRoleService {

	private TbYjzhUserRoleDAO tbYjzhUserRoleDAO;

	public TbYjzhUserRoleDAO getTbYjzhUserRoleDAO() {
		return tbYjzhUserRoleDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhUserRoleDAO(TbYjzhUserRoleDAO tbYjzhUserRoleDAO) {
		this.tbYjzhUserRoleDAO = tbYjzhUserRoleDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhUserRole>> resultMap = tbYjzhUserRoleDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getAll(String parames){
		return "";
	}
	
}

