package com.utsa.codequantum;

import java.util.Random;
import java.util.Scanner;

import com.utsa.codequantum.account.CheckingAccount;
import com.utsa.codequantum.account.SavingsAccount;

public class CodeQuantumBankGame {
	
	private CheckingAccount checkingAccount;
	private SavingsAccount savingsAccount;
	
	private Random random;
	
	private float dailySalary;
	private int minPriceDollars;
	private int maxPriceDollars;
	
	public CodeQuantumBankGame(int salary, int minPrice, int maxPrice) {
		this.checkingAccount = new CheckingAccount();
		this.savingsAccount = new SavingsAccount();
		
		this.checkingAccount.deposit(500.0f);
		this.savingsAccount.deposit(0f);
		
		this.random = new Random(System.currentTimeMillis());
		
		this.dailySalary = (float) (salary / 261.0); // divide salary by working days per year
		this.minPriceDollars = minPrice;
		this.maxPriceDollars = maxPrice;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("How much do you make per year: ");
		String salary = scanner.nextLine();
		
		System.out.print("What is the minimum price of an item: ");
		String minPrice = scanner.nextLine();
		
		System.out.print("What is the maximum price of an item: ");
		String maxPrice = scanner.nextLine();
		
		
		CodeQuantumBankGame game = new CodeQuantumBankGame(
				Integer.parseInt(salary), Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
		
		game.simulateYear();
	}
	
	public void simulateYear() {
		for(int i = 0; i < 365; ++i) {
			int dayOfWeek = i % 7; // what day in the week is it
			
			int roll = this.random.nextInt(100);
			
			if(dayOfWeek == 0 || dayOfWeek == 6) { //woo its the weekend
				simulateWeekend(roll);
			} else {
				simulateWeekday(roll);
			}
		}
		
		this.printEndOfYearStatus();
	}
	
	private void simulateWeekday(int roll) {
		if(roll < 5) {
			return; // You were sick today and made no money
		}
		
		this.checkingAccount.deposit(this.dailySalary * 0.8f); 
		this.savingsAccount.deposit(this.dailySalary * 0.2f); 
		
		if(roll < 40) { // today you saw something you wanted to buy and bought it
			int chargeAmount = this.random.nextInt(this.maxPriceDollars - this.minPriceDollars) + this.minPriceDollars;
			this.checkingAccount.makeCharge((float)chargeAmount, "Amazon");
		}
	}
	
	private void simulateWeekend(int roll) {
		if(roll < 70) { // today you saw something you wanted to buy and bought it
			int numberOfItemsBought = this.random.nextInt(5);
			
			for(int i = 0; i < numberOfItemsBought; i++) {
				int chargeAmount = this.random.nextInt(this.maxPriceDollars - this.minPriceDollars) + this.minPriceDollars;
				this.checkingAccount.makeCharge((float)chargeAmount, "Amazon");
			}
		}
	}
	
	private void printEndOfYearStatus() {
		System.out.println("You ended the year with ...");
		System.out.println(this.checkingAccount.getBalance() + " in your checking account!");
		System.out.println(this.savingsAccount.getBalance() + " in your savings account!");
		
	}
}
