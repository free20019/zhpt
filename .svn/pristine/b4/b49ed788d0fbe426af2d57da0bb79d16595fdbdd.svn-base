package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.gxdcServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/gxdc")
public class gxdcAction {
	private gxdcServer gxdcServer;
	
	public gxdcServer getGxdcServer() {
		return gxdcServer;
	}
	@Autowired
	public void setGxdcServer(gxdcServer gxdcServer) {
		this.gxdcServer = gxdcServer;
	}

	@RequestMapping(value = "/gxdccx")
	@ResponseBody
	public String gxdc(@RequestParam("gs") String gs,@RequestParam("bh") String bh) {
		String msg = "ok";
		System.out.println("####");
		msg = gxdcServer.gxdc(gs,bh);
		return msg;
	}
	@RequestMapping(value = "/bal")
	@ResponseBody
	public String bal() {
		String msg = "ok";
		System.out.println("####");
		msg = gxdcServer.bal();
		return msg;
	}
	@RequestMapping(value = "/lchajd")
	@ResponseBody
	public String lchajd(@RequestParam("sjhm") String sjhm,@RequestParam("nr") String nr) {
		String msg = "ok";
		msg = gxdcServer.lchajd(sjhm,nr);
		System.out.println(msg);
		return msg;
	}
	@RequestMapping(value = "/lchajd2")
	@ResponseBody
	public String lchajd2() {
		String msg = "ok";
		msg = gxdcServer.lchajd2();
		return msg;
	}
}
