import org.kaushal.school.dto.Student;

public class StudentFactory {
	public static Student createStudent(String type) {
		Student student = new Student();
		if("science".equals(type)){
			student.setStream("SCIENCE");
		} else{
			student.setStream("MATHMATICS");
		}
		return student;
	}
}
