package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhYjyaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/yjzh/yjya")
public class TbYjzhYjyaAction {
	private TbYjzhYjyaService tbYjzhYjyaService;
	
    public TbYjzhYjyaService getTbYjzhYjyaService() {
		return tbYjzhYjyaService;
	}
    @Autowired
	public void setTbYjzhYjyaService(TbYjzhYjyaService tbYjzhYjyaService) {
		this.tbYjzhYjyaService = tbYjzhYjyaService;
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
    	String result = tbYjzhYjyaService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
    /**
     * 
     * @param p parameter
     * @return
     */
    @RequestMapping(value="/getById")
    @ResponseBody
    public String getById(HttpServletRequest request,@RequestParam("id") String id) {
    	String result = tbYjzhYjyaService.getById(id);
    	System.out.println("####"+result);
    	return result;
    }
    
    @RequestMapping(value="/save")
    @ResponseBody
    public String save(HttpServletRequest request) {
    	String result = tbYjzhYjyaService.save(request);
    	System.out.println("####"+result);
    	return result;
    }
    
    @RequestMapping(value="/update")
    @ResponseBody
    public String update(HttpServletRequest request) {
    	String result = tbYjzhYjyaService.update(request);
    	System.out.println("####"+result);
    	return result;
    }
    
    @RequestMapping(value="/getAllNames")
    @ResponseBody
    public String getAllNames() {
    	String result = tbYjzhYjyaService.getAllNames();
    	System.out.println("####"+result);
    	return result;
    }
    
    @RequestMapping(value="/getContent")
    @ResponseBody
    public String getContent(HttpServletRequest request) {
    	String result = tbYjzhYjyaService.getContent(request);
    	System.out.println("####"+result);
    	return result;
    }
    
    @RequestMapping(value="/del")
    @ResponseBody
    public String del(HttpServletRequest request) {
    	String result = tbYjzhYjyaService.del(request);
    	System.out.println("####"+result);
    	return result;
    }
}

