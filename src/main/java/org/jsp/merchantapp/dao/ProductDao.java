package org.jsp.merchantapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantapp.dto.Product;
import org.jsp.merchantapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productrepo;

	public Product saveProduct(Product pr) {
		return productrepo.save(pr);
	}

	public Optional<Product> findById(int id) {
		return productrepo.findById(id);
	}

	public List<Product> findAll() {
		return productrepo.findAll();
	}

	public boolean deleteById(int id) {
		Optional<Product> recProduct = productrepo.findById(id);
		if (recProduct.isPresent()) {
			productrepo.delete(recProduct.get());
			return true;
		}
		return false;
	}

	public List<Product> findByName(String name) {
		return productrepo.findByName(name);
	}
}
