package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhYjzsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/yjzh/yjzs")
public class TbYjzhYjzsAction {
	private TbYjzhYjzsService tbYjzhYjzsService;
	
    public TbYjzhYjzsService getTbYjzhYjzsService() {
		return tbYjzhYjzsService;
	}
    @Autowired
	public void setTbYjzhYjzsService(TbYjzhYjzsService tbYjzhYjzsService) {
		this.tbYjzhYjzsService = tbYjzhYjzsService;
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
    	String result = tbYjzhYjzsService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

