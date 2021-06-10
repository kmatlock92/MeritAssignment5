package com.meritamerica.assignment5.assignment5;

public class CDOfferings {

    private final int term;
    private final double interestRate;

    public CDOfferings(int term, double interestRate){
        this.term = term;
        this.interestRate = interestRate;
    }

    public int getTerm() { return this.term; }

    public double getInterestRate() { return this.interestRate; }

    static CDOfferings readFromString(String cdOfferingDataString){
        String[] temp = cdOfferingDataString.split(",");
        return new CDOfferings(Integer.parseInt(temp[0]), Double.parseDouble(temp[1]));
    }

    public String writeToString() { return this.term + "," + this.interestRate; }

	public static void add(CDOfferings cdOfferings) {
		// TODO Auto-generated method stub
		
	}
}
