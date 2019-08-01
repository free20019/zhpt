package mvc.entity;

/**
 * 常规下线分析
 * @author sven.zhang
 *
 */
public class RegularOffline {
	/**
	 * id
	 */
	private String regularId;
	/**
	 * 企业报停数量
	 */
	private int cpNum;
	/**
	 * 设备故障数量
	 */
	private int efNum;
	
	/**
	 * 其他数量
	 */
	private int otherNum;
	/**
	 * SIM卡故障数量
	 */
	private int simNum;
	/**
	 * 合计数量
	 */
	private int total;
	/**
	 * 登记时间
	 */
	private String operatingTime;
	/**
	 * 登记人
	 */
	private String operatingUser;
	public String getRegularId() {
		return regularId;
	}
	public void setRegularId(String regularId) {
		this.regularId = regularId;
	}
	public int getCpNum() {
		return cpNum;
	}
	public void setCpNum(int cpNum) {
		this.cpNum = cpNum;
	}
	public int getEfNum() {
		return efNum;
	}
	public void setEfNum(int efNum) {
		this.efNum = efNum;
	}
	public int getOtherNum() {
		return otherNum;
	}
	public void setOtherNum(int otherNum) {
		this.otherNum = otherNum;
	}
	public int getSimNum() {
		return simNum;
	}
	public void setSimNum(int simNum) {
		this.simNum = simNum;
	}
	public int getTotal() {
		return this.simNum+this.otherNum+this.cpNum+this.efNum;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getOperatingTime() {
		return operatingTime;
	}
	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
	}
	public String getOperatingUser() {
		return operatingUser;
	}
	public void setOperatingUser(String operatingUser) {
		this.operatingUser = operatingUser;
	}
	
}
