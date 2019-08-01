package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/menu")
public class TbYjzhMenuAction {
	private TbYjzhMenuService tbYjzhMenuService;
	
    public TbYjzhMenuService getTbYjzhMenuService() {
		return tbYjzhMenuService;
	}
    @Autowired
	public void setTbYjzhMenuService(TbYjzhMenuService tbYjzhMenuService) {
		this.tbYjzhMenuService = tbYjzhMenuService;
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
    	String result = tbYjzhMenuService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

