package mvc.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mvc.service.TlqkServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/clqk")
public class TlqkAction {
	private TlqkServer tlqkService;

	public TlqkServer getTlqkServer() {
		return tlqkService;
	}

	@Autowired
	public void setTlqkServer(TlqkServer tlqkService) {
		this.tlqkService = tlqkService;
	}

	/**
	 * 所有车辆查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findvehiall")
	@ResponseBody
	public String findvehiall() {
		String msg = "ok";
		System.out.println("####");
		msg = tlqkService.findvehiall();
		return msg;
	}

	/**
	 * 查询公司
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findcomp")
	@ResponseBody
	public String findcomp() {
		String msg = "ok";
		msg = tlqkService.findcomp();
		return msg;
	}

	/**
	 * 根据公司id查询车号
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findvhic")
	@ResponseBody
	public String findvhic(@RequestParam("id") String id) {
		String msg = "ok";
		msg = tlqkService.findvhic(id);
		return msg;
	}

	/**
	 * 信息下发区域
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qyadd")
	@ResponseBody
	public String qyadd(@RequestParam("postData") String postData) {
		String msg = "ok";
		msg = tlqkService.qyadd(postData);
		return msg;
	}
	/**
	 * 信息区域
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qycx")
	@ResponseBody
	public String qycx() {
		String msg = tlqkService.qycx();
		return msg;
	}
	
	@RequestMapping(value = "/client")
	@ResponseBody
	public String client(HttpServletRequest request,@RequestParam("postData") String postData){
		System.out.println(postData);
		String info="";
		int a=tlqkService.client(request,postData);
		if(a!=0){
			info="发送成功";
		}else{
			info="发送失败";
		}
		return "{'info':'"+info+"'}";
	}
	/**
	 * 轨迹
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findGj")
	@ResponseBody
	public String findGj(@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = tlqkService.findGj(postData);
		return msg;
	}

	/**
	 * 一小时未营运
	 * 
	 * @return
	 */
	@RequestMapping(value = "/yxswyy")
	@ResponseBody
	public String yxswyy() {
		System.out.println("####");
		String msg = tlqkService.yxswyy();
		return msg;
	}

	/**
	 * 上线率
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sxl")
	@ResponseBody
	public String sxl() {
		String msg = "ok";
		System.out.println("####");
		msg = tlqkService.sxl();
		return msg;
	}
	
	
	/**
	 * 疑似停运
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ysty")
	@ResponseBody
	public String ysty() {
		String msg = "ok";
		System.out.println("####");
		msg = tlqkService.ysty();
		return msg;
	}


	/**
	 * 未营运车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/wyy")
	@ResponseBody
	public String cljkDel() {
		List<Map<String, Object>> msg;
		System.out.println("####");
		msg = tlqkService.findweiyingyun24(0);
		return msg.toString();
	}
	
	
	/**
	 * 在线营运
	 * 
	 * @return
	 */
	@RequestMapping(value = "/zxyy")
	@ResponseBody
	public String zzyy() {
		String msg = "ok";
		System.out.println("####");
		msg= tlqkService.zxyy();
		return msg;
	}

	/**
	 * 查询区域
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findArea")
	@ResponseBody
	public String cljkLsgj() {
		String msg = "ok";
		msg = tlqkService.findArea();
		return msg;
	}

	/**
	 * 增加区域
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addArea")
	@ResponseBody
	public String addArea(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String info = "";
		int msg = tlqkService.addArea(postData);
		if (msg != 0) {
			info = "添加成功";
		} else {
			info = "添加失败";
		}
		return "{'info':'" + info + "'}";
	}

	/**
	 * 重点监控区域车辆数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findzdqu")
	@ResponseBody
	public String findzdqu() {

		String msg = tlqkService.findzdqu();
		return msg;
	}
	/**
	 * 修改区域
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editAreaID")
	@ResponseBody
	public String editAreaID(HttpServletRequest request,
			@RequestParam("areaid") String areaid) {
		String msg = tlqkService.editAreaID(areaid);
		return msg;
	}
	/**
	 * 修改区域
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editArea")
	@ResponseBody
	public String editArea(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String info = "";
		int msg = tlqkService.editArea(postData);
		if (msg != 0) {
			info = "修改成功";
		} else {
			info = "修改失败";
		}
		return "{'info':'" + info + "'}";
	}

	/**
	 * 删除区域
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delArea")
	@ResponseBody
	public String delArea(@RequestParam("areaid") String areaid) {
		String info = "";
		int msg = tlqkService.delArea(areaid);
		if (msg != 0) {
			info = "删除成功";
		} else {
			info = "删除失败";
		}
		return "{'info':'" + info + "'}";
	}

	/**
	 * 
	 * 
	 * 综合监控
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findvehi")
	@ResponseBody
	public String findvehi(@RequestParam("vehino") String vehino) {
		String msg = "ok";
		msg = tlqkService.findvehi(vehino);
		return msg;
	}
	
	/**
	 * 
	 * 区域监控
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qyjk")
	@ResponseBody
	public String qyjk() {
		String msg = "ok";
		msg = tlqkService.qyjk();
		return msg;
	}
	/**
	 * 
	 * 根据前台车牌查询车号
	 * 
	 * @return
	 */
	@RequestMapping(value = "/onevhic")
	@ResponseBody
	public String findonevhic(@RequestParam("vehino") String vehino) {
		String msg = "ok";
		if(vehino.length()>2){
			msg = tlqkService.findonevhic(vehino);
		}else{
			msg="{'datas':[{}]}";
		}
		
		return msg;
	}
	
	/**
	 * 绕道
	 * 
	 * @return
	 */
	@RequestMapping(value = "/raodao")
	@ResponseBody
	public String raodao(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		msg = tlqkService.raodao(postData);
		return msg;
	}
	/**
	 * 计价器异常
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jjqyc")
	@ResponseBody
	public String jjqyc(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		msg = tlqkService.jjqyc(postData);
		return msg;
	}
	@RequestMapping("/clgz")
	@ResponseBody
	public String clgz(@RequestParam("vhic") String vhic){
		String msg = tlqkService.clgz(vhic);
		return msg;
	}
}
