package mvc.dao;

/**
 * TbYjzhYjzs entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhYjzs implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String miaoshu;

	// Constructors

	/** default constructor */
	public TbYjzhYjzs() {
	}

	/** full constructor */
	public TbYjzhYjzs(String name, String miaoshu) {
		this.name = name;
		this.miaoshu = miaoshu;
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

	public String getMiaoshu() {
		return this.miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

}