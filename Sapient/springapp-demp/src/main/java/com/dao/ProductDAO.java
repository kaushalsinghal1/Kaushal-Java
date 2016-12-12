package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.ProductEntity;

public class ProductDAO {

	private SessionFactory sessionFactory;

	public ProductEntity saveProduct(ProductEntity product) {
		
		Session session = sessionFactory.getCurrentSession();
	//	org.hibernate.Transaction transaction = session.beginTransaction();
		Integer id = (Integer) session.save(product);
		product.setId(id);
		//session.flush();
		//transaction.commit();
		return product;

	}

	public ProductEntity updateProduct(ProductEntity product) {
		Session session = sessionFactory.getCurrentSession();
		return (ProductEntity) session.merge(product);
	}

	public boolean deleteProduct(ProductEntity product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		return true;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<ProductEntity> listProducts(int limit){
		if(limit<1){
			limit =10;
		}
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ProductEntity.class);
		return criteria.list();
	}
}
