package org.jsp.merchantapp.controller;

import java.util.List;

import org.jsp.merchantapp.dto.Merchant;
import org.jsp.merchantapp.dto.Product;
import org.jsp.merchantapp.dto.ResponseStructure;
import org.jsp.merchantapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	@Autowired
	private ProductService productservice;

	@PostMapping("/{merchant_id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product pr,@PathVariable (name = "merchant_id")int merchant_id) {
		return productservice.saveProduct(pr,merchant_id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product pr) {
		return productservice.updateProduct(pr);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable(name = "id") int id) {
		return productservice.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id") int id) {
		return productservice.deleteById(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		return productservice.findAll();
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByName(@PathVariable String name) {
		return productservice.findByName(name);
	}
}
