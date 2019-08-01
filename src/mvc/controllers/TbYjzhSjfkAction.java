package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhSjfkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/yjzh/sjfk")
public class TbYjzhSjfkAction {
	private TbYjzhSjfkService tbYjzhSjfkService;
	
    public TbYjzhSjfkService getTbYjzhSjfkService() {
		return tbYjzhSjfkService;
	}
    @Autowired
	public void setTbYjzhSjfkService(TbYjzhSjfkService tbYjzhSjfkService) {
		this.tbYjzhSjfkService = tbYjzhSjfkService;
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
    	String result = tbYjzhSjfkService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

