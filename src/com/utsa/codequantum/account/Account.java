package com.utsa.codequantum.account;

public abstract class Account {
	private float balance;
	private float intrestRate;
	
	public float getBalance() {
		return this.balance;
	}

	public void deposit(float amount) {
		this.balance += amount;
	}

	public float withdraw(float amount) throws AccountOverdrawException {
		
		//check if we have the money first
		if(this.balance < amount) {
			throw new AccountOverdrawException(
					"Account attempted to withdraw " + amount + " but only has balance of " + this.balance);
		}
		
		this.balance -= amount;
		
		return this.balance;
	}

	public void setIntrestRate(float percent) {
		this.intrestRate = percent;
	}

	public float getIntrestRate() {
		return this.intrestRate;
	}
}
