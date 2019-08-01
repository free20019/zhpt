package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.CommonService;
import mvc.service.JyxxServer;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/jyxx")
public class JyxxAction {
	private JyxxServer jyxxServer;


	public JyxxServer getJyxxServer() {
		return jyxxServer;
	}

	@Autowired
	public void setJyxxServer(JyxxServer jyxxServer) {
		this.jyxxServer = jyxxServer;
	}


	/**
	 * 流程监控查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findzfb")
	@ResponseBody
	public String findzfb(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		msg=jyxxServer.findjyxx(postData);
		return msg;
	}

	/**
	 * 信息报送
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findxxbs")
	@ResponseBody
	public String findxxbs() {
		String msg = "ok";
		msg=jyxxServer.findxxbs();
		return msg;
	}
	/**
	 * 应急事件接入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jr")
	@ResponseBody
	public String lcjkCx(@RequestParam("sjzt") String sjzt) {
		String msg = "ok";
		System.out.println("####");
		msg=jyxxServer.fingyjsjjr(sjzt);
		return msg;
	}
	/**
	 * 应急事件接入保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jrsave")
	@ResponseBody
	public String jrsave(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
		String msg = "ok";
		msg=jyxxServer.addyjsjjr(postData);
		return msg;
	}
	/**
	 * 应急事件接入删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jrdel")
	@ResponseBody
	public String jrdel(HttpServletRequest request,
    		@RequestParam("id") String id) {
		String msg = "ok";
		msg=jyxxServer.delyjsjjr(id);
		return msg;
	}
	/**
	 * 应急事件接入修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jredit")
	@ResponseBody
	public String jredit(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
		String msg = "ok";
		msg=jyxxServer.edityjsjjr(postData);
		return msg;
	}
	/**
	 * 应急事件接入核实
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jrhs")
	@ResponseBody
	public String jrhs(HttpServletRequest request,
    		@RequestParam("id") String id) {
		String msg = "ok";
		msg=jyxxServer.hsyjsjjr(id);
		return msg;
	}
	/**
	 * 查询今天当月全部的支付宝交易金额
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findnowmonthall")
	@ResponseBody
	public String findnowmonthall() {
		String msg = "ok";
		msg=jyxxServer.findnowmonthall();
		return msg;
	}
	/**
	 * 值班查询
	 */
	@RequestMapping(value = "/findzbb")
	@ResponseBody
	public String findzbb() {
		String msg = "ok";
		msg=jyxxServer.findzbb();
		return msg;
	}
	/**
	 * 查询车号公司
	 */
	@RequestMapping(value = "/findchgs")
	@ResponseBody
	public String findchgs(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
		String msg = "ok";
		msg=jyxxServer.findchgs(postData);
		return msg;
	}
	/**
	 * 查询公司
	 */
	@RequestMapping(value = "/findcomp")
	@ResponseBody
	public String findcomp() {
		String msg = "ok";
		msg=jyxxServer.findcomp();
		return msg;
	}
	
	/**
	 * 查询人
	 */
	@RequestMapping(value = "/findname")
	@ResponseBody
	public String findname() {
		String msg = "ok";
		msg=jyxxServer.findname();
		return msg;
	}
	/**
	 * 视频
	 */
	@RequestMapping(value = "/findvideo")
	@ResponseBody
	public String findvideo(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
		String msg = "ok";
		msg=jyxxServer.findvideo(postData);
		return msg;
	}
}
