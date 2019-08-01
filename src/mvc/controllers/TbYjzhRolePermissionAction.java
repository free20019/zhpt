package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhRolePermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/rolepermission")
public class TbYjzhRolePermissionAction {
	private TbYjzhRolePermissionService tbYjzhRolePermissionService;
	
    public TbYjzhRolePermissionService getTbYjzhRolePermissionService() {
		return tbYjzhRolePermissionService;
	}
    @Autowired
	public void setTbYjzhRolePermissionService(TbYjzhRolePermissionService tbYjzhRolePermissionService) {
		this.tbYjzhRolePermissionService = tbYjzhRolePermissionService;
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
    	String result = tbYjzhRolePermissionService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

