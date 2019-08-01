package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/role")
public class TbYjzhRoleAction {
	private TbYjzhRoleService tbYjzhRoleService;
	
    public TbYjzhRoleService getTbYjzhRoleService() {
		return tbYjzhRoleService;
	}
    @Autowired
	public void setTbYjzhRoleService(TbYjzhRoleService tbYjzhRoleService) {
		this.tbYjzhRoleService = tbYjzhRoleService;
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
    	String result = tbYjzhRoleService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

