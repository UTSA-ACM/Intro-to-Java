package com.utsa.codequantum.account;

import java.util.Random;

public class CheckingAccount extends Account implements DebitCard {

	@Override
	public String getCardNumber() {
		Random random = new Random(System.currentTimeMillis());
		
		String cardNumber = "";
		
		for(int i = 0; i < 16; i++) {
			cardNumber += random.nextInt(10); // generates 0 - 9
			
			// every 4 digits there is a space
			if(i % 4 == 0) {
				cardNumber += " ";
			}
		}
		
		return cardNumber;
	}

	@Override
	public void makeCharge(float amount, String vendor) {
		System.out.println("Card swiped at " + vendor + " for " + amount);
		
		try {
			super.withdraw(amount);
		} catch (AccountOverdrawException e) {
			System.out.println("Card declined due to insufficent funds!");
		}
	}	
}
