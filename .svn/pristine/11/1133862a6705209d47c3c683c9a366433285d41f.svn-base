package mvc.service;

import java.util.List;
import java.util.Map;

import mvc.dao.TbYjzhDepartment;
import mvc.dao.TbYjzhDepartmentDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhDepartmentService {

	private TbYjzhDepartmentDAO tbYjzhDepartmentDAO;

	public TbYjzhDepartmentDAO getTbYjzhDepartmentDAO() {
		return tbYjzhDepartmentDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhDepartmentDAO(TbYjzhDepartmentDAO tbYjzhDepartmentDAO) {
		this.tbYjzhDepartmentDAO = tbYjzhDepartmentDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhDepartment>> resultMap = tbYjzhDepartmentDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getAll(String parames){
		return "";
	}
	
}

