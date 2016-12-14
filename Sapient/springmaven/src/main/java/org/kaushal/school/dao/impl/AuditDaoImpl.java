package org.kaushal.school.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.kaushal.school.dao.AuditDao;
import org.kaushal.school.entity.AuditEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AuditDaoImpl implements AuditDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public AuditEntity createAudit(AuditEntity auditEntity) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(auditEntity);
		auditEntity.setId(id);
		return auditEntity;
	}

	public AuditEntity getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(AuditEntity.class, id);
	}

	public List<AuditEntity> getByEntityType(String entity) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AuditEntity.class);
		criteria.add(Restrictions.eq("entity", entity));
		return criteria.list();
	}

}
