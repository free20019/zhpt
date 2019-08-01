package mvc.dao;

/**
 * TbYjzhUserRole entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhUserRole implements java.io.Serializable {

	// Fields

	private String id;
	private String tbId;
	private String userId;
	private String roleId;

	// Constructors

	/** default constructor */
	public TbYjzhUserRole() {
	}

	/** full constructor */
	public TbYjzhUserRole(String tbId, String userId, String roleId) {
		this.tbId = tbId;
		this.userId = userId;
		this.roleId = roleId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTbId() {
		return this.tbId;
	}

	public void setTbId(String tbId) {
		this.tbId = tbId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}