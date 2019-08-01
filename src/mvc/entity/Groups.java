package mvc.entity;
/**
 * 群组
 * @author sven.zhnag
 *
 */
public class Groups {
	/**
	 * 群组名
	 */
	private String groupName;
	/**
	 * 群组ID
	 */
	private String id;
	/**
	 * 车号
	 */
	private String groupVhic;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupVhic() {
		return groupVhic;
	}
	public void setGroupVhic(String groupVhic) {
		this.groupVhic = groupVhic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
