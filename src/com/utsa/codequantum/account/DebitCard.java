package com.utsa.codequantum.account;

public interface DebitCard {
	String getCardNumber();
	void makeCharge(float amount, String vendor);
}
