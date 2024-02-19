package org.jsp.merchantapp.exception;

import org.jsp.merchantapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MerchantAppExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> INFEhandler(IdNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Merchant Not Found");
		structure.setData(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCredetialsException.class)
	public ResponseEntity<ResponseStructure<String>> ICEhandler(InvalidCredetialsException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Merchant Not Found");
		structure.setData(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> PNFEhandler(ProductNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Product Not Found");
		structure.setData(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
