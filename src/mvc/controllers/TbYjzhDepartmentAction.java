package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/department")
public class TbYjzhDepartmentAction {
	private TbYjzhDepartmentService tbYjzhDepartmentService;
	
    public TbYjzhDepartmentService getTbYjzhDepartmentService() {
		return tbYjzhDepartmentService;
	}
    @Autowired
	public void setTbYjzhDepartmentService(TbYjzhDepartmentService tbYjzhDepartmentService) {
		this.tbYjzhDepartmentService = tbYjzhDepartmentService;
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
    	String result = tbYjzhDepartmentService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

