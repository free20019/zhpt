package mvc.service;

import java.util.List;
import java.util.Map;

import mvc.dao.TbYjzhWz;
import mvc.dao.TbYjzhWzDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhWzService {

	private TbYjzhWzDAO tbYjzhWzDAO;

	public TbYjzhWzDAO getTbYjzhWzDAO() {
		return tbYjzhWzDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhWzDAO(TbYjzhWzDAO tbYjzhWzDAO) {
		this.tbYjzhWzDAO = tbYjzhWzDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhWz>> resultMap = tbYjzhWzDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getAll(String parames){
		return "";
	}
	
}

