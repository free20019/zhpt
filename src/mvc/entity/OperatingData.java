package mvc.entity;

import java.io.Serializable;

/**
 * 
 * @author sven.zhang
 *	营运数据统计相关查询等实体类
 */
public class OperatingData implements Serializable {
	private static final  long serialVersionUID = 1243654223662458L;
	/**
	 * 编号
	 */
	private int number;
	/**
	 * 资格证号
	 */
	private String certNo;
	/**
	 * 群组
	 */
	private String group;
	/**
	 * 车牌号
	 */
	private String vhic;
	/**
	 * 总金额
	 */
	private float money;
	/**
	 * 公司
	 */	
	private String company;
	/**
	 * 分公司
	 */
	private String branch;
	/**
	 * 空驶
	 */
	private float empty;
	/**
	 * 等候时间
	 */
	private double waitTime;
	/**
	 * 计程
	 */
	private float distance;
	/**
	 * 营运时间
	 */
	private double timeOut;
	/**
	 * 交易次数
	 */
	private int times;
	/**
	 * 车辆总数
	 */
	private int total;
	/**
	 * 在运营的车辆数
	 */
	private int driving;
	/**
	 * 总里程
	 */
	private String  totalDis;
	/**
	 * 实载率
	 */
	private String ypercent;
	/**
	 * 出车率
	 */	
	private String vpercent;
	/**
	 * 
	 * 交易类型
	 */
	private String type;
	/**
	 * 上车时间
	 */
	private String upTaxi;
	/**
	 * 下车时间
	 */
	private String downTaxi;
	
	
	public String getUpTaxi() {
		return upTaxi;
	}
	public void setUpTaxi(String upTaxi) {
		this.upTaxi = upTaxi;
	}
	public String getDownTaxi() {
		return downTaxi;
	}
	public void setDownTaxi(String downTaxi) {
		this.downTaxi = downTaxi;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getDriving() {
		return driving;
	}
	public void setDriving(int driving) {
		this.driving = driving;
	}
	public double getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(double timeOut) {
		this.timeOut = timeOut;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public double getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(double waitTime) {
		this.waitTime = waitTime;
	}
	public float getEmpty() {
		return empty;
	}
	public void setEmpty(float empty) {
		this.empty = empty;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getVhic() {
		return vhic;
	}
	public void setVhic(String vhic) {
		this.vhic = vhic;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
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
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getTotalDis() {
		return totalDis;
	}
	public void setTotalDis(String totalDis) {
		this.totalDis = totalDis;
	}
	public String getYpercent() {
		return ypercent;
	}
	public void setYpercent(String ypercent) {
		this.ypercent = ypercent;
	}
	public String getVpercent() {
		return vpercent;
	}
	public void setVpercent(String vpercent) {
		this.vpercent = vpercent;
	}
	
	
}
