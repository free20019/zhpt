package mvc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import mvc.dao.TbYjzhSj;
import mvc.dao.TbYjzhSjDAO;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helper.JacksonUtil;

@Service
public class TbYjzhSjService {

	private TbYjzhSjDAO tbYjzhSjDAO;

	public TbYjzhSjDAO getTbYjzhSjDAO() {
		return tbYjzhSjDAO;
	}
	
	private JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();
	@Autowired
	public void setTbYjzhSjDAO(TbYjzhSjDAO tbYjzhSjDAO) {
		this.tbYjzhSjDAO = tbYjzhSjDAO;
	}
	
	public String get(String postData){
		Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
		int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
		int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
		paramMap.remove("page");
		paramMap.remove("pageSize");
		Map<String, List<TbYjzhSj>> resultMap = tbYjzhSjDAO.getAllPage(paramMap,page, pageSize);
		return jacksonUtil.toJson(resultMap);
	}
	
	public String getAll(String parames){
		return "";
	}

	public String save(String postData) {
		try {
//			Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
//			String sj = String.valueOf(paramMap.get("form_sj"));
//			String address = String.valueOf(paramMap.get("form_address"));
//			String lb = String.valueOf(paramMap.get("form_lb"));
//			String miaoshu = String.valueOf(paramMap.get("form_miaoshu"));
			TbYjzhSj yjzhSj = jacksonUtil.toObject(postData, TbYjzhSj.class);
//			yjzhSj.setAddress(address);
//			yjzhSj.setLb(lb);
//			yjzhSj.setMiaoshu(miaoshu);
			yjzhSj.setStatus("未审核");
//			yjzhSj.setSj(new Date());
			tbYjzhSjDAO.save(yjzhSj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String delete(String postData) {
		try {
			Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
			String id = String.valueOf(paramMap.get("id"));
			TbYjzhSj entity = tbYjzhSjDAO.get(id);
			tbYjzhSjDAO.delete(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{}";
	}

	public String heshi(String postData) {
		try {
			Map<String,Object> paramMap = jacksonUtil.toObject(postData,new TypeReference<Map<String,Object>>() {});
			String id = String.valueOf(paramMap.get("id"));
			TbYjzhSj entity = tbYjzhSjDAO.get(id);
			entity.setStatus("已审核");
			tbYjzhSjDAO.update(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{}";
	}
	
}

