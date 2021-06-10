package com.meritamerica.assignment5.assignment5;

import java.util.HashMap;

public class AccountHolder implements Comparable<AccountHolder> {

    //region InstanceVariables
    private String firstName;
    private String middleName;
    private String lastName;
    private String SSN;
    private final HashMap<Long, CheckingAccount> checkingAccounts;
    private final HashMap<Long, SavingsAccount> savingsAccounts;
    private final HashMap<Long, CDAccount> cdAccounts;
    private final double MAX_NEW_ACCOUNT_BALANCE = 250000.0;
    //endregion

    //region Constructors
    public AccountHolder(String firstName, String middleName, String lastName, String SSN) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.checkingAccounts = new HashMap<>();
        this.savingsAccounts = new HashMap<>();
        this.cdAccounts = new HashMap<>();
    }
    //endregion

    //region Basic Info Getters/Setters
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSSN() {
        return this.SSN;
    }

    public void setSSN(String ssn) {
        this.SSN = ssn;
    }
    //endregion

    //region CheckingAccount Methods
    public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceException, ExceedsFraudSuspicionException {
        return this.addCheckingAccount(new CheckingAccount(openingBalance));
    }

    public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceException, ExceedsFraudSuspicionException {
        if (this.getCheckingBalance() + this.getSavingsBalance() + checkingAccount.getBalance() > MAX_NEW_ACCOUNT_BALANCE){
            throw new ExceedsCombinedBalanceException();
        } else if (checkingAccount.getBalance() > 1000){
            throw new ExceedsFraudSuspicionException();
        }
        //checkingAccount.addTransaction(new DepositTransaction(checkingAccount, checkingAccount.getBalance()));
        this.checkingAccounts.put(checkingAccount.getAccountNumber(), checkingAccount);
        return checkingAccount;
    }

    public HashMap<Long, CheckingAccount> getCheckingAccounts() { return this.checkingAccounts; }

    public int getNumberOfCheckingAccounts() { return this.checkingAccounts.size(); }

    public double getCheckingBalance() {
        double result = 0.0;
        for (CheckingAccount account: this.checkingAccounts.values()) result += account.getBalance();
        return result;
    }
    
    public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceException, ExceedsFraudSuspicionException {
        return this.addSavingsAccount(new SavingsAccount(openingBalance));
    }

    public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceException, ExceedsFraudSuspicionException {
        if (this.getCheckingBalance() + this.getSavingsBalance() + savingsAccount.getBalance() > MAX_NEW_ACCOUNT_BALANCE){
            throw new ExceedsCombinedBalanceException();
        } else if (savingsAccount.getBalance() > 1000){
            throw new ExceedsFraudSuspicionException();
        }
        //savingsAccount.addTransaction(new DepositTransaction(savingsAccount, savingsAccount.getBalance()));
        this.savingsAccounts.put(savingsAccount.getAccountNumber(), savingsAccount);
        return savingsAccount;
    }

    public HashMap<Long, SavingsAccount> getSavingsAccounts() { return this.savingsAccounts; }

    public int getNumberOfSavingsAccounts() { return this.savingsAccounts.size(); }

    public double getSavingsBalance() {
        double result = 0.0;
        for (SavingsAccount account: this.savingsAccounts.values()) result += account.getBalance();
        return result;
    }
    //endregion

    //region CDAccount Methods
    public CDAccount addCDAccount(CDOffering cdOffering, int openingBalance) throws ExceedsFraudSuspicionException{
        return this.addCDAccount(new CDAccount(cdOffering, openingBalance));
    }

    public CDAccount addCDAccount(CDAccount cdAccount) throws ExceedsFraudSuspicionException {
        if (cdAccount.getBalance() > 1000){
            throw new ExceedsFraudSuspicionException();
        }
        //cdAccount.addTransaction(new DepositTransaction(cdAccount, cdAccount.getBalance()));
        this.cdAccounts.put(cdAccount.getAccountNumber(), cdAccount);
        return cdAccount;
    }

    public HashMap<Long, CDAccount> getCDAccounts() { return this.cdAccounts; }

    public int getNumberOfCDAccounts() { return this.cdAccounts.size(); }

    public double getCDBalance(){
        double result = 0.0;
        for (CDAccount account: this.cdAccounts.values()) result += account.getBalance();
        return result;
    }
    //endregion

    // region read/write from strings
    public String writeToString(){
        StringBuilder result = new StringBuilder(this.lastName + "," + this.middleName + "," + this.firstName + "," + this.SSN + "\n");

        result.append(this.getNumberOfCheckingAccounts()).append("\n");
        for (CheckingAccount account: this.checkingAccounts.values()) {
            result.append(account.writeToString()).append("\n");
            result.append(account.getTransactions().size()).append("\n");
            for (Transaction trans: account.getTransactions()){
                result.append(trans.writeToString()).append("\n");
            }
        }

        result.append(this.getNumberOfSavingsAccounts()).append("\n");
        for (SavingsAccount account: this.savingsAccounts.values()) {
            result.append(account.writeToString()).append("\n");
            result.append(account.getTransactions().size()).append("\n");
            for (Transaction trans: account.getTransactions()){
                result.append(trans.writeToString()).append("\n");
            }
        }

        result.append(this.getNumberOfCDAccounts()).append("\n");
        for (CDAccount account: this.cdAccounts.values()) {
            result.append(account.writeToString()).append("\n");
            result.append(account.getTransactions().size()).append("\n");
            for (Transaction trans: account.getTransactions()){
                result.append(trans.writeToString()).append("\n");
            }
        }

        return result.toString();
    }

    public static AccountHolder readFromString(String accountHolderData) throws Exception {
        String[] temp = accountHolderData.split(",");
        if (temp.length != 4) throw new Exception();
        return new AccountHolder(temp[2], temp[1], temp[0], temp[3]);
    }
    // endregion

    public double getCombinedBalance() { return this.getCheckingBalance() + this.getSavingsBalance() + this.getCDBalance(); }

    @Override
    public int compareTo(AccountHolder other) {
        if (this.getCombinedBalance() < other.getCombinedBalance()) return -1;
        else if (this.getCombinedBalance() > other.getCombinedBalance()) return 1;
        else return 0;
    }
}