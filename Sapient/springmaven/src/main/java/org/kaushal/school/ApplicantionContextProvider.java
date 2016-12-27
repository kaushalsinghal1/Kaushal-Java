package org.kaushal.school;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicantionContextProvider implements ApplicationContextAware {
	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext context) throws BeansException {

		this.context = context;
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static <T> T getBean(Class<T> classType) {
		return context.getBean(classType);
	}
	
	public static <T> T getBean(String name, Class<T> classType) {
		return context.getBean(name, classType);
	}

}
