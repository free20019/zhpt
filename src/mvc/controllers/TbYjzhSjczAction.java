package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhSjczService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/sjcz")
public class TbYjzhSjczAction {
	private TbYjzhSjczService tbYjzhSjczService;
	
    public TbYjzhSjczService getTbYjzhSjczService() {
		return tbYjzhSjczService;
	}
    @Autowired
	public void setTbYjzhSjczService(TbYjzhSjczService tbYjzhSjczService) {
		this.tbYjzhSjczService = tbYjzhSjczService;
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
    	String result = tbYjzhSjczService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

