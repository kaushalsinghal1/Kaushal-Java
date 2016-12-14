package org.kaushal.school.dto;

import java.util.Date;

public class AuditDTO {
	private int id;
	private String entity;
	private String operation;
	private Integer primaryKeyEntity;
	private Date opTime;
	private boolean status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Integer getPrimaryKeyEntity() {
		return primaryKeyEntity;
	}
	public void setPrimaryKeyEntity(Integer primaryKeyEntity) {
		this.primaryKeyEntity = primaryKeyEntity;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
