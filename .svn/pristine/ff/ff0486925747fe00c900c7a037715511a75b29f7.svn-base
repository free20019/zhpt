package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhSjwzService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/yjzh/sjwz")
public class TbYjzhSjwzAction {
	private TbYjzhSjwzService tbYjzhSjwzService;
	
    public TbYjzhSjwzService getTbYjzhSjwzService() {
		return tbYjzhSjwzService;
	}
    @Autowired
	public void setTbYjzhSjwzService(TbYjzhSjwzService tbYjzhSjwzService) {
		this.tbYjzhSjwzService = tbYjzhSjwzService;
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
    	String result = tbYjzhSjwzService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

