package mvc.service;

import java.util.List;
import java.util.Map;

import mvc.dao.TbYjzhRolePermission;
import mvc.dao.TbYjzhRolePermissionDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhRolePermissionService {

	private TbYjzhRolePermissionDAO tbYjzhRolePermissionDAO;

	public TbYjzhRolePermissionDAO getTbYjzhRolePermissionDAO() {
		return tbYjzhRolePermissionDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhRolePermissionDAO(TbYjzhRolePermissionDAO tbYjzhRolePermissionDAO) {
		this.tbYjzhRolePermissionDAO = tbYjzhRolePermissionDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhRolePermission>> resultMap = tbYjzhRolePermissionDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getAll(String parames){
		return "";
	}
	
}

