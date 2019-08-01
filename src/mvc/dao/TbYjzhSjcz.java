package mvc.dao;

import java.util.Date;

/**
 * TbYjzhSjcz entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhSjcz implements java.io.Serializable {

	// Fields

	private String id;
	private String tbId;
	private String sjid;
	private String yjids;
	private String node1;
	private Date time1;
	private String status;
	private String node2;
	private Date time2;

	// Constructors

	/** default constructor */
	public TbYjzhSjcz() {
	}

	/** full constructor */
	public TbYjzhSjcz(String tbId, String sjid, String yjids, String node1,
			Date time1, String status, String node2, Date time2) {
		this.tbId = tbId;
		this.sjid = sjid;
		this.yjids = yjids;
		this.node1 = node1;
		this.time1 = time1;
		this.status = status;
		this.node2 = node2;
		this.time2 = time2;
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

	public String getSjid() {
		return this.sjid;
	}

	public void setSjid(String sjid) {
		this.sjid = sjid;
	}

	public String getYjids() {
		return this.yjids;
	}

	public void setYjids(String yjids) {
		this.yjids = yjids;
	}

	public String getNode1() {
		return this.node1;
	}

	public void setNode1(String node1) {
		this.node1 = node1;
	}

	public Date getTime1() {
		return this.time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNode2() {
		return this.node2;
	}

	public void setNode2(String node2) {
		this.node2 = node2;
	}

	public Date getTime2() {
		return this.time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}

}