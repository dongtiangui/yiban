package com.yiban.yiban_application.javabean;

import java.io.Serializable;
public class SysLog implements Serializable {

	private static final long serialVersionUID = -8878596941954995444L;

	private Long id;

	private String username;

	private String operation;

	private Long time;

	private String method;

	private String params;

	private String ip;

	private String createTime;

	private String location;
	
	// 用于搜索条件中的时间字段
	private String timeField;

	/**
	 * @return ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return USERNAME
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * @return OPERATION
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 */
	public void setOperation(String operation) {
		this.operation = operation == null ? null : operation.trim();
	}

	/**
	 * @return TIME
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * @param time
	 */
	public void setTime(Long time) {
		this.time = time;
	}

	/**
	 * @return METHOD
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 */
	public void setMethod(String method) {
		this.method = method == null ? null : method.trim();
	}

	/**
	 * @return PARAMS
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params
	 */
	public void setParams(String params) {
		this.params = params == null ? null : params.trim();
	}

	/**
	 * @return IP
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	/**
	 * @return CREATE_TIME
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTimeField() {
		return timeField;
	}

	public void setTimeField(String timeField) {
		this.timeField = timeField;
	}
	

}