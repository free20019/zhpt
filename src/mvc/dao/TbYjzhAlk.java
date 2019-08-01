package mvc.dao;

import java.util.Date;

/**
 * TbYjzhAlk entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhAlk implements java.io.Serializable {

	// Fields

	private String id;
	private Date sj;
	private String name;
	private byte[] content;

	// Constructors

	/** default constructor */
	public TbYjzhAlk() {
	}

	/** full constructor */
	public TbYjzhAlk(Date sj, String name, byte[] content) {
		this.sj = sj;
		this.name = name;
		this.content = content;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] bs) {
		this.content = bs;
	}

}