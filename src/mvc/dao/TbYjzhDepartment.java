package mvc.dao;

/**
 * TbYjzhDepartment entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhDepartment implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String status;

	// Constructors

	/** default constructor */
	public TbYjzhDepartment() {
	}

	/** full constructor */
	public TbYjzhDepartment(String name, String status) {
		this.name = name;
		this.status = status;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}