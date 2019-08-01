package mvc.entity;

/**
 * 
 * @author sven.zhang 
 *	群组管理
 */
public class GroupManage {
	/**
	 * 编号
	 */
	private int number;
	/**
	 * 车牌号
	 */
	private String vehicle;
	/**
	 * 群组名
	 */
	private String groupName;
	/**
	 * 群组ID
	 */
	private String groupId;
	/**
	 * 分公司
	 */
	private String branch;
	/**
	 * 车辆颜色
	 */
	private String color;
	/**
	 * 终端类型
	 */
	private String terminalType;
	/**
	 * 车辆类型
	 */
	private String vehicleType;
	/**
	 * 群组内辆车号集合
	 */
	private String vList;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getvList() {
		return vList;
	}
	public void setvList(String vList) {
		this.vList = vList;
	}
	
	
	
}
