package com.goodcitizens.configuration;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.exception.CitizenNotFoundExpection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.goodcitizens.to.ErrorTO;

@ControllerAdvice
public class CitizenCustomRestExceptionHandler extends CustomRestExceptionHandler {

	@ExceptionHandler(CitizenGenericExpection.class)
	public final ResponseEntity<ErrorTO> handleAddressGenericException(CitizenGenericExpection age,
			WebRequest request) {
		return createErrorTO(HttpStatus.BAD_REQUEST, age.getMessage(), age.getParameters(), age.getErrorCode());
	}

	@ExceptionHandler(CitizenNotFoundExpection.class)
	public final ResponseEntity<ErrorTO> handleAddressNotFoundException(CitizenNotFoundExpection age,
			WebRequest request) {
		return createErrorTO(HttpStatus.NOT_FOUND, age.getMessage(), age.getParameters(), age.getErrorCode());
	}

}
