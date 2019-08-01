package mvc.dao;

import java.util.Date;

/**
 * TbYjzhLog entity. @author MyEclipse Persistence Tools
 */

public class TbYjzhLog implements java.io.Serializable {

	// Fields

	private String id;
	private String operator;
	private String method;
	private String content;
	private String result;
	private Date sj;
	private String object;
	private String message;

	// Constructors

	/** default constructor */
	public TbYjzhLog() {
	}

	/** full constructor */
	public TbYjzhLog(String operator, String method, String content,
			String result, Date sj, String object, String message) {
		this.operator = operator;
		this.method = method;
		this.content = content;
		this.result = result;
		this.sj = sj;
		this.object = object;
		this.message = message;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getSj() {
		return this.sj;
	}

	public void setSj(Date sj) {
		this.sj = sj;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}