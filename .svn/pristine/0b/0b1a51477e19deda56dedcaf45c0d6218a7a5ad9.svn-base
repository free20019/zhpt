package mvc.service;

import java.util.List;
import java.util.Map;

import mvc.dao.TbYjzhPermission;
import mvc.dao.TbYjzhPermissionDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhPermissionService {

	private TbYjzhPermissionDAO tbYjzhPermissionDAO;

	public TbYjzhPermissionDAO getTbYjzhPermissionDAO() {
		return tbYjzhPermissionDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhPermissionDAO(TbYjzhPermissionDAO tbYjzhPermissionDAO) {
		this.tbYjzhPermissionDAO = tbYjzhPermissionDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhPermission>> resultMap = tbYjzhPermissionDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getAll(String parames){
		return "";
	}
	
}

