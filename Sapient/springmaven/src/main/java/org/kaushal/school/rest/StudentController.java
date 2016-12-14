package org.kaushal.school.rest;

import java.util.List;

import org.kaushal.school.dto.Student;
import org.kaushal.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Student createStudent(@RequestParam("nanme") String name) {
		return studentService.createStudent(buildDummyStudent(name));
	}

	private Student buildDummyStudent(String name) {
		Student student = new Student();
		student.setName(name == null ? "kaushal" : name);
		student.setAddress("Marathalli");
		student.setCity("Bangalore");
		return student;
	}
}
