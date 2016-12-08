package com.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.Product;
import com.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductRest {

	private ProductService productService;

	@RequestMapping("/products")
	public List<Product> productList() {
		return productService.listProducts();

	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
