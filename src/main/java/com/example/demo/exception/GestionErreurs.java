package com.example.demo.exception;

public class GestionErreurs extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GestionErreurs(String message) {
		super(message);
	}
}
