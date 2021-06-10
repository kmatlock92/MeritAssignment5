package com.meritamerica.assignment5.assignment5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount {

    private final CDOffering offering;

    public CDAccount(CDOffering offering, double balance) {
        super(balance, offering.getInterestRate(), new Date());
        this.offering = offering;
    }

    private CDAccount(long accountNumber, double balance, double interestRate, int term, Date date) {
        super(accountNumber, balance, interestRate, date);
        this.offering = new CDOffering(term, interestRate);
    }

    public int getTerm() { return this.offering.getTerm(); }

    public static CDAccount readFromString(String accountData) throws ParseException {
        String[] temp = accountData.split(",");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        long accountNum = Long.parseLong(temp[0]);
        double balance = Double.parseDouble(temp[1]);
        double interestRate = Double.parseDouble(temp[2]);
        Date date = dateFormat.parse(temp[3]);
        int term = Integer.parseInt(temp[4]);

        return new CDAccount(accountNum, balance, interestRate, term, date);
    }

    public double futureValue(){ return super.futureValue(this.offering.getTerm()); }

    @Override
    public String writeToString() { return super.writeToString() + "," + this.offering.getTerm(); }

    @Override
    public boolean deposit(double amount) { return false; }

    @Override
    public boolean withdraw(double amount) { return false; }


}
