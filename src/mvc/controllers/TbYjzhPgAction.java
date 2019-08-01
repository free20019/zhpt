package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TbYjzhPgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/yjzh/pg")
public class TbYjzhPgAction {
	private TbYjzhPgService tbYjzhPgService;
	
    public TbYjzhPgService getTbYjzhPgService() {
		return tbYjzhPgService;
	}
    @Autowired
	public void setTbYjzhPgService(TbYjzhPgService tbYjzhPgService) {
		this.tbYjzhPgService = tbYjzhPgService;
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
    	String result = tbYjzhPgService.get(postData);
    	System.out.println("####"+result);
    	return result;
    }
    
}

