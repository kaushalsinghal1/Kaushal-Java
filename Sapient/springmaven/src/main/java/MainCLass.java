import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.kaushal.school.dto.Student;
import org.kaushal.school.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainCLass {
	public static void main(String[] args) {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");

//		Connection connection = null;
//
//		try {
//
//			connection = DriverManager.getConnection(
//					"jdbc:postgresql://127.0.0.1:5432/spring_mvc", "postgres",
//					"postgres");
//
//		} catch (SQLException e) {
//
//			System.out.println("Connection Failed! Check output console");
//			e.printStackTrace();
//			return;
//
//		}
//
//		if (connection != null) {
//			System.out.println("You made it, take control your database now!");
//		} else {
//			System.out.println("Failed to make connection!");
//		}
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
		StudentService service = applicationContext.getBean("studentService", StudentService.class);
		service.createStudent(buildDummyStudent());

	}

	private static Student buildDummyStudent() {
		Student student = new Student();
		student.setName("kaushal");
		student.setAddress("Marathalli");
		student.setCity("Bangalore");
		return student;
	}
}
