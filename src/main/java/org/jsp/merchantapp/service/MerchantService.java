package org.jsp.merchantapp.service;

import java.util.List;
import java.util.Optional;
import org.jsp.merchantapp.dao.MerchantDao;
import org.jsp.merchantapp.dto.Merchant;
import org.jsp.merchantapp.dto.ResponseStructure;
import org.jsp.merchantapp.exception.IdNotFoundException;
import org.jsp.merchantapp.exception.InvalidCredetialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantdao;

	public ResponseStructure<Merchant> saveMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<Merchant>();
		structure.setMessage("Merchant Saved");
		structure.setData(merchantdao.saveMerchant(merchant));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<Merchant>> update(Merchant merchant) {
		Optional<Merchant> recMerchant = merchantdao.findById(merchant.getId());
		ResponseStructure<Merchant> structure = new ResponseStructure<Merchant>();
		if (recMerchant.isPresent()) {
			Merchant dbMerchant = new Merchant();
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPassword(merchant.getPassword());
			structure.setMessage("Merchant updatated");
			structure.setData(merchantdao.saveMerchant(merchant));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
		} else {
			throw new IdNotFoundException("Cant Update Merchant as id is Invalid");
		}
	}

	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		Optional<Merchant> recMerchant = merchantdao.findById(id);
		ResponseStructure<Merchant> structure = new ResponseStructure<Merchant>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException("Cant Find Merchant as id is Invalid");
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		Optional<Merchant> recMerchant = merchantdao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			merchantdao.deleteById(id);
			structure.setMessage("Merchant  Found");
			structure.setData("Merchant Deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setMessage("Merchant Not Found");
		structure.setData("Merchant Can't Delete as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone, String password) {
		Optional<Merchant> recMerchant = merchantdao.verifyByPhone(phone, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<Merchant>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Verified");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredetialsException("Invalid phone or password");
	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findAll() {
		List<Merchant> recMerchant = merchantdao.findAll();
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<List<Merchant>>();
		if (recMerchant.size() > 0) {
			structure.setMessage("Merchant Found");
			structure.setData(merchantdao.findAll());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredetialsException("No Merchants in Database or Database is empty");
	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(String name) {
		List<Merchant> recMerchants = merchantdao.findByName(name);
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<List<Merchant>>();
		if (recMerchants.size() > 0) {
			structure.setMessage("Merchant Lists");
			structure.setData(merchantdao.findByName(name));
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredetialsException("Invalid Name or No Merchants Found Your Entered Name");
	}
}
