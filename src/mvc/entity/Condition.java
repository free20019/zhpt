package mvc.entity;

import java.io.Serializable;
/**
 * 
 * @author sven.zhang
 *	查询条件封装类
 */
public class Condition implements Serializable{
	private static final  long serialVersionUID = 12436542236624565L;
	/**
	 * 公司
	 */
	private String company;
	/**
	 * 分公司
	 */
	private String branch;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 车号
	 */
	private String cardNo;
	/**
	 * 资格证号
	 */
	private String certNo;
	/**
	 * 群组
	 */
	private String group;
	//终端编号
	private String mdtno;
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getMdtno() {
		return mdtno;
	}
	public void setMdtno(String mdtno) {
		this.mdtno = mdtno;
	}
	
}
