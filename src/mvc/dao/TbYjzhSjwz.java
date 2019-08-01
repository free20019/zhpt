package mvc.dao;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TbYjzhSjwz entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhSjwz implements java.io.Serializable {

	// Fields

	private String id;
	private String sjid;
	private String wzid;
	private String lx;
	private Date sj;
	private BigDecimal sl;

	// Constructors

	/** default constructor */
	public TbYjzhSjwz() {
	}

	/** full constructor */
	public TbYjzhSjwz(String sjid, String wzid, String lx, Date sj,
			BigDecimal sl) {
		this.sjid = sjid;
		this.wzid = wzid;
		this.lx = lx;
		this.sj = sj;
		this.sl = sl;
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

	public String getWzid() {
		return this.wzid;
	}

	public void setWzid(String wzid) {
		this.wzid = wzid;
	}

	public String getLx() {
		return this.lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public Date getSj() {
		return this.sj;
	}

	public void setSj(Date sj) {
		this.sj = sj;
	}

	public BigDecimal getSl() {
		return this.sl;
	}

	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}

}