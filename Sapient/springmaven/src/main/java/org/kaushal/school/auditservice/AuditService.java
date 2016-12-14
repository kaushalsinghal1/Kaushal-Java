package org.kaushal.school.auditservice;

import java.util.ArrayList;
import java.util.List;

import org.kaushal.school.dao.AuditDao;
import org.kaushal.school.dto.AuditDTO;
import org.kaushal.school.entity.AuditEntity;
import org.modelmapper.ModelMapper;

public class AuditService {
	private AuditDao auditDao;
	private ModelMapper modelMapper;

	public void setAuditDao(AuditDao auditDao) {
		this.auditDao = auditDao;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public AuditDTO createAudit(AuditDTO auditDTO) {
		AuditEntity auditEntity = convertToEntity(auditDTO);
		auditEntity = auditDao.createAudit(auditEntity);
		return convertToDto(auditEntity);

	}

	public AuditDTO getById(int id) {
		AuditEntity auditEntity = auditDao.getById(id);
		return convertToDto(auditEntity);
	}

	public List<AuditDTO> getByEntityType(String entityName) {
		List<AuditEntity> entities = auditDao.getByEntityType(entityName);
		List<AuditDTO> list = new ArrayList<AuditDTO>(entities.size());
		for (AuditEntity entity : entities) {
			list.add(convertToDto(entity));
		}
		return list;
	}

	private AuditEntity convertToEntity(AuditDTO auditDTO) {
		AuditEntity auditEntity = modelMapper.map(auditDTO, AuditEntity.class);
		return auditEntity;

	}

	private AuditDTO convertToDto(AuditEntity auditEntity) {
		AuditDTO auditDTO = modelMapper.map(auditEntity, AuditDTO.class);
		return auditDTO;
	}
}
