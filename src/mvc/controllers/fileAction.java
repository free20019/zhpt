package mvc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.service.fileServer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/excle")
public class fileAction {
	private fileServer fileServer;
	private ExcelUtil excelUtil=new ExcelUtil();
	public fileServer fileServer() {
		return fileServer;
	}

	@Autowired
	public void setfileServer(fileServer fileServer) {
		this.fileServer = fileServer;
	}
	
	/**
	 * 导入信息
	 * 
	 * @return
	 */	
	@RequestMapping(value="/upload")
    @ResponseBody
    public String upload(HttpServletRequest request,HttpServletResponse response){
    	try{
            if (ServletFileUpload.isMultipartContent(request)) {
            	 ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
                 response.setContentType("application/json");
                 List<FileItem> items = uploadHandler.parseRequest(request);
                     for (FileItem item : items) {
                    	 List<List<Object>> list = excelUtil.readExcel(item);
                    	 if(list==null){
                    		 return "请导入正确的Excel！";
                    	 }else{
                    		 System.out.println(list);
                    		 fileServer.upload(list);
                    	 }
                     } 
             }	 
         } catch (Exception e) {
        	 return "{\"code\":400,\"data\":\""+e.getMessage()+"\"}";
         } finally { 
         }
            return "导入成功！";
	}
	
	/**
	 * 显示
	 * 
	 * @return
	 */
	@RequestMapping(value = "/show")
	@ResponseBody
	public String show(HttpServletRequest request) {
		String msg = "ok";
		msg = fileServer.show();
		System.out.println(msg);
		return msg;
	}
	/**
	 * 显示
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insert")
	@ResponseBody
	public int insert(HttpServletRequest request) {
		int msg = 0;
		msg = fileServer.insert(request);
		System.out.println(msg);
		return msg;
	}
}