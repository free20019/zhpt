package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/log")
public class TbYjzhLogAction {
	private TbYjzhLogService tbYjzhLogService;
	
    public TbYjzhLogService getTbYjzhLogService() {
		return tbYjzhLogService;
	}
    @Autowired
	public void setTbYjzhLogService(TbYjzhLogService tbYjzhLogService) {
		this.tbYjzhLogService = tbYjzhLogService;
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
    	String result = tbYjzhLogService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

