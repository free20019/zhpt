package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhWzService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/yjzh/wz")
public class TbYjzhWzAction {
	private TbYjzhWzService tbYjzhWzService;
	
    public TbYjzhWzService getTbYjzhWzService() {
		return tbYjzhWzService;
	}
    @Autowired
	public void setTbYjzhWzService(TbYjzhWzService tbYjzhWzService) {
		this.tbYjzhWzService = tbYjzhWzService;
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
    	String result = tbYjzhWzService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

