package mvc.dao;

/**
 * TbYjzhMenu entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhMenu implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String parentid;
	private String status;

	// Constructors

	/** default constructor */
	public TbYjzhMenu() {
	}

	/** full constructor */
	public TbYjzhMenu(String name, String parentid, String status) {
		this.name = name;
		this.parentid = parentid;
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

	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}