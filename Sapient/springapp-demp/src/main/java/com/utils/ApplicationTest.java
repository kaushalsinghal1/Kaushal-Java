package com.utils;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.dto.Product;
import com.service.ProductService;

public class ApplicationTest {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductService productService = applicationContext.getBean("productService", ProductService.class);
		Product product = productService.saveProduct(generateProduct());
		System.out.println("Product " + product.getId());
		List<Product> list = productService.listProducts();
		System.out.println("list --> " + list.size());

	}

	private static Product generateProduct() {
		Product product = new Product();
		product.setName("Moto G4");
		product.setPrice(13500);
		product.setDescription("Motorolla");
		return product;
	}
}
