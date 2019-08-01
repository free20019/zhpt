package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhYjclService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/yjzh/yjcl")
public class TbYjzhYjclAction {
	private TbYjzhYjclService tbYjzhYjclService;
	
    public TbYjzhYjclService getTbYjzhYjclService() {
		return tbYjzhYjclService;
	}
    @Autowired
	public void setTbYjzhYjclService(TbYjzhYjclService tbYjzhYjclService) {
		this.tbYjzhYjclService = tbYjzhYjclService;
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
    	String result = tbYjzhYjclService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

