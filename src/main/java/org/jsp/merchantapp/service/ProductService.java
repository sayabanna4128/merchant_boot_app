package org.jsp.merchantapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantapp.dao.MerchantDao;
import org.jsp.merchantapp.dao.ProductDao;
import org.jsp.merchantapp.dto.Merchant;
import org.jsp.merchantapp.dto.Product;
import org.jsp.merchantapp.dto.ResponseStructure;
import org.jsp.merchantapp.exception.IdNotFoundException;
import org.jsp.merchantapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productdao;
	@Autowired
	private MerchantDao merchantdao;
	//Save Product
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id) {
		Optional<Merchant> recMerchant = merchantdao.findById(merchant_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			merchant.getProduct().add(product);
			product.setMerchant(merchant);
			structure.setData(productdao.saveProduct(product));
			structure.setMessage("Product Saved");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException("Invalid Merchant Id");
	}
	//Update Product
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product pr) {
		Optional<Product> recProduct = productdao.findById(pr.getId());
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			Product p = new Product();
			p.setName(pr.getName());
			p.setBrand(pr.getBrand());
			p.setCategory(pr.getCategory());
			p.setCategory(pr.getCategory());
			p.setDescription(pr.getDescription());
			p.setImage_url(pr.getImage_url());
			structure.setMessage("Product Updated");
			structure.setData(productdao.saveProduct(pr));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		structure.setMessage("Cant Update Product");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
	}
	//Find Product By ID
	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		Optional<Product> recProduct = productdao.findById(id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			structure.setMessage("Product Found");
			structure.setData(recProduct.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Product Id");
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		Optional<Product> recProduct = productdao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			structure.setMessage("Product Found");
			structure.setData("Product Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setMessage("Product Not Found");
		structure.setData("Cant Delete Merchant as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		List<Product> recProduct = productdao.findAll();
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (recProduct.size() > 0) {
			structure.setMessage("Product Found");
			structure.setData(productdao.findAll());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		structure.setMessage("Product Not Found");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByName(String name) {
		List<Product> recProducts = productdao.findByName(name);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (recProducts.size() > 0) {
			structure.setMessage("Product Found");
			structure.setData(productdao.findByName(name));
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.FOUND);
		}
		structure.setMessage("Product Found");
		structure.setData(null);
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.NOT_FOUND);
	}
}
