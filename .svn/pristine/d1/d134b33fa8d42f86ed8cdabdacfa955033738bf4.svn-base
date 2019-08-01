package mvc.controllers;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mvc.service.QxglServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/qxgl")
public class QxglAction {
	private QxglServer qxglService;

	public QxglServer getQxglServer() {
		return qxglService;
	}

	@Autowired
	public void setQxglServer(QxglServer qxglService) {
		this.qxglService = qxglService;
	}

	/**
	 * 查询部门
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qxglcheckbm")
	@ResponseBody
	public String qxglcheckbm(HttpServletRequest request) {
		String msg = "ok";
		msg = qxglService.qxglcheckbm(request);
		return msg;
	}
	/**
	 * 显示管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/xsgl")
	@ResponseBody
	public String xsgl(HttpServletRequest request) {
		String msg = "ok";
		msg = qxglService.xsgl(request);
		return msg;
	}
	
	
	
	/**
	 * 查询角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qxglcheckjs")
	@ResponseBody
	public String qxglcheckjs(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
		String msg = "ok";
		msg = qxglService.qxglcheckjs(postData);
		System.out.println(msg);
		return msg;
	}
	/**
	 * 删除角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delJs")
	@ResponseBody
	public String delJs(HttpServletRequest request,
			@RequestParam("id") String id) {
		String info="";
		int a=qxglService.delJs(id);
		if(a!=0){
			info="删除成功";
		}else{
			info="删除失败";
		}
		return "{'info':'"+info+"'}";
		
	}
	/**
	 * 添加角色
	 * 
	 * @return
	 */
	@RequestMapping("/addJs")
	@ResponseBody
	public String addJs(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String info="";
		int a=qxglService.addJs(postData);
		if(a!=0){
			info="添加成功";
		}else{
			info="添加失败";
		}
		return "{'info':'"+info+"'}";
	}
	/**
	 * 修改角色
	 * 
	 * @return
	 */
	@RequestMapping("/editJs")
	@ResponseBody
	public String editJs(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String info="";
		int a=qxglService.editJs(postData);
		if(a!=0){
			info="修改成功";
		}else{
			info="修改失败";
		}
		return "{'info':'"+info+"'}";
	}

	
	/**
	 * 查询岗位
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qxglcheckgw")
	@ResponseBody
	public String qxglcheckgw(HttpServletRequest request,
    		@RequestParam("postData") String postData) {
		String msg = "ok";
		msg = qxglService.qxglcheckgw(postData);
		return msg;
	}
	/**
	 * 删除岗位
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delGw")
	@ResponseBody
	public String delGw(HttpServletRequest request,
			@RequestParam("id") String id) {
		String info="";
		int a=qxglService.delGw(id);
		if(a!=0){
			info="删除成功";
		}else{
			info="删除失败";
		}
		return "{'info':'"+info+"'}";
		
	}
	/**
	 * 添加角色
	 * 
	 * @return
	 */
	@RequestMapping("/addGw")
	@ResponseBody
	public String addGw(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String info="";
		int a=qxglService.addGw(postData);
		if(a!=0){
			info="添加成功";
		}else{
			info="添加失败";
		}
		return "{'info':'"+info+"'}";
	}
	/**
	 * 修改角色
	 * 
	 * @return
	 */
	@RequestMapping("/editGw")
	@ResponseBody
	public String editGw(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String info="";
		int a=qxglService.editGw(postData);
		if(a!=0){
			info="修改成功";
		}else{
			info="修改失败";
		}
		return "{'info':'"+info+"'}";
	}
	
	/**
	 * 岗位树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gwTree")
	@ResponseBody
	public String gwTree(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = "ok";
		msg = qxglService.gwTree(postData);
		return msg;
	}

	/**
	 * 岗位下拉
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gwxl")
	@ResponseBody
	public String gwxl() {
		String msg = "ok";
		msg = qxglService.gwxl();
		return msg;
	}
	
}
