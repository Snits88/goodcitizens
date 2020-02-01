package com.goodcitizens.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CitizenGenericExpection extends GenericException {

    public CitizenGenericExpection(String errorMsg) {
        super(errorMsg);
    }

    public CitizenGenericExpection(String errorMsg, String errorCode) {
        super(errorMsg, errorCode);
    }

}
