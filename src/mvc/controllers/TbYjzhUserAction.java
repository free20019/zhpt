package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class TbYjzhUserAction {
	private TbYjzhUserService tbYjzhUserService;
	
    public TbYjzhUserService getTbYjzhUserService() {
		return tbYjzhUserService;
	}
    @Autowired
	public void setTbYjzhUserService(TbYjzhUserService tbYjzhUserService) {
		this.tbYjzhUserService = tbYjzhUserService;
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
    	String result = tbYjzhUserService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    

    /**
     * 
     * @param p parameter
     * @return
     */
    @RequestMapping(value="/login")
    @ResponseBody
    public String login(HttpServletRequest request,@RequestParam("postData") String postData) {
    	String result = "1";//tbYjzhUserService.login(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
    /**
     * 
     * @param p parameter
     * @return
     */
    @RequestMapping(value="/logout")
    @ResponseBody
    public String logout(HttpServletRequest request,@RequestParam("postData") String postData) {
    	String result = tbYjzhUserService.logout(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

