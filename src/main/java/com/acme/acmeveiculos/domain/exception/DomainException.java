/**
 * Classe resposável pelo tratamento de exceções das regras de negócio
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.domain.exception;

public class DomainException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DomainException(String message) {
		super(message);
	}
	
}
