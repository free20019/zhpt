package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.CommonService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/common")
public class CommonAction {
	private CommonService commonService;
	
    public CommonService getCommonService() {
		return commonService;
	}
    @Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	@RequestMapping(value="/findcomp")
    @ResponseBody
    public String findcomp() {
    	String msg = "ok";
    	msg = commonService.findcomp();
    	return msg;
    }
	@RequestMapping("/findVhicAll")
    @ResponseBody
    public String findVhicAll() {
    	String msg = "ok";
    	msg = commonService.findVhicAll();
    	return msg;
    }
	@RequestMapping("/findvhic")
    @ResponseBody
    public String findvhic(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
    	String msg = "ok";
    	msg = commonService.findvhic(postData);
    	return msg;
    }
	@RequestMapping("/findcardno")
	@ResponseBody
	public String findcardno(HttpServletRequest request,
			@RequestParam("postData") String postData){
		String msg=commonService.findcardno(postData);
		return msg;
	}
	@RequestMapping("/findarea")
	@ResponseBody
	public String findarea(){
		String msg=commonService.findarea();
		return msg;
	}
	
	@RequestMapping("/sendInfo")
	@ResponseBody
	public String sendInfo(@RequestParam("tels") String tels,@RequestParam("message") String message){
		String msg=commonService.sendInfo(tels, message);
		return msg;
	}
	@RequestMapping("/sendMail")
	@ResponseBody
	public String sendMail(@RequestParam("mail") String mail,@RequestParam("message") String message){
		String msg=commonService.sendMail(mail, message);
		return msg;
	}
	@RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request,
    		@RequestParam("postData") String postData){
    	String msg=commonService.login(request,postData);
    	return msg;
    }
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request){
    	String msg=commonService.logout(request);
    	return msg;
    }
    @RequestMapping("/hkvideo")
    @ResponseBody
    public String hkvideo(HttpServletRequest request){
    	JSONArray jsonArray = commonService.findcamera();
    	JSONObject hksp = new JSONObject(); 
		hksp.put("id", "hksp");
		hksp.put("pId", "");
		hksp.put("name", "海康视频");
		hksp.put("isParent",true);
		hksp.put("open",true);
		jsonArray.add(hksp);
		String cars = jsonArray.toString();
		return cars;
    }
    /*
     * 数据中心公司车辆
     */
    @RequestMapping(value="/find_sjzx_comp")
    @ResponseBody
    public String find_sjzx_comp() {
    	String msg = "ok";
    	msg = commonService.find_sjzx_comp();
    	return msg;
    }
	@RequestMapping("/find_sjzx_VhicAll")
    @ResponseBody
    public String find_sjzx_VhicAll() {
    	String msg = "ok";
    	msg = commonService.find_sjzx_VhicAll();
    	return msg;
    }
	
	@RequestMapping("/find_sjzx_Vhics")
    @ResponseBody
    public String find_sjzx_Vhics(@RequestParam("cphm") String cphm) {
    	String msg = "ok";
    	msg = commonService.find_sjzx_Vhics(cphm);
    	return msg;
    }
	@RequestMapping("/find_sjzx_vhic")
    @ResponseBody
    public String find_sjzx_vhic(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
    	String msg = "ok";
    	msg = commonService.find_sjzx_vhic(postData);
    	return msg;
    }
	@RequestMapping("/hzclcx")
    @ResponseBody
    public String hzclcx(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
    	String msg = "ok";
    	msg = commonService.hzclcx(postData);
    	return msg;
    }
	@RequestMapping("/sjzxclcx")
    @ResponseBody
    public String sjzxclcx(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
    	String msg = "ok";
    	msg = commonService.sjzxclcx(postData);
    	return msg;
    }
}
