package com.meritamerica.assignment5.assignment5;

public class MeritAmericaBankApp {
	public static void main(String[] args) {
		MeritBank.readFromFile("src/main/MeritBank_TestFile.txt");
		MeritBank.writeToFile("src/main/MeritBank_TestWrite.txt");
	}
}