package com.benz.dataProducer.Exception;

public class DataNotFoundException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
    
    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public DataNotFoundException() {
    }
}
