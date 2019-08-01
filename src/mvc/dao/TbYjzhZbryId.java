package mvc.dao;

/**
 * TbYjzhZbryId entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhZbryId implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String tel;
	private String zw;

	// Constructors

	/** default constructor */
	public TbYjzhZbryId() {
	}

	/** full constructor */
	public TbYjzhZbryId(String id, String name, String tel, String zw) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.zw = zw;
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

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZw() {
		return this.zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbYjzhZbryId))
			return false;
		TbYjzhZbryId castOther = (TbYjzhZbryId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getTel() == castOther.getTel()) || (this.getTel() != null
						&& castOther.getTel() != null && this.getTel().equals(
						castOther.getTel())))
				&& ((this.getZw() == castOther.getZw()) || (this.getZw() != null
						&& castOther.getZw() != null && this.getZw().equals(
						castOther.getZw())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getTel() == null ? 0 : this.getTel().hashCode());
		result = 37 * result + (getZw() == null ? 0 : this.getZw().hashCode());
		return result;
	}

}