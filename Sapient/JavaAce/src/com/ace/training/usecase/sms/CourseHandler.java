package com.ace.training.usecase.sms;

import java.util.HashMap;
import java.util.Map;

public class CourseHandler {
	private Map<Integer, Course> courseMap;
	// Eager Initialization
	private static CourseHandler instanse = new CourseHandler();

	public static CourseHandler getInstanse() {
		return instanse;
	}

	private CourseHandler() {
		courseMap = new HashMap<Integer, Course>();
		buildInitialMap(courseMap);
	}

	public Course addCourse(Course course) {
		course.setId(courseMap.size() + 1);
		courseMap.put(course.getId(), course);
		return course;
	}

	public Course getCourse(int id) {
		return courseMap.get(id);
	}

	private void buildInitialMap(Map<Integer, Course> courseMap) {
		Course course = new Course();
		course.setName("Core Java");
		course.setCost(5000);
		course.setDuration(120);// days
		addCourse(course);
		course = new Course();
		course.setName("WebServices");
		course.setCost(2000);
		course.setDuration(60);// days
		addCourse(course);
		course = new Course();
		course.setName("Spring-core");
		course.setCost(2000);
		course.setDuration(60);// days
		addCourse(course);
	}

}
