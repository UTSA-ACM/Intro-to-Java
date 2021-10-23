package com.utsa.codequantum.account;

public class AccountOverdrawException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public AccountOverdrawException(String message) {
		super(message);
	}
}
