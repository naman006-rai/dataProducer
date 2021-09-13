package com.benz.dataProducer.Exception;

public class DataAlreadyExistsException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6114377109262227178L;
	/**
	 * 
	 */
	
	private String message;
	
    public DataAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
    public DataAlreadyExistsException() {
    }
}
