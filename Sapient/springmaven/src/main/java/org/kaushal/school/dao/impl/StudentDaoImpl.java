package org.kaushal.school.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kaushal.school.dao.StudentDao;
import org.kaushal.school.entity.StudentEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class StudentDaoImpl implements StudentDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public StudentEntity createStudent(StudentEntity studentEntity) {
		Session session = sessionFactory.getCurrentSession();
		Integer id = (Integer) session.save(studentEntity);
		studentEntity.setId(id);
		return studentEntity;
	}

	public boolean updateStudent(StudentEntity studentEntity) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(studentEntity);
		return true;
	}

	public boolean deleteStudent(StudentEntity studentEntity) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(studentEntity);
		return true;
	}

	public List<StudentEntity> getStudentList() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(StudentEntity.class);
		return criteria.list();
	}

}
