package com.goodcitizens.service.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.goodcitizens.to.ErrorTO;


@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<ErrorTO> createErrorTO(HttpStatus status, String msg, Object[] args, String erroCode) {
		List<Object> o = null;
		if (args != null) {
			o = Arrays.asList(args);
		}
		ErrorTO errorTO;
		if(erroCode != null) {
			errorTO = new ErrorTO(status, msg, erroCode, o);
		}else {
			errorTO = new ErrorTO(status, msg, o);
		}
		ResponseEntity<ErrorTO> responseEntity = new ResponseEntity<>(errorTO, status);
		return responseEntity;
	}
	
}
