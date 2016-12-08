package com.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductDAO;
import com.dto.Product;
import com.entity.ProductEntity;

@Transactional
public class ProductService {
	private ProductDAO productDAO;
	private ModelMapper modelMapper;

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Product saveProduct(Product product) {
		ProductEntity entity = convertToEntity(product);
		ProductEntity product2 = productDAO.saveProduct(entity);
		return convertToDto(product2);

	}

	public Product updateProduct(Product product) {
		ProductEntity entity = convertToEntity(product);
		ProductEntity product2 = productDAO.updateProduct(entity);
		return convertToDto(product2);
	}

	public boolean deleteProduct(Product product) {
		ProductEntity entity = convertToEntity(product);
		return productDAO.deleteProduct(entity);
	}

	public List<Product> listProducts() {
		List<ProductEntity> list = productDAO.listProducts(10);
		List<Product> lProducts = new ArrayList<Product>(list.size());
		for (ProductEntity entity : list) {
			lProducts.add(convertToDto(entity));
		}
		return lProducts;
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	private ProductEntity convertToEntity(Product product) {
		ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
		return productEntity;

	}

	private Product convertToDto(ProductEntity productEntity) {
		Product product = modelMapper.map(productEntity, Product.class);
		return product;
	}
}
