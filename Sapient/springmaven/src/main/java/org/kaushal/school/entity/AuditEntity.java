package org.kaushal.school.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COM_AUDIT")
public class AuditEntity extends EntitySuper {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE2")
	@SequenceGenerator(name = "SEQUENCE2", sequenceName = "SEQUENCE2", allocationSize = 1)
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

	@Override
	public int getPrimaryKey() {
		return id;
	}
}
