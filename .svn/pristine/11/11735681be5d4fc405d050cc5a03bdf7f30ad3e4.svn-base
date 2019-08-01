package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhUserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/userrole")
public class TbYjzhUserRoleAction {
	private TbYjzhUserRoleService tbYjzhUserRoleService;
	
    public TbYjzhUserRoleService getTbYjzhUserRoleService() {
		return tbYjzhUserRoleService;
	}
    @Autowired
	public void setTbYjzhUserRoleService(TbYjzhUserRoleService tbYjzhUserRoleService) {
		this.tbYjzhUserRoleService = tbYjzhUserRoleService;
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
    	String result = tbYjzhUserRoleService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

