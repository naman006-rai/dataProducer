package com.benz.dataProducer.Exception;

public class InvalidFileTypeException extends RuntimeException {
		
	    /**
		 * 
		 */
		private static final long serialVersionUID = -6114377109262227178L;
		/**
		 * 
		 */
		
		private String message;
		
	    public InvalidFileTypeException(String message) {
	        super(message);
	        this.message = message;
	    }
	    public InvalidFileTypeException() {
	    }
	}


