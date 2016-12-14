package org.kaushal.school.dao;

import java.util.List;

import org.kaushal.school.entity.AuditEntity;

public interface AuditDao {
	AuditEntity createAudit(AuditEntity auditEntity);
	AuditEntity getById(int id);
	List<AuditEntity> getByEntityType(String entity);
	
}
