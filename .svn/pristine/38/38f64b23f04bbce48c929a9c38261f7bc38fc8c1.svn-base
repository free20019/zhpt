package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhYjryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/yjzh/yjry")
public class TbYjzhYjryAction {
	private TbYjzhYjryService tbYjzhYjryService;
	
    public TbYjzhYjryService getTbYjzhYjryService() {
		return tbYjzhYjryService;
	}
    @Autowired
	public void setTbYjzhYjryService(TbYjzhYjryService tbYjzhYjryService) {
		this.tbYjzhYjryService = tbYjzhYjryService;
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
    	String result = tbYjzhYjryService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

