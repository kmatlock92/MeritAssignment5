package com.meritamerica.assignment5.assignment5;

import java.text.ParseException;
import java.util.*;

public abstract class BankAccount {

    // region instance variables
    private final Date accountOpenedOn;
    private final Long accountNumber;
    private final double interestRate;
    private double balance;
    private final ArrayList<Transaction> transactions = new ArrayList<>();
    // endregion

    // region constructors
    BankAccount(double balance, double interestRate){
        this(MeritBank.getNextAccountNumber(), balance, interestRate, new Date());
    }

    BankAccount(double balance, double interestRate, Date accountOpenedOn){
        this(MeritBank.getNextAccountNumber(), balance, interestRate, accountOpenedOn);
    }

    protected BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountOpenedOn = accountOpenedOn;
    }
    // endregion

    // region getters/setters
    public long getAccountNumber() { return this.accountNumber; }

    public double getBalance() { return this.balance; }

    public double getInterestRate() { return this.interestRate; }

    public Date getOpenedOn() { return this.accountOpenedOn; }

    public boolean withdraw(double amount){
        if (this.balance - amount < 0) {
            return false;
        } else {
            this.balance -= amount;
            return true;
        }
    }

    public boolean deposit (double amount){
        if (amount < 0) {
            return false;
        } else {
            this.balance += amount;
            return true;
        }
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions() { return transactions; }
    // endregion

    // region read/write from string
    static BankAccount readFromString(String accountData) throws ParseException { return null; }

    public String writeToString() {
        return this.accountNumber + "," + this.balance + "," + String.format("%.4f", this.interestRate) + "," +
                (this.accountOpenedOn.getDate() + 1) + "/" +
                this.accountOpenedOn.getMonth() + "/" +
                (this.accountOpenedOn.getYear() + 1900);
    }
    // endregion

    public double futureValue(int years) { return MeritBank.recursiveFutureValue(this.balance, this.interestRate, years); }
}
