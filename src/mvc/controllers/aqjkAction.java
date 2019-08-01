package mvc.controllers;

import mvc.service.aqjkService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/aqjk")
public class aqjkAction {
	private aqjkService aqjkService;

	public aqjkService getAqjkService() {
		return aqjkService;
	}

	public void setAqjkService(aqjkService aqjkService) {
		this.aqjkService = aqjkService;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findvehiall")
	@ResponseBody
	public String findvehiall() {
		String msg = "ok";
		System.out.println("####");
		// msg = aqjkService.findvehiall();
		return msg;
	}
}
