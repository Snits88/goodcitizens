package com.goodcitizens.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class ErrorTO {

	private HttpStatus status;
    @JsonProperty("msg")
    private String message = "a problem is occurred";
    @JsonProperty("code")
    private String errorCode = "000-000-000";
    private List<Object> errors;

    public ErrorTO(HttpStatus status, List<Object> errors) {
        super();
        this.setStatus(status);
        this.setErrors(errors);
    }
 
    public ErrorTO(HttpStatus status, Object error) {
        super();
        this.setStatus(status);
        setErrors(Arrays.asList(error));
    }
    
    
    public ErrorTO(HttpStatus status, String message, List<Object> errors) {
        super();
        this.setStatus(status);
        this.message = message;
        this.setErrors(errors);
    }
 
    public ErrorTO(HttpStatus status, String message, Object error) {
        super();
        this.setStatus(status);
        this.message = message;
        setErrors(Arrays.asList(error));
    }
    
    public ErrorTO(HttpStatus status, String message, String errorCode, List<Object> errors) {
        super();
        this.setStatus(status);
        this.message = message;
        this.errorCode = errorCode;
        this.setErrors(errors);
    }
 
    public ErrorTO(HttpStatus status, String message, String errorCode, Object error) {
        super();
        this.setStatus(status);
        this.message = message;
        this.errorCode = errorCode;
        setErrors(Arrays.asList(error));
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }
}
