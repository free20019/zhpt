package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/yjzh/info")
public class TbYjzhInfoAction {
	private TbYjzhInfoService tbYjzhInfoService;
	
    public TbYjzhInfoService getTbYjzhInfoService() {
		return tbYjzhInfoService;
	}
    @Autowired
	public void setTbYjzhInfoService(TbYjzhInfoService tbYjzhInfoService) {
		this.tbYjzhInfoService = tbYjzhInfoService;
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
    	String result = tbYjzhInfoService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

