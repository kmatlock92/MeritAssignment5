package com.meritamerica.assignment5.assignment5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckingAccount extends BankAccount {

    public CheckingAccount(double openingBalance) { super(openingBalance, 0.0001, new Date()); }

    private CheckingAccount(long accountNumber, double balance, double interestRate, Date date) {
        super(accountNumber, balance, interestRate, date);
    }

    static CheckingAccount readFromString(String accountData) throws ParseException{
        String[] temp = accountData.split(",");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        long accountNum = Long.parseLong(temp[0]);
        double balance = Double.parseDouble(temp[1]);
        double interestRate = Double.parseDouble(temp[2]);
        Date date = dateFormat.parse(temp[3]);

        return new CheckingAccount(accountNum, balance, interestRate, date);
    }
}