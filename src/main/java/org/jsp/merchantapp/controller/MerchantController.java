package org.jsp.merchantapp.controller;

import java.util.List;
import org.jsp.merchantapp.dto.Merchant;
import org.jsp.merchantapp.dto.ResponseStructure;
import org.jsp.merchantapp.service.MerchantService;
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
@RequestMapping(value = "/merchants")
public class MerchantController {
	@Autowired
	private MerchantService merchantservice;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseStructure<Merchant> saveMerchant(@RequestBody Merchant merchant) {
		return merchantservice.saveMerchant(merchant);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Merchant>> update(@RequestBody Merchant merchant) {
		return merchantservice.update(merchant);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable(name = "id") int id) {
		return merchantservice.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id") int id) {
		return merchantservice.deleteById(id);
	}

	@PostMapping(value = "/verify-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam long phone,
			@RequestParam String password) {
		return merchantservice.verifyMerchant(phone, password);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Merchant>>> findAll() {
		return merchantservice.findAll();
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(@PathVariable String name) {
		return merchantservice.findByName(name);
	}
}
