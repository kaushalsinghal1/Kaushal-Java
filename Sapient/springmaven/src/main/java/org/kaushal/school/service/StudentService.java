package org.kaushal.school.service;

import java.util.ArrayList;
import java.util.List;

import org.kaushal.school.dao.StudentDao;
import org.kaushal.school.dto.Student;
import org.kaushal.school.entity.StudentEntity;
import org.modelmapper.ModelMapper;

public class StudentService {
	private StudentDao studentDao;
	private ModelMapper modelMapper;

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Student createStudent(Student student) {
		StudentEntity entity = convertToEntity(student);
		entity = studentDao.createStudent(entity);
		return convertToDto(entity);
	}
	
	public List<Student> getStudents() {
		List<StudentEntity> entities = studentDao.getStudentList();
		List<Student> list = new ArrayList<Student>(entities.size());
		for(StudentEntity entity : entities){
			list.add(convertToDto(entity));
		}
		return list;
	}


	public boolean updateStudent(Student student) {
		StudentEntity entity = convertToEntity(student);
		return studentDao.updateStudent(entity);
	}

	public boolean deleteStudent(Student student) {
		StudentEntity entity = convertToEntity(student);
		return studentDao.deleteStudent(entity);
	}

	private StudentEntity convertToEntity(Student student) {
		StudentEntity studentEntity = modelMapper.map(student, StudentEntity.class);
		return studentEntity;

	}

	private Student convertToDto(StudentEntity studentEntity) {
		Student student = modelMapper.map(studentEntity, Student.class);
		return student;
	}

}
