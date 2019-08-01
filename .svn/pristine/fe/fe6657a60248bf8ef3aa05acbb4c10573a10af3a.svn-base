package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhInfoLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/infolog")
public class TbYjzhInfoLogAction {
	private TbYjzhInfoLogService tbYjzhInfoLogService;
	
    public TbYjzhInfoLogService getTbYjzhInfoLogService() {
		return tbYjzhInfoLogService;
	}
    @Autowired
	public void setTbYjzhInfoLogService(TbYjzhInfoLogService tbYjzhInfoLogService) {
		this.tbYjzhInfoLogService = tbYjzhInfoLogService;
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
    	String result = tbYjzhInfoLogService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

