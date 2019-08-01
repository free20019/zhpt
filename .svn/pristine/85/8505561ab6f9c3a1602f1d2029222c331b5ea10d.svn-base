package mvc.controllers;

import javax.servlet.http.HttpServletRequest;

import mvc.service.CommonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/yjzh")
public class yjzhAction {
	private CommonService commonService;
	public CommonService getCommonService() {
		return commonService;
	}

	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}


	/**
	 * 流程监控查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/lcjkCx")
	@ResponseBody
	public String lcjkCx() {
		String msg = "ok";
		System.out.println("####");
		msg=commonService.lcjkCx();
		return msg;
	}

	/**
	 * 流程监控查询 明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/lcjkCxmx")
	@ResponseBody
	public String lcjkCxmx(@RequestParam("id") String id) {
		String msg = "ok";
		msg = commonService.lcjkCxmx(id);
		return msg;
	}

	/**
	 * 流程监控修改 地址
	 * 
	 * @return
	 */
	@RequestMapping(value = "/lcjkXg")
	@ResponseBody
	public String lcjkXg(@RequestParam("id") String id,@RequestParam("address") String address) {
		String msg = "ok";
		System.out.println("####");
		msg=commonService.lcjkXg(id,address);
		return msg;
	}

	/**
	 * 车辆监控查询，返回车辆列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cljkCx")
	@ResponseBody
	public String cljkCx() {
		String msg = "ok";
		System.out.println("####");
		msg = commonService.cljkCx();
		return msg;
	}

	/**
	 * 车辆 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cljkAdd")
	@ResponseBody
	public String cljkAdd(@RequestParam("vehicleNo") String vehicleNo) {
		String msg = "ok";
		System.out.println("####");
		msg = commonService.cljkAdd(vehicleNo);
		return msg;
	}

	/**
	 * 车辆 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cljkDel")
	@ResponseBody
	public String cljkDel(@RequestParam("vehicleNo") String vehicleNo) {
		String msg = "ok";
		System.out.println("####");
		msg = commonService.cljkDel(vehicleNo);
		return msg;
	}

	/**
	 * 车辆监控查询，返回指定车辆时间范围内的历史轨迹
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cljkLsgj")
	@ResponseBody
	public String cljkLsgj(@RequestParam("vehicleNo") String vehicleNo) {
		String msg = "ok";
		msg = commonService.cljkLsgj(vehicleNo);
		return msg;
	}
	/**
	 * 
	 * 根据前台车牌查询车号
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findclsj")
	@ResponseBody
	public String findclsj(@RequestParam("info") String info) {
		String msg = "ok";
		if(info.length()>2){
			msg = commonService.findclsj(info);
		}else{
			msg="{'datas':[{}]}";
		}
		
		return msg;
	}
	/**
	 * 
	 * 根据前台车牌查询车号
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findjtsj")
	@ResponseBody
	public String findjtsj() {
		String msg = "ok";
			msg = commonService.findjtsj();
		return msg;
	}
	/**
	 * 查询最近的应急事件是否在一周之内
	 */
	@RequestMapping(value = "/findyjsj")
	@ResponseBody
	public String findyjsj() {
		String msg = "ok";
		msg = commonService.findyjsj();
		return msg;
	}
	/**
	 * 将应急事件的车辆存入表中
	 */
	@RequestMapping(value = "/yjsjclsave")
	@ResponseBody
	public String yjsjclsave(@RequestParam("postData") String postData) {
		String msg = "ok";
		System.out.println("####");
		msg = commonService.yjsjclsave(postData);
		return msg;
	}
}
