package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhSjService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 接入
 * @author 16f3
 *
 */
@Controller
@RequestMapping(value="/yjzh/sj")
public class TbYjzhSjAction {
	private TbYjzhSjService tbYjzhSjService;
	
    public TbYjzhSjService getTbYjzhSjService() {
		return tbYjzhSjService;
	}
    @Autowired
	public void setTbYjzhSjService(TbYjzhSjService tbYjzhSjService) {
		this.tbYjzhSjService = tbYjzhSjService;
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
    	String result = tbYjzhSjService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
    @RequestMapping(value="/save")
    @ResponseBody
    public String save(HttpServletRequest request,@RequestParam("postData") String postData) {
    	String result = tbYjzhSjService.save(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
    @RequestMapping(value="/del")
    @ResponseBody
    public String del(HttpServletRequest request,@RequestParam("postData") String postData) {
    	String result = tbYjzhSjService.delete(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
    /**
     * 核实
     */
    @RequestMapping(value="/heshi")
    @ResponseBody
    public String heshi(HttpServletRequest request,@RequestParam("postData") String postData) {
    	String result = tbYjzhSjService.heshi(postData);
    	System.out.println("####"+result);
    	return result;
    }
}

