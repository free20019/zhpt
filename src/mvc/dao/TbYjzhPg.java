package mvc.dao;

/**
 * TbYjzhPg entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhPg implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String version;
	private String item1;
	private String item2;
	private String item3;
	private String status;

	// Constructors

	/** default constructor */
	public TbYjzhPg() {
	}

	/** full constructor */
	public TbYjzhPg(String name, String version, String item1, String item2,
			String item3, String status) {
		this.name = name;
		this.version = version;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
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

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getItem1() {
		return this.item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public String getItem2() {
		return this.item2;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}

	public String getItem3() {
		return this.item3;
	}

	public void setItem3(String item3) {
		this.item3 = item3;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}