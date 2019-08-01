package mvc.dao;

import java.util.Date;

/**
 * TbYjzhSjfk entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhSjfk implements java.io.Serializable {

	// Fields

	private String id;
	private String sjid;
	private Date sj;
	private String nr;

	// Constructors

	/** default constructor */
	public TbYjzhSjfk() {
	}

	/** full constructor */
	public TbYjzhSjfk(String sjid, Date sj, String nr) {
		this.sjid = sjid;
		this.sj = sj;
		this.nr = nr;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSjid() {
		return this.sjid;
	}

	public void setSjid(String sjid) {
		this.sjid = sjid;
	}

	public Date getSj() {
		return this.sj;
	}

	public void setSj(Date sj) {
		this.sj = sj;
	}

	public String getNr() {
		return this.nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

}