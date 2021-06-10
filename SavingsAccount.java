package com.meritamerica.assignment5.assignment5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(double openingBalance) { super(openingBalance, 0.01, new Date()); }

    private SavingsAccount(long accountNumber, double balance, double interestRate, Date date) {
        super(accountNumber, balance, interestRate, date);
    }

    static SavingsAccount readFromString(String accountData) throws ParseException {
        String[] temp = accountData.split(",");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        long accountNum = Long.parseLong(temp[0]);
        double balance = Double.parseDouble(temp[1]);
        double interestRate = Double.parseDouble(temp[2]);
        Date date = dateFormat.parse(temp[3]);

        return new SavingsAccount(accountNum, balance, interestRate, date);
    }
}