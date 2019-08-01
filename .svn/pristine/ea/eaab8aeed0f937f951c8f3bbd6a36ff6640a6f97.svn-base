package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhZbryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/yjzh/zbry")
public class TbYjzhZbryAction {
	private TbYjzhZbryService tbYjzhZbryService;
	
    public TbYjzhZbryService getTbYjzhZbryService() {
		return tbYjzhZbryService;
	}
    @Autowired
	public void setTbYjzhZbryService(TbYjzhZbryService tbYjzhZbryService) {
		this.tbYjzhZbryService = tbYjzhZbryService;
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
    	String result = tbYjzhZbryService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
//    @RequestMapping(value="/save")
//    @ResponseBody
//    public String save(HttpServletRequest request,@RequestParam("postData") String postData) {
//    	String result = tbYjzhZbryService.save(postData);
//    	System.out.println("####"+result);
//    	return result;
//    }
//    
//    @RequestMapping(value="/del")
//    @ResponseBody
//    public String del(HttpServletRequest request,@RequestParam("postData") String postData) {
//    	String result = tbYjzhZbryService.delete(postData);
//    	System.out.println("####"+result);
//    	return result;
//    }
}

