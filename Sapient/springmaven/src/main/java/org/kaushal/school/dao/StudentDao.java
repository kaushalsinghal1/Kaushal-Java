package org.kaushal.school.dao;

import java.util.List;

import org.kaushal.school.entity.StudentEntity;

public interface StudentDao {

	StudentEntity createStudent(StudentEntity studentEntity);

	boolean updateStudent(StudentEntity studentEntity);

	boolean deleteStudent(StudentEntity studentEntity);
	List<StudentEntity> getStudentList();
}
