package com.iftm.coursepds1.services.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;     //vai repassar a mensagem do titulo
	public ResourceNotFoundException(Object id) {
		super("Resource not found, id " + id);   //usando o id
	}

}
