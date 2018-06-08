package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.ProductDao;
import kr.ac.hansung.cse.model.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDao productdao;

	public Product getProductById(int id) {

		return productdao.getProductById(id);
	}

	public List<Product> getProducts() {
		return productdao.getProducts();
	}
	
	public void addProduct(Product product) {

		productdao.addProduct(product);
	}
	/*public boolean addProduct(Product product) {

		return productdao.addProduct(product);
	}*/ //jdbc

	public void deleteProduct(Product product) {

		productdao.deleteProduct(product);
	}
	/*public boolean deleteProduct(int id) {

		return productdao.deleteProduct(id);
	}*/ //jdbc

	public void updateProduct(Product product) {

		productdao.updateProduct(product);
	}
/*	public boolean updateProduct(Product product) {

		return productdao.updateProduct(product);
	}*/ //jdbc

}
