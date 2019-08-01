package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhPermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/permission")
public class TbYjzhPermissionAction {
	private TbYjzhPermissionService tbYjzhPermissionService;
	
    public TbYjzhPermissionService getTbYjzhPermissionService() {
		return tbYjzhPermissionService;
	}
    @Autowired
	public void setTbYjzhPermissionService(TbYjzhPermissionService tbYjzhPermissionService) {
		this.tbYjzhPermissionService = tbYjzhPermissionService;
	}

	@RequestMapping(value="/test")
    @ResponseBody
    public String test() {
    	String msg = "ok";
    	System.out.println("####");
    	return msg;
    }
    
    /**
     * 
     * @param p parameter
     * @return
     */
    @RequestMapping(value="/get")
    @ResponseBody
    public String get(HttpServletRequest request,@RequestParam("postData") String postData) {
    	String result = tbYjzhPermissionService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

