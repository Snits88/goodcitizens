package com.goodcitizens.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Object[] parameters;
	private String errorCode;

	public GenericException(String errorMsg) {
		super(errorMsg);
	}
	
	public GenericException(String errorMsg, String errorCode) {
		this(errorMsg);
		this.errorCode = errorCode;
	}
	
	public GenericException(String errorMsg, Object[] parameters) {
		this(errorMsg);
		this.parameters = parameters;
	}
	
	public GenericException(String errorMsg, Object[] parameters, String erroCode) {
		this(errorMsg, "000-000-000");
		this.parameters = parameters;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
