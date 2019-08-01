package mvc.dao;

import java.util.Date;

/**
 * TbYjzhInfo entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhInfo implements java.io.Serializable {

	// Fields

	private String id;
	private Date sj;
	private String content;
	private String status;

	// Constructors

	/** default constructor */
	public TbYjzhInfo() {
	}

	/** full constructor */
	public TbYjzhInfo(Date sj, String content, String status) {
		this.sj = sj;
		this.content = content;
		this.status = status;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getSj() {
		return this.sj;
	}

	public void setSj(Date sj) {
		this.sj = sj;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}