package mvc.service;

import java.util.List;
import java.util.Map;

import mvc.dao.TbYjzhInfoLog;
import mvc.dao.TbYjzhInfoLogDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhInfoLogService {

	private TbYjzhInfoLogDAO tbYjzhInfoLogDAO;

	public TbYjzhInfoLogDAO getTbYjzhInfoLogDAO() {
		return tbYjzhInfoLogDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhInfoLogDAO(TbYjzhInfoLogDAO tbYjzhInfoLogDAO) {
		this.tbYjzhInfoLogDAO = tbYjzhInfoLogDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhInfoLog>> resultMap = tbYjzhInfoLogDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getAll(String parames){
		return "";
	}
	
}

